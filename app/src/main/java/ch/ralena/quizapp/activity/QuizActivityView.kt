package ch.ralena.quizapp.activity

import android.view.LayoutInflater
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import ch.ralena.quizapp.R
import ch.ralena.quizapp.objects.Constants
import ch.ralena.quizapp.objects.Quiz
import ch.ralena.quizapp.view.BaseViewMvc

class QuizActivityView(
		layoutInflater: LayoutInflater
) : BaseViewMvc<QuizActivityView.Listener>(
		layoutInflater,
		R.layout.activity_quiz
) {
	interface Listener {
		fun onSubmitButtonClick(checkedRadioButton: RadioButton?)
	}

	private val questionsLeftTextView: TextView = findViewById(R.id.questionsLeftTextView)
	private val totalQuestionsTextView: TextView = findViewById(R.id.totalQuestionsTextView)
	private val correctTextView: TextView = findViewById(R.id.correctTextView)
	private val equationTextView: TextView = findViewById(R.id.equationTextView)
	private val submitButton: Button = findViewById(R.id.checkAnswerButton)
	private val answersGroup: RadioGroup = findViewById(R.id.answersGroup)

	init {
		submitButton.setOnClickListener {
			for (listener in listeners) {
				val radioButton: RadioButton = findViewById(answersGroup.checkedRadioButtonId)
				listener.onSubmitButtonClick(radioButton)
			}
		}
	}

	fun updateQuizQuestions(quiz: Quiz) {
		for (i in 0 until answersGroup.childCount) {
			val button = answersGroup.getChildAt(i) as RadioButton
			button.text = "${quiz.question.getAnswer(i)}"
			button.tag = i
		}


		val question = quiz.question
		equationTextView.text = question.text

		val questionsLeft = Constants.MAX_TRIES - quiz.totalTries
		questionsLeftTextView.text = "${questionsLeft}"
		totalQuestionsTextView.text = "${quiz.totalTries}"
		correctTextView.text = "${quiz.correctTries}"
	}

	fun clearCheckedButtons() {
		answersGroup.clearCheck()
	}
}