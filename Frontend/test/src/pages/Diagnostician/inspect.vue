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
      v-model="tem"
      hide-overlay
      width="800"
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
                :items="['检验', '检查', '处置']"
                label="类型"
                required
              ></v-select>
            </v-flex>
          </v-layout>
        </v-container>
        <v-divider></v-divider>
        <v-toolbar flat dense color="white">
          <v-toolbar-title>医技项目</v-toolbar-title>
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
            <td>{{ props.item.template_medical_skill_content_name }}</td>
            <td>{{ props.item.template_medical_skill_content_department_name }}</td>
            <td>{{ props.item.template_medical_skill_content_checkpoint }}</td>
            <td>{{ props.item.template_medical_skill_content_specification }}</td>
            <td>{{ props.item.template_medical_skill_content_department_id }}</td>
            <td>{{ props.item.template_medical_skill_content_unit_price }}</td>
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
      v-model="text"
      max-width="400px"
    >
      <v-card>
        <v-card-title>
          <span class="headline">添加医技模板</span>
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
          <v-btn color="blue darken-1" flat @click="text = false">关闭</v-btn>
          <v-btn color="blue darken-1" flat @click="addTem">保存</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog
      v-model="show"
      max-width="800"
    >
      <v-card>
        <v-toolbar flat>
          <v-toolbar-title  >医技项目目录</v-toolbar-title>
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
          :headers="headers_dia"
          :items="desserts_dia"
          :expand="expand_dia"
          :search="search"
          item-key="medical_skill_content_id"
          class="elevation-1"
        >
          <template v-slot:items="props">
            <td>{{ props.item.medical_skill_content_id }}</td>
            <td>{{ props.item.medical_skill_content_name }}</td>
            <td>{{ props.item.medical_skill_content_father }}</td>
            <td>{{ props.item.medical_skill_content_unit }}</td>
            <td>{{ props.item.medical_skill_content_price }}</td>
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
            <v-layout align-center justify-center row>
              <v-flex xs2 class="mr-3">
                <v-select
                  v-model="department"
                  :items="departments"
                  item-text="department_name"
                  item_value="department_id"
                  label="科室"
                  return-object
                  required
                ></v-select>
              </v-flex>
              <v-flex xs2 class="mr-3">
                <v-text-field
                  v-model="medical_skill_checkpoint"
                  label="部位"
                  required
                ></v-text-field>
              </v-flex>
              <v-flex xs2 class="mr-3">
                <v-text-field
                  v-model="medical_skill_purpose"
                  label="目的"
                  required
                ></v-text-field>
              </v-flex>
              <v-flex xs2 class="mr-3">
                <v-select
                  v-model="medical_skill_urgent"
                  :items="isNo"
                  label="是否加急"
                  required
                ></v-select>
              </v-flex>
              <v-btn
                small
                color="primary"
                @click="addItem(props.item)"
              >
                添加
              </v-btn>
            </v-layout>
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
          <v-toolbar-title  >医技项目目录</v-toolbar-title>
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
          :headers="headers_dia"
          :items="desserts_dia"
          :expand="expand_dia"
          :search="search"
          item-key="medical_skill_content_id"
          class="elevation-1"
        >
          <template v-slot:items="props">
            <td>{{ props.item.medical_skill_content_id }}</td>
            <td>{{ props.item.medical_skill_content_name }}</td>
            <td>{{ props.item.medical_skill_content_father }}</td>
            <td>{{ props.item.medical_skill_content_unit }}</td>
            <td>{{ props.item.medical_skill_content_price }}</td>
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
            <v-layout align-center justify-center row>
              <v-flex xs2 class="mr-3">
                <v-select
                  v-model="department"
                  :items="departments"
                  item-text="department_name"
                  item_value="department_id"
                  label="科室"
                  return-object
                  required
                ></v-select>
              </v-flex>
              <v-flex xs2 class="mr-3">
                <v-text-field
                  v-model="medical_skill_checkpoint"
                  label="部位"
                  required
                ></v-text-field>
              </v-flex>
              <v-flex xs2 class="mr-3">
                <v-text-field
                  v-model="medical_skill_purpose"
                  label="目的"
                  required
                ></v-text-field>
              </v-flex>
              <v-flex xs2 class="mr-3">
                <v-select
                  v-model="medical_skill_urgent"
                  :items="isNo"
                  label="是否加急"
                  required
                ></v-select>
              </v-flex>
              <v-btn
                small
                color="primary"
                @click="addTemContentSingle(props.item)"
              >
                添加
              </v-btn>
            </v-layout>
          </template>
        </v-data-table>
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
    <v-card class="scroll-x-transition">
    <v-layout>
      <v-flex  lg9 md9 sm9 xs9>
        <v-toolbar flat dense>
          <v-toolbar-title  >合计:{{fee}}元</v-toolbar-title>
          <v-spacer/>
          <v-btn
            small
            @click="show = !show"
            color="primary"
          >
            新增
          </v-btn>
          <v-btn
            small
            :disabled="isSel"
            @click="deleteItem"
            color="primary"
          >
            删除
          </v-btn>
          <v-btn
            small
            :disabled="isSel"
            @click="startItem"
            color="primary"
          >
            开立
          </v-btn>
          <v-btn
            small
            :disabled="isSel"
            @click="endItem"
            color="primary"
          >
            作废
          </v-btn>
        </v-toolbar>
        <v-data-table
          v-model="selected"
          :headers="headers"
          :items="filterDesserts"
          :expand=false
          item-key="medical_skill_id"
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
            <td>{{ props.item.medical_skill_name }}</td>
            <td>{{ props.item.medical_skill_execute_department }}</td>
            <td>{{ props.item.medical_skill_execute_state }}</td>
            <td>{{ props.item.medical_skill_fee }}</td>
            <td>
              <v-icon
                @click="props.expanded = !props.expanded,getItem(),geturl(props.item.medical_skill_id)"
              >
                remove_red_eye
              </v-icon>
            </td>
          </template>
          <template v-slot:expand="props">
            <v-flex xs12 sm6 offset-sm1>
              <v-card flat>
                <v-card-title>
                  <div>
                      <span>
                        <div v-if="props.item.medical_skill_urgent">加急：是</div><div v-else>加急：否</div>
                      </span>
                    <span>检查部位：{{ props.item.medical_skill_checkpoint }}</span><br>
                    <span>目的：{{ props.item.medical_skill_purpose}}</span><br>
                    <span class="font-weight-regular">结果</span><br>
                    <span>详情：{{props.item.medical_skill_result}}</span><br>
                    <el-image
                      style="width: 400px; height: 400px"
                      :src="url"
                    >
                    </el-image>
                  </div>
                </v-card-title>
              </v-card>
            </v-flex>
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
      </v-flex>
      <v-divider vertical></v-divider>
      <v-flex lg3 md3 sm3 xs3>
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
                class="ml-3"
                @click="seeTem(props.item);"
              >
                详情
              </v-btn>
            </td>
          </template>
        </v-data-table>
      </v-flex>
    </v-layout>
    </v-card>
    <v-divider/>
  </div >
