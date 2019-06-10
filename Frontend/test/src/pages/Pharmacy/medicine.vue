<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <v-dialog
      v-model="show"
    >
      <v-card>
        <v-toolbar flat>
          <v-toolbar-title>退药</v-toolbar-title>
          <v-spacer/>
        </v-toolbar>
        <v-data-table
          v-model="selected_refund_medicine"
          :headers="headers_refund_medicine"
          :items="desserts_refund_medicine"
          item-key="medicine_id"
          select-all
          class="scroll-y"
          hide-actions
        >
          <template v-slot:items="props">
            <td>
              <v-checkbox
                v-model="props.selected"
                primary
                hide-details
              ></v-checkbox>
            </td>
            <td>{{ props.item.medicine_id }}</td>
            <td>{{ props.item.medicine_name }}</td>
            <td>{{ props.item.medicine_usage }}</td>
            <td>{{ props.item.medicine_specifications }}</td>
            <td>{{ props.item.medicine_unit }}</td>
            <td>{{ props.item.medicine_factory }}</td>
            <td>{{ props.item.medicine_formulation }}</td>
            <td>{{ props.item.medicine_type }}</td>
            <td>{{ props.item.medicine_unit_price }}</td>
            <td>{{ props.item.medicine_pinyin}}</td>
          </template>
        </v-data-table>
        <v-divider/>
        <v-card-actions>
          <v-layout align-center justify-center row fill-height>
            <v-flex xs2>
              <v-text-field
                v-model="rule_name_create"
                label="规则名称"
                required
              ></v-text-field>
            </v-flex>
            <v-btn
              color="primary"
              style="margin-left:20px;margin-right:10px"
              @click="add_Rule"
            >
              保存
            </v-btn>
          </v-layout>
        </v-card-actions>
      </v-card>
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
        >
          <template v-slot:items="props">
            <td>{{ props.item.prescription_id }}</td>
            <td>{{ props.item.prescription_name }}</td>
            <td>{{ props.item.prescription_execute_state }}</td>
            <td>{{ props.item.prescription_type }}</td>
            <td>{{ props.item.prescription_fee }}</td>
            <td>
              <div v-if="!mode_refund">
                <v-btn right small color="primary" @click="sendMedicine(props.item)">
                  领药
                </v-btn>
              </div>
              <div v-else>
                <v-btn right small color="primary" @click="show = !show">
                  退药
                </v-btn>
              </div>
            </td>
          </template>
        </v-data-table>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer/>
          <el-date-picker
            v-model="date"
            type="datetimerange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            style="margin-right: 10px"
          >
          </el-date-picker>
          <v-btn color="primary" @click="mode_refund = true , search_refund">已领取搜索</v-btn>
        </v-card-actions>
        <v-divider></v-divider>

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
      mode_refund: false,
      date: ['', ''],
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
        this.mode_refund = false
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
      },
      search_refund: function () {
        let that = this
        var url = this.HOME + 'md-doctor/get-sent-prescription'
        this.$http.post(url, {
          patient_id: that.md_patient_id,
          start_time: that.date[0],
          end_time: that.date[1]
        })
          .then(function (response) {
            console.log(response.data)
            that.load()
            var item = {
              patient_record_id: that.md_patient_id
            }
            that.getPersonalMS(item)
          })

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
