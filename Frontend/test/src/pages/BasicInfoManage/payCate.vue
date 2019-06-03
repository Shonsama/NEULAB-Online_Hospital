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
          flat
          color="primary"
          @click="expand = !expand"
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
        item-key="name"
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
          <td >{{ props.item.constant_id }}</td>
          <td >{{ props.item.constant_name }}</td>
          <td >{{ props.item.constant_type }}</td>
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

    <v-divider></v-divider>

  </v-card>
</template>

<script>
export default {
  name: 'office',
  data: () => ({
    expand: false,
    selected: [],
    headers: [
      {
        text: '常量ID',
        align: 'left',
        value: 'constant_id'
      },
      { text: '常量名称', value: 'constant_name' },
      { text: '常量类别', value: 'constant_type' },
      { text: '操作', value: 'operation', sortable: false }
    ],
    desserts: [
      {
        constant_id: 'XNH',
        constant_name: '新农合',
        constant_type: '结算类别'
      }
    ]
  }),
  mounted: function () {
    this.load()
  },
  computed: {
  },
  watch: {
  },
  methods: {
    load: function () {
      let that = this
      console.log('load data ~~~~~~~~~')
      var url = this.HOME + '/constant/get-all'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.desserts = response.data
        })
      console.log('load data ~~~~~~~~~')
      console.log(this.desserts)
    }
  }
}
</script>

<style scoped>

</style>
