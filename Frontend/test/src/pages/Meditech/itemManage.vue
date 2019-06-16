<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card>
    <v-dialog
      v-model="show"
      width="300"
    >
      <v-layout justify-center>
        <v-flex xs12>
          <v-card ref="form">
            <v-card-text>
              <v-text-field
                ref="name"
                v-model="medical_skill_content_id"
                :error-messages="errorMessages"
                label="医技项目编号"
                placeholder="请输入医技项目编号"
                :disabled="!mode"
                required
              ></v-text-field>
              <v-text-field
                ref="address"
                v-model="medical_skill_content_name"
                label="医技项目名称"
                placeholder="请输入医技项目名称"
                required
              ></v-text-field>
              <v-text-field
                ref="city"
                v-model="medical_skill_content_detail"
                label="医技项目内涵"
                placeholder="请输入医技项目内涵"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="medical_skill_content_father"
                label="医技项目父级编码"
                placeholder="请输入医技项目父级编码"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="medical_skill_content_extra"
                label="医技项目除外内容"
                placeholder="请输入医技项目除外内容"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="medical_skill_content_unit"
                label="医技项目计价单位"
                placeholder="请输入医技项目计价单位"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="medical_skill_content_price"
                label="医技项目单价"
                placeholder="请输入医技项目单价"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="medical_skill_content_explain"
                label="医技项目说明"
                placeholder="请输入医技项目说明"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="medical_skill_content_department_id"
                label="医技项目科室ID"
                placeholder="请输入医技项目科室ID"
                required
              ></v-text-field>
            </v-card-text>
            <!--<v-divider class="mt-5"></v-divider>-->
            <v-card-actions>
              <v-btn flat @click="show =!show">取消</v-btn>
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
              <div v-if="mode">
                <v-btn color="primary" flat @click="addItem">确定</v-btn>
              </div>
              <div v-else>
                <v-btn color="primary" flat @click="updateItem">确定</v-btn>
              </div>

            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
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

    <v-flex>
      <v-toolbar flat>
        <v-flex xs3>
          <v-text-field
            prepend-inner-icon="search"
            v-model="search"
            :rules="nameRules"
            label="编号/名称"
            required
          ></v-text-field>
        </v-flex>

        <v-spacer></v-spacer>
        <v-btn
          icon
          flat
          color="primary"
          @click="show = !show , mode = true"
        >
          <v-icon>
            add
          </v-icon>
        </v-btn>
        <v-btn
          icon
          flat
          color="primary"
          @click="delete_selected()"
        >
          <v-icon>
            delete
          </v-icon>
        </v-btn>
      </v-toolbar>
      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="desserts"
        :search="search"
        item-key="medical_skill_content_id"
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
          <td>{{ props.item.medical_skill_content_id }}</td>
          <td>{{ props.item.medical_skill_content_name }}</td>
          <td>{{ props.item.medical_skill_content_detail }}</td>
          <td>{{ props.item.medical_skill_content_father }}</td>
          <td>{{ props.item.medical_skill_content_extra }}</td>
          <td>{{ props.item.medical_skill_content_unit }}</td>
          <td>{{ props.item.medical_skill_content_price }}</td>
          <td>{{ props.item.medical_skill_content_explain }}</td>
          <td>{{ props.item.medical_skill_content_department_id }}</td>
          <td>
            <v-icon
              small
              class="mr-2"
              @click="show =!show , mode = false , fillForm(props.item)"
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
  </v-card>
</template>

