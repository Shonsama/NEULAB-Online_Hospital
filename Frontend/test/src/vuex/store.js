import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    doctor: '',
    token: '',
    user: {},
    isLogin: false
  },
  getters: {
    getIsShowDrawer: function (state, data) {
      return state.showDrawer
    }
  },
  mutations: {
    set_user (state, user) {
      state.user = user
    },
    set_token (state, token) {
      state.token = token
      sessionStorage.token = token
    },
    del_token (state) {
      state.token = ''
      sessionStorage.removeItem('token')
    },
    login: function (state) {
      state.isLogin = true
    },
    logout: function (state) {
      state.isLogin = false
      state.user = ''
    }
  }
})
