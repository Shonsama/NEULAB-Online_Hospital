<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
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
                v-model="md_id"
                :error-messages="errorMessages"
                label="医技ID"
                placeholder="请输入医技ID"
                :disabled="true"
                required
              ></v-text-field>
              <v-select
                ref="country"
                :items="state_items"
                v-model="state"
                label="医技状态"
                placeholder="请输入医技状态"
                required
              ></v-select>
              <v-text-field
                ref="country"
                v-model="result"
                label="医技结果"
                placeholder="请输入医技结果"
                required
              ></v-text-field>
            </v-card-text>
            <!--<v-divider class="mt-5"></v-divider>-->
            <v-card-actions>
              <v-btn flat @click="show =!show">Cancel</v-btn>
              <v-spacer></v-spacer>
              <!--<v-slide-x-reverse-transition>-->
              <!--<v-tooltip-->
              <!--v-if="formHasErrors"-->
              <!--left-->
              <!--&gt;-->
              <!--<template v-slot:activator="{ on }">-->
              <!--<v-btn-->
              <!--icon-->
              <!--class="my-0"-->
              <!--@click="resetForm"-->
              <!--v-on="on"-->
              <!--&gt;-->
              <!--<v-icon>refresh</v-icon>-->
              <!--</v-btn>-->
              <!--</template>-->
              <!--<span>Refresh form</span>-->
              <!--</v-tooltip>-->
              <!--</v-slide-x-reverse-transition>-->

              <v-btn color="primary" flat @click="change">add</v-btn>

            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </v-dialog>

    <v-layout>
      <v-flex xs4>
        <v-card flat>
          <v-form v-model="valid">
            <v-toolbar flat dense>
              <v-toolbar-title>患者查询</v-toolbar-title>
            </v-toolbar>
            <v-layout>
              <v-text-field
                v-model="search_patient"
                append-icon="search"
                label="病历号"
                single-line
                hide-details
                style="margin-left: 10px"
              ></v-text-field>
              <v-btn color="primary" style="margin-top: 10px">
                搜索
              </v-btn>
            </v-layout>
          </v-form>
        </v-card>
        <v-card flat>
          <v-card-title >患者收费列表</v-card-title>
          <v-data-table
            :headers="headers_patient"
            :items="desserts_patient"
            :search="search_patient"
            class="elevation-1"
          >
            <template v-slot:items="props">
              <td>{{ props.item.patient_record_id }}</td>
              <td>{{ props.item.patient_name }}</td>
              <td >
                <v-btn right small icon flat color="primary" @click="getPersonalMD(props.item)">
                  <v-icon>check_circle</v-icon>
                </v-btn>
              </td>
            </template>
          </v-data-table>
        </v-card>
      </v-flex>
      <v-divider vertical></v-divider>
      <v-flex>
        <v-toolbar flat dense>
          <v-toolbar-title>检验项目</v-toolbar-title>
        </v-toolbar>
        <v-data-table
          :headers="headers_md"
          :items="desserts_md"
          class="elevation-1"
        >
          <template v-slot:items="props">
            <td>{{ props.item.prescription_id }}</td>
            <td>{{ props.item.prescription_name }}</td>
            <td>{{ props.item.prescription_execute_state }}</td>
            <td>{{ props.item.prescription_type }}</td>
            <td>{{ props.item.prescription_fee }}</td>
            <td>
              <!--<v-icon-->
                <!--small-->
                <!--class="mr-2"-->
                <!--@click="show =! show , fillForm(props.item)"-->
              <!--&gt;-->
                <!--edit-->
              <!--</v-icon>-->
              <v-btn right small color="primary" @click="sendMedicine(props.item)">
                领药
              </v-btn>
            </td>
          </template>
        </v-data-table>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
