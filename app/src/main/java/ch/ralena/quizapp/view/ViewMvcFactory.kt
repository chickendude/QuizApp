package ch.ralena.quizapp.view

import android.view.LayoutInflater
import ch.ralena.quizapp.activity.MainActivity
import ch.ralena.quizapp.activity.MainActivityView
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(
		private val layoutInflater: LayoutInflater
) {
	fun newMainActivityView() : MainActivityView {
		return MainActivityView(layoutInflater)
	}
}