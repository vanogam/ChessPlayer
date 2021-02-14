//import React, {Component} from 'react'

class Piece extends React.Component {
    state = {
        key: 0,
        index: 0,
        color: "",
        name: "",
        x: 0,
        y: 0,
        picked: false,
        board: null
    }
    static pickedPiece = null
    static oldX = 0
    static oldY = 0
    static hasListeners = false


    constructor(props) {
        super(props);
        console.log(props)
        this.state.color = props.color
        this.state.index = props.index
        this.state.key = props.index
        this.state.name = props.name
        this.state.x = props.x
        this.state.y = props.y
        this.state.board = props.board

    }

    componentDidMount() {
        if (!Piece.hasListeners) {
            document.addEventListener('mousemove', (e) => {

                if (Piece.pickedPiece != null) {
                    let board = document.getElementById("chessboard")
                    let size = board.offsetWidth / 8
                    let X = (e.clientX - board.offsetLeft) / size - 0.5
                    let Y = (e.clientY - board.offsetTop) / size - 0.5
                    Piece.pickedPiece.state.x = Y
                    Piece.pickedPiece.state.y = X
                    Piece.pickedPiece.forceUpdate()
                }
            });

            document.addEventListener('mouseup', (e) => {

                if(Piece.pickedPiece != null){
                    let X = Math.floor(Piece.pickedPiece.state.x + 0.5)
                    let Y = Math.floor(Piece.pickedPiece.state.y + 0.5)
                    console.log(X , Y)
                    if(X >= 8 || Y >= 8) {
                        Piece.pickedPiece.setState({x: Piece.oldX, y: Piece.oldY, picked: false})
                        Piece.pickedPiece = null
                        return
                    }
                    if(X < 0 || Y < 0) {
                        Piece.pickedPiece.setState({x: Piece.oldX, y: Piece.oldY, picked: false})
                        Piece.pickedPiece = null
                        return
                    }
                    let request = new XMLHttpRequest()
                    request.onreadystatechange = function () {
                        if (this.readyState === 4 && this.status === 200){
                            console.log(request.responseText + "!!")
                            let tokens = request.responseText.split("&")
                            if (tokens[0] === "1") {
                                Piece.pickedPiece.setState({x: X, y: Y, picked: false})
                                if (tokens[1] !=="-1") {

                                    document.getElementById("figure_" + tokens[1]).hidden = true
                                }
                            } else {
                                Piece.pickedPiece.setState({x: Piece.oldX, y: Piece.oldY, picked: false})

                            }

                            Piece.pickedPiece = null
                        }

                    }

                    request.open("POST", "/make-move", true)
                    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    request.send("boardId=1&pieceId=" + Piece.pickedPiece.state.index + "&targetX=" + X + "&targetY=" + Y);
                }

            });

            Piece.hasListeners = true;
        }
        let th = this;
        window.addEventListener('resize', () => {
            th.forceUpdate()
        });
    }

    render() {
        let figureId = "figure_" + this.state.index;
        let cls = "piece " + this.state.color + "_" + this.state.name;
        let translate = 'translate(' + this.state.y*100 + '%, '  + this.state.x*100 + '%)'
        let size = document.getElementById("chessboard").offsetWidth/8
        let x = this.state.y*size
        let y = this.state.x*size
        let th = this
        return (<div style={{
            marginTop: y,
            marginLeft: x
        }} id={figureId} key={this.state.index} className={cls + (this.state.picked ? " picked" : "")}
        onMouseDown={() => {
            console.log(Piece.pickedPiece)
            Piece.pickedPiece = th
            th.setState({picked: true})
            Piece.oldX = th.state.x
            Piece.oldY = th.state.y
        }}
        />);
    }

}
