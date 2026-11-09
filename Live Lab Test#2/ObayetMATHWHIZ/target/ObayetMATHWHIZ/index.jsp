
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Obayet - MATH WHIZ</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">

    <h1>🧠 MATH WHIZ</h1>

    <h2>Test Your Mathematical Skills!</h2>

    <p>
        Welcome to Math Whiz! Answer four exciting math questions
        and test your knowledge.
    </p>

    <form action="${pageContext.request.contextPath}/game" method="post">

        <input type="hidden" name="action" value="start">

        <label for="studentId">Enter Your Student ID:</label>

        <input type="text"
               id="studentId"
               name="studentId"
               placeholder="Enter your Student ID"
               maxlength="50"
               required>

        <label for="playerName">Enter Your Name:</label>

        <input type="text"
               id="playerName"
               name="playerName"
               placeholder="Enter your name"
               maxlength="100"
               required>

        <button type="submit">Start Game</button>

    </form>

    <p class="error">${error}</p>

    <a href="${pageContext.request.contextPath}/game?action=leaderboard">
        🏆 View Leaderboard
    </a>

</div>

</body>
</html>
