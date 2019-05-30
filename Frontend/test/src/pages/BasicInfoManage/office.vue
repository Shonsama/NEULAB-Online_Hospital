<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card>
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
          @click="expand = !expand"
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
          <td >{{ props.item.department_name }}</td>
          <td >{{ props.item.department_cat }}</td>
          <td >{{ props.item.department_type }}</td>
          <td >
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
    </v-flex>
  </v-card>
</template>

<script>
import Qs from 'qs'
export default {
  data: () => ({
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
      console.log('load data ~~~~~~~~~')
      var url = this.HOME + '/department/getall'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.desserts = response.data
        })
    },
    deleteItem: function (item) {
      let that = this
      let department = Qs.stringify({
        'department_id': item.department_id,
        'department_name': item.department_name,
        'department_cat': item.department_cat,
        'department_type': item.department_type
      })
      let data = Qs.stringify({
        'department': department
      })
      // var department = {
      //   "department_cat": item.department_cat,
      //   "department_id": item.department_id,
      //   "department_name": item.department_name,
      //   "department_type": item.department_type
      //
      // }
      var url = this.HOME + '/department/delete'
      this.$http.post(url, data)
        .then(function (response) {
          console.log(response.data)
          that.signal = response.data
        })
      console.log(this.signal)
    }

  },
  mounted: function () {
    this.load()
  },
  computed: {
  },
  watch: {
  }
}
</script>

<style scoped>

</style>
