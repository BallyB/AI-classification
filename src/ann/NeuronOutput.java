package ann;

public class NeuronOutput extends Neuron{
	
	private double expectedValue;
	
	public NeuronOutput(int id, String label) {
		super(id, label);
		this.expectedValue = 0;
	}

	public NeuronOutput(int id, String label, double synapticValue) {
		super(id, label, synapticValue);
		this.expectedValue = 0;
	}

	public double getExpectedValue() {
		return expectedValue;
	}

	public void setExpectedValue(double expectedValue) {
		this.expectedValue = expectedValue;
	}

	
}
