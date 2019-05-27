<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <v-card >
    <v-layout>
      <v-flex shrink >
        <v-expand-x-transition >
          <div v-show="show" style="white-space: nowrap">
            <v-toolbar flat>
              <v-text-field  prepend-inner-icon="account_box" name="login" label="选择患者"  type="text" ></v-text-field>
              <v-spacer></v-spacer>
              <v-btn flat icon color="primary">
                <v-icon>cached</v-icon>
              </v-btn>
            </v-toolbar>
            <v-layout>
            <v-text-field  xs2 prepend-inner-icon="search" name="login" label="查询患者"  type="text" style="margin-left: 10px"></v-text-field>
              <v-btn small color="primary" style="top: 15px">
              搜索
            </v-btn>
            </v-layout>
            <v-layout>
              <v-tabs
                v-model="active"
              >
                <v-tab
                  v-for="item in items1"
                  :key="item"
                  ripple
                >
                  {{item.name}}
                </v-tab>
                <v-tab-item
                  v-for="item in items1"
                  :key="item"
                >
                  <v-card flat >
                    <v-toolbar flat dense>
                      <v-toolbar-title>已诊断</v-toolbar-title>
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
                  <v-card flat >
                    <v-toolbar flat dense>
                      <v-toolbar-title>未诊断</v-toolbar-title>
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
                </v-tab-item>
              </v-tabs>
            </v-layout>
            <v-layout>

            </v-layout>
          </div>

        </v-expand-x-transition>
      </v-flex>
      <v-divider vertical></v-divider>

      <v-flex xs12 md12>
        <v-toolbar flat>
          <v-toolbar-side-icon
            @click.stop="show = !show"
          ></v-toolbar-side-icon>
          <v-toolbar-title  >请选择患者</v-toolbar-title>
        </v-toolbar>

        <v-tabs
          v-model="active1"
        >
          <v-tab
            v-for="item in items"
            :key="item"
            ripple
          >
            {{item.name}}
          </v-tab>
          <v-tab-item
            v-for="item in items"
            :key="item"
          >
            <v-card flat>
              <div v-if="item.id == 'caseHistory'">
                <caseHistory></caseHistory>
              </div>
              <div v-if="item.id == 'inspect'">
                <inspect></inspect>
              </div>
              <div v-if="item.id == 'check'">
                <check></check>
              </div>
              <div v-if="item.id == 'diagnosis'">
                <diagnosis></diagnosis>
              </div>
              <div v-if="item.id == 'dispose'">
                <dispose></dispose>
              </div>
              <div v-if="item.id == 'medicine'">
                <medicine></medicine>
              </div>
              <div v-if="item.id == 'drug'">
                <drug></drug>
              </div>
              <div v-if="item.id == 'cost'">
                <cost></cost>
              </div>
              <!--<router-view name="office"></router-view name="office">-->
            </v-card>
          </v-tab-item>
        </v-tabs>
      </v-flex>
      </v-layout>
    </v-card>
  </div>
</template>

<script>
import caseHistory from '@/pages/Diagnostician/case'
import inspect from '@/pages/Diagnostician/inspect'
import check from '@/pages/Diagnostician/check'
import diagnosis from '@/pages/Diagnostician/diagnosis'
import dispose from '@/pages/Diagnostician/dispose'
import medicine from '@/pages/Diagnostician/medicine'
import drug from '@/pages/Diagnostician/drug'
import cost from '@/pages/Diagnostician/cost'

export default {
  name: 'Diagnostician',
  components: {
    caseHistory,
    inspect,
    check,
    diagnosis,
    dispose,
    medicine,
    drug,
    cost
  },
  data () {
    return {
      e1: 1,
      active: '1',
      active1: '1',
      show: true,
      steps: 8,
      items1: [{
        name: '本人',
        id: 'self'
      },
      {
        name: '科室',
        id: 'department'
      }],
      items: [{
        name: '病例首页',
        id: 'caseHistory'
      },
      {
        name: '检查申请',
        id: 'inspect'
      },
      {
        name: '检验申请',
        id: 'check'
      },
      {
        name: '门诊确诊',
        id: 'diagnosis'
      },
      {
        name: '处置申请',
        id: 'dispose'
      },
      {
        name: '成药处方',
        id: 'medicine'
      },
      {
        name: '草药处方',
        id: 'drug'
      },
      {
        name: '费用查询',
        id: 'cost'
      }]
    }
  }
}
</script>

<style scoped>

</style>
