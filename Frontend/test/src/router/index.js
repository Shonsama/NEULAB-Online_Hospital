import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/pages/HelloWorld'
// import RegisterCharge from '@/pages/RegisterCharge'
// import BasicInfoManage from '@/pages/BasicInfoManage'
// import Diagnostician from '@/pages/Diagnostician'
// import Meditech from '@/pages/Meditech'
// import Pharmacy from '@/pages/Pharmacy'
// import Finance from '@/pages/Finance'
// import error from '@/pages/error'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: resolve => require(['@/pages/HelloWorld'], resolve),
      alias: '/login'
    },
    {
      path: '/BasicInfo',
      name: 'BasicInfoManage',
      component: resolve => require(['@/pages/BasicInfoManage'], resolve)
    },
    {
      path: '/RegisterCharge',
      name: 'RegisterCharge',
      component: resolve => require(['@/pages/RegisterCharge'], resolve)
    },
    {
      path: '/Diagnostician',
      name: 'Diagnostician',
      component: resolve => require(['@/pages/Diagnostician'], resolve)
    },
    {
      path: '/Meditech',
      name: 'Meditech',
      component: resolve => require(['@/pages/Meditech'], resolve)
    },
    {
      path: '/Pharmacy',
      name: 'Pharmacy',
      component: resolve => require(['@/pages/Pharmacy'], resolve)
    },
    {
      path: '/Finance',
      name: 'Finance',
      component: resolve => require(['@/pages/Finance'], resolve)
    }
  ]
})
export default router
