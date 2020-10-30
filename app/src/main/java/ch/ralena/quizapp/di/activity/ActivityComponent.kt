package ch.ralena.quizapp.di.activity

import ch.ralena.quizapp.activity.MainActivity
import ch.ralena.quizapp.activity.QuizActivity
import dagger.Component

@Component(modules = [ActivityModule::class])
@ActivityScope
interface ActivityComponent {
	fun inject(activity: MainActivity)
	fun inject(activity: QuizActivity)
}