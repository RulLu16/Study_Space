<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!-- front와 퍼블리셔의 차이를 이해 못했는데, html, css 등으로
     전체적인 디자인 틀을 잡는게 퍼블리셔나 웹디자이너가 하는일이고
     front는 거기서 필요한 로직을 코딩하고 백으로 받은 데이터를 처리하는 역할인 것 같다..
     드디어 차이가 뭔지 조금 감이 잡힌 것 같다. -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Clonstagram</title>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
          rel="stylesheet">
    <link rel="shortcut icon" href="/images/favicon.ico">
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<main id="login">
    <div class="login__column">
        <img src="/images/phoneImage.png" />
    </div>
    <div class="login__column">
        <div class="login__box">
            <img src="/images/loginLogo.png" />
            <form action="/auth/loginProc" method="post" class="login__form">
                <input type="text" name="username" placeholder="Username" required>
                <input type="password" name="password" placeholder="Password" required>
                <input type="submit" value="Log in">
            </form>
            <span class="login__divider">
          or
        </span>
            <a href="/auth/kakao/login" class="login__fb-link">
                <img width="100%" src="/images/kakao_account_login_btn_medium_wide.png"/>
            </a>
            <a href="#" class="login__small-link">Forgot password?</a>
        </div>
        <div class="login__box">
        <span class="login__text">
          Don't have an account? <br />
        </span>
            <a class="login__blue-link" href="/auth/join">Sign up</a>
        </div>
        <div class="login__t-box">
        <span class="login__text">
          Get the app.
        </span>
            <div class="login__appstores">
                <img src="/images/ios.png" class="login__appstore" />
                <img src="/images/android.png" class="login__appstore" />
            </div>
        </div>
    </div>
</main>
<%@ include file="../include/footer.jsp" %>
</body>
</html>
