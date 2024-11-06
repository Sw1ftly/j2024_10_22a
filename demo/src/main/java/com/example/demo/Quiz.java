package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quiz {

    private static final List<Question> questions = new ArrayList<>();
    private static int currentQuestionIndex = 0;

    static {
        // Add questions to the list
        questions.add(new Question("The Earth is flat.", "False"));
        questions.add(new Question("The sun rises in the east.", "True"));
        questions.add(new Question("Water is a liquid at room temperature.", "True"));
        questions.add(new Question("The sky is blue.", "True"));
        questions.add(new Question("Humans can breathe underwater without any equipment.", "False"));
        questions.add(new Question("Mount Everest is the tallest mountain in the world.", "True"));
        questions.add(new Question("The capital of France is Rome.", "False"));
        questions.add(new Question("Bats are mammals.", "True"));
        questions.add(new Question("The moon is made of cheese.", "False"));
        questions.add(new Question("The human body has 206 bones.", "True"));
    }

    // Get the next question in sequence
    public static Question getNextQuestion() {
        if (currentQuestionIndex >= questions.size()) {
            currentQuestionIndex = 0;  // Reset back to the first question when all are answered
        }
        return questions.get(currentQuestionIndex++);
    }

    // Get a random question from the list
    public static Question getRandomQuestion() {
        Random random = new Random();
        return questions.get(random.nextInt(questions.size()));
    }

    // Reset the quiz back to the first question
    public static void resetQuiz() {
        currentQuestionIndex = 0;
    }

    // Get all the questions (for displaying or other purposes)
    public static List<Question> getQuestions() {
        return questions;
    }
}