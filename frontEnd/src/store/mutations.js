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
    SET_CATEGORY_LIST(state, data) {
        state.categoryList = data
    },
    SET_CATEGORY_ID(state, data) {
        state.categoryId = data
    },
    SET_PAGE(state, data) {
        state.page = data
    }
}

export default mutations
