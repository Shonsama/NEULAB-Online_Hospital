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

    <v-layout class="ml-4 mt-4" justify-center>
      <v-card style="width: 700px">
        <v-container>
          <v-layout row justify-center>
            <div class="title font-weight-light">门诊收费日结单</div>
          </v-layout>
          <v-divider></v-divider>
          <v-layout row>
            <!--<v-flex style="margin-right: 0px" lg2 offset-xs2>-->
              <!--<v-subheader-->
                <!--class="mt-2">时间范围</v-subheader>-->
            <!--</v-flex>-->
            <v-flex style="margin-right: 50px" class="mt-2" lg3 offset-xs3>
              <v-text-field
                v-model="time_range"
                label="时间范围"
                readonly
              ></v-text-field>
            </v-flex>
            <!--<v-flex lg2>-->
              <!--<v-subheader-->
                <!--class="mt-2">收款员</v-subheader>-->
            <!--</v-flex>-->
            <v-flex class="mt-2" lg3>
              <v-text-field
                v-model="cashier"
                label="收款员"
                readonly
              ></v-text-field>
            </v-flex>
          </v-layout>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>中药费</v-subheader>
            </v-flex>
            <v-flex xs6>
              <v-text-field
                label="金额"
                value="10.00"
                suffix="元"
              ></v-text-field>
            </v-flex>
          </v-layout>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>西药费</v-subheader>
            </v-flex>
            <v-flex xs6>
              <v-text-field
                label="金额"
                value="10.00"
                suffix="元"
              ></v-text-field>
            </v-flex>
          </v-layout>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>挂号费</v-subheader>
            </v-flex>
            <v-flex xs6>
              <v-text-field
                label="金额"
                value="10.00"
                suffix="元"
              ></v-text-field>
            </v-flex>
          </v-layout>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>医技费</v-subheader>
            </v-flex>
            <v-flex xs6>
              <v-text-field
                label="金额"
                value="10.00"
                suffix="元"
              ></v-text-field>
            </v-flex>
          </v-layout>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>已打发票</v-subheader>
            </v-flex>
            <v-flex xs6 class="mr-4">
              <v-text-field
                label="数量"
                value="15"
                suffix="张"
              ></v-text-field>
            </v-flex>
            <v-flex xs1 class="mt-2">
              <v-btn
                flat
                color="primary"
                @click="show = !show , mode = true"
              >
                详细
              </v-btn>
            </v-flex>
          </v-layout>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>已打发票</v-subheader>
            </v-flex>
            <v-flex xs6 class="mr-4">
              <v-text-field
                label="数量"
                value="15"
                suffix="张"
              ></v-text-field>
            </v-flex>
            <v-flex xs1 class="mt-2">
              <v-btn
                flat
                color="primary"
                @click="show = !show , mode = true"
              >
                详细
              </v-btn>
            </v-flex>
          </v-layout>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>已打发票</v-subheader>
            </v-flex>
            <v-flex xs6 class="mr-4">
              <v-text-field
                label="数量"
                value="15"
                suffix="张"
              ></v-text-field>
            </v-flex>
            <v-flex xs1 class="mt-2">
              <v-btn
                flat
                color="primary"
                @click="show = !show , mode = true"
              >
                详细
              </v-btn>
            </v-flex>
          </v-layout>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>已打发票</v-subheader>
            </v-flex>
            <v-flex xs6 class="mr-4">
              <v-text-field
                label="数量"
                value="15"
                suffix="张"
              ></v-text-field>
            </v-flex>
            <v-flex xs1 class="mt-2">
              <v-btn
                flat
                color="primary"
                @click="show = !show , mode = true"
              >
                详细
              </v-btn>
            </v-flex>
          </v-layout>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>已打发票</v-subheader>
            </v-flex>
            <v-flex xs6 class="mr-4">
              <v-text-field
                label="数量"
                value="15"
                suffix="张"
              ></v-text-field>
            </v-flex>
            <v-flex xs1 class="mt-2">
              <v-btn
                flat
                color="primary"
                @click="show = !show , mode = true"
              >
                详细
              </v-btn>
            </v-flex>
          </v-layout>
          <v-layout row>
            <v-flex xs2 offset-xs2>
              <v-subheader>总收入</v-subheader>
            </v-flex>
            <v-flex xs6 class="mr-4">
              <v-text-field
                value="100"
                label="金额"
                suffix="元"
                outline
                readonly
              ></v-text-field>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card>
    </v-layout>
  </v-card>
</template>

<script>
export default {
  data: () => ({
    time_range: '2019.8.1-2019.9.1',
    cashier:'shuwanyang',
    daily_mid_prescription_sum: '100',
    daily_west_prescription_sum: '150',
    daily_med_sum: '250',
    daily_register_sum: '50',
    daily_ms_sum: '30',
    daily_cure_sum: '80',
    bill_already: '15'
  })
}
</script>

<style scoped>

</style>
