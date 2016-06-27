
package colouringgame;

import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import colouringgame.framework.Boundary;
import colouringgame.framework.Scene;
import colouringgame.framework.Object;
import colouringgame.framework.Sound;

public class MainMenu extends Scene {
	
	Object background;
	Object playbutton;
	Object quitbutton;
	Object creditsbutton;
	Sound backgroundmusic;
	
	MainMenu () {
		this.init();
		
		//background music
		backgroundmusic = new Sound (base, "\\data\\bgrnd.wav");
		backgroundmusic.setInfinite(true);
		backgroundmusic.setGainControl(-10.0f);
		backgroundmusic.setAutoStart(true);
		sounds.add(backgroundmusic);

		
		//background object
		background = new Object ("data/Background_001.png", 0,0);
				
		// playbutton
		playbutton = new Object (300, 144);
		playbutton.addSprite("data/playbutton.png", 0);
		playbutton.addSprite ("data/SelectedPlaybutton.png", 0);
		playbutton.addBoundary(new Boundary (300, 144, 500, 144, 500, 194, 300, 194));
		playbutton.addMouseOnFX(new Sound (base, "\\data\\start the game.wav"));
			
		//quit button
		quitbutton = new Object (300, 200);
		quitbutton.addSprite("data/QuitButton.png", 0);
		quitbutton.addSprite ("data/SelectedQuitButton.png", 0);
		quitbutton.addBoundary(new Boundary (300, 200, 500,200, 500,250, 300,250));
		quitbutton.addMouseOnFX(new Sound (base, "\\data\\exit.wav"));
		
		//credits button
		creditsbutton = new Object (300, 256);
		creditsbutton.addSprite("data/CreditsButton.png", 0);
		creditsbutton.addSprite ("data/SelectedCreditsButton.png", 0);
		creditsbutton.addBoundary(new Boundary (300, 256, 500, 256, 500, 306, 300,306));
		creditsbutton.addMouseOnFX(new Sound (base, "\\data\\credits.wav"));
		
		// add all object to a list to get update
		objects.add (background);
		objects.add (playbutton);
		objects.add (quitbutton);
		objects.add (creditsbutton);	
	}
	
	// mouse click event
	public void MouseClickEvent (MouseEvent inEvent) {
		
		if (this.playbutton.isMouseOn(inEvent.getX(), inEvent.getY())) {
			Scene.level++;
			this.stopAllSounds();
		} 
		
		else if (this.quitbutton.isMouseOn(inEvent.getX(), inEvent.getY())) {
			this.stopAllSounds();
			System.exit(0);
		}
		
		else if (this.creditsbutton.isMouseOn(inEvent.getX(), inEvent.getY())) {
			Scene.level = Scene.numlevel - 1;
			this.stopAllSounds();
		}
		
		else {
			
		}
	}
}
