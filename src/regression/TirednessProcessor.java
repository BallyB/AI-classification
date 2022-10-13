package regression;

import java.util.ArrayList;

/**
 * The Class TirednessProcessor.
 */
public class TirednessProcessor {

	/** The dm. */
	private DataManager dm;
	
	/** The rg. */
	private Regression rg;
	
	/** boundaries of tiredness level **/
	private int min,max;
	
	/**
	 * Instantiates a new tiredness processor.
	 * @param max value of tireness level
	 * @param min respectively min
	 */
	public TirednessProcessor(int min, int max){
		this.dm = new DataManager();
		this.min = min;
		this.max = max;
	}
	
	/**
	 * Gets the tiredness level.
	 *
	 * @param x the distance
	 * @param y the time
	 * @param z the cadence
	 * @return the tiredness level
	 */
	public double getTirednessLevel(int x, int y, int z){
		
		/* New regression method implementing Regression */
		rg = new MultipleLinearRegression(dm, min, max);
		int[] parameters = new int[3];
		parameters[0] = x;
		parameters[1] = y;
		parameters[2] = z;
		// rounding the number to have no decimal
		return rg.getTirednessLevel(parameters)*100/100;
		
	}
	
	/**
	 * Adds the sample.
	 *
	 * @param x the distance
	 * @param y the time
	 * @param z the cadence
	 * @param tiredness the tiredness level
	 */
	public void addSample(int x, int y, int z, int tiredness){
		ArrayList<Integer> al = new ArrayList<>();
		al.add(x);
		al.add(y);
		al.add(z);
		SampleTiredness sf = new SampleTiredness(al, tiredness);
		dm.addSample(sf);
	}
}
