package regression;

import java.util.ArrayList;
import java.util.List;


/**
 * The Class DataManager which will contain all the database
 */
public class DataManager {

	
	/** The list of sample used for the regression. */
	public List<SampleTiredness> sfs;
	
	/**
	 * Instantiates a new data manager.
	 */
	public DataManager(){
		this.sfs = new ArrayList<>();
	}
	
	/**
	 * Adds a sample.
	 *
	 * @param sf the sample
	 */
	public void addSample(SampleTiredness sf) {
		this.sfs.add(sf);
		
	}

	/**
	 * Gets the all training sample.
	 *
	 * @return the list of all training sample
	 */
	public List<SampleTiredness> getAllTrainingSample(){
		return sfs;
	}
	
	
}
