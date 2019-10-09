import {auth, account} from '../api'

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

}


export default actions

