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
        item-key="name"
        select-all
        class="elevation-1"
        hide-actions
      >
        <template v-slot:items="props">
          <td>
            <v-checkbox
              v-model="props.selected"
              primary
              hide-details
            ></v-checkbox>
          </td>
          <td >{{ props.item.id }}</td>
          <td >{{ props.item.name }}</td>
          <td >{{ props.item.standard }}</td>
          <td >{{ props.item.price_category }}</td>
          <td >{{ props.item.price }}</td>
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
    expand: false,
    selected: [],
    headers: [
      {
        text: '项目编码',
        align: 'left',
        value: 'id'
      },
      { text: '项目名称', value: 'name' },
      { text: '规格', value: 'standard' },
      { text: '费用分类', value: 'price_category' },
      { text: '单价', value: 'price' },
      { text: '操作', value: 'operation', sortable: false }
    ],
    desserts: [
      {
        id: 'NYFX(HJJ)',
        name: '尿液分析(含镜检)',
        standard: '江北',
        price_category: '检验费',
        price: '30.48'
      }
    ]
  }),
  computed: {
  },
  watch: {
  },
  methods: {
  }
}
</script>

<style scoped>

</style>
