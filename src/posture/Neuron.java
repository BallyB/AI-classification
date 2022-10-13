package posture;

public class Neuron {

	private final int id;
	private String label;
	private double synapticValue;
	private double delta;
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getSynapticValue() {
		return synapticValue;
	}

	public void setSynapticValue(double synapticValue) {
		this.synapticValue = synapticValue;
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	public int getId() {
		return id;
	}

	public Neuron(int id, String label){
		this.id = id;
		this.label = label;
		this.synapticValue = 0;
		this.delta = 0;
	}
	
}
