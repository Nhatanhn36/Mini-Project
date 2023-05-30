<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Book List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<main>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light">Book List</h1>
                <p>
                    <a href="books?action=new" class="btn btn-primary">Add new book</a>
                </p>
            </div>
        </div>
    </section>
    <div class="py-5 bg-light">
        <div class="container">
            <form action="books?action=searchByName" method="GET">
                <input type="hidden" name="action" value="search">
                <input type="text" name="searchTerm" placeholder="Tìm kiếm theo tên sách">
                <button class="btn btn-secondary" type="submit">Tìm kiếm</button>
            </form>
            <div class="row row-cols-1 row-cols-ms-2 row-cols-md-4 g-4">
                <c:forEach var="bookArr" items="${bookList}">
                    <div class="col">
                        <div class="card shadow-sm">
                            <img src="${bookArr.img}" style="height: 400px; width: auto; object-fit: fill" alt="${bookArr.nameBook}">
                            <div class="card-body">
                                <p class="card-text">
                                    <label>Book name: </label> ${bookArr.nameBook}
                                    <br>
                                    <label>Author: </label> ${bookArr.nameAuthor}
                                    <br>
                                    <label>Price: </label> ${bookArr.price}
                                    <br>
                                    <label>Quantity: </label> ${bookArr.quantity}
                                </p>
                                <div class="btn-group">
                                    <a type="button" href="books?action=edit&id=${bookArr.id}" class="btn btn-sm btn-outline-secondary">Edit</a>
                                    <a type="button" href="books?action=delete&id=${bookArr.id}" class="btn btn-sm btn-outline-secondary" onclick="return confirm('Are you sure you want to delete this book ?')">Delete</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</main>
</body>
</html>
