import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    doctor: '',
    token: '',
    user: {
      id: '1',
      department_id: 'AZBK',
      type: '挂号收费员'
    },
    isLogin: false
  },
  getters: {
    getIsShowDrawer: function (state, data) {
      return state.showDrawer
    }
  },
  mutations: {
    set_token (state, token) {
      state.token = token
      sessionStorage.token = token
    },
    del_token (state) {
      state.token = ''
      sessionStorage.removeItem('token')
    },
    showDrawer: function (state) {
      var stateNow = state.showDrawer
      state.showDrawer = !stateNow
    },
    saveForm: function (state) {
      var stateNow = state.showDrawer
      state.showDrawer = !stateNow
    },
    login: function (state) {
      state.isLogin = true
    }
  }
})
