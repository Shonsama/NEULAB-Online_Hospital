<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-card flat>
      <v-toolbar flat>
        <v-layout row>
          <v-flex xs2>
            <v-select
              v-model="department_type"
              :items="department_items"
              label="日结员"
              class="mr-4"
            ></v-select>
          </v-flex>
          <v-flex>
            <el-date-picker
              v-model="date"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              style="margin-top: 10px"
            >
            </el-date-picker>
            </v-flex>
          <v-spacer/>
          <v-tooltip bottom>
            <template v-slot:activator="{ on }">
              <v-btn
                large
                icon
                flat
                color="primary"
                @click="load"
                v-on="on"
              >
                <v-icon>
                  search
                </v-icon>
              </v-btn>
            </template>
            <span>搜索日结信息</span>
          </v-tooltip>
          <v-tooltip bottom>
            <template v-slot:activator="{ on }">
              <v-btn
                large
                icon
                flat
                color="primary"
                @click="load"
                v-on="on"
              >
                <v-icon>
                  save
                </v-icon>
              </v-btn>
            </template>
            <span>核对通过</span>
          </v-tooltip>
        </v-layout>
      </v-toolbar>

    <v-layout class="ml-4 mt-4">
      <v-container>
        <v-layout row justify-center>
          <div class="title font-weight-light">门诊收费日结单</div>
        </v-layout>
        <v-divider></v-divider>
        <v-card>
        <v-layout wrap>
          <v-flex xs3 offset-xs2>
            <v-text-field
                  class="title font-weight-light"
                  v-model="time_range"
                  label="时间范围"
                  readonly
            ></v-text-field>
          </v-flex>
          <v-flex xs3 offset-xs2>
            <v-text-field
              class="title font-weight-light"
              v-model="cashier"
              label="收费员"
              readonly
            ></v-text-field>
          </v-flex>
          <v-spacer/>
          <v-card>
            <v-card-title primary-title>
              <div>
                <h3 class="headline mb-0">发票</h3>
              </div>
            </v-card-title>
          </v-card>
        </v-layout>
        </v-card>
      </v-container>
    </v-layout>

  </v-card>
</template>

<script>
export default {
  data: () => ({
    time_range: '2019.8.1-2019.9.1',
    cashier:'shu'
  })
}
</script>

<style scoped>

</style>
