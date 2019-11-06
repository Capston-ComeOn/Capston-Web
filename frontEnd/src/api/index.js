import axios from 'axios'
import router from '../router/router'
import qs from 'qs'

const domain = 'http://localhost:8090'
const Unauthorized = 401

const onUnauthorized = () => {
    if (localStorage.access_token) {
        delete localStorage.access_token;
    }
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
    },
    fileUpload(path, data) {
        return axios.post(`${domain + path}`, data, {
            headers: {'Content-Type': 'multipart/form-data'}
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

export const account = {
    fetch(data) {
        if (data && data.name.length >= 1) {
            return request.post(`/api/accounts/search/name`, data).then((data) => data)
        }
        return request.get(`/api/accounts/login`).then((data) => data)
    },
    imageUpload(data) {
        return request.fileUpload(`/api/accounts/files`, data.formData)
    }
}

export const article = {
    fetch(data) {
        if (data.id) {
            return request.get(`/api/article/${data.categoryId}/${data.id}`).then(({data}) => data)
        }
        if (data.size) {
            return request.get(`/api/article/${data.categoryId}?page=${data.page}&size=${data.size}&sort=id,DESC`).then(({data}) => data)
        }
        return request.get(`/api/article/${data.categoryId}/size`).then(({data}) => data)
    },
    post(data) {
        return request.post(`/api/article/${data.categoryId}`, data).then((data) => data)
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


export const category = {
    fetch() {
        return request.get(`/api/article/category`).then(({data}) => data)
    },
}

export const message = {
    fetch(data) {
        if (!data) {
            return request.get(`/api/message`).then(({data}) => data)
        }
        return request.get(`/api/message/${data.from}`).then(({data}) => data)

    },
    post(data) {
        return request.post(`/api/message/${data.from}`, data).then((data) => data)
    }
}