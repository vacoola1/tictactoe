import config from './config';
import axios from "axios/index";

function loadGames() {
    return get(`/api/v1/games`);
}

function createGame(game) {
    return post(`/api/v1/games`, game);
}

function loadMoves(game) {
    return get(`/api/v1/games/${game.id}/moves`);
}

function doNextMove(game, cell) {
    return post(`/api/v1/games/${game.id}/moves/${cell}`, game);
}

function get(url, options = {}) {
    return axios.get(config.api.host + url).then(res => {
        return res.data;
    }).catch(reason => {
        console.log('=== err', reason);
    });
}

function post(url, data = {}) {
    return axios.post(config.api.host + url, data).then(res => {
        return res.data;
    }).catch(reason => {
        console.log('=== err', reason);
    });
}

export default {
    loadGames,
    createGame,
    doNextMove,
    loadMoves
};

