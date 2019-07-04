<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div class="scroll-y">
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
    <v-dialog
      v-model="show"
      max-width="300"
    >
      <v-card ref="form">
        <v-card-text>
          <v-select
            v-model="quantity_sub"
            :items="returnArray(quantity)"
            label="数量"
            required
          ></v-select>
        </v-card-text>
        <v-divider class="mt-2"></v-divider>
        <v-card-actions>
          <v-btn flat @click="show = !show">取消</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="primary" flat @click="returnItemMed">确定</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog
      v-model="dialog_bill"
      hide-overlay
      width="600"
    >
      <v-layout justify-center>
        <v-flex>
          <v-card>
            <div ref="printThem">
              <v-card-title>
                <img src="../icon/hospital.png" height="180" width="180"/>
                <v-spacer/>
                <v-list dense>
                  <v-list-tile>
                    <v-list-tile-content><v-text class="title">东软云医院</v-text></v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>联系电话:</v-list-tile-content>
                    <v-list-tile-content class="align-end">024-8366 2222</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>医院地址:</v-list-tile-content>
                    <v-list-tile-content class="align-end">辽宁省沈阳市浑南新区新秀街2号东软软件园A9座</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>公交路线:</v-list-tile-content>
                    <v-list-tile-content class="align-end">乘坐154路至“唯美品格”站或150路至“东海电子”站</v-list-tile-content>
                  </v-list-tile>
                </v-list>
                <v-list dense>
                  <v-list-tile>
                    <v-list-tile-content>发票号:</v-list-tile-content>
                    <v-list-tile-content style="margin-left: 20px" class="align-end"><el-input v-model="bill.bill_id" placeholder="请输入内容" size="mini"></el-input></v-list-tile-content>
                  </v-list-tile>
                </v-list>
                <v-layout justify-space-between>
                  <v-list dense>
                    <v-list-tile>
                      <v-list-tile-content>挂号号:</v-list-tile-content>
                      <v-list-tile-content class="align-end">{{ bill.bill_register_id }}</v-list-tile-content>
                    </v-list-tile>
                  </v-list>
                  <v-list dense>
                    <v-list-tile>
                      <v-list-tile-content>患者姓名:</v-list-tile-content>
                      <v-list-tile-content class="align-end">{{ patient_name }}</v-list-tile-content>
                    </v-list-tile>
                  </v-list>
                  <v-list dense>
                    <v-list-tile>
                      <v-list-tile-content>病历号:</v-list-tile-content>
                      <v-list-tile-content class="align-end">{{ patient_record_id }}</v-list-tile-content>
                    </v-list-tile>
                  </v-list>
                  <v-list dense>
                    <v-list-tile>
                      <v-list-tile-content>收费员ID:</v-list-tile-content>
                      <v-list-tile-content class="align-end">{{ bill.bill_user_id }}</v-list-tile-content>
                    </v-list-tile>
                  </v-list>
                </v-layout>
                <v-layout justify-space-between>
                  <v-list dense>
                    <v-list-tile>
                      <v-list-tile-content>发票类型:</v-list-tile-content>
                      <v-list-tile-content class="align-end">{{ bill.bill_state }}</v-list-tile-content>
                    </v-list-tile>
                  </v-list>
                  <v-list dense>
                    <v-list-tile>
                      <v-list-tile-content>收费类别:</v-list-tile-content>
                      <v-list-tile-content class="align-end">{{ bill.bill_type }}</v-list-tile-content>
                    </v-list-tile>
                  </v-list>
                  <v-list dense>
                    <v-list-tile>
                      <v-list-tile-content>收费总额:</v-list-tile-content>
                      <v-list-tile-content class="align-end">{{ bill.bill_sum }}</v-list-tile-content>
                    </v-list-tile>
                  </v-list>
                  <v-list dense>
                    <v-list-tile>
                      <v-list-tile-content>打印时间:</v-list-tile-content>
                      <v-list-tile-content class="align-end">{{ bill.bill_time }}</v-list-tile-content>
                    </v-list-tile>
                  </v-list>
                </v-layout>
              </v-card-title>
              <v-data-table
              v-model="selected"
              :headers="headers_bill"
              :items="list"
              hide-actions
              >
              <template v-slot:items="props">
                <td>{{ bill_info.name }}</td>
                <td>{{ bill_info.type }}</td>
                <td>{{ bill_info.number }}</td>
                <td>{{ bill_info.quantity}}</td>
              </template>
              </v-data-table>
            </div>
            <v-btn style="margin-left: 260px;margin-bottom: 10px" @click="$print($refs.printThem)" color="primary">打印发票</v-btn>
          </v-card>
        </v-flex>
      </v-layout>
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
    <v-card class="scroll-y">
      <v-toolbar extended flat dense>
        <v-toolbar-title>收费信息</v-toolbar-title>
        <template v-slot:extension>
          <v-flex xs4 lg2>
            <v-text-field v-model="bill.bill_id" prepend-inner-icon="assignment" name="login" label="发票号" type="text"
            ></v-text-field>
          </v-flex>
          <v-btn
            small
            class="ml-4"
            color="primary"
            @click="reprint_bill"
          >
            补打
            <v-icon small right>
              print
            </v-icon>
          </v-btn>
          <v-btn
            small
            color="primary"
            @click="overprint"
          >
            重打
            <v-icon small right>
              refresh
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
              md4
              lg3
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
          <v-layout wrap>
            <v-flex
              xs12
              md4
              lg2
            >
              <v-text-field
                v-model="patient_name"
                label="姓名"
                required
                readonly
              ></v-text-field>
            </v-flex>

            <v-flex
              xs12
              md4
              lg2
            >
              <v-text-field
                v-model="patient_gender"
                label="性别"
                required
                readonly
              ></v-text-field>
            </v-flex>

            <v-flex
              xs12
              md4
              lg4
            >
              <v-textarea
                v-model="patient_address"
                label="家庭住址"
                readonly
                rows="1"
              ></v-textarea>
            </v-flex>

            <v-flex
              xs12
              md4
              lg4
            >
              <v-text-field
                v-model="patient_credit_id"
                :counter="18"
                label="身份证号"
                readonly
                required
              ></v-text-field>
            </v-flex>
            <v-flex
              xs12
              md4
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
                  ></v-text-field>
                </template>
                <v-date-picker v-model="patient_birthDate" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn flat color="primary" @click="menu = false">取消</v-btn>
                  <v-btn flat color="primary" @click="$refs.menu.save(patient_birthDate)">确定</v-btn>
                </v-date-picker>
              </v-menu>
            </v-flex>

            <v-flex
              xs12
              md4
              lg2
            >
              <v-text-field
                v-model="patient_age"
                label="年龄"
                readonly
                required
              ></v-text-field>
            </v-flex>
          </v-layout>
        </v-container>
      </v-form>
    </v-card>
    <v-card class="scroll-y">
      <v-toolbar flat dense>
        <v-toolbar-title >患者交费</v-toolbar-title>
      </v-toolbar>
      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="desserts"
        item-key="id"
        class="elevation-1"
      >
        <template v-slot:items="props">
          <td>{{ props.item.code }}</td>
          <td>{{ props.item.name }}</td>
          <td>{{ props.item.state }}</td>
          <td>{{ props.item.type }}</td>
          <td>{{ props.item.number }}</td>
          <td>
            <v-btn
              small
              flat
              right
              icon
              class="ml-3"
              color="primary"
              @click="chargeItem(props.item)"
            >
              收费
            </v-btn>
          </td>
        </template>
      </v-data-table>
    </v-card>
    <v-card class="scroll-y">
      <v-toolbar flat extended dense>
        <v-toolbar-title >患者收费信息</v-toolbar-title>
        <template v-slot:extension>
          <el-date-picker
            v-model="date"
            type="datetimerange"
            align="right"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            :default-time="['12:00:00', '08:00:00']"
            style="margin-right: 5px;margin-bottom: 10px"
          >
          </el-date-picker>
        </template>
      </v-toolbar>
      <v-data-table
        v-model="selected1"
        :headers="headers1"
        :items="filterDesserts"
        item-key="hint"
        class="elevation-1 scroll-y"
      >
        <template v-slot:items="props">
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
              icon
              flat
              v-if="props.item.type === '检查' || props.item.type === '检验' || props.item.type === '处置' || props.item.state === '已退药' || props.item.state === '已领药并部分退药' || props.item.state === '未领药并部分退药'"
              class="ml-3"
              color="primary"
              @click="returnItem(props.item)"
            >
              退费
            </v-btn>
            <v-icon
              v-else
              @click="props.expanded = !props.expanded, getContent(props.item), prescription = props.item"
            >
              remove_red_eye
            </v-icon>
          </td>
        </template>
        <template v-slot:expand="props">
          <v-data-table
            v-model="selected_con"
            :headers="headers_con"
            :items="desserts_con"
            item-key="hint"
            select-all
            v-if="!(props.item.type === '检查' || props.item.type === '检验' || props.item.type === '处置' || props.item.state === '已退药')"
            class="elevation-1 scroll-y"
          >
            <template v-slot:items="props">
              <td>
                <v-checkbox
                  v-model="props.selected"
                  primary
                  hide-details
                ></v-checkbox>
              </td>
              <td>{{ props.item.medicine.medicine_name }}</td>
              <td>{{ props.item.prescription_medicine_id }}</td>
              <td>{{ props.item.prescription_unit_price }}</td>
              <td>
                {{props.item.prescription_refund_available_num}}
              </td>
              <td>
                <v-btn
                  small
                  right
                  icon
                  flat
                  class="ml-3"
                  color="primary"
                  @click="show = !show, medicine = props.item, quantity = props.item.prescription_refund_available_num"
                >
                  退费
                </v-btn>
              </td>
            </template>
          </v-data-table>
        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
