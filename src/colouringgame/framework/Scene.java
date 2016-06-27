package colouringgame.framework;

import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import colouringgame.assets.Speech;

public abstract class Scene {
	protected static int level=0;
	protected static int numlevel;
	protected static Colour currentcolour;
	protected static int ScreenWidth, ScreenHeight;
	protected int numberofstars=0;
	
	protected ArrayList <Object> objects;
	protected ArrayList <Object> stars;
	protected ArrayList <Sprite> painting;
	protected ArrayList <Speech> speech;
	protected ArrayList <Sound> sounds;
	
	protected boolean congrats = false;
	protected Objective levelobjective;
	protected String currentquestion;
	protected int currentpoint;
	protected static URI base;
	protected boolean isStarted;
		
	// Default colours
	protected Colour white = new Colour ("white");
	protected Colour blue = new Colour ("blue");
	protected Colour red = new Colour ("red");
	protected Colour yellow = new Colour ("yellow");
	protected Colour green = new Colour ("green");
	protected Colour purple = new Colour ("purple");
	protected Colour orange = new Colour ("orange");
	
	public static void setScreenSize (int inWidth, int inHeight) {
		ScreenWidth = inWidth;
		ScreenHeight = inHeight;
	}
	
	public static void setNumberOfLevels (int inNumberOfLevels) {
		numlevel = inNumberOfLevels;
	}
	
	protected static void setCurrentColour (Colour inCurrentColour) {
		currentcolour = inCurrentColour;
	}
	
	protected static Colour getCurrentColour () {
		return currentcolour;
	}
	
	protected void init () {
		objects = new ArrayList <Object> ();
		painting = new ArrayList <Sprite> ();
		speech = new ArrayList <Speech> ();
		sounds = new ArrayList <Sound> ();
		stars = new ArrayList <Object> ();
	}
	
	public void Start () {
		if (isStarted != true) {
			//this.autoStartSounds();
		}
		
		isStarted = true;
	}
	
	public void Stop () {
		if (isStarted != false) {
			this.stopAllSounds();
		}
		
		isStarted = false;
	}
		
	public void Update () {
				
		for (Object o : objects) {
			o.Update();
		}
		for (Speech sp: speech) {
			sp.Update();
		}
		for (Sound s: sounds) {
			s.Update();
		}
		for (Object st: stars) {
			st.Update ();
		}
		
		SceneUpdate ();
	}
	
	public void SceneUpdate (){
		// need this for the objective
	}
	
//	public void autoStartSounds () {
	//	for (Sound s: sounds) {
		//	if (s.isAutoStart() == true) {
			//	s.play();
//			}
	//	}
//	}
	
	public void stopAllSounds () {
		for (Sound s: sounds) {
			s.stop();
		}
	}
		
	public ArrayList <Sprite> getPaint () {
		painting.clear();
		for (Object o : objects) {
			if (o.isVisible()) {
				painting.add(o.getSprite());
			}
		}
		
		for (Object st : stars) {
			if (st.isVisible()) {
				painting.add(st.getSprite());
			}
		}
		return painting;
	}
	
	public ArrayList <Speech> getSpeech () {
		return speech;
	}
		
	// mouse click event
	public void MouseClickEvent (MouseEvent inEvent) {
		for (Object o : objects) {
			if (o.isMouseOn(inEvent.getX(), inEvent.getY())) o.ClickEffect();
		}
	}
	
	// mouse move event
	public void MouseMoveEvent (MouseEvent inEvent) {
		for (Object o : objects) {
			if (o.isMouseOn(inEvent.getX(), inEvent.getY())) {
				o.MouseOn();
			} else 
			{
				o.MouseOff ();
			}
		}
	}
	
	// mouse drag event
	public void MouseDragEvent (MouseEvent inEvent) {
	}
	
	public static int getLevel () {
		return Scene.level;
	}
	
	public static void setLevel (int inLevel) {
		Scene.level = inLevel;
	}
	
	public static void setBase (URL inURL) {
		try {
			base = inURL.toURI();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
