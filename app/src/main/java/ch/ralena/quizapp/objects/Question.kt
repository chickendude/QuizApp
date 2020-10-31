package ch.ralena.quizapp.objects

import java.util.*
import kotlin.math.min

class Question {
	// getters
	val text: String
	val correctAnswerIndex: Int

	private val answers = IntArray(3)

	fun getAnswer(i: Int): Int {
		return answers[i]
	}

	companion object {
		private val rand = Random()
	}

	init {
		// get the numbers to add together
		val correctAnswer = rand.nextInt(100) + 1
		val int1 = rand.nextInt(correctAnswer)
		val int2 = correctAnswer - int1

		// create the question string
		text = "$int1 + $int2 = ...?"
		var min = min (int1, int2)
		if (min > 97) min = 97

		correctAnswerIndex = rand.nextInt(3)
		for (i in answers.indices) {
			if (i == correctAnswerIndex)
				answers[i] = correctAnswer
			else {
				var value: Int
				do {
					value = rand.nextInt(100 - min) + min + 1
				} while (answers.contains(value))
				answers[i] = value
			}
		}
	}
}