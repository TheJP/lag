package jp.raytrace;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;
import javax.swing.JFrame;

import jp.vector.PlaneNDUVP;
import jp.vector.Vektor3d;

public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2844504444759950508L;

	private BufferedImage image0;
	private BufferedImage image1;
	private BufferedImage show;

	private boolean resizedFlag = false;

	private Scene scene = new Scene();
	private Viewport viewport;

	private double width = 10;
	private double height;

	//** Animation Objects **//
	private Sphere sphere;
	private DirectionalLight sun;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		super("Raytrace");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		setContentPane(new JComponent(){

			/**
			 * 
			 */
			private static final long serialVersionUID = -116175782183212715L;

			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(show, 0, 0, Color.black, new ImageObserver() {

					@Override
					public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
							int arg4, int arg5) {
						return false;
					}
				});
				g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
				g.setColor(Color.RED);
				g.drawString(Integer.toHexString(sphere.getColor()), 10, 20);
			}
		});

		//** Create Viewport **//
		{
			Vektor3d n = new Vektor3d(4,0,1);
			Vektor3d u = (Vektor3d.e3).cro(n);
			Vektor3d v = n.cro(u);
			double d = 0;
			Vektor3d p = new Vektor3d(-26,6,5);
			this.viewport = new Viewport(new PlaneNDUVP(new Vektor3d(-20,0,0), u, v, n, d), p);
		}

		//** Setup Scene **//
		{
			sun = new DirectionalLight(sunV);
			scene.getLights().add(sun);
			scene.getElements().add(new EndlessZPlane(-5));
//			scene.getElements().add(new Sphere(new Vektor3d(5,30,15), 1));
			sphere = new Sphere(new Vektor3d(10,5,0), 5, 0x00ffff);
			scene.getElements().add(sphere);
		}

		final View frame = this;
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask(){

			@Override
			public void run() {
				frame.repaint();
				frame.render();
			}

		}, 100, 20);
		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				frame.update();
			}
		}, 110, 20);

		resize();

		show = image0;

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				frame.resized(e);
			}
		});
	}

	/**
	 * Resizes the viewport and the images
	 */
	private void resize(){
		image0 = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		image1 = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		height = width * getHeight()/getWidth();
		{
			Vektor3d u = viewport.getPlane().getU();
			Vektor3d v = viewport.getPlane().getV();
			u = u.mul(width / (u.norm() * getWidth()));
			v = v.mul(height / (v.norm() * getHeight()));
			viewport.getPlane().setU(u);
			viewport.getPlane().setV(v);
		}
	}

	/**
	 * Renders the Scene and rotates the images
	 */
	public void render(){
		if(resizedFlag){
			resize();
		}
		scene.render(viewport, image1);
		show = image1;
		image1 = image0;
		image0 = show;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void resized(ComponentEvent e){
		resizedFlag = true;
	}

	//private Random r = new Random();
	private double sphereRadiusFactor = 0.01;
	private int sphereColorFactor = 1;
	private Vektor3d sunV = new Vektor3d(1,1,-1);
	private double factorSun = -0.05;
	/**
	 * Updates the scene
	 */
	public void update(){
		//** Sun Moving **//
		sunV = sunV.add(new Vektor3d(factorSun, 0.0, 0.0));
		sun.setV(sunV);
		if(sunV.get(0) < -2){
			factorSun = Math.abs(factorSun);
		}else if(sunV.get(0) > 2){
			factorSun = -Math.abs(factorSun);
		}
		//** Sphere Radius **//
		sphere.setR(sphere.getR() + sphereRadiusFactor);
		if(sphere.getR() < 3){
			sphereRadiusFactor = Math.abs(sphereRadiusFactor);
		}else if(sphere.getR() > 8){
			sphereRadiusFactor = -Math.abs(sphereRadiusFactor);
		}
		//** Sphere Color **//
		if(sphere.getColor() > 0x00fffe){
			sphereColorFactor = -Math.abs(sphereColorFactor);
		}else if(sphere.getColor() < 0x00ff00){
			sphereColorFactor = Math.abs(sphereColorFactor);
		}
		sphere.setColor(sphere.getColor() + sphereColorFactor);
	}

	public BufferedImage getShow(){
		return show;
	}

}
