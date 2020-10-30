package ch.ralena.quizapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ch.ralena.quizapp.objects.Quiz;
import ch.ralena.quizapp.R;
import ch.ralena.quizapp.view.ViewMvcFactory;

/*
	Icon courtesy of Android Asset Studio:
	https://romannurik.github.io/AndroidAssetStudio/

	Sound effects in the Public Domain, taken from:
	http://www.freesound.org/
*/

public class MainActivity extends BaseActivity implements MainActivityView.Listener {
	private static final String TAG = View.class.getSimpleName();

	@Inject ViewMvcFactory viewMvcFactory;
	private MainActivityView viewMvc;
	private Quiz quiz;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getInjector().inject(this);
		super.onCreate(savedInstanceState);
		viewMvc = viewMvcFactory.newMainActivityView();
		setContentView(viewMvc.getRootView());

		viewMvc.registerListener(this);

		// create our mQuiz and the answer mButtons
		quiz = new Quiz();

		// create a random question at the start
		String question;
		question = quiz.getQuestion();
		question += " ...?";
		viewMvc.loadQuestionText(question);
	}

	@Override
	public void onStartButtonClicked() {
		Intent intent = new Intent(MainActivity.this, QuizActivity.class);
		startActivity(intent);
	}
}