export default {
    data: () => ({
      // alert_success: false,
      // alert_error: false,
      // mode: true,
      // department_id: '',
      // department_name: '',
      // department_cat: '',
      // department_type: '',
      // show: false,
      // search: '',
      // expand: false,
      // selected: [],
      md_item: '',
      md_patient_id: '',
      show: false,
      department_default: '血液科',
      search_patient: '',
      state: '',
      md_id: '',
      result:'',
      signal: '',
      state_items: [
        {
          text: '已取消执行'
        },
        {
          text: '已确认执行'
        }
      ],
      headers_patient: [
        {
          text: '病人id',
          align: 'left',
          value: 'patient_record_id'
        },
        { text: '病人姓名', value: 'patient_name' },
        { text: '操作', value: 'operation', sortable: false }
      ],
      desserts_patient: [
        {
          patient_record_id: 1,
          patient_name: 'shu'
        }
      ],
      headers_md: [
        {
          text: '处方id',
          align: 'left',
          value: 'prescription_id'
        },
        { text: '处方名称', value: 'prescription_name' },
        { text: '处方状态', value: 'prescription_execute_state' },
        { text: '处方类型', value: 'prescription_type' },
        { text: '费用', value: 'prescription_fee' },
        { text: '操作', value: 'operation', sortable: false }
      ],
      desserts_md: [
        {
          prescription_id: 1,
          prescription_name: '这是处方',
          prescription_execute_state: '已缴费',
          prescription_type: '中药',
          prescription_fee: '25.00'
        }
      ]
    }),
    methods: {
      load: function () {
        let that = this
        var url = this.HOME + 'md-doctor/get-all-patients'
        this.$http.post(url, {
        })
          .then(function (response) {
            console.log(response.data)
            that.desserts_patient = response.data.data
          })
      },
      getPersonalMD: function (item) {
        this.md_patient_id = item.patient_record_id
        let that = this
        var url = this.HOME + 'md-doctor/get-prescription-by-patient'
        this.$http.post(url, {
          register_info_patient_id: item.patient_record_id
        })
          .then(function (response) {
            console.log(response.data)
            that.desserts_md = response.data.data
          })
      },
      sendMedicine: function (item) {
        let that = this
        var url = this.HOME + 'md-doctor/send-medicine'
        this.$http.post(url, {
          prescription_id: item.prescription_id
        })
          .then(function (response) {
            console.log(response.data)
            var item = {
              patient_record_id: that.md_patient_id
            }
            that.getPersonalMD(item)
          })
      },
      setResult: function () {
        let that = this
        var url = this.HOME + 'ms-doctor/medical-skill/add-result'
        this.$http.post(url, {
          medical_skill_id: that.md_id,
          medical_skill_result: that.result
        })
          .then(function (response) {
            console.log(response.data)
            that.load()
            var item = {
              patient_record_id: that.md_patient_id
            }
            that.getPersonalMS(item)
          })
      },
      confirmState: function (){
        let that = this
        var url = this.HOME + 'ms-doctor/medical-skill/confirm'
        this.$http.post(url, {
          medical_skill_id: that.md_id
        })
          .then(function (response) {
            console.log(response.data)
            that.load()
            var item = {
              patient_record_id: that.md_patient_id
            }
            that.getPersonalMS(item)
          })
      },
      cancelState: function (){
        let that = this
        var url = this.HOME + 'ms-doctor/medical-skill/cancel'
        this.$http.post(url, {
          medical_skill_id: that.md_id
        })
          .then(function (response) {
            console.log(response.data)
            that.load()
            var item = {
              patient_record_id: that.md_patient_id
            }
            that.getPersonalMS(item)
          })
      },
      change: function () {
        if (this.state !== this.md_item.medical_skill_execute_state) {
          if (this.state === '已确认执行') {
            this.confirmState()
          }
          if (this.state === '已取消执行') {
            this.cancelState()
          }
        }
        if (this.result !== this.md_item.medical_skill_result) {
          this.setResult()
        }
        this.show = !this.show
      },
      eraseForm: function () {
        this.md_id = ''
        this.result = ''
        this.state = ''
        this.md_item = ''
      },
      fillForm: function (item) {
        this.md_item = item
        this.md_id = item.medical_skill_id
        this.result = item.medical_skill_result
        this.state = item.medical_skill_execute_state
      }
    },
    mounted: function () {
      this.load()
    },
    watch: {
      show: function (newState, oldState) {
        if (newState === false) {
          this.eraseForm()
        }
      }
    }
  }
</script>

<style scoped>

</style>
