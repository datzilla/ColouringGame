package colouringgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.ArrayList;

import colouringgame.assets.Speech;
import colouringgame.framework.*;

@SuppressWarnings("serial")
public class StartingClass extends Applet implements MouseListener, MouseMotionListener, Runnable {
	public URL base;
	public int ScreenWidth, ScreenHeight;
	private Image image;
	private Graphics second;
	private ArrayList <Scene> allscenes = new ArrayList <Scene> ();
	private Scene currentscene;
	private int GameLevel;
	private static final int updateinterval = 17; // in milliseconds
	
	// Applet method 
	public void init () {
		
		//setup the screen size
		setSize (800, 480);
		this.ScreenHeight = this.getHeight();
		this.ScreenWidth = this.getWidth();

		Scene.setScreenSize(this.ScreenWidth, this.ScreenHeight);
		
		setBackground (Color.black);
		addMouseListener (this);
		addMouseMotionListener (this);
		setFocusable(true);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Colouring Game");
		
		try {
			base = getDocumentBase();
			Scene.setBase (base);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		this.GameLevel = 0;
		
		// load all the game levels
		this.allscenes.add (new MainMenu ());
		this.allscenes.add (new Level_001 ());
		this.allscenes.add (new Level_002 ());
		this.allscenes.add (new Level_003 ());
		//this.allscenes.add (new Level_004 ());
		this.allscenes.add(new Credits());
		Scene.setNumberOfLevels(this.allscenes.size());
		
		if (this.currentscene == null) {
			this.currentscene = this.allscenes.get(this.GameLevel);
		}	
	}
	
	//implementing method that start the Applet.
	public void start () {	
		Thread thread = new Thread (this);
		thread.start();
	}
	
	// this is the method need to be implemented to have it running multiple threads
	public void run () {
		while (true)
		{
			this.currentscene.Start();
			this.currentscene.Update();
			
			if (this.GameLevel != Scene.getLevel())
			{
				if ((Scene.getLevel() <= this.allscenes.size() - 1) && (Scene.getLevel() >= 0)) {
					this.currentscene.Stop();
					this.GameLevel = Scene.getLevel();
					this.currentscene = this.allscenes.get(this.GameLevel);
				}
				else {
					Scene.setLevel(this.GameLevel);
				}
			}
			

	
			repaint();
			
			try {
				Thread.sleep(StartingClass.updateinterval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void update (Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}
	
	public void paint (Graphics g) {
		for (Sprite p : this.currentscene.getPaint()) {
			if (p.getImagePath() != null){
				g.drawImage (getImage (this.base, p.getImagePath()), p.getStartX(), p.getStartY(), this);
			}
		}
		
		for (Speech s : this.currentscene.getSpeech()) {
			if (s.isVisible()) {
				if (s.getSpeech() != null) {
					
					// set the font size
					if (s.getFont() != null) {
						g.setFont(s.getFont());
					} else {
						g.setFont (new Font ("Serif", Font.PLAIN, 12));
					}
					g.drawString(s.getSpeech (), s.getTextX(), s.getTextY());
				}
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {
	}

	 public void mouseClicked(MouseEvent e) {
    	this.currentscene.MouseClickEvent(e);
    }

	public void mouseMoved (MouseEvent e) {
		System.out.println("X: " + e.getX() + " Y:" + e.getY());
		this.currentscene.MouseMoveEvent(e);
	}
		
	public void mouseDragged(MouseEvent e) {
	}

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
