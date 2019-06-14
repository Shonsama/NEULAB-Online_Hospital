<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
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
          <div v-if="item.id == 'diagnosis'">
            <diagnosis></diagnosis>
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
import office from '@/pages/BasicInfoManage/office'
import user from '@/pages/BasicInfoManage/user'
import registerLevel from '@/pages/BasicInfoManage/registerLevel'
import payCate from '@/pages/BasicInfoManage/payCate'
import nonMedicine from '@/pages/BasicInfoManage/nonMedicine'
import diagnosis from '@/pages/BasicInfoManage/diagnosis'
import workforce from '@/pages/BasicInfoManage/workforce'

export default {
  data () {
    return {
      active: null,
      items: [{
        name: '科室管理',
        id: 'office'
      },
      {
        name: '用户管理',
        id: 'user'
      },
      {
        name: '挂号级别管理',
        id: 'registerLevel'
      },
      {
        name: '常数类别管理',
        id: 'patCate'
      },
      {
        name: '诊断信息管理',
        id: 'diagnosis'
      },
      {
        name: '非药品收费项目管理',
        id: 'nonMedcine'
      },
      {
        name: '医生排班管理',
        id: 'workforce'
      }
      ]
    }
  },
  components: {
    office,
    user,
    registerLevel,
    payCate,
    nonMedicine,
    diagnosis,
    workforce
  },
  mounted: function () {
    this.Check()
  },
  computed: {
  },
  watch: {
  },
  methods: {
    Check: function () {
      if (!this.$store.state.isLogin) {
        this.$router.push('/login')
      } else if (this.$store.state.user.type === '医院管理员') {
        this.$router.push('/BasicInfoManage')
      } else {
        this.$router.push('/login')
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }
</style>
