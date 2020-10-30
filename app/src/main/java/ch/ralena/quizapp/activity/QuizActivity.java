package ch.ralena.quizapp.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ch.ralena.quizapp.objects.Constants;
import ch.ralena.quizapp.objects.Quiz;
import ch.ralena.quizapp.R;

public class QuizActivity extends AppCompatActivity {
	private static final String TAG = QuizActivity.class.getSimpleName();

	// Views
	private TextView mQuestionsLeftTextView, mTotalQuestionsTextView,
			mCorrectTextView, mEquationTextView;
	private Button mSubmitButton;
	private RadioGroup mAnswersGroup;

	// Other Variables
	private Quiz mQuiz;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		loadViews();
		mQuiz = new Quiz();
		// set submit button's onclick listener
		mSubmitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String msg;
				int id = mAnswersGroup.getCheckedRadioButtonId();
				Log.d(TAG, "" + id);
				if(id != -1) {
					RadioButton radioButton = (RadioButton) mAnswersGroup.findViewById(id);
					if (mQuiz.checkAnswer((int) radioButton.getTag())) {
						msg = "That's correct!";
						MediaPlayer.create(getApplicationContext(), R.raw.right).start();
					} else {
						msg = "Sorry, that wasn't correct";
						MediaPlayer.create(getApplicationContext(), R.raw.wrong).start();
					}
					boolean questionsLeft = loadNewQuestion();
					if (!questionsLeft) {
						Toast.makeText(QuizActivity.this,
								String.format("You got %d out of %d correct.",
										mQuiz.getCorrectTries(),
										mQuiz.getTotalTries()),
								Toast.LENGTH_SHORT).show();
						finish();
					} else {
						Toast.makeText(QuizActivity.this, msg, Toast.LENGTH_SHORT).show();
						mAnswersGroup.clearCheck();
					}
				} else
					Toast.makeText(QuizActivity.this, "Please select an answer first!", Toast.LENGTH_SHORT).show();
			}
		});
		loadNewQuestion();
	}

	public boolean loadNewQuestion() {
		boolean areQuestionsLeft = mQuiz.nextQuestion();
		if (areQuestionsLeft) {
			String question;
			question = mQuiz.getQuestion().getText();
			question += " ...?";
			// update radio buttons
			for (int i = 0; i < mAnswersGroup.getChildCount(); i++) {
				RadioButton button = (RadioButton) mAnswersGroup.getChildAt(i);
				button.setText("" + mQuiz.getQuestion().getAnswer(i));
				button.setTag(i);
			}
			int total = mQuiz.getTotalTries();
			int correct = mQuiz.getCorrectTries();

			mEquationTextView.setText(question);
			mQuestionsLeftTextView.setText(""+(Constants.MAX_TRIES - total));
			mTotalQuestionsTextView.setText(""+total);
			mCorrectTextView.setText(""+correct);
		}
		return areQuestionsLeft;
	}

	private void loadViews() {
		mQuestionsLeftTextView = (TextView) findViewById(R.id.questionsLeftTextView);
		mTotalQuestionsTextView = (TextView) findViewById(R.id.totalQuestionsTextView);
		mCorrectTextView = (TextView) findViewById(R.id.correctTextView);
		mEquationTextView = (TextView) findViewById(R.id.equationTextView);
		mAnswersGroup = (RadioGroup) findViewById(R.id.answersGroup);
		mSubmitButton = (Button) findViewById(R.id.checkAnswerButton);
	}

}
