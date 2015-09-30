package jp.raytrace;

import jp.vector.Vektor3d;

public class DirectionalLight implements ILight {

	private Vektor3d v;

	public DirectionalLight(Vektor3d v) {
		setV(v);
	}

	@Override
	public Vektor3d getDirection(Vektor3d p) {
		return getV();
	}

	public Vektor3d getV() {
		return v;
	}

	public void setV(Vektor3d v){
		this.v = v.mul(-1/v.norm());
	}
}
