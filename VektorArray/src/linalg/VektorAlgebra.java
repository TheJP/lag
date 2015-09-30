package linalg;

public class VektorAlgebra {

public static double[] vsub(double[] a, double[] b) {
	return new double[]{a[0]-b[0], a[1]-b[1],  a[2]-b[2]};
}

public static double[] vcross(double[] a, double[] b) {
	return new double[]{
		a[1]*b[2] - a[2]*b[1],
		a[2]*b[0] - a[0]*b[2],
		a[0]*b[1] - a[1]*b[0]
	};
}



public static double vnorm(double[] v) {
	return Math.sqrt(v[0]*v[0] + v[1]*v[1] + v[2]*v[2]);
}

public static double[] vmult(double f, double[] v) {
	return new double[]{f*v[0], f*v[1],  f*v[2]};
}


public static double[] vadd(double[] a, double[] b) {
	return new double[]{a[0]+b[0], a[1]+b[1],  a[2]+b[2]};
}
public static double vdot(double[] n, double[] p) {
	return n[0]*p[0]+n[1]*p[1]+n[2]*p[2];
}
}
