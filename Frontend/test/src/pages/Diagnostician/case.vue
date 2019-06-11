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
    <v-dialog
      v-model="show"
      max-width="400"
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
            <td>{{ props.item.disease_icd }}</td>
            <td>{{ props.item.disease_name }}</td>
            <td>{{ props.item.disease_type }}</td>
          </template>
        </v-data-table>
      </v-card>
    </v-dialog>
    <v-toolbar flat dense >
      <v-layout align-center justify-space-around row fill-height>
      <v-btn small color="primary" @click="save">
        暂存
      </v-btn>
      <v-btn small color="primary" @click="submit">
        提交
      </v-btn>
      <v-btn small color="primary" @click="clear">
        清空
      </v-btn>
      <v-btn small color="primary" @click="refresh">
        刷新
      </v-btn>
    </v-layout>
    </v-toolbar>
    <v-layout>
      <v-flex xs12>
      <v-form
        class="pa-3 pt-4 scroll-y"
        style="max-height: 600px"
      >
        <v-card-title primary-title>
          <div>
            <h5 class="subheading">病史</h5>
          </div>
        </v-card-title>
        <v-textarea
          v-model="form.record_syndrome"
          box
          label="主诉"
          placeholder="请输入主诉"
          rows="1"
        ></v-textarea>
        <v-textarea
          v-model="form.record_xianbingshi"
          box
          label="现病史"
          placeholder="请输入现病史"
          rows="2"
        ></v-textarea>
        <v-textarea
          v-model="form.record_cure_situation"
          box
          label="现病治疗情况"
          placeholder="请输入现病治疗情况"
          rows="2"
        ></v-textarea>
        <v-textarea
          v-model="form.record_jiwangshi"
          box
          label="既往史"
          placeholder="请输入既往史"
          rows="2"
        ></v-textarea>
        <v-textarea
          v-model="form.record_allergy_his"
          box
          label="过敏史"
          placeholder="请输入过敏史"
          rows="2"
        ></v-textarea>
        <v-textarea
          v-model="form.record_health_check"
          box
          label="体格检查"
          placeholder="请输入体格检查"
          rows="2"
        ></v-textarea>
        <v-layout >
      <v-card-title primary-title>
        <div>
          <h5 class="subheading">初步诊断</h5>
        </div>
      </v-card-title>
          <v-btn flat icon color="primary" style="margin-top: 17px" @click="expand = !expand">
            <v-icon>add</v-icon>
          </v-btn>
          <v-flex shrink>
            <v-expand-x-transition>
              <div v-show="expand" style="white-space: nowrap">
                <v-btn color="primary" style="margin-top: 17px"
                       @click="choose_WE"
                >
                  西医诊断
                </v-btn>
                <v-btn color="primary" style="margin-top: 17px"
                       @click="choose_CH"
                >
                  中医诊断
                </v-btn>
              </div>
            </v-expand-x-transition>
          </v-flex>
        </v-layout>
        <v-expand-transition>
          <div v-show="expand_WE">
           <v-card>
             <v-toolbar flat >
               <v-toolbar-title  >西医诊断</v-toolbar-title>
               <v-spacer></v-spacer>
               <v-btn   flat icon color="primary" @click="show = !show">
                 <v-icon> add</v-icon>
               </v-btn>
               <v-btn   flat icon color="primary" @click="expand = !expand">
                 <v-icon>delete</v-icon>
               </v-btn>
             </v-toolbar>
             <v-data-table
               v-model="selected"
               :headers="headers"
               :items="desserts"
               item-key="diagnose_id"
               select-all
               class="elevation-1"
               style="margin-bottom: 20px"
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
                 <td>{{ props.item.diagnose_time.getFullYear() + '-'}}{{ props.item.diagnose_time.getMonth() + '-'}}{{ props.item.diagnose_time.getDate()+' '}}{{ props.item.diagnose_time.getHours()+':'+props.item.diagnose_time.getMinutes()+':'+props.item.diagnose_time.getSeconds()}}</td>
               </template>
             </v-data-table>
           </v-card>
          </div>
        </v-expand-transition>
        <v-expand-transition>
          <div v-show="expand_CH">
            <v-card>
              <v-toolbar flat >
                <v-toolbar-title  >中医诊断</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-btn   flat icon color="primary" @click="show = !show">
                  <v-icon> add</v-icon>
                </v-btn>
                <v-btn   flat icon color="primary" @click="expand = !expand">
                  <v-icon>delete</v-icon>
                </v-btn>
              </v-toolbar>
              <v-data-table
                v-model="selected"
                :headers="headers"
                :items="desserts"
                item-key="diagnose_disease_id"
                select-all
                class="elevation-1"
                style="margin-bottom: 20px"
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
                  <td>{{ props.item.diagnose_time.getFullYear() + '-'}}{{ props.item.diagnose_time.getMonth() + '-'}}{{ props.item.diagnose_time.getDate()+' '}}{{ props.item.diagnose_time.getHours()+':'+props.item.diagnose_time.getMinutes()+':'+props.item.diagnose_time.getSeconds()}}</td>
                </template>
              </v-data-table>
            </v-card>
          </div>
        </v-expand-transition>
        <v-textarea
          v-model="form.record_suggestion"
          box
          label="检查建议"
          placeholder="请输入检查建议"
          rows="2"
        ></v-textarea>
        <v-textarea
          v-model="form.record_attention"
          box
          label="注意事项"
          placeholder="请输入注意事项"
          rows="2"
        ></v-textarea>
      </v-form>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
