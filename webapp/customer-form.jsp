<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Customer Management Application</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .navbar {
            background-color: orange;
            overflow: hidden;
        }
        .navbar a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }
        .navbar a:hover {
            background-color: darkorange;
        }
        .container {
            padding: 20px;
            max-width: 600px;
            margin: auto;
        }
        .card {
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-top: 20px;
        }
        .card-body {
            padding: 10px;
        }
        h2 {
            color: tomato;
            text-align: center;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .btn-success {
            background-color: green;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }
        .btn-success:hover {
            background-color: darkgreen;
        }
    </style>
</head>
<body>
    <header>
        <div class="navbar">
            <a href="https://www.javaguides.net" class="navbar-brand">Customer Data App</a>
            <a href="<%=request.getContextPath()%>/list">Customers</a>
        </div>
    </header>
    <br>
    <div class="container">
        <div class="card">
            <div class="card-body">
                <c:if test="${customer != null}">
                    <form action="update" method="post">
                </c:if>
                <c:if test="${customer == null}">
                    <form action="insert" method="post">
                </c:if>
                <h2>
                    <c:if test="${customer != null}">
                        Edit Customer
                    </c:if>
                    <c:if test="${customer == null}">
                        Add New Customer
                    </c:if>
                </h2>
                <c:if test="${customer != null}">
                    <input type="hidden" name="id" value="<c:out value='${customer.id}' />" />
                </c:if>
                <div class="form-group">
                    <label>First Name</label>
                    <input type="text" value="<c:out value='${customer.firstName}' />" class="form-control" name="firstName" required="required">
                </div>
                <div class="form-group">
                    <label>Last Name</label>
                    <input type="text" value="<c:out value='${customer.lastName}' />" class="form-control" name="lastName" required="required">
                </div>
                <div class="form-group">
                    <label>Street</label>
                    <input type="text" value="<c:out value='${customer.street}' />" class="form-control" name="street">
                </div>
                <div class="form-group">
                    <label>Address</label>
                    <input type="text" value="<c:out value='${customer.address}' />" class="form-control" name="address">
                </div>
                <div class="form-group">
                    <label>City</label>
                    <input type="text" value="<c:out value='${customer.city}' />" class="form-control" name="city">
                </div>
                <div class="form-group">
                    <label>State</label>
                    <input type="text" value="<c:out value='${customer.state}' />" class="form-control" name="state">
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <input type="email" value="<c:out value='${customer.email}' />" class="form-control" name="email">
                </div>
                <div class="form-group">
                    <label>Phone</label>
                    <input type="text" value="<c:out value='${customer.phone}' />" class="form-control" name="phone">
                </div>
                <button type="submit" class="btn-success">Save</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
