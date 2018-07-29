import React, {Component, PureComponent} from 'react'
import PlayGrid from '../PlayGrid/index'

export default class Game extends PureComponent {

    render() {
        const {game, isOpen, onButtonClick} = this.props
        // const style = {width: '80%', high:'80%'}
        const body = isOpen && <section className="card-text"><PlayGrid/></section>
        return (
            <div className='card border-primary mb-3'>
                <div className="card-header">
                    <h2>
                        {game.name} / {game.status}
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
