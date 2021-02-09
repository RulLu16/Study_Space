<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal" />
</sec:authorize>

<nav class="navigation">
    <a href="/"> <img src="/images/logo.png" />
    </a> <input type="text" placeholder="Search">
    <div class="navigation__links">
        <a href="/image/explore" class="navigation__link">
            <i class="fa fa-compass"></i>
        </a>
        <a href="#" class="navigation__link like_popup">
            <i class="fa fa-heart-o"></i>
        </a>
        <a href="/user/${principal.user.id}" class="navigation__link">
            <i class="fa fa-user-o"></i>
        </a>
    </div>
</nav>

<div id="modal">
    <div id="follow_popup_list">
        <div class="follow_popup_item">
            <img src="/images/avatar.jpg" alt="코스사진">
            <p><a href="#">코스</a></p>
            <button class="following_btn">팔로잉</button>
        </div>

        <div class="follow_popup_item">
            <img src="/images/avatar.jpg" alt="코스사진">
            <p><a href="#">코스</a></p>
            <button class="follow_btn">팔로우</button>
        </div>

        <div class="follow_popup_close">
            <button type="button">닫기</button>
        </div>
    </div>
</div>