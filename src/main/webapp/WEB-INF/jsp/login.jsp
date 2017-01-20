<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <sec:authorize access="isAuthenticated()">
        <% response.sendRedirect("/"); %>
    </sec:authorize>
    <meta charset="UTF-8"/>
    <title>Login page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link rel="stylesheet" href="/css/user.css">
</head>

<body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <div class="col-sm-6">
        <h2>Sign in</h2>
        <form role="form" action="/login" method="post">
            <div class="mt5">
                <label for="email" class="col-sm-3">Email address</label>
                <input type="text" name="email" id="email" required="required" autofocus="autofocus" autocomplete="off">
            </div>
            <div class="mt5">
                <label for="password" class="col-sm-3">Password</label>
                <input type="password" name="password" id="password" required="required" autocomplete="off">
            </div>
            <div class="mt5">
                <label for="remember-me" class="col-sm-3">Remember me</label>
                <input type="checkbox" name="remember-me" id="remember-me">
            </div>
            <button type="submit">Sign in</button>
        </form>
    </div>

    <div class="col-sm-6">
        <h2>Registration</h2>
        <div class="mt5">
            <label class="col-sm-3">Email address</label>
            <input type="text" name="r-email" id="r-email" required="required" autocomplete="off">
        </div>
        <div class="mt5">
            <label class="col-sm-3">Password</label>
            <input type="password" name="r-password" id="r-password" required="required" autocomplete="off">
        </div>
        <div class="mt5">
            <label class="col-sm-3">Re-type Password</label>
            <input type="password" name="re-type-password" required="required" autocomplete="off">
        </div>
        <button id="btnRegister" onclick="register()" class="col-sm-offset-3 mt5">Sign up</button>
    </div>
    <script>
        function register(){
            var _username = $('#r-email').val();
            var _password =  $('#r-password').val();
            $.post("/registration",
                {
                    username: _username,
                    password: _password
                }, function (data, status) {
                    if (status == "success") {
                        $("#email").val(data.email);
                        $("#password").val(data.passwordHash);
                    }
                });
        }
    </script>
</body>
</html>