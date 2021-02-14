//import React, {Component} from 'react'


class App extends React.Component {
    state = {
        user: "",
        rating: 0,
        page: "main",
        name: ""
    }

    componentDidMount() {
        let th = this
        let request = new XMLHttpRequest()
        request.open("POST", "/getuserinfo", true)
        request.onreadystatechange= function () {
            if (this.readyState === 4 && this.status === 200) {
                if (request.responseText == null || request.responseText.length === 0){
                    th.setState({user: ""})
                }
                else {
                    let tokens = request.responseText.split(",")
                    th.setState({user: tokens[0], rating: tokens[1], name: tokens[2]})

                }
            }
        }
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.send("")
    }

    render() {
        let th = this


        return (<div>
                    <table id={'nav'} className={'navbar'}>
                        <tbody>
                            <tr>
                                <td className={'navbar-button'} onClick={() => {
                                    th.setState({page: 'main'});
                                }}>
                                    <div>ChessPlayer</div>
                                </td>
                                <td style={{width: '100%'}}/>
                                {this.state.user === "" ? ([
                                <td className={'navbar-button'} onClick={() => {
                                    th.setState({page: 'login'});
                                }}>
                                    <div>Login</div>
                                </td>,
                                <td className={'navbar-button'} onClick={() => {
                                    th.setState({page: 'register'});
                                }}>
                                    <div>Register</div>
                                </td>]) :
                                    ([
                                        <td className={'navbar-button'} onClick={() => {
                                            th.setState({page: 'profile'});
                                        }}>
                                            <div>{this.state.user}</div>
                                        </td>,
                                        <td className={'navbar-button'} onClick={() => {
                                            let request = new XMLHttpRequest()
                                            request.open("POST", "/logout", false)
                                            request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                                            request.send("");

                                            th.setState({page: 'main', user: ""});
                                        }}>
                                            <div>Logout</div>
                                        </td>])}
                                <td className="icon navbar-button" onClick={
                                    () => {
                                        let x = document.getElementById("nav");
                                        if (x.className === "navbar") {
                                            x.className += " responsive";
                                        } else {
                                            x.className = "navbar";
                                        }
                                    }
                                }>
                                    <i className="fa fa-bars" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    {this.state.page === 'login' ? (<LoginForm type={'login'}/>)
                        : this.state.page === 'register' ? (<LoginForm type={'register'}/>)
                        : this.state.page === 'profile' ? (<Profile name={this.state.name}
                                                                    user={this.state.user}
                                                                    rating={this.state.rating}/>) : <Board user={this.state.user}/>
                    }</div>);
    }

}

ReactDOM.render(<App/>, document.getElementById("root"))