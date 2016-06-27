package colouringgame.framework;

import java.util.ArrayList;

public class Objective {
	private ArrayList <String> questions;
	private ArrayList <Sound> questionvoice;
	private ArrayList <Pair <Object, Colour>> answers;
	private boolean completed;
	private int questionnumber;
	private int point;
	private int congratstime;
	private boolean congratulated;
	
	public Objective () {
		this.questions = new ArrayList <String> ();
		this.answers = new ArrayList <Pair <Object, Colour>> ();
		this.completed = false;
		this.questionnumber = 0;
		this.point = 0;
		this.congratstime = 0;
	}
	
	public String getQuestion () {
		return this.questions.get(this.questionnumber);
	}
	
	public Sound getQuestionVoice () {
		return this.questionvoice.get(this.questionnumber);
	}
	
	public void addQuestionVoice (Sound inQuestionVoice) {
		this.questionvoice.add(inQuestionVoice);
	}
	
	public void addQuestion (String inQuestion) {
		this.questions.add(inQuestion);
	}
	
	public void addAnswer (Object inObject, Colour inColour) {
		this.answers.add(new Pair <Object, Colour> (inObject, inColour));		
	}
	
	public boolean checkAnswer (Object inObject) {
		boolean tempflag = false;
		if ((inObject.getColour() != null) && (this.questions.size() >= 1) && (this.questionnumber < this.questions.size())){
			
			// check if it's the right object 
			// we need to cast Object because it gets confused with the system Object class
			if (this.answers.get(this.questionnumber).getFirst().getName().equals(inObject.getName())) {
				// check if the object has the right colour
				if (inObject.getColour().equals(answers.get(this.questionnumber).getSecond())) {
					tempflag = true;
					// advance to the next question
					this.questionnumber++;
					// give them 1 point
					this.point++;
					//reset congratulation times to 0 so it will congratulate them;
					this.congratstime = 0;
				}
			}
		}
		
		return tempflag;
	}
	
	public void congratulate () {
		this.congratstime++;
	}
	
	public boolean isCongratulated () {
		if (this.congratstime == 0) {
			this.congratulated = false;
		} else {
			this.congratulated = true;
		}
		
		return this.congratulated;
	}
	
	public int getPoint () {
		return this.point;
	}
	
	public boolean isCompleted () {
		this.CheckComplete();
		return this.completed;
	}
	
	private void CheckComplete () {
		if (this.questionnumber >= this.questions.size()) {
			this.completed = true;
			this.congratstime += this.questions.size();
		}
		else {
			this.completed = false;
		}
	}

}
