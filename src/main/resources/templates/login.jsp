<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Login page</title>
</head>
<body>
    <form role="form" action="/login" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div>
            <label for="email">Email address</label>
            <input type="text" name="email" id="email" required="required" autofocus="true">
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