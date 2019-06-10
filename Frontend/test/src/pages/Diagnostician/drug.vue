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
          :expand="expand"
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
                @click="addItem(props.item)"
              >
                添加
              </v-btn>
            </v-layout>
          </template>
        </v-data-table>
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
              @click="show = !show"
            >
              增方
            </v-btn>
            <v-btn
              small
              color="primary"
            >
              删方
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
            v-model="selected_pre"
            :headers="headers_pre"
            :items="desserts_pre"
            :expand="expand1"
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
                <td>{{ props.item.prescription_name }}</td>
                <td>{{ props.item.prescription_execute_state }}</td>
                <td>{{ props.item.prescription_fee }}</td>
                <td>
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
                  :items="desserts_con"
                  item-key="name"
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
                      <td>{{ props.item.name }}</td>
                      <td>{{ props.item.state }}</td>
                      <td>{{ props.item.fee }}</td>
                      <td>
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
      selected_dia: '',
      selected_pre: '',
      selected_con: '',
      selected_tem: '',
      search: '',
      show: false,
      text: false,
      expand: true,
      expand1: false,
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
          value: 'medicine_id'
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
      desserts_con: [],
      headers_pre: [
        {
          text: '处方名称',
          align: 'left',
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
        {
          text: '操作',
          value: 'operation',
          sortable: false
        }],
      desserts_pre: [{
        name: '1',
        state: '1',
        fee: '1'
      }],
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
      prescription_frequency: '一天十次',
      prescription_num: '',
      prescription_day: ''
    }
  },
  mounted: function () {
    this.getItem()
    this.load_medicne()
  },
  methods: {
    getItem () {
      let that = this
      var url = this.HOME + '/doctor/get-record'
      var data = {
        record_id: '5'
      }
      this.$http.post(url, data)
        .then(response => {
          console.log(response.data.data)
          that.desserts_pre = response.data.data.prescriptions
        })
    },
    addItem: function (value) {
      let that = this
      console.log(value)
      var data = {
        prescription_type: '中药',
        prescription_doctor_id: 1,
        prescription_register_info_id: 6
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
      var url = this.HOME + '/doctor/start-prescription'
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
      var url = this.HOME + '/doctor/end-prescription'
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
