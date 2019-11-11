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
        delete localStorage.email;
        setAuthInHeader(null)
        window.location.replace("/")
    },
    CLEAR_ACCESS_TOKEN(state){
        console.log('aaa')
      state.access_token=null;
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
    SET_CATEGORY_LIST(state, data) {
        state.categoryList = data
    },
    SET_CATEGORY_ID(state, data) {
        state.categoryId = data
    },
    SET_PAGE(state, data) {
        state.page = data
    },
    SET_MINI(state, data) {
        state.mini = data
    },
    SET_ACCOUNT_LIST(state, {data}) {
        state.accountList = []
        if (data.length > 0) {
            state.accountList = data
        }
    },
    SET_MESSAGE_LIST(state, data) {
        state.messageList = []
        if (data.length > 0) {
            state.messageList = data
        }
    },
    SET_RECENT_MESSAGE_LIST(state, data) {
        state.recentMessageList = []
        if (data.length > 0) {
            state.recentMessageList = data
        }
    },
    CLEAR_MESSAGE_LIST(state) {
        state.messageList = []
    },
    SET_LOGIN_ACCOUNT(state, {data}) {
        state.account = data
    },
    SET_MENTORING_LIST(state, {data}) {
        state.mentoringList = []
        if (data.length > 0) {
            state.mentoringList = data
        }
    },
    CLEAR_ACCOUNT_FILE(state) {
        state.account.accountFile = []
    },
    SET_MENTORING(state, data) {
        state.mentoring = data
    }
}
export default mutations
