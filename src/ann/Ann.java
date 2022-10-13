package ann;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import datas.LearningfinishedCallback;
import datas.TrainingDataManager;
import datas.TrainingSample;


public class Ann {
	
	protected TrainingDataManager tdm;
	private List<Double> listTrainingError;
	private List<Double> listTestError;
	private List<Connector> listConnectors;
	private List<Neuron> listNeurons;
	private ArrayList<NeuronInput> listNeuronsInput;
	private ArrayList<ArrayList<NeuronHidden>> listNeuronsHidden;
	private ArrayList<NeuronOutput> listNeuronsOutput;
	private List<TrainingSample> completeLearningDataBase;
	private List<TrainingSample> testDataBase;
	final static double STOP_CRITERIA = 0.025;
	private int numberofhiddenlayer;
	
	public Ann(int dimensions, int classNumber, LearningfinishedCallback lc){
		this.init(dimensions, classNumber);
		lc.informLearningStatus(true);
		
	}
	
	private void init(int dimensions, int classNumber){
		this.listTrainingError = new ArrayList<Double>();
		this.listTestError = new ArrayList<Double>();
		this.listConnectors = new ArrayList<Connector>();
		this.listNeurons = new ArrayList<Neuron>();
		this.listNeuronsInput = new ArrayList<NeuronInput>();
		this.listNeuronsHidden = new ArrayList<ArrayList<NeuronHidden>>();
		this.listNeuronsOutput = new ArrayList<NeuronOutput>();
		this.tdm = new TrainingDataManager();
		this.completeLearningDataBase = tdm.getAllTrainingSamples();
		System.out.print("How many hidden layer ? ");
		Scanner sc = new Scanner(System.in);
		this.numberofhiddenlayer = sc.nextInt();
		//sc.close();
		
		
		this.constructNetwork(dimensions, numberofhiddenlayer, classNumber);
		this.previousTreatment();
		
		this.extractTest();
		this.calibrateNetwork();
		GraphPanel.createAndShowGui(listTrainingError);
		GraphPanel.createAndShowGui(listTestError);
	}
	
	
	private void extractTest() {
		testDataBase = new ArrayList<TrainingSample>();
		System.out.println(completeLearningDataBase.size());
		for (int i = 0; i < 100; i++) {
			TrainingSample ts = completeLearningDataBase.remove(0);
			testDataBase.add(ts);
		}
		
		
		
	}

	public int getPostureID(double PostureData[]){
		for (int j = 0; j < listNeuronsInput.size(); j++) {					
			listNeuronsInput.get(j).setSynapticValue((double)PostureData[j]/1024);
		}
	//	System.out.print(listNeuronsInput.get(0).getSynapticValue()+" ");
	//	System.out.print(listNeuronsInput.get(1).getSynapticValue()+" ");
	//	System.out.print(listNeuronsInput.get(2).getSynapticValue()+" ");
	//	System.out.print(listNeuronsInput.get(3).getSynapticValue()+" \n ");
		forwardPropagation();
		
		double synapticValue = Double.MIN_VALUE;
		int label = 0;
	//	System.out.println("Values");
		for (NeuronOutput neuron : listNeuronsOutput) {
		//	System.out.println(neuron.getSynapticValue());
				if(neuron.getSynapticValue() > synapticValue){
					synapticValue = neuron.getSynapticValue();
					label = Integer.parseInt(neuron.getLabel());
			}
		}
		//System.out.println("Etiquette :"+label);
		return label;
	}
	
