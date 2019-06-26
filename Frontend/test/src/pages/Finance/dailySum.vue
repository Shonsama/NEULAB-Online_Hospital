<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card flat>
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
    <v-toolbar flat>
        <v-layout row>
          <v-flex xs2>
            <v-select
              v-model="cashier_user_object"
              :items="allCashier"
              label="日结员"
              item-text="user_name"
              item-value="user_id"
              class="mr-4"
              return-object
            ></v-select>
          </v-flex>
          <v-spacer></v-spacer>
          <v-flex>
            <el-date-picker
              v-model="date"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              style="margin-top: 10px"
              value-format= "yyyy-MM-dd HH:mm:ss"
            >
            </el-date-picker>
            <v-btn
              large
              icon
              flat
              color="primary"
              @click="load"
            >
              <v-icon>
                search
              </v-icon>
            </v-btn>
          </v-flex>
          <v-btn
            style="margin-top: 15px"
            color="primary"
            @click="pass_daily"
          >
            核对通过
          </v-btn>
        </v-layout>
    </v-toolbar>

    <v-layout class="ml-4 mt-4" justify-center>
      <v-card style="width: 700px">
        <v-container>
          <v-layout row justify-center>
            <div class="title font-weight-light mb-2">门诊收费日结单</div>
          </v-layout>
          <v-divider></v-divider>
          <v-layout row>
            <v-flex style="margin-right: 50px" class="mt-2" lg3 offset-xs3>
              <v-text-field
                v-model="time_range"
                label="时间范围"
                readonly
              ></v-text-field>
            </v-flex>
            <v-flex class="mt-2" lg3>
              <v-text-field
                v-model="cashier"
                label="收款员"
                readonly
              ></v-text-field>
            </v-flex>
          </v-layout>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>中药费</v-subheader>
            </v-flex>
            <v-flex xs6>
              <v-text-field
                v-model="daily_mid_prescription_sum"
                label="金额"
                suffix="元"
                readonly
              ></v-text-field>
            </v-flex>
          </v-layout>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>西药费</v-subheader>
            </v-flex>
            <v-flex xs6>
              <v-text-field
                label="金额"
                v-model="daily_west_prescription_sum"
                suffix="元"
                readonly
              ></v-text-field>
            </v-flex>
          </v-layout>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>挂号费</v-subheader>
            </v-flex>
            <v-flex xs6>
              <v-text-field
                label="金额"
                v-model="daily_register_sum"
                suffix="元"
                readonly
              ></v-text-field>
            </v-flex>
          </v-layout>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>医技费</v-subheader>
            </v-flex>
            <v-flex xs6>
              <v-text-field
                label="金额"
                v-model="daily_ms_sum"
                suffix="元"
                readonly
              ></v-text-field>
            </v-flex>
          </v-layout>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>已打发票</v-subheader>
            </v-flex>
            <v-flex xs6 class="mr-4">
              <v-text-field
                label="数量"
                v-model="bill_already"
                suffix="张"
                readonly
              ></v-text-field>
            </v-flex>
            <v-flex xs1 class="mt-2">
              <v-btn
                flat
                color="primary"
                @click="expand_already = !expand_already"
              >
                详细
              </v-btn>
            </v-flex>
          </v-layout>
          <v-expand-transition>
            <div v-show="expand_already">
              <v-data-table
                class="ml-5 elevation-1 mb-4"
                :headers="headers_already"
                :items="desserts_already"
                item-key="bill_id"
              >
                <template v-slot:items="props">
                  <td>{{ props.item.bill_id }}</td>
                  <td>{{ props.item.bill_type }}</td>
                  <td>{{ props.item.bill_state }}</td>
                  <!--<td>{{ props.item.bill_num}}</td>-->
                </template>
              </v-data-table>
            </div>
          </v-expand-transition>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>重打发票</v-subheader>
            </v-flex>
            <v-flex xs6 class="mr-4">
              <v-text-field
                label="数量"
                v-model="bill_again"
                suffix="张"
                readonly
              ></v-text-field>
            </v-flex>
            <v-flex xs1 class="mt-2">
              <v-btn
                flat
                color="primary"
                @click="expand_again = !expand_again"
              >
                详细
              </v-btn>
            </v-flex>
          </v-layout>
          <v-expand-transition>
            <div v-show="expand_again">
              <v-data-table
                class="ml-5 elevation-1 mb-4"
                :headers="headers_already"
                :items="desserts_again"
                item-key="bill_id"
              >
                <template v-slot:items="props">
                  <td>{{ props.item.bill_id }}</td>
                  <td>{{ props.item.bill_type }}</td>
                  <td>{{ props.item.bill_num}}</td>
                </template>
              </v-data-table>
            </div>
          </v-expand-transition>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>补打发票</v-subheader>
            </v-flex>
            <v-flex xs6 class="mr-4">
              <v-text-field
                label="数量"
                v-model="bill_complement"
                suffix="张"
                readonly
              ></v-text-field>
            </v-flex>
            <v-flex xs1 class="mt-2">
              <v-btn
                flat
                color="primary"
                @click="expand_complement = !expand_complement"
              >
                详细
              </v-btn>
            </v-flex>
          </v-layout>
          <v-expand-transition>
            <div v-show="expand_complement">
              <v-data-table
                class="ml-5 elevation-1 mb-4"
                :headers="headers_already"
                :items="desserts_complement"
                item-key="bill_id"
              >
                <template v-slot:items="props">
                  <td>{{ props.item.bill_id }}</td>
                  <td>{{ props.item.bill_type }}</td>
                  <td>{{ props.item.bill_num}}</td>
                </template>
              </v-data-table>
            </div>
          </v-expand-transition>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>作废发票</v-subheader>
            </v-flex>
            <v-flex xs6 class="mr-4">
              <v-text-field
                label="数量"
                v-model="bill_waste"
                suffix="张"
                readonly
              ></v-text-field>
            </v-flex>
            <v-flex xs1 class="mt-2">
              <v-btn
                flat
                color="primary"
                @click="expand_waste = !expand_waste"
              >
                详细
              </v-btn>
            </v-flex>
          </v-layout>
          <v-expand-transition>
            <div v-show="expand_waste">
              <v-data-table
                class="ml-5 elevation-1 mb-4"
                :headers="headers_already"
                :items="desserts_waste"
                item-key="bill_id"
              >
                <template v-slot:items="props">
                  <td>{{ props.item.bill_id }}</td>
                  <td>{{ props.item.bill_type }}</td>
                  <td>{{ props.item.bill_num}}</td>
                </template>
              </v-data-table>
            </div>
          </v-expand-transition>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>对冲发票</v-subheader>
            </v-flex>
            <v-flex xs6 class="mr-4">
              <v-text-field
                label="数量"
                v-model="bill_opposite"
                suffix="张"
                readonly
              ></v-text-field>
            </v-flex>
            <v-flex xs1 class="mt-2">
              <v-btn
                flat
                color="primary"
                @click="expand_opposite = !expand_opposite"
              >
                详细
              </v-btn>
            </v-flex>
          </v-layout>
          <v-expand-transition>
            <div v-show="expand_opposite">
              <v-data-table
                class="ml-5 elevation-1 mb-4"
                :headers="headers_already"
                :items="desserts_opposite"
                item-key="bill_id"
              >
                <template v-slot:items="props">
                  <td>{{ props.item.bill_id }}</td>
                  <td>{{ props.item.bill_type }}</td>
                  <td>{{ props.item.bill_num}}</td>
                </template>
              </v-data-table>
            </div>
          </v-expand-transition>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>总收入</v-subheader>
            </v-flex>
            <v-flex xs6 class="mr-4">
              <v-text-field
                v-model="daily_sum"
                label="金额"
                suffix="元"
                outline
                readonly
              ></v-text-field>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card>
    </v-layout>
  </v-card>
