<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <v-card>
      <v-toolbar extended flat dense>
        <v-toolbar-title >收费信息</v-toolbar-title>
        <template v-slot:extension>
          <v-flex xs2>
            <v-text-field  prepend-inner-icon="assignment" name="login" label="发票号" type="text" ></v-text-field>
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
            <div class="title font-weight-light">患者信息查询</div>
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
                box
              ></v-text-field>

            </v-flex>
            <v-btn
              large
              style="margin-top: 15px"
            >查询
            </v-btn>
          </v-layout>
          <v-layout>
            <div class="title font-weight-light">患者信息确认</div>
          </v-layout>
          <v-layout>

            <v-flex
              xs12
              md2
            >
              <v-text-field
                v-model="lastname"
                :rules="nameRules"
                :counter="10"
                label="姓名"
                required
                box
              ></v-text-field>
            </v-flex>

            <v-flex
              xs12
              md2
            >
              <v-select
                :items="items"
                label="性别"
                required
                box
              ></v-select>
            </v-flex>

            <v-flex
              xs12
              md4
            >
              <v-text-field
                v-model="lastname"
                :rules="nameRules"
                :counter="10"
                label="家庭住址"
                required
                box
              ></v-text-field>
            </v-flex>

            <v-flex
              xs12
              md4
            >
              <v-text-field
                v-model="firstname"
                :rules="nameRules"
                :counter="10"
                label="身份证号"
                required
                box
              ></v-text-field>
            </v-flex>
          </v-layout>
          <v-layout>
            <v-flex
              xs12
              md2
            >
              <v-menu
                ref="menu"
                v-model="menu"
                :close-on-content-click="false"
                :nudge-right="40"
                :return-value.sync="date"
                lazy
                transition="scale-transition"
                offset-y
                full-width
                min-width="290px"
              >
                <template v-slot:activator="{ on }">
                  <v-text-field
                    v-model="date"
                    label="出生日期"
                    readonly
                    v-on="on"
                    box
                  ></v-text-field>
                </template>
                <v-date-picker v-model="date" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn flat color="primary" @click="menu = false">Cancel</v-btn>
                  <v-btn flat color="primary" @click="$refs.menu.save(date)">OK</v-btn>
                </v-date-picker>
              </v-menu>
            </v-flex>

            <v-flex
              xs12
              md2
            >
              <v-text-field
                v-model="lastname"
                :rules="nameRules"
                :counter="10"
                label="年龄"
                required
                box
              ></v-text-field>
            </v-flex>
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
  name: 'charge'
}
</script>

<style scoped>

</style>
