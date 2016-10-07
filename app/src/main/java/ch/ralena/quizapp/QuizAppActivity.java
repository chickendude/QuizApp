package ch.ralena.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/*
	Icon courtesy of Android Asset Studio:
	https://romannurik.github.io/AndroidAssetStudio/
*/

public class QuizAppActivity extends AppCompatActivity {
	private static final String TAG = View.class.getSimpleName();
	// get textviews/button(s)
	private RelativeLayout quizLayout;
	private LinearLayout buttons;
	private TextView questionTextView;
	private Button startButton;

	// our quiz
	private Quiz quiz;
	// the buttons in our quiz
	List<Button> buttonList;
	// TextView to say how many questions we've answered correctly
	private TextView attemptsTextView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_app);

		// find our main layout
		quizLayout = (RelativeLayout) findViewById(R.id.quizLayout);
		buttons = (LinearLayout) findViewById(R.id.buttons);

		// create our quiz and the answer buttons
		quiz = new Quiz();
		buttonList = new ArrayList<>();

		// create a random question at the start
		String question;
		questionTextView = (TextView)findViewById(R.id.questionTextView);
		question = quiz.getQuestion();
		question += " ...?";
		questionTextView.setText(question);

		// attach listeners to button
		startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switchToQuiz();
			}
		});
	}

	private void switchToQuiz() {
		buttons.removeView(startButton);

		// create onclick listener for new buttons
		View.OnClickListener onClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String msg;
				if (quiz.checkAnswer((int) v.getTag())) {
					msg = "That's correct!";
				} else {
					msg = "Sorry, that wasn't correct";
				}
				Toast.makeText(QuizAppActivity.this, msg, Toast.LENGTH_SHORT).show();
				loadNewQuestion();
			}
		};

		attemptsTextView = (TextView) findViewById(R.id.scoreTextView);
		attemptsTextView.setText("Correct: 0 / Total: 0");
		attemptsTextView.setVisibility(View.VISIBLE);

		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT,
				1);
		for (int i = 0; i < 3; i++) {
			Button button = new Button(this);
			button.setTag(i);
			button.setLayoutParams(lp);
			button.setOnClickListener(onClickListener);
			buttonList.add(button);
		}
		for (Button button : buttonList) {
			buttons.addView(button);
		}
		loadNewQuestion();
	}

	public void loadNewQuestion() {
		quiz.nextQuestion();
		String question;
		questionTextView = (TextView)findViewById(R.id.questionTextView);
		question = quiz.getQuestion();
		question += " ...?";
		questionTextView.setText(question);
		for (int i = 0; i<buttonList.size(); i++) {
			Button button = buttonList.get(i);
			button.setText(quiz.getAnswer(i)+"");
		}
		String scoreTxt = String.format("Correct: %d / Total: %d",
				quiz.getCorrectTries(),
				quiz.getTotalTries());
		attemptsTextView.setText(scoreTxt);
	}
}
