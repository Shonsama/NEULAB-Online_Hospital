<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <v-layout>
      <v-flex xs4>
        <v-toolbar flat dense>
          <v-toolbar-title>患者查询</v-toolbar-title>
          <v-spacer/>
          <v-flex md6>
          <v-text-field
            v-model="search_patient"
            append-icon="search"
            label="病历号"
            hide-details
            style="margin-bottom: 15px"
          ></v-text-field>
          </v-flex>
        </v-toolbar>
        <v-divider/>
        <v-data-table
          :headers="headers_patient"
          :items="desserts_patient"
          :search="search_patient"
        >
          <template v-slot:items="props">
            <td>{{ props.item.register_info_id }}</td>
            <td>{{ props.item.register_info_patient_id }}</td>
            <td>{{ props.item.patient.patient_name }}</td>
            <td>
              <v-btn small right icon flat color="primary" @click="getPersonalMS(props.item)" class="ml-3">
                查看
              </v-btn>
            </td>
          </template>
        </v-data-table>
      </v-flex>
      <v-divider vertical></v-divider>
      <v-flex>
        <v-toolbar flat dense>
          <v-toolbar-title>检验项目</v-toolbar-title>
        </v-toolbar>
        <v-divider/>
        <v-data-table
          :headers="headers"
          :items="desserts"
        >
          <template v-slot:items="props">
            <td>{{ props.item.medical_skill_id }}</td>
            <td>{{ props.item.medical_skill_execute_state }}</td>
            <td>{{ props.item.medical_skill_result }}</td>
            <td>
              <v-icon
                small
                class="mr-2"
                @click="show =! show , fillForm(props.item)"
              >
                edit
              </v-icon>
            </td>
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
      search_patient: '',
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
        text: '操作',
        value: 'operation'
      }],
      desserts_patient: [],
      headers: [
        {
          text: '挂号ID',
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
      desserts_dia: []
    }
  },
  mounted: function () {
    this.get()
  },
  computed: {

  },
  watch: {
    record: function (newState, oldState) {
      this.get()
    }
  },
  methods: {
    get: function () {
      let that = this
      var url = this.HOME + '/doctor/get-doctor-treated-registers'
      var data = {
        doctor_id: that.msgfromfa.register_info_doctor_id
      }
      this.$http.post(url, data)
        .then(response => {
          that.desserts_patient = response.data.data
          console.log(that.desserts_patient)
        })
    },
    getFee: function (value) {
      let that = this
      var url = this.HOME + '/doctor/get-fee-records'
      var data = {
        patient_record_id: that.msgfromfa.register_info_doctor_id
      }
      this.$http.post(url, data)
        .then(response => {
          that.desserts_patient = response.data.data
          console.log(that.desserts_patient)
        })
    }
  }
}
</script>

<style scoped>

</style>