</template>

<script>
export default {
  props: ['msgfromfa', 'record'],
  data () {
    return {
      url: '',
      dialog_err: false,
      dialog_suc: false,
      msg_suc: 'success',
      msg_err: 'error',
      tem: false,
      dialog: false,
      selected_dia: [],
      selected: [],
      selected_tem: [],
      search: '',
      alert_success: false,
      msg_success: '',
      alert_error: false,
      msg_error: '',
      text: false,
      show: false,
      show_tem: false,
      expand: false,
      expand_dia: false,
      isNo: [{text: '是', value: 'true'}, {text: '否', value: 'false'}],
      headers_dia: [
        {
          text: '编号',
          align: 'left',
          value: 'medical_skill_content_id'
        },
        { text: '项目名称', value: 'medical_skill_content_name' },
        { text: '父级编码', value: 'medical_skill_content_father' },
        { text: '单位', value: 'medical_skill_content_unit' },
        { text: '单价', value: 'medical_skill_content_price' },
        { text: '操作', value: 'operation' }],
      desserts_dia: [],
      headers: [
        {
          text: '项目名称',
          value: 'medical_skill_name'
        },
        {
          text: '执行科室',
          value: 'medical_skill_execute_department'
        },
        {
          text: '执行状态',
          value: 'medical_skill_execute_state'
        },
        {
          text: '单价',
          value: 'medical_skill_fee'
        },
        { text: '操作', value: 'operation' }
      ],
      desserts: [],
      headers_tem: [
        {
          text: '模板名称',
          align: 'left',
          value: 'template_name'
        },
        { text: '操作', value: 'operation', sortable: false }
      ],
      headers_tem_con: [
        {
          text: '项目名称',
          value: 'template_medical_skill_content_name'
        },
        {
          text: '执行科室',
          value: 'template_medical_skill_content_department_name'
        },
        {
          text: '检查部位',
          value: 'template_medical_skill_content_checkpoint'
        },
        {
          text: '目的',
          value: 'template_medical_skill_content_specification'
        },
        {
          text: '是否加急',
          value: 'template_medical_skill_content_department_id'
        },
        {
          text: '单价',
          value: 'template_medical_skill_content_unit_price'
        },
        { text: '操作', value: 'operation' }
      ],
      desserts_tem: [],
      desserts_tem_con: [],
      medical_skill_name: '',
      medical_skill_checkpoint: '',
      medical_skill_purpose: '',
      medical_skill_urgent: '',
      template_name: '',
      template_range: '',
      template_id: '',
      template_con_name: '',
      template_con_range: '',
      template_con_type: '',
      template_con_id: '',
      departments: [],
      department: ''
    }
  },
  created: function () {
    this.load_mediskill()
    this.getItem()
    this.getTem()
    this.load_departs()
  },
  watch: {
    record: function (newState, oldState) {
      this.load_mediskill()
      this.getItem()
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
  computed: {
    filterDesserts () {
      return this.desserts.filter(this.filterType)
    },
    filterDeparts () {
      return this.departments.filter(this.filterDepart)
    },
    filterDessertsTem () {
      return this.desserts_tem.filter(this.filterType_tem)
    },
    fee () {
      var i
      var fee = 0
      for (i = 0; i < this.filterDesserts.length; i++) {
        fee = fee + this.filterDesserts[i].medical_skill_fee
      }
      return fee
    },
    isSel () {
      if (this.selected.length) {
        return false
      } else {
        return true
      }
    }
  },
  methods: {
    geturl: function (value) {
      var that = this
      var url = '/dfs/get'
      var data = {
        id: value
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.url = response.data.data[0].url
        })
    },
    network_out: function () {
      this.dialog = false
      this.dialog_err = true
      this.msg_err = '网络不通畅，请重新来过'
    },
    load_departs: function () {
      var that = this
      var url = this.HOME + '/maintenance/department/get-all'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), {
      })
        .then(function (response) {
          console.log(response.data)
          that.departments = response.data.data
        })
    },
    filterType (value) {
      return value.medical_skill_type === '检查'
    },
    filterDepart: function (value) {
      return value.department_cat === '医技科室'
    },
    filterType_tem (value) {
      return value.template_type === '检查'
    },
    getTem () {
      let that = this
      var data = {
        doctor_id: this.$store.state.user.id,
        department_id: this.$store.state.user.department_id
      }
      var url = this.HOME + '/template/get-all'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          that.desserts_tem = response.data.data
          that.dialog = false
        })
    },
    deleteTem () {
      let that = this
      var data = {
        template_id: that.template_con_id
      }
      var url = this.HOME + '/template/delete-template'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          that.getTem()
          that.tem = false
        })
    },
    updateTem () {
      let that = this
      var data = {
        template_name: that.template_con_name,
        template_init_date: new Date(),
        template_type: that.template_con_type,
        template_range: that.template_con_range,
        template_id: that.template_con_id,
        template_doctor_id: that.msgfromfa.register_info_doctor_id
      }
      var url = this.HOME + '/template/update-template'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          that.getTem()
          that.tem = false
        })
    },
    useTem (value) {
      let that = this
      var data = {
        template_id: value.template_id
      }
      var url = this.HOME + '/template/get-content-non-medicine'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          var i
          that.dialog = true
          for (i = 0; i < response.data.data.length; i++) {
            that.addItemByTem(response.data.data[i])
          }
          that.dialog = false
        })
    },
    seeTem (value) {
      let that = this
      this.tem = true
      var data = {
        template_id: value.template_id
      }
      that.template_con_name = value.template_name
      that.template_con_range = value.template_range
      that.template_con_type = value.template_type
      that.template_con_id = value.template_id
      var url = this.HOME + '/template/get-content-non-medicine'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          that.desserts_tem_con = response.data.data
          that.dialog = false
        })
    },
    addTem () {
      let that = this
      var data = {
        template_type: '检查',
        template_name: that.template_name,
        template_range: that.template_range,
        template_doctor_id: that.msgfromfa.register_info_doctor_id
      }
      console.log(data)
      var url = this.HOME + '/template/add-template'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          that.dialog = true
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
      var url = this.HOME + '/template/add-content-non-medicine'
      for (i = 0; i < that.filterDesserts.length; i++) {
        var data = {
          template_connect_id: that.template_con_id,
          template_medical_skill_content_id: that.filterDesserts[i].medical_skill_content_id,
          template_medical_skill_content_name: that.filterDesserts[i].medical_skill_name,
          template_medical_skill_content_department_id: that.filterDesserts[i].medical_skill_urgent,
          template_medical_skill_content_specification: that.filterDesserts[i].medical_skill_purpose,
          template_medical_skill_content_checkpoint: that.filterDesserts[i].medical_skill_checkpoint,
          template_medical_skill_content_department_name: that.filterDesserts[i].medical_skill_execute_department,
          template_medical_skill_content_unit_price: that.filterDesserts[i].medical_skill_fee
        }
        console.log(data)
        this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
          .then(function (response) {
            console.log(response.data)
            // that.dialog = true
            // that.getItem()
          })
      }
    },
    addTemContentSingle (value) {
      let that = this
      var url = this.HOME + '/template/add-content-non-medicine'
      var data = {
        template_connect_id: that.template_con_id,
        template_medical_skill_content_id: value.medical_skill_content_id,
        template_medical_skill_content_name: value.medical_skill_content_name,
        template_medical_skill_content_department_id: that.medical_skill_urgent,
        template_medical_skill_content_specification: that.medical_skill_purpose,
        template_medical_skill_content_checkpoint: that.medical_skill_checkpoint,
        template_medical_skill_content_department_name: that.department.department_name,
        template_medical_skill_content_unit_price: value.medical_skill_content_price
      }
      that.medical_skill_urgent = ''
      that.medical_skill_purpose = ''
      that.medical_skill_checkpoint = ''
      console.log(value)
      that.dialog = true
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          var value = {
            template_name: that.template_con_name,
            template_range: that.template_con_range,
            template_type: that.template_con_type,
            template_id: that.template_con_id
          }
          that.seeTem(value)
          that.dialog = false
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
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
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
        this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
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
    getItem () {
      let that = this
      var url = this.HOME + '/doctor/get-record'
      var data = {
        record_id: that.msgfromfa.register_info_id
      }
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(response => {
          console.log(response.data.data)
          that.desserts = response.data.data.medicalSkills
        })
    },
    addItem: function (value) {
      let that = this
      console.log(value)
      var data = {
        medical_skill_type: '检查',
        medical_skill_name: value.medical_skill_content_name,
        medical_skill_checkpoint: that.medical_skill_checkpoint,
        medical_skill_purpose: that.medical_skill_purpose,
        medical_skill_urgent: that.medical_skill_urgent,
        medical_skill_register_info_id: that.msgfromfa.register_info_id,
        medical_skill_doctor_id: that.msgfromfa.register_info_doctor_id,
        medical_skill_content_id: value.medical_skill_content_id,
        medical_skill_fee: value.medical_skill_content_price,
        medical_skill_execute_department: that.department.department_name
      }
      console.log(data)
      var url = this.HOME + '/doctor/add-medical-skill'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          that.show = false
          that.dialog = true
          that.getItem()
          that.dialog = false
        })
    },
    addItemByTem: function (value) {
      let that = this
      console.log(value)
      var data = {
        medical_skill_type: '检查',
        medical_skill_name: value.template_medical_skill_content_name,
        medical_skill_checkpoint: value.template_medical_skill_content_checkpoint,
        medical_skill_purpose: value.template_medical_skill_content_specification,
        medical_skill_urgent: value.template_medical_skill_content_department_id,
        medical_skill_register_info_id: that.msgfromfa.register_info_id,
        medical_skill_doctor_id: that.msgfromfa.register_info_doctor_id,
        medical_skill_content_id: value.template_medical_skill_content_id,
        medical_skill_fee: value.template_medical_skill_content_unit_price,
        medical_skill_execute_department: value.template_medical_skill_content_department_name
      }
      console.log(data)
      var url = this.HOME + '/doctor/add-medical-skill'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
        .then(function (response) {
          console.log(response.data)
          that.show = false
          that.dialog = true
          that.getItem()
          that.dialog = false
        })
    },
    deleteItem: function () {
      let that = this
      var i
      that.dialog = true
      var url = this.HOME + '/doctor/delete-medical-skill'
      console.log(that.selected)
      var data
      for (i = 0; i < that.selected.length; i++) {
        data = {
          medical_skill_id: that.selected[i].medical_skill_id
        }
        this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
          .then(function (response) {
            console.log(response.data)
            that.getItem()
            that.dialog = false
          })
      }
    },
    startItem: function () {
      let that = this
      var i
      that.dialog = true
      var url = this.HOME + '/doctor/start-medical-skill'
      console.log(that.selected)
      for (i = 0; i < that.selected.length; i++) {
        var data = {
          medical_skill_id: that.selected[i].medical_skill_id
        }
        this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
          .then(function (response) {
            console.log(response.data)
            that.getItem()
            that.dialog = false
          })
      }
    },
    endItem: function () {
      let that = this
      var i
      that.dialog = true
      var url = this.HOME + '/doctor/end-medical-skill'
      console.log(that.selected)
      for (i = 0; i < that.selected.length; i++) {
        var data = {
          medical_skill_id: that.selected[i].medical_skill_id
        }
        this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
          .then(function (response) {
            console.log(response.data)
            that.getItem()
            that.dialog = false
          })
      }
    },
    load_mediskill: function () {
      let that = this
      var url = this.HOME + '/maintenance/medical-skill-content/get-all'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), {
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
