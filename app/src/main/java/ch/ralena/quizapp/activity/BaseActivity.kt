package ch.ralena.quizapp.activity

import androidx.appcompat.app.AppCompatActivity
import ch.ralena.quizapp.di.activity.ActivityModule
import ch.ralena.quizapp.di.activity.DaggerActivityComponent

open class BaseActivity : AppCompatActivity() {

	val injector get() = DaggerActivityComponent.builder()
			.activityModule(ActivityModule(this))
			.build()
}