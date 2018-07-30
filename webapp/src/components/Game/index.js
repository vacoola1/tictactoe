import React, {Component, PureComponent} from 'react'
import Board from "../Board";

export default class Game extends PureComponent {

    statuses = {
        IN_PROGRESS: {
            key: "IN_PROGRESS",
            label: "In progress"
        },
        WON_X: {
            key: "WIN_X",
            label: "X won"
        },
        WON_O: {
            key: "WIN_O",
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

    componentWillUpdate(nextProps) {
        console.log('--- WillUpdate : ', nextProps)
        this.updateGame(nextProps.game)
    }

    componentDidMount() {
        console.log('--- DidMoun : ', this.props)
        this.updateGame(this.props.game)
    }

    render() {
        const {isOpen, onButtonClick} = this.props
        const body = isOpen &&
            <div className="card-body">
                <section className="card-text"> <Board game={this.state.game} statuses={this.statuses} onChangeGame={this.onChangeGame.bind(this)}/></section>
            </div>
        return (
            <div className='card border-primary mb-3'>
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

    onChangeGame = (game) => {
        this.updateGame(game)
    }

    updateGame(game) {
        this.setState({
            game: game,
            name: game.name,
            status: this.statuses[game.status].label
        })
    }
}
