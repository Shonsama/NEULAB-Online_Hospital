<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
<div>
<v-card>
  <v-toolbar extended flat dense>
    <v-toolbar-title >挂号信息</v-toolbar-title>
      <template v-slot:extension>
      <v-flex xs2>
        <v-text-field prepend-inner-icon="assignment" name="login" label="发票号" type="text" :disabled="disabled" ></v-text-field>
      </v-flex>
      <v-btn
        small
        icon
        flat
        color="primary"
        @click="disabled = !disabled"
      >
        <v-icon>
          refresh
        </v-icon>
      </v-btn>
      <v-btn
        small
        icon
        flat
        color="primary"
        @click="disabled = !disabled"
      >
        <v-icon>
          print
        </v-icon>
      </v-btn>
      <v-spacer></v-spacer>
    </template>
  </v-toolbar>
  <v-divider></v-divider>
  <v-layout class="ml-4">
    <v-form v-model="valid">
      <v-container>
        <v-layout>
          <div class="title font-weight-light">患者信息查询</div>
        </v-layout>
        <v-layout>
          <v-flex
            xs12
            md6
            lg3
          >
            <v-text-field
              v-model="patient_record_id"
              label="病历号"
              required
            ></v-text-field>
          </v-flex>
          <v-btn
            @click="get_patient"
            color="primary"
            flat
            icon
            style="margin-top: 20px"
          >
            <v-icon>search</v-icon>
          </v-btn>
        </v-layout>
        <v-layout>
          <div class="title font-weight-light">患者信息确认</div>
        </v-layout>
        <v-layout wrap>
          <v-flex
            xs12
            md6
            lg2
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
            md6
            lg2
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
            md6
            lg4
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
            md6
            lg4
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
          <v-flex
            xs12
            md6
            lg2
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
            md6
            lg2
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
            md6
            lg2
          >
            <v-select
              v-model="paycate"
              :items="payCates"
              :rules="payRules"
              item-text="constant_name"
              item_value="constant_id"
              label="结算类别"
              required
              placeholder="请选择结算类别"
            ></v-select>
          </v-flex>
          <v-flex
            xs12
            md6
            lg2
          >
            <v-select
              v-model="register_level"
              :items="registers"
              :rules="registerRules"
              item-text="register_level_name"
              item_value="register_level_id"
              return-object
              label="挂号级别"
              required
              placeholder="请选择挂号级别"
            ></v-select>
          </v-flex>
          <v-flex
            xs12
            md6
            lg2
          >
            <v-select
              v-model="departmentId"
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
            xs12
            md6
            lg2
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
          <v-flex
            xs12
            md6
            lg2
          >
            <v-text-field
              v-model="bill_sum"
              label="应收金额"
              placeholder="未知"
              disabled
            ></v-text-field>
          </v-flex>
          <v-flex
            xs12
            md6
            lg2
          >
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
          <v-btn small color="primary" style="margin-top: 25px" @click="submit_register">挂号</v-btn>
        </v-layout>
      </v-container>
    </v-form>
  </v-layout>
</v-card>
  <v-card>
    <v-toolbar flat dense>
      <v-toolbar-title >挂号信息列表</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>
    <v-data-table
      v-model="selected"
      :headers="headers"
      :items="register_items"
      item-key="register_info_id"
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
        <td>{{ props.item.register_info_id }}</td>
        <td>{{ props.item.register_info_patient_id }}</td>
        <td>{{ props.item.register_info_state }}</td>
        <td>{{ props.item.register_info_doctor_id }}</td>
        <td>{{ props.item.register_info_doctor_id }}</td>
        <td>{{ props.item.register_info_fee }}</td>
        <td>
          <v-btn
            class="ml-3"
            small
            flat
            icon
            right
            :disabled="props.item.register_info_state != '已挂号'"
            color="primary"
            @click="refund(props.item.register_info_id)"
          >
            退号
          </v-btn>
        </td>
      </template>
    </v-data-table>
  </v-card>

</div>
</template>

