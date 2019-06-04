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
              ></v-select>
              <!--<v-text-field-->
                <!--ref="country"-->
                <!--v-model="user_department_id"-->
                <!--label="用户科室"-->
                <!--placeholder="请输入用户科室"-->
                <!--required-->
              <!--&gt;</v-text-field>-->
              <div v-if="isDoctor">
                <v-text-field
                  ref="country"
                  v-model="doctor_register_level_id"
                  label="医生职称"
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
                  label="医生排班"
                  placeholder="请输入医生排班"
                  required
                ></v-text-field>
              </div>
            </v-card-text>
            <!--<v-divider class="mt-5"></v-divider>-->
            <v-card-actions>
              <v-btn flat @click="show =!show">Cancel</v-btn>
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
                <v-btn color="primary" flat @click="addItem">add</v-btn>
              </div>
              <div v-else>
                <v-btn color="primary" flat @click="updateItem">update</v-btn>
              </div>

            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </v-dialog>

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
          falt
          @click="show = !show , mode = true"
        >
          <v-icon>
            add
          </v-icon>
        </v-btn>
        <v-btn
          icon
          falt
          @click="expand = !expand"
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
        item-key="department_id"
        select-all
        class="elevation-1"
      >
        <template v-slot:items="props">
          <td>
            <v-checkbox
              v-model="props.selected"
              primary
              hide-details
            ></v-checkbox>
          </td>
          <td>{{ props.item.user_id }}</td>
          <td>{{ props.item.user_account }}</td>
          <td>{{ props.item.user_password }}</td>
          <td>{{ props.item.user_type }}</td>
          <td>{{ props.item.user_name }}</td>
          <td>{{ props.item.user_department_id }}</td>
          <td>{{ props.item.doctor_register_level_id }}</td>
          <td>{{ props.item.doctor_position }}</td>
          <td>{{ props.item.doctor_arrange_or_not }}</td>
          <td>
            <v-icon
              small
              class="mr-2"
              @click="show =!show , mode = false , fillForm(props.item)"
            >
              edit
            </v-icon>
            <v-icon
              small
              @click="deleteItem(props.item)"
            >
              delete
            </v-icon>
          </td>
        </template>
      </v-data-table>
    </v-flex>
  </v-card>
</template>

<script>
export default {
  data: () => ({
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
    headers: [
      {
        text: '用户ID',
        align: 'left',
        value: 'user_id'
      },
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
      // that.users = []
      var url = this.HOME + '/user/get-all-user'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.temUser = response.data
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
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.doctors = response.data
          for (let i = 0; i < that.doctors.length; i++) {
            // let doctor = that.temUser[i]
            var tempDoctor = {
              user_id: that.temUser[i].doctor_id,
              user_account: that.temUser[i].doctor_account,
              user_password: that.temUser[i].doctor_password,
              user_type: that.temUser[i].doctor_type,
              user_name: that.temUser[i].doctor_name,
              user_department_id: that.temUser[i].doctor_department_id,
              doctor_register_level_id: that.temUser[i].doctor_register_level_id,
              doctor_position: that.temUser[i].doctor_position,
              doctor_arrange_or_not: that.temUser[i].doctor_arrange_or_not
            }
            that.users.append(tempDoctor)
          }
        })
    },
    deleteItem: function (item) {
      let that = this
      var url = ''
      if (item.user_type === '门诊医生' || item.user_type === '医技医生') {
        url = this.HOME + '/user/delete-doctor'
      } else {
        url = this.HOME + '/user/delete-user'
      }
      this.$http.post(url, item.user_account)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data
          if (that.signal.result === 'success') {
            that.load()
          }
        })
      console.log(this.signal)
    },
    addItem: function () {
      let that = this
      var url
      var user
      if (this.user_type === '门诊医生' || this.user_type === '医技医生') {
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
      this.$http.post(url, user)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data
          if (that.signal.result === 'success') {
            that.load()
            that.show = !that.show
          }
        })
      console.log(this.signal)
      // console.log('This is the departmentid')
      // console.log(this.user_department_id)
    },
    updateItem: function () {
      let that = this
      var url = ''
      var user
      if (this.user_type === '门诊医生' || this.user_type === '医技医生') {
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
      this.$http.post(url, user)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data
          if (that.signal.result === 'success') {
            that.load()
            that.show = !that.show
          }
        })
      console.log(this.signal)
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
      var url = this.HOME + '/department/get-all'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.items_departments = response.data
        })
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
      if (newState === '门诊医生' || newState === '医技医生') {
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
