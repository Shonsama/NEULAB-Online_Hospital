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
                v-model="non_medicine_id"
                :error-messages="errorMessages"
                label="非药品编号"
                placeholder="请输入非药品编号"
                :disabled="!mode"
                required
              ></v-text-field>
              <v-text-field
                ref="address"
                v-model="non_medicine_name"
                label="非药品名称"
                placeholder="请输入非药品名称"
                required
              ></v-text-field>
              <v-text-field
                ref="city"
                v-model="non_medicine_type"
                label="非药品类型"
                placeholder="请输入非药品类型"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="non_medicine_specification"
                label="非药品规格"
                placeholder="请输入非药品规格"
                required
              ></v-text-field>
              <v-text-field
                ref="city"
                v-model="non_medicine_unit_price"
                label="单价"
                placeholder="请输入单价"
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
      :value="alert_success"
      type="success"
      transition="slide-y-transition"
    >
      This is a success alert.
    </v-alert>

    <v-alert
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
    non_medicine_id: '',
    non_medicine_name: '',
    non_medicine_type: '',
    non_medicine_specification: '',
    non_medicine_unit_price: '',
    show: false,
    search: '',
    expand: false,
    selected: [],
    signal: '',
    headers: [
      {
        text: '非药品编号',
        align: 'left',
        value: 'non_medicine_id'
      },
      { text: '非药品名称', value: 'non_medicine_name' },
      { text: '非药品类型', value: 'non_medicine_type' },
      { text: '非药品规格', value: 'non_medicine_specification' },
      { text: '单价', value: 'non_medicine_unit_price' },
      { text: '操作', value: 'operation', sortable: false }
    ],
    desserts: []
  }),
  methods: {
    load: function () {
      let that = this
      var url = this.HOME + '/maintenance/non-medicine/get-all'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), {
      })
        .then(function (response) {
          console.log(response.data)
          that.desserts = response.data.data
        })
    },
    deleteItem: function (item) {
      let that = this
      var url = this.HOME + '/maintenance/non-medicine/delete'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), {non_medicine_id: item.non_medicine_id})
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
        non_medicine_id: this.non_medicine_id,
        non_medicine_name: this.non_medicine_name,
        non_medicine_type: this.non_medicine_type,
        non_medicine_specification: this.non_medicine_specification,
        non_medicine_unit_price: this.non_medicine_unit_price
      }
      let that = this
      var url = this.HOME + '/maintenance/non-medicine/add'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
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
        non_medicine_id: this.non_medicine_id,
        non_medicine_name: this.non_medicine_name,
        non_medicine_type: this.non_medicine_type,
        non_medicine_specification: this.non_medicine_specification,
        non_medicine_unit_price: this.non_medicine_unit_price
      }
      let that = this
      var url = this.HOME + '/maintenance/non-medicine/update'
      this.$http.post(url + '?token=' + sessionStorage.getItem('token'), data)
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
      this.non_medicine_id = item.non_medicine_id
      this.non_medicine_name = item.non_medicine_name
      this.non_medicine_type = item.non_medicine_type
      this.non_medicine_specification = item.non_medicine_specification
      this.non_medicine_unit_price = item.non_medicine_unit_price
    },
    eraseForm: function () {
      this.non_medicine_id = ''
      this.non_medicine_name = ''
      this.non_medicine_type = ''
      this.non_medicine_specification = ''
      this.non_medicine_unit_price = ''
    },
    notice_success: function () {
      this.change_success()
      window.setTimeout(this.change_success, 1500)
    },
    change_success: function () {
      this.alert_success = !this.alert_success
    },
    notice_error: function () {
      this.change_error()
      window.setTimeout(this.change_error, 1500)
    },
    change_error: function () {
      this.alert_error = !this.alert_error
    },
    delete_selected: function () {
      var count = 0
      for (let i = 0; i < this.selected.length; i++) {
        var item = {
          non_medicine_id: this.selected[i].non_medicine_id
        }
        let that = this
        var url = this.HOME + '/maintenance/non-medicine/delete'
        this.$http.post(url + '?token=' + sessionStorage.getItem('token'), {non_medicine_id: item.non_medicine_id})
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
