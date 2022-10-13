package regression;

import java.util.List;

/**
 * The Class SampleTiredness.
 */
public class SampleTiredness {

	/** The tireness level. */
	public int tiredness;
	
	/** The parameters : distance, time, cadence (in this order) */
	private List<Integer> parameters;
	
	/**
	 * Instantiates a new sample tiredness.
	 *
	 * @param params the params
	 * @param id the id
	 */
	public SampleTiredness(List<Integer> params, int id){
		this.tiredness = id;
		this.parameters = params;
		
	}

	/**
	 * Gets the tiredness level.
	 *
	 * @return the level
	 */
	public int getTiredness() {
		return tiredness;
	}

	/**
	 * Sets the tiredness level.
	 *
	 * @param tiredness the new tiredness value
	 */
	public void setTiredness(int tiredness) {
		this.tiredness = tiredness;
	}

	/**
	 * Gets the parameters.
	 *
	 * @return the parameters
	 */
	public List<Integer> getParameters() {
		return parameters;
	}

	/**
	 * Sets the parameters.
	 *
	 * @param parameters the new parameters (distance, time, cadence)
	 */
	public void setParameters(List<Integer> parameters) {
		this.parameters = parameters;
	}
}
