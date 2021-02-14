//import React, {Component} from 'react'


class Board extends React.Component{
    state = {
        pieces: [],
        myuser: "You",
        rating: 1200
    }

    constructor(props) {
        super(props);
        this.state.myuser = props.user
        this.state.rating = props.rating
    }

    componentDidMount() {

        let th = this
        let request1 = new XMLHttpRequest()
        request1.open("POST", "/getuserinfo", true)
        request1.onreadystatechange= function () {
            if (this.readyState === 4 && this.status === 200) {
                if (request.responseText == null || request.responseText.length === 0){
                    th.setState({user: ""})
                }
                else {
                    let tokens = request.responseText.split(",")
                    th.setState({user: tokens[0], rating: tokens[1]})

                }
            }
        }
        request1.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request1.send("")

        let request = new XMLHttpRequest()
        request.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200){

                let tokens = request.responseText.split(", ")
                let index = 0
                for(const token of tokens) {
                    if (token.length < 3) {
                        index ++
                        continue
                    }
                    let obj = JSON.parse(token)
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
        return <div className={'board-container'}><div>Opponent</div><div id="chessboard" className="board"/><div>{this.state.myuser}</div></div>;

    }



}
//ReactDOM.render(<Board/>, document.getElementById("root"));