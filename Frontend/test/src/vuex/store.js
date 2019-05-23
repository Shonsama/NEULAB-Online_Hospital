import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    isPostGraduate: false,
    alert: {
      isShown: false,
      content: ''
    }
  },
  getters: {
    getIsPostGraduate: function (state, data) {
      state.isPostGraduate = sessionStorage.getItem('type') === '1'
      return state.isPostGraduate
    }
  },
  mutations: {
    setIsPostGraduate: function (state, data) {
      state.isPostGraduate = data
      if (data === true) {
        sessionStorage.setItem('type', '1')
      } else {
        sessionStorage.setItem('type', '0')
      }
    },
    setAlert: function (state, content) {
      state.alert.content = content
      state.alert.isShown = true
    }
  }
})
