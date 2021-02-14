//import React, {Component} from 'react'


class Profile extends React.Component {
    state = {
        user: "",
        name: "",
        rating: 0
    }

    constructor(props) {
        super(props);
        this.state.user = props.user
        this.state.name = props.name
        this.state.rating = props.rating
    }

    render() {
        return (
            <table className={'profile-container'}>
                <tbody>
                    <td>
                        <img className={'profile-image'} src={'/resources/img/noimage.png'}/>
                    </td>
                    <td>
                        <div className={'profile-user'}>{this.state.user}</div>
                        <div className={'profile-name'}>{this.state.name}</div>
                        <div className={'profile-rating'}>{this.state.rating}</div>
                    </td>
                </tbody>
            </table>
        )
    }
}