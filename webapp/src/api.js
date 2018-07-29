import config from './config';
import axios from "axios/index";

function loadGames() {
    return fetch(`/api/v1/games`);
}

function fetch(url, options = {}) {
    return axios.get(config.api.host + url).then(res => {
        return res.data;
    }).catch(reason => {
        console.log('=== err', reason);

    });
}

export default {
    loadGames
};

