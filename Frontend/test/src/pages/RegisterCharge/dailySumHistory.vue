<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card>
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
    <v-flex
    >
      <v-toolbar dense flat>
        <v-toolbar-title>日结历史</v-toolbar-title>
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
        <v-btn
          class="white--text"
          color="primary"
          flat
          @click="getDaily"
          icon
        >
          <v-icon>search</v-icon>
        </v-btn>
      </v-toolbar>
      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="daily"
        item-key="daily_id"
        class="elevation-1"
      >
        <template v-slot:items="props">
          <td>{{ props.item.daily_id }}</td>
          <td class="text-xs-right">{{ props.item.daily_start }}</td>
          <td class="text-xs-right">{{ props.item.daily_end }}</td>
          <td class="text-xs-right">{{ props.item.daily_operate_time }}</td>
          <td class="text-xs-right">{{ props.item.daily_pass_state }}</td>
          <td class="text-xs-right">{{ props.item.daily_sum }}</td>
          <td class="text-xs-right">{{ props.item.daily_mid_prescription_sum }}</td>
          <td class="text-xs-right">{{ props.item.daily_west_prescription_sum }}</td>
          <td class="text-xs-right">{{ props.item.daily_register_sum }}</td>
          <td class="text-xs-right">{{ props.item.daily_check_sum }}</td>
          <td class="text-xs-right">{{ props.item.daily_examine_sum }}</td>
          <td class="text-xs-right">{{ props.item.daily_handle_sum }}</td>
        </template>
      </v-data-table>
    </v-flex>
  </v-card>
</template>

<script>
export default {
  data () {
    return {
      date: ['', ''],
      dialog_err: false,
      dialog_suc: false,
      msg_suc: 'success',
      msg_err: 'error',
      headers: [{
        text: '发票号',
        align: 'left',
        value: 'daily_id'
      },
      {text: '开始时间', value: 'daily_start'},
      {text: '结束时间', value: 'daily_end'},
      {text: '操作时间', value: 'daily_operate_time'},
      {text: '日结状态', value: 'daily_pass_state'},
      {text: '日结总计', value: 'daily_sum'},
      {text: '中药统计', value: 'daily_mid_prescription_sum'},
      {text: '西药统计', value: 'daily_west_prescription_sum'},
      {text: '挂号费统计', value: 'daily_register_sum'},
      {text: '检验统计', value: 'daily_check_sum'},
      {text: '检查统计', value: 'daily_examine_sum'},
      {text: '处置统计', value: 'daily_handle_sum'}],
      selected: '',
      daily: []
    }
  },
  created: function () {
    this.getDate()
  },
  computed: {
  },
  watch: {
    dialog_suc (val) {
      if (!val) return
      setTimeout(() => (this.dialog_suc = false), 1000)
    },
    dialog_err (val) {
      if (!val) return
      setTimeout(() => (this.dialog_err = false), 1000)
    }
  },
  mounted: function () {
  },
  methods: {
    filterDate: function (value) {
      return ((this.date[0] <= new Date(value.daily_operate_time) && this.date[1] >= new Date(value.daily_operate_time)) || this.date[0] === '' || this.date[1] === '') && value.daily_pass_state !== '未确认状态'
    },
    getDate: function () {
      this.date[0] = new Date(2019, 5, 1, 10, 10)
      this.date[1] = new Date()
    },
    getDaily: function () {
      let that = this
      var url = this.HOME + '/user-service/daily-get'
      var data = {
        daily_user_id: that.$store.state.user.id
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.daily = response.data.data
        })
    }
  }
}
</script>

<style scoped>

</style>
