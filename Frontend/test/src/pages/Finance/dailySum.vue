<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card flat>
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
          <v-flex>
            <el-date-picker
              v-model="date"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              style="margin-top: 10px"
            >
            </el-date-picker>
            </v-flex>
          <v-spacer/>
          <v-tooltip bottom>
            <template v-slot:activator="{ on }">
              <v-btn
                large
                icon
                flat
                color="primary"
                @click="load"
                v-on="on"
              >
                <v-icon>
                  search
                </v-icon>
              </v-btn>
            </template>
            <span>搜索日结信息</span>
          </v-tooltip>
          <v-tooltip bottom>
            <template v-slot:activator="{ on }">
              <v-btn
                large
                icon
                flat
                color="primary"
                @click="load"
                v-on="on"
              >
                <v-icon>
                  save
                </v-icon>
              </v-btn>
            </template>
            <span>核对通过</span>
          </v-tooltip>
        </v-layout>
      </v-toolbar>

    <v-layout class="ml-4 mt-4" justify-center>
      <v-card style="width: 700px">
        <v-container>
          <v-layout row justify-center>
            <div class="title font-weight-light">门诊收费日结单</div>
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
                  <td>{{ props.item.bill_num}}</td>
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
                :headers="headers_again"
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
                :headers="headers_complement"
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
                :headers="headers_waste"
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
                :headers="headers_opposite"
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
    cashier_user_object: '',
    date:['',''],
    cashier_user_id: '',
    expand_already: false,
    expand_again: false,
    expand_waste: false,
    expand_complement: false,
    expand_opposite: false,
    bill_already: '15',
    bill_again: '2',
    bill_waste: '3',
    bill_complement: '5',
    bill_opposite: '6',
    time_range: '2019.8.1-2019.9.1',
    cashier:'shuwanyang',
    daily_mid_prescription_sum: '100',
    daily_west_prescription_sum: '150',
    daily_register_sum: '50',
    daily_ms_sum: '30',
    daily_sum: '200',
    headers_already: [
      {
        text: '发票id',
        align: 'left',
        value: 'bill_id'
      },
      { text: '发票类型', value: 'bill_type' },
      { text: '发票号', value: 'bill_num' },
    ],
    desserts_already: [
      {
        bill_id: 1,
        bill_type: '已打',
        bill_num: 123456
      }
    ],
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
  methods: {
    load: function () {
      this.time_range = this.date[0] + '-' + this.date[1]
      this.cashier = this.cashier_user_object.user_name
      let that = this
      var url = this.HOME + '/user-service/daily-get'
      this.$http.post(url, {
        daily_user_id: that.cashier_user_id,
        daily_start: that.date[0],
        daily_end: that.date[1]
      })
        .then(function (response) {
          console.log(response.data)
          that.daily = response.data.data
          that.daily_mid_prescription_sum = that.daily.daily_mid_prescription_sum
          that.daily_west_prescription_sum= that.daily.daily_west_prescription_sum
          that.daily_register_sum = that.daily.daily_register_sum
          that.daily_ms_sum = that.daily.daily_check_sum + that.daily.daily_examine_sum + that.daily.daily_handle_sum
        })
    },
    load_cashiers: function () {
      let that = this
      var url = this.HOME + '/user/get-all-cashier'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.allCashier = response.data.data
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
