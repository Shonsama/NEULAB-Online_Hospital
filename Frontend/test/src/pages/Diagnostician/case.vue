<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
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
        style="max-height: 900px"
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
                 <td>{{ props.item.diagnose_id }}</td>
                 <td>{{ props.item.diagnose_disease_name }}</td>
                 <td>{{ props.item.diagnose_time }}</td>
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
                  <td>{{ props.item.diagnose_id }}</td>
                  <td>{{ props.item.diagnose_disease_name }}</td>
                  <td>{{ props.item.diagnose_time }}</td>
                </template>
              </v-data-table>
            </v-card>
          </div>
        </v-expand-transition>
        <v-textarea
          v-model="form.advise"
          box
          label="检查建议"
          placeholder="请输入检查建议"
          rows="2"
        ></v-textarea>
        <v-textarea
          v-model="form.notice"
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
  data () {
    return {
      selected: [],
      selected_dia: [],
      show: false,
      expand: false,
      expand_CH: false,
      expand_WE: false,
      headers: [{
        text: '编码',
        align: 'left',
        value: 'diagnose_id'
      },
      {
        text: '名称',
        value: 'diagnose_disease_name'
      },
      {
        text: '发病时间',
        value: 'diagnose_time'
      }],
      desserts: [{
        diagnose_id: '1',
        diagnose_disease_name: '1',
        diagnose_time: '1'
      }, {
        diagnose_id: '1',
        diagnose_disease_name: '1',
        diagnose_time: '1'
      }],
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
        notice: ''
      }
    }
  },
  mounted: function () {
    this.load_diagnosis()
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
          diagnose_type: this.selected_dia[n].disease_type
        }
        this.desserts.push(data)
      }
    },
    submit () {
      let that = this
      var url = this.HOME + '/doctor/submit-record'
      this.$http.post(url, this.form)
        .then(response => {
          that.desserts = response.data
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
        diagnosis: {
          cate: '',
          content: []
        },
        notice: ''
      }
      this.desserts = []
    },
    save () {
      let that = this
      var url = this.HOME + ''
      var data = {
        record_syndrome: '',
        record_health_check: '',
        record_xianbingshi: '',
        record_jiwangshi: '',
        record_cure_situation: '',
        record_allergy_his: '',
        diagnosis: [],
        notice: ''
      }
      this.$http.post(url, data)
        .then(response => {
          that.desserts = response.data
        })
    },
    load_diagnosis () {
      let that = this
      var url = this.HOME + '/diseaseInfo/get-all'
      this.$http.post(url)
        .then(response => {
          that.desserts_dia = response.data.data
        })
    },
    refresh () {
      let that = this
      var url = this.HOME + ''
      this.form.diagnosis.content = this.content
      this.$http.post(url, this.form)
        .then(response => {
          that.desserts = response.data
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
