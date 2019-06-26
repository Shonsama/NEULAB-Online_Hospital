// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuetify from 'vuetify'
import Vuex from 'vuex'
import store from './vuex/store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import 'vuetify/dist/vuetify.min.css'
import ECharts from 'vue-echarts' // refers to components/ECharts.vue in webpack
// import ECharts modules manually to reduce bundle size
import 'echarts/lib/chart/bar'
import 'echarts/lib/component/tooltip'
// If you want to use ECharts extensions, just import the extension package and it will work
// Taking ECharts-GL as an example:
// You only need to install the package with `npm install --save echarts-gl` and import it as follows
import 'echarts-gl'
import JsonExcel from 'vue-json-excel'
import print from './print.js'

Vue.component('downloadExcel', JsonExcel)
// let instance = axios.create({
//   transfor
// mRequest: [function (data) {
//     data = Qs.stringify(data)
//     return data
//   }],
//   headers: {'Content-Type': 'application/x-www-form-urlencoded'}
// })
//
// Vue.use(axios, instance)
// Vue.prototype.HOME = 'http://www.wangpeng.pub'
Vue.prototype.HOME = 'http://localhost:8080'
// Vue.prototype.HOME = 'http://lcoalhost:80/'
Vue.prototype.$http = axios
Vue.config.productionTip = false
Vue.component('v-chart', ECharts)

Vue.use(Vuetify)
Vue.use(Vuex)
Vue.use(ElementUI)
Vue.use(print)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
