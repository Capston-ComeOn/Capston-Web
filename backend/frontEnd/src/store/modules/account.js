import {account, auth, setAuthInHeader} from "../../api";


const state = {
    account: [],
    accountList: [],
    access_token: null
}
const getters = {
    isAuthenticated(state) {
        return !!state.access_token
    }
}

const actions = {
    LOGIN({commit, dispatch}, {username, password, grant_type}) {
        return auth.login({username, password, grant_type})
            .then((data) => {
                const {access_token} = data
                commit('LOGIN', {access_token})
                dispatch('FETCH_LOGIN_ACCOUNT')
            })
    },
    FETCH_LOGIN_ACCOUNT({commit}) {
        return account.fetch()
            .then(data => {
                commit('SET_LOGIN_ACCOUNT', data)
            })
    },
    ADD_ACCOUNT(_, data) {
        return account.post(data)
            .then((response) => response)
    },
    FETCH_ACCOUNT_LIST({commit}, {name}) {
        return account.fetch({name})
            .then(data => {
                commit('SET_ACCOUNT_LIST', data)
            })
    },
    ADD_ACCOUNT_FILE({commit}, data) {
        return account.imageUpload(data)
            .then(() => {
                commit('CLEAR_ACCOUNT_FILE')
            })
    },
}

const mutations = {
    SET_ACCOUNT_LIST(state, {data}) {
        state.accountList = []
        if (data.length > 0) {
            state.accountList = data
        }
    },
    SET_LOGIN_ACCOUNT(state, {data}) {
        state.account = data
    },
    CLEAR_ACCOUNT_FILE(state) {
        state.account.accountFile = []
    },
    LOGIN(state, {access_token}) {
        if (!access_token) return
        state.access_token = access_token
        localStorage.access_token = access_token
        setAuthInHeader(access_token)
    },
    LOGOUT(state) {
        state.access_token = null;
        delete localStorage.access_token;
        delete localStorage.email;
        setAuthInHeader(null)
        window.location.replace("/")
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}