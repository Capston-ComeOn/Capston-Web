import {auth, article, category, message, account, mentoring} from '../api'

const actions = {
    LOGIN({commit, dispatch}, {username, password, grant_type}) {
        return auth.login({username, password, grant_type})
            .then((data) => {
                const {access_token} = data
                commit('LOGIN', {access_token})
                dispatch('FETCH_LOGIN_ACCOUNT')
            })
    },
    ADD_ARTICLE(_, {title, contents, categoryId}) {
        return article.post({title, contents, categoryId})
            .then((response) => (response))
    },
    UPDATE_ARTICLE(_, {id, title, contents}) {
        return article.update({id, title, contents})
            .then((response) => (response))
    },
    FETCH_ARTICLE({commit}, {id, categoryId}) {
        return article.fetch({id, categoryId})
            .then(data => {
                commit('SET_ARTICLE', data)
                return data;
            })
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
    },
    FETCH_CATEGORY_LIST({commit}) {
        return category.fetch().then((data) => {
            commit('SET_CATEGORY_LIST', data)
        })
    },
    DESTROY_ARTICLE(_, {id}) {
        return article.destroy({id})
            .then(data => data)
    },
    FETCH_ACCOUNT_LIST({commit}, {name}) {
        return account.fetch({name})
            .then(data => {
                commit('SET_ACCOUNT_LIST', data)
            })
    },
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
    FETCH_LOGIN_ACCOUNT({commit}) {
        return account.fetch()
            .then(data => {
                commit('SET_LOGIN_ACCOUNT', data)
            })
    },
    ADD_ACCOUNT_FILE({commit}, data) {
        return account.imageUpload(data)
            .then(() => {
                commit('CLEAR_ACCOUNT_FILE')
            })
    },
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

export default actions

