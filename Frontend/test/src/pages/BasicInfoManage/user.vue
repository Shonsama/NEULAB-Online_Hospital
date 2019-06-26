<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card>
    <v-dialog
      v-model="show"
      width="300"
    >
      <v-layout justify-center>
        <v-flex xs12>
          <v-card ref="form">
            <v-card-text>
              <v-text-field
                ref="name"
                v-model="user_id"
                :error-messages="errorMessages"
                label="用户ID"
                placeholder="请输入用户ID"
                :disabled="true"
                required
              ></v-text-field>
              <v-text-field
                ref="address"
                v-model="user_account"
                label="用户账户"
                placeholder="请输入用户账户"
                required
              ></v-text-field>
              <v-text-field
                ref="city"
                v-model="user_password"
                label="用户密码"
                placeholder="请输入用户密码"
                required
              ></v-text-field>
              <v-select
                ref="country"
                :items="user_type_items"
                v-model="user_type"
                label="用户类型"
                placeholder="请输入用户类型"
                required
              ></v-select>
              <v-text-field
                ref="country"
                v-model="user_name"
                label="用户姓名"
                placeholder="请输入用户姓名"
                required
              ></v-text-field>
              <v-select
                v-model="user_department_id"
                :items="items_departments"
                item-text="department_name"
                item-value="department_id"
                label="科室"
                placeholder="请输入科室"
              ></v-select>
              <div v-if="isDoctor">
                <v-text-field
                  ref="country"
                  v-model="doctor_register_level_id"
                  label="挂号级别ID"
                  placeholder="请输入医生挂号级别ID"
                  required
                ></v-text-field>
                <v-text-field
                  ref="country"
                  v-model="doctor_position"
                  label="医生职称"
                  placeholder="请输入医生职称"
                  required
                ></v-text-field>
                <v-text-field
                  ref="country"
                  v-model="doctor_arrange_or_not"
                  label="医生是否排班"
                  placeholder="请输入医生是否排班"
                  required
                ></v-text-field>
              </div>
            </v-card-text>
            <v-card-actions>
              <v-btn flat @click="show =!show">取消</v-btn>
              <v-spacer></v-spacer>
              <v-slide-x-reverse-transition>
                <v-tooltip
                  v-if="formHasErrors"
                  left
                >
                  <template v-slot:activator="{ on }">
                    <v-btn
                      icon
                      class="my-0"
                      @click="resetForm"
                      v-on="on"
                    >
                      <v-icon>refresh</v-icon>
                    </v-btn>
                  </template>
                  <span>Refresh form</span>
                </v-tooltip>
              </v-slide-x-reverse-transition>
              <div v-if="mode">
                <v-btn color="primary" flat @click="addItem">确定</v-btn>
              </div>
              <div v-else>
                <v-btn color="primary" flat @click="updateItem">确定</v-btn>
              </div>

            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </v-dialog>

    <v-alert
      :value="alert_success"
      type="success"
      transition="slide-y-transition"
    >
      This is a success alert.
    </v-alert>

    <v-alert
      :value="alert_error"
      type="error"
      transition="slide-y-transition"
    >
      This is a error alert.
    </v-alert>

    <v-flex>
      <v-toolbar flat>
        <v-flex xs3>
          <v-text-field
            prepend-inner-icon="search"
            v-model="search"
            :rules="nameRules"
            label="编号/名称"
            required
          ></v-text-field>
        </v-flex>

        <v-spacer></v-spacer>
        <v-btn
          icon
          flat
          color="primary"
          @click="show = !show , mode = true"
        >
          <v-icon>
            add
          </v-icon>
        </v-btn>
        <v-btn
          icon
          flat
          color="primary"
          @click="delete_selected"
        >
          <v-icon>
            delete
          </v-icon>
        </v-btn>
      </v-toolbar>
      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="users"
        :search="search"
        item-key="user_account"
        select-all
      >
        <template v-slot:items="props">
          <td>
            <v-checkbox
              v-model="props.selected"
              primary
              hide-details
            ></v-checkbox>
          </td>
          <!--<td>{{ props.item.user_id }}</td>-->
          <td>{{ props.item.user_account }}</td>
          <td>{{ props.item.user_password }}</td>
          <td>{{ props.item.user_type }}</td>
          <td>{{ props.item.user_name }}</td>
          <td>{{ props.item.user_department_id }}</td>
          <td>{{ props.item.doctor_register_level_id }}</td>
          <td>{{ props.item.doctor_position }}</td>
          <td>{{ props.item.doctor_arrange_or_not }}</td>
          <td>
            <layout row>
              <div>
                <v-icon
                  small
                  @click="show =!show , mode = false , fillForm(props.item)"
                >
                  edit
                </v-icon>
              </div>
              <div>
                <v-icon
                  small
                  @click="deleteItem(props.item)"
                >
                  delete
                </v-icon>
              </div>
            </layout>
          </td>
        </template>
      </v-data-table>
    </v-flex>
    <v-divider/>
    <v-card-actions>
      <v-spacer/>
      <download-excel
        class = "export-excel-wrapper"
        :data = "desserts"
        :fields = "json_fields"
        name = "用户信息.xls">
        <v-btn
          color="primary"
        >
          导出表格
          <v-icon right>
            cloud_download
          </v-icon>
        </v-btn>
      </download-excel>
    </v-card-actions>
  </v-card>
