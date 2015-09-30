package jp.vector;

public class PlaneNDUVP extends PlaneBase {

	private Vektor3d n;
	private double d;
	private Vektor3d u, v, p;

//	public PlaneNDUV(Vektor3d n, double d){
//		this.n = n;
//		this.d = d;
//	}

//	public PlaneNDUV(Vektor3d u, Vektor3d v) {
//		this.u = u;
//		this.v = v;
//		this.n = u.cro(v);
//		double x = 1.0, y = 1.0;
//
//	}

	public PlaneNDUVP(Vektor3d p, Vektor3d u, Vektor3d v, Vektor3d n, double d) {
		this.setP(p);
		this.setU(u);
		this.setV(v);
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

	public Vektor3d getU() {
		return u;
	}

	public void setU(Vektor3d u) {
		this.u = u;
	}

	public Vektor3d getV() {
		return v;
	}

	public void setV(Vektor3d v) {
		this.v = v;
	}

	public Vektor3d getP() {
		return p;
	}

	public void setP(Vektor3d p) {
		this.p = p;
	}

}
