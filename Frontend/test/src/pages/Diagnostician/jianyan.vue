<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
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
          v-model="selected_dia"
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
                <v-text-field
                  v-model="medical_skill_name"
                  label="申请名称"
                  required
                ></v-text-field>
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
    <v-layout>
      <v-flex  lg8>
        <v-toolbar flat dense>
          <v-toolbar-title  >合计:</v-toolbar-title>
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
            @click="deleteItem"
            color="primary"
          >
            删除
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
          v-model="selected"
          :headers="headers"
          :items="desserts"
          :expand="expand"
          item-key="medical_skill_name"
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
            <td>{{ props.item.medical_skill_checkpoint }}</td>
            <td>{{ props.item.medical_skill_purpose }}</td>
            <td>{{ props.item.medical_skill_fee }}</td>
            <td>
              <v-icon
                @click="props.expanded = !props.expanded"
              >
                remove_red_eye
              </v-icon>
            </td>
          </template>
          <template v-slot:expand="props">
            <v-card flat>
              <v-card-text>Peek-a-boo!</v-card-text>
            </v-card>
          </template>
        </v-data-table>
        <v-divider/>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            class="white--text"
            color="primary"
            small
          >
            保存
          </v-btn>
        </v-card-actions>
      </v-flex>
      <v-divider vertical></v-divider>
      <v-flex lg4>
        <v-toolbar flat dense>
          <v-toolbar-title  >常用模板</v-toolbar-title>
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
                check
              </v-icon>
              <v-icon
                small
              >
                settings
              </v-icon>
            </td>
          </template>
        </v-data-table>
      </v-flex>
    </v-layout>
    <v-divider/>
  </div >
</template>

<script>
export default {
  props: ['msgfromfa'],
  data () {
    return {
      selected_dia: '',
      selected: '',
      selected_tem: '',
      search: '',
      text: false,
      show: false,
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
          text: '检查部位',
          value: 'medical_skill_checkpoint'
        },
        {
          text: '目的',
          value: 'medical_skill_purpose'
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
          value: 'non_medicine_id'
        },
        { text: '操作', value: 'operation', sortable: false }
      ],
      desserts_tem: [{
        non_medicine_id: '1'
      }
      ],
      medical_skill_name: '',
      medical_skill_checkpoint: '',
      medical_skill_purpose: '',
      medical_skill_urgent: '',
      template_name: ''
    }
  },
  mounted: function () {
    this.load_mediskill()
    this.getItem()
  },
  methods: {
    addTem () {
      if (this.text) {

      } else {
        this.text = true
      }
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
          that.desserts = response.data.data.medicalSkills
        })
    },
    addItem: function (value) {
      let that = this
      console.log(value)
      var data = {
        medical_skill_type: '检验',
        medical_skill_name: value.medical_skill_content_name,
        medical_skill_checkpoint: that.medical_skill_checkpoint,
        medical_skill_purpose: that.medical_skill_purpose,
        medical_skill_urgent: that.medical_skill_urgent,
        medical_skill_register_info_id: '6',
        medical_skill_doctor_id: 1,
        medical_skill_content_id: value.medical_skill_content_id,
        medical_skill_fee: value.medical_skill_content_price,
        medical_skill_execute_department: '血液科'
      }
      console.log(data)
      var url = this.HOME + '/doctor/add-medical-skill'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.getItem()
        })
    },
    deleteItem: function () {
      let that = this
      var i
      var url = this.HOME + '/doctor/delete-medical-skill'
      console.log(that.selected)
      var data
      for (i = 0; i < that.selected.length; i++) {
        data = {
          medical_skill_id: that.selected[i].medical_skill_id
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
      var url = this.HOME + '/doctor/start-medical-skill'
      console.log(that.selected)
      for (i = 0; i < that.selected.length; i++) {
        var data = {
          medical_skill_id: that.selected[i].medical_skill_id
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
      var url = this.HOME + '/doctor/end-medical-skill'
      console.log(that.selected)
      for (i = 0; i < that.selected.length; i++) {
        var data = {
          medical_skill_id: that.selected[i].medical_skill_id
        }
        this.$http.post(url, data)
          .then(function (response) {
            console.log(response.data)
            that.getItem()
          })
      }
    },
    load_mediskill: function () {
      let that = this
      var url = this.HOME + '/maintenance/medical-skill-content/get-all'
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
