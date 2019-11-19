import {article} from "../../api";

const state = {
    article: null,
    articleList: [],
    page: 0,
    mini: true
}

const getters = {}

const actions = {
    ADD_ARTICLE(_, {title, contents, categoryId}) {
        return article.post({title, contents, categoryId})
            .then((response) => (response))
    },
    UPDATE_ARTICLE(_, {id, title, contents}) {
        return article.update({id, title, contents})
            .then((response) => (response))
    },
    DESTROY_ARTICLE(_, {id}) {
        return article.destroy({id})
            .then(data => data)
    },
    FETCH_ARTICLE({commit}, {id, categoryId}) {
        return article.fetch({id, categoryId})
            .then(data => {
                commit('SET_ARTICLE', data)
                return data;
            })
    },
    FETCH_ARTICLE_LIST_RETURN(_, {categoryId, size, page}) {
        return article.fetch({categoryId, size, page}).then((data) => data)
    },
    FETCH_ARTICLE_SIZE(_, {categoryId}) {
        return article.fetch({categoryId}).then(data => {
            return data;
        })
    },
    FETCH_ARTICLE_LIST({commit}, {categoryId, size, page}) {
        return article.fetch({categoryId, size, page}).then((data) => {
            commit('SET_ARTICLE_LIST', data)
        })
    }
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
    SET_ARTICLE(state, data) {
        state.article = data
    },
    SET_ARTICLE_LIST(state, content) {
        state.articleList = []
        for (let i = 0; i < content.length; i++) {
            state.articleList.push(content[i])
        }
    },
    SET_PAGE(state, data) {
        state.page = data
    },
    SET_MINI(state, data) {
        state.mini = data
    },
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}