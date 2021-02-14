//import React, {Component} from 'react'


class Board extends React.Component{
    state = {
        pieces: []
    }

    componentDidMount() {
       /* let th = this
        fetch("/board", {method: 'POST'})
            .then(function (request) {
                console.log(response)
                let tokens = response.split(", ")
                for(const token of tokens) {
                    let obj = JSON.parse(token)
                    th.state.pieces.push(new Piece(obj.name, obj.color, obj.x, obj.y))
                    th.render()
                }
            })*/
        let th = this
        let request = new XMLHttpRequest()
        request.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200){

                console.log(request.responseText)
                let tokens = request.responseText.split(", ")
                let index = 0
                let pieceArray = []
                for(const token of tokens) {
                    if (token.length < 3) {
                        index ++
                        continue
                    }
                    let obj = JSON.parse(token)
                    console.log(token)
                    th.state.pieces.push(<Piece index={index ++}
                                           color={obj.color}
                                           name={obj.name}
                                           x={obj.x}
                                           y={obj.y}
                                           board={th}/>)
                    //th.state.pieces.push(new Piece(index ++, obj.color, obj.name, obj.x, obj.y))

                }
                ReactDOM.render(<div>{th.state.pieces}</div>, document.getElementById("chessboard"))
                th.forceUpdate()
            }

        }

        request.open("POST", "/board", true)
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.send("");

        window.addEventListener('resize', () => {
            th.forceUpdate()
        });
    }



    render() {
        console.log(this.state.pieces.length)
        return <div id="chessboard" className="board"/>;

    }



}
//ReactDOM.render(<Board/>, document.getElementById("root"));