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
                v-model="medicine_id"
                :error-messages="errorMessages"
                label="药品编号"
                placeholder="请输入药品编号"
                :disabled="!mode"
                required
              ></v-text-field>
              <v-text-field
                ref="address"
                v-model="medicine_name"
                label="药品名称"
                placeholder="请输入药品名称"
                required
              ></v-text-field>
              <v-text-field
                ref="city"
                v-model="medicine_usage"
                label="药品用法"
                placeholder="请输入药品用法"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="medicine_specifications"
                label="药品规格"
                placeholder="请输入药品规格"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="medicine_unit"
                label="药品单位"
                placeholder="请输入药品单位"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="medicine_factory"
                label="药品厂家"
                placeholder="请输入药品厂家"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="medicine_formulation"
                label="药品剂型"
                placeholder="请输入药品剂型"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="medicine_type"
                label="药品类型"
                placeholder="请输入药品类型"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="medicine_unit_price"
                label="药品单价"
                placeholder="请输入药品单价"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="medicine_pinyin"
                label="药品拼音码"
                placeholder="请输入药品拼音码"
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
        item-key="medicine_id"
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
          <td>{{ props.item.medicine_id }}</td>
          <td>{{ props.item.medicine_name }}</td>
          <td>{{ props.item.medicine_usage }}</td>
          <td>{{ props.item.medicine_specifications }}</td>
          <td>{{ props.item.medicine_unit }}</td>
          <td>{{ props.item.medicine_factory }}</td>
          <td>{{ props.item.medicine_formulation }}</td>
          <td>{{ props.item.medicine_type }}</td>
          <td>{{ props.item.medicine_unit_price }}</td>
          <td>{{ props.item.medicine_pinyin}}</td>
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
    medicine_id: '',
    medicine_name: '',
    medicine_usage: '',
    medicine_specifications: '',
    medicine_unit: '',
    medicine_factory: '',
    medicine_formulation: '',
    medicine_type: '',
    medicine_unit_price: '',
    medicine_pinyin: '',
    show: false,
    search: '',
    expand: false,
    selected: [],
    signal: '',
    headers: [
      {
        text: '药品编号',
        align: 'left',
        value: 'medicine_id'
      },
      { text: '药品名称', value: 'medicine_name' },
      { text: '药品用法', value: 'medicine_usage' },
      { text: '药品规格', value: 'medicine_specifications' },
      { text: '药品单位', value: 'medicine_unit' },
      { text: '药品厂家', value: 'medicine_factory' },
      { text: '药品剂型', value: 'medicine_formulation' },
      { text: '药品类型', value: 'medicine_type' },
      { text: '药品单价', value: 'medicine_unit_price' },
      { text: '药品拼音码', value: 'medicine_pinyin' },
      { text: '操作', value: 'operation', sortable: false }
    ],
    desserts: []
  }),
  methods: {
    load: function () {
      let that = this
      var url = this.HOME + '/maintenance/medicine/get-all'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.desserts = response.data.data
        })
    },
    deleteItem: function (item) {
      let that = this
      var url = this.HOME + '/maintenance/medicine/delete'
      this.$http.post(url, {medicine_id: item.medicine_id})
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
      var medicine = {
        medicine_id: this.medicine_id,
        medicine_name: this.medicine_name,
        medicine_usage: this.medicine_usage,
        medicine_specifications: this.medicine_specifications,
        medicine_unit: this.medicine_unit,
        medicine_factory: this.medicine_factory,
        medicine_formulation: this.medicine_formulation,
        medicine_type: this.medicine_type,
        medicine_unit_price: this.medicine_unit_price,
        medicine_pinyin: this.medicine_pinyin
      }
      let that = this
      var url = this.HOME + '/maintenance/medicine/add'
      this.$http.post(url, medicine)
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
      var medicine = {
        medicine_id: this.medicine_id,
        medicine_name: this.medicine_name,
        medicine_usage: this.medicine_usage,
        medicine_specifications: this.medicine_specifications,
        medicine_unit: this.medicine_unit,
        medicine_factory: this.medicine_factory,
        medicine_formulation: this.medicine_formulation,
        medicine_type: this.medicine_type,
        medicine_unit_price: this.medicine_unit_price,
        medicine_pinyin: this.medicine_pinyin
      }
      let that = this
      var url = this.HOME + '/maintenance/medicine/update'
      this.$http.post(url, medicine)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data.msg
          if (that.signal === 'SUCCESS') {
            that.load()
            that.show = !that.show
            that.notice_success()
            that.eraseForm()
          } else {
            that.notice_error()
          }
        })
      console.log(this.signal)
    },
    fillForm: function (item) {
      this.medicine_id = item.medicine_id
      this.medicine_name = item.medicine_name
      this.medicine_usage = item.medicine_usage
      this.medicine_specifications = item.medicine_specifications
      this.medicine_unit = item.medicine_unit
      this.medicine_factory = item.medicine_factory
      this.medicine_formulation = item.medicine_formulation
      this.medicine_type = item.medicine_type
      this.medicine_unit_price = item.medicine_unit_price
      this.medicine_pinyin = item.medicine_pinyin
    },
    eraseForm: function () {
      this.medicine_id = ''
      this.medicine_name = ''
      this.medicine_usage = ''
      this.medicine_specifications = ''
      this.medicine_unit = ''
      this.medicine_factory = ''
      this.medicine_formulation = ''
      this.medicine_type = ''
      this.medicine_unit_price = ''
      this.medicine_pinyin = ''
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
          medicine_id: this.selected[i].medicine_id
        }
        let that = this
        var url = this.HOME + '/maintenance/medicine/delete'
        this.$http.post(url, {medicine_id: item.medicine_id})
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
