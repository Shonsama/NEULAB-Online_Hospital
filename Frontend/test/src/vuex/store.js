import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    showDrawer: null
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
    }
  }
})
