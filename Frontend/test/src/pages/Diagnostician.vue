<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <v-card >
    <v-layout>
      <v-flex shrink >
        <v-expand-x-transition >
          <div v-show="show" style="white-space: nowrap; width:300px">
            <v-toolbar flat>
              <v-flex md9 xs9>
                <v-text-field
                  v-model="patient_name"
                  prepend-inner-icon="account_box"
                  name="login"
                  label="选择患者"
                  type="text"
                  disabled>
                </v-text-field>
              </v-flex>
              <v-btn
                flat
                color="primary"
                icon
                @click="refresh"
              >
                <v-icon>cached</v-icon>
              </v-btn>
            </v-toolbar>
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
                      <v-toolbar-title>已诊断</v-toolbar-title>
                    </v-toolbar>
                    <v-data-table
                      v-model="selected"
                      :search="search"
                      :headers="headers1"
                      :items="desserts_per.off"
                      item-key="register_info_patient_id"
                      class="elevation-1"
                    >
                      <template v-slot:items="props">
                        <td>{{ props.item.register_info_patient_id }}</td>
                        <td>{{ props.item.register_info_patient_id }}</td>
                      </template>
                    </v-data-table>
                  </v-card>
                    <v-card flat >
                    <v-toolbar flat dense>
                      <v-toolbar-title>未诊断</v-toolbar-title>
                    </v-toolbar>
                    <v-data-table
                      v-model="selected"
                      :search="search"
                      :headers="headers"
                      :items="desserts_per.on"
                      item-key="register_info_patient_id"
                      class="elevation-1"
                    >
                      <template v-slot:items="props">
                        <td>{{ props.item.register_info_patient_id }}</td>
                        <td>{{ props.item.register_info_patient_id }}</td>
                        <td>
                          <v-btn
                            color="primary"
                            small
                            :disabled = "disable"
                            append-icon="search"
                            @click="treat(props.item.register_info_patient_id)"
                          >
                            接诊
                          </v-btn>
                        </td>
                      </template>
                    </v-data-table>
                  </v-card>
                  </div>
                  <div v-if="item.id == 'desserts_depart'">
                    <v-card flat >
                      <v-toolbar flat dense>
                        <v-toolbar-title>已诊断</v-toolbar-title>
                      </v-toolbar>
                      <v-data-table
                        v-model="selected"
                        :search="search"
                        :headers="headers1"
                        :items="desserts_depart.off"
                        item-key="register_info_patient_id"
                        class="elevation-1"
                      >
                        <template v-slot:items="props">
                          <td>{{ props.item.register_info_patient_id }}</td>
                          <td>{{ props.item.register_info_patient_id }}</td>
                        </template>
                      </v-data-table>
                    </v-card>
                    <v-card flat >
                      <v-toolbar flat dense>
                        <v-toolbar-title>未诊断</v-toolbar-title>
                      </v-toolbar>
                      <v-data-table
                        v-model="selected"
                        :search="search"
                        :headers="headers1"
                        :items="desserts_depart.on"
                        item-key="register_info_patient_id"
                        class="elevation-1"
                      >
                        <template v-slot:items="props">
                          <td>{{ props.item.register_info_patient_id }}</td>
                          <td>{{ props.item.register_info_patient_id }}</td>
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
        <v-toolbar flat>
          <v-toolbar-side-icon
            @click.stop="show = !show"
          ></v-toolbar-side-icon>
          <v-toolbar-title>患者:{{patient_name}}</v-toolbar-title>
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
                <caseHistory :msgfromfa="patient_record_id"></caseHistory>
              </div>
              <div v-if="item.id == 'inspect'">
                <inspect :msgfromfa="patient_record_id"></inspect>
              </div>
              <div v-if="item.id == 'jianyan'">
                <jianyan :msgfromfa="patient_record_id"></jianyan>
              </div>
              <div v-if="item.id == 'diagnosis'">
                <diagnosis :msgfromfa="patient_record_id"></diagnosis>
              </div>
              <div v-if="item.id == 'dispose'">
                <dispose :msgfromfa="patient_record_id"></dispose>
              </div>
              <div v-if="item.id == 'medicine'">
                <medicine :msgfromfa="patient_record_id"></medicine>
              </div>
              <div v-if="item.id == 'drug'">
                <drug :msgfromfa="patient_record_id"></drug>
              </div>
              <div v-if="item.id == 'cost'">
                <cost :msgfromfa="patient_record_id"></cost>
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
      e1: 1,
      disable: false,
      search: '',
      headers: [
        {
          text: '病历号',
          align: 'left',
          value: 'register_info_id'
        },
        { text: '患者名称', value: 'register_info_patient_id' },
        { text: '操作', value: 'operation', sortable: false }
      ],
      headers1: [
        {
          text: '病历号',
          align: 'left',
          value: 'register_info_id'
        },
        { text: '患者名称', value: 'register_info_patient_id' }
      ],
      active: '1',
      active1: '1',
      show: true,
      patient_record_id: '',
      patient_gender: '',
      patient_name: '',
      patient_credit_id: '',
      patient_birthDate: '',
      patient_address: '',
      patient_age: '',
      desserts_per: {
        on: [],
        off: []
      },
      desserts_depart: {
        on: [],
        off: []
      },
      steps: 8,
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
      }]
    }
  },
  mounted: function () {
    this.load_patient_self()
    this.load_patient_depart()
  },
  methods: {
    load_patient_self: function () {
      var url = this.HOME + '/doctor/get-all-registers'
      var that = this
      var data = {
        'doctor_id': '1'
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          let i
          for (i = 0; i < response.data.data.length; i++) {
            if (response.data.data[i].register_info_state === '已挂号') {
              that.desserts_per.on.push(response.data.data[i])
            }
            if (response.data.data[i].register_info_state === '诊毕') {
              that.desserts_per.off.push(response.data.data[i])
            }
          }
        })
      console.log(this.desserts_per)
    },
    load_patient_depart: function () {
      var url = this.HOME + '/doctor/get-department-registers'
      var that = this
      var data = {
        'department_id': '1'
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          let i
          for (i = 0; i < response.data.data.length; i++) {
            if (response.data.data[i].register_info_state === '已挂号') {
              that.desserts_depart.on.push(response.data.data[i])
            }
            if (response.data.data[i].register_info_state === '诊毕') {
              that.desserts_depart.off.push(response.data.data[i])
            }
          }
        })
      console.log(this.desserts_depart)
    },
    treat: function (value) {
      var url = this.HOME + '/doctor/treat'
      var that = this
      var data = {
        'register_id': '1'
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.patient_name = value
        })
    },
    finish: function () {
      var url = this.HOME + '/doctor/finish'
      // var that = this
      var data = {
        'register_id': '1'
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
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
