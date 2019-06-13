<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card>
    <v-flex
    >
      <v-toolbar flat dense>
        <v-toolbar-title>日结表</v-toolbar-title>
        <v-spacer></v-spacer>
        <el-date-picker
          v-model="date"
          type="datetime"
          placeholder="选择日期时间"
          >
        </el-date-picker>
        <v-btn
          class="white--text"
          color="primary"
          flat
          @click="addDaily"
          icon
        >
          <v-icon>search</v-icon>
        </v-btn>
      </v-toolbar>
      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="desserts_daily"
        item-key="bill_id"
      >
        <template v-slot:items="props">
          <td>{{ props.item.bill_id }}</td>
          <td>{{ props.item.bill_type }}</td>
          <td>{{ props.item.bill_time }}</td>
          <td>{{ props.item.bill_sum }}</td>
        </template>
      </v-data-table>
    </v-flex>
    <v-divider></v-divider>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        class="white--text"
        color="primary"
        @click="confirmDaily"
      >
        结算
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  data () {
    return {
      date: '',
      desserts: [{
        code: '1',
        cost: '11',
        state: '1',
        type: '1',
        time: '1'
      },
      {
        code: '11',
        cost: '111',
        state: '11',
        type: '11',
        time: '1'
      }],
      headers: [{
        text: '发票号',
        align: 'left',
        value: 'bill_id'
      },
      {text: '结算类型', value: 'bill_type'},
      {text: '收费时间', value: 'bill_time'},
      {text: '收费金额', value: 'bill_sum'}],
      selected: '',
      daily: '',
      desserts_daily: []
    }
  },
  created: function () {
    this.getDate()
  },
  computed: {
  },
  mounted: function () {
  },
  methods: {
    getDate: function () {
      this.date = new Date()
    },
    addDaily: function () {
      let that = this
      var url = this.HOME + '/user-service/daily-search'
      var data = {
        daily_user_id: that.$store.state.user.id
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.daily = response.data.data
          that.desserts_daily = response.data.data.bills
        })
    },
    confirmDaily: function () {
      let that = this
      var url = this.HOME + '/user-service/daily-submit'
      var data = {
        daily_id: that.daily.daily_id
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.getItem()
        })
    }
  }
}
</script>

<style scoped>

</style>
