<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-app id="sandbox" :dark="dark">
    <v-toolbar app  absolute  dense>
      <v-toolbar-title>
        <v-icon>local_hospital</v-icon>
        NeuHospital
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-sm-and-down">
        <v-menu offset-y>
          <template v-slot:activator="{ on }">
            <v-btn
              flat
            >
              我的身份
              <v-icon
                v-on="on"
              >
                arrow_drop_down
              </v-icon>
            </v-btn>
          </template>
        </v-menu>
      </v-toolbar-items>
      <v-toolbar-items class="hidden-sm-and-down">
        <v-menu offset-y>
          <template v-slot:activator="{ on }">
            <v-btn
              flat
              icon
            >
              <v-icon
                v-on="on"
              >
                person
              </v-icon>
            </v-btn>
          </template>
        </v-menu>
      </v-toolbar-items>
    </v-toolbar>
    <v-divider />
    <v-content>
      <v-container>
      <router-view></router-view>
      </v-container>
    </v-content>
    <v-footer :inset="footer.inset" app>
      <span class="px-3">&copy; {{ new Date().getFullYear() }}</span>
    </v-footer>
  </v-app>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      item_now: '',
      items: [
        {title: '基础信息维护', icon: 'dashboard', id: '/BasicInfo'},
        {title: '门诊挂号收费', icon: 'account_box', id: '/RegisterCharge'},
        {title: '门诊医生工作站', icon: 'assignment', id: '/Diagnostician'},
        {title: '门诊医技工作站', icon: 'credit_card', id: '/Meditech'},
        {title: '门诊药房工作站', icon: 'card_travel', id: '/Pharmacy'},
        {title: '门诊财务管理', icon: 'calendar_today', id: '/Finance'}
      ],
      right: null,
      dark: false,
      drawers: ['Default (no property)', 'Permanent', 'Temporary'],
      primaryDrawer: {
        model: null,
        type: 'default (no property)'
      },
      footer: {
        inset: false
      }
    }
  },
  methods: {
    send: function () {
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
