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
          <div v-if="item.id == 'inspect'">
            <inspect></inspect>
          </div>
          <div v-if="item.id == 'check'">
            <check></check>
          </div>
          <div v-if="item.id == 'dispose'">
            <dispose></dispose>
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
import dispose from '@/pages/Meditech/dispose'
import check from '@/pages/Meditech/check'
import inspect from '@/pages/Meditech/inspect'
import itemManage from '@/pages/Meditech/itemManage'
export default {
  name: 'Meditech',
  components: {
    dispose,
    check,
    inspect,
    itemManage
  },
  data () {
    return {
      active: null,
      items: [{
        name: '患者检查',
        id: 'inspect'
      },
      {
        name: '患者检验',
        id: 'check'
      },
      {
        name: '患者处置',
        id: 'dispose'
      },
      {
        name: '医技管理',
        id: 'itemManage'
      }]
    }
  }
}
</script>

<style scoped>

</style>
