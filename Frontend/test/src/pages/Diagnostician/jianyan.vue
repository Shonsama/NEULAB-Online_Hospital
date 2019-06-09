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
      show: false,
      expand: false,
      headers_dia: [
        {
          text: '非药品编号',
          align: 'left',
          value: 'non_medicine_id'
        },
        { text: '非药品名称', value: 'non_medicine_name' },
        { text: '非药品类型', value: 'non_medicine_type' },
        { text: '非药品规格', value: 'non_medicine_specification' },
        { text: '单价', value: 'non_medicine_unit_price' }],
      desserts_dia: [],
      headers: [
        {
          text: '申请名称',
          align: 'left',
          value: 'non_medicine_id'
        },
        {
          text: '项目名称',
          value: 'non_medicine_name'
        },
        {
          text: '执行科室',
          value: 'non_medicine_type'
        },
        {
          text: '执行状态',
          value: 'medical_skill_execute_state'
        },
        {
          text: '单价',
          value: 'non_medicine_unit_price'
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
      }
      ]
    }
  },
  mounted: function () {
    this.load_mediskill()
  },
  methods: {
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
