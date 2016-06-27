package colouringgame;

import java.awt.Font;
import java.awt.event.MouseEvent;

import colouringgame.assets.ColourObject;
import colouringgame.assets.Cursor;
import colouringgame.assets.Speech;
import colouringgame.framework.Boundary;
import colouringgame.framework.Colour;
import colouringgame.framework.Object;
import colouringgame.framework.Scene;
import colouringgame.framework.Sound;

public class Level_001 extends Scene {
	
	private Object background,triangle, square, rectangle, circle, palette, chipmunk, speechbubble;
	private Object nextbutton, backbutton, menubutton;
	private ColourObject inksplatter;
	private Speech chipmunkspeech;
	private Cursor mousecursor;
	private Sound backgroundmusic, welcomevoice, trianglesound, hey, circlesound, rectanglesound, squaresound;
	

	private Object bluepaint, redpaint, yellowpaint, greenpaint, purplepaint, orangepaint;
	
	Level_001 () {
		this.init();
		
		//background music
		this.backgroundmusic = new Sound (base, "\\data\\intro3.wav");
		this.backgroundmusic.setInfinite(true);
		this.backgroundmusic.setAutoStart(true);
		this.backgroundmusic.setGainControl(-10.0f);
		this.sounds.add(this.backgroundmusic);
		
		this.trianglesound = new Sound (base, "\\data\\clicktriangle.wav");
		this.sounds.add(this.trianglesound);
		this.hey = new Sound (base, "\\data\\hey.wav");
		this.sounds.add(this.hey);
		this.circlesound = new Sound (base, "\\data\\circleclick.wav");
		this.sounds.add(this.circlesound);
		this.rectanglesound = new Sound (base, "\\data\\rectclick.wav");
		this.sounds.add(this.rectanglesound);
		this.squaresound = new Sound (base, "\\data\\licksquare.wav");
		this.sounds.add(this.squaresound);
		
		//welcome voice to level
		this.welcomevoice = new Sound (base, "\\data\\Welcome.wav");
		this.welcomevoice.setAutoStart(true);
		this.welcomevoice.setDelay(1);
		//welcomevoice.setRepeats(1);
		this.sounds.add(this.welcomevoice);
		
						
		// create the background
		this.background = new Object ("data/Background_002.png", 0, 0);
		
		// create triangle
		this.triangle = new Object ("data/BlueTriangle.png", 170, 50);
		this.triangle.addBoundary(new Boundary (194 , 232, 291 , 70, 396 , 232));

		// create a rectangle
		this.rectangle = new Object ("data/RedRectangle.png", 170, 250);
		this.rectangle.addBoundary(new Boundary (184, 260, 428, 260, 428, 428, 184, 428));

		// create a circle
		this.circle = new Object ("data/GreenCircle.png", 520, 60);
		this.circle.addBoundary(new Boundary (520, 60, 100));

		// create a square
		this.square = new Object ("data/YellowSquare.png", 520, 260);
		this.square.addBoundary(new Boundary (536, 273, 698, 273, 698, 426, 536, 426));
		
		// create colour palette
		this.palette = new Object ("data/palette.png", 10, 125);
		this.palette.setVisible(false);
		this.bluepaint = new Object (0,0);
		this.bluepaint.addBoundary(new Boundary (79,154, 116,154, 116,206, 79,206));
		this.redpaint = new Object (0,0);
		this.redpaint.addBoundary(new Boundary (40,154, 78,154, 78,206, 40,206));
		this.yellowpaint = new Object (0,0);
		this.yellowpaint.addBoundary(new Boundary (40,221, 78,221, 78,278, 40,278));
		this.greenpaint = new Object (0,0);
		this.greenpaint.addBoundary(new Boundary (79,221, 116,221, 116,278, 79,278));
		this.purplepaint = new Object (0,0);
		this.purplepaint.addBoundary(new Boundary (40,290, 78,290, 78,346, 40,346));
		this.orangepaint = new Object (0,0);
		this.orangepaint.addBoundary(new Boundary (79,290, 116,290, 116,346, 79,346));
		
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
		
		this.chipmunkspeech = new Speech (45,275);
		this.chipmunkspeech.setVisible(false);
		this.chipmunkspeech.setTimeout(200);
		this.chipmunkspeech.setFont(new Font ("Serif", Font.BOLD, 14));
		this.chipmunkspeech.addAutoSpeech("Hello!");
		this.chipmunkspeech.addAutoSpeech("Hi there!");
		this.chipmunkspeech.addAutoSpeech("How are you today?");
		this.chipmunkspeech.addAutoSpeech("Having a nice day?");
		this.chipmunkspeech.addAutoSpeech("That tickles");
		this.chipmunkspeech.addAutoSpeech("Hey! I am warning you");
		this.chipmunkspeech.addAutoSpeech("My name is Chippy");
		
		// setup the buttons
		// menu button
		this.menubutton = new Object (17,8);
		this.menubutton.addSprite("data/menubutton.png", 0);
		this.menubutton.addSprite("data/selectedmenubutton.png", 0);
		this.menubutton.addBoundary(new Boundary (17,8,137,8,137,38,17,38));
		menubutton.addMouseOnFX(new Sound (base, "\\data\\backmainmenu.wav"));
		
		//back buttons
		this.backbutton = new Object (160,8);
		this.backbutton.addSprite("data/backbutton.png", 0);
		this.backbutton.addSprite("data/selectedbackbutton.png", 0);
		this.backbutton.addBoundary(new Boundary (160,8, 280, 8, 280,38,160,38));
		backbutton.addMouseOnFX(new Sound (base, "\\data\\backprevious.wav"));
		
		// next button
		this.nextbutton = new Object (640,8);
		this.nextbutton.addSprite("data/nextbutton.png", 0);
		this.nextbutton.addSprite("data/selectednextbutton.png", 0);
		this.nextbutton.addBoundary(new Boundary (640,8, 760, 8, 760,38,640,38));
		nextbutton.addMouseOnFX(new Sound (base, "\\data\\next.wav"));
		
		// create ink splatter
		this.inksplatter = new ColourObject (26, 43);
		this.inksplatter.setVisible(false);
		this.inksplatter.addSprite("data/redinksplatter.png",red);
		this.inksplatter.addSprite("data/blueinksplatter.png", blue);
		this.inksplatter.addSprite("data/greeninksplatter.png", green);
		this.inksplatter.addSprite("data/yellowinksplatter.png", yellow);
		this.inksplatter.addSprite("data/purpleinksplatter.png", purple);
		this.inksplatter.addSprite("data/orangeinksplatter.png", orange);
		
		//create paintbrush mouse cursor
		this.mousecursor = new Cursor (Scene.ScreenWidth/2, Scene.ScreenHeight/2, 50, 70);
		this.mousecursor.addSprite("data/RedPaintBrush.png", red);
		this.mousecursor.addSprite("data/BluePaintBrush.png", blue);
		this.mousecursor.addSprite("data/GreenPaintBrush.png", green);
		this.mousecursor.addSprite("data/YellowPaintBrush.png", yellow);
		this.mousecursor.addSprite("data/PurplePaintBrush.png", purple);
		this.mousecursor.addSprite("data/OrangePaintBrush.png", orange);
		
		
		// add objects and speech to collection
		// the order that you put the object in this list, this is the order it will get painted. eg
		// the first one will be at the back and last object is at the front
		this.objects.add (this.background);
		this.objects.add (this.triangle);
		this.objects.add(this.rectangle);
		this.objects.add (this.circle);
		this.objects.add (this.chipmunk);
		this.objects.add (this.square);
		this.objects.add (this.palette);
		this.objects.add (this.speechbubble);
		this.objects.add(this.menubutton);
		this.objects.add(this.backbutton);
		this.objects.add(this.nextbutton);
		this.objects.add(this.inksplatter);
		this.objects.add(this.mousecursor);
		this.speech.add (this.chipmunkspeech);
	}
	
