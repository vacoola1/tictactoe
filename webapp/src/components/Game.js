import React, { PureComponent } from 'react'
import Board from "./Board";

export default class Game extends PureComponent {

    statuses = {
        IN_PROGRESS: {
            key: "IN_PROGRESS",
            label: "In progress"
        },
        WON_X: {
            key: "WON_X",
            label: "X won"
        },
        WON_O: {
            key: "WON_O",
            label: "O won"
        },
        DRAW: {
            key: "DRAW",
            label: "Draw"
        }
    }

    state = {
        game: null,
        name: "",
        status: this.statuses.IN_PROGRESS.label
    }

    componentDidMount() {
        this.updateGame(this.props.game)
    }

    render() {
        const {isOpen, onButtonClick} = this.props
        const body = isOpen &&
            <div className="card-body">
                <section className="card-text"> <Board game={this.state.game} statuses={this.statuses} onChangeGame={this.onChangeGame.bind(this)}/></section>
            </div>

        console.log('--- this.state.status : ', this.state.status)

        return (
            <div className={(this.state.status === this.statuses.IN_PROGRESS.label) ? 'card border-primary mb-3':'card mb-3'}>
                <div className="card-header">
                    <h2>
                        <span>{this.state.name}</span> - <span>{this.state.status}</span>
                        <button className="btn btn-primary btn-lg float-right" onClick={onButtonClick}>
                            {isOpen ? 'Close' : 'Open'}
                        </button>
                    </h2>
                </div>
                {body}
            </div>
        )
    }

    onChangeGame = (newStatus) => {
        this.setState({
            status: this.statuses[newStatus].label
        })
    }

    updateGame(game) {
        this.setState({
            game: game,
            name: game.name,
            status: this.statuses[game.status].label
        })
    }
}
