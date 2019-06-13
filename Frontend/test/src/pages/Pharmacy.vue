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