	// mouse click event
	public void MouseClickEvent (MouseEvent inEvent) {
		
		// chipmunk click
		if (this.chipmunk.isMouseOn (inEvent.getX(), inEvent.getY())) {
			this.speechbubble.setVisible(true);
			this.chipmunkspeech.setRandomSpeech();
			this.chipmunkspeech.setVisible(true);
			this.hey.play();
			
		}
		
		// click event for the triangle
		else if (this.triangle.isMouseOn (inEvent.getX(), inEvent.getY())) {
			
	
			this.trianglesound.play();
			
			this.speechbubble.setVisible(true);
			this.chipmunkspeech.setSpeech ("You clicked on the triangle!");
			
			//show the cipmunk speech
			this.chipmunkspeech.setVisible(true);
			
			//set the inksplatter
			this.inksplatter.setVisible(true);
			this.inksplatter.setColour(blue);
			
			//set paint brush
			this.mousecursor.setColour(blue);
			
		}
		
		// click event for the rectangle
		else if (this.rectangle.isMouseOn(inEvent.getX(), inEvent.getY())) {
			this.speechbubble.setVisible(true);
			this.chipmunkspeech.setSpeech("You clicked on the rectangle!");
			this.chipmunkspeech.setVisible(true);
			//set the inksplatter
			this.inksplatter.setVisible(true);
			this.inksplatter.setColour(red);
			this.mousecursor.setColour(red);
			this.rectanglesound.play();
		}
		
		// click event for the circle
		else if (this.circle.isMouseOn(inEvent.getX(), inEvent.getY())) {
			this.speechbubble.setVisible(true);
			this.chipmunkspeech.setSpeech("You clicked on the circle!");
			this.chipmunkspeech.setVisible(true);
			//set the inksplatter
			this.inksplatter.setVisible(true);
			this.inksplatter.setColour(green);
			this.mousecursor.setColour(green);
			this.circlesound.play();
		}
		
		// click event for the square
		else if (this.square.isMouseOn(inEvent.getX(), inEvent.getY())) {
			this.speechbubble.setVisible(true);
			this.chipmunkspeech.setSpeech("You clicked on the square!");
			this.chipmunkspeech.setVisible(true);
			//set the inksplatter
			this.inksplatter.setVisible(true);
			this.inksplatter.setColour(yellow);
			this.mousecursor.setColour(yellow);
			this.squaresound.play();
		}
		
		// click event for menu button
		else if (this.menubutton.isMouseOn(inEvent.getX(), inEvent.getY())) {
			Scene.level = 0;
		}
		
		// click event for next button
		else if (this.nextbutton.isMouseOn(inEvent.getX(), inEvent.getY())) {
			Scene.level++;
		}
		
		// click event for back button
		else if (this.backbutton.isMouseOn(inEvent.getX(), inEvent.getY())) {
			Scene.level--;
		}
		
		else if (this.bluepaint.isMouseOn(inEvent.getX(), inEvent.getY())) {
			
			//set the inksplatter
			this.inksplatter.setVisible(true);
			this.inksplatter.setColour(blue);
			this.mousecursor.setColour(blue);
			
		}
		
		else if (this.redpaint.isMouseOn(inEvent.getX(), inEvent.getY())) {
			
			//set the inksplatter
			this.inksplatter.setVisible(true);
			this.inksplatter.setColour(red);
			this.mousecursor.setColour(red);
			Scene.currentcolour = red;
			
		}
		
		else if (this.yellowpaint.isMouseOn(inEvent.getX(), inEvent.getY())) {
			
			//set the inksplatter
			this.inksplatter.setVisible(true);
			this.inksplatter.setColour(yellow);
			this.mousecursor.setColour(yellow);
			Scene.currentcolour = yellow;
			
		}
		
		else if (this.greenpaint.isMouseOn(inEvent.getX(), inEvent.getY())) {
			
			//set the inksplatter
			this.inksplatter.setVisible(true);
			this.inksplatter.setColour(green);
			this.mousecursor.setColour(green);
			Scene.currentcolour = green;
			
		}
		
		else if (this.purplepaint.isMouseOn(inEvent.getX(), inEvent.getY())) {
			
			//set the inksplatter
			this.inksplatter.setVisible(true);
			this.inksplatter.setColour(purple);
			this.mousecursor.setColour(purple);
			Scene.currentcolour = purple;
			
		}
		
		else if (this.orangepaint.isMouseOn(inEvent.getX(), inEvent.getY())) {
			
			//set the inksplatter
			this.inksplatter.setVisible(true);
			this.inksplatter.setColour(orange);
			this.mousecursor.setColour(orange);
			Scene.currentcolour = orange;
			
		}
		
		else {
			this.inksplatter.setVisible(false);
			this.mousecursor.reset();
			Scene.currentcolour = null;
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
		
		this.mousecursor.setPosition(inEvent.getX(), inEvent.getY());
	}
}
