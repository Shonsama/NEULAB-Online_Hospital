<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <v-layout column>
      <v-flex>
    <v-card>
      <v-toolbar flat dense>
        <v-toolbar-title  >门诊诊断</v-toolbar-title>
      </v-toolbar>
      <v-layout>
        <v-flex xs3>

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
        </v-flex>
        <v-divider vertical></v-divider>
        <v-flex  xs9>
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
        </v-flex>
      </v-layout>
    </v-card>
      </v-flex>
      <v-flex>
      <v-card>
        <v-layout>
          <v-flex xs3>
            <v-toolbar flat dense>
              <v-toolbar-title  >处方模板</v-toolbar-title>
            </v-toolbar>
            <v-data-table
              v-model="selected"
              :headers="headers"
              :items="desserts"
              item-key="name"
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
          </v-flex>
          <v-divider vertical></v-divider>
          <v-flex  xs9>
            <v-toolbar flat dense>
              <v-toolbar-title> 模板详情</v-toolbar-title>
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
          </v-flex>
        </v-layout>
      </v-card>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
export default {
  name: 'drug'
}
</script>

<style scoped>

</style>
