<template>
  <div style="font-size: 20px; font-weight: bold; color: red;text-decoration: underline ; font-style: italic ">
    balala!大家好
  </div>
  <div style="margin-bottom: 200px ">
    {{ data.d }} {{ data.c }} {{ data.a }}
  </div>

  <div>
    <input v-model="data.a"/>
  </div>

  <div>
    <span style="color: red" v-if="data.name === 'hh'">小镇战列舰</span>
    <span style="color: yellow" v-if="data.name === 123">洛克王国</span>
    <span style="color:blue" v-else>国</span>
  </div>
  <div style="display: flex">
    <div
        style="width: 300px;height: 300px;text-align: center;line-height:300px;font-size:30px;margin-right:10px;background-color: green"
        v-for="item in data.arr">{{ item }}
    </div>
    <!--  <div style="width: 300px;height: 300px;text-align: center;line-height:300px;font-size:30px;margin-right:10px;background-color: green">2</div>-->
    <!--  <div style="width: 300px;height: 300px;text-align: center;line-height:300px;font-size:30px;margin-right:10px;background-color: green">3</div>-->
  </div>

  <div>
    <select style="width: 200px">
      <option v-for="item in data.fruits">{{ item }}</option>
    </select>
  </div>

  <div>
    <el-button type="primary" @click="click">点击砍一刀</el-button>

    <button @click="click">点击砍一刀</button>
  </div>

  <div>
    <el-button @click="anger" :style="{color:'red'}">爸爸</el-button>
    <el-button type="primary">妈妈</el-button>
    <el-button type="success">爷爷</el-button>
    <el-button type="info">奶奶</el-button>
    <el-button type="warning">机器人</el-button>
    <el-button type="danger">恶灵</el-button>
  </div>

  <div style="margin: 30px">
    <el-icon :size="20">
      <Edit/>
    </el-icon>

    <span style="margin-left:30px">
      <el-icon :size="20">
        <View/>
      </el-icon>
    </span>
  </div>

</template>
<script setup>
import {reactive, ref} from "vue";

const a = ref(1)
const data = reactive({
  a: 111,
  b: 2,
  c: 3,
  d: "hello,u mtfker",
  name: "h",
  arr: [12, 3],
  fruits: ["apple", "banana", "orange"]
})

const click = () => {
  alert("砍一刀")
}

const anger = () => {
  alert("你再说一遍！")
}
</script>


