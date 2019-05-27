import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/pages/HelloWorld'
import RegisterCharge from '@/pages/RegisterCharge'
import BasicInfoManage from '@/pages/BasicInfoManage'
import Diagnostician from '@/pages/Diagnostician'
import Meditech from '@/pages/Meditech'
import Pharmacy from '@/pages/Pharmacy'
import Finance from '@/pages/Finance'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
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
