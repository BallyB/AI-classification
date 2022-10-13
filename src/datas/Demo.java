package datas;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import ann.Ann;
import knn.Knn;

// TODO: Auto-generated Javadoc
/**
 * The Class Demo.
 */
public class Demo {

	/** The testsamples. */
	protected double[] testsamples;
	
	
	/**
	 * Instantiates a new demo.
	 */
	public Demo(){
		//Knn algorithm = new Knn(3);
		Checker c = new Checker();
		Ann ann = new Ann(4, 3, c);
		
		
		
		
		this.testsamples = new double[4];
		// LX:172 LY:631 LZ:752 RX:169 RY:704 RZ:676 test
		testsamples[0] = 77;
			testsamples[1] = 38;
		testsamples[2] = 67;
		testsamples[3] = 22;
//		ann.getPostureID(testsamples);
	//	7.7,3.8,6.7,2.2,Iris-virginica 3
		
	/*	PrintStream dummyStream = new PrintStream(new OutputStream(){
		    public void write(int b) {
		        // NO-OP
		    }
		});

		System.setOut(dummyStream);*/
		//Logger.getLogger(getClass().getName()).log(Level.FINE, "...");
		testsamples[0] = 51;
			testsamples[1] = 34;
			testsamples[2] = 15;
			testsamples[3] = 20;
	//	5.1,3.4,1.5,0.2,Iris-setosa 1
	//	testsamples[4] = 854;
	//	testsamples[5] = 491;
//		ann.getPostureID(testsamples); // 1 expected
		testsamples[0] = 66;
		testsamples[1] = 29;
		testsamples[2] = 46;
		testsamples[3] = 13;
	//	6.6,2.9,4.6,1.3,Iris-versicolor
//		ann.getPostureID(testsamples);
		
		testsamples[0] = 64;
		testsamples[1] = 27;
		testsamples[2] = 53;
		testsamples[3] = 19;
//		ann.getPostureID(testsamples);
		
		testsamples[0] = 45;
		testsamples[1] = 23;
		testsamples[2] = 13;
		testsamples[3] = 3;
//		ann.getPostureID(testsamples);
		//4.5,2.3,1.3,0.3,Iris-setosa
		//6.4,2.7,5.3,1.9,Iris-virginica
		//algorithm.getPostureID(testsamples);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello classification!");
		new Demo();
	}

}
