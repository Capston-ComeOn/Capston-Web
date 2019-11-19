import {mentoring} from "../../api";

const state = {
    mentoring: null,
    mentoringList: [],
}

const getters = {}

const actions = {
    ADD_MENTORING(_, data) {
        return mentoring.post(data)
            .then((data) => {
                return data
            })
    },
    FETCH_MENTORING_LIST({commit}) {
        return mentoring.fetch()
            .then(data => {
                commit('SET_MENTORING_LIST', data)
                return data;
            })
    },
    FETCH_MENTORING({commit}, data) {
        return mentoring.fetch(data)
            .then(data => {
                commit('SET_MENTORING', data)
                return data;
            })
    },
    ADD_MENTEE(_, data) {
        return mentoring.post(data)
            .then((data) => {
                return data
            })
    }
}

const mutations = {
    SET_MENTORING_LIST(state, {data}) {
        state.mentoringList = []
        if (data.length > 0) {
            state.mentoringList = data
        }
    },

    SET_MENTORING(state, data) {
        state.mentoring = data
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}