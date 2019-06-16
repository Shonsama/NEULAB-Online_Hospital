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
          item-value="department_id"
          label="科室"
        ></v-select>
        </v-flex>
        <v-flex md2>
          <v-select
            style="margin-left:10px;margin-right:10px"
            v-model="register_level_rule"
            :items="items_register_level"
            item-text="register_level_name"
            item-value="register_level_id"
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

    <v-alert
      transition :duration="1"
      :value="alert_success"
      type="success"
      transition="slide-y-transition"
    >
      This is a success alert.
    </v-alert>

    <v-alert
      transition :duration="1"
      :value="alert_error"
      type="error"
      transition="slide-y-transition"
    >
      This is a error alert.
    </v-alert>

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
              <v-btn flat @click="edit_show =!edit_show">取消</v-btn>
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
                <v-btn color="primary" flat @click="updateItem">确定</v-btn>
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
          @click="delete_selected"
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
        item-key="rule_id"
        :search="department_scheduling"
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
          <td>{{ props.item.rule_id }}</td>
          <td>{{ props.item.rule_name }}</td>
          <td>{{ props.item.doctor_id }}</td>
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
      <v-btn color="primary" @click="show_calendar =! show_calendar">显示排班</v-btn>
      <v-spacer/>
        <el-date-picker
          v-model="date"
          type="datetimerange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          style="margin-right: 10px"
          value-format= "yyyy-MM-dd HH:mm:ss"
        >
        </el-date-picker>
        <v-btn color="primary" @click="add_schedule">保存</v-btn>
    </v-card-actions>
    <v-dialog
      v-model="show_calendar"
      width="800px"
    >
      <v-layout justify-center>
        <v-card style="width: 800px">
          <v-layout wrap>
            <v-flex
              xs12
              class="mb-3"
            >
              <v-sheet height="500">
                <v-calendar
                  ref="calendar"
                  v-model="start"
                  :end="end"
                  :type="type"
                  color="primary"
                >
                  <template v-slot:day="{ date }">
                    <template v-for="event in eventsMap[date]">
                      <v-menu
                        :key="event.title"
                        v-model="event.open"
                        full-width
                        offset-x
                      >
                        <template v-slot:activator="{ on }">
                          <div
                            v-if="!event.time"
                            v-ripple
                            class="my-event"
                            v-on="on"
                            v-html="event.title"
                          ></div>
                        </template>
                        <v-card
                          color="grey lighten-4"
                          min-width="350px"
                          flat
                        >
                          <v-toolbar
                            color="primary"
                            dark
                          >
                            <v-btn icon>
                              <v-icon>edit</v-icon>
                            </v-btn>
                            <v-toolbar-title v-html="event.title"></v-toolbar-title>
                            <v-spacer></v-spacer>
                            <v-btn icon>
                              <v-icon>favorite</v-icon>
                            </v-btn>
                            <v-btn icon>
                              <v-icon>more_vert</v-icon>
                            </v-btn>
                          </v-toolbar>
                          <v-card-title primary-title>
                            <span v-html="event.details"></span>
                          </v-card-title>
                          <v-card-actions>
                            <v-btn
                              flat
                              color="secondary"
                            >
                              取消
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-menu>
                    </template>
                  </template>
                </v-calendar>
              </v-sheet>
            </v-flex>
            <v-flex
              sm4
              xs12
              offset-xs1
              class="text-sm-left text-xs-center"
            >
              <v-btn @click="$refs.calendar.prev()">
                <v-icon
                  dark
                  left
                >
                  keyboard_arrow_left
                </v-icon>
                前一月
              </v-btn>
            </v-flex>
            <v-flex
              sm4
              xs12
              offset-xs2
              class="text-sm-right text-xs-center"
            >
              <v-btn @click="$refs.calendar.next()">
                后一月
                <v-icon
                  right
                  dark
                >
                  keyboard_arrow_right
                </v-icon>
              </v-btn>
            </v-flex>
          </v-layout>
        </v-card>
      </v-layout>
    </v-dialog>
  </v-card>
</template>

