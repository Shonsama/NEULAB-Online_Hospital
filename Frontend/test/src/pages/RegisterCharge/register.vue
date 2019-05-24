<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
<div>
<v-card>
  <v-toolbar extended flat dense>
    <v-toolbar-title >挂号信息</v-toolbar-title>
    <template v-slot:extension>
    <v-flex xs2>
    <v-text-field prepend-icon="assignment" name="login" label="发票号" type="text" ></v-text-field>
    </v-flex>
    <v-btn
      small
    >更新发票号
    </v-btn>
      <v-spacer></v-spacer>

    </template>

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
            label="病历号"
            required
          ></v-text-field>
        </v-flex>

        <v-flex
          xs12
          md4
        >
          <v-text-field
            v-model="lastname"
            :rules="nameRules"
            :counter="10"
            label="姓名"
            required
          ></v-text-field>
        </v-flex>

        <v-flex
          xs12
          md4
        >
          <v-select
            :items="items"
            label="性别"
            required
          ></v-select>
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex
          xs12
          md4
        >
          <v-text-field
            v-model="firstname"
            :rules="nameRules"
            :counter="10"
            label="出身日期"
            required
          ></v-text-field>
        </v-flex>

        <v-flex
          xs12
          md4
        >
          <v-text-field
            v-model="lastname"
            :rules="nameRules"
            :counter="10"
            label="年龄"
            required
          ></v-text-field>
        </v-flex>

        <v-flex
          xs12
          md4
        >
          <v-select
            :items="items"
            label="结算类别"
            required
          ></v-select>
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex
          xs12
          md4
        >
          <v-text-field
            v-model="firstname"
            :rules="nameRules"
            :counter="10"
            label="病历号"
            required
          ></v-text-field>
        </v-flex>

        <v-flex
          xs12
          md4
        >
          <v-text-field
            v-model="lastname"
            :rules="nameRules"
            :counter="10"
            label="姓名"
            required
          ></v-text-field>
        </v-flex>

        <v-flex
          xs12
          md4
        >
          <v-select
            :items="items"
            label="性别"
            required
          ></v-select>
        </v-flex>
      </v-layout>

    </v-container>
  </v-form>
</v-card>
  <v-card>
    <v-toolbar flat dense>
      <v-toolbar-title >挂号信息列表</v-toolbar-title>
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
  name: 'register',
  data: () => ({
    valid: false,
    firstname: '',
    lastname: '',
    nameRules: [
      v => !!v || 'Name is required',
      v => v.length <= 10 || 'Name must be less than 10 characters'
    ],
    email: '',
    emailRules: [
      v => !!v || 'E-mail is required',
      v => /.+@.+/.test(v) || 'E-mail must be valid'
    ]
  })
}
</script>

<style scoped>

</style>
