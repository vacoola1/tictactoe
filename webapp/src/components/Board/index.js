import React, { Component } from 'react';

import './style.css';
import api from "../../api";

export default class Board extends Component {

    constructor() {
        super();
        this.state = {
            winner: undefined,
        };
        this.gameState = {
            turn: 'X',
            gameEnded: false,
            board: Array(9).fill(''),
            totalMoves: 0
        }
    }

    clicked(box) {
        if(this.gameState.gameEnded) return;
        this.doNextMove(box);
    }

    doNextMove(box) {
        const {game, statuses} = this.props
        const cell = box.dataset.square

        return api.doNextMove(game, cell).then((status) => {
            if(this.gameState.board[cell-1] === '') {
                this.gameState.totalMoves++
                this.gameState.turn = this.gameState.totalMoves % 2 === 0 ? 'O' : 'X'
                this.gameState.board[cell-1] = this.gameState.turn
                box.innerText = this.gameState.turn;
            }
            if(status !== statuses.inProgress.key) {
                this.gameState.gameEnded = true;
            }

        });
    }

    render() {
        return (
            <div id="game">
                <div id="status">{this.state.winnerLine}</div>
                <div id="board" onClick={(e)=>this.clicked(e.target)}>
                    <div className="square" data-square="1"/>
                    <div className="square" data-square="2"/>
                    <div className="square" data-square="3"/>
                    <div className="square" data-square="4"/>
                    <div className="square" data-square="5"/>
                    <div className="square" data-square="6"/>
                    <div className="square" data-square="7"/>
                    <div className="square" data-square="8"/>
                    <div className="square" data-square="9"/>
                </div>
            </div>
        );
    }
}
