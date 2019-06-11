import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    doctor: '',
    user: {
      id: '',
      department_id: '',
      type: ''
    },
    isLogin: false
  },
  getters: {
    getIsShowDrawer: function (state, data) {
      return state.showDrawer
    }
  },
  mutations: {
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
