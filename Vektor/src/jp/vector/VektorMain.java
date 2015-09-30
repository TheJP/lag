package jp.vector;

public class VektorMain {

	public static void a4_9(){
		Vektor3d a = new Vektor3d(3,1,5);
		Vektor3d b = new Vektor3d(1,6,7);
		Vektor3d c = new Vektor3d(-2,4,10);

		Vektor3d ab = b.sub(a);
		Vektor3d ac = c.sub(a);

		double result = Math.acos(
				ab.dot(ac) / (ab.norm()*ac.norm())
        );

		//System.out.println(result/Math.PI*180);
		System.out.println(Math.toDegrees(result));
	}

	public static void a4_8(){
		Vektor3d a = new Vektor3d(3,-1,2);
		Vektor3d b = new Vektor3d(1,2,4);
		//Vektor3d c = new Vektor3d(3, -1);
		double result = Math.acos(
				a.dot(b) / (a.norm()*b.norm())
        );
		System.out.println(Math.toDegrees(result));
	}

	public static void a6(){
		Vektor3d a,b,c,d,l,v,n;
		a = new Vektor3d(3,4,-2);
		b = new Vektor3d(5,6,1);
		c = new Vektor3d(-1,3,3);
		d = new Vektor3d(2,4,6);
		l = new Vektor3d(3,8,12);
		v = a.sub(l);
		n = c.sub(b).cro(d.sub(b));

		PlaneND e = new PlaneND(n, n.dot(b));

		System.out.println(e.pprojection(a, v));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		a6();
	}

}
