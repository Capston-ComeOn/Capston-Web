import {auth} from '../api'

const actions = {
    LOGIN({commit}, {username, password, grant_type}) {
        return auth.login({username, password, grant_type})
            .then((data) => {
                commit('LOGIN', data)
            })
    }
}

export default actions

