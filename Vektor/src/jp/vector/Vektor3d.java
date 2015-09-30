package jp.vector;

public class Vektor3d extends Vektor {

	public static final Vektor3d e1 = new Vektor3d(1,0,0);
	public static final Vektor3d e2 = new Vektor3d(0,1,0);
	public static final Vektor3d e3 = new Vektor3d(0,0,1);

	public Vektor3d(double... x) {
		this.x = x;
		if(x.length != 3){
			this.x = new double[]{x[0], x[1], x[2]};
		}
	}

	@Override
	public int getDimensions() {
		return 3;
	}

	public Vektor3d add(Vektor3d v) {
		return new Vektor3d(new double[] { get(0) + v.get(0), get(1) + v.get(1), get(2) + v.get(2) });
	}

	public Vektor3d sub(Vektor3d v) {
		return new Vektor3d(new double[] { get(0) - v.get(0), get(1) - v.get(1), get(2) - v.get(2) });
	}

	@Override
	public Vektor3d mul(double a) {
		return new Vektor3d(new double[] { a * get(0), a * get(1), a * get(2) });
	}

	@Override
	public double norm() {
		return Math.sqrt(get(0) * get(0) + get(1) * get(1) + get(2) * get(2));
	}

	public double dot(Vektor3d v) {
		return (get(0) * v.get(0) + get(1) * v.get(1) + get(2) * v.get(2));
	}

	@Override
	public Vektor normalize() {
		double n = norm();
		return new Vektor3d(new double[]{ get(0) / n, get(1) / n, get(2) / n });
	}

	/**
	 * Crossproduct = Vektorprodukt
	 * @param v
	 * @return
	 */
	public Vektor3d cro(Vektor3d v){
		double c3 = get(0)*v.get(1) - get(1)*v.get(0);
		//if(c3 == 0){ c3 = 1/0; }
		return new Vektor3d(
		    get(1)*v.get(2) - get(2)*v.get(1),
		    get(2)*v.get(0) - get(0)*v.get(2),
		    c3
        );
	}

}
