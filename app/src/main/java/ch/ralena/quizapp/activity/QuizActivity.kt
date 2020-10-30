package ch.ralena.quizapp.activity

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import ch.ralena.quizapp.R
import ch.ralena.quizapp.activity.QuizActivity
import ch.ralena.quizapp.objects.Constants
import ch.ralena.quizapp.objects.Quiz

class QuizActivity : BaseActivity() {
	// Views
	private var mQuestionsLeftTextView: TextView? = null
	private var mTotalQuestionsTextView: TextView? = null
	private var mCorrectTextView: TextView? = null
	private var mEquationTextView: TextView? = null
	private var mSubmitButton: Button? = null
	private var mAnswersGroup: RadioGroup? = null

	// Other Variables
	private var mQuiz: Quiz? = null
	override fun onCreate(savedInstanceState: Bundle?) {
		injector.inject(this)
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_quiz)
		loadViews()
		mQuiz = Quiz()
		// set submit button's onclick listener
		mSubmitButton!!.setOnClickListener {
			val msg: String
			val id = mAnswersGroup!!.checkedRadioButtonId
			Log.d(TAG, "" + id)
			if (id != -1) {
				val radioButton = mAnswersGroup!!.findViewById<View>(id) as RadioButton
				if (mQuiz!!.checkAnswer(radioButton.tag as Int)) {
					msg = "That's correct!"
					MediaPlayer.create(applicationContext, R.raw.right).start()
				} else {
					msg = "Sorry, that wasn't correct"
					MediaPlayer.create(applicationContext, R.raw.wrong).start()
				}
				val questionsLeft = loadNewQuestion()
				if (!questionsLeft) {
					Toast.makeText(this@QuizActivity, String.format("You got %d out of %d correct.",
							mQuiz!!.correctTries,
							mQuiz!!.totalTries),
							Toast.LENGTH_SHORT).show()
					finish()
				} else {
					Toast.makeText(this@QuizActivity, msg, Toast.LENGTH_SHORT).show()
					mAnswersGroup!!.clearCheck()
				}
			} else Toast.makeText(this@QuizActivity, "Please select an answer first!", Toast.LENGTH_SHORT).show()
		}
		loadNewQuestion()
	}

	fun loadNewQuestion(): Boolean {
		val areQuestionsLeft = mQuiz!!.nextQuestion()
		if (areQuestionsLeft) {
			var question: String
			question = mQuiz!!.question.text
			question += " ...?"
			// update radio buttons
			for (i in 0 until mAnswersGroup!!.childCount) {
				val button = mAnswersGroup!!.getChildAt(i) as RadioButton
				button.text = "" + mQuiz!!.question.getAnswer(i)
				button.tag = i
			}
			val total = mQuiz!!.totalTries
			val correct = mQuiz!!.correctTries
			mEquationTextView!!.text = question
			mQuestionsLeftTextView!!.text = "" + (Constants.MAX_TRIES - total)
			mTotalQuestionsTextView!!.text = "" + total
			mCorrectTextView!!.text = "" + correct
		}
		return areQuestionsLeft
	}

	private fun loadViews() {
		mQuestionsLeftTextView = findViewById<View>(R.id.questionsLeftTextView) as TextView
		mTotalQuestionsTextView = findViewById<View>(R.id.totalQuestionsTextView) as TextView
		mCorrectTextView = findViewById<View>(R.id.correctTextView) as TextView
		mEquationTextView = findViewById<View>(R.id.equationTextView) as TextView
		mAnswersGroup = findViewById<View>(R.id.answersGroup) as RadioGroup
		mSubmitButton = findViewById<View>(R.id.checkAnswerButton) as Button
	}

	companion object {
		private val TAG = QuizActivity::class.java.simpleName
	}
}