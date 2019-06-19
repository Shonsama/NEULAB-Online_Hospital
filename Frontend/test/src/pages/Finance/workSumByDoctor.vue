<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card>
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

    <v-flex>
      <v-toolbar flat>
        <v-flex xs3>
          <v-text-field
            prepend-inner-icon="search"
            v-model="search"
            :rules="nameRules"
            label="编号/名称"
            required
          ></v-text-field>
        </v-flex>

        <v-spacer></v-spacer>
        <el-date-picker
          v-model="date"
          type="datetimerange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          style="margin-right: 10px"
          value-format= "yyyy-MM-dd HH:mm:ss"
        >
        </el-date-picker>
        <v-btn
          icon
          flat
          color="primary"
          @click="load"
        >
          <v-icon>
            search
          </v-icon>
        </v-btn>
      </v-toolbar>
      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="desserts"
        :search="search"
        item-key="department_id"
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
          <td>{{ props.item.getDoctorName }}</td>
          <td>{{ props.item.getVisitorsNum }}</td>
          <td>{{ props.item.getBillsNum }}</td>
          <td>{{ props.item.getRegisterFee }}</td>
          <td>{{ props.item.getMedicalSkillCheck }}</td>
          <td>{{ props.item.getMedicalSkillInspect }}</td>
          <td>{{ props.item.getMedicalSkillDispose }}</td>
          <td>{{ props.item.getPrescriptionWest }}</td>
          <td>{{ props.item.getPrescriptionChinese }}</td>
        </template>
      </v-data-table>
    </v-flex>
    <v-flex>
      <v-chart
        style="width: 100%"
        :options="bar"
        :init-options="initOptions"
        ref="bar"
        theme="ovilia-green"
        autoresize
      />
    </v-flex>
  </v-card>
</template>

<script>
/* eslint-disable camelcase */

import qs from 'qs'
export default {
  data () {
    let options = qs.parse(location.search, { ignoreQueryPrefix: true })
    return {
      // option: qs.parse(location.search, { ignoreQueryPrefix: true }),
      initOptions: {
        renderer: options.renderer || 'canvas'
      },
      bar: {
        legend: {},
        tooltip: {},
        dataset: {
          // Provide data.
          source: [
            ['Product', '看诊人次', '发票数量', '总收费']
          ]
        },
        // Declare X axis, which is a category axis, mapping
        // to the first column by default.
        xAxis: {type: 'category'},
        // Declare Y axis, which is a value axis.
        yAxis: {},
        // Declare several series, each of them mapped to a
        // column of the dataset by default.
        series: [{type: 'bar'}, {type: 'bar'}, {type: 'bar'}]
      },
      dialog: false,
      date: ['', ''],
      search: '',
      expand: false,
      selected: [],
      signal: '',
      headers: [
        {
          text: '医生姓名',
          align: 'left',
          value: 'getDoctorName'
        },
        { text: '看诊人次', value: 'getVisitorsNum' },
        { text: '发票数量', value: 'getBillsNum' },
        { text: '挂号费', value: 'getRegisterFee' },
        { text: '检验费', value: 'getMedicalSkillCheck' },
        { text: '检查费', value: 'getMedicalSkillInspect' },
        { text: '处置费', value: 'getMedicalSkillDispose' },
        { text: '西药费', value: 'getPrescriptionWest' },
        { text: '中药费', value: 'getPrescriptionChinese' }
      ],
      desserts: []
    }
  },
  methods: {
    load: function () {
      let that = this
      var url = this.HOME + '/workload/get-doctor'
      that.dialog = true
      this.$http.post(url, {
        start_time: that.date[0],
        end_time: that.date[1]
      })
        .then(function (response) {
          console.log(response.data)
          that.dialog = false
          that.desserts = response.data.data

          that.bar.dataset.source = []
          var cat = ['Product', '看诊人次', '发票数量', '总收费']
          that.bar.dataset.source.push(cat)
          for (let i = 0; i < that.desserts.length; i++) {
            var temp_data = []
            var cost_total = that.desserts[i].getRegisterFee + that.desserts[i].getMedicalSkillCheck + that.desserts[i].getMedicalSkillInspect + that.desserts[i].getMedicalSkillDispose + that.desserts[i].getPrescriptionWest + that.desserts[i].getPrescriptionChinese
            temp_data.push(that.desserts[i].getDoctorName)
            temp_data.push(that.desserts[i].getVisitorsNum)
            temp_data.push(that.desserts[i].getBillsNum)
            temp_data.push(cost_total)
            that.bar.dataset.source.push(temp_data)
          }
        })
    }
  },
  computed: {
  },
  watch: {
  }
}
</script>

<style scoped>

</style>
