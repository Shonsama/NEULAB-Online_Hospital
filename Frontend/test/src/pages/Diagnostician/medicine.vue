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
          Please stand by
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
      v-model="show_tem"
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
                  @click="addTemContentSingle(props.item)"
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
      v-model="tem"
      hide-overlay
      width="950"
    >
      <v-card>
        <v-toolbar flat>
          <v-toolbar-title >医技模板详情</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn
            icon
            flat
            color="primary"
            @click="deleteTem"
          >
            <v-icon
            >
              delete
            </v-icon>
          </v-btn>
          <v-btn
            icon
            flat
            color="primary"
            @click="updateTem"
          >
            <v-icon
            >
              save
            </v-icon>
          </v-btn>
        </v-toolbar>
        <v-container grid-list-md>
          <v-layout>
            <v-flex xs12>
              <v-text-field
                v-model="template_con_name"
                label="模板名称"
                required
              ></v-text-field>
            </v-flex>
            <v-flex xs12>
              <v-select
                v-model="template_con_range"
                :items="['个人', '科室', '全院']"
                label="模板范围"
                required
              ></v-select>
            </v-flex>
            <v-flex xs12>
              <v-select
                v-model="template_con_type"
                :items="['中药', '西药']"
                label="类型"
                required
              ></v-select>
            </v-flex>
          </v-layout>
        </v-container>
        <v-divider></v-divider>
        <v-toolbar flat dense color="white">
          <v-toolbar-title>药品项目</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn
            icon
            flat
            color="primary"
            @click="deleteTemContent"
          >
            <v-icon
            >
              delete
            </v-icon>
          </v-btn>
          <v-btn
            icon
            flat
            color="primary"
            @click="show_tem = !show_tem"
          >
            <v-icon
            >
              add
            </v-icon>
          </v-btn>
        </v-toolbar>
        <v-divider></v-divider>
        <v-data-table
          v-model="selected_tem"
          :headers="headers_tem_con"
          :items="desserts_tem_con"
          select-all
          item-key="template_content_id"
        >
          <template v-slot:items="props">
            <td>
              <v-checkbox
                v-model="props.selected"
                primary
                hide-details
              ></v-checkbox>
            </td>
            <td>{{ props.item.template_medical_skill_content_department_name }}</td>
            <td>{{ props.item.template_medicine_name }}</td>
            <td>{{ props.item.template_medicine_specification }}</td>
            <td>{{ props.item.template_medicine_usage }}</td>
            <td>{{ props.item.template_medicine_consumption }}</td>
            <td>{{ props.item.template_medicine_unit_price }}</td>
            <td>{{ props.item.template_medicine_frequency }}</td>
            <td>{{ props.item.template_medicine_number }}</td>
            <td>
              <v-btn
                icon
                flat
                small
                color="primary"
                right
                @click="deleteTemContentSingle(props.item)"
                class="ml-3"
              >
                删除
              </v-btn>
            </td>
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
    <v-dialog
      v-model="text"
      max-width="400"
    >
      <v-card>
        <v-card-title>
          <span class="headline">添加处方模板</span>
        </v-card-title>
        <v-card-text>
          <v-container grid-list-md>
            <v-layout wrap>
              <v-flex xs12>
                <v-text-field
                  v-model="template_name"
                  label="模板名称"
                  required
                ></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-select
                  v-model="template_range"
                  :items="['个人', '科室', '全院']"
                  label="模板范围"
                  required
                ></v-select>
              </v-flex>
            </v-layout>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click="text = false">Close</v-btn>
          <v-btn color="blue darken-1" flat @click="addTem">Save</v-btn>
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
                        @click="text = !text;tem_add = props.item.prescriptionContents"
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
            <v-toolbar-title  >常用模板</v-toolbar-title>
          </v-toolbar>
          <v-data-table
            :headers="headers_tem"
            :items="filterDessertsTem"
            item-key="template_id"
          >
            <template v-slot:items="props">
              <td>{{ props.item.template_name }}</td>
              <td>
                <v-btn
                  icon
                  color="primary"
                  flat
                  right
                  small
                  class="ml-3"
                  @click="useTem(props.item)"
                >
                  使用
                </v-btn>
                <v-btn
                  icon
                  color="primary"
                  flat
                  small
                  right
                  @click="seeTem(props.item);"
                >
                  详情
                </v-btn>
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
  props: ['msgfromfa', 'record'],
  data () {
    return {
      tem: false,
      tem_add: [],
      selected_dia: [],
      selected_pre: [],
      selected_tem: [],
      selected_con: [],
      alert_success: false,
      alert_error: false,
      search: '',
      show: false,
      show_tem: false,
      show_pre_dia: false,
      dialog: false,
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
          value: 'template_name'
        },
        { text: '操作', value: 'operation', sortable: false }
      ],
      desserts_tem: [],
      headers_tem_con: [
        {
          text: '药品编号',
          align: 'left',
          value: 'template_medical_skill_content_department_name'
        },
        {
          text: '药品名称',
          align: 'left',
          value: 'template_medicine_name'
        },
        {
          text: '规格',
          value: 'template_medicine_specification'
        },
        {
          text: '用法',
          value: 'template_medicine_usage'
        },
        {
          text: '用量',
          value: 'template_medicine_consumption'
        },
        {
          text: '单价',
          value: 'template_medicine_unit_price'
        },
        {
          text: '频次',
          value: 'template_medicine_frequency'
        },
        {
          text: '数量',
          value: 'template_medicine_number'
        },
        { text: '操作', value: 'operation', sortable: false }
      ],
      desserts_tem_con: [],
      prescription_consumption: '',
      prescription_frequency: '',
      prescription_num: '',
      prescription_day: '',
      prescription_name: '',
      prescription_id: '',
      template_name: '',
      template_range: '',
      template_id: '',
      template_con_name: '',
      template_con_range: '',
      template_con_type: '',
      template_con_id: '',
      new_prescription_id: ''
    }
  },
  watch: {
    record: function (newState, oldState) {
      this.getItem()
      this.load_medicne()
      this.getTem()
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
  mounted: function () {
    this.getItem()
    this.load_medicne()
    this.getTem()
  },
  computed: {
    filterDesserts () {
      return this.desserts_pre.filter(this.filterType)
    },
    filterDessertsTem () {
      return this.desserts_tem.filter(this.filterType_tem)
    }
  },
  methods: {
    notice_success: function () {
      window.setTimeout(this.change_success, 1500)
    },
    filterType (value) {
      return value.prescription_type === '西药'
    },
    filterType_tem (value) {
      return value.template_type === '西药'
    },
    filterType_med (value) {
      return value.medicine_type === '西药'
    },
    getTem () {
      let that = this
      var data = {
        department_id: this.$store.state.user.department_id,
        doctor_id: this.$store.state.user.id
      }
      var url = this.HOME + '/template/get-all'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.desserts_tem = response.data.data
        })
    },
    deleteTem () {
      let that = this
      var data = {
        template_id: that.template_con_id
      }
      var url = this.HOME + '/template/delete-template'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.getTem()
          that.dialog =false
          that.tem = false
        })
    },
    updateTem () {
      let that = this
      var data = {
        template_id: that.template_con_id,
        template_type: that.template_con_type,
        template_range: that.template_con_range,
        template_name: that.template_con_name,
        template_init_date: new Date(),
        template_doctor_id: that.msgfromfa.register_info_doctor_id
      }
      that.dialog = true
      var url = this.HOME + '/template/update-template'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.getTem()
          that.dialog = false
          that.tem = false
        })
    },
    useTem (value) {
      let that = this
      var data = {
        prescription_type: '西药',
        prescription_doctor_id: that.msgfromfa.register_info_doctor_id,
        prescription_register_info_id: that.msgfromfa.register_info_id,
        prescription_name: value.template_name,
        prescription_fee: '0'
      }
      console.log(data)
      var url = this.HOME + '/doctor/add-prescription'
      this.$http.post(url, data)
        .then(function (response) {
          that.dialog = true
          that.new_prescription_id = response.data.data.prescription_id
          data = {
            template_id: value.template_id
          }
          var url1 = that.HOME + '/template/get-content-non-medicine'
          that.$http.post(url1, data)
            .then(function (response) {
              console.log(response.data)
              var i
              for (i = 0; i < response.data.data.length; i++) {
                that.addContentByTem(response.data.data[i])
              }
            })
          console.log(response.data)
          that.getItem()
        })
    },
    seeTem (value) {
      let that = this
      this.tem = true
      var data = {
        template_id: value.template_id
      }
      that.template_con_id = value.template_id
      that.template_con_name = value.template_name
      that.template_con_range = value.template_range
      that.template_con_type = value.template_type
      var url = this.HOME + '/template/get-content-non-medicine'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.desserts_tem_con = response.data.data
          that.dialog = false
        })
    },
    addTem () {
      let that = this
      var data = {
        template_type: '西药',
        template_name: that.template_name,
        template_range: that.template_range,
        template_doctor_id: that.msgfromfa.register_info_doctor_id
      }
      console.log(data)
      var url = this.HOME + '/template/add-template'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.template_con_id = response.data.data.template_id
          that.addTemContent()
          that.text = false
          that.getTem()
          that.dialog = false

        })
    },
    addTemContent () {
      let that = this
      var i
      var url = this.HOME + '/template/add-content-medicine'
      for (i = 0; i < that.tem_add.length; i++) {
        var data = {
          template_medicine_unit_price: that.tem_add[i].prescription_unit_price,
          template_medicine_consumption: that.tem_add[i].prescription_consumption,
          template_medicine_unit: that.tem_add[i].medicine_unit,
          template_connect_id: that.template_con_id,
          template_medicine_name: that.tem_add[i].medicine.medicine_name,
          template_medicine_specification: that.tem_add[i].medicine.medicine_specifications,
          template_medicine_usage: that.tem_add[i].prescription_day,
          template_medicine_number: that.tem_add[i].prescription_num,
          template_medicine_frequency: that.tem_add[i].prescription_frequency,
          template_medical_skill_content_department_name: that.tem_add[i].medicine.medicine_id
        }
        console.log(data)
        this.$http.post(url, data)
          .then(function (response) {
            console.log(response.data)
            // that.dialog = true
            // that.getTem()
          })
      }
    },
    addTemContentSingle (value) {
      let that = this
      var url = this.HOME + '/template/add-content-medicine'
      var data = {
        template_connect_id: that.template_con_id,
        template_medicine_name: value.medicine_name,
        template_medicine_specification: value.medicine_specifications,
        template_medicine_unit: value.medicine_unit,
        template_medicine_usage: that.prescription_day,
        template_medicine_consumption: that.prescription_consumption,
        template_medicine_frequency: that.prescription_frequency,
        template_medicine_number: that.prescription_num,
        template_medicine_unit_price: value.medicine_unit_price,
        template_medical_skill_content_department_name: value.medicine_id
      }
      that.prescription_day = ''
      that.prescription_consumption = ''
      that.prescription_num = ''
      that.prescription_frequency = ''
      console.log(value)
      that.dialog = true
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          var value = {
            template_name: that.template_con_name,
            template_range: that.template_con_range,
            template_type: that.template_con_type,
            template_id: that.template_con_id
          }
          that.seeTem(value)
          that.show_tem = false
        })
    },
    deleteTemContentSingle (value) {
      let that = this
      var url = this.HOME + '/template/delete-content'
      var data = {
        template_content_id: value.template_content_id
      }
      that.dialog = true
      console.log(value)
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          var value = {
            template_name: that.template_con_name,
            template_range: that.template_con_range,
            template_type: that.template_con_type,
            template_id: that.template_con_id
          }
          that.seeTem(value)
        })
    },
    deleteTemContent () {
      let that = this
      var i
      var url = this.HOME + '/template/delete-content'
      for (i = 0; i < that.selected_tem.length; i++) {
        var data = {
          template_content_id: that.selected_tem[i].template_content_id
        }
        console.log(data)
        this.$http.post(url, data)
          .then(function (response) {
            console.log(response.data)
            var value = {
              template_name: that.template_con_name,
              template_range: that.template_con_range,
              template_type: that.template_con_type,
              template_id: that.template_con_id
            }
            that.dialog = true
            that.seeTem(value)
          })
      }
    },
    addContentByTem: function (value) {
      console.log(value)
      let that = this
      var data = {
        prescription_id: that.new_prescription_id,
        prescription_consumption: value.template_medicine_consumption,
        prescription_medicine_id: value.template_medical_skill_content_department_name,
        prescription_frequency: value.template_medicine_frequency,
        prescription_num: value.template_medicine_number,
        prescription_day: value.template_medicine_usage
      }
      var url = this.HOME + '/doctor/add-medicine'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.getItem()
          that.show = false
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
      that.dialog = true
      var url = this.HOME + '/doctor/add-medicine'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.getItem()
          that.show = false
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
      that.dialog = true
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
    getItem () {
      let that = this
      var url = this.HOME + '/doctor/get-record'
      var data = {
        record_id: that.msgfromfa.register_info_id
      }
      that.dialog = true
      this.$http.post(url, data)
        .then(response => {
          console.log(response.data.data)
          that.desserts_pre = response.data.data.prescriptions
          that.dialog = false
        })
    },
    addItem: function () {
      let that = this
      var data = {
        prescription_type: '西药',
        prescription_doctor_id: that.msgfromfa.register_info_doctor_id,
        prescription_register_info_id: that.msgfromfa.register_info_id,
        prescription_name: that.prescription_name,
        prescription_fee: '0'
      }
      console.log(data)
      var url = this.HOME + '/doctor/add-prescription'
      this.$http.post(url, data)
        .then(function (response) {
          that.dialog = true
          that.show_pre_dia = false
          console.log(response.data)
          that.getItem()
        })
    },
    addItemByName: function (name, id) {

    },
    deleteItem: function () {
      let that = this
      var i
      var url = this.HOME + '/doctor/delete-prescription'
      console.log(that.selected)
      that.dialog = true
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
            that.dialog = true
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
            that.dialog = true
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
          that.desserts_dia = response.data.data.filter(that.filterType_med)
        })
    }
  }
}
</script>

<style scoped>

</style>
