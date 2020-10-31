package ch.ralena.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import ch.ralena.quizapp.activity.QuizActivity
import javax.inject.Inject

class ScreensNavigator @Inject constructor(private val activity: AppCompatActivity) {
	fun navigateBack() {
		activity.onBackPressed()
	}

	fun toQuizActivity() {
		val intent = Intent(activity, QuizActivity::class.java)
		activity.startActivity(intent)
	}

}