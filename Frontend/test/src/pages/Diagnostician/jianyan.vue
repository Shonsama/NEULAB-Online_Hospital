<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <v-dialog
      v-model="show1"
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
          :search="search"
          item-key="non_medicine_id"
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
            <td>{{ props.item.non_medicine_id }}</td>
            <td>{{ props.item.non_medicine_name }}</td>
            <td>{{ props.item.non_medicine_type }}</td>
            <td>{{ props.item.non_medicine_specification }}</td>
            <td>{{ props.item.non_medicine_unit_price }}</td>
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
            color="primary"
          >
            删除
          </v-btn>
          <v-btn
            small
            color="primary"
          >
            开立
          </v-btn>
          <v-btn
            small
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
          item-key="name"
          select-all
        >
          <template v-slot:items="props">
            <tr @click="props.expanded = !props.expanded">
            <td>
              <v-checkbox
                v-model="props.selected"
                primary
                hide-details
              ></v-checkbox>
            </td>
            <td>{{ props.item.name }}</td>
            <td class="text-xs-right">{{ props.item.calories }}</td>
            <td class="text-xs-right">{{ props.item.fat }}</td>
            <td class="text-xs-right">{{ props.item.carbs }}</td>
            <td class="text-xs-right">{{ props.item.protein }}</td>
            <td class="text-xs-right">{{ props.item.iron }}</td>
            <td class="justify-center layout px-0">
              <v-icon
                small
                class="mr-2"
                @click="editItem(props.item)"
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
            </tr>
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
          v-model="selected"
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
      search: '',
      show1: false,
      expand: false,
      headers_dia: [
        {
          text: '编号',
          align: 'left',
          value: 'medical_skill_content_id'
        },
        { text: '项目名称', value: 'medical_skill_content_name' },
        { text: '父级编码', value: 'medical_skill_content_father' },
        { text: '单位', value: 'medical_skill_content_unit' },
        { text: '单价', value: 'medical_skill_content_price' }],
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
        }
      ],
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
      ]
    }
  },
  mounted: function () {
    this.load_mediskill()
  },
  methods: {
    addItem: function () {
      let that = this
      var data = {
        medical_skill_type: '检验',
        medical_skill_name: '血检',
        medical_skill_checkpoint: '血液',
        medical_skill_purpose: '检查',
        medical_skill_urgent: 'true',
        medical_skill_register_info_id: 5,
        medical_skill_user_id: 1,
        medical_skill_doctor_id: 1,
        medical_skill_non_medical_id: 2,
        medical_skill_fee: '50.00',
        medical_skill_execute_department: '血液科'
      }
      var url = this.HOME + '/doctor/add-medical-skill'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.desserts_dia = response.data.data
        })
    },
    deleteItem: function () {
      let that = this
      var data = {
        medical_skill_id: 1
      }
      var url = this.HOME + '/doctor/delete-medical-skill'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.desserts_dia = response.data.data
        })
    },
    startItem: function () {
      let that = this
      var data = {
        medical_skill_id: 1
      }
      var url = this.HOME + '/doctor/start-medical-skill'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.desserts_dia = response.data.data
        })
    },
    endItem: function () {
      let that = this
      var data = {
        medical_skill_id: 1
      }
      var url = this.HOME + '/doctor/end-medical-skill'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.desserts_dia = response.data.data
        })
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
