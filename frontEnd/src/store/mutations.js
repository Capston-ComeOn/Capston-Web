import {setAuthInHeader} from "../api";

const mutations = {
    LOGIN(state, {access_token}) {
        if (!access_token) return
        state.access_token = access_token
        localStorage.access_token = access_token
        setAuthInHeader(access_token)
    },
    LOGOUT(state) {
        state.access_token = null;
        delete localStorage.access_token;
        delete localStorage.loginAccountId;
        setAuthInHeader(null)
    }
}

export default mutations
