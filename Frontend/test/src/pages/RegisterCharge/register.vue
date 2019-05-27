<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
<div>
<v-card>
  <v-toolbar extended flat dense>
    <v-toolbar-title >挂号信息</v-toolbar-title>
    <template v-slot:extension>
    <v-flex xs2>
    <v-text-field prepend-inner-icon="assignment" name="login" label="发票号" type="text" ></v-text-field>
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

        ></v-text-field>
        <v-btn
          small
        >查询
        </v-btn>
        <v-btn
          small
        >新增
        </v-btn>
      </v-flex>
      </v-layout>
      <v-layout>
        <div class="title font-weight-light">患者信息确认</div>
      </v-layout>
      <v-layout>

        <v-flex
          xs2
        >
          <v-text-field
            v-model="lastname"
            :rules="nameRules"
            :counter="10"
            label="姓名"
            placeholder="请输入姓名"
            required
          ></v-text-field>
        </v-flex>

        <v-flex
          xs2
        >
          <v-select
            :items="items"
            label="性别"
            required
            placeholder="请选择性别"
          ></v-select>
        </v-flex>

        <v-flex
          xs4
        >
          <v-textarea
            v-model="lastname"
            :rules="nameRules"
            :counter="10"
            label="家庭住址"
            placeholder="请输入家庭住址"
            required
            rows="1"
          ></v-textarea>
        </v-flex>

        <v-flex
          xs3
        >
          <v-text-field
            v-model="firstname"
            :rules="nameRules"
            :counter="10"
            label="身份证号"
            placeholder="请输入身份证号"
            required
          ></v-text-field>
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex
          xs2
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
                placeholder="请选择出生日期"
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
          xs2
          md2
        >
          <v-text-field
            v-model="lastname"
            :rules="nameRules"
            :counter="10"
            label="年龄"
            required
            placeholder="请输入年龄"
          ></v-text-field>
        </v-flex>

        <v-flex
          xs3
          md1
        >
          <v-select
            :items="items"
            label="单位"
            required
            placeholder="岁"
          ></v-select>
        </v-flex>
        <v-flex
          xs3
          md3
        >
          <v-select
            :items="items"
            label="结算类别"
            required
            placeholder="请选择结算类别"
          ></v-select>
        </v-flex>

        <v-flex
          xs3
          md3
        >
          <v-select
            :items="items"
            label="挂号级别"
            required
            placeholder="请选择挂号级别"
          ></v-select>
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex
          xs2
          md2
        >
          <v-select
            :items="items"
            label="科室"
            required
            placeholder="请选择科室"
          ></v-select>
        </v-flex>
        <v-flex
          xs2
          md2
        >
          <v-select
            :items="items"
            label="看诊医生"
            required
            placeholder="请选择看诊医生"
          ></v-select>
        </v-flex>

        <v-flex xs4>
          <v-checkbox
            v-model="checkbox"
            v-validate="'required'"
            value="1"
            style="margin-left: 10px"
            label="是否需要病历本"
            data-vv-name="checkbox"
            type="checkbox"
            required
          ></v-checkbox>
        </v-flex>
        <v-flex xs2 md2>
          <v-text-field
            label="应收金额"
            placeholder="$23"
            disabled
          ></v-text-field>
        </v-flex>
        <v-btn small style="margin-top: 25px">挂号</v-btn>
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
