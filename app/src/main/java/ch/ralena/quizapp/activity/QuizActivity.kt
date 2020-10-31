package ch.ralena.quizapp.activity

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import ch.ralena.quizapp.R
import ch.ralena.quizapp.objects.Quiz
import ch.ralena.quizapp.view.ViewMvcFactory
import javax.inject.Inject

class QuizActivity : BaseActivity(), QuizActivityView.Listener {
	@Inject lateinit var viewMvcFactory : ViewMvcFactory
	private lateinit var viewMvc : QuizActivityView
	private lateinit var mQuiz: Quiz

	override fun onCreate(savedInstanceState: Bundle?) {
		injector.inject(this)
		super.onCreate(savedInstanceState)

		viewMvc = viewMvcFactory.newQuizActivityView()
		viewMvc.registerListener(this)
		setContentView(viewMvc.rootView)

		mQuiz = Quiz()
		// set submit button's onclick listener
		loadNewQuestion()
	}

	private fun loadNewQuestion(): Boolean {
		val areQuestionsLeft = mQuiz.nextQuestion()
		if (areQuestionsLeft) {
			var question: String
			question = mQuiz.question.text
			question += " ...?"
			// update radio buttons
			viewMvc.updateQuizQuestions(mQuiz)
		}
		return areQuestionsLeft
	}

	companion object {
		private val TAG = QuizActivity::class.java.simpleName
	}

	override fun onSubmitButtonClick(checkedRadioButton: RadioButton?) {
		if (checkedRadioButton != null) {
			val msg: String
			if (mQuiz.checkAnswer(checkedRadioButton.tag as Int)) {
				msg = "That's correct!"
				MediaPlayer.create(applicationContext, R.raw.right).start()
			} else {
				msg = "Sorry, that wasn't correct"
				MediaPlayer.create(applicationContext, R.raw.wrong).start()
			}
			val questionsLeft = loadNewQuestion()
			if (!questionsLeft) {
				Toast.makeText(this@QuizActivity, String.format("You got %d out of %d correct.",
						mQuiz.correctTries,
						mQuiz.totalTries),
						Toast.LENGTH_SHORT).show()
				finish()
			} else {
				Toast.makeText(this@QuizActivity, msg, Toast.LENGTH_SHORT).show()
				viewMvc.clearCheckedButtons()
			}
		} else Toast.makeText(this@QuizActivity, "Please select an answer first!", Toast.LENGTH_SHORT).show()	}
}