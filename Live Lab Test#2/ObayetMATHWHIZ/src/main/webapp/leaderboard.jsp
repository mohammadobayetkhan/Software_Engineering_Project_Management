
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.obayet.mathwhiz.model.GameScore" %>

<%!
    private String escapeHtml(String value) {
        if (value == null) return "";

        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
%>

<%
    List<GameScore> scores =
            (List<GameScore>) request.getAttribute("scores");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Math Whiz - Leaderboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">

    <h1>🏆 LEADERBOARD</h1>
    <h2>Top 10 Players</h2>

    <table>
        <thead>
        <tr>
            <th>Rank</th>
            <th>Student ID</th>
            <th>Player Name</th>
            <th>Score</th>
            <th>Difficulty</th>
            <th>Played At</th>
        </tr>
        </thead>

        <tbody>
        <%
            if (scores != null && !scores.isEmpty()) {
                int rank = 1;

                for (GameScore score : scores) {
        %>
        <tr>
            <td><%= rank++ %></td>
            <td><%= escapeHtml(score.getStudentId()) %></td>
            <td><%= escapeHtml(score.getPlayerName()) %></td>
            <td><%= score.getScore() %> / 10</td>
            <td><%= escapeHtml(score.getDifficulty()) %></td>
            <td><%= score.getPlayedAt() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">
                No scores available yet. Play the game first!
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <br>

    <a href="${pageContext.request.contextPath}/index.jsp">
        <button type="button">Play Game</button>
    </a>

</div>

</body>
</html>
