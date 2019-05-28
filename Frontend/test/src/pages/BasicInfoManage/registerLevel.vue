<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card>
    <v-flex
    >
      <v-toolbar flat>
        <v-flex xs3>
          <v-text-field
            prepend-inner-icon="search"
            v-model="firstname"
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
          <td >{{ props.item.id }}</td>
          <td >{{ props.item.level }}</td>
          <td >{{ props.item.fee }}</td>
          <td >{{ props.item.sequence }}</td>
          <td >{{ props.item.maximum }}</td>
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
        text: '号别编码',
        align: 'left',
        value: 'id'
      },
      { text: '号别名称', value: 'level' },
      { text: '顺序号', value: 'sequence' },
      { text: '费用', value: 'fee' },
      { text: '挂号限额', value: 'maximum' },
      { text: '操作', value: 'operation', sortable: false }
    ],
    desserts: [
      {
        id: 'ZJ',
        level: '专家',
        sequence: 2,
        fee: 5,
        maximum: 50
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
