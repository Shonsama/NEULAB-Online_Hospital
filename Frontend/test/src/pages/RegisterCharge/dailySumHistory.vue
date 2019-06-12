<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card>
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
          icon
        >
          <v-icon>search</v-icon>
        </v-btn>
      </v-toolbar>
      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="desserts"
        item-key="name"
        select-all
        class="elevation-1"
      >
        <template v-slot:items="props">
          <td>
            <v-checkbox
              v-model="props.selected"
              primary
              hide-details
            ></v-checkbox>
          </td>
          <td>{{ props.item.name }}</td>
          <td class="text-xs-right">{{ props.item.calories }}</td>
          <td class="text-xs-right">{{ props.item.fat }}</td>
          <td class="text-xs-right">{{ props.item.carbs }}</td>
          <td class="text-xs-right">{{ props.item.protein }}</td>
          <td class="text-xs-right">{{ props.item.iron }}</td>
          <td class="justify-center layout px-0">
            <v-icon
              small
              class="mr-2"
              @click="editItem(props.item)"
            >
              edit
            </v-icon>
            <v-icon
              small
              @click="deleteItem(props.item)"
            >
              delete
            </v-icon>
          </td>
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
        value: 'code'
      },
      {text: '结算类型', value: 'type'},
      {text: '收费时间', value: 'time'},
      {text: '收费金额', value: 'cost'}],
      selected: '',
      daily: ''
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
      this.date[0] = new Date(2019, 5, 1, 10, 10)
      this.date[1] = new Date()
    },
    getDaily: function () {
      let that = this
      var url = this.HOME + '/user-service/daily-search'
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
