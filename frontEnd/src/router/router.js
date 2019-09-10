import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home'
import Login from '../views/Login'
import Join from '../views/Join'
import Board from '../views/Board'
import Read from '../components/boards/Read'
import Write from '../components/boards/Write'

Vue.use(Router)

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
            component: Board
        },
        {
            path: '/board/write',
            component: Write
        },
        {
            path: '/board/:id',
            component: Read
        }
    ]
})
