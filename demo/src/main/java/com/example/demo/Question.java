package com.example.demo;

public class Question {

    private String questionText;
    private String correctAnswer;

    // Constructor
    public Question(String questionText, String correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    // Getters and Setters
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    // Method to check if the provided answer is correct
    public boolean isCorrectAnswer(String answer) {
        return this.correctAnswer.equalsIgnoreCase(answer);
    }
}
