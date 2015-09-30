package jp.raytrace;

// ---------------  Raytracing Rahmen-Programm   (Bodenflaeche mit Kreisringen)  ------------------

import java.applet.*;
import java.awt.*;

import jp.vector.PlaneND;
import jp.vector.Vektor3d;

public class Raytrace extends Applet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Color backColor = new Color(10,100,160); // Hintergrund
	Vektor3d s = new Vektor3d(8, 0, 2); // Beobachter
	Vektor3d m = new Vektor3d(4, 0, 1); // Stuetzpunkt der Bildebene E
	Vektor3d n; // Normale der Bildebene E
	// Vektor3d e3 = new Vektor3d(0, 0, 1); // up-Vektor
	double b = 8, h; // Breite, Hoehe Bildrechteck
	double r = 1; // Kugelradius
	double zBoden = -r; // z-Koordinate der Bodenflaeche

	// --------------- Methoden -----------------------------------------

	Color bodenFarbe(Vektor3d s, Vektor3d v) // Bodenfarbe in einer
												// Blickrichtung v
	{
		if (v.get(2) >= 0) {
			return backColor; // Strahl trifft nicht auf Boden
		}
		// Vektor3d n = Vektor3d.e3; // Normale der Bodenebene
		PlaneND plane = new PlaneND(Vektor3d.e3/* n */, zBoden);
		Vektor3d x = plane.pprojection(s, v); // Spurpunkt des Strahls auf Boden
		double r = Math.sqrt(x.get(0) * x.get(0) + x.get(1) * x.get(1)); // Abstand
																			// vom
																			// Nullpunkt
		if (r > 20)
			return backColor;
		int m = (int) r;
		if (m % 2 == 0) {
			return new Color(0,100,0); // Punkt in dunklem Ring
		} else {
			return new Color(60,200,60); // Punkt inh hellem Ring
		}
	}

	void zeichnePunkt(Graphics g, int col, int line, Color color) {
		g.setColor(color);
		g.fillRect(col, line, 1, 1);
	}

	public void init() {
		setBackground(Color.black);
		n = s.sub(m); // Normale der Bildebene E
	}

	public void paint(Graphics g) {
		Dimension dim = getSize(); // Window-Abmessungen
		int cols = dim.width;
		int lines = dim.height;
		h = b * lines / cols; // Hoehe Bildrechteck
		Vektor3d u = (Vektor3d.e3).cro(n); // Horizontalrichtung in E
		Vektor3d v = n.cro(u); // Vertikalrichtung in E
		u = u.mul(0.5 * b / u.norm()); // Laengenanpassung auf Bildrechteck
		v = v.mul(0.5 * h / v.norm());
		Vektor3d p = v.add(m.sub(u)); // Referenzpukt in Bildebene
										// (left,top)
		Vektor3d du = u.mul(2.0 / (cols - 1)); // Gitterabstand in Richtung u
		Vektor3d dv = v.mul(2.0 / (lines - 1)); // Gitterabstand in Richtung
												// v
		Vektor3d gitterPkt; // laufender Gitterpunkt
		Vektor3d p1; // Hilfspunkt
		Color color; // Pixelfarbe
		for (int i = 0; i < lines; i++) { // Gitterzeilen durchlaufen
			p1 = p.add(dv.mul(-i)); // Startpunkt Zeile i
			for (int j = 0; j < cols; j++) { // Gitterspalten durchlaufen
				gitterPkt = p1.add(du.mul(j)); // Gitterpunkt p - i*dv + j*du
				v = gitterPkt.sub(s); // Richtung des Strahls
				color = bodenFarbe(s, v); // Bodenfarbe im Auftreffpunkt des
											// Strahls
				zeichnePunkt(g, j, i, color); // Pixel zeichnen
			}
		}
	}

}
