/**
 * Vuex
 *
 * @library
 *
 * https://vuex.vuejs.org/en/
 */

// Lib imports
import Vue from 'vue'
import Vuex from 'vuex'
import state from './state'
import mutations from './mutations'
import actions from './actions'
import getters from './getters'
import modules from './modules'

Vue.use(Vuex)

// Create a new store
const store = new Vuex.Store({
  actions,
  modules,
  getters,
  mutations,
  state,
})

const {access_token} = localStorage
store.commit('LOGIN', {access_token: access_token})
export default store
