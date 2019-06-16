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
          <div v-if="item.id == 'cost'">
            <cost></cost>
          </div>
          <div v-if="item.id == 'dailySum'">
            <dailySum></dailySum>
          </div>
          <div v-if="item.id == 'workSumByDepart'">
            <workSumByDepart></workSumByDepart>
          </div>
          <div v-if="item.id == 'workSumByDoctor'">
            <workSumByDoctor></workSumByDoctor>
          </div>
          <!--<router-view name="office"></router-view name="office">-->
        </v-card>
      </v-tab-item>
    </v-tabs>
  </div>
</template>

<script>
import cost from '@/pages/Finance/cost'
import dailySum from '@/pages/Finance/dailySum'
import workSumByDepart from '@/pages/Finance/workSumByDepart'
import workSumByDoctor from '@/pages/Finance/workSumByDoctor'

export default {
  name: 'Finance',
  components: {
    cost,
    dailySum,
    workSumByDepart,
    workSumByDoctor
  },
  data () {
    return {
      active: null,
      items: [{
        name: '费用科目管理',
        id: 'cost'
      },
      {
        name: '门诊日结核对',
        id: 'dailySum'
      },
      {
        name: '门诊科室工作量统计',
        id: 'workSumByDepart'
      },
      {
        name: '门诊医生工作量统计',
        id: 'workSumByDoctor'
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
      } else if (this.$store.state.user.type === '财务管理员') {
        this.$router.push('/Finance')
      } else {
        this.$router.push('/login')
      }
    }
  }
}
</script>

<style scoped>

</style>