<script>
export default {
  data: () => ({
    alert_success: false,
    alert_error: false,
    mode: true,
    medical_skill_content_id: '',
    medical_skill_content_name: '',
    medical_skill_content_detail: '',
    medical_skill_content_father: '',
    medical_skill_content_extra: '',
    medical_skill_content_unit: '',
    medical_skill_content_price: '',
    medical_skill_content_explain: '',
    medical_skill_content_department_id: '',
    show: false,
    search: '',
    expand: false,
    selected: [],
    signal: '',
    headers: [
      {
        text: '医技项目编号',
        align: 'left',
        value: 'medical_skill_content_id'
      },
      { text: '医技项目名称', value: 'medical_skill_content_name' },
      { text: '医技项目内涵', value: 'medical_skill_content_detail' },
      { text: '医技项目父级编码', value: 'medical_skill_content_father' },
      { text: '医技项目除外内容', value: 'medical_skill_content_extra' },
      { text: '医技项目计价单位', value: 'medical_skill_content_unit' },
      { text: '医技项目单价', value: 'medical_skill_content_price' },
      { text: '医技项目说明', value: 'medical_skill_content_explain' },
      { text: '医技项目科室ID', value: 'medical_skill_content_department_id' },
      { text: '操作', value: 'operation', sortable: false }
    ],
    desserts: []
  }),
  methods: {
    load: function () {
      let that = this
      var url = this.HOME + '/maintenance/medical-skill-content/get-all'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.desserts = response.data.data
        })
    },
    deleteItem: function (item) {
      let that = this
      var url = this.HOME + '/maintenance/medical-skill-content/delete'
      this.$http.post(url, {medical_skill_content_id: item.medical_skill_content_id})
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data.msg
          if (that.signal === 'SUCCESS') {
            that.load()
            that.notice_success()
          } else {
            that.notice_error()
          }
        })
      console.log(this.signal)
    },
    addItem: function () {
      var department = {
        medical_skill_content_id: this.medical_skill_content_id,
        medical_skill_content_name: this.medical_skill_content_name,
        medical_skill_content_detail: this.medical_skill_content_detail,
        medical_skill_content_father: this.medical_skill_content_father,
        medical_skill_content_extra: this.medical_skill_content_extra,
        medical_skill_content_unit: this.medical_skill_content_unit,
        medical_skill_content_price: this.medical_skill_content_price,
        medical_skill_content_explain: this.medical_skill_content_explain,
        medical_skill_content_department_id: this.medical_skill_content_department_id
      }
      let that = this
      var url = this.HOME + '/maintenance/medical-skill-content/add'
      this.$http.post(url, department)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data.msg
          if (that.signal === 'SUCCESS') {
            that.load()
            that.show = !that.show
            that.notice_success()
          } else {
            that.notice_error()
          }
        })
      console.log(this.signal)
    },
    updateItem: function () {
      var department = {
        medical_skill_content_id: this.medical_skill_content_id,
        medical_skill_content_name: this.medical_skill_content_name,
        medical_skill_content_detail: this.medical_skill_content_detail,
        medical_skill_content_father: this.medical_skill_content_father,
        medical_skill_content_extra: this.medical_skill_content_extra,
        medical_skill_content_unit: this.medical_skill_content_unit,
        medical_skill_content_price: this.medical_skill_content_price,
        medical_skill_content_explain: this.medical_skill_content_explain,
        medical_skill_content_department_id: this.medical_skill_content_department_id
      }
      let that = this
      var url = this.HOME + '/maintenance/medical-skill-content/update'
      this.$http.post(url, department)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data.msg
          if (that.signal === 'SUCCESS') {
            that.load()
            that.show = !that.show
            that.eraseForm()
            that.notice_success()
          } else {
            that.notice_error()
          }
        })
      console.log(this.signal)
    },
    fillForm: function (item) {
      this.medical_skill_content_id = item.medical_skill_content_id
      this.medical_skill_content_name = item.medical_skill_content_name
      this.medical_skill_content_detail = item.medical_skill_content_detail
      this.medical_skill_content_father = item.medical_skill_content_father
      this.medical_skill_content_extra = item.medical_skill_content_extra
      this.medical_skill_content_unit = item.medical_skill_content_unit
      this.medical_skill_content_price = item.medical_skill_content_price
      this.medical_skill_content_explain = item.medical_skill_content_explain
      this.medical_skill_content_department_id = item.medical_skill_content_department_id
    },
    eraseForm: function () {
      this.medical_skill_content_id = ''
      this.medical_skill_content_name = ''
      this.medical_skill_content_detail = ''
      this.medical_skill_content_father = ''
      this.medical_skill_content_extra = ''
      this.medical_skill_content_unit = ''
      this.medical_skill_content_price = ''
      this.medical_skill_content_explain = ''
      this.medical_skill_content_department_id = ''
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
      var length = this.selected.length
      for (let i = 0; i < this.selected.length; i++) {
        var item = {
          medical_skill_content_id: this.selected[i].medical_skill_content_id
        }
        let that = this
        var url = this.HOME + '/maintenance/medical-skill-content/delete'
        this.$http.post(url, {medical_skill_content_id: item.medical_skill_content_id})
          .then(function (response) {
            console.log(response.data)
            that.signal = response.data.msg
            if (that.signal === 'SUCCESS') {
              that.load()
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
  mounted: function () {
    this.load()
  },
  computed: {
  },
  watch: {
    show: function (newState, oldState) {
      if (newState === false) {
        this.eraseForm()
      }
    }
  }
}
</script>

<style scoped>

</style>
