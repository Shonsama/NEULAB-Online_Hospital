import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/pages/HelloWorld'
// const HelloWorld = resolve => require(['@/pages/HelloWorld'], resolve)
const RegisterCharge = resolve => require(['@/pages/RegisterCharge'], resolve)
const BasicInfoManage = resolve => require(['@/pages/BasicInfoManage'], resolve)
const Diagnostician = resolve => require(['@/pages/Diagnostician'], resolve)
const Meditech = resolve => require(['@/pages/Meditech'], resolve)
const Pharmacy = resolve => require(['@/pages/Pharmacy'], resolve)
const Finance = resolve => require(['@/pages/Finance'], resolve)

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld,
      alias: '/login'
    },
    {
      path: '/BasicInfo',
      name: 'BasicInfoManage',
      component: BasicInfoManage
    },
    {
      path: '/RegisterCharge',
      name: 'RegisterCharge',
      component: RegisterCharge
    },
    {
      path: '/Diagnostician',
      name: 'Diagnostician',
      component: Diagnostician
    },
    {
      path: '/Meditech',
      name: 'Meditech',
      component: Meditech
    },
    {
      path: '/Pharmacy',
      name: 'Pharmacy',
      component: Pharmacy
    },
    {
      path: '/Finance',
      name: 'Finance',
      component: Finance
    }
  ]
})
export default router
