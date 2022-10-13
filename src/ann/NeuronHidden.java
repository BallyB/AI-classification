package ann;

public class NeuronHidden extends Neuron{

	private final int layer;
	
	public NeuronHidden(int id, String label, int layer) {
		super(id, label);
		this.layer = layer;
	}

	
	public int getLayer(){
		return this.layer;
	}

}
