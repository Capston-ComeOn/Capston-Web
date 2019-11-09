import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home'
import Login from '../views/Login'
import Logout from '../views/Logout'
import Join from '../views/Join'
import Board from '../views/boards/Board'
import MentoringDetail from '../views/mentoring/MentoringDetail'
import MentoringWrite from '../views/mentoring/MentoringWrite'
import Read from '../views/boards/ReadBoard'
import Write from '../views/boards/BoardWrite'
import Update from '../views/boards/BoardUpdate'
import Mentoring from '../views/mentoring/Mentoring'
// import MentoringView from '../views/MentoringView'
import Timeline from '../views/Timeline'
import Profile from '../views/Profile'
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
            path: "/timeline",
            component: Timeline,
            beforeEnter: requireAuth()
        },
        {
            path: '/mentoring',
            component: Mentoring,
            beforeEnter: requireAuth()
        },
        {
            path: '/mentoring/detail/:id',
            component: MentoringDetail,
            beforeEnter: requireAuth()
        },
        {
            path: '/mentoring/write',
            component: MentoringWrite,
            beforeEnter: requireAuth()
        },
        {
            path: '/profile',
            component: Profile,
            beforeEnter: requireAuth()
        },
        {
            path: '/logout',
            component: Logout,
            beforeEnter: requireAuth()
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
