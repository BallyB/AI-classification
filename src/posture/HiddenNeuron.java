package posture;

public class HiddenNeuron extends Neuron{

	private final int layer;
	
	public HiddenNeuron(int id, String label, int layer) {
		super(id, label);
		this.layer = layer;
	}

	
	public int getLayer(){
		return this.layer;
	}

}
