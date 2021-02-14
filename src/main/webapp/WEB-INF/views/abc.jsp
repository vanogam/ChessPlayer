<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ge.chessplayer.controllers.MainPage" %>
<%@ page isELIgnored="false" %>


<html>


    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="../../resources/css/mainpage.css">
        <title>Chess</title>

    </head>



    <body class="body" style="margin: 0; overflow: hidden;">
    <script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
    <script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>
    <script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>

    <div id = "root" class="board_container">
    </div>
    <div id = "popup-container" class="board_container"></div>



    <script src="../../resources/js/profile.jsx" type="text/babel"></script>
    <script src="../../resources/js/LoginForm.jsx" type="text/babel"></script>
    <script src="../../resources/js/board.jsx" type="text/babel"></script>
    <script src="../../resources/js/Piece.jsx" type="text/babel"></script>
    <script src="../../resources/js/App.jsx" type="text/babel"></script>

    <script>
    </script>
    </body>
</html>
