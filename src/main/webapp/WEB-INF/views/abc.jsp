<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ge.chessplayer.controllers.MainPage" %>
<%@ page isELIgnored="false" %>

<html onmousemove="movePiece(event)"  onmouseup="placePiece(event)">
    <head>
        <link rel="stylesheet" href="../../resources/css/mainpage.css">
        <title>Chess</title>
    </head>
    <body style="margin: 0; overflow: hidden;">
        <table class="header">
            <tr style="height: 50px; width: 100%; background-color: lightgrey">
                <td style="width: 100%;">temporary styles</td>

                <td>
                    <div class="login-button">Login</div>
                </td>
                <td>
                    <div class="login-button">Register</div>
                </td>
            </tr>
            <tr>
                <td>
                    <div id="chessboard" class="board">
                        <%--@elvariable id="board" type="ge.chessplayer.model.Board"--%>
                        <c:set value="0" var="index"/>
                        <c:forEach items="${board.pieces}" var="piece">
                            <c:if test="${piece != null}">
                                <div id="figure_${index}" class="piece ${piece.color}_${piece.name}" style="transform: translate(${piece.position.y*100}%, ${piece.position.x*100}%);" onmousedown="pickup('figure_${index}')"></div>
                            </c:if>
                            <c:set var="index" value="${index + 1}"/>
                        </c:forEach>
                    </div>
                </td>

            </tr>
        </table>
    </body>
    <script>
        /*const PieceTypes = {
            WHITE_PAWN: 'white_pawn',
            WHITE_KNIGHT: 'white_knight',
            WHITE_BISHOP: 'white_bishop',
            WHITE_ROOK: 'white_rook',
            WHITE_QUEEN: 'white_queen',
            WHITE_KING: 'white_queen',

            BLACK_PAWN: 'black_pawn',
            BLACK_KNIGHT: 'black_knight',
            BLACK_BISHOP: 'black_bishop',
            BLACK_ROOK: 'black_rook',
            BLACK_QUEEN: 'black_queen',
            BLACK_KING: 'black_queen'
        }


        function loadPosition() {
            let xhttp = new XMLHttpRequest()
            xhttp.open("POST", "/getposition")
            xhttp.send()
        }
        initialArrangement()
        */
        let pickedPiece

        let board = document.getElementById("chessboard")
        let oldPosition
        function pickup(id) {
            pickedPiece = document.getElementById(id)
            oldPosition = pickedPiece.style.transform.toString()
        }
        function movePiece(event) {

            if (pickedPiece != null) {
                let X = 800 * (event.clientX - board.offsetLeft) / board.offsetWidth - 50
                let Y = 800 * (event.clientY - board.offsetTop) / board.offsetHeight - 50
                //console.log(X, Y)
                pickedPiece.style = "transform: translate(" + X + "%, " + Y + "%)"
            }
        }


        function placePiece(event){
            if(pickedPiece != null){
                let X = Math.floor(8*(event.clientX - board.offsetLeft)/(board.offsetHeight))
                let Y = Math.floor(8*(event.clientY - board.offsetTop)/(board.offsetWidth))
                console.log(oldPosition)
                if(X >= 8 || Y >= 8) {
                    pickedPiece.style.transform = oldPosition
                    pickedPiece = null
                    return
                }
                if(X < 0 || Y < 0) {
                    pickedPiece.style.transform = oldPosition
                    pickedPiece = null
                    return
                }
                let request = new XMLHttpRequest()
                request.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200){
                        let tokens = request.responseText.split("&")
                        if (tokens[0] === "1") {
                            pickedPiece.style = "transform: translate(" + Math.floor(X) * 100 + "%, " + Math.floor(Y) * 100 + "%);"
                            if (tokens[1] !=="-1") {
                                document.getElementById("figure_" + tokens[1]).hidden = true
                            }
                        } else {
                            pickedPiece.style.transform = oldPosition

                        }

                        pickedPiece = null
                    }

                }

                request.open("POST", "/make-move", true)
                request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                request.send("boardId=${board.id}&pieceId=" + pickedPiece.id + "&targetX=" + Y + "&targetY=" + X);
            }

        }

            /*
            for(let i = 0; i < 8; i ++){
                let pawn = document.createElement("div")
                pawn.classList.add("white_pawn")
                board.appendChild(pawn)
                pawn.style = "transform: translate(" + i*100 + "%, 600%);"
                pawn.addEventListener("mousedown", function (event) {
                    event.preventDefault()
                    console.log(event.clientX + " " + event.clientY)
                    pickedPiece = pawn
                    console.log("down")

                    moveFigure(pawn, event)
                })

            }
        }

        function moveFigure(figure, event) {

            figure.style = "transform: translate(" + (event.clientX - board.offsetLeft - figure.offsetHeight/2) + "px, " + (event.clientY - board.offsetTop - figure.offsetWidth/2) + "px)"

        }*/
    </script>
</html>
