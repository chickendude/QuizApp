package ch.ralena.quizapp;

import java.util.Arrays;
import java.util.Random;

public class Question {
	private String mQuestion;
	private int[] mAnswer = new int[3];
	private int mCorrect;
	private static Random rand = new Random();

	public Question() {
		changeEquation();
	}

	public void changeEquation() {
		// get the numbers to add together
		int int1 = rand.nextInt(99) + 1;
		int int2 = rand.nextInt(100 - int1) + 1;
		// create the mQuestion string
		mQuestion = int1 + " + " + int2 + " =";

		// fill the mAnswer box with random data
		for (int i = 0; i < mAnswer.length; i++) {
			int value;
			do {
				value = rand.nextInt(99) + 1;
			} while (Arrays.asList(mAnswer).contains(value));
			mAnswer[i] = rand.nextInt(99) + 1;
		}

		// make sure the mCorrect mAnswer is actually mCorrect
		mCorrect=rand.nextInt(3);
		mAnswer[mCorrect]=int1+int2;
	}

	// getters

	public String getQuestion() {
		return mQuestion;
	}

	public int getAnswer(int i) {
		return mAnswer[i];
	}

	public int getCorrect() {
		return mCorrect;
	}
}
