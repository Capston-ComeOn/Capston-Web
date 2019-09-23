import axios from 'axios'
import router from '../router/router'
import qs from 'qs'

const domain = 'http://localhost:8090'
const Unauthorized = 401
const onUnauthorized = () => {
    router.push(`/login?returnPath=${encodeURIComponent(location.pathname)}`)
}

const request = {
    get(path) {
        return axios.get(`${domain + path}`).catch(({response}) => {
            const {status} = response
            console.log(status)
            if (status === Unauthorized) return onUnauthorized()
            throw Error(response)
        })
    },
    post(path, data) {
        return axios.post(`${domain + path}`, data)
    },
    put(path, data) {
        return axios.put(`${domain + path}`, data)
    },
    delete(path) {
        return axios.delete(`${domain + path}`)
    },
    login(path, data) {
        return axios.post(`${domain + path}`, qs.stringify(data), {
            auth: {
                username: 'id',
                password: 'secret'
            }
        })
    }
}

export const setAuthInHeader = token => {
    axios.defaults.headers.common['Authorization'] = token ? `Bearer ${token}` : null;
}

export const auth = {
    login({username, password, grant_type}) {
        return request.login('/oauth/token', {username, password, grant_type})
            .then(({data}) => data)
    },
}

export const article = {
    fetch(data) {
        if (!data) {
            return request.get(`/api/article/size`).then(({data}) => data)
        }
        if (data.id) {
            return request.get(`/api/article/${data.id}`).then(({data}) => data)
        }
        if (data.size) {
            return request.get(`/api/article?page=${data.page}&size=${data.size}&sort=id,DESC`).then(({data}) => data)
        }
    },
    post(data) {
        return request.post('/api/article', data).then((data) => data)
    },
    update(data) {
        return request.put(`/api/article/${data.id}`, {
            title: data.title,
            contents: data.contents
        }).then(({data}) => data)
    },
    destroy(data) {
        return request.delete(`/api/article/${data.id}`).then(({data}) => data)
    }
}
