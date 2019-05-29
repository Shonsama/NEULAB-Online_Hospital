<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
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
      item-key="name1"

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
</template>

<script>
export default {
  name: 'cost'
}
</script>

<style scoped>

</style>
