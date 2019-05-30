<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card>
    <v-dialog
      v-model="show"
    >
    <v-card>
      <v-toolbar flat>
        <v-toolbar-title>新增排班规则</v-toolbar-title>
        <v-spacer></v-spacer>
          <v-select
            style="width:20px;"
            :items="items_departments"
            label="科室"
          ></v-select>
          <v-select
            style="width:20px; margin-left: 10px"
            :items="items"
            label="挂号等级"
          ></v-select>

        <v-text-field name="rule_name" label="规则名称" type="text" style="width: 30px;margin-left:10px"></v-text-field>
        <v-btn   @click="show = !show">清空
        </v-btn>
        <v-btn   @click="test">新增排班规则
        </v-btn>
      </v-toolbar>
        <v-data-table
          v-model="selected_rule"
          :headers="headers_rule"
          :items="desserts_rule"
          item-key="name"
          select-all
          class="elevation-1 scroll-y"
        >
          <template v-slot:items="props">
            <td>
              <v-checkbox
                v-model="props.selected"
                primary
                hide-details
              ></v-checkbox>
            </td>
            <td>{{ props.item.doctorName }}</td>
            <td v-for="n in 14" v-bind:key = 'n'>
              <v-checkbox
                v-model="props.item.time[n-1]"
                primary
                hide-details
              ></v-checkbox>
            </td>
          </template>
        </v-data-table>
    </v-card>
    </v-dialog>
    <v-flex
    >
      <v-toolbar flat>
        <v-toolbar-title>排班规则列表</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-flex xs2>
        <v-select
          :items="items_departments"
          v-model="search"
          label="科室"
        ></v-select>
        </v-flex>
        <v-btn
          icon
          falt
          @click="show = !show"
        >
          <v-icon>
            add
          </v-icon>
        </v-btn>
        <v-btn
          icon
          falt
          @click="expand = !expand"
        >
          <v-icon>
            delete
          </v-icon>
        </v-btn>
      </v-toolbar>
      <v-data-table
        v-model="selected_scheduling"
        :headers="headers_scheduling"
        :items="desserts_scheduling"
        item-key="name"
        :search="search"
        select-all
        class="elevation-1"
      >
        <template v-slot:items="props">
          <td>
            <v-checkbox
              v-model="props.selected_scheduling"
              primary
              hide-details
            ></v-checkbox>
          </td>
          <td>{{ props.item.ruleName }}</td>
          <td>{{ props.item.departmentName }}</td>
          <td>{{ props.item.doctorName }}</td>
          <td>{{ props.item.time }}</td>
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
    <v-divider></v-divider>
  </v-card>
</template>

<script>
export default {
  name: 'workforce',
  data: () => ({
    show: false,
    expand: false,
    search: '',
    selected_rule: [],
    selected_scheduling: [],
    items_departments: ['心血管', '口腔'],
    headers_rule: [
      {
        text: '医生名称',
        align: 'left'
      },
      { text: '星期一上午' },
      { text: '星期一下午' },
      { text: '星期二上午' },
      { text: '星期二下午' },
      { text: '星期三上午' },
      { text: '星期三下午' },
      { text: '星期四上午' },
      { text: '星期四下午' },
      { text: '星期五上午' },
      { text: '星期五下午' },
      { text: '星期六上午' },
      { text: '星期六下午' },
      { text: '星期日上午' },
      { text: '星期日下午' }
    ],
    desserts_rule: [
      {
        doctorName: '扁鹊',
        time: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
      }
    ],
    headers_scheduling: [
      {
        text: '规则名称',
        align: 'left',
        value: 'ruleName'
      },
      {text: '科室名称', value: 'departmentName'},
      {text: '医生名称', value: 'doctorName'},
      {text: '时间', value: 'time'},
      {text: '操作', value: 'operation'}

    ],
    desserts_scheduling: [
      {
        ruleName: 'q1',
        departmentName: '心血管内科',
        doctorName: '扁鹊',
        time: 11111111111111
      }
    ]
  }),
  mounted: {
    //  向后端query所有department
  },
  methods: {
    test: function () {
      var that = this
      console.log(that.desserts_rule[0].time)
    }
  }
}
</script>

<style scoped>

</style>
