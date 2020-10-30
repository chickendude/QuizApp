package ch.ralena.quizapp.di.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {
	@Provides
	fun activity() = activity

	companion object {
		@Provides
		fun layoutInflater(activity: AppCompatActivity): LayoutInflater = LayoutInflater.from(activity)
	}
}