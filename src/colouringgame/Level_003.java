package colouringgame;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import colouringgame.assets.ColourObject;
import colouringgame.assets.Cursor;
import colouringgame.assets.Speech;
import colouringgame.framework.Boundary;
import colouringgame.framework.Object;
import colouringgame.framework.Objective;
import colouringgame.framework.Scene;
import colouringgame.framework.Sound;

public class Level_003 extends Scene {
	
	private Object background, palette, chipmunk, speechbubble, nextbutton, backbutton, menubutton;
	private Object star1,star2,star3,star4;
	private ColourObject inksplatter, triangle, square, rectangle, circle;
	private Speech chipmunkspeech, objectivequestion, objectivepoint;
	private Cursor mousecursor;
	private Object bluepaint, redpaint, yellowpaint, greenpaint, purplepaint, whitepaint;
	private Sound correct, incorrect;
	private Sound sred, sblue, syellow, sgreen, spurple, swhite, bgmusic;
	
	Level_003 () {
		this.init();
		
		//create sound
		this.correct = new Sound (base, "\\data\\correctly.wav");
		this.correct.setAutoStart(false);
		this.sounds.add(this.correct);
		
		this.sred = new Sound (base, "\\data\\chosered.wav");
		this.sblue = new Sound (base, "\\data\\choseblue.wav");
		this.syellow = new Sound (base, "\\data\\choseyellow.wav");
		this.sgreen = new Sound (base, "\\data\\chosegreen.wav");
		this.spurple = new Sound (base, "\\data\\chosepurple.wav");
		this.swhite = new Sound (base, "\\data\\chosewhite.wav");
		this.sounds.add(this.sred);
		this.sounds.add(this.sblue);
		this.sounds.add(this.syellow);
		this.sounds.add(this.sgreen);
		this.sounds.add(this.spurple);
		this.sounds.add(this.swhite);

		this.incorrect = new Sound (base, "\\data\\veryclose.wav");
		this.sounds.add(this.incorrect);
		
		this.bgmusic = new Sound (base, "\\data\\background1.wav");
		this.bgmusic.setAutoStart(true);
		this.bgmusic.setInfinite(true);
		this.bgmusic.setGainControl(-10.0f);
		this.sounds.add(this.bgmusic);
		
							
		// create the background
		this.background = new Object ("data/Background_004.png", 0, 0);
		
		// create stars
		this.star1 = new Object ("data/YellowStar.png", 300, 6);
		this.star2 = new Object ("data/YellowStar.png", 370, 6);
		this.star3 = new Object ("data/YellowStar.png", 440, 6);
		this.star4 = new Object ("data/YellowStar.png", 510, 6);
		this.star1.setVisible(false);
		this.star2.setVisible(false);
		this.star3.setVisible(false);
		this.star4.setVisible(false);
		this.stars = new ArrayList <Object> ();
		this.stars.add(this.star1);
		this.stars.add(this.star2);
		this.stars.add(this.star3);
		this.stars.add(this.star4);
				
		// create triangle
		this.triangle = new ColourObject ("data/WhiteTriangle.png", 170, 50);
		this.triangle.setName("triangle");
		this.triangle.addSprite("data/whitetriangle.png", white);
		this.triangle.addSprite("data/redtriangle.png", red);
		this.triangle.addSprite("data/bluetriangle.png", blue);
		this.triangle.addSprite("data/yellowtriangle.png", yellow);
		this.triangle.addSprite("data/greentriangle.png", green);
		this.triangle.addSprite("data/purpletriangle.png", purple);
		this.triangle.addSprite("data/orangetriangle.png", orange);
		this.triangle.addBoundary(new Boundary (194 , 232, 291 , 70, 396 , 232));

		// create a rectangle
		this.rectangle = new ColourObject ("data/WhiteRectangle.png", 170, 250);
		this.rectangle.setName("rectangle");
		this.rectangle.addBoundary(new Boundary (184, 260, 428, 260, 428, 428, 184, 428));
		this.rectangle.addSprite("data/whiterectangle.png", white);
		this.rectangle.addSprite("data/redrectangle.png", red);
		this.rectangle.addSprite("data/bluerectangle.png", blue);
		this.rectangle.addSprite("data/yellowrectangle.png", yellow);
		this.rectangle.addSprite("data/greenrectangle.png", green);
		this.rectangle.addSprite("data/purplerectangle.png", purple);
		this.rectangle.addSprite("data/orangerectangle.png", orange);

		// create a circle
		this.circle = new ColourObject ("data/WhiteCircle.png", 520, 60);
		this.circle.setName("circle");
		this.circle.addBoundary(new Boundary (520, 60, 100));
		this.circle.addSprite("data/whitecircle.png", white);
		this.circle.addSprite("data/redcircle.png", red);
		this.circle.addSprite("data/bluecircle.png", blue);
		this.circle.addSprite("data/yellowcircle.png", yellow);
		this.circle.addSprite("data/greencircle.png", green);
		this.circle.addSprite("data/purplecircle.png", purple);
		this.circle.addSprite("data/orangecircle.png", orange);

		// create a square
		this.square = new ColourObject ("data/WhiteSquare.png", 520, 260);
		this.square.setName("square");
		this.square.addBoundary(new Boundary (536, 273, 698, 273, 698, 426, 536, 426));
		this.square.addSprite("data/whitesquare.png", white);
		this.square.addSprite("data/redsquare.png", red);
		this.square.addSprite("data/bluesquare.png", blue);
		this.square.addSprite("data/yellowsquare.png", yellow);
		this.square.addSprite("data/greensquare.png", green);
		this.square.addSprite("data/purplesquare.png", purple);
		this.square.addSprite("data/orangesquare.png", orange);
		
		// create colour palette
		this.palette = new Object ("data/palette.png", 10, 125);
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
		this.whitepaint = new Object (0,0);
		this.whitepaint.addBoundary(new Boundary (79,290, 116,290, 116,346, 79,346));
		
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
		this.inksplatter.addSprite("data/whiteinksplatter.png", white);
		
		//create paintbrush mouse cursor
		this.mousecursor = new Cursor (Scene.ScreenWidth/2, Scene.ScreenHeight/2, 50, 70);
		this.mousecursor.addSprite("data/RedPaintBrush.png", red);
		this.mousecursor.addSprite("data/BluePaintBrush.png", blue);
		this.mousecursor.addSprite("data/GreenPaintBrush.png", green);
		this.mousecursor.addSprite("data/YellowPaintBrush.png", yellow);
		this.mousecursor.addSprite("data/PurplePaintBrush.png", purple);
		this.mousecursor.addSprite("data/whitepaintBrush.png", white);
		
		// create an objective for this level
		// add the question and then add the answer the order of question and answer should be respective
		this.levelobjective = new Objective ();
		this.objectivepoint = new Speech (494, 35);
		this.objectivepoint.setFont(new Font ("Serif", Font.BOLD, 30));
		this.objectivequestion = new Speech (180, 470);
		this.objectivequestion.setFont(new Font ("Serif", Font.BOLD, 24));
		this.levelobjective.addQuestion("Let's colour the circle blue!");
		this.levelobjective.addAnswer(this.circle, blue);
		this.levelobjective.addQuestion ("Let's colour the square green!");
		this.levelobjective.addAnswer(this.square, green);
		this.levelobjective.addQuestion("Let's colour the rectangle red!");
		this.levelobjective.addAnswer(this.rectangle, red);
		this.levelobjective.addQuestion("Let's colour the triangle purple!");
		this.levelobjective.addAnswer(this.triangle, purple);
				
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
		this.speech.add (this.objectivepoint);
		this.speech.add (this.objectivequestion);
	}
		