	private void calibrateNetwork() {
		System.out.println("Learning... \n");
		double currentPropagationError = 0.9;
		double oldPropagationError = 1;
		int cmpt = 0;
		/* Stabilisation loop of the network */
	//	while (oldPropagationError > currentPropagationError) {
		for (int r = 0; r < 200; r++) {
			
		//	System.out.println(r);
			//System.out.println("Number of loop on the all learning base :"+cmpt+" Erreur old "+oldPropagationError+", Erreur current"+currentPropagationError);

			oldPropagationError = currentPropagationError;
			currentPropagationError = 0;
			cmpt++;
			
			/* Loop on all the training base */
			for (int i = 0; i < completeLearningDataBase.size(); i++) {
				
				/* Initializing Input neurons */
				for (int j = 0; j < listNeuronsInput.size(); j++) {			
				//	System.out.println((double)completeLearningDataBase.get(i).getParameters().get(j)/1024);
						listNeuronsInput.get(j).setSynapticValue((double)completeLearningDataBase.get(i).getParameters().get(j)/1024);
				}
				
				/*System.out.println("Neurones entrées ");
				System.out.print(listNeuronsInput.get(0).getSynapticValue()+" ");
				System.out.print(listNeuronsInput.get(1).getSynapticValue()+" ");
				System.out.print(listNeuronsInput.get(2).getSynapticValue()+" ");
				System.out.print(listNeuronsInput.get(3).getSynapticValue()+" ");*/
				
				//System.out.println(cmpt);
				
				
				int expectedResult = completeLearningDataBase.get(i).getID();
//				System.out.println("classe attendue  :"+expectedResult);
				for (int j = 0; j < listNeuronsOutput.size(); j++) {
					listNeuronsOutput.get(j).setExpectedValue(0);
				}
				listNeuronsOutput.get(expectedResult-1).setExpectedValue(1);
				
//				System.out.println("Neurones sorties");
//					System.out.print(listNeuronsOutput.get(j).getExpectedValue()+" ");
//				System.out.println("");
				
				forwardPropagation();
//				System.out.println("FIN DU FORWARD -------------------------------------------");
				currentPropagationError += quadraticError();
				backPropagation();
//				System.out.println(" FIN BACK PROP ---------------------------------------------\n");
				updateWeights();
			}
			currentPropagationError = (double)((double)currentPropagationError/(double)completeLearningDataBase.size());
			double testError = 0;
			for (int i = 0; i < testDataBase.size(); i++) {
				int size = testDataBase.get(i).getParameters().size();
				double[] tab = new double[size];
				for (int j = 0; j < size; j++) {
					tab[j] = testDataBase.get(i).getParameters().get(j);
			//		System.out.print(testDataBase.get(i).getParameters().get(j)+" ");
				}
				int id = getPostureID(tab);
			//	System.out.print(testDataBase.get(i).getID()+"\n");
			//	System.out.println("Found :"+id);
				if (id!=testDataBase.get(i).ID)
					testError++;
			}
			
			System.out.println(testError);
			
			listTrainingError.add(currentPropagationError);
			listTestError.add((double)testError);
		}
		System.out.println(" Finished.\n");
		System.out.println("Average of finale propagation error = " + currentPropagationError + "\n");
		System.out.println("Values");
		for (int j = 0; j < listNeuronsOutput.size(); j++) {
			System.out.println(listNeuronsOutput.get(j).getSynapticValue());
		}
		System.out.println("");
	}

	
	private void backPropagation() {
		
//		System.out.println("BACK PROPAGATION -----------------------------------------------");
		
//		System.out.println("OUTPUT --------------------------------------------------");
		for (NeuronOutput neuron : listNeuronsOutput) {
			//neuroneSortie.setDelta(neuroneSortie.getValeurAttendue() - erreurPropagation);
			neuron.setDelta(derivatedLogistic(neuron.getSynapticValue())*(neuron.getExpectedValue()-neuron.getSynapticValue()));
			// deriveTanH(neuron.getSynapticValue())*
			//System.out.println(derivatedLogistic(neuron.getSynapticValue())*(neuron.getExpectedValue()-neuron.getSynapticValue()));
			//derivatedLogistic(neuron.getSynapticValue())*
//			System.out.println("Delta = ("+neuron.getExpectedValue()+" - "+neuron.getSynapticValue()+") * "+derivatedLogistic(neuron.getSynapticValue())+"");
//			System.out.println("Delta = "+neuron.getDelta());
		}
//		System.out.println("COUCHE CACHE --------------------------------------------------");
		for (int o = listNeuronsHidden.size()-1; o >= 0 ; o--) {
			for (NeuronHidden neuron : listNeuronsHidden.get(o)) {
//				System.out.println("Je suis le neurone "+neuron.getLabel());
				double sum = 0;
				double delta = 0;
				ArrayList<Neuron> successors = (ArrayList<Neuron>) successor(neuron);
				for (int i = 0; i < successors.size(); i++) {
//					System.out.println("successeur "+successors.get(i).getLabel());
//					System.out.println("poids arêtes * delta dest = "+getConnector(neuron, successors.get(i)).getSynapticWeight()+"*"+successors.get(i).getDelta());
					sum += getConnector(neuron, successors.get(i)).getSynapticWeight() * successors.get(i).getDelta();
					//System.out.println(sum);
				}
				delta = derivatedLogistic(neuron.getSynapticValue()) * sum;
//				System.out.println("delta dans "+neuron.getLabel()+" est donc "+delta);
				neuron.setDelta(delta);
			}
		}
		
		
	}
	
	private double deriveTanH(double x) {
		return 1.0 - (Math.tanh(x) * Math.tanh(x));
	}
	
	private double tangenteHyperbolic(double x) {
		//System.out.println("tanH : "+Math.tanh(x));
		return Math.tanh(x);
	}

