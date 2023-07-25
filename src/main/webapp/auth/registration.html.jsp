<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reg</title>
</head>

<body>
<div class="container">
    <form
            action="sign-in"
            method="GET"
    >
        <div class="form-group">
            <div
                    class="first-title-item"
            >
                <input class="btn-return" type="submit" value="&#10060;">
            </div>
            <h2>Registration</h2>
        </div>
    </form>
    <form action="auth" method="post">
        <div class="form-group">
            <div class="group-item">
                <div>
                    <label for="email">Email</label>
                </div>
                <div>
                    <input type="email" id="email" name="email" required>
                </div>
            </div>
            <div class="group-item">
                <div>
                    <label for="username">Login</label>
                </div>
                <div>
                    <input type="text" id="username" name="username" required>
                </div>
            </div>
            <div class="group-item">
                <div>
                    <label for="password">Pass</label>
                </div>
                <div>
                    <input type="password" id="password" name="password" required>
                </div>
            </div>
        </div>
        <button type="submit" class="btn">Create an account</button>
    </form>
</div>
</body>

<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
    }

    .container {
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
        padding: 20px;
        width: 300px;
    }

    h2 {
        margin: 0 0 20px 0;
        text-align: center;
    }

    .form-group {
        display: flex;
        flex-direction: column;
    }

    .group-item {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 10px;
    }

    .first-title-item {
        text-align: right;
    }

    label {
        font-weight: bold;
        cursor: pointer;
    }

    input[type="email"],
    input[type="text"],
    input[type="password"] {
        width: 200px;
        padding: 10px;
        border-radius: 3px;
        border: 1px solid #ccc;
    }

    .btn {
        background-color: #4CAF50;
        border: none;
        color: white;
        margin-bottom: 10px;
        padding: 10px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        border-radius: 3px;
        cursor: pointer;
        width: 100%;
    }

    .btn:hover {
        background-color: #45a049;
    }

    .btn-return {
        padding: 0;
        margin: 3px;
        width: 17px;
        height: 17px;
        background-color: white;
        border: none;
        text-align: center;
        cursor: pointer;
    }
</style>
</html>

