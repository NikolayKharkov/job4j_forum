<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>

    <title>Post Page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/'/>">Все посты</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/edit?id=${0}'/>"> Создать пост </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/usersPosts'/>"> Мои посты </a>
            </li>
        </ul>
    </div>
</div>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <p><b>Пост:</b> <c:out value="${post.name}"/></p>
                <p><b>Дата создания:</b> <c:out value="${post.created.time}"/></p>
                <p><b>Автор:</b> <c:out value="${post.user.username}"/></p>
                <p><b>Описание:</b><br> <c:out value="${post.description}"/></p>
            </div>
            <p><b>Оставить комментарий:</b><br></p>
            <div class="row">
                <form class="container" action="/addComment?id=${post.id}" method="POST">
                    <div class="form-group">
                        <textarea name="text" class="form-control" placeholder="Ваш комментарий" cols="100" rows="5"></textarea>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Оставить комментарий</button>
                    </div>
                </form>
            </div>
            <p><b>Обсуждение. Комментариев : </b><br> <c:out value="${post.comments.size()}"/></p>
            <c:forEach items="${post.comments}" var="comment">
                <div class="row">
                    <div class="container border border-warning rounded">
                        <p><b>Автор:</b> <c:out value="${comment.user.username}"/> <b>Время:</b> <c:out value="${comment.created.time}"/></p>
                        <p><c:out value="${comment.text}"/></p>
                    </div>
                </div>
                <br>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>