// ---------------  Raytracing Rahmen-Programm   (Bodenflaeche mit Kreisringen)  ------------------

import java.applet.*;
import java.awt.*;
import static linalg.VektorAlgebra.*;
import static linalg.VektorGeometrie.*;

public class Raytrace extends Applet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Color backColor = new Color(10, 100, 0); // Hintergrund
	double[] s = { 8, 0, 2 }; // Beobachter
	double[] m = { 4, 0, 1 }; // Stuetzpunkt der Bildebene E
	double[] e3 = { 0, 0, 1 }; // up-Vektor
	double b = 8, h; // Breite, Hoehe Bildrechteck
	double r = 1; // Kugelradius
	double zBoden = -r; // z-Koordinate der Bodenflaeche

	// --------------- Methoden -----------------------------------------

	Color bodenFarbe(double[] s, double[] v) // Bodenfarbe in einer
												// Blickrichtung v
	{
		if (v[2] >= 0)
			return backColor; // Strahl trifft nicht auf Boden
		double[] n = { 0, 0, 1 }; // Normale der Bodenebene
		double[] x = pprojektion(s, v, n, zBoden); // Spurpunkt des Strahls auf
													// Boden
		double r = Math.sqrt(x[0] * x[0] + x[1] * x[1]); // Abstand vom
															// Nullpunkt
		if (r > 20) {
			return backColor;
		}
		int m = (int) r;
		if (m % 2 == 0) {
			return Color.gray; // Punkt in dunklem Ring
		} else {
			return Color.lightGray; // Punkt inh hellem Ring
		}
	}

	void zeichnePunkt(Graphics g, int col, int line, Color color) {
		g.setColor(color);
		g.fillRect(col, line, 1, 1);
	}

	public void init() {
		setBackground(Color.black);
	}

	public void paint(Graphics g) {
		Dimension dim = getSize(); // Window-Abmessungen
		int cols = dim.width;
		int lines = dim.height;
		h = b * lines / cols; // Hoehe Bildrechteck
		double[] n = vsub(s, m); // Normale der Bildebene E
		double[] u = vcross(e3, n); // Horizontalrichtung in E
		double[] v = vcross(n, u); // Vertikalrichtung in E
		u = vmult(0.5 * b / vnorm(u), u); // Laengenanpassung auf Bildrechteck
		v = vmult(0.5 * h / vnorm(v), v);
		double[] p = vadd(vsub(m, u), v); // Referenzpukt in Bildebene
											// (left,top)
		double[] du = vmult(2.0 / (cols - 1), u); // Gitterabstand in Richtung u
		double[] dv = vmult(2.0 / (lines - 1), v); // Gitterabstand in Richtung
													// v
		double[] gitterPkt; // laufender Gitterpunkt
		double[] p1; // Hilfspunkt
		Color color; // Pixelfarbe
		for (int i = 0; i < lines; i++) // Gitterzeilen durchlaufen
		{
			p1 = vadd(p, vmult(-i, dv)); // Startpunkt Zeile i
			for (int j = 0; j < cols; j++) // Gitterspalten durchlaufen
			{
				gitterPkt = vadd(p1, vmult(j, du)); // Gitterpunkt p - i*dv +
													// j*du
				v = vsub(gitterPkt, s); // Richtung des Strahls
				color = bodenFarbe(s, v); // Bodenfarbe im Auftreffpunkt des
											// Strahls
				zeichnePunkt(g, j, i, color); // Pixel zeichnen
			}
		}
	}

}