export default {
  props: ['msgfromfa', 'dialog'],
  data () {
    return {
      dialog: false,
      selected: [],
      selected_dia: [],
      show: false,
      expand: false,
      expand_CH: false,
      expand_WE: false,
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
      form: {
        record_syndrome: '',
        record_health_check: '',
        record_xianbingshi: '',
        record_jiwangshi: '',
        record_cure_situation: '',
        record_allergy_his: '',
        diagnosis: {
          cate: '',
          content: []
        },
        record_suggestion: '',
        record_attention: ''
      }
    }
  },
  mounted: function () {
    this.load_diagnosis()
    console.log(this.msgfromfa)
  },
  methods: {
    getItem () {
      let that = this
      var url = this.HOME + ''
      this.$http.get(url).then(response => {
        that.desserts = response.data
      })
    },
    addItem () {
      this.show = !this.show
      console.log(this.selected_dia)
      var n
      this.desserts = []
      for (n = 0; n < this.selected_dia.length; n++) {
        var data = {
          diagnose_disease_id: this.selected_dia[n].disease_icd,
          diagnose_disease_name: this.selected_dia[n].disease_name,
          diagnose_record_id: this.msgfromfa.register_info_id,
          diagnose_time: new Date()
        }
        this.desserts.push(data)
      }
    },
    submit_diagnoses () {
      // let that = this
      var url = this.HOME + '/doctor/submit-first-diagnose'
      this.$http.post(url, this.desserts)
        .then(response => {
          console.log(response.data)
        })
    },
    submit () {
      let that = this
      var url = this.HOME + '/doctor/submit-record'
      that.dialog = true
      var data = {
        record_syndrome: that.form.record_syndrome,
        record_health_check: that.form.record_health_check,
        record_xianbingshi: that.form.record_xianbingshi,
        record_jiwangshi: that.form.record_jiwangshi,
        record_cure_situation: that.form.record_cure_situation,
        record_allergy_his: that.form.record_allergy_his,
        record_suggestion: that.form.record_suggestion,
        record_attention: that.form.record_attention,
        record_patient_id: that.msgfromfa.register_info_patient_id,
        record_doctor_id: that.msgfromfa.register_info_doctor_id,
        record_id: that.msgfromfa.register_info_id,
        record_doctor_type: that.form.diagnosis.cate
      }
      this.$http.post(url, data)
        .then(response => {
          console.log(response.data)
          this.submit_diagnoses()
          this.confirm_record()
          that.dialog = false
          // that.desserts = response.data
        })
    },
    confirm_record () {
      // let that = this
      var url = this.HOME + '/doctor/confirm-first-diagnose'
      var data = {
        record_id: this.msgfromfa.register_info_id
      }
      this.$http.post(url, data)
        .then(response => {
          console.log(response.data)
          // that.desserts = response.data
        })
    },
    clear () {
      this.form = {
        record_syndrome: '',
        record_health_check: '',
        record_xianbingshi: '',
        record_jiwangshi: '',
        record_cure_situation: '',
        record_allergy_his: '',
        record_suggestion: '',
        record_attention: '',
        diagnosis: {
          cate: '',
          content: []
        }
      }
      this.desserts = []
    },
    save () {
      let that = this
      var url = this.HOME + '/doctor/submit-record'
      var data = {
        record_syndrome: that.form.record_syndrome,
        record_health_check: that.form.record_health_check,
        record_xianbingshi: that.form.record_xianbingshi,
        record_jiwangshi: that.form.record_jiwangshi,
        record_cure_situation: that.form.record_cure_situation,
        record_allergy_his: that.form.record_allergy_his,
        record_suggestion: that.form.record_suggestion,
        record_attention: that.form.record_attention,
        record_patient_id: that.msgfromfa.register_info_patient_id,
        record_doctor_id: that.msgfromfa.register_info_doctor_id,
        record_id: that.msgfromfa.register_info_id,
        record_doctor_type: that.form.diagnosis.cate
      }
      that.dialog = true
      this.$http.post(url, data)
        .then(response => {
          console.log(response.data)
          that.submit_diagnoses()
          that.dialog = false
          // that.desserts = response.data
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
    refresh () {
      let that = this
      var url = this.HOME + '/doctor/get-record'
      var data = {
        record_id: that.msgfromfa.register_info_id
      }
      that.dialog = true
      this.$http.post(url, data)
        .then(response => {
          console.log(response.data.data)
          that.form.record_syndrome = response.data.data.record_syndrome
          that.form.record_health_check = response.data.data.record_health_check
          that.form.record_xianbingshi = response.data.data.record_xianbingshi
          that.form.record_jiwangshi = response.data.data.record_jiwangshi
          that.form.record_cure_situation = response.data.data.record_cure_situation
          that.form.record_allergy_his = response.data.data.record_allergy_his
          that.form.record_suggestion = response.data.data.record_suggestion
          that.form.record_attention = response.data.data.record_attention
          that.form.diagnosis.cate = response.data.data.record_doctor_type
          var i
          for (i = 0;i < response.data.data.firstDiagnoses.length; i++) {
            var data = {
              diagnose_disease_id: response.data.data.firstDiagnoses[i].diagnose_disease_id,
              diagnose_disease_name: response.data.data.firstDiagnoses[i].diagnose_disease_name,
              diagnose_id: response.data.data.firstDiagnoses[i].diagnose_id,
              diagnose_time: new Date(response.data.data.firstDiagnoses[i].diagnose_time.slice(0,19))
            }
            that.desserts.push(data)
          }
          // that.desserts = response.data.data.firstDiagnoses
          console.log(that.desserts)
          that.expand_WE = true
          if (that.expand_CH) {
            that.expand_CH = !that.expand_CH
          }
          that.dialog = false
        })
    },
    choose_WE () {
      this.expand_WE = !this.expand_WE
      this.form.diagnosis.cate = '西医'
      this.form.diagnosis.content = []
      if (this.expand_CH) {
        this.expand_CH = !this.expand_CH
      }
    },
    choose_CH () {
      this.expand_CH = !this.expand_CH
      this.form.diagnosis.cate = '中医'
      this.form.diagnosis.content = []
      if (this.expand_WE) {
        this.expand_WE = !this.expand_WE
      }
    }
  }
}
</script>

<style scoped>

</style>
