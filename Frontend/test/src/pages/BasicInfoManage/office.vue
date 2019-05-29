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
          <v-btn   @click="expand = !expand">
            新增
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
            <td class="justify-center layout px-0">
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

    <v-divider></v-divider>

    <v-card-actions>
      <v-btn
        flat
        @click="tree = []"
      >
        Reset
      </v-btn>

      <v-spacer></v-spacer>

      <v-btn
        class="white--text"
        color="green darken-1"
        depressed
      >
        Save
        <v-icon right>mdi-content-save</v-icon>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: 'office',
  data: () => ({
    search: '',
    expand: false,
    selected: [],
    headers: [
      {
        text: '科室编号',
        align: 'left',
        value: 'id'
      },
      { text: '科室名称', value: 'name' },
      { text: '科室分类', value: 'classification' },
      { text: '科室类型', value: 'type' },
      { text: '操作', value: 'operation', sortable: false }
    ],
    //  desserts: []
    desserts: [
      {
        department_id: 'XXGNK',
        department_name: '心血管内科',
        department_cat: '内科',
        department_type: '临床'
      },
      {
        department_id: 'GK',
        department_name: '骨科',
        department_cat: '外科',
        department_type: '临床'
      }
    ]
  }),
  methods: {
    load: function () {
      let that = this
      var url = this.HOME + '/department/getall'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.desserts = response.data
        })
      console.log('load data ~~~~~~~~~')
      console.log(this.desserts)
    }
  },
  mounted: function () {
    //  this.load()
  },
    computed: {
  },
  watch: {
  }
}
</script>

<style scoped>

</style>
