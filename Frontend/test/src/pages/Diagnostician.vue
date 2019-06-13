<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <v-dialog
      v-model="dialog"
      hide-overlay
      persistent
      width="300"
    >
      <v-card
        color="primary"
        dark
      >
        <v-card-text>
          请稍等
          <v-progress-linear
            indeterminate
            color="white"
            class="mb-0"
          ></v-progress-linear>
        </v-card-text>
      </v-card>
    </v-dialog>
    <v-flex shrink>
      <v-expand-transition>
        <div v-show="dialog_err" style="white-space: nowrap">
          <v-alert
            :value="true"
            type="error"
          >
            {{msg_err}}
          </v-alert>
        </div>
      </v-expand-transition>
    </v-flex>
    <v-flex shrink>
      <v-expand-transition>
        <div v-show="dialog_suc" style="white-space: nowrap">
          <v-alert
            :value="true"
            type="success"
          >
            {{msg_suc}}
          </v-alert>
        </div>
      </v-expand-transition>
    </v-flex>
    <v-card>
    <v-layout>
      <v-flex shrink >
        <v-expand-x-transition >
          <div v-show="show" style="white-space: nowrap; width:300px">
            <v-layout>
              <v-flex md10 xs10>
                <v-text-field
                  prepend-inner-icon="search"
                  v-model="search"
                  name="login"
                  label="查询患者"
                  type="text"
                  style="margin-left: 25px"
                ></v-text-field>
              </v-flex>
            </v-layout>
            <v-layout>
              <v-tabs
                v-model="active"
                style="width: 300px"
              >
                <v-tab
                  v-for="item in items1"
                  :key="item"
                  ripple
                >
                  {{item.name}}
                </v-tab>
                <v-tab-item
                  v-for="item in items1"
                  :key="item"
                >
                  <div v-if="item.id == 'desserts_per'">
                    <v-card flat >
                      <v-toolbar flat dense>
                        <v-toolbar-title>未诊断</v-toolbar-title>
                      </v-toolbar>
                      <v-data-table
                        :search="search"
                        :headers="headers"
                        :items="desserts_per.YiGuaHao"
                        class="elevation-1"
                      >
                        <template v-slot:items="props">
                          <td>{{ props.item.patient.patient_record_id }}</td>
                          <td>{{ props.item.patient.patient_name }}</td>
                          <td>
                            <v-btn
                              color="primary"
                              small
                              flat
                              icon
                              right
                              :disabled = "disable"
                              append-icon="search"
                              class="ml-3"
                              @click="treat(props.item)"
                            >
                              接诊
                            </v-btn>
                          </td>
                        </template>
                      </v-data-table>
                    </v-card>
                    <v-card flat >
                    <v-toolbar flat dense>
                      <v-toolbar-title>已诊断</v-toolbar-title>
                    </v-toolbar>
                    <v-data-table
                      :search="search"
                      :headers="headers1"
                      :items="desserts_per.YiZhenBi"
                      class="elevation-1"
                    >
                      <template v-slot:items="props">
                        <td>{{ props.item.patient.patient_record_id }}</td>
                        <td>{{ props.item.patient.patient_name }}</td>
                      </template>
                    </v-data-table>
                  </v-card>
                  </div>
                  <div v-if="item.id == 'desserts_depart'">
                    <v-card flat >
                      <v-toolbar flat dense>
                        <v-toolbar-title>未诊断</v-toolbar-title>
                      </v-toolbar>
                      <v-data-table
                        :search="search"
                        :headers="headers1"
                        :items="desserts_depart.YiGuaHao"
                        class="elevation-1"
                      >
                        <template v-slot:items="props">
                          <td>{{ props.item.patient.patient_record_id }}</td>
                          <td>{{ props.item.patient.patient_name }}</td>
                        </template>
                      </v-data-table>
                    </v-card>
                    <v-card flat >
                      <v-toolbar flat dense>
                        <v-toolbar-title>已诊断</v-toolbar-title>
                      </v-toolbar>
                      <v-data-table
                        :search="search"
                        :headers="headers1"
                        :items="desserts_depart.YiZhenBi"
                        class="elevation-1"
                      >
                        <template v-slot:items="props">
                          <td>{{ props.item.patient.patient_record_id }}</td>
                          <td>{{ props.item.patient.patient_name }}</td>
                        </template>
                      </v-data-table>
                    </v-card>
                  </div>
                </v-tab-item>
              </v-tabs>
            </v-layout>
          </div>

        </v-expand-x-transition>
      </v-flex>
      <v-divider vertical></v-divider>
      <v-flex xs12 md12>
        <v-toolbar flat dense>
          <v-toolbar-side-icon
            @click.stop="show = !show"
          ></v-toolbar-side-icon>
          <v-toolbar-items>
            <v-menu offset-y>
              <template v-slot:activator="{ on }">
                <v-btn
                  flat
                  v-on="on"
                >
                  患者{{':'+patient.patient_name}}
                  <v-icon
                  >
                    arrow_drop_down
                  </v-icon>
                </v-btn>
              </template>
              <v-card>
                <v-list dense>
                  <v-list-tile>
                    <v-list-tile-content>姓名:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ patient.patient_name }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>性别:</v-list-tile-content>
                    <v-list-tile-content v-if="patient.patient_gender" class="align-end">男</v-list-tile-content>
                    <v-list-tile-content v-else class="align-end">女</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>年龄:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ patient.patient_age }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>出生日期:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ patient.patient_birthDate.slice(0,10) }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>身份证号:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ patient.patient_credit_id }}</v-list-tile-content>
                  </v-list-tile>
                </v-list>
              </v-card>
            </v-menu>
          </v-toolbar-items>
          <v-spacer/>
          <v-btn
            color="primary"
            small
            @click="finish"
          >
            诊毕
          </v-btn>
        </v-toolbar>
        <v-tabs
          v-model="active1"
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
              <div v-if="item.id == 'caseHistory'">
                <caseHistory :msgfromfa="message" :record="record"></caseHistory>
              </div>
              <div v-if="item.id == 'inspect'">
                <inspect :msgfromfa="message" :record="record"></inspect>
              </div>
              <div v-if="item.id == 'jianyan'">
                <jianyan :msgfromfa="message" :record="record"></jianyan>
              </div>
              <div v-if="item.id == 'diagnosis'">
                <diagnosis :msgfromfa="message" :record="record"></diagnosis>
              </div>
              <div v-if="item.id == 'dispose'">
                <dispose :msgfromfa="message" :record="record"></dispose>
              </div>
              <div v-if="item.id == 'medicine'">
                <medicine :msgfromfa="message" :record="record"></medicine>
              </div>
              <div v-if="item.id == 'drug'">
                <drug :msgfromfa="message" :record="record"></drug>
              </div>
              <div v-if="item.id == 'cost'">
                <cost :msgfromfa="message" :record="record"></cost>
              </div>
              <!--<router-view name="office"></router-view name="office">-->
            </v-card>
          </v-tab-item>
        </v-tabs>
      </v-flex>
      </v-layout>
    </v-card>
  </div>
