//import React, {Component} from 'react'


class LoginForm extends React.Component {
    state = {
        type: ""
    }

    constructor(props) {
        super(props);
        this.state.type = props.type
    }

    componentWillUpdate(nextProps, nextState, nextContext) {
        this.state.type = nextProps.type
    }

    successfulLogin() {
        ReactDOM.render(
            <div id={'loginPopup'} className={'popup'}>
                <div className={'popup-content successful'}>
                    <span className={'popup-close'} onClick={() => {
                        document.getElementById('loginPopup').style.display = 'none'
                    }}>&times;</span>
                    <p>{this.state.type === "login" ? 'Login' : 'Registration'} successful</p>
                </div>
            </div>, document.getElementById("popup-container"));
    }

    unsuccessfulLogin(message) {
        ReactDOM.render(
            <div id={'loginPopup'} className={'popup'}>
                <div className={'popup-content unsuccessful'}>
                    <span className={'popup-close'} onClick={() => {
                        document.getElementById('loginPopup').style.display = 'none'
                    }}>&times;</span>
                    <p>{message}</p>
                </div>
            </div>, document.getElementById("popup-container"));
    }

    render() {
        return (
            <table>
                <tbody>
                <tr>
                    <td style={{width: '50%'}}/>
                    <table className={'login-container'}>
                        <tbody>
                        <tr>
                            <td className={'login-header'}>{this.state.type === "login" ? 'Login' : 'Register'}</td>
                        </tr>
                        <tr>
                            <table className={'login-form-container'}>
                                <tbody>
                                <tr>
                                    <td>
                                        <div className={'login-label'}>username: </div>
                                    </td>
                                    <td>
                                        <input id={'user'} className={'login-input'} spellCheck={"false"} type={'text'}/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div className={'login-label'}>password: </div>
                                    </td>
                                    <td>
                                        <input id={'password'} className={'login-input'} spellCheck={"false"} type={'password'}/>
                                    </td>
                                </tr>
                                {this.state.type === "register" ? (
                                    [
                                    <tr>
                                        <td>
                                            <div className={'login-label'}>First Name: </div>
                                        </td>
                                        <td>
                                            <input id={'firstName'} className={'login-input'} spellCheck={"false"} type={'text'}/>
                                        </td>
                                    </tr>,
                                    <tr>
                                        <td>
                                            <div className={'login-label'}>Last Name: </div>
                                        </td>
                                        <td>
                                            <input id={'lastName'} className={'login-input'} spellCheck={"false"} type={'text'}/>
                                        </td>
                                    </tr>
                                    ]) : (<div/>)}
                                <tr>
                                    <td style={{width: '50%'}}/>
                                    <button className={'login-button'} onClick={() =>{
                                        let th = this
                                        let request = new XMLHttpRequest()
                                        request.onreadystatechange = function () {
                                            if (this.readyState === 4 && this.status === 200) {

                                                console.log(request.responseText)
                                                switch (request.responseText) {
                                                    case "0":
                                                        th.successfulLogin();
                                                        setTimeout(() => {
                                                            location.replace("/")
                                                        }, 3000)
                                                        break
                                                    default:
                                                        th.unsuccessfulLogin(request.responseText)
                                                }

                                            }
                                        }
                                        request.open("POST", "/" + (th.state.type === "login" ? 'login' : 'register'), true)
                                        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                                        request.send("username="+ document.getElementById("user").value +"&password=" + document.getElementById("password").value+(th.state.type === "login" ? '' : '&firstName=' + document.getElementById("firstName").value + '&lastName=' + document.getElementById("lastName").value));

                                    }}>{this.state.type === "login" ? 'Login' : 'Register'}</button>
                                    <td style={{width: '50%'}}/>
                                </tr>
                                </tbody>
                            </table>
                        </tr>
                        </tbody>
                    </table>
                    <td style={{width: '50%'}}/>
                </tr>
                </tbody>
            </table>

        );
    }

}

//ReactDOM.render(<LoginForm/>, document.getElementById("root"))