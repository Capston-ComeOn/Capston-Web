import {auth, account, article, category} from '../api'

const actions = {
    LOGIN({commit}, {username, password, grant_type}) {
        return auth.login({username, password, grant_type})
            .then((data) => {
                commit('LOGIN', data)
            })
    },
    FETCH_ACCOUNT({commit}, {id}) {
        return account.fetch({id})
            .then(data => {
                commit('SET_ACCOUNT', data)
                return data;
            })
    },
    FETCH_ACCOUNT_LIST({commit}, {size, page}) {
        return account.fetch({size, page})
            .then((data) => {
                commit('SET_ACCOUNT_LIST', data)
            })
    },
    FETCH_ACCOUNT_SIZE(_) {
        return account.fetch().then(data => data)
    },
    FETCH_CATEGORY_LIST({commit}) {
        return category.fetch().then((data) => {
            commit('SET_CATEGORY_LIST', data)
        })
    },
    FETCH_ARTICLE({commit}, {id, categoryId}) {
        return article.fetch({id, categoryId})
            .then(data => {
                commit('SET_ARTICLE', data)
                return data;
            })
    },
    FETCH_ARTICLE_SIZE(_, {categoryId}) {
        return article.fetch({categoryId}).then(data => data)
    },
    FETCH_ARTICLE_LIST({commit}, {categoryId, size, page}) {
        return article.fetch({categoryId, size, page}).then((data) => {
            commit('SET_ARTICLE_LIST', data)
        })
    },
    DESTROY_ARTICLE(_, {id}) {
        return article.destroy({id})
            .then(data => data)
    }

}


export default actions

