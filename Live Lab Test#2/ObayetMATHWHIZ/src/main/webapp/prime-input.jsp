
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Math Whiz - Prime Number Check</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">

    <h1>🧠 PRIME NUMBER CHECK</h1>

    <h2>Enter an Integer</h2>

    <p>
        To test your prime number knowledge, please enter any integer.
        We will use your number to create a Prime Number question
        with three answer options.
    </p>

    <form action="${pageContext.request.contextPath}/game" method="post">

        <input type="hidden" name="action" value="getOptions">

        <label for="integer">The Integer:</label>

        <input type="number"
               id="integer"
               name="integer"
               placeholder="Enter your integer here..."
               step="1"
               required>

        <p class="error">${error}</p>

        <button type="submit">Get Options</button>

    </form>

</div>

</body>
</html>
