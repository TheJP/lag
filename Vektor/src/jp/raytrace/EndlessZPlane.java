package jp.raytrace;

import java.util.ArrayList;

import jp.vector.PlaneND;
import jp.vector.Vektor3d;

public class EndlessZPlane implements ISceneElement {

	private double z;
	private PlaneND plane;
	public static final int COLOR_DARK = 0x006400; // 0/100/0
	public static final int COLOR_BRIGHT = 0x3cc83c; // 60/200/60

	/**
	 * 
	 * @param z Position of the Plane
	 */
	public EndlessZPlane(double z) {
		this.setZ(z);
	}

	@Override
	public Vektor3d intersection(Vektor3d v, Vektor3d p) {
		if ((p.get(2) >= z && v.get(2) >= 0)
				|| (p.get(2) <= z && v.get(2) <= 0)) {
			return null;
		}
		return p.sub(plane.pprojection(p, v));
	}

	@Override
	public int getColor(Vektor3d pToI, Vektor3d p, ArrayList<ILight> lights) {
		Vektor3d x = p.add(pToI);
		int r = (int)Math.sqrt(x.get(0) * x.get(0) + x.get(1) * x.get(1)); // Abstand vom Nullpunkt
		if((int)x.get(0) == 0 || (int)x.get(1) == 0){
			return COLOR_DARK - 0x003200;
		}else if(r % 2 == 0){
			return COLOR_DARK;
		}else{
			return COLOR_BRIGHT;
		}
	}

	public double getZ() {
		return -z;
	}

	public void setZ(double z) {
		this.z = z;
		this.plane = new PlaneND(Vektor3d.e3, z);
	}

}
