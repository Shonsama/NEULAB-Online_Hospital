<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
<div>
<v-card>
  <v-toolbar extended flat dense>
    <v-toolbar-title >挂号信息</v-toolbar-title>
    <template v-slot:extension>
    <v-flex xs2>
    <v-text-field prepend-inner-icon="assignment" name="login" label="发票号" type="text" ></v-text-field>
    </v-flex>
    <v-btn
      small
    >更新发票号
    </v-btn>
      <v-spacer></v-spacer>
    </template>
  </v-toolbar>
  <v-divider></v-divider>
  <v-form v-model="valid">
    <v-container>
      <v-layout>
        <div class="title font-weight-light">患者信息查询</div>
      </v-layout>
      <v-layout>
      <v-flex
        xs12
        md4
      >
        <v-text-field
          v-model="patient_record_id"
          label="病历号"
          required
        ></v-text-field>
        <v-btn
          small
        >查询
        </v-btn>
        <v-btn
          small
        >新增
        </v-btn>
      </v-flex>
      </v-layout>
      <v-layout>
        <div class="title font-weight-light">患者信息确认</div>
      </v-layout>
      <v-layout>

        <v-flex
          xs12
          md2
        >
          <v-text-field
            v-model="patient_name"
            :rules="nameRules"
            label="姓名"
            placeholder="请输入姓名"
            required
          ></v-text-field>
        </v-flex>

        <v-flex
          xs12
          md2
        >
          <v-select
            v-model="patient_gender"
            :rules="genderRules"
            :items="genders"
            label="性别"
            required
            placeholder="请选择性别"
          ></v-select>
        </v-flex>

        <v-flex
          xs12
          md4
        >
          <v-textarea
            v-model="patient_address"
            label="家庭住址"
            placeholder="请输入家庭住址"
            rows="1"
          ></v-textarea>
        </v-flex>

        <v-flex
          xs12
          md3
        >
          <v-text-field
            v-model="patient_credit_id"
            :rules="creditRules"
            :counter="18"
            label="身份证号"
            placeholder="请输入身份证号"
            required
          ></v-text-field>
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex
          xs12
          md2
        >
          <v-menu
            ref="menu"
            v-model="menu"
            :close-on-content-click="false"
            :nudge-right="40"
            :return-value.sync="patient_birthDate"
            lazy
            transition="scale-transition"
            offset-y
            full-width
            min-width="290px"
          >
            <template v-slot:activator="{ on }">
              <v-text-field
                v-model="patient_birthDate"
                label="出生日期"
                required
                readonly
                v-on="on"
                placeholder="请选择出生日期"
              ></v-text-field>
            </template>
            <v-date-picker v-model="patient_birthDate" no-title scrollable>
              <v-spacer></v-spacer>
              <v-btn flat color="primary" @click="menu = false">Cancel</v-btn>
              <v-btn flat color="primary" @click="$refs.menu.save(patient_birthDate)">OK</v-btn>
            </v-date-picker>
          </v-menu>
        </v-flex>

        <v-flex
          xs12
          md2
        >
          <v-text-field
            v-model="patient_age"
            label="年龄"
            required
            placeholder="请输入年龄"
          ></v-text-field>
        </v-flex>

        <v-flex
          xs12
          md4
        >
          <v-select
            v-model="paycate"
            :items="payCates"
            :rules="payRules"
            label="结算类别"
            required
            placeholder="请选择结算类别"
          ></v-select>
        </v-flex>

        <v-flex
          xs12
          md3
        >
          <v-select
            :items="registers"
            :rules="registerRules"
            label="挂号级别"
            required
            placeholder="请选择挂号级别"
          ></v-select>
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex
          xs2
          md2
        >
          <v-select
            change="load_doctors"
            v-model="department_name"
            :items="departments"
            item-text="department_name"
            item_value="department_id"
            :rules="departRules"
            label="科室"
            return-object
            required
            placeholder="请选择科室"
          ></v-select>
        </v-flex>
        <v-flex
          xs2
          md2
        >
          <v-select
            v-model="doctor_id"
            :items="doctors"
            item-text="doctor_name"
            item-value="doctor_id"
            :rules="doctorRules"
            label="看诊医生"
            return-object
            required
            placeholder="请选择看诊医生"
          ></v-select>
        </v-flex>

        <v-flex xs12 md4>
          <v-checkbox
            v-model="checkbox"
            v-validate="'required'"
            value="1"
            style="margin-left: 10px"
            label="是否需要病历本"
            data-vv-name="checkbox"
            type="checkbox"
            required
          ></v-checkbox>
        </v-flex>
        <v-flex xs12 md2>
          <v-text-field
            v-model="bill_sum"
            label="应收金额"
            placeholder="未知"
            disabled
          ></v-text-field>
        </v-flex>
        <v-btn small style="margin-top: 25px" @click="submit_register">挂号</v-btn>
      </v-layout>
    </v-container>
  </v-form>
