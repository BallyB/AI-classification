package regression;


/**
 * The Class ApplicationDemo.
 */
public class ApplicationDemo {

	
	
	
	public ApplicationDemo(){
		/* Defining a new Tiredness processor with scale of tirednesslevel and adding calibrating sample ('learning' database)*/
		TirednessProcessor tp = new TirednessProcessor(1, 10);
		tp.addSample(4, 10, 16, 2);
		tp.addSample(26, 25, 17, 4);
		tp.addSample(72, 60, 16, 7);
		tp.addSample(160, 120, 20, 9);
		tp.addSample(15, 30, 30, 5);
		tp.addSample(30, 50, 20, 4);
		tp.addSample(20, 47, 20, 6);
		tp.addSample(7, 15, 22, 3);
		tp.addSample(110, 90, 18, 7);
	
		/* prediction of tiredness of a new sample of data */
		System.out.println(tp.getTirednessLevel(10,60,50));

	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new ApplicationDemo();
	}

}

