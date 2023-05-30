<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<main>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
            <h1 class="fw-light">Book Form</h1>
        </div>
    </div>
    </section>
    <div class="py-5 bg-light">
        <div class="container">
            <c:choose>
                <c:when test="${empty book.id}">
                    <form method="POST" action="books?action=create" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label for="nameBook" class="form-label">Book name: </label>
                            <input type="text" class="form-control" id="nameBook" name="nameBook">
                        </div>
                        <div class="mb-3">
                            <label for="nameAuthor" class="form-label">Name Author: </label>
                            <input type="text" class="form-control" id="nameAuthor" name="nameAuthor">
                        </div>
                        <div class="mb-3">
                            <label for="price" class="form-label">Price: </label>
                            <input type="text" class="form-control" id="price" name="price">
                        </div>
                        <div class="mb-3">
                            <label for="quantity" class="form-label">Quantity: </label>
                            <input type="text" class="form-control" id="quantity" name="quantity">
                        </div>
                        <div class="mb-3">
                            <label for="img" class="form-label">Image: </label>
                            <input type="file" class="form-control" id="img" name="img">
                        </div>
                        <input type="submit" class="btn btn-primary" value="Create">
                        <a href="books" class="btn btn-secondary">Cancel</a>
                    </form>
                </c:when>
                <c:otherwise>
                    <form action="books?action=update" method="POST" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="${book.id}">
                        <div class="mb-3">
                            <label for="nameBook" class="form-label">Book name: </label>
                            <input type="text" class="form-control" id="nameBook" name="nameBook" value="${book.nameBook}">
                        </div>
                        <div class="mb-3">
                            <label for="nameAuthor" class="form-label">Name Author: </label>
                            <input type="text" class="form-control" id="nameAuthor" name="nameAuthor" value="${book.nameAuthor}">
                        </div>
                        <div class="mb-3">
                            <label for="price" class="form-label">Price: </label>
                            <input type="text" class="form-control" id="price" name="price" value="${book.price}">
                        </div>
                        <div class="mb-3">
                            <label for="quantity" class="form-label">Quantity: </label>
                            <input type="text" class="form-control" id="quantity" name="quantity" value="${book.quantity}">
                        </div>
                        <div class="mb-3">
                            <label for="img" class="form-label">Image: </label>
                            <input type="file" class="form-control" id="img" name="img">
                        </div>
                        <input type="submit" class="btn btn-primary" value="Update">
                        <a href="books" class="btn btn-secondary">Cancel</a>
                    </form>
                    <form action="books?action=delete" method="POST">
                        <input type="hidden" name="id" value="${book.id}">
                        <input type="submit" class="btn btn-danger" value="Delete">
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</main>
</body>
</html>
