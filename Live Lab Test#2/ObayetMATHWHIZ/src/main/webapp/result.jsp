
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Math Whiz - Result</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">

    <h1>🏆 Game Completed!</h1>

    <h2>Congratulations, ${playerName}!</h2>

    <p><strong>Student ID:</strong> ${studentId}</p>

    <h2>Your Score</h2>

    <h1>${totalScore} / 10</h1>

    <p>Difficulty: <strong>${difficulty}</strong></p>

    <div class="question">

        <h3>Your Answer Results</h3>

        <p>
            Question 1 - Prime Number:
            <strong>${primeCorrect ? "Correct" : "Incorrect"}</strong>
        </p>

        <p>
            Question 2 - Modulus:
            <strong>${modCorrect ? "Correct" : "Incorrect"}</strong>
        </p>

        <p>
            Question 3 - Trigonometry:
            <strong>${trigCorrect1 ? "Correct" : "Incorrect"}</strong>
        </p>

        <p>
            Question 4 - Trigonometry:
            <strong>${trigCorrect2 ? "Correct" : "Incorrect"}</strong>
        </p>

    </div>

    <a href="${pageContext.request.contextPath}/index.jsp">
        <button type="button">Play Again</button>
    </a>

    <br><br>

    <a href="${pageContext.request.contextPath}/game?action=leaderboard">
        🏆 View Leaderboard
    </a>

</div>

</body>
</html>