</template>

<script>
import caseHistory from '@/pages/Diagnostician/case'
import inspect from '@/pages/Diagnostician/inspect'
import diagnosis from '@/pages/Diagnostician/diagnosis'
import dispose from '@/pages/Diagnostician/dispose'
import medicine from '@/pages/Diagnostician/medicine'
import drug from '@/pages/Diagnostician/drug'
import cost from '@/pages/Diagnostician/cost'
import jianyan from '@/pages/Diagnostician/jianyan'

export default {
  components: {
    caseHistory,
    inspect,
    diagnosis,
    dispose,
    medicine,
    drug,
    cost,
    jianyan
  },
  data () {
    return {
      dialog_add: false,
      dialog_err: false,
      dialog_suc: false,
      msg_suc: 'success',
      msg_err: 'error',
      dialog: false,
      e1: 1,
      disable: false,
      search: '',
      headers: [
        {
          text: '病历号',
          align: 'left',
          value: 'patient.patient_record_id'
        },
        { text: '患者名称', value: 'patient.patient_name' },
        { text: '操作', value: 'operation', sortable: false }
      ],
      headers1: [
        {
          text: '病历号',
          align: 'left',
          value: 'patient.patient_record_id'
        },
        { text: '患者名称', value: 'patient.patient_name' }
      ],
      active: '1',
      active1: '1',
      show: true,
      patient: {
        patient_record_id: '',
        patient_gender: '',
        patient_name: '',
        patient_credit_id: '',
        patient_birthDate: '',
        patient_address: '',
        patient_age: ''
      },
      record_id: '',
      desserts_per: '',
      desserts_depart: '',
      steps: 8,
      message: '',
      items1: [{
        name: '本人',
        id: 'desserts_per'
      },
      {
        name: '科室',
        id: 'desserts_depart'
      }],
      items: [{
        name: '病例首页',
        id: 'caseHistory'
      },
      {
        name: '检查申请',
        id: 'inspect'
      },
      {
        name: '检验申请',
        id: 'jianyan'
      },
      {
        name: '门诊确诊',
        id: 'diagnosis'
      },
      {
        name: '处置申请',
        id: 'dispose'
      },
      {
        name: '成药处方',
        id: 'medicine'
      },
      {
        name: '草药处方',
        id: 'drug'
      },
      {
        name: '费用查询',
        id: 'cost'
      }],
      record: ''
    }
  },
  computed: {
    depart_on () {
      return this.desserts_depart.filter(this.filterState_on)
    },
    depart_off () {
      return this.desserts_depart.filter(this.filterState_off)
    },
    per_on () {
      console.log(this.desserts_per.filter(this.filterState_on))
      return this.desserts_per.filter(this.filterState_on)
    },
    per_off () {
      return this.desserts_per.filter(this.filterState_off)
    }
  },
  watch: {
    dialog_suc (val) {
      if (!val) return
      setTimeout(() => (this.dialog_suc = false), 1000)
    },
    dialog (val) {
      if (!val) return
      setTimeout(() => (this.network_out), 10000)
    },
    dialog_err (val) {
      if (!val) return
      setTimeout(() => (this.dialog_err = false), 1000)
    }
  },
  mounted: function () {
    this.load_patient_self()
    this.load_patient_depart()
  },
  methods: {
    network_out: function () {
      this.dialog = false
      this.dialog_err = true
      this.msg_err = '网络不通畅，请重新来过'
    },
    filterState_on: function (value) {
      return value.register_info_state === '已挂号'
    },
    filterState_off: function (value) {
      return value.register_info_state === '诊毕'
    },
    load_patient_self: function () {
      var url = this.HOME + '/doctor/get-all-registers'
      var that = this
      var data = {
        'doctor_id': this.$store.state.user.id
      }
      that.dialog = true
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.desserts_per = response.data.data
          console.log(that.desserts_per)
        })
    },
    load_patient_depart: function () {
      var url = this.HOME + '/doctor/get-department-registers'
      var that = this
      var data = {
        'department_id': this.$store.state.user.department_id
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.desserts_depart = response.data.data
          console.log(that.desserts_depart)
          that.dialog = false
        })
    },
    treat: function (value) {
      var url = this.HOME + '/doctor/treat'
      var that = this
      that.message = value
      that.patient = value.patient
      that.record_id = value.register_info_id
      var data = {
        register_id: value.register_info_id
      }
      console.log(value)
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          if (response.data.code === 200) {
            that.load_patient_self()
            that.load_patient_depart()
            that.get()
            that.dialog_suc = true
            that.msg_suc = '接诊成功'
          } else {
            that.dialog_err = true
            that.msg_err = '接诊失败'
          }
        })
    },
    finish: function () {
      var url = this.HOME + '/doctor/finish'
      var that = this
      var data = {
        register_id: that.record_id
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          if (response.data.code === 200) {
            that.dialog_suc = true
            that.msg_suc = '已诊毕'
          }
        })
    },
    get: function () {
      let that = this
      var url = this.HOME + '/doctor/get-record'
      var data = {
        record_id: that.record_id
      }
      this.$http.post(url, data)
        .then(response => {
          console.log(response.data.data)
          that.record = response.data.data
        })
    },
    refresh: function () {
      this.patient_name = ''
    }
  }
}
</script>

<style scoped>

</style>