	public void SceneUpdate () {
		if (this.levelobjective != null) {
			// is the level completed?
			if (!levelobjective.isCompleted()) {
				this.objectivequestion.setSpeech("Question: " + this.levelobjective.getQuestion());
				//this.objectivepoint.setSpeech(" X " + Integer.toString(this.levelobjective.getPoint()));
				if (this.levelobjective.getPoint() > 0) {
					this.stars.get(this.levelobjective.getPoint() - 1 ).setVisible(true);
				}
			}
			else {
				this.objectivequestion.setSpeech("Congratuations! You have completed this Level!");
				//this.objectivepoint.setSpeech(" X " + Integer.toString(this.levelobjective.getPoint()));
				if (this.levelobjective.getPoint() > 0) this.stars.get(this.levelobjective.getPoint() - 1).setVisible(true);
			}
		} 
	}
	
	// mouse click event
	public void MouseClickEvent (MouseEvent inEvent) {
		
		// chipmunk click
		if (this.chipmunk.isMouseOn (inEvent.getX(), inEvent.getY())) {
			this.speechbubble.setVisible(true);
			this.chipmunkspeech.setRandomSpeech();
			this.chipmunkspeech.setVisible(true);
			
		}
		
		// click event for the triangle
		else if (this.triangle.isMouseOn (inEvent.getX(), inEvent.getY())) {
			this.speechbubble.setVisible(true);
			this.chipmunkspeech.setSpeech ("You clicked on the triangle!");
			this.chipmunkspeech.setVisible(true);
			
			if (Scene.currentcolour != null) {
				this.triangle.setColour(Scene.getCurrentColour());
			} else {
				this.triangle.reset();
			}
			
			// there's a click, check the answer
			if ((this.levelobjective != null) && (!this.levelobjective.isCompleted())) {
				// check answer
				if (this.levelobjective.checkAnswer(this.triangle)) {
					// if the answer is correct, check if we have congratulated
					if (!this.levelobjective.isCongratulated()) {
						// congratulate
						this.levelobjective.congratulate();
						this.speechbubble.setVisible(true);
						this.chipmunkspeech.setVisible(true);
						this.chipmunkspeech.setSpeech("Correct! Well done!");
						this.correct.play();
					}
				}
				else {
					//wrong answer 
					this.levelobjective.congratulate();
					this.speechbubble.setVisible(true);
					this.chipmunkspeech.setVisible(true);
					this.chipmunkspeech.setSpeech("That's not correct.");
					this.incorrect.play();
				}
			}
		}
		
		// click event for the rectangle
		else if (this.rectangle.isMouseOn(inEvent.getX(), inEvent.getY())) {
			this.speechbubble.setVisible(true);
			this.chipmunkspeech.setSpeech("You clicked on the rectangle!");
			this.chipmunkspeech.setVisible(true);
			
			if (Scene.currentcolour != null) {
				this.rectangle.setColour(Scene.getCurrentColour());
			}else {
				this.rectangle.reset();
			}
			
			// there's a click, check the answer
			if ((this.levelobjective != null) && (!this.levelobjective.isCompleted())) {
				// check answer
				if (this.levelobjective.checkAnswer(this.rectangle)) {
					// if the answer is correct, check if we have congratulated
					if (!this.levelobjective.isCongratulated()) {
						// congratulate
						this.levelobjective.congratulate();
						this.speechbubble.setVisible(true);
						this.chipmunkspeech.setVisible(true);
						this.chipmunkspeech.setSpeech("Correct! Well done!");
						this.correct.play();
					}
				}
				else {
					//wrong answer 
					this.levelobjective.congratulate();
					this.speechbubble.setVisible(true);
					this.chipmunkspeech.setVisible(true);
					this.chipmunkspeech.setSpeech("That's not correct.");
					this.incorrect.play();
				}
			}
			
		}
		
		// click event for the circle
		else if (this.circle.isMouseOn(inEvent.getX(), inEvent.getY())) {
			this.speechbubble.setVisible(true);
			this.chipmunkspeech.setSpeech("You clicked on the circle!");
			this.chipmunkspeech.setVisible(true);
			
			if (Scene.currentcolour != null) {
				this.circle.setColour(Scene.getCurrentColour());
			} else {
				this.circle.reset();
			}
			
			// there's a click, check the answer
			if ((this.levelobjective != null) && (!this.levelobjective.isCompleted())) {
				// check answer
				if (this.levelobjective.checkAnswer(this.circle)) {
					// if the answer is correct, check if we have congratulated
					if (!this.levelobjective.isCongratulated()) {
						// congratulate
						this.levelobjective.congratulate();
						this.speechbubble.setVisible(true);
						this.chipmunkspeech.setVisible(true);
						this.chipmunkspeech.setSpeech("Correct! Well done!");
						this.correct.play();
					}
				}
				else {
					//wrong answer 
					this.levelobjective.congratulate();
					this.speechbubble.setVisible(true);
					this.chipmunkspeech.setVisible(true);
					this.chipmunkspeech.setSpeech("That's not correct.");
					this.incorrect.play();
				}
			}
			
		}
		
		// click event for the square
		else if (this.square.isMouseOn(inEvent.getX(), inEvent.getY())) {
			this.speechbubble.setVisible(true);
			this.chipmunkspeech.setSpeech("You clicked on the square!");
			this.chipmunkspeech.setVisible(true);
			
			if (Scene.currentcolour != null) {
				this.square.setColour(Scene.getCurrentColour());
			} else  {
				this.square.reset();
			}
			
			// there's a click, check the answer
			if ((this.levelobjective != null) && (!this.levelobjective.isCompleted())) {
				// check answer
				if (this.levelobjective.checkAnswer(this.square)) {
					// if the answer is correct, check if we have congratulated
					if (!this.levelobjective.isCongratulated()) {
						// congratulate
						this.levelobjective.congratulate();
						this.speechbubble.setVisible(true);
						this.chipmunkspeech.setVisible(true);
						this.chipmunkspeech.setSpeech("Correct! Well done!");
						this.correct.play();
					}
				}
				else {
					//wrong answer 
					this.levelobjective.congratulate();
					this.speechbubble.setVisible(true);
					this.chipmunkspeech.setVisible(true);
					this.chipmunkspeech.setSpeech("That's not correct.");
					this.incorrect.play();
				}
			}
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
			this.sblue.play();
			Scene.setCurrentColour(blue);
			
		}
		
		else if (this.redpaint.isMouseOn(inEvent.getX(), inEvent.getY())) {
			
			//set the inksplatter
			this.inksplatter.setVisible(true);
			this.inksplatter.setColour(red);
			this.mousecursor.setColour(red);
			this.sred.play();
			Scene.setCurrentColour(red);
			
		}
		
		else if (this.yellowpaint.isMouseOn(inEvent.getX(), inEvent.getY())) {
			
			//set the inksplatter
			this.inksplatter.setVisible(true);
			this.inksplatter.setColour(yellow);
			this.mousecursor.setColour(yellow);
			this.syellow.play();
			Scene.setCurrentColour( yellow);
			
		}
		
		else if (this.greenpaint.isMouseOn(inEvent.getX(), inEvent.getY())) {
			
			//set the inksplatter
			this.inksplatter.setVisible(true);
			this.inksplatter.setColour(green);
			this.mousecursor.setColour(green);
			this.sgreen.play();
			Scene.setCurrentColour(green);
			
		}
		
		else if (this.purplepaint.isMouseOn(inEvent.getX(), inEvent.getY())) {
			
			//set the inksplatter
			this.inksplatter.setVisible(true);
			this.inksplatter.setColour(purple);
			this.mousecursor.setColour(purple);
			this.spurple.play();
			Scene.setCurrentColour(purple);
		}
		
		else if (this.whitepaint.isMouseOn(inEvent.getX(), inEvent.getY())) {
			
			//set the inksplatter
			this.inksplatter.setVisible(true);
			this.inksplatter.setColour(white);
			this.mousecursor.setColour(white);
			this.swhite.play();
			Scene.currentcolour = white;
			
		}
		
		else {
			this.inksplatter.setVisible(false);
			this.mousecursor.reset();
			Scene.currentcolour = null;
		}
	}
	
	public void MouseMoveEvent (MouseEvent inEvent) {
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
