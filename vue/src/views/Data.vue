<template>
  <div>
    <el-row :gutter="10">
      <el-col :span="12" style="margin-bottom: 10px">
        <div class="card" style="padding: 20px;height:400px;" id="bar"></div>
      </el-col>
      <el-col :span="12" style="margin-bottom: 10px">
        <div class="card" style="padding: 20px;height:400px;" id="line"></div>
      </el-col>
      <el-col :span="12" style="margin-bottom: 10px">
        <div class="card" style="padding: 20px;height:400px;" id="pie"></div>
      </el-col>
    </el-row>

    <!-- 为 ECharts 准备一个定义了宽高的 DOM -->

  </div>
</template>
<script setup>
import {reactive, onMounted} from "vue";
import * as echarts from 'echarts';
import request from "@/untils/request.js";


const barOption = {
  title: {
    text: '各部门员工数量'
  },
  tooltip: {},
  legend: {
    trigger: 'item'
  },
  xAxis: {
    data: []
  },
  yAxis: {},
  series: [
    {
      name: '人数',
      type: 'bar',
      data: [],
      itemStyle: {
        color: function (params) {
          let colors = ['#5470c6', '#91cc75', '#fac858', '#ee6666', '#73c0de', '#3ba272', '#fc8452', '#9a60b4', '#ea7ccc'];
          return colors[params.dataIndex % colors.length];
        }
      }
    },
  ]
};

const lineOption = {
  title: {
    text: '近七天发布文章的数量'
  },
  tooltip: {},
  legend: {
    trigger: 'item'
  },
  xAxis: {
    data: []
  },
  yAxis: {},
  series: [
    {
      name: '发布数量',
      type: 'line',
      data: [],
      smooth: true,
    },
  ]
};

const pieOption = {
  title: {
    text: '部门员工数量占比',
    left: 'center'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: '员工数量',
      type: 'pie',
      radius: '50%',
      data: [],
      center: ['50%', '50%'],
      label: {
        formatter: '{b}: {@2012} ({d}%)'
      },
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0,0,0,0.5)'
        }
      }
    }
  ]
}

const data = reactive({})
// 初始化图表
onMounted(() => {

      // 获取图表实例
      const barChart = echarts.init(document.getElementById('bar'));

      request.get('/barData').then(res => {
        barOption.xAxis.data = res.data.department;
        barOption.series[0].data = res.data.count;
        // 设置图表数据
        barChart.setOption(barOption);
      })

      const lineChart = echarts.init(document.getElementById('line'));
      request.get('/lineData').then(res => {
        lineOption.xAxis.data = res.data.date;
        lineOption.series[0].data = res.data.count;
        // 设置图表数据
        lineChart.setOption(lineOption);
      })

      const pieChart = echarts.init(document.getElementById('pie'));
      request.get('/pieData').then(res => {
        pieOption.series[0].data = res.data;
        // 设置图表数据
        pieChart.setOption(pieOption);
      })
    }
)


</script>

<style scoped>

</style>