// https://vuex.vuejs.org/en/mutations.html
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
    window.location.replace('/')
  },
  SET_ACCOUNT(state, data) {
    state.account = data
  },
  SET_ACCOUNT_LIST(state, {content}) {
    state.accountList = []
    for (let i = 0; i < content.length; i++) {
      state.accountList.push(content[i])
    }
  },
  SET_PAGE(state, data) {
    state.page = data
  }
}
export default mutations
