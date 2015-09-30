package jp.vector;

public class PlaneND extends PlaneBase {

	private Vektor3d n;
	private double d;

	public PlaneND(Vektor3d n, double d){
		this.n = n;
		this.d = d;
	}

	/**
	 * Calculates the projection on the plane of the point p.
	 */
	@Override
	public Vektor3d pprojection(Vektor3d p, Vektor3d v) {
		double t = (d - n.dot(p)) / n.dot(v);
		return p.add(v.mul(t));
	}

	public Vektor3d getN() {
		return n;
	}

	public void setN(Vektor3d n) {
		this.n = n;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

}
