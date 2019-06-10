<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <v-dialog
      v-model="show"
      max-width="800"
    >
      <v-card>
        <v-toolbar flat>
          <v-toolbar-title>药品项目目录</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-flex xs3>
            <v-text-field
              prepend-inner-icon="search"
              v-model="search"
              label="编号/名称"
              required
            ></v-text-field>
          </v-flex>
        </v-toolbar>
        <v-data-table
          v-model="selected_dia"
          :headers="headers_dia"
          :items="desserts_dia"
          expand
          :search="search"
          item-key="medicine_id"
          class="elevation-1"
        >
          <template v-slot:items="props">
            <td>{{ props.item.medicine_id }}</td>
            <td>{{ props.item.medicine_name }}</td>
            <td>{{ props.item.medicine_type }}</td>
            <td>{{ props.item.medicine_specifications }}</td>
            <td>{{ props.item.medicine_unit_price }}</td>
            <td>
              <v-btn
                icon
                flat
                color="primary"
                right
              >
                <v-icon
                  @click="props.expanded = !props.expanded"
                  color="primary"
                >
                  add
                </v-icon>
              </v-btn>
            </td>
          </template>
          <template v-slot:expand="props">
            <div>
            <v-layout align-center justify-center row>
              <v-flex xs2 class="mr-3">
                <v-text-field
                  v-model="prescription_day"
                  label="用量"
                  required
                ></v-text-field>
              </v-flex>
              <v-flex xs2 class="mr-3">
                <v-text-field
                  v-model="prescription_consumption"
                  label="用法"
                  required
                ></v-text-field>
              </v-flex>
              <v-flex xs2 class="mr-3">
                <v-text-field
                  v-model="prescription_frequency"
                  label="频次"
                  required
                ></v-text-field>
              </v-flex>
              <v-flex xs2 class="mr-3">
                <v-text-field
                  v-model="prescription_num"
                  label="数量"
                  required
                ></v-text-field>
              </v-flex>
              <v-btn
                small
                color="primary"
                @click="addContent(props.item)"
              >
                添加
              </v-btn>
            </v-layout>
            </div>
          </template>
        </v-data-table>
      </v-card>
    </v-dialog>
    <v-dialog
      v-model="show_pre_dia"
      max-width="400"
    >
      <v-card ref="form">
        <v-card-text>
          <v-text-field
            v-model="prescription_name"
            label="处方名称"
            required
          ></v-text-field>
        </v-card-text>
        <v-divider class="mt-2"></v-divider>
        <v-card-actions>
          <v-btn flat @click="show_pre_dia = !show_pre_dia">Cancel</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="primary" flat @click="addItem">Submit</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-card>
      <v-layout>
        <v-flex xs9>
          <v-toolbar flat dense>
            <v-toolbar-title>处方</v-toolbar-title>
            <v-spacer/>
            <v-btn
              color="primary"
              small
              @click="show_pre_dia = !show_pre_dia"
            >
              增方
            </v-btn>
            <v-btn
              small
              @click="deleteItem"
              color="primary"
            >
              删方
            </v-btn>
            <v-btn
              small
              @click="startItem"
              color="primary"
            >
              开立
            </v-btn>
            <v-btn
              small
              @click="endItem"
              color="primary"
            >
              作废
            </v-btn>
          </v-toolbar>
          <v-data-table
            v-model="selected_pre"
            :headers="headers_pre"
            :items="filterDesserts"
            item-key="prescription_id"
            select-all
            :expand="expand"
          >
            <template v-slot:items="props">
              <td>
                <v-checkbox
                  v-model="props.selected"
                  primary
                  hide-details
                ></v-checkbox>
              </td>
              <td>{{ props.item.prescription_id }}</td>
              <td>{{ props.item.prescription_name }}</td>
              <td>{{ props.item.prescription_execute_state }}</td>
              <td>{{ props.item.prescription_fee }}</td>
              <td>
                <v-icon
                  @click="props.expanded = !props.expanded"
                >
                  remove_red_eye
                </v-icon>
              </td>
            </template>
            <template v-slot:expand="props">
              <div>
              <v-container style="max-width: 750px">
              <v-card>
                <v-toolbar flat dense color="white">
                  药品
                  <v-spacer/>
                  <v-btn
                    color="primary"
                    small
                    icon
                    flat
                    @click="show=!show; prescription_id = props.item.prescription_id"
                  >
                    <v-icon>
                      add
                    </v-icon>
                  </v-btn>
                  <v-btn
                    color="primary"
                    small
                    icon
                    flat
                    @click="deleteContent(props.item)"
                  >
                    <v-icon>
                      delete
                    </v-icon>
                  </v-btn>
                </v-toolbar>
                <v-divider/>

                <v-data-table
                  v-model="selected_con"
                  :headers="headers_con"
                  :items="props.item.prescriptionContents"
                  item-key="prescription_content_id"
                  hide-actions
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
                    <td>{{ props.item.prescription_medicine_id }}</td>
                    <td>{{ props.item.medicine.medicine_name }}</td>
                    <td>{{ props.item.medicine.medicine_specifications }}</td>
                    <td>{{ props.item.prescription_day }}</td>
                    <td>{{ props.item.prescription_consumption }}</td>
                    <td>{{ props.item.prescription_unit_price }}</td>
                    <td>{{ props.item.prescription_frequency }}</td>
                    <td>{{ props.item.prescription_num }}</td>
                  </template>
                </v-data-table>
                <v-divider/>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn
                    class="white--text"
                    color="primary"
                    small
                    @click="text = !text"
                  >
                    保存
                  </v-btn>
                </v-card-actions>
              </v-card>
              </v-container>
              </div>
            </template>
          </v-data-table>
          <v-divider/>
        </v-flex>
        <v-divider vertical></v-divider>
        <v-flex  xs3>
          <v-toolbar flat dense>
            <v-toolbar-title>常用模板</v-toolbar-title>
          </v-toolbar>
            <v-data-table
              v-model="selected_tem"
              :headers="headers_tem"
              :items="desserts_tem"
              item-key="non_medicine_id"
            >
              <template v-slot:items="props">
                <td>{{ props.item.non_medicine_id }}</td>
                <td>
                  <v-icon
                    small
                    class="mr-2"
                  >
                    add
                  </v-icon>
                  <v-icon
                    small
                  >
                    settings
                  </v-icon>
                </td>
              </template>
            </v-data-table>
          <v-divider/>
        </v-flex>
      </v-layout>
    </v-card>
  </div>
