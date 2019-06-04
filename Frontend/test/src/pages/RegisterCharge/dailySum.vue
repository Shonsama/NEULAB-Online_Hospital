<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card>
    <v-flex
    >
      <v-toolbar flat dense>
        <v-toolbar-title>日结表</v-toolbar-title>
        <v-spacer></v-spacer>
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
        item-key="code"
        select-all
      >
        <template v-slot:items="props">
          <td>
            <v-checkbox
              v-model="props.selected"
              primary
              hide-details
            ></v-checkbox>
          </td>
          <td>{{ props.item.code }}</td>
          <td>{{ props.item.type }}</td>
          <td>{{ props.item.time }}</td>
          <td>{{ props.item.cost }}</td>
        </template>
      </v-data-table>
    </v-flex>
    <v-divider></v-divider>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        class="white--text"
        color="primary"
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
      selected: ''
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
    }
  }
}
</script>

<style scoped>

</style>
