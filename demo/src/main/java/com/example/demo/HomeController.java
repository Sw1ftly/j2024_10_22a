package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {

    @Value("${quiz.total-questions}")
    private int totalQuestions;

    // Show login page
    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    // Handle login POST request
    @PostMapping("/login")
    public String login(String email, String password, HttpSession session) {
        if (isEmailValid(email) && isPasswordValid(password)) {
            session.setAttribute("score", 0); // Initialize score
            session.setAttribute("questionIndex", 0); // Start from first question
            session.setAttribute("email", email); // Store email in session
            return "redirect:/quiz"; // Redirect to quiz page
        }
        return "badPassword"; // Show bad password page if login fails
    }

    // Show quiz page with current question and score
    @GetMapping("/quiz")
    public String showQuizPage(HttpSession session, Model model) {
        Integer questionIndex = (Integer) session.getAttribute("questionIndex");
        Integer currentScore = (Integer) session.getAttribute("score");

        // Handle session attribute initialization if null
        if (questionIndex == null) questionIndex = 0;
        if (currentScore == null) currentScore = 0;

        if (questionIndex >= totalQuestions) {
            return "redirect:/quizComplete"; // Redirect to quiz completion page if all questions answered
        }

        // Get the current sequential question based on questionIndex
        Question currentQuestion = Quiz.getQuestions().get(questionIndex);
        model.addAttribute("question", currentQuestion);
        model.addAttribute("score", currentScore); // Display score
        model.addAttribute("questionIndex", questionIndex + 1); // Display current question number
        model.addAttribute("totalQuestions", totalQuestions); // Display total number of questions

        return "quiz";
    }

    // Handle quiz answer submission
    @PostMapping("/quiz")
    public String answerQuiz(String answer, HttpSession session) {
        Integer questionIndex = (Integer) session.getAttribute("questionIndex");
        Integer currentScore = (Integer) session.getAttribute("score");

        // Handle session attribute initialization if null
        if (questionIndex == null) questionIndex = 0;
        if (currentScore == null) currentScore = 0;

        // Get the current sequential question based on questionIndex
        Question currentQuestion = Quiz.getQuestions().get(questionIndex);

        // Check if the answer is correct and update the score
        if (currentQuestion.isCorrectAnswer(answer)) {
            session.setAttribute("score", currentScore + 1); // Increase score
        }

        session.setAttribute("questionIndex", questionIndex + 1); // Move to next question

        return "redirect:/quiz"; // Redirect to quiz page for next question
    }

    // Show quiz completion page
    @GetMapping("/quizComplete")
    public String showQuizCompletePage(HttpSession session, Model model) {
        Integer score = (Integer) session.getAttribute("score");
        if (score == null) score = 0;
        model.addAttribute("score", score);
        return "quizComplete"; // Show final score page
    }

    // Helper methods for email and password validation
    private boolean isEmailValid(String email) {
        return email != null && email.contains("@"); // Basic email validation
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.length() >= 6; // Basic password validation
    }
}
