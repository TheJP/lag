package jp.raytrace;

import java.util.ArrayList;

import jp.vector.Vektor3d;

public interface ISceneElement {
	/**
	 * Calculates the intersection between a given line a the SceneElement
	 * @param v Direction of the line
	 * @param p Reference Point on the line
	 * @return Vektor from p to the calculated intersection
	 */
	Vektor3d intersection(Vektor3d v, Vektor3d p);
	/**
	 * Claculate Color at intersection point
	 * TODO: Pass lightsources
	 * @param pToI Vektor from p to intersection (Has direction v)
	 * @param p Point on the Viewport
	 * @param lights Lightsources
	 * @return
	 */
	int getColor(Vektor3d pToI, Vektor3d p, ArrayList<ILight> lights);
}
