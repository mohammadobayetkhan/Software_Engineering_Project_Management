
package com.obayet.mathwhiz.controller;

import com.obayet.mathwhiz.dao.ScoreDAO;
import com.obayet.mathwhiz.model.GameScore;
import com.obayet.mathwhiz.service.MathService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private final Random random = new Random();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("leaderboard".equals(action)) {

            ScoreDAO scoreDAO = new ScoreDAO();

            request.setAttribute("scores",
                    scoreDAO.getTop10Scores());

            request.getRequestDispatcher("/leaderboard.jsp")
                    .forward(request, response);

        } else {

            response.sendRedirect(
                    request.getContextPath() + "/index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("start".equals(action)) {

            startGame(request, response);

        } else if ("getOptions".equals(action)) {

            getOptions(request, response);

        } else if ("submit".equals(action)) {

            submitGame(request, response);

        } else {

            response.sendRedirect(
                    request.getContextPath() + "/index.jsp");
        }
    }

    // Step 1: Receive Student ID and Name
    private void startGame(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String studentId = request.getParameter("studentId");
        String playerName = request.getParameter("playerName");

        if (studentId == null || studentId.trim().isEmpty()
                || playerName == null || playerName.trim().isEmpty()) {

            request.setAttribute("error",
                    "Please enter both Student ID and Player Name.");

            request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);

            return;
        }

        HttpSession session = request.getSession();

        session.setAttribute("studentId", studentId.trim());
        session.setAttribute("playerName", playerName.trim());

        response.sendRedirect(
                request.getContextPath() + "/prime-input.jsp");
    }

    // Step 2: Receive integer and generate three options
    private void getOptions(HttpServletRequest request,
                            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null
                || session.getAttribute("playerName") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/index.jsp");

            return;
        }

        try {

            int primeNumber = Integer.parseInt(
                    request.getParameter("integer").trim());

            // Store the user's integer
            session.setAttribute("primeNumber", primeNumber);

            // Generate three options: 1, 0 and a random number
            List<Integer> options = new ArrayList<>();

            options.add(1);
            options.add(0);
            options.add(random.nextInt(4) + 2);

            // Shuffle options so their positions are random
            Collections.shuffle(options);

            session.setAttribute("primeOptions", options);

            // Generate the remaining three questions
            generateOtherQuestions(session);

            // Send all questions to game.jsp
            setGameAttributes(request, session);

            request.getRequestDispatcher("/game.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {

            request.setAttribute("error",
                    "Please enter a valid integer.");

            request.getRequestDispatcher("/prime-input.jsp")
                    .forward(request, response);
        }
    }

    // Generate Modulus and Trigonometry questions
    private void generateOtherQuestions(HttpSession session) {

        int a = random.nextInt(41) + 10;
        int b = random.nextInt(8) + 2;

        String[] operations = {"SIN", "COS", "TAN"};
        int[] degrees = {30, 45, 60};

        String trig1 = operations[random.nextInt(3)];
        int degree1 = degrees[random.nextInt(3)];

        String trig2 = operations[random.nextInt(3)];
        int degree2 = degrees[random.nextInt(3)];

        session.setAttribute("modA", a);
        session.setAttribute("modB", b);

        session.setAttribute("trig1", trig1);
        session.setAttribute("degree1", degree1);

        session.setAttribute("trig2", trig2);
        session.setAttribute("degree2", degree2);
    }

    // Set question information for game.jsp
    private void setGameAttributes(HttpServletRequest request,
                                   HttpSession session) {

        request.setAttribute("primeNumber",
                session.getAttribute("primeNumber"));

        request.setAttribute("primeOptions",
                session.getAttribute("primeOptions"));

        request.setAttribute("modA",
                session.getAttribute("modA"));

        request.setAttribute("modB",
                session.getAttribute("modB"));

        request.setAttribute("trig1",
                session.getAttribute("trig1"));

        request.setAttribute("degree1",
                session.getAttribute("degree1"));

        request.setAttribute("trig2",
                session.getAttribute("trig2"));

        request.setAttribute("degree2",
                session.getAttribute("degree2"));
    }

    // Step 3: Check answers and save score
    private void submitGame(HttpServletRequest request,
                            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null
                || session.getAttribute("playerName") == null
                || session.getAttribute("primeNumber") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/index.jsp");

            return;
        }

        try {

            String studentId =
                    (String) session.getAttribute("studentId");

            String playerName =
                    (String) session.getAttribute("playerName");

            int primeNumber =
                    (int) session.getAttribute("primeNumber");

            int a = (int) session.getAttribute("modA");
            int b = (int) session.getAttribute("modB");

            String trig1 = (String) session.getAttribute("trig1");
            int degree1 = (int) session.getAttribute("degree1");

            String trig2 = (String) session.getAttribute("trig2");
            int degree2 = (int) session.getAttribute("degree2");

            int totalScore = 0;

            // Question 1: Prime Number (2 marks)
            int primeAnswer = Integer.parseInt(
                    request.getParameter("primeAnswer").trim());

            boolean primeCorrect =
                    primeAnswer == MathService.isPrime(primeNumber);

            if (primeCorrect) {
                totalScore += 2;
            }

            // Question 2: Modulus (2 marks)
            int modAnswer = Integer.parseInt(
                    request.getParameter("modAnswer").trim());

            boolean modCorrect =
                    modAnswer == MathService.calculateMod(a, b);

            if (modCorrect) {
                totalScore += 2;
            }

            // Question 3: Trigonometry (3 marks)
            double trigAnswer1 = Double.parseDouble(
                    request.getParameter("trigAnswer1").trim());

            double correctTrig1 = calculateTrig(trig1, degree1);

            boolean trigCorrect1 =
                    Math.abs(trigAnswer1 - correctTrig1) < 0.01;

            if (trigCorrect1) {
                totalScore += 3;
            }

            // Question 4: Trigonometry (3 marks)
            double trigAnswer2 = Double.parseDouble(
                    request.getParameter("trigAnswer2").trim());

            double correctTrig2 = calculateTrig(trig2, degree2);

            boolean trigCorrect2 =
                    Math.abs(trigAnswer2 - correctTrig2) < 0.01;

            if (trigCorrect2) {
                totalScore += 3;
            }

            // Save score in database
            GameScore gameScore = new GameScore(
                    studentId, playerName, totalScore, "NORMAL");

            ScoreDAO scoreDAO = new ScoreDAO();

            boolean saved = scoreDAO.saveScore(gameScore);

            if (!saved) {
                throw new ServletException(
                        "Could not save score to database.");
            }

            // Send result information
            request.setAttribute("studentId", studentId);
            request.setAttribute("playerName", playerName);
            request.setAttribute("totalScore", totalScore);

            request.setAttribute("primeCorrect", primeCorrect);
            request.setAttribute("modCorrect", modCorrect);
            request.setAttribute("trigCorrect1", trigCorrect1);
            request.setAttribute("trigCorrect2", trigCorrect2);

            request.setAttribute("difficulty", "NORMAL");

            // Clear game information
            session.invalidate();

            request.getRequestDispatcher("/result.jsp")
                    .forward(request, response);

        } catch (NumberFormatException | NullPointerException e) {

            request.setAttribute("error",
                    "Please provide valid answers.");

            setGameAttributes(request, session);

            request.getRequestDispatcher("/game.jsp")
                    .forward(request, response);
        }
    }

    private double calculateTrig(String operation, int degree) {

        switch (operation) {

            case "SIN":
                return MathService.calculateSin(degree);

            case "COS":
                return MathService.calculateCos(degree);

            case "TAN":
                return MathService.calculateTan(degree);

            default:
                throw new IllegalArgumentException(
                        "Invalid trigonometric operation.");
        }
    }
}
