package ch.ralena.quizapp.objects

class Quiz {
	var question: Question = Question()
	var totalTries: Int = 0
	var correctTries: Int = 0

	fun nextQuestion(): Boolean {
		question = Question()
		return totalTries < Constants.MAX_TRIES
	}

	fun checkAnswer(index: Int): Boolean {
		val correct = index == question.correctAnswerIndex
		totalTries++
		if (correct) correctTries++
		return correct
	}
}