</template>

<script>
export default {
  props: ['msgfromfa'],
  data () {
    return {
      selected_dia: [],
      selected_pre: [],
      selected_con: [],
      selected_tem: [],
      alert_success: false,
      alert_error: false,
      search: '',
      show: false,
      show_pre_dia: false,
      text: false,
      expand: false,
      expand1: true,
      expand_dia: false,
      headers_dia: [
        {
          text: '药品编号',
          align: 'left',
          value: 'medicine_id'
        },
        { text: '药品名称', value: 'medicine_name' },
        { text: '药品类型', value: 'medicine_type' },
        { text: '药品规格', value: 'medicine_specification' },
        { text: '单价', value: 'medicine_unit_price' },
        { text: '操作', value: 'operation', sortable: false }],
      desserts_dia: [],
      headers_con: [
        {
          text: '药品编号',
          align: 'left',
          value: 'prescription_medicine_id'
        },
        {
          text: '药品名称',
          align: 'left',
          value: 'medicine_name'
        },
        {
          text: '规格',
          value: 'medicine_specification'
        },
        {
          text: '用法',
          value: 'prescription_day'
        },
        {
          text: '用量',
          value: 'prescription_consumption'
        },
        {
          text: '单价',
          value: 'prescription_content_id'
        },
        {
          text: '频次',
          value: 'prescription_frequency'
        },
        {
          text: '数量',
          value: 'prescription_num'
        }
      ],
      headers_pre: [
        {
          text: '处方ID',
          align: 'left',
          value: 'prescription_id'
        },
        {
          text: '处方名称',
          value: 'prescription_name'
        },
        {
          text: '状态',
          value: 'prescription_execute_state'
        },
        {
          text: '金额',
          value: 'prescription_fee'
        },
        { text: '操作', value: 'operation', sortable: false }],
      desserts_pre: [],
      headers_tem: [
        {
          text: '模板名称',
          align: 'left',
          value: 'non_medicine_id'
        },
        { text: '操作', value: 'operation', sortable: false }
      ],
      desserts_tem: [{
        non_medicine_id: '1'
      }],
      headers_tem_con: [
        {
          text: '药品编号',
          align: 'left',
          value: 'medicine_id'
        },
        {
          text: '药品名称',
          align: 'left',
          value: 'medicine_name'
        },
        {
          text: '规格',
          value: 'medicine_specifications'
        },
        {
          text: '用法',
          value: 'prescription_day'
        },
        {
          text: '用量',
          value: 'prescription_consumption'
        },
        {
          text: '单价',
          value: 'medicine_unit_price'
        },
        {
          text: '频次',
          value: 'prescription_frequency'
        },
        {
          text: '数量',
          value: 'prescription_num'
        }
      ],
      desserts_tem_con: [],
      prescription_consumption: '',
      prescription_frequency: '',
      prescription_num: '',
      prescription_day: '',
      prescription_name: '',
      prescription_id: ''
    }
  },
  created: function () {
    this.getItem()
    this.load_medicne()
  },
  computed: {
    filterDesserts () {
      return this.desserts_pre.filter(this.filterType)
    }
  },
  methods: {
    notice_success: function () {
      window.setTimeout(this.change_success, 1500)
    },
    filterType (value) {
      return value.prescription_type === '中药'
    },
    getItem () {
      let that = this
      var url = this.HOME + '/doctor/get-record'
      var data = {
        record_id: '6'
      }
      this.$http.post(url, data)
        .then(response => {
          console.log(response.data.data)
          that.desserts_pre = response.data.data.prescriptions
        })
    },
    addContent: function (value) {
      let that = this
      console.log(value)
      var data = {
        prescription_id: that.prescription_id,
        prescription_consumption: that.prescription_consumption,
        prescription_medicine_id: value.medicine_id,
        prescription_frequency: that.prescription_frequency,
        prescription_num: that.prescription_num,
        prescription_day: that.prescription_day
      }
      console.log(data)
      var url = this.HOME + '/doctor/add-medicine'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.getItem()
        })
      that.prescription_id = ''
      that.prescription_consumption = ''
      that.prescription_frequency = ''
      that.prescription_num = ''
      that.prescription_day = ''
    },
    deleteContent: function () {
      let that = this
      var i
      var url = this.HOME + '/doctor/delete-medicine'
      console.log(that.selected_con)
      var data
      for (i = 0; i < that.selected_con.length; i++) {
        data = {
          prescription_content_id: that.selected_con[i].prescription_content_id
        }
        this.$http.post(url, data)
          .then(function (response) {
            console.log(response.data)
            that.getItem()
          })
      }
    },
    addItem: function () {
      let that = this
      var data = {
        prescription_type: '中药',
        prescription_doctor_id: 1,
        prescription_register_info_id: 6,
        prescription_name: that.prescription_name

      }
      console.log(data)
      var url = this.HOME + '/doctor/add-prescription'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.getItem()
        })
    },
    deleteItem: function () {
      let that = this
      var i
      var url = this.HOME + '/doctor/delete-prescription'
      console.log(that.selected)
      var data
      for (i = 0; i < that.selected_pre.length; i++) {
        data = {
          prescription_id: that.selected_pre[i].prescription_id
        }
        this.$http.post(url, data)
          .then(function (response) {
            console.log(response.data)
            that.getItem()
          })
      }
    },
    startItem: function () {
      let that = this
      var i
      var url = this.HOME + '/doctor/send-prescription'
      console.log(that.selected_pre)
      for (i = 0; i < that.selected_pre.length; i++) {
        var data = {
          prescription_id: that.selected_pre[i].prescription_id
        }
        this.$http.post(url, data)
          .then(function (response) {
            console.log(response.data)
            that.getItem()
          })
      }
    },
    endItem: function () {
      let that = this
      var i
      var url = this.HOME + '/doctor/end-prescription'
      console.log(that.selected)
      for (i = 0; i < that.selected_pre.length; i++) {
        var data = {
          prescription_id: that.selected_pre[i].prescription_id
        }
        this.$http.post(url, data)
          .then(function (response) {
            console.log(response.data)
            that.getItem()
          })
      }
    },
    load_medicne: function () {
      let that = this
      var url = this.HOME + '/maintenance/medicine/get-all'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.desserts_dia = response.data.data
        })
    }
  }
}
</script>

<style scoped>

</style>
