import {setAuthInHeader} from "../api";

const mutations = {
    LOGIN(state, {access_token}) {
        if (!access_token) return
        state.access_token = access_token
        localStorage.access_token = access_token
        setAuthInHeader(access_token)
    },
    LOGOUT(state) {
        state.access_token = null;
        delete localStorage.access_token;
        delete localStorage.loginAccountId;
        setAuthInHeader(null)
    },
    SET_ARTICLE(state, data) {
        state.article = data
    },
    SET_ARTICLE_LIST(state, {content}) {
        state.articleList = []
        for (let i = 0; i < content.length; i++) {
            state.articleList.push(content[i])
        }
    },
}

export default mutations
