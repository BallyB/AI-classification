package ann;

public class Neuron {
	
	private final int id;
	private String label;
	private double synapticValue;
	private double delta;
	
	public Neuron(int id, String label){
		this.id = id;
		this.label = label;
		this.synapticValue = 0;
		this.delta = 0;
	}
	
	public Neuron(int id, String label, double synapticValue){
		this.id = id;
		this.label = label;
		this.synapticValue = synapticValue;
		this.delta = 0;
	}
	
	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	public int getId(){
		return id;
	}
	
	public String getLabel(){
		return label;
	}
	
	public void setLabel(String label){
		this.label = label;
	}
	
	
	public double getSynapticValue() {
		return synapticValue;
	}

	public void setSynapticValue(double synapticValue) {
		this.synapticValue = synapticValue;
	}

	
	@Override
	public int hashCode() {
		final int prime = 7;
		int result = prime * this.id + this.label.length();
		return result;
	}

	@Override
	public String toString() {
		return "Noeud["+this.id+", "+this.label+", "+this.synapticValue+", "+this.delta+"]";
	}
	
}
