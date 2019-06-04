<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card >
    <v-dialog
      v-model="show"
    >
    <v-card>
      <v-toolbar flat>
        <v-toolbar-title>新增排班规则</v-toolbar-title>
        <v-spacer/>
        <v-flex md2>
          <v-select
            v-model="department_rule"
            :items="items_departments"
            item-text="department_name"
            item-value="department_name"
            label="科室"
          ></v-select>
        </v-flex>
        <v-flex md2>
          <v-select
            style="margin-left:10px;margin-right:10px"
            v-model="register_level_rule"
            :items="items_register_level"
            item-text="register_level_level"
            item-value="register_level_level"
            label="挂号等级"
          ></v-select>
          </v-flex>
        <v-btn
          icon
          flat
          color="primary"
          @click="show = !show , department_level_doctor">
          <v-icon>
            refresh
          </v-icon>
        </v-btn>
      </v-toolbar>
        <v-data-table
          v-model="selected_rule"
          :headers="headers_rule"
          :items="desserts_rule"
          item-key="name"
          select-all
          class="scroll-y"
          hide-actions
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
            <!--<td>{{ props.item.MonM }}</td>-->
            <!--<td>{{ props.item.MonA }}</td>-->
            <!--<td>{{ props.item.TueM }}</td>-->
            <!--<td>{{ props.item.TueA }}</td>-->
            <!--<td>{{ props.item.WedM }}</td>-->
            <!--<td>{{ props.item.WedA }}</td>-->
            <!--<td>{{ props.item.ThuM }}</td>-->
            <!--<td>{{ props.item.ThuA }}</td>-->
            <!--<td>{{ props.item.FriM }}</td>-->
            <!--<td>{{ props.item.FriA }}</td>-->
            <!--<td>{{ props.item.SatM }}</td>-->
            <!--<td>{{ props.item.SatA }}</td>-->
            <!--<td>{{ props.item.SunM }}</td>-->
            <!--<td>{{ props.item.SunA }}</td>-->

            <td v-for="n in 14" v-bind:key = 'n'>
              <v-checkbox
                v-model="props.item.time[n-1]"
                primary
                hide-details
              ></v-checkbox>
            </td>
          </template>
        </v-data-table>
      <v-divider/>
        <v-card-actions>
          <v-spacer/>
          <v-flex xs2>
            <v-text-field
              v-model="rule_name"
              label="规则名称"
              required
            ></v-text-field>
          </v-flex>
          <v-btn small color="primary" style="margin-left:20px;margin-right:10px" >
            保存
          </v-btn>
        </v-card-actions>
    </v-card>
    </v-dialog>
    <v-toolbar flat>
      <v-toolbar-title>排班规则列表</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-flex xs2>
        <v-select
          :items="items_departments"
          v-model="department_scheduling"
          item-text="department_name"
          item-value="department_name"
          label="科室"
          style="margin-right: 20px"
        ></v-select>
      </v-flex>
      <v-btn
        icon
        flat
        color="primary"
        @click="show = !show"
      >
        <v-icon>
          add
        </v-icon>
      </v-btn>
      <v-btn
        icon
        flat
        color="primary"
        @click="expand = !expand"
      >
        <v-icon>
          delete
        </v-icon>
      </v-btn>
    </v-toolbar>

    <v-card class="elevation-1">
      <v-data-table
        v-model="selected_scheduling"
        :headers="headers_scheduling"
        :items="desserts_scheduling"
        item-key="departmentName"
        :search="search_scheduling"
        select-all
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
    <v-divider></v-divider>
    <v-card-actions>
      <v-spacer/>
        <el-date-picker
          v-model="date"
          type="datetimerange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          style="margin-right: 10px"
        >
        </el-date-picker>
        <v-btn color="primary">保存</v-btn>
    </v-card-actions>
    </v-card>
  </v-card>
</template>

<script>
export default {
  name: 'workforce',
  data: () => ({
    date: ['', ''],
    show: false,
    expand: false,
    department_rule: '',
    register_level_rule: '',
    department_scheduling: '',
    search_scheduling: '',
    selected_rule: [],
    selected_scheduling: [],
    items_departments: [],
    items_register_level: [],
    headers_rule: [
      {
        text: '医生名称',
        align: 'left',
        value: 'doctorName'
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
        departmentName: '艾滋病科',
        doctorName: '扁鹊',
        time: 11111111111111
      },
      {
        ruleName: 'q1',
        departmentName: '病理科',
        doctorName: '李雨泽',
        time: 11111111111110
      }
    ]
  }),
  mounted: function () {
    this.load()
  },
  methods: {
    load: function () {
      let that = this
      var url = this.HOME + '/department/get-all'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.items_departments = response.data
        })
    },
    department_level_doctor: function () {
      // 返回这个department下的所有doctor
      // 并将doctor的每个属性对应到十四time slot上

    }
  }
}
</script>

<style scoped>

</style>
