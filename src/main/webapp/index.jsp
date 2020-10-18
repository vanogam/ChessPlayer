<html>
    <head>
        <link rel="stylesheet" href="views/css/mainpage.css">
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
                    </div>
                </td>

            </tr>
        </table>
    </body>
    <script>

        let pickedPiece
        let board
        initialArrangement()
        function initialArrangement() {
            board = document.getElementById("chessboard")
            window.addEventListener("mousemove", function (event) {

                console.log("moving")
                if(pickedPiece != null) {
                    event.preventDefault()
                    moveFigure(pickedPiece, event)
                }
            })
            window.addEventListener("mouseup", function (event) {
                console.log("up")
                if(pickedPiece != null){
                    let X = 8*(event.clientX - board.offsetLeft)/(board.offsetHeight)
                    let Y = 8*(event.clientY - board.offsetTop)/(board.offsetWidth)
                    if(X >= 8 || Y >= 8) {
                        pickedPiece = null
                        return
                    }
                    if(X < 0 || Y < 0) {
                        pickedPiece = null
                        return
                    }

                    pickedPiece.style = "transform: translate(" + Math.floor(X)*100 + "%, " + Math.floor(Y)*100 + "%);"

                }
                pickedPiece = null
            })


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

        }
    </script>
</html>
