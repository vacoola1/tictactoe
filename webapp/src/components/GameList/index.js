import React, {PureComponent} from 'react'
import Game from '../Game'
import NewGameForm from '../NewGameForm'
import api from "../../api";

export default class GameList extends PureComponent {

    state = {
        openGameId: null,
        games: []
    }

    componentDidMount() {
        this.updateGames();
    }

    render() {
        const gameElements = this.state.games.map((game) => (
            <li key={game.id} style={{"listStyle": "none"}}>
                <Game game={game} isOpen={this.state.openGameId === game.id}
                       onButtonClick={this.handleClickButton.bind(this, game.id)}/>
            </li>
        ))
        return (
            <div className="row">
                <div className="cell col-sm">
                    <div>
                        <NewGameForm onCreateClick={this.updateGames.bind(this)}/>
                    </div>
                    <div>
                        <ul style={{paddingLeft:0}}>
                            {gameElements}
                        </ul>
                    </div>
                </div>
            </div>
        )
    }

    handleClickButton = (gameId) => {
        this.setState({
            openGameId: this.state.openGameId === gameId ? null : gameId
        })
    }

    updateGames = () => {
        api.loadGames().then(games => {
            console.log('=== res', games);
            this.setState({
                games: games
            });
        })
    }
}