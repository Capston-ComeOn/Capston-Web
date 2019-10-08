import {auth, article, category} from '../api'

const actions = {
    LOGIN({commit}, {username, password, grant_type}) {
        return auth.login({username, password, grant_type})
            .then((data) => {
                commit('LOGIN', data)
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
        return article.fetch({categoryId}).then(data => data)
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
    }
}

export default actions

