import {auth, article} from '../api'

const actions = {
    LOGIN({commit}, {username, password, grant_type}) {
        return auth.login({username, password, grant_type})
            .then((data) => {
                commit('LOGIN', data)
            })
    },

    ADD_ARTICLE(_, {title, contents}) {
        return article.post({title, contents})
            .then((response) => (response))
    },
    UPDATE_ARTICLE(_, {id, title, contents}) {
        return article.update({id, title, contents})
            .then((response) => (response))
    },
    FETCH_ARTICLE({commit}, {id}) {
        return article.fetch({id})
            .then(data => {
                commit('SET_ARTICLE', data)
                return data;
            })
    },
    FETCH_ARTICLE_LIST({commit}, {size, page}) {
        return article.fetch({size, page})
            .then(data => {
                commit('SET_ARTICLE_LIST', data)
            })
    },
    DESTROY_ARTICLE(_, {id}) {
        return article.destroy({id})
            .then(data => data)
    }
}

export default actions

