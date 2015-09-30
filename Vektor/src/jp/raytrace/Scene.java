package jp.raytrace;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import jp.vector.Vektor3d;


public class Scene {

	private int backColor = 0x000000;
	private ArrayList<ISceneElement> elements = new ArrayList<>();
	private ArrayList<ILight> lights = new ArrayList<ILight>();

	public void render(Viewport viewport, BufferedImage image) {
		Vektor3d p1 = viewport.getPlane().getP(), p2, v, minI, tmpMinI;
		ISceneElement minSceneElement;
		double minimum, tmpMinimum;
		for(int y = 0; y < image.getHeight(); y++){
			p2 = p1.mul(1);
			for(int x = 0; x < image.getWidth(); x++){
				v = p2.sub(viewport.getP());

				//** Find nearest Element **//
				minimum = Double.MAX_VALUE;minI = null;minSceneElement = null;
				for(ISceneElement element : getElements()){
					tmpMinI = element.intersection(v, viewport.getP());
					if(tmpMinI != null){
					tmpMinimum = tmpMinI.norm();
						if(tmpMinimum < minimum){
							minimum = tmpMinimum;
							minI = tmpMinI;
							minSceneElement = element;
						}
					}
				}

				//** Paint Element **//
				if(minSceneElement != null){
					image.setRGB(x, image.getHeight()-y-1, minSceneElement.getColor(minI, viewport.getP(), getLights()));
				}else{
					image.setRGB(x, image.getHeight()-y-1, backColor);
				}

				p2 = p2.add(viewport.getPlane().getU());
//				/*cool*/ image.setRGB(x, y, x*y);
			}
			p1 = p1.add(viewport.getPlane().getV());
		}
	}

	public ArrayList<ISceneElement> getElements() {
		return elements;
	}

	public ArrayList<ILight> getLights() {
		return lights;
	}

}
