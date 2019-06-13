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
  },
  mounted: function () {
    this.Check()
  },
  methods: {
    Check: function () {
      if (!this.$store.state.isLogin) {
        this.$router.push('/login')
      } else if (this.$store.state.user.type === '门诊医生') {
        this.$router.push('/Diagnostician')
      } else if (this.$store.state.user.type === '医技医生') {
        this.$router.push('/Meditech')
      } else if (this.$store.state.user.type === '医院管理员') {
        this.$router.push('/BasicInfoManage')
      } else if (this.$store.state.user.type === '挂号收费员') {
        this.$router.push('/RegisterCharge')
      } else if (this.$store.state.user.type === '药房操作员') {
        this.$router.push('/Pharmacy')
      } else if (this.$store.state.user.type === '财务管理员') {
        this.$router.push('/Finance')
      }
    }
  }
}
</script>

<style scoped>

</style>