</template>

<script>
/* eslint-disable camelcase */
export default {
  data: () => ({
    alert_success: false,
    alert_error: false,
    user_type_items: [
      {text: '挂号收费员'},
      {text: '门诊医生'},
      {text: '医技医生'},
      {text: '药房操作员'},
      {text: '财务管理员'},
      {text: '医院管理员'}
    ],
    isDoctor: false,
    mode: true,
    user_id: '',
    user_account: '',
    user_password: '',
    user_type: '',
    user_name: '',
    user_department_id: '',
    doctor_position: '',
    doctor_arrange_or_not: '',
    show: false,
    search: '',
    expand: false,
    selected: [],
    signal: '',
    json_fields: {
      '用户账户': 'user_account',
      '用户密码': 'user_password',
      '用户类型': 'user_type',
      '用户姓名': 'user_name',
      '用户科室': 'user_department_id',
      '挂号级别': 'doctor_register_level_id',
      '医生职称': 'doctor_position',
      '医生排班': 'doctor_arrange_or_not'
    },
    headers: [
      // {
      //   text: '用户ID',
      //   align: 'left',
      //   value: 'user_id'
      // },
      { text: '用户账户', value: 'user_account' },
      { text: '用户密码', value: 'user_password' },
      { text: '用户类型', value: 'user_type' },
      { text: '用户姓名', value: 'user_name' },
      { text: '用户科室', value: 'user_department_id' },
      { text: '挂号级别', value: 'doctor_register_level_id' },
      { text: '医生职称', value: 'doctor_position' },
      { text: '医生排班', value: 'doctor_arrange_or_not' },
      { text: '操作', value: 'operation', sortable: false }
    ],
    users: [],
    temUser: [],
    doctors: [],
    items_departments: []
  }),
  mounted: function () {
    this.load()
    this.load_items_departments()
  },
  methods: {
    load: function () {
      let that = this
      that.users = []
      var url = this.HOME + '/user/get-all-user'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), {
      })
        .then(function (response) {
          console.log(response.data)
          that.temUser = response.data.data
          console.log('This is all the user')
          console.log(that.temUser.length)
          for (let i = 0; i < that.temUser.length; i++) {
            var userTemp = {
              user_id: that.temUser[i].user_id,
              user_account: that.temUser[i].user_account,
              user_password: that.temUser[i].user_password,
              user_type: that.temUser[i].user_type,
              user_name: that.temUser[i].user_name,
              user_department_id: that.temUser[i].user_department_id,
              doctor_register_level_id: '无',
              doctor_position: '无',
              doctor_arrange_or_not: '无'
            }
            that.users.push(userTemp)
          }
        })
      url = this.HOME + '/user/get-all-doctor'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), {
      })
        .then(function (response) {
          console.log(response.data)
          that.doctors = response.data.data
          console.log('This is all the doctors')
          console.log(that.doctors.length)
          for (let i = 0; i < that.doctors.length; i++) {
            // let doctor = that.temUser[i]
            var tempDoctor = {
              user_id: that.doctors[i].doctor_id,
              user_account: that.doctors[i].doctor_account,
              user_password: that.doctors[i].doctor_password,
              user_type: that.doctors[i].doctor_type,
              user_name: that.doctors[i].doctor_name,
              user_department_id: that.doctors[i].doctor_department_id,
              doctor_register_level_id: that.doctors[i].doctor_register_level_id,
              doctor_position: that.doctors[i].doctor_position,
              doctor_arrange_or_not: that.doctors[i].doctor_arrange_or_not
            }
            that.users.push(tempDoctor)
          }
        })
      console.log('This is total the user')
      console.log(that.users.length)
    },
    deleteItem: function (item) {
      let that = this
      var url = ''
      var data
      if (item.user_type === '门诊医生' || item.user_type === '医技医生' || item.user_type === '药房操作员') {
        url = this.HOME + '/user/delete-doctor'
        data = {doctor_account: item.user_account}
      } else {
        url = this.HOME + '/user/delete-user'
        data = {user_account: item.user_account}
      }
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data.msg
          if (that.signal === 'SUCCESS') {
            that.load()
            that.notice_success()
          } else {
            that.notice_error()
          }
        })
      console.log(this.signal)
    },
    addItem: function () {
      if (this.check_account_valid(this.user_account)) {
        let that = this
        var url
        var user
        if (this.user_type === '门诊医生' || this.user_type === '医技医生' || this.user_type === '药房操作员') {
          user = {
            doctor_account: this.user_account,
            doctor_password: this.user_password,
            doctor_type: this.user_type,
            doctor_name: this.user_name,
            doctor_department_id: this.user_department_id,
            doctor_register_level_id: this.doctor_register_level_id,
            doctor_position: this.doctor_position,
            doctor_arrange_or_not: this.doctor_arrange_or_not
          }
          url = this.HOME + '/user/add-doctor'
        } else {
          user = {
            user_account: this.user_account,
            user_password: this.user_password,
            user_type: this.user_type,
            user_name: this.user_name,
            user_department_id: this.user_department_id
          }
          url = this.HOME + '/user/add-user'
        }
        this.$http.post(url + '?token=' + sessionStorage.getItem('token'), user)
          .then(function (response) {
            console.log(response.data)
            that.signal = response.data.msg
            if (that.signal === 'SUCCESS') {
              that.load()
              that.show = !that.show
              that.notice_success()
            } else {
              that.notice_error()
            }
          })
        console.log(this.signal)
      } else {
        this.notice_error()
      }
    },
    updateItem: function () {
      if (this.check_account_valid(this.user_account)) {
        let that = this
        var url = ''
        var user
        if (this.user_type === '门诊医生' || this.user_type === '医技医生' || this.user_type === '药房操作员') {
          user = {
            doctor_id: this.user_id,
            doctor_account: this.user_account,
            doctor_password: this.user_password,
            doctor_type: this.user_type,
            doctor_name: this.user_name,
            doctor_department_id: this.user_department_id,
            doctor_register_level_id: this.doctor_register_level_id,
            doctor_position: this.doctor_position,
            doctor_arrange_or_not: this.doctor_arrange_or_not
          }
          url = this.HOME + '/user/update-doctor'
        } else {
          user = {
            user_id: this.user_id,
            user_account: this.user_account,
            user_password: this.user_password,
            user_type: this.user_type,
            user_name: this.user_name,
            user_department_id: this.user_department_id
          }
          url = this.HOME + '/user/update-user'
        }
        this.$http.post(url + '?token=' + sessionStorage.getItem('token'), user)
          .then(function (response) {
            console.log(response.data)
            that.signal = response.data.msg
            if (that.signal === 'SUCCESS') {
              that.load()
              that.show = !that.show
              that.notice_success()
            } else {
              that.notice_error()
            }
          })
        console.log(this.signal)
      } else {
        this.notice_error()
      }
    },
    fillForm: function (item) {
      this.user_id = item.user_id
      this.user_account = item.user_account
      this.user_password = item.user_password
      this.user_type = item.user_type
      this.user_name = item.user_name
      this.user_department_id = item.user_department_id
      this.doctor_register_level_id = item.doctor_register_level_id
      this.doctor_position = item.doctor_position
      this.doctor_arrange_or_not = item.doctor_arrange_or_not
    },
    eraseForm: function () {
      this.user_id = ''
      this.user_account = ''
      this.user_password = ''
      this.user_type = ''
      this.user_name = ''
      this.user_department_id = ''
      this.doctor_register_level_id = ''
      this.doctor_position = ''
      this.doctor_arrange_or_not = ''
    },
    load_items_departments: function () {
      let that = this
      var url = this.HOME + '/maintenance/department/get-all'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), {
      })
        .then(function (response) {
          console.log(response.data)
          that.items_departments = response.data.data
        })
    },
    notice_success: function () {
      this.change_success()
      window.setTimeout(this.change_success, 1500)
    },
    change_success: function () {
      this.alert_success = !this.alert_success
    },
    notice_error: function () {
      this.change_error()
      window.setTimeout(this.change_error, 1500)
    },
    change_error: function () {
      this.alert_error = !this.alert_error
    },
    check_account_valid: function (user_account_test) {
      for (let i = 0; i < this.users.length; i++) {
        if (user_account_test === this.users[i].user_account) {
          return false
        }
      }
      return true
    },
    delete_selected: function () {
      var count = 0
      for (let i = 0; i < this.selected.length; i++) {
        var item = {
          user_type: this.selected[i].user_type,
          user_account: this.selected[i].user_account
        }
        let that = this
        var url = ''
        var data
        if (item.user_type === '门诊医生' || item.user_type === '医技医生' || item.user_type === '药房操作员') {
          url = this.HOME + '/user/delete-doctor'
          data = {doctor_account: item.user_account}
        } else {
          url = this.HOME + '/user/delete-user'
          data = {user_account: item.user_account}
        }
        this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
          .then(function (response) {
            console.log(response.data)
            that.signal = response.data.msg
            if (that.signal === 'SUCCESS') {
              count = count + 1
            }
          })
      }
      this.load()
      if (this.count === this.length) {
        this.notice_success()
      } else {
        this.notice_error()
      }
    }

  },
  computed: {
  },
  watch: {
    show: function (newState, oldState) {
      if (newState === false) {
        this.eraseForm()
      }
    },
    user_type: function (newState, oldState) {
      if (newState === '门诊医生' || newState === '医技医生' || newState === '药房操作员') {
        this.isDoctor = true
      } else {
        this.isDoctor = false
      }
    }
  }
}
</script>

<style scoped>

</style>
