package posture;

public class OutputNeuron extends Neuron{
	
	private double expectedValue;
	
	public OutputNeuron(int id, String label) {
		super(id, label);
		this.expectedValue = 0;
	}

	public double getExpectedValue() {
		return expectedValue;
	}

	public void setExpectedValue(double expectedValue) {
		this.expectedValue = expectedValue;
	}

	
}
