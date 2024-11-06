package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QuizRestController {

    // Endpoint to check password strength
    @PostMapping("/check-password")
    public String checkPasswordStrength(@RequestParam String password) {
        if (password.length() < 8) {
            return "Password is too short. Minimum length is 8 characters.";
        }
        if (!password.matches(".*[A-Za-z].*")) {
            return "Password must contain at least one letter.";
        }
        if (!password.matches(".*[0-9].*")) {
            return "Password must contain at least one number.";
        }
        return "Password is strong.";
    }

    // Endpoint to validate email
    @PostMapping("/validate-email")
    public String validateEmail(@RequestParam String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex) ? "Email is valid." : "Invalid email address.";
    }

    // Endpoint to get the next quiz question in sequence
    @GetMapping("/next-quiz-question")
    public Question getNextQuizQuestion() {
        return Quiz.getNextQuestion();
    }

    // Endpoint to get a random quiz question
    @GetMapping("/random-quiz-question")
    public Question getRandomQuizQuestion() {
        return Quiz.getRandomQuestion();
    }

    // Endpoint to check if the provided answer is correct for a question
    @PostMapping("/check-answer")
    public String checkAnswer(@RequestParam String questionText, @RequestParam String answer) {
        for (Question question : Quiz.getQuestions()) {
            if (question.getQuestionText().equals(questionText)) {
                if (question.isCorrectAnswer(answer)) {
                    return "Correct!";
                } else {
                    return "Incorrect. The correct answer is: " + question.getCorrectAnswer();
                }
            }
        }
        return "Question not found.";
    }
}