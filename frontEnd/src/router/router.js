import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home'
import Login from '../views/Login'
import Logout from '../views/Logout'
import Join from '../views/Join'
import Board from '../views/Board'
import MentoringDetail from '../views/MentoringDetail'
import Read from '../components/boards/Read'
import Write from '../components/boards/Write'
import Update from '../components/boards/Update'
import Mentoring from '../views/Mentoring'
import Timeline from '../views/Timeline'
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
            path: "/timeline",
            component: Timeline
        },
        {
            path: '/mentoring',
            name: 'Mentoring',
            component: Mentoring
        },
        {
            path:'/mentoring/detail',
            component:MentoringDetail
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
            path: '/logout',
            component: Logout
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
            path: '/board/write/:id',
            component: Update,
            beforeEnter: requireAuth()
        },
        {
            path: '/board/:categoryId/:id',
            component: Read,
            beforeEnter: requireAuth()
        }
    ]
})
