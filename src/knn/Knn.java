package knn;


import java.util.ArrayList;
import java.util.List;

import datas.TrainingDataManager;
import datas.TrainingSample;

// TODO: Auto-generated Javadoc
/**
 * The Class Knn.
 */
public class Knn {

	
	
	/** The k. */
	public int k;
	
	/** The entries. */
	public double[][] entries;
	
	/** The labels. */
	public int[] labels;
	
	/** The class number. */
	public int classNumber;
	
	/** The dimension. */
	protected int dimension = 6;
	
	/** The tdm. */
	protected TrainingDataManager tdm;
	
	/**
	 * Instantiates a new knn.
	 *
	 * @param k the k
	 */
	public Knn(int k){
		this.init(k);
	}
	
	/**
	 * Inits the.
	 *
	 * @param k the k
	 */
	private void init(int k){
		this.k = k;
		this.tdm = new TrainingDataManager();
	}
	
	/**
	 * Function distance.
	 *
	 * @param a first vector of entries
	 * @param b second vector of entries
	 * @return distance between the two vectors
	 */
	private double distance(double[] a, double[] b) {
		double dist = 0.;
		int dim = a.length;
		for(int i = 0; i < dim; i++){
			dist += (double)Math.abs(b[i]-a[i]);
			
		}
		return dist;
	}
	
	
	/**
	 * Gets the posture ID.
	 *
	 * @param PostureData the posture data
	 * @return the posture ID
	 */
	public int getPostureID(double PostureData[]){
		List<TrainingSample> l = tdm.getAllTrainingSamples();
		setClassNumber(l);
		this.entries = new double[l.size()][this.dimension];
		this.labels = new int[l.size()];
		for (int j = 0; j < l.size(); j++) {
			for (int i = 0; i < dimension; i++) {
				entries[j][i] = l.get(j).getParameters().get(i);
			}
			labels[j] = l.get(j).getID();
				
		}
	/*	for (int i = 0; i < entries.length; i++) {
			System.out.println("");
			for (int j = 0; j < entries[0].length; j++) {
				System.out.print(entries[i][j]+" ");
			}
			System.out.print(labels[i]);
		}
		*/
		double[] distanceTable = new double[l.size()];
		double[][] Kmin = new double[k][2];
		
		for (int i = 0; i < l.size(); i++) {
			distanceTable[i] = distance(entries[i],PostureData);
		}
		
		for (int i = 0; i < Kmin.length; i++) {
			
			double min = Integer.MAX_VALUE;
			int indice = 0;
			for (int j = 0; j < distanceTable.length; j++) {
				if(distanceTable[j]<min){
					min = distanceTable[j];
					indice = j;
					
				}
			}
			Kmin[i][0] = min;
			Kmin[i][1] = indice;
			distanceTable[indice]= Integer.MAX_VALUE;
			
		}
		int Res[] = new int[k];
		for (int i = 0; i < Kmin.length; i++) {
			Res[i] = labels[(int) Kmin[i][1]];
		}
		int[] Q = new int[classNumber+1];
		for (int i = 0; i < Res.length; i++) {
			Q[Res[i]]++;
		}
		int max = 0;
		int indice = 0;
		for (int i = 0; i < Q.length; i++) {
			
			if(max < Q[i]){
				max = Q[i];
				indice = i;
			}
			
		}
		System.out.println(indice);
		return indice;
	}
	

	/**
	 * Sets the class number.
	 *
	 * @param l the new class number
	 */
	private void setClassNumber(List<TrainingSample> l){
		ArrayList<Integer> listClasse = new ArrayList<Integer>();
		for (int i = 0; i < l.size(); i++) {
			int tmpid = l.get(i).getID();
			if(!listClasse.contains(tmpid)){
				listClasse.add(tmpid);
			}
		}
		this.classNumber = listClasse.size();
	}
}
