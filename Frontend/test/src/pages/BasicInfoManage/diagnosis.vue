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
                v-model="disease_id"
                :error-messages="errorMessages"
                label="疾病编号"
                placeholder="请输入疾病编号"
                :disabled="!mode"
                required
              ></v-text-field>
              <v-text-field
                ref="address"
                v-model="disease_name"
                label="疾病名称"
                placeholder="请输入疾病名称"
                required
              ></v-text-field>
              <v-text-field
                ref="city"
                v-model="disease_icd"
                label="国际ICD编码"
                placeholder="请输入国际ICD编码"
                required
              ></v-text-field>
              <v-text-field
                ref="country"
                v-model="disease_type"
                label="疾病类型"
                placeholder="请输入疾病类型"
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
        item-key="disease_id"
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
          <td>{{ props.item.disease_id }}</td>
          <td>{{ props.item.disease_name }}</td>
          <td>{{ props.item.disease_icd }}</td>
          <td>{{ props.item.disease_type }}</td>
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
    disease_id: '',
    disease_name: '',
    disease_icd: '',
    disease_type: '',
    show: false,
    search: '',
    expand: false,
    selected: [],
    signal: '',
    headers: [
      {
        text: '疾病编号',
        align: 'left',
        value: 'disease_id'
      },
      { text: '疾病名称', value: 'disease_name' },
      { text: '国际ICD编码', value: 'disease_icd' },
      { text: '疾病类型', value: 'disease_type' },
      { text: '操作', value: 'operation', sortable: false }
    ],
    desserts: []
  }),
  methods: {
    load: function () {
      let that = this
      var url = this.HOME + '/maintenance/disease/get-all'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.desserts = response.data.data
        })
    },
    deleteItem: function (item) {
      let that = this
      var url = this.HOME + '/maintenance/disease/delete'
      this.$http.post(url, {disease_id: item.disease_id})
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
      var disease = {
        disease_id: this.disease_id,
        disease_name: this.disease_name,
        disease_icd: this.disease_icd,
        disease_type: this.disease_type
      }
      let that = this
      var url = this.HOME + '/maintenance/disease/add'
      this.$http.post(url, disease)
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
      var disease = {
        disease_id: this.disease_id,
        disease_name: this.disease_name,
        disease_icd: this.disease_icd,
        disease_type: this.disease_type
      }
      let that = this
      var url = this.HOME + '/maintenance/disease/update'
      this.$http.post(url, disease)
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
      this.disease_id = item.disease_id
      this.disease_name = item.disease_name
      this.disease_icd = item.disease_icd
      this.disease_type = item.disease_type
    },
    eraseForm: function () {
      this.disease_id = ''
      this.disease_name = ''
      this.disease_icd = ''
      this.disease_type = ''
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
          disease_id: this.selected[i].disease_id
        }
        let that = this
        var url = this.HOME + '/maintenance/disease/delete'
        this.$http.post(url, {disease_id: item.disease_id})
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
