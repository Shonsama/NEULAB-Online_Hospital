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
          <td >{{ props.item.disease_id }}</td>
          <td >{{ props.item.disease_name }}</td>
          <td >{{ props.item.disease_icd }}</td>
          <td >{{ props.item.disease_type }}</td>
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
        text: '疾病编码',
        align: 'left',
        value: 'disease_id'
      },
      { text: '疾病名称', value: 'disease_name' },
      { text: '国际ICD编码', value: 'disease_icd' },
      { text: '疾病所属分类', value: 'disease_type' },
      { text: '操作', value: 'operation', sortable: false }
    ],
    desserts: [
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
      var url = this.HOME + '/diseaseinfo/getall'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.desserts = response.data
        })
    }
  }
}
</script>

<style scoped>

</style>
