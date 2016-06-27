package colouringgame.assets;

import java.awt.Font;
import java.util.ArrayList;

import colouringgame.framework.Object;

public class Speech extends Object{
	protected int length, height, margin;
	protected String currentspeech;
	protected ArrayList <String> speeches;
	protected int randomnumber;
	protected Font speechfont;
		
	public Speech (int inStartX, int StartY) {
		super (inStartX, StartY);
		speeches = new ArrayList <String>();
	}
	
	public void setSpeech (String inText) {
		this.currentspeech = inText;
	}
	
	public void addAutoSpeech (String inText) {
		this.speeches.add(inText);
	}
	
	public String getSpeech () {
		return this.currentspeech;
	}
	
	public void setRandomSpeech () {
		if (this.speeches.size () > 0) {
			this.randomnumber = (int) (Math.random() * (speeches.size() - 1));
			this.currentspeech = this.speeches.get(this.randomnumber);
		}
	}
	
	public int getTextX () {
		return this.startX;
	}
	
	public int getTextY () {
		return this.startY;
	}
	
	public void setFont (Font inFont) {
		this.speechfont = inFont; 
	}
	
	public Font getFont () {
		return this.speechfont;
	}
}
