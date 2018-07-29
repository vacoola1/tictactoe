import React, {PureComponent} from 'react'
import 'bootstrap/dist/css/bootstrap.css'

import GameList from './GameList'
import games from '../fixtures';

class App extends PureComponent {

    render() {
        return (
            <div className="container">
                <div className="header" style={{'textAlign': 'center'}}>
                    <h1 className="display-4">
                        Tic Tac Toe
                    </h1>
                </div>
                <GameList games={ games.slice().reverse() }/>
            </div>
        )
    }
}

export default App