package ch.ralena.quizapp.objects;

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

		int min = int1 > int2 ? int1 : int2;
		if (min > 97)
			min = 97;

		// fill the mAnswer box with random data
		for (int i = 0; i < mAnswer.length; i++) {
			int value;
			do {
				value = rand.nextInt(100-min) + min + 1;
			} while (Arrays.asList(mAnswer).contains(value));
			mAnswer[i] = value;
		}

		// make sure the mCorrect mAnswer is actually mCorrect
		mCorrect=rand.nextInt(3);
		mAnswer[mCorrect]=int1+int2;
	}

	// getters

	public String getText() {
		return mQuestion;
	}

	public int getAnswer(int i) {
		return mAnswer[i];
	}

	public int getCorrect() {
		return mCorrect;
	}
}
