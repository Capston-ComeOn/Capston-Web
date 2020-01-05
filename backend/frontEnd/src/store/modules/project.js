import {project} from "../../api";

const state = {
    project: [],
    projectList: []
}

const getters = {}

const actions = {
    ADD_PROJECT(_, data) {
        return project.post(data)
            .then((data) => {
                return data
            })
    },
    FETCH_PROJECT_LIST({commit}) {
        return project.fetch()
            .then(data => {
                commit('SET_PROJECT_LIST', data)
                return data;
            })
    },
    FETCH_PROJECT({commit}, data) {
        return project.fetch(data)
            .then(data => {
                commit('SET_PROJECT', data)
                return data;
            })
    },
    ADD_APPLY(_, data) {
        console.log(data)
        return project.post(data)
            .then((data) => {
                return data
            })
    }
}

const mutations = {
    SET_PROJECT_LIST(state, {data}) {
        state.projectList = []
        if (data.length > 0) {
            state.projectList = data
        }
    },
    SET_PROJECT(state, data) {
        state.project = data
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}