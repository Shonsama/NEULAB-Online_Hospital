<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <v-card>
      <v-toolbar flat dense>
        <v-toolbar-title >
          患者费用查询
        </v-toolbar-title>
      </v-toolbar>
      <v-divider></v-divider>
      <v-form v-model="valid">
        <v-container>
          <v-layout>
            <v-flex
              xs12
              md4
            >
              <v-text-field
                v-model="firstname"
                :rules="nameRules"
                :counter="10"
                label="病历号/姓名"
                required
                box
              ></v-text-field>
            </v-flex>
            <v-btn
              large
              style="margin-top: 15px"
            >查询
            </v-btn>
          </v-layout>
        </v-container>
      </v-form>
    </v-card>
    <v-card>
      <v-toolbar flat dense>
        <v-toolbar-title >患者收费列表</v-toolbar-title>
        <v-spacer></v-spacer>
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
        </v-btn>
      </v-card-actions>
    </v-card>

  </div>
</template>

<script>
export default {
  name: 'cost'
}
</script>

<style scoped>

</style>
