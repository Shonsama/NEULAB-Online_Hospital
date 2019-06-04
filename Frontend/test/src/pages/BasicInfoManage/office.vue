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
                    v-model="department_id"
                    :error-messages="errorMessages"
                    label="科室编号"
                    placeholder="请输入科室编号"
                    :disabled="!mode"
                    required
                  ></v-text-field>
                  <v-text-field
                    ref="address"
                    v-model="department_name"
                    label="科室名称"
                    placeholder="请输入科室名称"
                    required
                  ></v-text-field>
                  <v-text-field
                    ref="city"
                    v-model="department_cat"
                    label="科室分类"
                    placeholder="请输入科室分类"
                    required
                  ></v-text-field>
                  <v-text-field
                    ref="country"
                    v-model="department_type"
                    label="科室类型"
                    placeholder="请输入科室类型"
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
          @click="expand = !expand"
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
        item-key="department_id"
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
          <td>{{ props.item.department_id }}</td>
          <td>{{ props.item.department_name }}</td>
          <td>{{ props.item.department_cat }}</td>
          <td>{{ props.item.department_type }}</td>
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
    mode: true,
    department_id: '',
    department_name: '',
    department_cat: '',
    department_type: '',
    show: false,
    search: '',
    expand: false,
    selected: [],
    signal: '',
    headers: [
      {
        text: '科室编号',
        align: 'left',
        value: 'department_id'
      },
      { text: '科室名称', value: 'department_name' },
      { text: '科室分类', value: 'department_cat' },
      { text: '科室类型', value: 'department_type' },
      { text: '操作', value: 'operation', sortable: false }
    ],
    desserts: []
  }),
  methods: {
    load: function () {
      let that = this
      var url = this.HOME + '/department/get-all'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.desserts = response.data
        })
    },
    deleteItem: function (item) {
      let that = this
      var url = this.HOME + '/department/delete'
      this.$http.post(url, {department_id: item.department_id})
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data
          if (that.signal.msg === 'SUCCESS') {
            that.load()
          }
        })
      console.log(this.signal)
    },
    addItem: function () {
      var department = {
        department_id: this.department_id,
        department_name: this.department_name,
        department_cat: this.department_cat,
        department_type: this.department_type
      }
      let that = this
      var url = this.HOME + '/department/add'
      this.$http.post(url, department)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data
          if (that.signal.msg === 'SUCCESS') {
            that.load()
            that.show = !that.show
          }
        })
      console.log(this.signal)
    },
    updateItem: function () {
      var department = {
        department_id: this.department_id,
        department_name: this.department_name,
        department_cat: this.department_cat,
        department_type: this.department_type
      }
      let that = this
      var url = this.HOME + '/department/update'
      this.$http.post(url, department)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data
          if (that.signal.msg === 'SUCCESS') {
            that.load()
            that.show = !that.show
            that.eraseForm()
          }
        })
      console.log(this.signal)
    },
    fillForm: function (item) {
      this.department_id = item.department_id
      this.department_name = item.department_name
      this.department_cat = item.department_cat
      this.department_type = item.department_type
    },
    eraseForm: function () {
      this.department_id = ''
      this.department_name = ''
      this.department_cat = ''
      this.department_type = ''
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
