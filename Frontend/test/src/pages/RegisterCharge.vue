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
          <div v-if="item.id == 'charge'">
            <charge></charge>
          </div>
          <div v-if="item.id == 'dailySum'">
            <dailySum></dailySum>
          </div>
          <div v-if="item.id == 'dailySumHistory'">
            <dailySumHistory></dailySumHistory>
          </div>
          <!--<router-view name="office"></router-view name="office">-->
        </v-card>
      </v-tab-item>
    </v-tabs>
  </div>
</template>

<script>
import register from '@/pages/RegisterCharge/register'
import charge from '@/pages/RegisterCharge/charge'
import dailySum from '@/pages/RegisterCharge/dailySum'
import dailySumHistory from '@/pages/RegisterCharge/dailySumHistory'

export default {
  name: 'RegisterCharge',
  components: {
    charge,
    register,
    dailySum,
    dailySumHistory
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
      },
      {
        name: '日结历史查询',
        id: 'dailySumHistory'
      }]
    }
  }
}
</script>

<style scoped>

</style>
