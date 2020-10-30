package ch.ralena.quizapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import ch.ralena.quizapp.objects.Quiz
import ch.ralena.quizapp.view.ViewMvcFactory
import javax.inject.Inject

/*
   Icon courtesy of Android Asset Studio:
   https://romannurik.github.io/AndroidAssetStudio/

   Sound effects in the Public Domain, taken from:
   http://www.freesound.org/
*/
class MainActivity : BaseActivity(), MainActivityView.Listener {

	@Inject	lateinit var viewMvcFactory: ViewMvcFactory
	private lateinit var viewMvc: MainActivityView
	private lateinit var quiz: Quiz

	override fun onCreate(savedInstanceState: Bundle?) {
		injector.inject(this)
		super.onCreate(savedInstanceState)
		viewMvc = viewMvcFactory.newMainActivityView()
		setContentView(viewMvc.rootView)

		// create our mQuiz and the answer mButtons
		quiz = Quiz()

		// create a random question at the start
		var question = quiz.question.text
		question += " ...?"
		viewMvc.loadQuestionText(question)
	}

	override fun onStart() {
		super.onStart()
		viewMvc.registerListener(this)
	}

	override fun onStop() {
		super.onStop()
		viewMvc.unregisterListener(this)
	}

	override fun onStartButtonClicked() {
		val intent = Intent(this, QuizActivity::class.java)
		startActivity(intent)
	}

	companion object {
		private val TAG = View::class.java.simpleName
	}
}