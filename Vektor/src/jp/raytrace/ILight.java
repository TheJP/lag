package jp.raytrace;

import jp.vector.Vektor3d;

public interface ILight {
	/**
	 * Get Inversed direction of the light with a given intersection point
	 * (Intersection of Light and Object)
	 * @param p Position of intersection (Ortsvektor)
	 * @return Inversed Direction
	 */
	Vektor3d getDirection(Vektor3d p);
}
