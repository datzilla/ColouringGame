package colouringgame.framework;

import java.util.ArrayList;

public class Object {
	protected String name;
	protected int ID;
	protected ArrayList <Sprite> allsprites;
	protected ArrayList <Boundary> objectboundary;
	protected Sprite currentsprite;
	protected int startX, startY;
	protected boolean visibility;
	protected int timeout;
	protected boolean animated;
	protected Animation objectanimation;
	protected int lapsedtime;
	protected int frameIndex;
	protected Colour objectcolour;
	protected Sound hoveroff;
	protected Sound hoveron;
	protected Sound mouseon;
	protected boolean ismouseon;
	
	public Object (String inSpritePath, int inStartX, int inStartY) {
		this.currentsprite = new Sprite (inSpritePath, inStartX, inStartY);
		this.visibility = true;
		this.animated = false;
		this.timeout = 0;
		this.lapsedtime = 0;
		this.frameIndex = 0;
	}
			
	// this is created because the object is animated, you can add the sprite later	
	public Object (int inStartX, int inStartY){
		this.startX = inStartX;
		this.startY = inStartY;
		this.visibility = true;
		this.animated = false;
		this.timeout = 0;
		this.lapsedtime = 0;
		this.frameIndex = 0;
	}
	
	public void setName (String inName) {
		this.name = inName;
	}
	
	public String getName () {
		return this.name;
	}
	
	public void setColour (Colour inColour) {
		this.objectcolour = inColour;
	}
	
	public Colour getColour () {
		return this.objectcolour;
	}
	
	public void addHoverFX (Sound inHoverOnFX, Sound inHoverOffFX) {
		this.hoveroff = inHoverOffFX;
		this.hoveron = inHoverOnFX;
	}
	
	public void addMouseOnFX (Sound inMouseOnFX) {
		this.mouseon = inMouseOnFX;
	}
		
	public void setPosition (int inPosX, int inPosY) {
		this.startX = inPosX;
		this.startY = inPosY;
		if (this.currentsprite !=null) {
			this.currentsprite.setStartX(inPosX);
			this.currentsprite.setStartX(inPosY);
		}
	}
	
	public void setTimeout (int inTimeout) {
		this.timeout = inTimeout;
	}
	
	public boolean isAnimated () {
		return this.animated;
	}
	
	public void setAnimated () {
		if (this.allsprites.size() > 1) {
			this.animated = true;
			this.objectanimation = new Animation (this.allsprites);
		}
	}
	
	public void addSprite (String inSpritePath, int inInterval) {
		Sprite TempSprite = new Sprite (inSpritePath, inInterval); 
		TempSprite.setStartX(this.startX);
		TempSprite.setStartY(this.startY);
		
		if (this.currentsprite == null) this.currentsprite = TempSprite; 
		if (this.allsprites == null) this.allsprites = new ArrayList <Sprite> ();
		this.allsprites.add (TempSprite);
	}
	
	public void addSprite (String inSpritePath) {
		Sprite TempSprite = new Sprite (inSpritePath, this.startX, this.startY); 
		if (this.currentsprite == null) this.currentsprite = TempSprite; 
		if (this.allsprites == null) this.allsprites = new ArrayList <Sprite> ();
		this.allsprites.add (TempSprite);
	}
	
	public void addBoundary (Boundary b) {
		if (this.objectboundary == null) this.objectboundary = new ArrayList <Boundary> ();
		this.objectboundary.add(b);
	}
	
	public Sprite getSprite () {
		// if the object is animated, get the current sprite from the animated class
		if (this.allsprites == null) this.allsprites = new ArrayList <Sprite> ();
		if ((this.allsprites.size() > 1) && (this.animated)) {
			return this.objectanimation.getSprite();
		} else {
			return currentsprite;
		}
	}
	
	public void Update () {
		if (this.animated) this.objectanimation.update();
		if (this.timeout > 0) this.TimeoutUpdate ();
	}
		
	public boolean isVisible () {
		return this.visibility;
	}
	
	public void setVisible (boolean inVisibility) {
		this.visibility = inVisibility;
	}
	
	public boolean isMouseOn (int inX, int inY) {
		boolean xmarksthespot = false;
		if (this.objectboundary != null) {
			for (Boundary b : objectboundary) {
				if (b.Contains(inX, inY)){
					if (b.include()) {
						xmarksthespot = true;
					}
				}
			}
		}

		return xmarksthespot;
	}
	
	public void MouseOn () {
			
		if (this.allsprites == null) this.allsprites = new ArrayList <Sprite> ();
		if (this.allsprites.size() > 1) {
			if (this.frameIndex == 0) {
				this.frameIndex++;
			}
			
			this.currentsprite = this.allsprites.get(this.frameIndex);
		}
		
		if (this.mouseon != null) {
			if (this.ismouseon == false) {
				this.mouseon.play();
			}
		}
		
		if (this.hoveron != null) {
			if (this.ismouseon == false) {
				this.hoveron.play();
			}
		}
		
		this.ismouseon = true;
	}
	
	public void MouseOff () {
		if (this.allsprites == null) this.allsprites = new ArrayList <Sprite> ();
		if (this.allsprites.size() > 1) {
			if (this.frameIndex > 0) {
				this.frameIndex--;
			}
			this.currentsprite = this.allsprites.get(this.frameIndex);
		}
		
		if (this.hoveroff != null) {
			if (this.ismouseon == true) {
				this.hoveron.stop();
				this.hoveroff.play();
			}
		}
		
		if (this.mouseon != null) {
			if (this.ismouseon == true) {
				this.mouseon.stop();
			}
		}
		
		this.ismouseon = false;
	}
	
	private void TimeoutUpdate () {
		this.lapsedtime +=1;
		if (this.lapsedtime > this.timeout) {
			this.lapsedtime = 0;
			this.visibility = false;
		}
	}

	public void ClickEffect() {
		// TODO Auto-generated method stub
		
	}
}
