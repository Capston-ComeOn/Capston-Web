import Vuex from 'vuex'
import Vue from 'vue'
import account from "./modules/account";
import article from './modules/article'
import category from './modules/category'
import mentoring from './modules/mentoring'
import message from './modules/message'
import project from "./modules/project";

Vue.use(Vuex)

const store = new Vuex.Store({
    modules: {
        account,
        article,
        category,
        mentoring,
        message,
        project
    }
})

const {access_token, email} = localStorage
store.commit('account/LOGIN', {access_token: access_token})
store.commit('account/LOGIN', {email: email})

export default store
