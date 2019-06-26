<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card>
    <v-dialog
      v-model="show"
      width="300"
    >
      <v-layout justify-center>
        <v-flex xs12>
          <v-card ref="form">
            <v-card-text>
              <v-text-field
                ref="name"
                v-model="disease_id"
                :error-messages="errorMessages"
                label="疾病编号"
                placeholder="请输入疾病编号"
                :disabled="!mode"
                required
              ></v-text-field>
              <v-text-field
                ref="address"
                v-model="disease_name"
                label="疾病名称"
                placeholder="请输入疾病名称"
                required
              ></v-text-field>
              <v-text-field
                ref="city"
                v-model="disease_icd"
                label="国际ICD编码"
                placeholder="请输入国际ICD编码"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="disease_type"
                label="疾病类型"
                placeholder="请输入疾病类型"
                required
              ></v-text-field>
            </v-card-text>
            <!--<v-divider class="mt-5"></v-divider>-->
            <v-card-actions>
              <v-btn flat @click="show =!show">取消</v-btn>
              <v-spacer></v-spacer>
              <v-slide-x-reverse-transition>
                <v-tooltip
                  v-if="formHasErrors"
                  left
                >
                  <template v-slot:activator="{ on }">
                    <v-btn
                      icon
                      class="my-0"
                      @click="resetForm"
                      v-on="on"
                    >
                      <v-icon>refresh</v-icon>
                    </v-btn>
                  </template>
                  <span>Refresh form</span>
                </v-tooltip>
              </v-slide-x-reverse-transition>
              <div v-if="mode">
                <v-btn color="primary" flat @click="addItem">确定</v-btn>
              </div>
              <div v-else>
                <v-btn color="primary" flat @click="updateItem">确定</v-btn>
              </div>

            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </v-dialog>

    <v-alert
      :value="alert_success"
      type="success"
      transition="slide-y-transition"
    >
      This is a success alert.
    </v-alert>

    <v-alert
      :value="alert_error"
      type="error"
      transition="slide-y-transition"
    >
      This is a error alert.
    </v-alert>

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
        <v-btn
          icon
          flat
          color="primary"
          @click="show = !show , mode = true"
        >
          <v-icon>
            add
          </v-icon>
        </v-btn>
        <v-btn
          icon
          flat
          color="primary"
          @click="delete_selected"
        >
          <v-icon>
            delete
          </v-icon>
        </v-btn>
      </v-toolbar>
      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="desserts"
        :search="search"
        item-key="disease_id"
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
          <td>{{ props.item.disease_icd }}</td>
          <td>{{ props.item.disease_type }}</td>
          <td>
            <v-icon
              small
              class="mr-2"
              @click="show =!show , mode = false , fillForm(props.item)"
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
    <v-divider/>
    <v-card-actions>
      <v-spacer/>
      <download-excel
        class = "export-excel-wrapper"
        :data = "desserts"
        :fields = "json_fields"
        name = "疾病信息.xls">
        <v-btn
          color="primary"
        >
          导出表格
          <v-icon right>
            cloud_download
          </v-icon>
        </v-btn>
      </download-excel>
      <v-btn
        color="primary"
        class="ml-2"
        @click="show_upload =! show_upload"
      >
        导入表格
        <v-icon right>
          cloud_upload
        </v-icon>
      </v-btn>
    </v-card-actions>
    <v-layout>
    <v-spacer/>
      <v-flex shrink>
        <v-expand-transition>
          <div v-show="show_upload" style="white-space: nowrap" class="mb-2">
            <v-btn
              flat
              icon
              color="primary"
              @click=""
            >
              <v-icon>
                add
              </v-icon>
            </v-btn>
            <input type="file" @change="importf(this)" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"/>
          </div>
        </v-expand-transition>
      </v-flex>
    </v-layout>
  </v-card>
</template>

