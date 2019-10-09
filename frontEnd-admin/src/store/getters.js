const getters = {
  isAuthenticated(state) {
    return !!state.access_token
  }
}

export default getters
