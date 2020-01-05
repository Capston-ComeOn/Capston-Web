import {category} from "../../api";


const state = {
    categoryId: 2,   // 자유 게시판 defalut
    categoryList: []
}

const getters = {}

const actions = {
    FETCH_CATEGORY_LIST({commit}) {
        return category.fetch().then((data) => {
            commit('SET_CATEGORY_LIST', data)
            return data;
        })
    },
}

const mutations = {
    SET_CATEGORY_LIST(state, data) {
        state.categoryList = data
    },
    SET_CATEGORY_ID(state, data) {
        state.categoryId = data
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}