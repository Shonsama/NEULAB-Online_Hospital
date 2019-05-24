<template>
  <div>
    <v-tabs
      v-model="active"
    >
      <v-tab
        v-for="item in items"
        :key="item"
        ripple
      >
        {{item.name}}
      </v-tab>
      <v-tab-item
        v-for="item in items"
        :key="item"
      >
        <v-card flat>
          <div v-if="item.id == 'register'">
            <register></register>
          </div>
          <div v-if="item.id == 'office'">
            <office></office>
          </div>
          <div v-if="item.id == 'user'">
            <user></user>
          </div>
          <div v-if="item.id == 'registerLevel'">
            <registerLevel></registerLevel>
          </div>
          <div v-if="item.id == 'nonMedcine'">
            <non-medicine></non-medicine>
          </div>
          <div v-if="item.id == 'workforce'">
            <workforce></workforce>
          </div>
          <div v-if="item.id == 'patCate'">
            <pay-cate></pay-cate>
          </div>
          <!--<router-view name="office"></router-view name="office">-->
        </v-card>
      </v-tab-item>
    </v-tabs>
  </div>
</template>

<script>
import register from '@/pages/RegisterCharge/register'
export default {
  name: 'RegisterCharge',
  components: {
    register
  },
  data () {
    return {
      active: null,
      items: [{
        name: '门诊挂号',
        id: 'register'
      },
      {
        name: '收费',
        id: 'charge'
      },
      {
        name: '收费员日结',
        id: 'dailySum'
      }]
    }
  }
}
</script>

<style scoped>

</style>
