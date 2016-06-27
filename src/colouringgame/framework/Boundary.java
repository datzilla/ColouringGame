package colouringgame.framework;
import java.awt.Polygon;

public class Boundary {
	private Polygon polygon;
	private boolean include;

	// create triangle boundary
	public Boundary (int inpointx1, int inpointy1, int inpointx2, int inpointy2, int inpointx3, int inpointy3) {	
		this.polygon = new Polygon ();
		this.include = true;
		
		this.polygon.addPoint(inpointx1, inpointy1);
		this.polygon.addPoint(inpointx2, inpointy2);
		this.polygon.addPoint(inpointx3, inpointy3);
	}
	
	// create circle boundary
	public Boundary (int inpointx1, int inpointy1, int radius) {
		this.polygon = new Polygon ();
		this.include = true;
		
		int centerx = inpointx1 + radius;
		int centery = inpointy1 + radius;
		int steps = 30;
		int x, y;
		
		//in this circle we define the steps but in the future we can expand this so the user can define the steps
		for (int i = 0; i < steps; i++) {
			x = (int) (centerx + radius * Math.cos(2 * Math.PI * i / steps));
			y = (int) (centery + radius * Math.sin(2 * Math.PI * i / steps));
			this.polygon.addPoint (x, y);
		}	
	}
	
	
	//signature for rectangle
	public Boundary (int inpointx1, int inpointy1, int inpointx2, int inpointy2, int inpointx3, int inpointy3, int inpointx4, int inpointy4) {
		this.polygon = new Polygon ();
		this.include = true;
		
		this.polygon.addPoint(inpointx1, inpointy1);
		this.polygon.addPoint(inpointx2, inpointy2);
		this.polygon.addPoint(inpointx3, inpointy3);
		this.polygon.addPoint(inpointx4, inpointy4);
	}
	
	// Check if the coordinate is within the Boundary
	public boolean Contains (int inX, int inY) {
		return this.polygon.contains(inX, inY);
	}
	
	// tells if the boundary include the area or excludes the area,
	// example, if you dont want to include a the area within a boundary
	// then you can define the boundary.
	public boolean include () {
		return this.include;
	}
}
