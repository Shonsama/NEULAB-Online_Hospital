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
  </v-card>
</template>

<script>
export default {
  data: () => ({
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
  }),
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
