package ch.ralena.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*
	Icon courtesy of Android Asset Studio:
	https://romannurik.github.io/AndroidAssetStudio/

	Sound effects in the Public Domain, taken from:
	http://www.freesound.org/
*/

public class QuizAppActivity extends AppCompatActivity {
	private static final String TAG = View.class.getSimpleName();
	// get textviews/button(s)
	private TextView mQuestionTextView;
	private Button mStartButton;

	// our mQuiz
	private Quiz mQuiz;
	// the mButtons in our mQuiz
	List<RadioButton> mRadioButtonList;
	RadioGroup mRadioGroup;
	// TextView to say how many questions we've answered correctly
	private TextView mAttemptsTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_app);

		// create our mQuiz and the answer mButtons
		mQuiz = new Quiz();
		mRadioButtonList = new ArrayList<>();
		mRadioGroup = new RadioGroup(this);

		// create a random question at the start
		String question;
		mQuestionTextView = (TextView)findViewById(R.id.questionTextView);
		question = mQuiz.getQuestion();
		question += " ...?";
		mQuestionTextView.setText(question);

		// attach listeners to button
		mStartButton = (Button) findViewById(R.id.startButton);
		mStartButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(QuizAppActivity.this,QuizActivity.class);
				startActivity(intent);
			}
		});
	}

}