</template>

<script>
export default {
  data: () => ({
    allCashier: [],
    dialog: false,
    cashier_user_object: '',
    date: ['', ''],
    expand_already: false,
    dialog_err: false,
    dialog_suc: false,
    msg_suc: 'success',
    msg_err: 'error',
    expand_again: false,
    expand_waste: false,
    expand_complement: false,
    expand_opposite: false,
    bill_already: '0',
    bill_again: '0',
    bill_waste: '0',
    bill_complement: '0',
    bill_opposite: '0',
    time_range: '',
    cashier: 'shuwanyang',
    daily_mid_prescription_sum: '0',
    daily_west_prescription_sum: '0',
    daily_register_sum: '0',
    daily_ms_sum: '0',
    daily_sum: '0',
    headers_already: [
      {
        text: '发票id',
        align: 'left',
        value: 'bill_id'
      },
      { text: '发票类型', value: 'bill_type' },
      { text: '发票状态', value: 'bill_state' }
      // { text: '发票号', value: 'bill_num' },
    ],
    desserts_already: [],
    headers_again: [],
    desserts_again: [],
    headers_waste: [],
    desserts_waste: [],
    headers_complement: [],
    desserts_complement: [],
    headers_opposite: [],
    desserts_opposite: [],
    daily: ''
  }),
  watch: {
    dialog_suc (val) {
      if (!val) return
      setTimeout(() => (this.dialog_suc = false), 1000)
    },
    // dialog (val) {
    //   if (!val) return
    //   setTimeout(() => (this.dialog = false), 10000)
    // },
    dialog_err (val) {
      if (!val) return
      setTimeout(() => (this.dialog_err = false), 1000)
    }
  },
  methods: {
    erase: function () {
      this.date[0] = ''
      this.date[1] = ''
      this.time_range = ''
      this.daily_mid_prescription_sum = 0
      this.daily_west_prescription_sum = 0
      this.daily_register_sum = 0
      this.daily_ms_sum = 0
      this.daily_sum = 0
      // bill 下面赋值的
      this.bill_already = 0
      this.desserts_already = []
      this.bill_again = 0
      this.desserts_again = []
      this.bill_complement = 0
      this.desserts_complement = []
      this.bill_opposite = 0
      this.desserts_opposite = []
      this.bill_waste = 0
      this.desserts_waste = []
    },
    load: function () {
      this.cashier = this.cashier_user_object.user_name
      console.log('This is the format of the date')
      let that = this
      var url = this.HOME + '/user-service/daily-get'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), {
        daily_user_id: that.cashier_user_object.user_id,
        daily_start: that.date[0],
        daily_end: that.date[1],
        daily_pass_state: '已确认提交'
      })
        .then(function (response) {
          console.log(response.data)
          var list = response.data.data
          that.daily = list[list.length - 1]
          that.date[0] = that.daily.daily_start
          that.date[1] = that.daily.daily_end
          that.time_range = that.date[0] + '-' + that.date[1]
          that.daily_mid_prescription_sum = that.daily.daily_mid_prescription_sum
          that.daily_west_prescription_sum = that.daily.daily_west_prescription_sum
          that.daily_register_sum = that.daily.daily_register_sum
          that.daily_ms_sum = that.daily.daily_check_sum + that.daily.daily_examine_sum + that.daily.daily_handle_sum
          that.daily_sum = that.daily_mid_prescription_sum + that.daily_west_prescription_sum + that.daily_register_sum + that.daily_ms_sum
          that.load_bills()
        })
    },
    load_bills: function () {
      let that = this
      var url = this.HOME + '/user-service/daily-bill-get'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), {
        daily_id: that.daily.daily_id
      })
        .then(function (response) {
          console.log(response.data)
          that.daily = response.data.data
          that.bill_already = that.daily.bills.length
          that.desserts_already = that.daily.bills
          that.bill_again = that.daily.redoBillList.length
          that.desserts_again = that.daily.redoBillList
          that.bill_complement = that.daily.overprintBillList.length
          that.desserts_complement = that.daily.overprintBillList
          that.bill_opposite = that.daily.flushBillList.length
          that.desserts_opposite = that.daily.flushBillList
          that.bill_waste = that.daily.abandonBillList.length
          that.desserts_waste = that.daily.abandonBillList
        })
    },
    load_cashiers: function () {
      let that = this
      var url = this.HOME + '/user/get-all-cashier'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), {
      })
        .then(function (response) {
          console.log(response.data)
          that.allCashier = response.data.data
        })
    },
    pass_daily: function () {
      let that = this
      var url = this.HOME + '/user-service/daily-pass'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), {
        daily_id: that.daily.daily_id,
        daily_owner_id: that.$store.state.user.id
      })
        .then(function (response) {
          if (response.data.code === 200) {
            that.dialog_suc = true
            that.msg_suc = '核对成功'
            that.erase()
            console.log(response.data)
          } else {
            that.dialog_err = true
            that.msg_err = '核对失败'
          }
        })
    }
  },
  mounted: function () {
    this.load_cashiers()
  }
}
</script>

<style scoped>

</style>