export default {
  data () {
    return {
      bill: {},
      date: ['', ''],
      show: false,
      dialog: '',
      dialog_add: false,
      dialog_err: false,
      dialog_suc: false,
      dialog_bill: false,
      msg_suc: 'success',
      msg_err: 'error',
      selected: [],
      selected1: [],
      selected_con: [],
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
      list: ['1'],
      bill_info: {
        name: '',
        type: '',
        number: '',
        quantity: ''
      },
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
      headers_bill: [
        {text: '名称', value: 'name'},
        {text: '类型', value: 'type'},
        {text: '金额', value: 'number'},
        {text: '数量', value: 'quantity'}
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
      ],
      headers_con: [
        {text: '名称', value: 'medicine.medicine_name'},
        {text: '编码', value: 'prescription_medicine_id'},
        {text: '金额', value: 'prescription_unit_price'},
        {text: '数量', value: 'prescription_refund_available_num'},
        {text: '操作', value: 'operation', sortable: false}
      ],
      desserts_con: [],
      content: [],
      quantity: '',
      quantity_sub: '',
      medicine: '',
      prescription: ''
    }
  },
  computed: {
    filterDesserts () {
      return this.desserts1.filter(this.filterDate)
    }
  },
  watch: {
    dialog_suc (val) {
      if (!val) return
      setTimeout(() => (this.dialog_suc = false), 1000)
    },
    dialog (val) {
      if (!val) return
      setTimeout(() => (this.dialog = false), 3000)
    },
    dialog_err (val) {
      if (!val) return
      setTimeout(() => (this.dialog_err = false), 1000)
    }
  },
  mounted: function () {
  },
  methods: {
    returnArray: function (value) {
      var arr = []
      for (var i = 1; i <= value; i++) {
        arr.push(i)
      }
      return arr
    },
    filterDate: function (value) {
      return ((this.date[0] <= new Date(value.time) && this.date[1] >= new Date(value.time)) || this.date[0] === '' || this.date[1] === '')
    },
    chargeItem: function (value) {
      let that = this
      console.log(value)
      var url = this.HOME + '/pay/pay'
      var data = {
        id: value.id,
        type: value.type,
        user_id: this.$store.state.user.id,
        register_id: value.code
      }
      that.bill_info = {
        name: value.name,
        type: value.type,
        number: value.number,
        quantity: 1
      }
      that.dialog = true
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          if (response.data.code === 200) {
            that.bill = response.data.data
            that.dialog_bill = true
            that.getItem()
            that.getItem_charge()
            that.dialog = false
            that.dialog_suc = true
            that.msg_suc = '缴费成功'
          } else {
            that.dialog_err = true
            that.msg_err = '缴费失败'
          }
        })
    },
    returnItem: function (value) {
      console.log(value)
      let that = this
      var url = this.HOME + '/user-service/refund'
      var data = {
        id: value.id,
        type: value.type
      }
      var quantity = 1
      if (that.quantity_sub !== '') {
        quantity = that.quantity_sub
      }
      that.bill_info = {
        name: value.name,
        type: value.type,
        number: value.number,
        quantity: quantity
      }
      that.quantity_sub = ''
      that.dialog = true
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          if (response.data.code === 200) {
            that.bill = response.data.data
            that.dialog_bill = true
            that.getItem()
            that.dialog = false
            that.dialog_suc = true
            that.msg_suc = '退费成功'
          } else {
            that.dialog_err = true
            that.msg_err = '退费失败'
          }
        })
    },
    returnItemMed: function () {
      let that = this
      that.bill_info = {
      }
      var url = this.HOME + '/user-service/refund/return-prescription'
      var data = {
        prescription_id: that.prescription.id,
        prescription_content_id: that.medicine.prescription_content_id,
        prescription_num: that.quantity_sub
      }
      var value = {
        name: that.medicine.medicine.medicine_name,
        id: that.prescription.id,
        type: that.prescription.type,
        number: that.medicine.prescription_unit_price
      }
      console.log(data)
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          that.returnItem(value)
          that.show = !that.show
          // that.getItem()
        })
    },
    getContent: function (value) {
      console.log(value)
      let that = this
      var url = this.HOME + '/user-service/refund/prescription/get-content'
      var data = {
        prescription_id: value.id
      }
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          that.desserts_con = response.data.data
        })
    },
    getItem: function () {
      var url = this.HOME + '/pay/get-medical-skill-canceled-or-paid'
      var url1 = this.HOME + '/pay/get-prescription-returned-or-paid'
      var that = this
      var data = {
        'register_info_patient_id': that.patient_record_id
      }
      that.desserts1 = []
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          var i
          for (i = 0; i < response.data.data.length; i++) {
            var data = {
              id: response.data.data[i].medical_skill_id,
              code: response.data.data[i].medical_skill_register_info_id,
              name: response.data.data[i].medical_skill_name,
              state: response.data.data[i].medical_skill_execute_state,
              type: response.data.data[i].medical_skill_type,
              number: response.data.data[i].medical_skill_fee,
              time: response.data.data[i].medical_skill_pay_time,
              hint: response.data.data[i].medical_skill_id + response.data.data[i].medical_skill_type
            }
            console.log(data)
            that.desserts1.push(data)
          }
        })
      this.$http.post(url1 + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          var i
          for (i = 0; i < response.data.data.length; i++) {
            var data = {
              id: response.data.data[i].prescription_id,
              code: response.data.data[i].prescription_register_info_id,
              name: response.data.data[i].prescription_name,
              state: response.data.data[i].prescription_execute_state,
              type: response.data.data[i].prescription_type,
              number: response.data.data[i].prescription_fee,
              time: response.data.data[i].prescription_pay_time,
              hint: response.data.data[i].prescription_id + response.data.data[i].prescription_type
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
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          var i
          for (i = 0; i < response.data.data.length; i++) {
            var data = {
              id: response.data.data[i].medical_skill_id,
              code: response.data.data[i].medical_skill_register_info_id,
              name: response.data.data[i].medical_skill_name,
              state: response.data.data[i].medical_skill_execute_state,
              type: response.data.data[i].medical_skill_type,
              number: response.data.data[i].medical_skill_fee,
              hint: response.data.data[i].medical_skill_id + response.data.data[i].medical_skill_type
            }
            console.log(data)
            that.desserts.push(data)
          }
        })
      this.$http.post(url1 + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          var i
          for (i = 0; i < response.data.data.length; i++) {
            var data = {
              id: response.data.data[i].prescription_id,
              code: response.data.data[i].prescription_register_info_id,
              name: response.data.data[i].prescription_name,
              state: response.data.data[i].prescription_execute_state,
              type: response.data.data[i].prescription_type,
              number: response.data.data[i].prescription_fee,
              hint: response.data.data[i].prescription_id + response.data.data[i].prescription_type
            }
            console.log(data)
            that.desserts.push(data)
          }
        })
    },
    reprint_bill: function () {
      let that = this
      var url = this.HOME + '/bill/reprint'
      var data = {
        bill_id: that.bill.bill_id
      }
      if (that.bill.bill_id) {
        this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
          .then(function (response) {
            that.bill = response.data.data
            console.log(response.data)
            that.dialog_bill = true
            that.dialog_suc = true
            that.msg_suc = '发票号补打成功'
          })
      } else {
        this.dialog_err = true
        this.msg_err = '发票号为空'
      }
    },
    overprint: function () {
      let that = this
      var url = this.HOME + '/bill/overprint'
      var data = {
        bill_id: that.bill.bill_id
      }
      if (that.bill.bill_id) {
        this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
          .then(function (response) {
            that.bill = response.data.data
            that.dialog_bill = true
            that.dialog_suc = true
            that.msg_suc = '发票号重打成功'
            console.log(response.data)
          })
      } else {
        this.dialog_err = true
        this.msg_err = '发票号为空'
      }
    },
    get_patient_register: function () {
      var url = this.HOME + '/constant/get'
      var that = this
      var data = {
        'patient_record_id': that.patient_record_id
      }
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
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
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
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
