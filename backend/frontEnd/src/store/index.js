import Vuex from 'vuex'
import Vue from 'vue'
import state from './state'
import mutations from './mutations'
import actions from './actions'
import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
    state,
    mutations,
    getters,
    actions
})

const {access_token, email} = localStorage
store.commit('LOGIN', {access_token: access_token})
store.commit('LOGIN', {email: email})
export default store
