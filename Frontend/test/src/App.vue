<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-app id="sandbox" :dark="dark">
    <v-toolbar app dark absolute  dense color="primary">
      <v-toolbar-title>
        <v-icon>local_hospital</v-icon>
        NeuHospital
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-menu offset-y>
          <template v-slot:activator="{ on }">
            <v-btn
              flat
              v-on="on"
            >
              我的身份：{{$store.state.user.type}}
              <v-icon
              >
                arrow_drop_down
              </v-icon>
            </v-btn>
          </template>
          <v-list>
            <v-list-tile
              v-for="(item, index) in items"
              :key="index"
              @click="$router.push(item.id)"
            >
              <v-list-tile-title>{{ item.title }}</v-list-tile-title>
            </v-list-tile>
          </v-list>
        </v-menu>
      </v-toolbar-items>
      <v-toolbar-items class="hidden-sm-and-down">
        <v-toolbar-item >
          <v-menu offset-y>
            <template v-slot:activator="{ on }">
              <v-btn
                flat
                icon
                v-on="on"
              >
                <v-icon
                >
                  settings
                </v-icon>
              </v-btn>
            </template>
            <v-card>
              <v-container>
                <v-switch
                  v-model="dark"
                  :label="`黑暗模式`"
                ></v-switch>
              </v-container>
            </v-card>
          </v-menu>
        </v-toolbar-item>
        <v-toolbar-item >
          <v-menu offset-y>
            <template v-slot:activator="{ on2 }">
              <v-btn
                flat
                icon
                v-on="on2"
              >
                <v-icon
                >
                  person
                </v-icon>
              </v-btn>
            </template>
          </v-menu>
        </v-toolbar-item>
      </v-toolbar-items>
    </v-toolbar>
    <v-content>
      <v-card>
        <router-view></router-view>
      </v-card>
    </v-content>
  </v-app>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      item_now: '',
      items: [
        {title: '医院管理员', icon: 'dashboard', id: '/BasicInfo'},
        {title: '挂号收费员', icon: 'account_box', id: '/RegisterCharge'},
        {title: '门诊医生', icon: 'assignment', id: '/Diagnostician'},
        {title: '医技医生', icon: 'credit_card', id: '/Meditech'},
        {title: '药房操作员', icon: 'card_travel', id: '/Pharmacy'},
        {title: '财务管理员', icon: 'calendar_today', id: '/Finance'}
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