	private double quadraticError() {
		double error = 0;
		for (NeuronOutput neuron : listNeuronsOutput) {
				//System.out.println(neuroneSortie.getValeurSynaptique());
				error += (neuron.getExpectedValue() - neuron.getSynapticValue())
						* (neuron.getExpectedValue() - neuron.getSynapticValue());
		}
		error = error * 0.5;
		return error;
	}
	
	private double entropieRelative() {
		double erreur = 0;
		for (NeuronOutput neurone : listNeuronsOutput) {		
				erreur += (-neurone.getExpectedValue()
						* Math.log((double)(neurone.getSynapticValue() / neurone.getExpectedValue())));
		}
		erreur = erreur * 0.5;
		return erreur;
	}

	
	private void forwardPropagation() {
//		System.out.println("Forward ----------------------------------");
		/* Hidden neurons treatment */
//		System.out.println("COUCHE CACHE-------------------------------------------------");
		for (int i = 0; i < listNeuronsHidden.size(); i++) {
			for (NeuronHidden neuronh : listNeuronsHidden.get(i)) {
				double potentialPostSynaptic = potentialPostSynaptique(neuronh);
				
				//System.out.println(neuronh.getSynapticValue()+"   ma vauelur ");
				neuronh.setSynapticValue(logistic(potentialPostSynaptic));
//				System.out.println("Je mets dans "+neuronh.getLabel()+" la logistic de "+potentialPostSynaptic+" et c'est "+neuronh.getSynapticValue());
			}
		}
		
	//	System.out.println("COUCHE OUTPUT-----------------------------------------------");
		/* last (output) layer treatment */
		for (Neuron neurono : listNeuronsOutput) {
	//		System.out.println(softMax(neurono, denominatorSoftMax()));
			
			neurono.setSynapticValue(softMax(neurono, denominatorSoftMax()));
		//	double potentialPostSynaptic = potentialPostSynaptique(neurono);
		//	neurono.setSynapticValue(logistic(potentialPostSynaptic));
//			System.out.println("Je mets dans "+neurono.getLabel()+" la logistic de "+potentialPostSynaptic+" et c'est "+neurono.getSynapticValue());
		}
		
	}
	
	private double relu(double x){
		//System.out.println("ReLU : "+Math.log(1.0 + Math.exp(x)));
		return Math.log(1.0 + Math.exp(x));
	}
	
	private double deriveRelu(double x){
		return logistic(x);
	}
	
	private List<Neuron> successor(Neuron node) {
		ArrayList<Neuron> res = new ArrayList<>();
		for (Connector clef : listConnectors) {
			if (clef.getSource().equals(node))
				res.add(clef.getDestination());
		}
		return res;
	}
	
	public void updateWeights() {
		double learningStep = 0.1;
//		System.out.println("MAJ DES POIDS-----------------");
		for (Connector cnct : listConnectors) {
	//		System.out.println("arete de "+cnct.getSource().getLabel()+" à "+cnct.getDestination().getLabel());
	//		System.out.println("poids courant : "+cnct.getSynapticWeight());
			double newWeight = cnct.getSynapticWeight()
					+ learningStep * cnct.getSource().getSynapticValue() * cnct.getDestination().getDelta();
			cnct.setPoidsSynaptique(newWeight);
	//		System.out.println("nouveau poids : "+newWeight);
		}

	}
	
	private double potentialPostSynaptique(Neuron neuron) {
		double result = 0;
//		System.out.println("Je suis : "+neuron.getLabel());
		ArrayList<Neuron> ancestors = (ArrayList<Neuron>) ancestor(neuron);
		for (int i = 0; i < ancestors.size(); i++) {
//			System.out.println("Precedent : "+ancestors.get(i).getLabel());
//		System.out.println("Poids arrete reliant : "+getConnector(ancestors.get(i), neuron).getSynapticWeight());
//			System.out.println("Valeur Synaptique : "+ancestors.get(i).getSynapticValue());
			
			
			result += getConnector(ancestors.get(i), neuron).getSynapticWeight()
					* ancestors.get(i).getSynapticValue();
		}
//		System.out.println("Potentiel post synaptique : "+result);
		return result;
	}
	
	private double denominatorSoftMax() {
		double res = 0;
		for (NeuronOutput neuron : listNeuronsOutput) {
			res += Math.exp(potentialPostSynaptique(neuron));
		}
		return res;
	}
	
	private double numeratorSoftMax(Neuron neuron) {
		return Math.exp(potentialPostSynaptique(neuron));
	}


