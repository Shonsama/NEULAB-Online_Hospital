<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <v-card>
      <v-toolbar extended flat dense>
        <v-toolbar-title >收费信息</v-toolbar-title>
        <template v-slot:extension>
          <v-flex xs2>
            <v-text-field  prepend-inner-icon="assignment" name="login" label="发票号" type="text" :disabled = "disabled"></v-text-field>
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
            color="primary"
            flat
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
      <v-form v-model="valid">
        <v-container>
          <v-layout>
            <div class="title font-weight-light">患者信息查询</div>
          </v-layout>
          <v-layout>
            <v-flex
              xs12
              md3
            >
              <v-text-field
                v-model="patient_record_id"
                :rules="nameRules"
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
          <v-layout>
            <v-flex
              xs12
              md2
            >
              <v-text-field
                v-model="patient_name"
                label="姓名"
                required
                disabled
              ></v-text-field>
            </v-flex>

            <v-flex
              xs12
              md2
            >
              <v-text-field
                v-model="patient_gender"
                label="性别"
                required
                disabled
              ></v-text-field>
            </v-flex>

            <v-flex
              xs12
              md4
            >
              <v-textarea
                v-model="patient_address"
                label="家庭住址"
                disabled
                rows="1"
              ></v-textarea>
            </v-flex>

            <v-flex
              xs12
              md3
            >
              <v-text-field
                v-model="patient_credit_id"
                :counter="18"
                label="身份证号"
                disabled
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
                    disabled
                    v-on="on"
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
                disabled
                required
              ></v-text-field>
            </v-flex>
          </v-layout>
        </v-container>
      </v-form>
    </v-card>
    <v-card>
      <v-toolbar flat dense>
        <v-toolbar-title >患者交费</v-toolbar-title>
      </v-toolbar>
      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="desserts"
        item-key="code"
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
          <td>{{ props.item.code }}</td>
          <td>{{ props.item.name }}</td>
          <td>{{ props.item.state }}</td>
          <td>{{ props.item.type }}</td>
          <td>{{ props.item.number }}</td>
          <td>
            <v-btn
              small
              right
              color="primary"
              @click="chargeItem(props.item)"
            >
              收费
            </v-btn>
          </td>
        </template>
      </v-data-table>
    </v-card>
    <v-card>
      <v-toolbar extended flat dense>
        <v-toolbar-title >患者收费信息</v-toolbar-title>
        <template v-slot:extension>
          <el-date-picker
            v-model="date"
            type="datetimerange"
            align="right"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            :default-time="['12:00:00', '08:00:00']"
            style="margin-right: 10px;margin-bottom: 10px"
          >
          </el-date-picker>
        </template>
      </v-toolbar>
      <v-data-table
        v-model="selected1"
        :headers="headers1"
        :items="filterDesserts"
        item-key="code"
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
          <td>{{ props.item.code }}</td>
          <td>{{ props.item.name }}</td>
          <td>{{ props.item.state }}</td>
          <td>{{ props.item.type }}</td>
          <td>{{ props.item.number }}</td>
          <td>{{ props.item.time }}</td>
          <td>
            <v-btn
              small
              right
              color="primary"
              @click="returnItem(props.item)"
            >
              退费
            </v-btn>
          </td>
        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
