<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card>
      <v-flex>
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
        text: 'Dessert (100g serving)',
        align: 'left',
        sortable: false,
        value: 'name'
      },
      { text: 'Calories', value: 'calories' },
      { text: 'Fat (g)', value: 'fat' },
      { text: 'Carbs (g)', value: 'carbs' },
      { text: 'Protein (g)', value: 'protein' },
      { text: 'Iron (%)', value: 'iron' },
      { text: 'Actions', value: 'name', sortable: false }

    ],
    desserts: [
      {
        name: 'Frozen Yogurt',
        calories: 159,
        fat: 6.0,
        carbs: 24,
        protein: 4.0,
        iron: '1%'
      },
      {
        name: 'Ice cream sandwich',
        calories: 237,
        fat: 9.0,
        carbs: 37,
        protein: 4.3,
        iron: '1%'
      },
      {
        name: 'Eclair',
        calories: 262,
        fat: 16.0,
        carbs: 23,
        protein: 6.0,
        iron: '7%'
      },
      {
        name: 'Cupcake',
        calories: 305,
        fat: 3.7,
        carbs: 67,
        protein: 4.3,
        iron: '8%'
      },
      {
        name: 'Gingerbread',
        calories: 356,
        fat: 16.0,
        carbs: 49,
        protein: 3.9,
        iron: '16%'
      },
      {
        name: 'Jelly bean',
        calories: 375,
        fat: 0.0,
        carbs: 94,
        protein: 0.0,
        iron: '0%'
      },
      {
        name: 'Lollipop',
        calories: 392,
        fat: 0.2,
        carbs: 98,
        protein: 0,
        iron: '2%'
      },
      {
        name: 'Honeycomb',
        calories: 408,
        fat: 3.2,
        carbs: 87,
        protein: 6.5,
        iron: '45%'
      },
      {
        name: 'Donut',
        calories: 452,
        fat: 25.0,
        carbs: 51,
        protein: 4.9,
        iron: '22%'
      },
      {
        name: 'KitKat',
        calories: 518,
        fat: 26.0,
        carbs: 65,
        protein: 7,
        iron: '6%'
      }
    ],
    breweries: [],
    isLoading: false,
    tree: '',
    types: [],

    items: [
      {
        id: 1,
        name: 'Vuetify Human Resources',
        children: [
          {
            id: 2,
            name: 'Core team',
            children: [
              {
                id: 201,
                name: 'John'
              },
              {
                id: 202,
                name: 'Kael'
              },
              {
                id: 203,
                name: 'Nekosaur'
              },
              {
                id: 204,
                name: 'Jacek'
              },
              {
                id: 205,
                name: 'Andrew'
              }
            ]
          },
          {
            id: 3,
            name: 'Administrators',
            children: [
              {
                id: 301,
                name: 'Ranee'
              },
              {
                id: 302,
                name: 'Rachel'
              }
            ]
          },
          {
            id: 4,
            name: 'Contributors',
            children: [
              {
                id: 401,
                name: 'Phlow'
              },
              {
                id: 402,
                name: 'Brandon'
              },
              {
                id: 403,
                name: 'Sean'
              }
            ]
          }
        ]
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
