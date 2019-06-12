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
                v-model="register_level_id"
                :error-messages="errorMessages"
                label="挂号级别ID"
                placeholder="请输入挂号级别ID"
                :disabled="true"
                required
              ></v-text-field>
              <v-text-field
                ref="address"
                v-model="register_level_seq_num"
                label="顺序号"
                placeholder="请输入顺序号"
                required
              ></v-text-field>
              <v-text-field
                ref="city"
                v-model="register_level_name"
                label="挂号级别"
                placeholder="请输入级别"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="register_level_max"
                label="挂号限额"
                placeholder="请输入挂号限额"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="register_level_fee"
                label="挂号费用"
                placeholder="请输入挂号费用"
                required
              ></v-text-field>
            </v-card-text>
            <!--<v-divider class="mt-5"></v-divider>-->
            <v-card-actions>
              <v-btn flat @click="show =!show">Cancel</v-btn>
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
                <v-btn color="primary" flat @click="addItem">add</v-btn>
              </div>
              <div v-else>
                <v-btn color="primary" flat @click="updateItem">update</v-btn>
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
          @click="delete_selected"
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
        item-key="register_level_id"
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
          <td>{{ props.item.register_level_id }}</td>
          <td>{{ props.item.register_level_seq_num }}</td>
          <td>{{ props.item.register_level_name }}</td>
          <td>{{ props.item.register_level_max }}</td>
          <td>{{ props.item.register_level_fee }}</td>
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
    register_level_id: '',
    register_level_seq_num: '',
    register_level_name: '',
    register_level_max: '',
    register_level_fee: '',
    show: false,
    search: '',
    expand: false,
    selected: [],
    signal: '',
    headers: [
      {
        text: '挂号级别ID',
        align: 'left',
        value: 'register_level_id'
      },
      { text: '顺序号', value: 'register_level_seq_num' },
      { text: '挂号级别', value: 'register_level_name' },
      { text: '挂号限额', value: 'register_level_max' },
      { text: '挂号费用', value: 'register_level_fee' },
      { text: '操作', value: 'operation', sortable: false }
    ],
    desserts: []
  }),
  methods: {
    load: function () {
      let that = this
      var url = this.HOME + '/maintenance/register-level/get-all'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.desserts = response.data.data
        })
    },
    deleteItem: function (item) {
      let that = this
      var url = this.HOME + '/maintenance/register-level/delete'
      this.$http.post(url, {register_level_id: item.register_level_id})
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
      var data = {
        register_level_seq_num: this.register_level_seq_num,
        register_level_name: this.register_level_name,
        register_level_max: this.register_level_max,
        register_level_fee: this.register_level_fee
      }
      let that = this
      var url = this.HOME + '/maintenance/register-level/add'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data.msg
          if (that.signal === 'SUCCESS') {
            that.show = !that.show
            that.load()
            that.notice_success()
          } else {
            that.notice_error()
          }
        })
      console.log(this.signal)
    },
    updateItem: function () {
      var data = {
        register_level_id: this.register_level_id,
        register_level_seq_num: this.register_level_seq_num,
        register_level_name: this.register_level_name,
        register_level_max: this.register_level_max,
        register_level_fee: this.register_level_fee
      }
      let that = this
      var url = this.HOME + '/maintenance/register-level/update'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data.msg
          if (that.signal === 'SUCCESS') {
            that.show = !that.show
            that.load()
            that.notice_success()
          } else {
            that.notice_error()
          }
        })
      console.log(this.signal)
    },
    fillForm: function (item) {
      this.register_level_id = item.register_level_id
      this.register_level_seq_num = item.register_level_seq_num
      this.register_level_name = item.register_level_name
      this.register_level_max = item.register_level_max
      this.register_level_fee = item.register_level_fee
    },
    eraseForm: function () {
      this.register_level_id = ''
      this.register_level_seq_num = ''
      this.register_level_name = ''
      this.register_level_max = ''
      this.register_level_fee = ''
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
          register_level_id: this.selected[i].register_level_id
        }
        let that = this
        var url = this.HOME + '/maintenance/register-level/delete'
        this.$http.post(url, {register_level_id: item.register_level_id})
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
