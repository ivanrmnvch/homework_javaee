<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Auth</title>
</head>

<body>
<form
        action="sign-in"
        method="GET"
>
    <button class="button">Auth</button>
</form>
</body>

<style>
    html, body {
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .button {
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

    .button:hover {
        background-color: #45a049;
    }
</style>
</html>