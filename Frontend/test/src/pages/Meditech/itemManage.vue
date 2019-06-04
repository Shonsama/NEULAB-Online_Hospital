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
                v-model="non_medicine_type"
                label="非药品类型"
                placeholder="请输入非药品类型"
                required
              ></v-text-field>
              <v-text-field
                ref="city"
                v-model="non_medicine_name"
                label="非药品名称"
                placeholder="请输入非药品名称"
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
                ref="country"
                v-model="non_medicine_unit_price"
                label="非药品单价"
                placeholder="请输入非药品单价"
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
          falt
          @click="show = !show , mode = true"
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
          <td>{{ props.item.non_medicine_type }}</td>
          <td>{{ props.item.non_medicine_name }}</td>
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
      mode: true,
      non_medicine_id: '',
      non_medicine_type: '',
      non_medicine_name: '',
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
        { text: '非药品类型', value: 'non_medicine_type' },
        { text: '非药品名称', value: 'non_medicine_name' },
        { text: '非药品规格', value: 'non_medicine_specification' },
        { text: '非药品单价', value: 'non_medicine_unit_price' },
        { text: '操作', value: 'operation', sortable: false }
      ],
      desserts: []
    }),
    methods: {
      load: function () {
        let that = this
        var url = this.HOME + '/non_medicine/getall'
        this.$http.post(url, {
        })
          .then(function (response) {
            console.log(response.data)
            that.desserts = response.data
          })
      },
      deleteItem: function (item) {
        let that = this
        var url = this.HOME + '/non_medicine/delete'
        this.$http.post(url, {non_medicine_id: item.non_medicine_id})
          .then(function (response) {
            console.log(response.data)
            that.signal = response.data
            if (that.signal.result === 'success') {
              that.load()
            }
          })
        console.log(this.signal)
      },
      addItem: function () {
        var non_medicine = {
          non_medicine_id: this.non_medicine_id,
          non_medicine_type: this.non_medicine_type,
          non_medicine_name: this.non_medicine_name,
          non_medicine_specification: this.non_medicine_specification,
          non_medicine_unit_price: this.non_medicine_unit_price
        }
        let that = this
        var url = this.HOME + '/non_medicine/add'
        this.$http.post(url, non_medicine)
          .then(function (response) {
            console.log(response.data)
            that.signal = response.data
            if (that.signal.result === 'success') {
              that.load()
              that.show = !that.show
            }
          })
        console.log(this.signal)
      },
      updateItem: function () {
        var non_medicine = {
          non_medicine_id: this.non_medicine_id,
          non_medicine_type: this.non_medicine_type,
          non_medicine_name: this.non_medicine_name,
          non_medicine_specification: this.non_medicine_specification,
          non_medicine_unit_price: this.non_medicine_unit_price
        }
        let that = this
        var url = this.HOME + '/non_medicine/update'
        this.$http.post(url, non_medicine)
          .then(function (response) {
            console.log(response.data)
            that.signal = response.data
            if (that.signal.result === 'success') {
              that.load()
              that.show = !that.show
              that.eraseForm()
            }
          })
        console.log(this.signal)
      },
      fillForm: function (item) {
        this.non_medicine_id = item.non_medicine_id
        this.non_medicine_type = item.non_medicine_type
        this.non_medicine_name = item.non_medicine_name
        this.non_medicine_specification = item.non_medicine_specification
        this.non_medicine_unit_price = item.non_medicine_unit_price
      },
      eraseForm: function () {
        this.non_medicine_id = ''
        this.non_medicine_type = ''
        this.non_medicine_name = ''
        this.non_medicine_specification = ''
        this.non_medicine_unit_price = ''
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
