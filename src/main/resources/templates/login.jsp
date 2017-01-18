<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <sec:authorize access="isAuthenticated()">
        <% response.sendRedirect("/"); %>
    </sec:authorize>
    <meta charset="UTF-8"/>
    <title>Login page</title>
</head>
<body>
    <form role="form" action="/login" method="post">
        <div>
            <label for="email">Email address</label>
            <input type="text" name="email" id="email" required="required" autofocus="autofocus">
        </div>
        <div>
            <label for="password">Password</label>
            <input type="password" name="password" id="password" required="required">
        </div>
        <div>
            <label for="remember-me">Remember me</label>
            <input type="checkbox" name="remember-me" id="remember-me">
        </div>
        <button type="submit">Sign in</button>
    </form>
</body>
</html>