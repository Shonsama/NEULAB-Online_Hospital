<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <v-layout>
      <v-flex xs4>
        <v-toolbar flat>
          <v-toolbar-title>患者查询</v-toolbar-title>
          <v-spacer/>
          <v-flex md6>
          <v-text-field
            v-model="search_patient"
            append-icon="search"
            label="搜索"
            single-line
            hide-details
            style="margin-bottom: 12px"
          ></v-text-field>
          </v-flex>
        </v-toolbar>
        <v-divider/>
        <v-data-table
          :headers="headers_patient"
          :items="desserts_patient.filter(filterPatients)"
          :search="search_patient"
        >
          <template v-slot:items="props">
            <td>{{ props.item.register_info_id }}</td>
            <td>{{ props.item.register_info_patient_id }}</td>
            <td>{{ props.item.patient.patient_name }}</td>
            <td>{{ props.item.register_info_state }}</td>
            <td>
              <v-btn small right icon flat color="primary" @click="getFee(props.item)" class="ml-3">
                查看
              </v-btn>
            </td>
          </template>
        </v-data-table>
      </v-flex>
      <v-divider vertical></v-divider>
      <v-flex>
        <v-toolbar flat>
          <v-toolbar-title>项目明细</v-toolbar-title>
        </v-toolbar>
        <v-divider/>
        <v-data-table
          :headers="headers"
          :items="desserts.filter(filterType)"
        >
          <template v-slot:items="props">
            <td>{{ props.item.code }}</td>
            <td>{{ props.item.name }}</td>
            <td>{{ props.item.state }}</td>
            <td>{{ props.item.type }}</td>
            <td>{{ props.item.number}}</td>
          </template>
        </v-data-table>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
export default {
  props: ['msgfromfa', 'record'],
  data () {
    return {
      search_patient: this.msgfromfa.register_info_id,
      headers_patient: [{
        text: '挂号ID',
        align: 'left',
        value: 'register_info_id'
      },
      {
        text: '病历号',
        value: 'register_info_patient_id'
      },
      {
        text: '患者',
        value: 'patient.patient_name'
      },
      {
        text: '状态',
        value: 'register_info_state'
      },
      {
        text: '操作',
        value: 'operation'
      }],
      desserts_patient: [],
      headers: [
        {
          text: 'ID',
          align: 'left',
          value: 'code'
        },
        {text: '名称', value: 'name'},
        {text: '状态', value: 'state'},
        {text: '类型', value: 'type'},
        {text: '金额', value: 'number'}
      ],
      desserts: [],
      headers_dia: [{
        text: '编码',
        align: 'left',
        value: 'disease_icd'
      },
      {
        text: '名称',
        value: 'disease_name'
      },
      {
        text: '分类',
        value: 'disease_type'
      }],
      desserts_dia: [],
      sum: ''
    }
  },
  mounted: function () {
    this.get()
    this.search_patient = this.msgfromfa.register_info_id
  },
  computed: {
    returnSum: function () {
      var sum = 0
      for (var i = 0; i < this.desserts; i++) {
        sum = sum + this.desserts[i].number
      }
      return sum
    }
  },
  watch: {
    record: function (newState, oldState) {
      this.get()
    }
  },
  methods: {
    filterType: function (value) {
      return value.state === '已开立' || value.state === '已发送'
    },
    filterPatients: function (value) {
      return value.register_info_state !== '已退号' && value.register_info_state !== '已挂号'
    },
    get: function () {
      let that = this
      var url = this.HOME + '/doctor/get-doctor-treated-registers?token=' + sessionStorage.getItem('token')
      var data = {
        doctor_id: that.msgfromfa.register_info_doctor_id
      }
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(response => {
          that.desserts_patient = response.data.data
          console.log(that.desserts_patient)
        })
    },
    getFee: function (value) {
      let that = this
      var url = this.HOME + '/doctor/get-all-prescription?token=' + sessionStorage.getItem('token')
      console.log(value)
      var data = {
        register_id: value.register_info_id
      }
      that.desserts = []
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(response => {
          console.log(response.data)
          for (var i = 0; i < response.data.data.length; i++) {
            var name = {
              code: response.data.data[i].prescription_id,
              name: response.data.data[i].prescription_name,
              state: response.data.data[i].prescription_execute_state,
              type: response.data.data[i].prescription_type,
              number: response.data.data[i].prescription_fee
            }
            that.desserts.push(name)
          }
        })
      var url1 = this.HOME + '/doctor/get-all-medical-skill?token=' + sessionStorage.getItem('token')
      this.$http.post(url1 + '?token=' + sessionStorage.getItem('token'), data)
        .then(response => {
          console.log(response.data)
          for (var i = 0; i < response.data.data.length; i++) {
            var name = {
              code: response.data.data[i].medical_skill_id,
              name: response.data.data[i].medical_skill_name,
              state: response.data.data[i].medical_skill_execute_state,
              type: response.data.data[i].medical_skill_type,
              number: response.data.data[i].medical_skill_fee
            }
            that.desserts.push(name)
          }
        })
    }
  }
}
</script>

<style scoped>

</style>
