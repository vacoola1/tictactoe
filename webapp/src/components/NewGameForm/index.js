import React, {PureComponent} from 'react'
import Game from '../Game/'
import api from "../../api";

export default class NewGameForm extends PureComponent {

    constructor() {
        super();
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        window.alert('asdad')
        // const data = new FormData(event.target);
        //
        // fetch('/api/form-submit-url', {
        //     method: 'POST',
        //     body: data,
        // });

        // api.loadGames().then(games => {
        //     console.log('=== res', games);
        //     this.setState({
        //         games: games
        //     });
        // })
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit} className="form-inline">
                <div className="form-group mb-2">
                    <label htmlFor="newname" className="">New Game</label>
                </div>
                <div className="form-group mx-sm-3 mb-2">
                    <input type="text" className="form-control" id="newname" name="newname" placeholder="Input name"/>
                </div>
                <button className="btn btn-primary mb-2">New Game</button>
            </form>
        );
    }
}