<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <v-card>
      <v-layout>
        <v-flex xs9>
          <v-toolbar flat dense>
            <v-toolbar-title>处方</v-toolbar-title>
            <v-spacer/>
            <v-btn
              color="primary"
              small
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
            v-model="selected"
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
                  v-model="selected"
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
      search: '',
      show: false,
      expand: false,
      expand1: false,
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
      headers_con: [
        {
          text: '药品名称',
          align: 'left',
          value: 'non_medicine_id'
        },
        {
          text: '规格',
          value: 'non_medicine_name'
        },
        {
          text: '用法',
          value: 'non_medicine_type'
        },
        {
          text: '用量',
          value: 'medical_skill_execute_state'
        },
        {
          text: '单价',
          value: 'non_medicine_unit_price'
        },
        {
          text: '频次',
          value: 'medical_skill_execute_state'
        },
        {
          text: '数量',
          value: 'non_medicine_unit_price'
        },
        {
          text: '操作',
          value: 'non_medicine_unit_price'
        }
      ],
      desserts_con: [],
      headers_pre: [
        {
          text: '处方名称',
          align: 'left',
          value: 'name'
        },
        {
          text: '状态',
          value: 'state'
        },
        {
          text: '金额',
          value: 'fee'
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
          text: '药品名称',
          align: 'left',
          value: 'non_medicine_id'
        },
        {
          text: '规格',
          value: 'non_medicine_name'
        },
        {
          text: '用法',
          value: 'non_medicine_type'
        },
        {
          text: '用量',
          value: 'medical_skill_execute_state'
        },
        {
          text: '单价',
          value: 'non_medicine_unit_price'
        },
        {
          text: '频次',
          value: 'medical_skill_execute_state'
        },
        {
          text: '数量',
          value: 'non_medicine_unit_price'
        },
        {
          text: '操作',
          value: 'non_medicine_unit_price'
        }
      ],
      desserts_tem_con: []
    }
  }
}
</script>

<style scoped>

</style>
