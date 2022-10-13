package posture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;






public class NeuralNetwork {

	
	
	public int id;
	private List<Connector> listConnectors;
	private List<Neuron> listNeurons;
	private ArrayList<InputNeuron> listNeuronsInput;
	private ArrayList<HiddenNeuron> listNeuronsHidden;
	private ArrayList<OutputNeuron> listNeuronsOutput;
	
	public NeuralNetwork(int id){
		this.id = id;
		this.listConnectors = new ArrayList<Connector>();
		this.listNeurons = new ArrayList<Neuron>();
		this.listNeuronsInput = new ArrayList<InputNeuron>();
		this.listNeuronsHidden = new ArrayList<HiddenNeuron>();
		this.listNeuronsOutput = new ArrayList<OutputNeuron>();
		this.constructNetwork();
		this.initializeWeights();
	}
	
	
	private void initializeWeights() {
		for (Connector cnct : listConnectors) {
			Random r = new Random();
			double randomValue = (0.2 * r.nextDouble()) - 0.1;
			cnct.setSynapticWeight(randomValue);
		}
		
	}
	
	private void calibrateNetwork(){
		System.out.println("Learning... \n");
		
		
	}
	
	public void constructNetwork(){
		System.out.println("Creating neurons... ");
		for (int i = 1; i < 7; i++) {
			InputNeuron ni = new InputNeuron(10+i, "i"+i+"");
			listNeuronsInput.add(ni);
			listNeurons.add(ni);
		}
		for (int i = 1; i < 7; i++) {
			HiddenNeuron ni = new HiddenNeuron(20+i, "h"+i+"", 1);
			listNeuronsHidden.add(ni);
			listNeurons.add(ni);
		}
		OutputNeuron ni = new OutputNeuron(31, ""+1+"");
		listNeuronsOutput.add(ni);
		listNeurons.add(ni);
		
		System.out.println("Creating connectors");
		for (int i = 0; i < listNeuronsInput.size(); i++) {
			for (int j = 0; j < listNeuronsHidden.size(); j++) {
				Connector c = new Connector(listNeuronsInput.get(i),listNeuronsHidden.get(j));
				listConnectors.add(c);
			}
		}
		for (int i = 0; i < listNeuronsHidden.size(); i++) {
			for (int j = 0; j < listNeuronsOutput.size(); j++) {
				Connector c = new Connector(listNeuronsHidden.get(i),listNeuronsOutput.get(j));
				listConnectors.add(c);
			}
		}
		System.out.println("MLP ready (" + this.listNeurons.size() + " neuron(s) et " + this.listConnectors.size()
		+ " connector(s)).");
	}
	
}
