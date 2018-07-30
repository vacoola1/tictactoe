import React, {PureComponent} from 'react'
import api from "../../api";

export default class NewGameForm extends PureComponent {

    constructor() {
        super();
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        const {onCreateClick} = this.props

        console.log('--- target', event.target)
        const formData = new FormData(event.target)
        const newGame = { name: formData.get('newname') };
        console.log('===!!!! new game', newGame);

        api.createGame(newGame).then((game) => {
            console.log('=== new game', game);
            onCreateClick()
        })
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