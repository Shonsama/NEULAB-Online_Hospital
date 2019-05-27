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
          <div v-if="item.id == 'medicine'">
            <medicine></medicine>
          </div>
          <div v-if="item.id == 'itemManage'">
            <itemManage></itemManage>
          </div>
          <!--<router-view name="office"></router-view name="office">-->
        </v-card>
      </v-tab-item>
    </v-tabs>
  </div>
</template>

<script>
import medicine from '@/pages/Pharmacy/medicine'
import itemManage from '@/pages/Pharmacy/itemManage'

export default {
  name: 'Pharmacy',
  components: {
    medicine,
    itemManage
  },
  data () {
    return {
      active: null,
      items: [{
        name: '门诊发药',
        id: 'medicine'
      },
      {
        name: '药品管理',
        id: 'itemManage'
      }]
    }
  }
}
</script>

<style scoped>

</style>
