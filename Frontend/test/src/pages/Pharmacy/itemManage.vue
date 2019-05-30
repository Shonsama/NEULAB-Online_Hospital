<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card>
    <v-flex
    >
      <v-toolbar flat>
        <v-toolbar-title>药品目录</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn   @click="expand = !expand">
          {{ expand ? 'Close' : 'Keep' }} other rows
        </v-btn>
      </v-toolbar>
      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="desserts"
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
          <td>{{ props.item.medicine_id }}</td>
          <td>{{ props.item.medicine_name }}</td>
          <td>{{ props.item.medicine_specifications }}</td>
          <td>{{ props.item.medicine_unit }}</td>
          <td>{{ props.item.medicine_factory }}</td>
          <td>{{ props.item.medicine_formulation }}</td>
          <td>{{ props.item.medicine_type }}</td>
          <td>{{ props.item.medicine_unit_price }}</td>
          <td>{{ props.item.medicine_pinyin }}</td>
          <td>{{ props.item.medicine_usage }}</td>
          <td>
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
  name: 'itemManage',
  data: () => ({
    search: '',
    expand: false,
    selected: [],
    signal: '',
    headers: [
      {
        text: '药品编码',
        align: 'left',
        value: 'medicine_id'
      },
      { text: '药品名称', value: 'medicine_name' },
      { text: '规格', value: 'medicine_specifications' },
      { text: '包装单位', value: 'medicine_unit' },
      { text: '厂家', value: 'medicine_factory' },
      { text: '剂型', value: 'medicine_formulation' },
      { text: '药品类型', value: 'medicine_type' },
      { text: '单价', value: 'medicine_unit_price' },
      { text: '拼音码', value: 'medicine_pinyin' },
      { text: '用法', value: 'medicine_usage' },
      { text: '操作', value: 'operation', sortable: false }
    ],
    desserts: []
  }),
  methods: {
    load: function () {
      let that = this
      console.log('load data ~~~~~~~~~')
      var url = this.HOME + '/medicine/getall'
      this.$http.post(url, {
      })
        .then(function (response) {
          console.log(response.data)
          that.desserts = response.data
        })
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
