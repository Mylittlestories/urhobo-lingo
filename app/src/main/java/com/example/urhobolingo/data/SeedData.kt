package com.example.urhobolingo.data

import com.example.urhobolingo.model.Achievement
import com.example.urhobolingo.model.DailyChallenge
import com.example.urhobolingo.model.Lesson
import com.example.urhobolingo.model.QuizQuestion
import com.example.urhobolingo.model.WordPair

object SeedData {
    val lessons = listOf(
        Lesson(
            id = "basics_1",
            title = "Greetings",
            description = "Learn simple everyday greetings in Urhobo.",
            xpReward = 20,
            difficulty = "Beginner",
            words = listOf(
                WordPair("Hello", "Mighwo", "Greetings"),
                WordPair("Good morning", "Mighwo r'oyibo", "Greetings"),
                WordPair("How are you?", "Oghene vwo?", "Greetings"),
                WordPair("I am fine", "Mẹ vwẹ ọma", "Greetings")
            ),
            quizQuestions = listOf(
                QuizQuestion("q1", "What is 'Hello' in Urhobo?", listOf("Mighwo", "Ose", "Avwan", "Mema"), "Mighwo", "Mighwo is used as a greeting similar to hello."),
                QuizQuestion("q2", "Translate 'Good morning'", listOf("Mighwo r'oyibo", "Mighwo", "Oghene vwo?", "Mẹ vwẹ ọma"), "Mighwo r'oyibo", "This is a seed translation for morning greeting."),
                QuizQuestion("q3", "What means 'How are you?'", listOf("Mẹ vwẹ ọma", "Oghene vwo?", "Ame", "Le"), "Oghene vwo?", "This asks about wellbeing.")
            )
        ),
        Lesson(
            id = "basics_2",
            title = "Family",
            description = "Learn basic family words.",
            xpReward = 25,
            difficulty = "Beginner",
            words = listOf(
                WordPair("Father", "Baba", "Family"),
                WordPair("Mother", "Yẹ", "Family"),
                WordPair("Child", "Omote", "Family"),
                WordPair("Family", "Emọ", "Family")
            ),
            quizQuestions = listOf(
                QuizQuestion("q4", "What does 'Omote' mean?", listOf("Mother", "Child", "Family", "Friend"), "Child", "Omote means child in this seed content."),
                QuizQuestion("q5", "Translate 'Mother'", listOf("Yẹ", "Baba", "Emọ", "Ọkpọ"), "Yẹ", "Yẹ corresponds to mother in this dataset.")
            )
        ),
        Lesson(
            id = "basics_3",
            title = "Food & Daily Life",
            description = "Useful words for food and common items.",
            xpReward = 30,
            difficulty = "Beginner",
            words = listOf(
                WordPair("Water", "Ame", "Daily Life"),
                WordPair("Food", "Emu", "Daily Life"),
                WordPair("Come", "Ruo", "Daily Life"),
                WordPair("Eat", "Le", "Daily Life")
            ),
            quizQuestions = listOf(
                QuizQuestion("q6", "Translate 'Water'", listOf("Ame", "Emu", "Le", "Ruo"), "Ame", "Ame means water."),
                QuizQuestion("q7", "Which word means 'Eat'?", listOf("Le", "Ame", "Ruo", "Baba"), "Le", "Le is the matching word for eat.")
            )
        )
    )

    val achievements = listOf(
        Achievement("first_lesson", "First Step", "Complete your first lesson"),
        Achievement("streak_3", "On Fire", "Reach a 3-day streak"),
        Achievement("translator_10", "Translator", "Use the translator 10 times"),
        Achievement("xp_100", "Century XP", "Earn 100 XP")
    )

    val dailyChallenges = listOf(
        DailyChallenge("dc1", "Practice Greetings", "Finish the Greetings lesson", 15),
        DailyChallenge("dc2", "Translate 5 Words", "Use the translator for five terms", 10),
        DailyChallenge("dc3", "Review Vocabulary", "Study 8 flashcards today", 12)
    )

    val dictionary = listOf(
        WordPair("Hello", "Mighwo", "Greetings"),
        WordPair("Good morning", "Mighwo r'oyibo", "Greetings"),
        WordPair("How are you?", "Oghene vwo?", "Greetings"),
        WordPair("I am fine", "Mẹ vwẹ ọma", "Greetings"),
        WordPair("Father", "Baba", "Family"),
        WordPair("Mother", "Yẹ", "Family"),
        WordPair("Child", "Omote", "Family"),
        WordPair("Family", "Emọ", "Family"),
        WordPair("Water", "Ame", "Daily Life"),
        WordPair("Food", "Emu", "Daily Life"),
        WordPair("Come", "Ruo", "Daily Life"),
        WordPair("Eat", "Le", "Daily Life")
    )
}