<script>
export default {
  data: () => ({
    register_items: [],
    headers: [
      {
        text: '挂号ID',
        align: 'left',
        value: 'register_info_id'
      },
      { text: '患者名称', value: 'register_info_patient_id' },
      { text: '状态', value: 'register_info_state' },
      { text: '所属科室', value: 'register_info_doctor_id' },
      { text: '所属医生', value: 'register_info_doctor_id' },
      { text: '挂号费', value: 'register_info_fee' },
      { text: '操作', value: 'operation', sortable: false }
    ],
    valid: false,
    disabled: true,
    register_info_id: '1',
    patient_record_id: '',
    patient_gender: '',
    patient_name: '',
    patient_credit_id: '',
    patient_birthDate: '',
    patient_address: '',
    patient_age: '',
    checkbox: '',
    bill_sum: '',
    doctor_id: '',
    doctor_name: '',
    department_name: '',
    departmentId: '',
    genders: ['男', '女'],
    registers: [],
    register_level: '',
    payCates: [],
    paycate: '',
    registerLevel: '',
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
  watch: {
    checkbox: function (newState) {
      if (newState) {
        this.bill_sum++
      } else {
        this.bill_sum--
      }
    },
    patient_birthDate: function (newState) {
      var date = new Date()
      console.log(newState)
      var date1 = new Date(newState.replace(/-/, '/'))
      this.patient_age = date.getFullYear() - date1.getFullYear()
    },
    departmentId: function (newState) {
      this.load_doctors()
    },
    register_level: function (newState) {
      this.load_doctors()
    }
  },
  mounted: function () {
    this.load_constants()
    this.load_departs()
    this.load_registerLevels()
    this.load_register_fee()
  },
  methods: {
    change: function () {
      this.disabled = !this.disabled
    },
    load_register_fee: function () {
      this.bill_sum = 32
    },
    get_patient_register: function () {
      var url = this.HOME + 'user-service/refund/get-paid-registers'
      var that = this
      var data = {
        patient_id: that.patient_record_id
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.register_items = response.data.data
        })
    },
    get_patient: function () {
      var url = this.HOME + '/patient/get-by-patient-id'
      var that = this
      var data = {
        'patient_record_id': that.patient_record_id
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          if (response.data.data.patient_gender) {
            that.patient_gender = '男'
          } else {
            that.patient_gender = '女'
          }
          that.patient_name = response.data.data.patient_name
          that.patient_credit_id = response.data.data.patient_credit_id
          that.patient_birthDate = response.data.data.patient_birthDate.substring(0, 10)
          that.patient_address = response.data.data.patient_address
          that.get_patient_register()
        })
    },
    load_constants: function () {
      let that = this
      var url = this.HOME + '/maintenance/constant/get'
      var data = {
        constant_type: 'payment_type'
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.payCates = response.data.data
        })
    },
    load_registerLevels: function () {
      let that = this
      var url = this.HOME + '/maintenance/register-level/get-all'
      this.$http.post(url)
        .then(function (response) {
          console.log(response.data)
          that.registers = response.data.data
        })
    },
    load_departs: function () {
      var that = this
      var url = this.HOME + '/maintenance/department/get-all'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.departments = response.data.data
        })
    },
    load_doctors: function () {
      let that = this
      console.log(that.departmentId)
      console.log(that.register_level)
      var data = {
        department_id: that.departmentId.department_id,
        register_level_id: that.register_level.register_level_id
      }
      var url = this.HOME + '/register/get-all-doctor'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.doctors = response.data.data
        })
    },
    refund: function (id) {
      let that = this
      var data = {
        'register_id': id
      }
      var url = this.HOME + '/register/refund'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.get_patient_register()
        })
    },
    print_bill: function () {
      let that = this
      var url = this.HOME + '/register/print_bill'
      var data = {
        bill_actual_sum: that.bill_sum,
        bill_sum: that.bill_sum,
        bill_time: new Date(),
        bill_type: '挂号费',
        bill_register_id: that.register_info_id,
        bill_user_id: '1'
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
        })
    },
    submit_register: function () {
      let that = this
      var url = this.HOME + '/register/submit'
      var checkbox
      if (that.checkbox) {
        checkbox = 'true'
      } else {
        checkbox = 'false'
      }
      var data = {
        register_info_id: '',
        register_info_state: '未看诊',
        register_info_fee: that.bill_sum,
        register_info_pay_type: that.paycate,
        register_info_doctor_id: that.doctor_id.doctor_id,
        register_info_patient_id: that.patient_record_id,
        register_info_user_id: '1',
        register_info_records_book: checkbox
      }
      console.log(data)
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.get_patient_register()
        })
    }
  }
}
</script>

<style scoped>

</style>
