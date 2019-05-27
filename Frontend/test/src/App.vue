<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-app id="sandbox" :dark="dark">
    <v-navigation-drawer
      v-model="primaryDrawer.model"
      :permanent="primaryDrawer.type === 'permanent'"
      :temporary="primaryDrawer.type === 'temporary'"
      :clipped="primaryDrawer.clipped"
      :floating="primaryDrawer.floating"
      :mini-variant="primaryDrawer.mini"
      absolute
      overflow
      app
    >
      <v-list class="pt-0">
        <v-divider></v-divider>
        <v-list-tile
          v-for="item in items"
          :key="item.title"
          @click="$router.push(item.id)"
        >
          <v-list-tile-action >
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-tile-action>

          <v-list-tile-content>
            <v-list-tile-title>{{ item.title }}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
    <v-toolbar :clipped-left="primaryDrawer.clipped" app dark absolute color="primary" dense>
      <v-toolbar-side-icon
        dark
        v-if="primaryDrawer.type !== 'permanent'"
        @click.stop="primaryDrawer.model = !primaryDrawer.model"
      ></v-toolbar-side-icon>
      <v-toolbar-title class="white--text">东软云HIS</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-menu offset-y>
        <template v-slot:activator="{ on }">
          <v-icon
            v-on="on"
          >
            settings
          </v-icon>
        </template>
              <v-card>
                <v-card-text>
                  <v-layout row wrap>
                    <v-flex xs12 md6>
                      <span>Scheme</span>
                      <v-switch v-model="dark" primary label="Dark"></v-switch>
                    </v-flex>
                    <v-flex xs12 md6>
                      <span>Drawer</span>
                      <v-radio-group v-model="primaryDrawer.type" column>
                        <v-radio
                          v-for="drawer in drawers"
                          :key="drawer"
                          :label="drawer"
                          :value="drawer.toLowerCase()"
                          primary
                        ></v-radio>
                      </v-radio-group>
                      <v-switch v-model="primaryDrawer.clipped" label="Clipped" primary></v-switch>
                      <v-switch v-model="primaryDrawer.floating" label="Floating" primary></v-switch>
                      <v-switch v-model="primaryDrawer.mini" label="Mini" primary></v-switch>
                    </v-flex>
                    <v-flex xs12 md6>
                      <span>Footer</span>
                      <v-switch v-model="footer.inset" label="Inset" primary></v-switch>
                    </v-flex>
                  </v-layout>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn flat>Cancel</v-btn>
                  <v-btn flat color="primary">Submit</v-btn>
                </v-card-actions>
              </v-card>
      </v-menu>
    </v-toolbar>
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
      items: [
        {title: '基础信息维护', icon: 'dashboard', id: '/BasicInfo'},
        {title: '门诊挂号收费', icon: 'account_box', id: '/RegisterCharge'},
        {title: '门诊医生工作站', icon: 'assignment', id: '/Diagnostician'},
        {title: '门诊医技工作站', icon: 'credit_card', id: '/RegisterCharge'},
        {title: '门诊药房工作站', icon: 'card_travel', id: '/RegisterCharge'},
        {title: '门诊财务管理', icon: 'calendar_today', id: '/RegisterCharge'}
      ],
      right: null,
      dark: false,
      drawers: ['Default (no property)', 'Permanent', 'Temporary'],
      primaryDrawer: {
        model: null,
        type: 'default (no property)',
        clipped: false,
        floating: false,
        mini: false
      },
      footer: {
        inset: false
      }
    }
  },
  methods: {
    send: function () {
      this.$store.commit('increment')
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
