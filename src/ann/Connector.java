package ann;

public class Connector {
	
	private Neuron source;
	private Neuron destination;
	private double synapticWeight;
	
	
	public Connector(Neuron source, Neuron destination){
		this.source = source;
		this.destination = destination;
		this.synapticWeight = 1;
	}
	
	public Neuron getSource(){
		return this.source;
	}
	
	public Neuron getDestination(){
		return this.destination;
	}
	
	public double getSynapticWeight(){
		return this.synapticWeight;
	}
	
	public void setPoidsSynaptique(double poids){
		this.synapticWeight = poids;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Connector other = (Connector) obj;
		if (this.synapticWeight != other.synapticWeight || this.destination != other.destination || this.source != other.source)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 7;
		int result = (int) (prime * this.synapticWeight + this.source.getLabel().length() + this.destination.getLabel().length());
		return result;
	}

	@Override
	public String toString() {
		return "Arete["+this.source.getLabel()+", "+this.destination.getLabel()+", "+this.synapticWeight+"]";
	}

	
	
}
