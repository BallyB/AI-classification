package datas;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The Class TrainingDataManager.
 */
public class TrainingDataManager {

	
	
	/** The list. */
	protected List<TrainingSample> list;
	

	protected List<TrainingSample> listRightFeet;
	protected List<TrainingSample> listLeftFeet;
	
	/**
	 * Instantiates a new training data manager.
	 */
	public TrainingDataManager(){
		
		
	//	list = new ArrayList<TrainingSample>();
		
		
	/*	ArrayList<Integer> p = new ArrayList<Integer>();
		p.addAll(Arrays.asList(24, -98, 1003, 30, 38, 959));
		list.add(new TrainingSample(p,1));
		p = new ArrayList<Integer>();
		p.addAll(Arrays.asList(24, -97, 1005, 30, 38, 961));
		list.add(new TrainingSample(p,1));
		p = new ArrayList<Integer>();
		p.addAll(Arrays.asList(24, -98, 1003, 30, 38, 959));
		list.add(new TrainingSample(p,1));
		p = new ArrayList<Integer>();
		p.addAll(Arrays.asList(21, 843, 509, 146, 854, 491));
		list.add(new TrainingSample(p,2));
		p = new ArrayList<Integer>();
		p.addAll(Arrays.asList(41, 828, 533, 155, 855, 486));
		list.add(new TrainingSample(p,2));
		p = new ArrayList<Integer>();
		p.addAll(Arrays.asList(67, 804, 566, 140, 850, 493));
		list.add(new TrainingSample(p,2));
		p = new ArrayList<Integer>();
		p.addAll(Arrays.asList(24, -92, 1006, 149, 977, -329));
		list.add(new TrainingSample(p,3));
		p = new ArrayList<Integer>();
		p.addAll(Arrays.asList(24, -93, 1005, 104, 1008, -39));
		list.add(new TrainingSample(p,3));
		
		
		
*/
		
		try {
			list = lectureFichier("src/iris.data.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Collections.shuffle(list);
	}
	
	
	public List<TrainingSample> getAllRightTrainingSamples() {
		return listRightFeet;
	}
	public List<TrainingSample> getAllLeftTrainingSamples() {
		return listLeftFeet;
	}

	public void addLeftSample(int lx, int ly, int lz, int rx, int ry, int rz, int ID){
		List<Integer> p = new ArrayList<>();
		p.add(lx);
		p.add(ly);
		p.add(lz);
		p.add(rx);
		p.add(ry);
		p.add(rz);
		TrainingSample ts = new TrainingSample(p, ID);
		listLeftFeet.add(ts);
	}
	
	public void addRightSample(int lx, int ly, int lz, int rx, int ry, int rz, int ID){
		List<Integer> p = new ArrayList<>();
		p.add(lx);
		p.add(ly);
		p.add(lz);
		p.add(rx);
		p.add(ry);
		p.add(rz);
		TrainingSample ts = new TrainingSample(p, ID);
		listRightFeet.add(ts);
	}
	
	private ArrayList<TrainingSample> lectureFichier(String fichier) throws IOException {
		@SuppressWarnings("unused")
		int tailleBase = 0;
		BufferedReader br1, br2;
		String st;
		ArrayList<TrainingSample> fleurs = new ArrayList<TrainingSample>();
		br1 = new BufferedReader(new FileReader(fichier));
		System.out.print("Lecture du fichier IRIS... ");
		while ((st = br1.readLine()) != null) {
			tailleBase++;
		}
		br1.close();
		String[] separated;
		br2 = new BufferedReader(new FileReader(fichier));
		ArrayList<Integer> p;
		while ((st = br2.readLine()) != null) {
			p = new ArrayList<Integer>();
			separated = st.split(",");
			double loS = Double.parseDouble(separated[0]);
			double laS = Double.parseDouble(separated[1]);
			double loP = Double.parseDouble(separated[2]);
			double laP = Double.parseDouble(separated[3]);
			String c = separated[4];
			p.addAll(Arrays.asList((int)(loS*10), (int)(laS*10), (int)(loP*10), (int)(laP*10)));
			int classe = 0;
			if(c.equals("Iris-setosa"))classe = 1;
			if(c.equals("Iris-versicolor"))classe = 2;
			if(c.equals("Iris-virginica"))classe = 3;
			fleurs.add(new TrainingSample(p, classe));
			//p. = new Fleur(loS, laS, loP, laP, c);
			//fleurs.add(f);
		}
		br2.close();
		System.out.println(" Termine\n");
		return fleurs;
	}
	
	/**
	 * Gets the all training samples.
	 *
	 * @return the all training samples
	 */
	public List<TrainingSample> getAllTrainingSamples(){
		return list;
	}
	
	
}
