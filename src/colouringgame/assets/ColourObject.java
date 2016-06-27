package colouringgame.assets;
import java.util.ArrayList;

import colouringgame.framework.Colour;
import colouringgame.framework.Object;
import colouringgame.framework.Pair;
import colouringgame.framework.Sprite;

public class ColourObject extends Object {
	
	private ArrayList <Pair <Sprite, Colour>> allspritencolours;
	private Sprite defaultsprite;
	
	public ColourObject (int inStartX, int inStartY) {
		super (inStartX, inStartY);
	}
	
	public ColourObject (String inImagePath, int inStartX, int inStartY) {
		super (inStartX, inStartY);
		this.defaultsprite = new Sprite (inImagePath, inStartX, inStartY);
		this.currentsprite = this.defaultsprite;
	}
	
	
	//@override
	public void addSprite (String inImagePath, Colour inColour) {
		if (this.allspritencolours== null ) {
			this.allspritencolours = new ArrayList <Pair <Sprite, Colour>> ();
		}
				
		//this.allsprites.add(new Sprite (inImagePath, this.startX, this.startY));
		this.allspritencolours.add (new Pair <Sprite, Colour> (new Sprite (inImagePath, this.startX, this.startY), inColour));
	}
	
	//@override	
	public Sprite getSprite () {
		
		if ((this.allspritencolours.size() > 1)&&(this.objectcolour != null)) {
			this.currentsprite = this.getSpriteFromColour (this.objectcolour);
		}
		
		return currentsprite;
	}
	
	private Sprite getSpriteFromColour (Colour inColour) {
		for (Pair <Sprite, Colour> p : this.allspritencolours) {
			if (p.getSecond().equals(inColour)) {
				return p.getFirst();
			}
		} 
		
		return this.currentsprite;	
	}
	
	public void reset () {
		this.currentsprite = defaultsprite;
		this.objectcolour = null;
	}
}
