package ch.ralena.quizapp.activity

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.*
import ch.ralena.quizapp.R
import ch.ralena.quizapp.objects.Quiz
import ch.ralena.quizapp.view.ViewMvcFactory
import javax.inject.Inject

class QuizActivity : BaseActivity(), QuizActivityView.Listener {
	@Inject	lateinit var viewMvcFactory: ViewMvcFactory
	private lateinit var viewMvc: QuizActivityView
	private lateinit var quiz: Quiz

	override fun onCreate(savedInstanceState: Bundle?) {
		injector.inject(this)
		super.onCreate(savedInstanceState)

		viewMvc = viewMvcFactory.newQuizActivityView()
		viewMvc.registerListener(this)
		setContentView(viewMvc.rootView)

		quiz = Quiz()
		quiz.nextQuestion()
	}

	companion object {
		private val TAG = QuizActivity::class.java.simpleName
	}

	override fun onSubmitButtonClick(checkedRadioButton: RadioButton?) {
		if (checkedRadioButton != null) {
			val msg: String
			if (quiz.checkAnswer(checkedRadioButton.tag as Int)) {
				msg = "That's correct!"
				MediaPlayer.create(this, R.raw.right).start()
			} else {
				msg = "Sorry, that wasn't correct"
				MediaPlayer.create(applicationContext, R.raw.wrong).start()
			}

			if (!quiz.nextQuestion()) {
				Toast.makeText(this,
						"You got ${quiz.correctTries} out of ${quiz.totalTries} correct.",
						Toast.LENGTH_SHORT).show()
				finish()
			} else {
				Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
				viewMvc.clearCheckedButtons()
			}
		} else Toast.makeText(this,
				"Please select an answer first!",
				Toast.LENGTH_SHORT).show()
	}
}