<script>
export default {
  name: 'workforce',
  data: () => ({
    show_calendar: false,
    type: 'month',
    allSchedule: [],
    start: '2019-06-13',
    end: '2019-06-20',
    alert_success: false,
    alert_error: false,
    signal: '',
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
    items_departments: [],
    items_register_level: [],
    // 添加rule里面的dessert
    desserts_rule: [],
    doctor_rule: [],
    selected_rule: [],
    // schedule
    search_scheduling: '',
    selected_scheduling: [],
    rule_schedule: [],
    desserts_scheduling: [],
    doctor: [],
    doctors: [],
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
    events: [
      {
        title: 'Vacation',
        details: 'Going to the beach!',
        date: '2019-06-16',
        open: false
      }
    ]
  }),
  mounted: function () {
    this.load()
    this.load_rule()
    this.load_doctor()
  },
  methods: {
    open (event) {
      alert(event.title)
    },
    load_rule: function () {
      let that = this
      that.desserts_scheduling = []
      var url = this.HOME + '/rule/get-all-names'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.desserts_scheduling = response.data.data
        })
    },
    load: function () {
      this.load_schedule()
      this.items_departments = []
      this.items_register_level = []
      let that = this
      var url = this.HOME + '/maintenance/department/get-all'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.items_departments = response.data.data
        })
      url = this.HOME + '/maintenance/register-level/get-all'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.items_register_level = response.data.data
        })
    },
    department_level_doctor: function () {
      this.desserts_rule = []
      this.doctor_rule = []
      let that = this
      var url = this.HOME + '/rule/get-department_level_doctor'
      var data = {
        department_id: that.department_rule,
        register_level_id: that.register_level_rule
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.doctor_rule = response.data.data
          console.log('This is the doctor length')
          console.log(that.doctor_rule.length)
          for (let i = 0; i < that.doctor_rule.length; i++) {
            var tempRule = {
              doctor_id: that.doctor_rule[i].doctor_id,
              doctor_name: that.doctor_rule[i].doctor_name,
              time: [false, false, false, false, false, false, false, false, false, false, false, false, false, false]
            }
            that.desserts_rule.push(tempRule)
          }
        })
    },
    add_Rule: function () {
      // 从selected_rule里面建立，三个属性
      let that = this
      var url = this.HOME + '/rule/add'
      console.log(that.selected_rule)
      var time_array = []
      for (let i = 0; i < that.selected_rule[0].time.length; i++) {
        if (that.selected_rule[0].time[i]) {
          time_array[i] = 1
        } else {
          time_array[i] = 0
        }
      }
      var time_string = time_array.join('')
      var data = {
        rule_doctor_id: that.selected_rule[0].doctor_id,
        rule_work_time: time_string,
        rule_name: that.rule_name_create
      }
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data.msg
          if (that.signal === 'SUCCESS') {
            that.load_rule()
            that.show = !that.show
            that.notice_success()
          } else {
            that.notice_error()
          }
        })
    },
    add_schedule: function () {
      var data = {
        schedule_start_date: this.date[0],
        schedule_end_date: this.date[1],
        schedule_work_time: this.selected_scheduling[0].rule_work_time,
        schedule_doctor_name: this.selected_scheduling[0].doctor_name,
        schedule_doctor_id: this.selected_scheduling[0].doctor_id,
      }
      let that = this
      var url = this.HOME + '/schedule/add'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data.msg
          if (that.signal === 'SUCCESS') {
            that.load_schedule()
            that.load_rule()
            that.notice_success()
          } else {
            that.notice_error()
          }
        })
      console.log(this.signal)

    },
    load_schedule: function (){
      this.events = []
      let that = this
      var url = this.HOME + '/schedule/get-all'
      this.$http.post(url, {})
        .then(function (response) {
          that.allSchedule = response.data.data
          console.log(response.data)
          that.signal = response.data.msg
          if (that.signal === 'SUCCESS') {
            console.log('!!!!!!!!!!!!!!!!!!')
            console.log(that.allSchedule.length)
            for (let i = 0; i < that.allSchedule.length; i++) {
              var start_time = that.allSchedule[i].schedule_start_date.substring(0,10)
              var end_time = that.allSchedule[i].schedule_end_date.substring(0,10)
              start_time = start_time.replace(/-/g, '/')
              end_time = end_time.replace(/-/g, '/')
              console.log('?????????????????')
              that.getDiffDate(start_time, end_time, that.allSchedule[i].schedule_doctor_name, that.allSchedule[i].schedule_work_time)
            }
          } else {
            that.notice_error()
          }
        })
    },
    getDiffDate: function (start, end, doctor_name, work_time) {
      var startTime = new Date(start)

      var endTime = new Date(end)

      var dateArr = []

      var week_duty = work_time.split('')

      console.log(week_duty)
      while ((endTime.getTime() - startTime.getTime()) > 0) {

        var year = startTime.getFullYear();

        var month = startTime.getMonth().toString().length === 1 ? "0" + (parseInt(startTime.getMonth().toString(),10) + 1) : (startTime.getMonth() + 1);

        var day = startTime.getDate().toString().length === 1 ? "0" + startTime.getDate() : startTime.getDate();

        var date_temp = year + "-" + month + "-" + day

        dateArr.push(date_temp);

        var week_day = startTime.getDay()
        if (week_duty[week_day*2-2] === "1" && week_duty[week_day*2-1] === "1"){
          this.events.push(
            {
              title: doctor_name,
              details: '上午和下午排班',
              date: date_temp,
              open: false
            }
          )
        } else if (week_duty[week_day*2-2] === "1"){
          this.events.push(
            {
              title: doctor_name,
              details: '上午排班',
              date: date_temp,
              open: false
            }
          )
        }else if (week_duty[week_day*2-1] === "1"){
          this.events.push(
            {
              title: doctor_name,
              details: '下午排班',
              date: date_temp,
              open: false
            }
          )
        } else if (week_duty[12] === "1" && week_day === 0 && week_duty[13] === "1"){
          this.events.push(
            {
              title: doctor_name,
              details: '上午和下午排班',
              date: date_temp,
              open: false
            }
          )
        } else if (week_duty[12] === "1" && week_day === 0){
          this.events.push(
            {
              title: doctor_name,
              details: '上午排班',
              date: date_temp,
              open: false
            }
          )
        } else if(week_duty[13] === "1" && week_day === 0){
          this.events.push(
            {
              title: doctor_name,
              details: '下午排班',
              date: date_temp,
              open: false
            }
          )
        }
        startTime.setDate(startTime.getDate() + 1);
      }
    },
    updateItem: function () {
      var rule = {
        rule_id: this.rule_id,
        rule_name: this.rule_name,
        rule_work_time: this.rule_work_time
      }
      let that = this
      var url = this.HOME + '/rule/update'
      this.$http.post(url, rule)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data.msg
          if (that.signal === 'SUCCESS') {
            that.load_rule()
            that.edit_show = !that.edit_show
            that.notice_success()
          } else {
            that.notice_error()
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
          that.signal = response.data.msg
          if (that.signal === 'SUCCESS') {
            that.load_rule()
            that.notice_success()
          } else {
            that.notice_error()
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
    },
    eraseForm_add: function () {
      this.desserts_rule = []
      this.department_rule = ''
      this.register_level_rule = ''
      this.rule_name_create = ''
    },
    notice_success: function () {
      this.change_success()
      var timeout_1 = window.setTimeout(this.change_success, 1500)
    },
    change_success: function () {
      this.alert_success = !this.alert_success
    },
    notice_error: function () {
      this.change_error()
      var timeout_2 = window.setTimeout(this.change_error, 1500)
    },
    change_error: function () {
      this.alert_error = !this.alert_error
    },
    delete_selected: function () {
      var count = 0
      var length = this.selected_scheduling.length
      for (let i = 0; i < this.selected_scheduling.length; i++) {
        var item = {
          rule_id: this.selected_scheduling[i].rule_id
        }
        let that = this
        var url = this.HOME + '/rule/delete'
        this.$http.post(url, {rule_id: item.rule_id})
          .then(function (response) {
            console.log(response.data)
            that.signal = response.data.msg
            if (that.signal === 'SUCCESS') {
              that.load_rule()
              count = count + 1
            }
          })
      }
      if (this.count === this.length) {
        this.notice_success()
      } else {
        this.notice_error()
      }
    }
  },
  watch: {
    edit_show: function (newState, oldState) {
      if (newState === false) {
        this.eraseForm()
      }
    },
    show: function (newState, oldState) {
      if (newState === false) {
        this.eraseForm_add()
      }
    }
  },
  computed: {
    // convert the list of events into a map of lists keyed by date
    eventsMap () {
      const map = {}
      this.events.forEach(e => (map[e.date] = map[e.date] || []).push(e))
      return map
    }
  }
}
</script>

<style scoped>
  .my-event {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    border-radius: 2px;
    background-color: #1867c0;
    color: #ffffff;
    border: 1px solid #1867c0;
    width: 100%;
    font-size: 12px;
    padding: 3px;
    cursor: pointer;
    margin-bottom: 1px;
  }
</style>
