import Vue from 'vue'
import Vuetify from 'vuetify'
import App from './App.vue'
import Vuex from 'vuex'
import VueRouter from 'vue-router'
import { sync } from 'vuex-router-sync'
import store from './store' // vuex store instance
import router from './router' // vue-router instance
import 'vuetify/dist/vuetify.min.css'
const unsync = sync(store, router)
Vue.use(VueRouter)
Vue.use(Vuetify)
Vue.use(Vuex)
unsync()

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
