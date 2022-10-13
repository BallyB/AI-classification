package regression;

/**
 * The Class Matrix.
 */
public class Matrix {

    /** The number of rows in the matrix. */
    private int nrows;
    
    /** The number of columns in the matrix. */
    private int ncols;
    
    /** The matrix. */
    private double[][] data;

    /**
     * Instantiates a new matrix.
     *
     * @param data the table which is the futur matrix
     */
    public Matrix(double[][] data) {
        this.data = data;
        this.nrows = data.length;
        this.ncols = data[0].length;
    }

    /**
     * Instantiates a new matrix.
     *
     * @param nrow the number of row
     * @param ncol the number of column
     */
    public Matrix(int nrow, int ncol) {
        this.nrows = nrow;
        this.ncols = ncol;
        data = new double[nrow][ncol];
    }

	/**
	 * Gets the nrows.
	 *
	 * @return the nrows
	 */
	public int getNrows() {
		return nrows;
	}

	/**
	 * Sets the nrows.
	 *
	 * @param nrows the new number of rows
	 */
	public void setNrows(int nrows) {
		this.nrows = nrows;
	}

	/**
	 * Gets the ncols.
	 *
	 * @return the number of cols
	 */
	public int getNcols() {
		return ncols;
	}

	/**
	 * Sets the ncols.
	 *
	 * @param ncols the new number of cols
	 */
	public void setNcols(int ncols) {
		this.ncols = ncols;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data matrix
	 */
	public double[][] getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data matrix
	 */
	public void setData(double[][] data) {
		this.data = data;
	}
    
	/**
	 * Gets the value at.
	 *
	 * @param i the i
	 * @param j the j
	 * @return the value at index i,j
	 */
	public double getValueAt(int i, int j){
		return data[i][j];
	}
	
	/**
	 * Sets the value at.
	 *
	 * @param i the i
	 * @param j the j
	 * @param val the value tu put at index i,j
	 */
	public void setValueAt(int i, int j, double val){
		data[i][j] = val;
	}
	
	/**
	 * Checks if is square.
	 *
	 * @return true, if the matrix is square
	 */
	public boolean isSquare() {
		return nrows == ncols;
	}

	/**
	 * Size.
	 *
	 * @return the size of the matrix
	 */
	public int size() {
		if (isSquare())
			return nrows;
		return -1;
	}
	
	/**
	 * Display the matrix
	 */
	public void display(){
		System.out.println("Display : ");
		for (int j = 0; j < this.nrows; j++) {
			System.out.println("");
    		for (int i = 0; i < this.ncols; i++) {
    			System.out.print(""+this.getValueAt(j, i)+" ");
			}
		}
		System.out.println(" ");
	}
	
	/**
	 * Multiply the matrix by constant.
	 *
	 * @param constant the constant
	 * @return the matrix
	 */
	public Matrix multiplyByConstant(double constant) {
		Matrix mat = new Matrix(nrows, ncols);
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				mat.setValueAt(i, j, data[i][j] * constant);
			}
		}
		return mat;
	}
}