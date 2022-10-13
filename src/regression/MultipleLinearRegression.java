package regression;


import java.util.List;

/**
 * The Class MultipleLinearRegression.
 */
public class MultipleLinearRegression implements Regression {

	/** The dm. */
	private DataManager dm;
	
	/** boundaries of tiredness level **/
	private int min,max;
	
	/**
	 * Instantiates a new multiple linear regression.
	 *
	 * @param dm the dm
	 * @param max level of tiredness
	 * @param min level of tiredness
	 */
	public MultipleLinearRegression(DataManager dm, int min, int max) {
		this.dm = dm;
		this.max = max;
		this.min = min;
	}
	
	
	/**
	 * Returning the tiredness level of the data in the table parameters
	 */
	@Override
	public double getTirednessLevel(int[] parameters) {
		/* Creating matrix X and Y with the data samples */
		List<SampleTiredness> examples = dm.getAllTrainingSample();
		Matrix x = new Matrix(examples.size(),parameters.length+1);
		Matrix y = new Matrix(examples.size(),1);
		
		/* full these matrix */
		int n = 0;
		for(SampleTiredness sf : examples){
			x.setValueAt(n, 0, 1);
			x.setValueAt(n, 1, sf.getParameters().get(0));
			x.setValueAt(n, 2, sf.getParameters().get(1));
			x.setValueAt(n, 3, sf.getParameters().get(2));
			y.setValueAt(n, 0, sf.getTiredness());
			n++;
		}
		
		/* Ordinary least square regression method */
		Matrix xtransposed = transpose(x);
		Matrix xxtr = multiply(xtransposed,x);
		Matrix xtxinv = inverse(xxtr);
		Matrix xty = multiply(xtransposed, y);
		Matrix betas = multiply(xtxinv, xty);
		
		/* Predicting the new tiredness value from the sample in parameters using the Betas values calculated */
		double tiredness = 0;
		for (int i = 1; i < betas.getNrows(); i++) {
			tiredness += betas.getValueAt(i, 0)*parameters[i-1];
		}
		tiredness += betas.getValueAt(0, 0);
		
		/* Returning the predicted tireness level */
		if(tiredness<min)tiredness = min;
		if(tiredness>max)tiredness = max;
		return tiredness;
	}
	
	/**
	 * Odd or even number test
	 *
	 * @param i the i
	 * @return the int
	 */
	private int changeSign(int i) {
		if (i%2==0)
			return 1;
		return -1;
	}
	
	/**
	 * Inverse matrix.
	 *
	 * @param matrix the matrix
	 * @return the inverted matrix
	 */
	public Matrix inverse(Matrix matrix) {
		return (transpose(cofactor(matrix)).multiplyByConstant(1.0/determinant(matrix)));
	}
	
	/**
	 * Cofactor matrix.
	 *
	 * @param matrix the matrix
	 * @return the cofactor matrix
	 */
	public Matrix cofactor(Matrix matrix){
		Matrix mat = new Matrix(matrix.getNrows(), matrix.getNcols());
		for (int i=0;i<matrix.getNrows();i++) {
			for (int j=0; j<matrix.getNcols();j++) {
				mat.setValueAt(i, j, changeSign(i) * changeSign(j) * determinant(createSubMatrix(matrix, i, j)));
			}
		}
		
		return mat;
	}
	
	/**
	 * Determinant of the matrix.
	 *
	 * @param matrix the matrix
	 * @return the determinant double
	 */
	public double determinant(Matrix matrix){
		if (matrix.size() == 1){
			return matrix.getValueAt(0, 0);
		}
			
		if (matrix.size()==2) {
			return (matrix.getValueAt(0, 0) * matrix.getValueAt(1, 1)) - ( matrix.getValueAt(0, 1) * matrix.getValueAt(1, 0));
		}
		double sum = 0.0;
		for (int i=0; i<matrix.getNcols(); i++) {
			sum += changeSign(i) * matrix.getValueAt(0, i) * determinant(createSubMatrix(matrix, 0, i));
		}
		return sum;
	}
	
	/**
	 * Multiply two matrix together (1*2 because it is not reversible).
	 *
	 * @param matrix1 the matrix 1
	 * @param matrix2 the matrix 2
	 * @return the matrix
	 */
	public Matrix multiply(Matrix matrix1, Matrix matrix2)  {
		Matrix multipliedMatrix = new Matrix(matrix1.getNrows(), matrix2.getNcols());
		
		for (int i=0;i<multipliedMatrix.getNrows();i++) {
			for (int j=0;j<multipliedMatrix.getNcols();j++) {
				double sum = 0.0;
				for (int k=0;k<matrix1.getNcols();k++) {
					sum += matrix1.getValueAt(i, k) * matrix2.getValueAt(k, j);
				}
				multipliedMatrix.setValueAt(i, j, sum);
			}
		}
		return multipliedMatrix;
	}
	
	/**
	 * Creates the sub matrix.
	 *
	 * @param matrix the matrix
	 * @param excluding_row the excluding row
	 * @param excluding_col the excluding col
	 * @return the matrix
	 */
	public Matrix createSubMatrix(Matrix matrix, int excluding_row, int excluding_col) {
		Matrix mat = new Matrix(matrix.getNrows()-1, matrix.getNcols()-1);
		int r = -1;
		for (int i=0;i<matrix.getNrows();i++) {
			if (i==excluding_row)
				continue;
			r++;
			int c = -1;
			for (int j=0;j<matrix.getNcols();j++) {
				if (j==excluding_col)
					continue;
				mat.setValueAt(r, c+1, matrix.getValueAt(i, j));
				c++;
			}
		}
		return mat;
	}
	
	/**
	 * Transpose a matrix.
	 *
	 * @param matrix the matrix
	 * @return the transposed matrix
	 */
	public Matrix transpose(Matrix matrix) {
	    Matrix transposedMatrix = new Matrix(matrix.getNcols(), matrix.getNrows());
	    for (int i=0;i<matrix.getNrows();i++) {
	        for (int j=0;j<matrix.getNcols();j++) {
	            transposedMatrix.setValueAt(j, i, matrix.getValueAt(i, j));
	        }
	    }
	    return transposedMatrix;
	} 
		

}
