<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Customer List</title>
    <style>
        /* Existing CSS styles */
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
            max-width: 800px;
            margin: auto;
        }
        .card {
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-top: 20px;
        }
        h2 {
            color: tomato;
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .btn {
            padding: 5px 10px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        .btn-edit {
            background-color: #4CAF50;
            color: white;
        }
        .btn-delete {
            background-color: #f44336;
            color: white;
        }
        .btn-new {
            background-color: #008CBA;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            margin-top: 20px;
            display: inline-block;
        }
        .btn-sync {
            background-color: #FFC107;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
            display: inline-block;
        }
        .btn-new:hover, .btn-edit:hover, .btn-delete:hover, .btn-sync:hover {
            opacity: 0.8;
        }
        .search-container {
            margin-bottom: 20px;
        }
        .search-container label {
            margin-right: 10px;
        }
        .search-container select, .search-container input[type="text"] {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .search-container button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 5px;
            cursor: pointer;
        }
        .search-container button:hover {
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
    <div class="container">
        <h2>Customer List</h2>
        <a href="new" class="btn-new">Add New Customer</a>
        <a href="sync" class="btn-sync">Sync</a>
        
        <!-- Search form -->
        <div class="search-container">
            <form action="list" method="get">
                <label for="searchField">Search By:</label>
                <select id="searchField" name="searchField">
                    <option value="firstName">First Name</option>
                    <option value="lastName">Last Name</option>
                    <option value="email">Email</option>
                    <option value="phone">Phone</option>
                    <!-- Add more options as needed -->
                </select>
                <input type="text" name="searchTerm" placeholder="Enter search term" value="${param.searchTerm}">
                <button type="submit">Search</button>
            </form>
        </div>

        <div class="card">
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Street</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="customer" items="${listCustomer}">
                    <tr>
                        <td><c:out value="${customer.firstName}"/></td>
                        <td><c:out value="${customer.lastName}"/></td>
                        <td><c:out value="${customer.street}"/></td>
                        <td><c:out value="${customer.address}"/></td>
                        <td><c:out value="${customer.city}"/></td>
                        <td><c:out value="${customer.state}"/></td>
                        <td><c:out value="${customer.email}"/></td>
                        <td><c:out value="${customer.phone}"/></td>
                        <td>
                            <a href="edit?id=${customer.id}" class="btn btn-edit">Edit</a>
                            <a href="delete?id=${customer.id}" class="btn btn-delete">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
