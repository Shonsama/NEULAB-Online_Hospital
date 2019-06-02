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
        select-all
        class="elevation-1"
        extended
      >
        <template v-slot:items="props">
          <td>
            <v-checkbox
              v-model="props.selected"
              primary
              hide-details
            ></v-checkbox>
          </td>
          <td>{{ props.item.id }}</td>
          <td >{{ props.item.name }}</td>
          <td >{{ props.item.classification }}</td>
          <td >{{ props.item.type }}</td>
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
        text: '科室编号',
        align: 'left',
        value: 'id'
      },
      { text: '科室名称', value: 'name' },
      { text: '科室分类', value: 'classification' },
      { text: '科室类型', value: 'type' },
      { text: '操作', value: 'operation', sortable: false }
    ],
    desserts: [
      {
        id: 'XXGNK',
        name: '心血管内科',
        classification: '内科',
        type: '临床'
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
