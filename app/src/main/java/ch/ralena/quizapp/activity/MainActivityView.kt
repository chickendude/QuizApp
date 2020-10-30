package ch.ralena.quizapp.activity

import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import ch.ralena.quizapp.R
import ch.ralena.quizapp.view.BaseViewMvc

class MainActivityView(
		layoutInflater: LayoutInflater
) : BaseViewMvc<MainActivityView.Listener>(
		layoutInflater,
		R.layout.activity_main
) {
	interface Listener {
		fun onStartButtonClicked()
	}

	private val questionTextView: TextView = findViewById(R.id.questionTextView)
	private val startButton: Button = findViewById(R.id.startButton)

	init {
		startButton.setOnClickListener {
			for (listener in listeners) {
				listener.onStartButtonClicked()
			}
		}
	}

	fun loadQuestionText(text: String) {
		questionTextView.text = text
	}
}