</v-card>
  <v-card>
    <v-toolbar flat dense>
      <v-toolbar-title >挂号信息列表</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>
    <v-data-table
      v-model="selected"
      :headers="headers"
      :items="desserts"
      item-key="name"
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
        <td>{{ props.item.name }}</td>
        <td class="text-xs-right">{{ props.item.calories }}</td>
        <td class="text-xs-right">{{ props.item.fat }}</td>
        <td class="text-xs-right">{{ props.item.carbs }}</td>
        <td class="text-xs-right">{{ props.item.protein }}</td>
        <td class="text-xs-right">{{ props.item.iron }}</td>
        <td class="justify-center layout px-0">
          <v-icon
            small
            class="mr-2"
            @click="editItem(props.item)"
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
  </v-card>

</div>
</template>

<script>
import Qs from 'qs'
export default {
  name: 'register',
  data: () => ({
    valid: false,
    patient_record_id: '',
    patient_gender: '',
    patient_name: '',
    patient_credit_id: '',
    patient_birthDate: '',
    patient_address: '',
    patient_age: '',
    bill_sum: '',
    doctor_id: '',
    doctor_name: '',
    department_name: '',
    department_id: '',
    genders: ['男', '女'],
    registers: [],
    payCates: [],
    paycate: '',
    departments: [],
    doctors: [],
    creditRules: [
      v => !!v || '身份证号是必须的',
      v => v.length === 18 || '身份证号必须为18位'
    ],
    nameRules: [
      v => !!v || '姓名是必须的'
    ],
    genderRules: [
      v => !!v || '性别是必须的'
    ],
    doctorRules: [
      v => !!v || '医生是必须的'
    ],
    departRules: [
      v => !!v || '科室是必须的'
    ],
    registerRules: [
      v => !!v || '挂号级别是必须的'
    ],
    payRules: [
      v => !!v || '结算方式是必须的'
    ]
  }),
  mounted: function () {
    this.load_constants()
    this.load_departs()
    this.load_doctors()
  },
  methods: {
    get_patient: function (id) {
      var url = this.HOME + '/constant/get'
      var that = this
      var data = Qs.stringify({
        'patient_record_id': that.patient_record_id
      })
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
        })
    },
    load_constants: function () {
      let that = this
      var url = this.HOME + '/constant/get'
      var data = Qs.stringify({
        'constant_type': 'payment_type'
      })
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.payCates = response.data
        })
    },
    load_departs: function () {
      var that = this
      var url = this.HOME + '/department/getall'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.departments = response.data
        })
    },
    load_doctors: function () {
      let that = this
      var data = Qs.stringify({
        'department_id': that.department_id
      })
      var url = this.HOME + '/register/get-all-doctor'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
        })
    },
    submit_register: function () {
      let that = this
      var url = this.HOME + '/register/submit'
      var data = Qs.stringify({
        'register': {
          'register_info_id': '',
          'register_info_state': '未看诊',
          'register_info_fee': that.bill_sum,
          'register_info_pay_type': that.paycate,
          'register_info_doctor_id': that.doctor_id,
          'register_info_patient_id': that.patient_record_id,
          'register_info_user_id': '',
          'doctor': '',
          'user': '',
          'patient': ''
        }
      })
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
        })
    }
  }
}
</script>

<style scoped>

</style>
