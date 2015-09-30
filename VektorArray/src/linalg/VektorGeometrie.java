package linalg;
import static linalg.VektorAlgebra.*;

public class VektorGeometrie {

	public static double[] pprojektion(double[] p, double[] v, double[] n,
			double d) {
		double t = (d-vdot(n, p))/vdot(n,v);
		//x = p + tv
		return vadd(p,vmult(t, v));
	}

}
