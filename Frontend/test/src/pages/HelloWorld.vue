<template>
  <div>
    <section>
      <v-parallax src="https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg" height="650">
        <v-layout
          column
          align-center
          justify-center
          class="white--text"
        >
          <h1 class="white--text mb-2 display-1 text-xs-center">
            <v-icon dark large style="margin-bottom: 2px">
              local_hospital
            </v-icon>
            NeuHospital
          </h1>
            <v-card
              width="350"
              class="elevation-2"
            >
              <v-layout
                justify-center
                column
              >
                <v-flex xs12 sm10 md8 lg6>
                    <v-layout
                      justify-center
                      column
                      align-center
                      class="mt-4"
                    >
                      <div>
                        <h6 class="title">登录</h6>
                      </div>
                      <div>
                        <h6 class="body-1 font-weight-regular mt-2">使用你的云医院账号</h6>
                      </div>
                    </v-layout>
                </v-flex>
                <v-flex xs12 sm10 md8 lg8 class="mr-4 ml-4">
                  <v-text-field
                    v-model="account"
                    label="用户名"
                    prepend-inner-icon="person"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 class="mr-4 ml-4">
                  <v-text-field
                    v-model="password"
                    prepend-inner-icon="edit"
                    :append-icon="show1 ? 'visibility' : 'visibility_off'"
                    :type="show1 ? 'text' : 'password'"
                    name="input-10-1"
                    label="密码"
                    counter
                    @click:append="show1 = !show1"
                  ></v-text-field>
                </v-flex>
                <v-flex row xs12 sm12 md12 class="mr-4 ml-4">
                  <v-checkbox
                    v-model="isDoctor"
                    primary
                    label="是否为医生"
                    hide-details
                  ></v-checkbox>
                  <v-spacer/>
                </v-flex>
                <v-flex xs12 sm6 class="text-xs-center mb-3 mt-3">
                  <v-btn
                    color="primary"
                    @click="checkLogin"
                  >
                    登录
                  </v-btn>
                </v-flex>
              </v-layout>
            </v-card>
        </v-layout>
      </v-parallax>
    </section>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      show1: false,
      isDoctor: false,
      account: '',
      password: '',
      route: '',
      states: [
        '门诊医生', '医技医生', '挂号收费员', '药房操作员',
        '财务管理员', '医院管理员'
      ]
    }
  },
  props: {
    source: String
  },
  computed: {
  },
  watch: {
  },
  methods: {
    checkLogin: function () {
      let that = this
      var data
      var url = this.HOME + '/user/check-valid'
      if (!that.isDoctor) {
        data = {
          user_account: that.account,
          user_password: that.password
        }
      } else {
        data = {
          doctor_account: that.account,
          doctor_password: that.password
        }
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          var data
          if (!that.isDoctor) {
            data = {
              account: response.data.data.user_account,
              department_id: response.data.data.user_department_id,
              id: response.data.data.user_id,
              name: response.data.data.user_name,
              type: response.data.data.user_type
            }
            if (data.type === '挂号收费员') {
              that.$router.push('/RegisterCharge')
            } else if (data.type === '门诊医生') {
              that.$router.push('/Diagnostician')
            } else if (data.type === '医技医生') {
              that.$router.push('/Meditech')
            } else if (data.type === '药房操作员') {
              that.$router.push('/Pharmacy')
            } else if (data.type === '财务管理员') {
              that.$router.push('/Finance')
            } else if (data.type === '医院管理员') {
              that.$router.push('/BasicInfo')
            }
            // that.store.commit('set_user', data)
          } else {
            data = {
              account: response.data.data.doctor_account,
              department_id: response.data.data.doctor_department_id,
              id: response.data.data.doctor_id,
              name: response.data.data.doctor_name,
              type: response.data.data.doctor_type
            }
            console.log(data)
            if (data.type === '挂号收费员') {
              that.$router.push('/RegisterCharge')
            } else if (data.type === '门诊医生') {
              that.$router.push('/Diagnostician')
            } else if (data.type === '医技医生') {
              that.$router.push('/Meditech')
            } else if (data.type === '药房操作员') {
              that.$router.push('/Pharmacy')
            } else if (data.type === '财务管理员') {
              that.$router.push('/Finance')
            } else if (data.type === '医院管理员') {
              that.$router.push('/BasicInfo')
            }
            // that.store.commit('set_user', data)
          }
        })
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
