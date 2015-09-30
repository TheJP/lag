package jp.raytrace;

import jp.vector.PlaneNDUVP;
import jp.vector.Vektor3d;

/**
 * Combination of Camera and Display (Size of Location of each)
 * @author Janis
 *
 */
public class Viewport {

	private PlaneNDUVP plane;
	private Vektor3d p; //Referenzpunkt (Ort des Betrachter; Augpunkt)

	/**
	 * 
	 * @param plane Rectangle of Viewport (Rectangle of the display)
	 * @param p Eyepoint
	 */
	public Viewport(PlaneNDUVP plane, Vektor3d p) {
		this.plane = plane;
		this.p = p;
	}

	public PlaneNDUVP getPlane() {
		return plane;
	}

	public void setPlane(PlaneNDUVP plane) {
		this.plane = plane;
	}

	public Vektor3d getP() {
		return p;
	}

	public void setP(Vektor3d p) {
		this.p = p;
	}

}
