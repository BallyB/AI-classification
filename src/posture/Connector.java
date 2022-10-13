package posture;

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
	
	public void setSynapticWeight(double weight){
		this.synapticWeight = weight;
	}



	
	
}
