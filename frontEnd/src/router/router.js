import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home'
import Login from '../views/Login'
import Join from '../views/Join'
import Board from '../views/Board'
import Read from '../components/boards/Read'
import Write from '../components/boards/Write'
import store from '../store'
Vue.use(Router)

const requireAuth = () => (from, to, next) => {
    store.state.access_token ?
        next() :
        next(`/login?returnPath=${encodeURIComponent(from.path)}`)
}

export default new Router({
    mode: 'history',
    routes: [
        {
            path: "/",
            name: 'Home',
            component: Home
        },
        {
            path: '/login',
            name: 'Login',
            component: Login
        },
        {
            path: '/join',
            component: Join
        },
        {
            path: '/board',
            component: Board,
            beforeEnter: requireAuth()
        },
        {
            path: '/board/write',
            component: Write,
            beforeEnter: requireAuth()
        },
        {
            path: '/board/:id',
            component: Read,
            beforeEnter: requireAuth()
        }
    ]
})
