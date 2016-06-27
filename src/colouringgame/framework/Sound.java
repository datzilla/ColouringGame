package colouringgame.framework;
import java.io.File;
import java.net.URI;

import javax.sound.sampled.*;

public class Sound {
	private boolean isplaying;
	private File soundfile;
	private AudioInputStream stream;
	private AudioFormat format;
	private Clip clip;
	private DataLine.Info info;
	private int updatecount; // convert frames to second, since we update every 17ms. so every second is 60 counts.
	private long totallength;
	private long lapsedtime;
	private boolean isrepeated;
	private int totalrepeats;
	private int repeats;
	private boolean isinfinite;
	private int totalupdatespersecond = (int) 1000/17;
	private FloatControl gainControl;
	private boolean isAutoStart;
	private long delaytime;
	private long lapseddelaytime;
	private boolean isplayed;
	private boolean isplayonce;
	private float gain;
	
	public Sound (URI inbase, String inSound) {
		File base = new File (inbase);
		this.soundfile = new File (base.getParentFile().toString() + inSound);
		
		this.lapsedtime = 0;
		this.repeats = 0;
		this.lapseddelaytime = 0;
		this.delaytime = 0;
		this.gain = 0.0f;
		
		this.isrepeated = false;
		this.isinfinite = false;
		this.isAutoStart = false;
		this.isplayed = false;
		this.isplayonce = true;

		this.load();
	}
	
	private void load () {
		try {
			this.stream = AudioSystem.getAudioInputStream(this.soundfile);
			this.format = stream.getFormat();
			this.info = new DataLine.Info(Clip.class, format);
			this.clip = (Clip) AudioSystem.getLine(this.info);
			this.clip.open (this.stream);
			this.gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			if (this.gain != 0) this.gainControl.setValue(this.gain);
			this.setTotalLength ();
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
	
	public void play () {
		if (this.isplaying == false) {
			if (this.lapseddelaytime >= this.delaytime) {
				this.load();
			    this.clip.start();
			    this.isplaying = true;
			    this.isplayed = true;
			    this.lapsedtime = 0;
			    this.lapseddelaytime = 0;
			}
		}
	}
	
	public void stop () {
		if (this.isplaying == true) {
			this.clip.stop();
			this.isplaying = false;
			this.lapsedtime = 0;
			this.lapseddelaytime = 0;
		}
	}
	
	private void setTotalLength() {
	    long audioFileLength = this.soundfile.length();
	    int frameSize = format.getFrameSize();
	    float frameRate = format.getFrameRate();
	    this.totallength = (long) Math.ceil(audioFileLength / (frameSize * frameRate));
	}
	
	public boolean isAutoStart () {
		return this.isAutoStart;
	}
	
	public void setAutoStart (boolean inAutoStart) {
		this.isAutoStart = inAutoStart;
	}
	
	private void checkEndOfSound () {
		if (this.isplaying) {
			if (this.lapsedtime > this.totallength) {
				this.stop();
			}
		}
	}
	
	public void Update () {
		this.updatecount++;
	
		// convert lapsed time to second
		if (this.updatecount >= this.totalupdatespersecond) {
			
			this.updatecount = 0;

			if (this.isplaying == true) {
				this.lapsedtime++;
			} else {
				this.lapseddelaytime++;
			}
		}
		
		this.checkEndOfSound();	
		
		//if auto start, then we play it
		if (this.isAutoStart == true) {
			if (this.isrepeated == true  && this.isplaying == false) this.repeat();
			if (this.isinfinite == true) this.play();
			if (this.isplayonce == true && this.isplayed==false) this.play();
		}
	}
	
	private void repeat () {
		// if the lapsed time is greater than the total length, we know it has repeated
		this.repeats++;
		System.out.println("got here");
		if (this.repeats <= this.totalrepeats) {
			this.play();
		} else {
			this.isrepeated = false;
		}
	}
	
	public void setRepeats (int inRepeats) {
		if (inRepeats > 0 ) {
			this.isrepeated = true;
			this.totalrepeats = inRepeats;
			this.isplayonce = false;
		} else {
			this.isrepeated = false;
			this.totalrepeats = inRepeats;
			this.isplayonce = true;
		}
	}
	
	public void setInfinite (boolean inInfinite) {
		this.isinfinite = inInfinite;
		if (this.isinfinite == true) {
			this.isplayonce = false;
		}
		else {
			this.isplayonce = true;
		}
	}
	
	public void setGainControl (float inDecibels) {
		this.gain = inDecibels;
	}
	
	public void setDelay (long inSeconds) {
		this.delaytime = inSeconds;
	}
}
