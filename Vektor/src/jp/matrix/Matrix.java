package jp.matrix;

public class Matrix {
	private double[][] m;

	public Matrix(double[]... m){
		this.m = m;
	}

	public double get(int y, int x){
		return m[y][x];
	}

	public void set(int y, int x, double value){
		m[y][x] = value;
	}

	public int getRows(){
		return m.length;
	}

	public int getColumns(){
		return m[0].length;
	}

	public Matrix mul(Matrix a){
		Matrix newMatrix = new Matrix(new double[getRows()][a.getColumns()]);
		for(int row = 0; row < getRows(); row++){
			for(int col = 0; col < a.getColumns(); col++){
				double sum = 0.0;
				for(int dot = 0; dot < getColumns(); dot++){
					sum += get(row, dot) * a.get(dot, col);
				}
				newMatrix.set(row, col, sum);
			}
		}
		return null;
	}
}