export default {
  data () {
    return {
      date: ['', ''],
      selected: [],
      selected1: [],
      patient_record_id: '',
      patient_gender: '',
      patient_name: '',
      patient_credit_id: '',
      patient_birthDate: '',
      patient_address: '',
      patient_age: '',
      disabled: true,
      desserts: [],
      desserts1: [],
      headers: [
        {
          text: '挂号ID',
          align: 'left',
          value: 'code'
        },
        {text: '名称', value: 'name'},
        {text: '状态', value: 'state'},
        {text: '类型', value: 'type'},
        {text: '金额', value: 'number'},
        {text: '操作', value: 'operation', sortable: false}
      ],
      headers1: [
        {
          text: '挂号ID',
          align: 'left',
          value: 'code'
        },
        {text: '名称', value: 'name'},
        {text: '状态', value: 'state'},
        {text: '类型', value: 'type'},
        {text: '金额', value: 'number'},
        {text: '收费时间', value: 'time'},
        {text: '操作', value: 'operation', sortable: false}
      ]
    }
  },
  computed: {
    filterDesserts () {
    return this.desserts1.filter(this.filterDate)
  }
},
  mounted: function () {
  },
  methods: {
    filterDate: function (value) {
      return (this.date[0] <= value.time && this.date[1] >= value.time) || this.date[0] === '' || this.date[1] === ''
    },
    chargeItem: function (value) {
      let that = this
      console.log(value)
      var url = this.HOME + '/pay/medical-skill'
      var url1 = this.HOME + '/pay/prescription'
      if (value.type === '检查' || value.type === '检验' || value.type === '处置') {
        var data = {
          'register_info_patient_id': that.patient_record_id
        }
        this.$http.post(url, data)
          .then(function (response) {
            console.log(response.data)
          })
      } else {
        var data = {
          'register_info_patient_id': that.patient_record_id
        }
        this.$http.post(url1, data)
          .then(function (response) {
            console.log(response.data)
          })
      }
    },
    returnItem: function (value) {
      console.log(value)
      var url = this.HOME + '/pay/get-medical-skill-canceled-or-paid'

    },
    getItem: function () {
      var url = this.HOME + '/pay/get-medical-skill-canceled-or-paid'
      var url1 = this.HOME + '/pay/get-prescription-returned-or-paid'
      var that = this
      var data = {
        'register_info_patient_id': that.patient_record_id
      }
      that.desserts1 = []
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          var i
          for (i = 0; i < response.data.data.length; i++) {
            var data = {
              code: response.data.data[i].medical_skill_register_info_id,
              name: response.data.data[i].medical_skill_name,
              state: response.data.data[i].medical_skill_execute_state,
              type: response.data.data[i].medical_skill_type,
              number: response.data.data[i].medical_skill_fee,
              time: response.data.data[i].medical_skill_pay_time
            }
            console.log(data)
            that.desserts1.push(data)
          }
        })
      this.$http.post(url1, data)
        .then(function (response) {
          console.log(response.data)
          var i
          for (i = 0; i < response.data.data.length; i++) {
            var data = {
              code: response.data.data[i].prescription_register_info_id,
              name: response.data.data[i].prescription_name,
              state: response.data.data[i].prescription_execute_state,
              type: response.data.data[i].prescription_type,
              number: response.data.data[i].prescription_fee,
              time: response.data.data[i].prescription_pay_time
            }
            console.log(data)
            that.desserts1.push(data)
          }
        })
    },
    getItem_charge: function () {
      var url1 = this.HOME + '/pay/get-prescription-sent'
      var url = this.HOME + '/pay/get-medical-skill-drew'
      var that = this
      var data = {
        'register_info_patient_id': that.patient_record_id
      }
      that.desserts = []
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          var i
          for (i = 0; i < response.data.data.length; i++) {
            var data = {
              code: response.data.data[i].medical_skill_register_info_id,
              name: response.data.data[i].medical_skill_name,
              state: response.data.data[i].medical_skill_execute_state,
              type: response.data.data[i].medical_skill_type,
              number: response.data.data[i].medical_skill_fee,
            }
            console.log(data)
            that.desserts.push(data)
          }
        })
      this.$http.post(url1, data)
        .then(function (response) {
          console.log(response.data)
          var i
          for (i = 0; i < response.data.data.length; i++) {
            var data = {
              code: response.data.data[i].prescription_register_info_id,
              name: response.data.data[i].prescription_name,
              state: response.data.data[i].prescription_execute_state,
              type: response.data.data[i].prescription_type,
              number: response.data.data[i].prescription_fee,
            }
            console.log(data)
            that.desserts.push(data)
          }
        })
    },
    get_patient_register: function () {
      var url = this.HOME + '/constant/get'
      var that = this
      var data = {
        'patient_record_id': that.patient_record_id
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
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
          that.patient_age = response.data.data.patient_age
          that.patient_address = response.data.data.patient_address
          that.getItem()
          that.getItem_charge()
          console.log(that.desserts)
          console.log(that.desserts1)
        })
    }
  }
}
</script>

<style scoped>

</style>
