package jp.raytrace;

import java.util.ArrayList;

import jp.vector.Vektor3d;

public class Sphere implements ISceneElement {

	private Vektor3d m;
	private double r;
	private double r2;
	private int color;

	/**
	 * 
	 * @param m Middle of the Sphere
	 * @param r Raduis of the Sphere
	 */
	public Sphere(Vektor3d m, double r) {
		this(m, r, 0x0000ff);
	}
	/**
	 * 
	 * @param m Middle of the Sphere
	 * @param r Raduis of the Sphere
	 * @param color Color of the Sphere
	 */
	public Sphere(Vektor3d m, double r, int color) {
		this.m = m;
		this.setR(r);
		this.setColor(color);
	}

	public Double solve(Vektor3d p, Vektor3d v){
		double a = v.dot(v);
		double b = (v.mul(2)).dot(p.sub(m));
		double c = (p.sub(m)).dot(p.sub(m)) - r2;
		double disk = (b*b) - (4*a*c);
		if(disk < 0){
			return null;
		}else if(disk == 0){
			return (-b)/(2*a);
		}else{
			double x1 = (-b + Math.sqrt(disk))/(2*a);
			double x2 = (-b - Math.sqrt(disk))/(2*a);
			return x1 < x2 ? x1 : x2;
		}
	}

	@Override
	public Vektor3d intersection(Vektor3d v, Vektor3d p) {
		/*Vektor3d l = m.sub(p);
		double tca = l.dot(v);
		if(tca < 0){ return null; }
		double d2 = l.dot(l) - tca * tca;
		if(d2 > r2){ return null; }
		double thc = Math.sqrt(r2 - d2);
		double t0 = tca - thc;*/
		//double t1 = tca + thc;
		Double t0 = solve(p, v);
		if(t0==null){ return null; }
		return (v.mul(t0));

	}

	@Override
	public int getColor(Vektor3d pToI, Vektor3d p, ArrayList<ILight> lights) {
		int colorR = (int)((color >> 16) & 0xff);
		int colorG = (int)((color >> 8) & 0xff);
		int colorB = (int)(color & 0xff);

		Vektor3d i = p.add(pToI);
		Vektor3d n = i.sub(m);
//		i = pToI.mul(-1);
//		double colorFactor = (n.dot(i))/(r * i.norm());
		double colorFactor = 0.0;

		for(ILight light : lights){
			Vektor3d v = light.getDirection(i);
			double localFactor = (n.dot(v))/(r); //v.norm() must be 1
			if(localFactor > 0){
				colorFactor += localFactor;
			}
		}

		return (int)(((int)(colorR * colorFactor) << 16) +
			((int)(colorG * colorFactor) << 8) +
			colorB * colorFactor);
//		return (((int)(((color >> 16) & 0xff) * colorFactor)) << 16) +
//				(((int)(((color >> 8) & 0xff) * colorFactor)) << 8) +
//				((int)((color & 0xff) * colorFactor));
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
		this.r2 = r*r;
	}

	public Vektor3d getM() {
		return m;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}