	private double softMax(Neuron neuron, double denominator) {
		return numeratorSoftMax(neuron) / denominator;
	}
	
	
	private List<Neuron> ancestor(Neuron node) {
		ArrayList<Neuron> resultat = new ArrayList<Neuron>();
		for (Connector c : listConnectors) {
			if (c.getDestination().equals(node))
				resultat.add(c.getSource());
		}
		return resultat;
	}

	private double logistic(double x) {
		return 1.0 / (1.0 + Math.exp(-x));
	}

	private double derivatedLogistic(double x) {
		return logistic(x) * (1.0 - logistic(x));
	}
	
	private void previousTreatment() {
		Collections.shuffle(this.completeLearningDataBase);
		
		initializeWeights();	
	}

	

	private void initializeWeights() {
		for (Connector cnct : listConnectors) {
		//	Random r = new Random();
		//	double randomValue = -0.3 + (0.3 - -0.3) * r.nextDouble();
			Random r = new Random();
			double randomValue = (0.2 * r.nextDouble()) - 0.1;
			cnct.setPoidsSynaptique(randomValue);
		}
		
	}

	private void constructNetwork(int numberOfInputNeuron, int numberOfHiddenLayer, int classNumber) {
		
		
		
		
		
		System.out.println("Creating neurons... ");
		
		// Inputs
		
		for (int i = 1; i < numberOfInputNeuron+1; i++) {
			NeuronInput ni = new NeuronInput(10+i, "i"+i+"");
			listNeuronsInput.add(ni);
			listNeurons.add(ni);
		}
		
		/*   */
		
	//	System.out.println(listNeurons.size());
		//Hidden
		ArrayList<NeuronHidden> firsthiddenlayer = new ArrayList<NeuronHidden>();
		for (int i = 1; i < numberOfInputNeuron+1; i++) {
			NeuronHidden ni = new NeuronHidden(20+i, "h"+i+"", 1);
			firsthiddenlayer.add(ni);
			listNeurons.add(ni);
		}
		listNeuronsHidden.add(firsthiddenlayer);
		
		
		

		for (int i = 0; i < numberOfHiddenLayer; i++) {
			ArrayList<NeuronHidden> listnh = new ArrayList<NeuronHidden>();
			
			System.out.print("How many neurons for layer number "+(i+1)+" ?");
			Scanner sc = new Scanner(System.in);
			int nbofn = sc.nextInt();
			//sc.close();
			for (int j = 1; j < nbofn+1; j++) {
				NeuronHidden ni = new NeuronHidden(((i+3)*10)+j, "h"+j+"", i+1);
				listnh.add(ni);
				listNeurons.add(ni);
			}
			listNeuronsHidden.add(listnh);
			
		}
		//Outputs
		
		for (int i = 1; i < classNumber+1; i++) {
			NeuronOutput ni = new NeuronOutput(30+i, ""+i+"");
			listNeuronsOutput.add(ni);
			listNeurons.add(ni);
		}
		
		System.out.println("Creating connections... ");
		
		for (int i = 0; i < listNeuronsInput.size(); i++) {
			for (int j = 0; j < listNeuronsHidden.get(0).size(); j++) {
				Connector c = new Connector(listNeuronsInput.get(i),listNeuronsHidden.get(0).get(j));
				listConnectors.add(c);
			}
		}
		
		for (int i = 0; i < listNeuronsHidden.size()-1; i++) {
			for (int j = 0; j < listNeuronsHidden.get(i).size(); j++) {
				for (int j2 = 0; j2 < listNeuronsHidden.get(i+1).size(); j2++) {
					Connector c = new Connector(listNeuronsHidden.get(i).get(j),listNeuronsHidden.get(i+1).get(j2));
					listConnectors.add(c);
				}
				
			}
		}
		
		
		for (int i = 0; i < listNeuronsHidden.get(listNeuronsHidden.size()-1).size(); i++) {
			for (int j = 0; j < listNeuronsOutput.size(); j++) {
				Connector c = new Connector(listNeuronsHidden.get(listNeuronsHidden.size()-1).get(i),listNeuronsOutput.get(j));
				listConnectors.add(c);
			}
		}
		System.out.println("MLP ready (" + this.listNeurons.size() + " neuron(s) et " + this.listConnectors.size()
		+ " connector(s)).");
		
	}

	public void addConnector(Connector connector) {
		listConnectors.add(connector);
	}

	private Connector getConnector(Neuron source, Neuron destination) {
		Connector resultat = null;
		for (Connector cnt : listConnectors) {
			if (cnt.getSource().equals(source) && cnt.getDestination().equals(destination))
				resultat = cnt;
		}
		return resultat;
	}
	
	public void addNeuron(Neuron neuron) {
		listNeurons.add(neuron);
	}
	
	

}
