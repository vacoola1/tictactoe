import React, {Component, PureComponent} from 'react'
import Board from "../Board";

export default class Game extends PureComponent {

    constructor() {
        super();
        this.statuses = {
            inProgress: {
                key: "IN_PROGRESS",
                label: "In progress"
            },
            winX: {
                key: "WIN_X",
                label: "X won"
            },
            winO: {
                key: "WIN_O",
                label: "O won"
            },
            draw: {
                key: "DRAW",
                label: "Draw"
            }
        }
    }

    render() {
        const {game, isOpen, onButtonClick} = this.props
        const body = isOpen && <section className="card-text">
            <Board game={game} statuses={this.statuses}/>
        </section>
        return (
            <div className='card border-primary mb-3'>
                <div className="card-header">
                    <h2>
                        <span>{game.name}</span> - <span>{game.status}</span>
                        <button className="btn btn-primary btn-lg float-right" onClick={onButtonClick}>
                            {isOpen ? 'Close' : 'Open'}
                        </button>
                    </h2>
                </div>

                <div className="card-body">
                    {body}
                </div>
            </div>
        )
    }

}
