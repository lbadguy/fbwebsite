<template >
  <div>
    <div style="margin-bottom: 20px">
      <RouterLink to="/test">跳转到Test.vue</RouterLink>
    </div>
<!--编程式路由跳转-->
    <div>
      <el-button type="primary" @click="router.push('/test')">跳转到新的页面</el-button>
      <el-button type="primary" @click="router.replace('/test')">跳转到新的页面</el-button>
    </div>
  <div>
    <el-input v-model="data.input" style="width: 240px" placeholder="Please input" :prefix-icon="Search"/>
    {{ data.input }}
    <el-input style="width: 200px" :suffix-icon="Calendar"></el-input>
    <el-input type="textarea" v-model="data.desc" style="width: 300px" placeholder="enter please"></el-input>

  </div>

  <div style="margin: 20px 0">
    <el-select
        clearable
        multiple
        v-model="data.value"
        placeholder="Select"
        size="large"
        style="width: 240px"
    >
      <el-option
          v-for="item in data.options"
          :key="item"
          :label="item"
          :value="item"
      />
    </el-select>

  </div>

  <div style="margin: 20px 0">
    <el-radio-group v-model="data.sex">
      <el-radio value="男">男</el-radio>
      <el-radio value="女">女</el-radio>
    </el-radio-group>
  </div>
  </div>
</template>

<script setup>
import {reactive} from "vue";
import {Calendar, Search} from "@element-plus/icons-vue";
import router from "@/router/index.js";
import request from "@/untils/request.js";
const data = reactive({
  input: null,
  desc:'dassdaadasaddsaldadakdaldsasda',
  value:'',
  options:['apple','banana','orange'],
  sex:'男',
  employeeList:[]
})

request.get('/employee/selectAll').then(res =>{
  console.log(res)
  data.employeeList=res.data;
})

</script>