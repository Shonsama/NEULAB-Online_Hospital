<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <v-card flat>
      <v-toolbar flat>
      <v-toolbar-title>Expandable Table</v-toolbar-title>
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
        <td>{{ props.item.name }}</td>
        <td class="text-xs-right">{{ props.item.calories }}</td>
        <td class="text-xs-right">{{ props.item.fat }}</td>
        <td class="text-xs-right">{{ props.item.carbs }}</td>
        <td class="text-xs-right">{{ props.item.protein }}</td>
        <td class="text-xs-right">{{ props.item.iron }}</td>
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
    </v-card>
    <v-chart
      :options="bar"
      :init-options="initOptions"
      ref="bar"
      theme="ovilia-green"
      autoresize
    />  </div>
</template>

<script>
import qs from 'qs'

export default {
  name: 'workSumByDepartment',
  data () {
    let data = []
    let options = qs.parse(location.search, { ignoreQueryPrefix: true })

    for (let i = 0; i <= 360; i++) {
      let t = i / 180 * Math.PI
      let r = Math.sin(2 * t) * Math.cos(2 * t)
      data.push([r, i])
    }
    return {
      initOptions: {
        renderer: options.renderer || 'canvas'
      },
      bar: {
        legend: {},
        tooltip: {},
        dataset: {
          // Provide data.
          source: [
            ['Product', '2015', '2016', '2017'],
            ['Matcha Latte', '2015', '2016', '2017'],
            ['Milk Tea', '2015', '2016', '2017'],
            ['Cheese Cocoa', '2015', '2016', '2017'],
            ['Walnut Brownie', '2015', '2016', '2017']
          ]
        },
        // Declare X axis, which is a category axis, mapping
        // to the first column by default.
        xAxis: {type: 'category'},
        // Declare Y axis, which is a value axis.
        yAxis: {},
        // Declare several series, each of them mapped to a
        // column of the dataset by default.
        series: [{type: 'bar'}, {type: 'bar'}, {type: 'bar'}]
      }
    }
  }
}
</script>

<style scoped>

</style>
