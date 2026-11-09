
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>

<%
    List<Integer> primeOptions =
            (List<Integer>) request.getAttribute("primeOptions");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Math Whiz - Game</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">

    <h1>🧠 MATH WHIZ</h1>

    <h2>Good Luck, ${sessionScope.playerName}!</h2>

    <p><strong>Student ID:</strong> ${sessionScope.studentId}</p>

    <p>Difficulty: <strong>NORMAL</strong></p>
    <p>Answer all four questions carefully.</p>

    <p class="error">${error}</p>

    <form action="${pageContext.request.contextPath}/game"
          method="post">

        <input type="hidden" name="action" value="submit">

        <!-- Question 1: Prime Number -->
        <div class="question">

            <h3>Question 1: Prime Number (2 Marks)</h3>

            <p>
                Is <strong>${primeNumber}</strong> a Prime Number?
            </p>

            <p>Select the correct answer:</p>

            <%
                if (primeOptions != null) {
                    for (Integer option : primeOptions) {
            %>

            <label>
                <input type="radio"
                       name="primeAnswer"
                       value="<%= option %>"
                       required>
                <%= option %>
            </label>

            <%
                    }
                }
            %>

        </div>

        <!-- Question 2: Modulus -->
        <div class="question">

            <h3>Question 2: Modulus (2 Marks)</h3>

            <p>Calculate: ${modA} % ${modB}</p>

            <input type="number"
                   name="modAnswer"
                   placeholder="Enter your answer"
                   required>

        </div>

        <!-- Question 3: Trigonometry -->
        <div class="question">

            <h3>Question 3: Trigonometry (3 Marks)</h3>

            <p>Calculate: ${trig1}(${degree1}°)</p>

            <input type="number"
                   name="trigAnswer1"
                   step="any"
                   placeholder="Enter decimal answer"
                   required>

        </div>

        <!-- Question 4: Trigonometry -->
        <div class="question">

            <h3>Question 4: Trigonometry (3 Marks)</h3>

            <p>Calculate: ${trig2}(${degree2}°)</p>

            <input type="number"
                   name="trigAnswer2"
                   step="any"
                   placeholder="Enter decimal answer"
                   required>

        </div>

        <button type="submit">Submit Answers</button>

    </form>

</div>

</body>
</html>
