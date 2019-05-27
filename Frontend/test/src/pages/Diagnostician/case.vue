<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
  <v-toolbar flat dense >
    <v-layout align-center justify-space-around row fill-height>
      <v-btn small color="primary">
        暂存
      </v-btn>


      <v-btn small color="primary">
        提交
      </v-btn>


      <v-btn small color="primary">
        清空
      </v-btn>


      <v-btn small color="primary">
        刷新
      </v-btn>

    </v-layout>
  </v-toolbar>
    <v-layout>
      <v-flex xs12>
      <v-form
        class="pa-3 pt-4"
      >
        <v-card-title primary-title>
          <div>
            <h5 class="subheading">病史</h5>
          </div>
        </v-card-title>
        <v-textarea
          box
          label="主诉"
          placeholder="请输入主诉"
          rows="1"
        ></v-textarea>
        <v-textarea
          box
          label="现病史"
          placeholder="请输入现病史"
          rows="2"
        ></v-textarea>
        <v-textarea
          box
          label="现病治疗情况"
          placeholder="请输入现病治疗情况"
          rows="2"
        ></v-textarea>
        <v-textarea
        box
        label="既往史"
        placeholder="请输入既往史"
        rows="2"
      ></v-textarea>
        <v-textarea
          box
          label="过敏史"
          placeholder="请输入过敏史"
          rows="2"
        ></v-textarea>
        <v-layout >
      <v-card-title primary-title>
        <div>
          <h5 class="subheading">初步诊断</h5>
        </div>
      </v-card-title>
          <v-btn flat icon color="primary" style="margin-top: 17px" @click="expand = !expand">
            <v-icon>add</v-icon>
          </v-btn>
          <v-flex shrink>
            <v-expand-x-transition>
              <div v-show="expand" style="white-space: nowrap">
                <v-btn color="primary" style="margin-top: 17px" @click="Dexpand = !Dexpand">
                  西医诊断
                </v-btn>
                <v-btn color="primary" style="margin-top: 17px" @click="Dexpand = !Dexpand">
                  中医诊断
                </v-btn>
              </div>
            </v-expand-x-transition>
          </v-flex>
        </v-layout>
        <v-expand-transition>
          <div v-show="Dexpand">
           <v-card>
             <v-toolbar flat >
               <v-toolbar-title  >西医</v-toolbar-title>
               <v-spacer></v-spacer>
               <v-btn   dark color="primary" @click="expand = !expand">
                 新增
               </v-btn>
               <v-btn   dark color="primary" @click="expand = !expand">
                 删除
               </v-btn>
             </v-toolbar>
             <v-data-table
               v-model="selected"
               :headers="headers"
               :items="desserts"
               item-key="name"
               select-all
               class="elevation-1"
               style="margin-bottom: 20px"
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
          </div>
        </v-expand-transition>
        <v-textarea
          box
          label="检查建议"
          placeholder="请输入检查建议"
          rows="2"
        ></v-textarea>
        <v-textarea
          box
          label="注意事项"
          placeholder="请输入注意事项"
          rows="2"
        ></v-textarea>
      </v-form>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
export default {
  name: 'case',
  data () {
    return {
      expand:false,
      Dexpand:false

    }
  }
}
</script>

<style scoped>

</style>
