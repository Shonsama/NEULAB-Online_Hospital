<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card>
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
          @click="department_level_doctor">
          <v-icon>
            refresh
          </v-icon>
        </v-btn>
      </v-toolbar>
        <v-data-table
          v-model="selected_rule"
          :headers="headers_rule"
          :items="desserts_rule"
          item-key="doctor_id"
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
            <td>{{ props.item.doctor_id }}</td>
            <td>{{ props.item.doctor_name }}</td>
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
          <v-layout align-center justify-center row fill-height>
          <v-flex xs2>
            <v-text-field
              v-model="rule_name_create"
              label="规则名称"
              required
            ></v-text-field>
          </v-flex>
          <v-btn
            color="primary"
            style="margin-left:20px;margin-right:10px"
            @click="add_Rule"
          >
            保存
          </v-btn>
          </v-layout>
        </v-card-actions>
    </v-card>
    </v-dialog>

    <!--这个是编辑时候出现的对话框-->
    <v-dialog
      v-model="edit_show"
      width="300"
    >
      <v-layout justify-center>
        <v-flex xs12>
          <v-card ref="form">
            <v-card-text>
              <v-text-field
                ref="address"
                v-model="rule_id"
                :disabled="true"
                label="规则ID"
                placeholder="请输入规则ID"
                required
              ></v-text-field>
              <v-text-field
                ref="name"
                v-model="rule_name"
                :error-messages="errorMessages"
                label="规则名称"
                placeholder="请输入规则名称"
                required
              ></v-text-field>
              <v-text-field
                ref="address"
                v-model="department_name"
                :disabled="true"
                label="科室"
                placeholder="请输入科室"
                required
              ></v-text-field>
              <v-text-field
                ref="city"
                v-model="doctor_name"
                :disabled="true"
                label="医生姓名"
                placeholder="请输入医生姓名"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="rule_work_time"
                label="工作时间"
                placeholder="请输入工作时间"
                required
              ></v-text-field>
            </v-card-text>
            <!--<v-divider class="mt-5"></v-divider>-->
            <v-card-actions>
              <v-btn flat @click="edit_show =!edit_show">Cancel</v-btn>
              <v-spacer></v-spacer>
              <v-slide-x-reverse-transition>
                <v-tooltip
                  v-if="formHasErrors"
                  left
                >
                  <template v-slot:activator="{ on }">
                    <v-btn
                      icon
                      class="my-0"
                      @click="resetForm"
                      v-on="on"
                    >
                      <v-icon>refresh</v-icon>
                    </v-btn>
                  </template>
                  <span>Refresh form</span>
                </v-tooltip>
              </v-slide-x-reverse-transition>
              <div>
                <v-btn color="primary" flat @click="updateItem">update</v-btn>
              </div>
            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </v-dialog>

    <v-flex>
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
      <v-data-table
        v-model="selected_scheduling"
        :headers="headers_scheduling"
        :items="desserts_scheduling"
        item-key="department_name"
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
          <td>{{ props.item.rule_id }}</td>
          <td>{{ props.item.rule_name }}</td>
          <td>{{ props.item.department_name }}</td>
          <td>{{ props.item.doctor_name }}</td>
          <td>{{ props.item.rule_work_time }}</td>
          <td>
            <v-icon
              small
              class="mr-2"
              @click="edit_show = !edit_show , fillForm(props.item)"
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
        <v-btn color="primary" @click="add_schedule">保存</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: 'workforce',
  data: () => ({
    date: ['', ''],
    show: false,
    edit_show: false,
    expand: false,
    // 编辑的dialog使用的属性
    rule_id: '',
    rule_name: '',
    department_name: '',
    doctor_name: '',
    rule_work_time: '',
    // 规定的规则名称
    rule_name_create: '',
    // 选择窗口用到的两个选择
    department_rule: '',
    register_level_rule: '',
    // 上面搜索的部门
    department_scheduling: '',
    // 搜索
    search_scheduling: '',
    selected_rule: [],
    selected_scheduling: [],
    items_departments: [],
    items_register_level: [],
    desserts_rule: [],
    doctor_rule: [],
    desserts_scheduling: [],
    headers_rule: [
      {
        text: '医生ID',
        align: 'left',
        value: 'doctor_id'
      },
      {
        text: '医生名称',
        align: 'left',
        value: 'doctor_name'
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
    // desserts_rule: [
    //   {
    //     doctor_id: '1',
    //     doctor_name: '扁鹊',
    //     time: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
    //   }
    // ],
    headers_scheduling: [
      {
        text: '规则ID',
        align: 'left',
        value: 'rule_id'
      },
      {text: '规则名称', value: 'rule_name'},
      {text: '科室名称', value: 'department_name'},
      {text: '医生名称', value: 'doctor_name'},
      {text: '时间', value: 'rule_work_time'},
      {text: '操作', value: 'operation'}

    ],
    desserts_scheduling: [
      {
        rule_id: 1,
        rule_name: 'q1',
        department_name: '艾滋病科',
        doctor_name: '扁鹊',
        rule_work_time: 11111111111111
      },
      {
        rule_id: 2,
        rule_name: 'q1',
        department_name: '病理科',
        doctor_name: '李雨泽',
        rule_work_time: 11111111111110
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
      // let that = this
      // var url = this.HOME + '/rule/get-department_level_doctor'
      // var data = {
      //   department: that.department_rule,
      //   level: that.level
      // }
      // this.$http.post(url, data)
      //   .then(function (response) {
      //     console.log(response.data)
      //     that.doctor_rule = response.data
      //     for (let i = 0; i < that.doctor_rule.length; i++) {
      //       var tempRule = {
      //         doctor_id: that.doctor_rule[i].doctor_id,
      //         doctor_name: that.doctor_rule[i].doctor_name,
      //         time: ['0','0','0','0','0','0','0','0','0','0','0','0','0','0',]
      //       }
      //     }
      //     that.desserts_rule.push(tempRule)
      //   })
      var tempRule = {
                  doctor_id: 1,
                  doctor_name: 'lala',
                  time: [false, false, false, false, true, true, true, true, true, true, true, true, true, true]
                }
      this.desserts_rule.push(tempRule)
    },
    add_Rule: function () {
      //从selected_rule里面建立，三个属性
      let that = this
      var url = this.HOME + '/rule/add'
      console.log(that.selected_rule)
      var time_array = []
      for (let i = 0; i < that.selected_rule[0].time.length; i++) {
        if (that.selected_rule[0].time[i]){
          time_array[i] = 1
        } else {
          time_array[i] = 0
        }
      }
      var time_string = time_array.join('')
      var data = {
        rule_doctor_id: that.selected_rule[0].doctor_id,
        rule_work_time: time_string,
        rule_name: that.rule_name_create,
      }
      console.log('This is gonna be the rule')
      console.log(data)
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.doctor_rule = response.data
          if (that.signal.msg === 'SUCCESS') {
            that.load()
            that.show = !that.show
          }
        })
    },
    add_schedule: function () {
      var data = {
        schedule_start_date: this.date[0],
        schedule_end_date: this.date[1],
        schedule_rule_id: this.selected_scheduling[0].rule_id,
      }
      let that = this
      var url = this.HOME + '/schedule/add'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data
          if (that.signal.msg === 'SUCCESS') {
            that.load()
          }
        })
      console.log(this.signal)

    },
    updateItem: function () {
      var rule = {
        rule_id: this.department_id,
        rule_name: this.department_name,
        rule_work_time: this.department_type
      }
      let that = this
      var url = this.HOME + '/rule/update'
      this.$http.post(url, rule)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data
          if (that.signal.msg === 'SUCCESS') {
            that.load()
            that.edit_show = !that.edit_show
          }
        })
      console.log(this.signal)
    },
    deleteItem: function (item) {
      let that = this
      var url = this.HOME + '/rule/delete'
      this.$http.post(url, {rule_id: item.rule_id})
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data
          if (that.signal.msg === 'SUCCESS') {
            that.load()
          }
        })
      console.log(this.signal)
    },
    fillForm: function (item) {
      this.rule_id = item.rule_id
      this.rule_name = item.rule_name
      this.department_name = item.department_name
      this.doctor_name = item.doctor_name
      this.rule_work_time = item.rule_work_time
    },
    eraseForm: function () {
      this.rule_id = ''
      this.rule_name = ''
      this.department_name = ''
      this.doctor_name = ''
      this.rule_work_time = ''
    }
  },
  watch: {
    edit_show: function (newState, oldState) {
      if (newState === false) {
        this.eraseForm()
      }
    }
  }
}
</script>

<style scoped>

</style>