<script>
  /* eslint-disable no-unused-vars */


  export default {
  data: () => ({
    accountList: [],
    show_upload: false,
    alert_success: false,
    alert_error: false,
    mode: true,
    disease_id: '',
    disease_name: '',
    disease_icd: '',
    disease_type: '',
    show: false,
    search: '',
    expand: false,
    selected: [],
    signal: '',
    json_fields: {
      '疾病编号': 'disease_id',
      '疾病名称': 'disease_name',
      '国际ICD编码': 'disease_icd',
      '疾病类型': 'disease_type'
    },
    headers: [
      {
        text: '疾病编号',
        align: 'left',
        value: 'disease_id'
      },
      { text: '疾病名称', value: 'disease_name' },
      { text: '国际ICD编码', value: 'disease_icd' },
      { text: '疾病类型', value: 'disease_type' },
      { text: '操作', value: 'operation', sortable: false }
    ],
    desserts: []
  }),
  methods: {
    importf (obj) {
      let _this = this

      let inputDOM = this.$refs.inputer

      this.file = event.currentTarget.files[0]

      var rABS = false // 是否将文件读取为二进制字符串

      var f = this.file

      var reader = new FileReader()

      // if (!FileReader.prototype.readAsBinaryString) {

      FileReader.prototype.readAsBinaryString = function (f) {
        var binary = ''

        var rABS = false // 是否将文件读取为二进制字符串

        var pt = this

        var wb // 读取完成的数据

        var outdata

        var reader = new FileReader()

        reader.onload = function (e) {
          var bytes = new Uint8Array(reader.result)

          var length = bytes.byteLength

          for (var i = 0; i < length; i++) {
            binary += String.fromCharCode(bytes[i])
          }

          var XLSX = require('xlsx')

          if (rABS) {
            wb = XLSX.read(btoa(fixdata(binary)), { // 手动转化

              type: 'base64'

            })
          } else {
            wb = XLSX.read(binary, {

              type: 'binary'

            })
          }

          // outdata就是你想要的东西 excel导入的数据

          outdata = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]])

          let arr = []

          outdata.map(v => {
            let obj = {}

            obj.disease_id = v.疾病编号

            obj.disease_name = v.疾病名称

            obj.disease_icd = v.国际ICD编码

            obj.disease_type = v.疾病类型

            arr.push(obj)
          })

          _this.accountList = [...arr]

          console.log(_this.accountList)

          _this.reload()
        }

        reader.readAsArrayBuffer(f)
      }

      if (rABS) {
        reader.readAsArrayBuffer(f)
      } else {
        reader.readAsBinaryString(f)
      }
    },
    load: function () {
      let that = this
      var url = this.HOME + '/maintenance/disease/get-all'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), {
      })
        .then(function (response) {
          console.log(response.data)
          that.desserts = response.data.data
        })
    },
    deleteItem: function (item) {
      let that = this
      var url = this.HOME + '/maintenance/disease/delete'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), {disease_id: item.disease_id})
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data.msg
          if (that.signal === 'SUCCESS') {
            that.load()
            that.notice_success()
          } else {
            that.notice_error()
          }
        })
      console.log(this.signal)
    },
    addItem: function () {
      var disease = {
        disease_id: this.disease_id,
        disease_name: this.disease_name,
        disease_icd: this.disease_icd,
        disease_type: this.disease_type
      }
      let that = this
      var url = this.HOME + '/maintenance/disease/add'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), disease)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data.msg
          if (that.signal === 'SUCCESS') {
            that.show = !that.show
            that.load()
            that.notice_success()
          } else {
            that.notice_error()
          }
        })
      console.log(this.signal)
    },
    updateItem: function () {
      var disease = {
        disease_id: this.disease_id,
        disease_name: this.disease_name,
        disease_icd: this.disease_icd,
        disease_type: this.disease_type
      }
      let that = this
      var url = this.HOME + '/maintenance/disease/update'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), disease)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data.msg
          if (that.signal === 'SUCCESS') {
            that.show = !that.show
            that.load()
            that.notice_success()
          } else {
            that.notice_error()
          }
        })
      console.log(this.signal)
    },
    fillForm: function (item) {
      this.disease_id = item.disease_id
      this.disease_name = item.disease_name
      this.disease_icd = item.disease_icd
      this.disease_type = item.disease_type
    },
    eraseForm: function () {
      this.disease_id = ''
      this.disease_name = ''
      this.disease_icd = ''
      this.disease_type = ''
    },
    notice_success: function () {
      this.change_success()
      window.setTimeout(this.change_success, 1500)
    },
    change_success: function () {
      this.alert_success = !this.alert_success
    },
    notice_error: function () {
      this.change_error()
      window.setTimeout(this.change_error, 1500)
    },
    change_error: function () {
      this.alert_error = !this.alert_error
    },
    delete_selected: function () {
      var count = 0
      for (let i = 0; i < this.selected.length; i++) {
        var item = {
          disease_id: this.selected[i].disease_id
        }
        let that = this
        var url = this.HOME + '/maintenance/disease/delete'
        this.$http.post(url + '?token=' + sessionStorage.getItem('token'), {disease_id: item.disease_id})
          .then(function (response) {
            console.log(response.data)
            that.signal = response.data.msg
            if (that.signal === 'SUCCESS') {
              that.load()
              count = count + 1
            }
          })
      }
      if (this.count === this.length) {
        this.notice_success()
      } else {
        this.notice_error()
      }
    }

  },
  mounted: function () {
    this.load()
  },
  computed: {
  },
  watch: {
    show: function (newState, oldState) {
      if (newState === false) {
        this.eraseForm()
      }
    }
  }
}
</script>

<style scoped>

</style>
