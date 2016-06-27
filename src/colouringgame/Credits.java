package colouringgame;

import java.awt.event.MouseEvent;

import colouringgame.assets.ColourObject;
import colouringgame.assets.Cursor;
import colouringgame.assets.Speech;
import colouringgame.framework.Boundary;
import colouringgame.framework.Colour;
import colouringgame.framework.Object;
import colouringgame.framework.Scene;
import colouringgame.framework.Sound;

public class Credits extends Scene {
	
	private Object background, chipmunk, speechbubble, menubutton;
	private Speech chipmunkspeech;
	private Sound backgroundmusic;
	
	Credits () {
		this.init();
						
		// create the background
		this.background = new Object ("data/Credits_Background.png", 0, 0);
		
		//background music
		this.backgroundmusic = new Sound (base, "\\data\\intro3.wav");
		this.backgroundmusic.setInfinite(true);
		this.backgroundmusic.setAutoStart(true);
		this.backgroundmusic.setGainControl(-10.0f);
		sounds.add(this.backgroundmusic);
		
			
		// create host character
		this.chipmunk = new Object (30, 360);
		this.chipmunk.addSprite("data/chipmunk1.png", 300);
		this.chipmunk.addSprite("data/chipmunk2.png", 1);
		this.chipmunk.addSprite("data/chipmunk3.png", 1);
		this.chipmunk.addSprite("data/chipmunk4.png", 1);
		this.chipmunk.addSprite("data/chipmunk5.png", 5);
		this.chipmunk.addSprite("data/chipmunk4.png", 1);
		this.chipmunk.addSprite("data/chipmunk3.png", 1);
		this.chipmunk.addSprite("data/chipmunk2.png", 1);
		this.chipmunk.setAnimated();
		this.chipmunk.addBoundary(new Boundary (24,369, 40));
		this.chipmunk.addBoundary(new Boundary (44,438, 92,438, 97,497, 41,465));
		
		//set up speech
		this.speechbubble = new Object ("data/Speech.png", 35,219);
		this.speechbubble.setVisible(false);
		this.speechbubble.setTimeout(200);
		
		this.chipmunkspeech = new Speech (55,270);
		this.chipmunkspeech.setVisible(false);
		this.chipmunkspeech.setTimeout(200);
		this.chipmunkspeech.addAutoSpeech("Hello!");
		this.chipmunkspeech.addAutoSpeech("Hi there!");
		this.chipmunkspeech.addAutoSpeech("How are you today?");
		this.chipmunkspeech.addAutoSpeech("Having a nice day?");
		this.chipmunkspeech.addAutoSpeech("Stop touching me");
		this.chipmunkspeech.addAutoSpeech("Hey! I am warning you");
		this.chipmunkspeech.addAutoSpeech("Stop abusing me!");
		this.chipmunkspeech.addAutoSpeech("I am reporting you!");
		
		// setup the buttons
		// menu button
		this.menubutton = new Object (17,8);
		this.menubutton.addSprite("data/menubutton.png", 0);
		this.menubutton.addSprite("data/selectedmenubutton.png", 0);
		this.menubutton.addBoundary(new Boundary (17,8,137,8,137,38,17,38));
		
		
		// add objects and speech to collection
		// the order that you put the object in this list, this is the order it will get painted. eg
		// the first one will be at the back and last object is at the front
		this.objects.add (this.background);
		this.objects.add (this.chipmunk);
		this.objects.add (this.speechbubble);
		this.objects.add(this.menubutton);
		this.speech.add (this.chipmunkspeech);
	}
	
	// mouse click event
	public void MouseClickEvent (MouseEvent inEvent) {
		
		// chipmunk click
		if (this.chipmunk.isMouseOn (inEvent.getX(), inEvent.getY())) {
			this.speechbubble.setVisible(true);
			this.chipmunkspeech.setRandomSpeech();
			this.chipmunkspeech.setVisible(true);
			
		}
	
		// click event for menu button
		else if (this.menubutton.isMouseOn(inEvent.getX(), inEvent.getY())) {
			Scene.level = 0;
		}
		
		else {
			
		}
	}
	
	public void MouseMoveEvent (MouseEvent inEvent) {
		//System.out.println( "X: " + inEvent.getX() +  " Y: " + inEvent.getY());
		for (Object o : objects) {
			if (o.isMouseOn(inEvent.getX(), inEvent.getY())) {
				o.MouseOn();
			} else 
			{
				o.MouseOff ();
			}
		}
	}
}
