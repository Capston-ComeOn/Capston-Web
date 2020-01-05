import {message} from "../../api";


const state = {
    recentMessageList: [],
    messageList: [],
}

const getters = {}

const actions = {
    ADD_MESSAGE(_, {from, content}) {
        return message.post({from, content})
            .then(data => data)
    },
    FETCH_MESSAGE_LIST({commit}, {from}) {
        return message.fetch({from})
            .then(data => {
                commit('SET_MESSAGE_LIST', data)
            })
    },
    FETCH_RECENT_CONTACT_LIST({commit},) {
        return message.fetch()
            .then(data => {
                commit('SET_RECENT_MESSAGE_LIST', data)
            })
    },
}

const mutations = {
    SET_MESSAGE_LIST(state, data) {
        state.messageList = []
        if (data.length > 0) {
            state.messageList = data
        }
    },
    SET_RECENT_MESSAGE_LIST(state, data) {
        state.recentMessageList = []
        if (data.length > 0) {
            state.recentMessageList = data
        }
    },
    CLEAR_MESSAGE_LIST(state) {
        state.messageList = []
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}