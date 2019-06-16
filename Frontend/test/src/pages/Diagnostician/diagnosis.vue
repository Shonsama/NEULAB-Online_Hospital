<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
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
      v-model="show"
      max-width="800"
    >
      <v-card>
        <v-toolbar flat dense>
          <v-toolbar-title  >诊断目录</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn   flat icon dark color="primary" @click="addItem">
            <v-icon>
              add
            </v-icon>
          </v-btn>
        </v-toolbar>
        <v-data-table
          v-model="selected_dia"
          :headers="headers_dia"
          :items="desserts_dia"
          item-key="disease_icd"
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
            <td>{{ props.item.disease_id }}</td>
            <td>{{ props.item.disease_name }}</td>
            <td>{{ props.item.disease_type }}</td>
          </template>
        </v-data-table>
      </v-card>
    </v-dialog>
    <v-card>
      <v-toolbar flat dense>
        <v-toolbar-title>{{type}}</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn   flat icon color="primary"  @click="get">
          <v-icon> refresh</v-icon>
        </v-btn>
        <v-btn   flat icon color="primary"  @click="show = !show">
          <v-icon> add</v-icon>
        </v-btn>
        <v-btn   flat icon color="primary" @click="deleteItem">
          <v-icon>delete</v-icon>
        </v-btn>
        <v-btn   flat icon color="primary"  @click="submit_diagnoses">
          <v-icon>send</v-icon>
        </v-btn>
      </v-toolbar>
      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="desserts"
        item-key="diagnose_disease_id"
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
          <td>{{ props.item.diagnose_disease_id }}</td>
          <td>{{ props.item.diagnose_disease_name }}</td>
          <td>{{ props.item.diagnose_time}}</td>
        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
export default {
  props: ['msgfromfa', 'record'],
  data () {
    return {
      dialog_err: false,
      dialog_suc: false,
      msg_suc: 'success',
      msg_err: 'error',
      selected: [],
      selected_dia: [],
      dialog: false,
      show: false,
      headers: [{
        text: '编码',
        align: 'left',
        value: 'diagnose_disease_id'
      },
      {
        text: '名称',
        value: 'diagnose_disease_name'
      },
      {
        text: '发病时间',
        value: 'diagnose_time'
      }],
      desserts: [],
      headers_dia: [{
        text: '编码',
        align: 'left',
        value: 'disease_icd'
      },
      {
        text: '名称',
        value: 'disease_name'
      },
      {
        text: '分类',
        value: 'disease_type'
      }],
      desserts_dia: [],
      type: ''
    }
  },
  mounted: function () {
    this.load_diagnosis()
  },
  watch: {
    record: function (newState, oldState) {
      this.get()
    },
    dialog_suc (val) {
      if (!val) return
      setTimeout(() => (this.dialog_suc = false), 1000)
    },
    dialog_err (val) {
      if (!val) return
      setTimeout(() => (this.dialog_err = false), 1000)
    },
    dialog (val) {
      if (!val) return
      setTimeout(() => (this.network_out), 10000)
    }
  },
  methods: {
    submit_diagnoses () {
      let that = this
      var url = this.HOME + '/doctor/submit-final-diagnose'
      this.$http.post(url, this.desserts)
        .then(response => {
          console.log(response.data)
          if (response.data.code === 200) {
            that.dialog_suc = true
            that.msg_suc = '提交成功'
          } else {
            that.dialog_err = true
            that.msg_err = '提交失败'
          }
        })
    },
    addItem () {
      this.show = !this.show
      console.log(this.selected_dia)
      var n
      var time_now = new Date()
      var year = time_now.getFullYear();

      var month = time_now.getMonth().toString().length === 1 ? '0' + (parseInt(time_now.getMonth().toString(),10) + 1) : (time_now.getMonth() + 1);

      var day = time_now.getDate().toString().length === 1 ? '0' + time_now.getDate() : time_now.getDate();

      var date_temp = year + '-' + month + '-' + day + ' ' + time_now.getHours() + ':' + time_now.getMinutes() + ':' +time_now.getSeconds()

      for (n = 0; n < this.selected_dia.length; n++) {
        var data = {
          diagnose_disease_id: this.selected_dia[n].disease_id,
          diagnose_disease_name: this.selected_dia[n].disease_name,
          diagnose_record_id: this.msgfromfa.register_info_id,
          diagnose_time: date_temp
        }
        this.desserts.push(data)
      }
    },
    get () {
      let that = this
      var url = this.HOME + '/doctor/get-record'
      var data = {
        record_id: that.msgfromfa.register_info_id
      }
      that.desserts = []
      this.$http.post(url, data)
        .then(response => {
          console.log(response.data.data)
          that.type = response.data.data.record_doctor_type
          var i
          if (response.data.data.finalDiagnoses.length === 0) {
            for (i = 0; i < response.data.data.firstDiagnoses.length; i++) {
              var data = {
                diagnose_disease_id: response.data.data.firstDiagnoses[i].diagnose_disease_id,
                diagnose_disease_name: response.data.data.firstDiagnoses[i].diagnose_disease_name,
                diagnose_record_id: that.msgfromfa.register_info_id,
                diagnose_time: response.data.data.firstDiagnoses[i].diagnose_time.slice(0, 19)
              }
              that.desserts.push(data)
            }
            // that.desserts = response.data.data.firstDiagnoses
            console.log(that.desserts)
            that.dialog = false
          } else {
            for (i = 0; i < response.data.data.finalDiagnoses.length; i++) {
              var data1 = {
                diagnose_disease_id: response.data.data.finalDiagnoses[i].diagnose_disease_id,
                diagnose_disease_name: response.data.data.finalDiagnoses[i].diagnose_disease_name,
                diagnose_record_id: that.msgfromfa.register_info_id,
                diagnose_time: response.data.data.finalDiagnoses[i].diagnose_time.slice(0, 19)
              }
              that.desserts.push(data1)
            }
            // that.desserts = response.data.data.firstDiagnoses
            console.log(that.desserts)
            that.dialog = false
          }
        })
    },
    load_diagnosis () {
      let that = this
      var url = this.HOME + '/maintenance/disease/get-all'
      this.$http.post(url)
        .then(response => {
          that.desserts_dia = response.data.data
        })
    },
    indexOf (val, val1) {
      for (var i = 0; i < val.length; i++) {
        if (val[i] === val1) return i
      }
      return -1
    },
    remove (val, val1) {
      var index = this.indexOf(val, val1)
      if (index > -1) {
        val.splice(index, 1)
      }
    },
    deleteItem () {
      for (var i = 0; i < this.selected.length; i++) {
        this.remove(this.desserts, this.selected[i])
      }
    }
  }
}
</script>

<style scoped>

</style>
