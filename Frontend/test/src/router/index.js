import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/pages/HelloWorld'
import RegisterCharge from '@/pages/RegisterCharge'
import office from '@/pages/BasicInfoManage/office'
import user from '@/pages/BasicInfoManage/user'
import registerLevel from '@/pages/BasicInfoManage/registerLevel'
import payCate from '@/pages/BasicInfoManage/payCate'
import nonMedicine from '@/pages/BasicInfoManage/nonMedicine'
import diagnosis from '@/pages/BasicInfoManage/diagnosis'
import workforce from '@/pages/BasicInfoManage/workforce'
import BasicInfoManage from '@/pages/BasicInfoManage'

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
      component: BasicInfoManage,
      children: [
        {path: '/BasicInfo/office', component: office},
        {path: '/BasicInfo/user', component: user},
        {path: '/BasicInfo/registerLevel', component: registerLevel},
        {path: '/BasicInfo/payCate', component: payCate},
        {path: '/BasicInfo/nonMedicine', component: nonMedicine},
        {path: '/BasicInfo/diagnosis', component: diagnosis},
        {path: '/BasicInfo/workforce', component: workforce}
      ]
    },
    {
      path: '/RegisterCharge',
      name: 'RegisterCharge',
      component: RegisterCharge,
      children: [
        {path: '/RegisterCharge/office', component: office},
        {path: '/RegisterCharge/user', component: user},
        {path: '/RegisterCharge/registerLevel', component: registerLevel},
        {path: '/RegisterCharge/payCate', component: payCate},
        {path: '/RegisterCharge/nonMedicine', component: nonMedicine},
        {path: '/RegisterCharge/diagnosis', component: diagnosis},
        {path: '/RegisterCharge/workforce', component: workforce}
      ]
    }
  ]
})
