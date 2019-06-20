<!--suppress ALL -->
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
          class="mb-2"
          v-model="date"
          type="datetimerange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          style="margin-right: 10px"
          value-format= "yyyy-MM-dd HH:mm:ss"
        >
        </el-date-picker>
        <v-flex xs2>
          <v-select
            v-model="department_type"
            :items="department_items"
            label="科室类型"
          ></v-select>
        </v-flex>
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
          <td>{{ props.item.getDepartmentName }}</td>
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
import qs from 'qs'
/* eslint-disable camelcase */

export default {
  data () {
  //   canvasId: 'myCanvas',
  //   type: 'bar',
  //   width: 600,
  //   height: 500,
  //   data: [
  //     {name: '这',value: 1},
  // {name: '是',value: 2},
  // {name: '一',value: 3},
  // {name: '个',value: 4},
  // {name: '例',value: 5},
  // {name: '子',value: 6}
  // ],
  //   let data = []
    let options = qs.parse(location.search, { ignoreQueryPrefix: true })
    // for (let i = 0; i <= 360; i++) {
    //   let t = i / 180 * Math.PI
    //   let r = Math.sin(2 * t) * Math.cos(2 * t)
    //   // data.push([r, i])
    // }
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
      department_type: '',
      department_items: [
        {text: '开单科室'},
        {text: '执行科室'}
      ],
      dialog: false,
      date: ['', ''],
      search: '',
      expand: false,
      selected: [],
      signal: '',
      headers: [
        {
          text: '科室名称',
          align: 'left',
          value: 'getDepartmentName'
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
      var url = ''
      if (this.department_type !== '') {
        if (this.department_type === '开单科室') {
          url = this.HOME + '/workload/get-department-draw'
        } else {
          url = this.HOME + '/workload/get-department-execute'
        }
        let that = this
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
              temp_data.push(that.desserts[i].getDepartmentName)
              temp_data.push(that.desserts[i].getVisitorsNum)
              temp_data.push(that.desserts[i].getBillsNum)
              temp_data.push(cost_total)
              that.bar.dataset.source.push(temp_data)
            }
            console.log(that.bar.dataset.source)
            // that.bar.dataset.source = [
            //   ['Product', '2015', '2016', '2017'],
            //   ['Matcha Latte', '2015', '2016', '2017'],
            //   ['Milk Tea', '1000', '2016', '2017'],
            //   ['Cheese Cocoa', '2015', '2016', '2017'],
            //   ['Walnut Brownie', '2015', '2016', '2017']
            // ]
          })
      }
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
