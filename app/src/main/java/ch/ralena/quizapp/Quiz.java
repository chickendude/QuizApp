package ch.ralena.quizapp;

public class Quiz {
	private Question mQuestion;
	private int mTotalTries;
	private int mCorrectTries;

	public Quiz() {
		mQuestion = new Question();
		mTotalTries = 0;
		mCorrectTries = 0;
	}

	public void nextQuestion() {
		mQuestion.changeEquation();
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