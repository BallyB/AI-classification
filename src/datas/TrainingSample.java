package datas;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class TrainingSample.
 */
public class TrainingSample {

	
	/** The id. */
	public int ID;
	private List<Integer> parameters;
	/**
	 * Instantiates a new training sample.
	 *
	 * @param lx the lx
	 * @param ly the ly
	 * @param lz the lz
	 * @param rx the rx
	 * @param ry the ry
	 * @param rz the rz
	 * @param ID the id
	 */
	public TrainingSample(List<Integer> params, int ID){
		this.parameters = params;
		this.ID = ID;
	}
	
	
	/**
	 * Sets the id.
	 *
	 * @param iD the new id
	 */
	public void setID(int iD) {
		ID = iD;
	}


	public List<Integer> getParameters() {
		return parameters;
	}


	public void setParameters(List<Integer> parameters) {
		this.parameters = parameters;
	}


	public int getID() {
		return ID;
	}
}
