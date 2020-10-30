package ch.ralena.quizapp.di.activity

import ch.ralena.quizapp.activity.QuizAppActivity
import dagger.Component

@Component(modules = [ActivityModule::class])
@ActivityScope
interface ActivityComponent {
	fun inject(activity: QuizAppActivity)
}