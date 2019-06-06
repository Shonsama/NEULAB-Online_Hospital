<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
      <v-layout>
        <v-flex  lg9>
          <v-toolbar flat dense>
            <v-toolbar-title  >合计:</v-toolbar-title>
            <v-spacer/>
            <v-btn
              small
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
            item-key="name"
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
            </template>
          </v-data-table>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              class="white--text"
              color="primary"
            >
              保存
            </v-btn>
          </v-card-actions>
        </v-flex>
        <v-divider vertical></v-divider>
        <v-flex  xs3>
          <v-toolbar flat dense>
            <v-toolbar-title >常用模板</v-toolbar-title>
          </v-toolbar>
          <v-data-table
            v-model="selected"
            :headers="headers_tem"
            :items="desserts"
            item-key="name1"
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
            </template>
          </v-data-table>
        </v-flex>
      </v-layout>
  </div>

</template>

<script>
export default {
  props: ['msgfromfa'],
  data () {
    return {
      headers: [
        {
          text: '申请名称',
          align: 'left',
          value: 'non_medicine_id'
        },
        { text: '项目名称', value: 'non_medicine_name' },
        { text: '执行科室', value: 'non_medicine_type' },
        { text: '执行状态', value: 'medical_skill_execute_state' },
        { text: '单价', value: 'non_medicine_unit_price' },
        { text: '检查结果', value: 'operation', sortable: false }
      ],
      headers_tem: [
        {
          text: '模板名称',
          align: 'left',
          value: 'non_medicine_id'
        },
        { text: '检查结果', value: 'operation', sortable: false }
      ]
    }
  }
}
</script>

<style scoped>

</style>
