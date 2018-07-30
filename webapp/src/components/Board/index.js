import React, {PureComponent} from 'react';

import './style.css';
import api from "../../api";

export default class Board extends PureComponent {

    state = {
        gameEnded: false,
        board: Array(9).fill(''),
        totalMoves: 0
    }

    // componentWillUpdate(nextProps) {
    //     this.loadMoves(nextProps.game)
    // }

    componentDidMount() {
        this.loadMoves(this.props.game)
    }

    doNextMove(box) {
        if (this.state.gameEnded) return;

        const {game, statuses} = this.props
        const cell = box.dataset.square

        let gameEnded = this.state.gameEnded
        let totalMoves = this.state.totalMoves;
        let board = this.state.board

        api.doNextMove(game, cell).then((status) => {
            if (board[cell - 1] === '') {
                totalMoves++
                let turn = totalMoves % 2 === 0 ? 'O' : 'X'
                board[cell - 1] = turn
                box.innerText = turn
            }
            if (status !== statuses.IN_PROGRESS.key) {
                gameEnded = true;
            }

            this.setState({
                gameEnded: gameEnded,
                board: board,
                totalMoves: totalMoves
            })

        });
    }

    loadMoves(game) {
        const {statuses} = this.props
        let gameEnded = game.status === statuses.IN_PROGRESS
        let totalMoves = 0;
        let board = Array(9).fill('')

        api.loadMoves(game).then((moves) => {
            for (let index in moves) {
                let move = moves[index]
                console.log('--- move : ', move)
                console.log('--- cell : ', move.cell)
                board[move.cell - 1] = move.id % 2 === 0 ? 'O' : 'X'
                totalMoves++
            }

            console.log('--- state 1 : ', this.state)
            this.setState({
                gameEnded: gameEnded,
                board: board,
                totalMoves: totalMoves
            })
            console.log('--- state 2 : ', this.state)

        });

    }

    render() {
        return (
            <div id="game">
                <div id="board" onClick={(e) => this.doNextMove(e.target)}>
                    <div className="square" data-square="1">{this.state.board[0]}</div>
                    <div className="square" data-square="2">{this.state.board[1]}</div>
                    <div className="square" data-square="3">{this.state.board[2]}</div>
                    <div className="square" data-square="4">{this.state.board[3]}</div>
                    <div className="square" data-square="5">{this.state.board[4]}</div>
                    <div className="square" data-square="6">{this.state.board[5]}</div>
                    <div className="square" data-square="7">{this.state.board[6]}</div>
                    <div className="square" data-square="8">{this.state.board[7]}</div>
                    <div className="square" data-square="9">{this.state.board[9]}</div>
                </div>
            </div>
        );
    }
}
