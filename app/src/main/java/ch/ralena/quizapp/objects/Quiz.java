package ch.ralena.quizapp.objects;

import ch.ralena.quizapp.objects.Question;

public class Quiz {
	private Question mQuestion;
	private int mTotalTries;
	private int mCorrectTries;
	public final static int MAX_TRIES = 20;

	public Quiz() {
		mQuestion = new Question();
		mTotalTries = 0;
		mCorrectTries = 0;
	}

	public boolean nextQuestion() {
		if (mTotalTries == MAX_TRIES)
			return false;
		else {
			mQuestion.changeEquation();
			return true;
		}
	}

	public boolean checkAnswer(int answer) {
		boolean correct = answer == mQuestion.getCorrect();
		mTotalTries++;
		if (correct)
			mCorrectTries++;
		return correct;
	}

	// getters

	public String getQuestion() {
		return mQuestion.getQuestion();
	}

	public int getTotalTries() {
		return mTotalTries;
	}

	public int getCorrectTries() {
		return mCorrectTries;
	}

	public int getAnswer(int i) {
		return mQuestion.getAnswer(i);
	}
}