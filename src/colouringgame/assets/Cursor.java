package colouringgame.assets;

import java.util.ArrayList;

import colouringgame.framework.Colour;
import colouringgame.framework.Object;
import colouringgame.framework.Pair;
import colouringgame.framework.Sprite;

public class Cursor extends Object {
	
	private int width, height;
	private Sprite defaultsprite = new Sprite ("data/emptypaintbrush.png", this.width, this.height);
	private ArrayList <Pair <String, Colour>> allspritencolours;
	
	public Cursor (int inStartX, int inStartY, int inWidth, int inHeight) {
		super (inStartX - inWidth, inStartY - inHeight);
		this.height = inHeight;
		this.width = inWidth;
		this.currentsprite = defaultsprite;
	}
	
	//@Override
	public void reset () {
		this.currentsprite = defaultsprite;
		this.currentsprite.setStartX(this.startX);
		this.currentsprite.setStartY(this.startY);
		this.objectcolour = null;
	}
	
	//@override
	public void setPosition (int inStartX, int inStartY) {
		this.startX = inStartX;
		this.startY = inStartY - this.height;
		if (this.currentsprite !=null) {
			this.currentsprite.setStartX(this.startX);
			this.currentsprite.setStartY(this.startY);
		}
	}
	
	//@override
	public void addSprite (String inImagePath, Colour inColour) {
		if (this.allspritencolours== null ) {
			this.allspritencolours = new ArrayList <Pair <String, Colour>> ();
		}
				
		//this.allsprites.add(new Sprite (inImagePath, this.startX, this.startY));
		this.allspritencolours.add (new Pair <String, Colour> (inImagePath, inColour));
	}
	
	//@override	
	public Sprite getSprite () {
		
		if ((this.allspritencolours.size() > 1)&&(this.objectcolour != null)) {
			this.currentsprite = this.getSpriteFromColour (this.objectcolour);
		}
	
		return currentsprite;
	}
	
	private Sprite getSpriteFromColour (Colour inColour) {
		String temppath = "";
		for (Pair <String, Colour> p : this.allspritencolours) {
			if (p.getSecond().equals(inColour)) {
				temppath = p.getFirst();
			}
		} 
		
		return new Sprite (temppath, this.startX, this.startY);
	}
}
