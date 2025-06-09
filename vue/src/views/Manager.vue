<template>
  <div>
    <!--头部开始-->
    <div style="height: 60px;background-color: #3c7fff;display: flex;align-items: center">
      <div style="width: 200px;display: flex;align-items: center;padding-left: 20px">
        <img style="width: 40px" src="@/assets/121_传统.png" alt="">
        <span style="font-size: 20px;color: white;margin-left: 5px">后台管理系统</span>
      </div>
      <div style="flex: 1"></div>
      <div style="width: fit-content;display:flex;align-items: center;padding-right:10px  ">
        <img
            :src="data.user.avatar || 'https://tse3-mm.cn.bing.net/th/id/OIP-C.mWqbERralY6bvkWEQuhAjwHaIQ?w=152&h=180&c=7&r=0&o=5&pid=1.7'"
            alt=""
            style="width: 40px;height: 40px;border-radius: 50%">
        <span style="color: white;margin-left: 5px">{{ data.user.name }}</span>
      </div>
    </div>
    <!--左侧导航-->
    <div style="display: flex">
      <div style="width: 200px;border-right:1px solid #ddd;min-height:calc(100vh - 60px) ">
        <el-menu router :default-active="router.currentRoute.value.path" :default-openeds="['1', 'book-system']" style="border: 0">
          <el-menu-item index="/manager/home">
            <el-icon>
              <House/>
            </el-icon>
            首页
          </el-menu-item>
          <el-sub-menu index="book-system">
            <template #title>
              <el-icon>
                <Reading/>
              </el-icon>
              <span>图书管理系统</span>
            </template>
            <el-menu-item index="/manager/book">
              <el-icon>
                <Notebook/>
              </el-icon>
              图书管理
            </el-menu-item>
            <el-menu-item index="/manager/borrow">
              <el-icon>
                <DocumentCopy/>
              </el-icon>
              借阅管理
            </el-menu-item>
            <el-menu-item index="/manager/reader">
              <el-icon>
                <User/>
              </el-icon>
              读者管理
            </el-menu-item>
            <el-menu-item index="/manager/category">
              <el-icon>
                <CollectionTag/>
              </el-icon>
              分类管理
            </el-menu-item>
          </el-sub-menu>
          <el-menu-item index="/manager/data">
            <el-icon>
              <DataAnalysis/>
            </el-icon>
            数据统计
          </el-menu-item>
          <el-menu-item index="/manager/article"  v-if="data.user.role === 'ADM'">
            <el-icon>
              <Document/>
            </el-icon>
            文章管理
          </el-menu-item>
          <el-menu-item index="/manager/department" v-if="data.user.role === 'ADM'">
            <el-icon>
              <OfficeBuilding/>
            </el-icon>
            部门管理
          </el-menu-item>
          <el-sub-menu index="1" v-if="data.user.role === 'ADM'">
            <template #title>
              <el-icon>
                <User/>
              </el-icon>
              <span>用户管理</span>
            </template>
            <el-menu-item index="/manager/admin">管理员信息</el-menu-item>
            <el-menu-item index="/manager/employee">员工信息</el-menu-item>
          </el-sub-menu>
          <el-menu-item index="/manager/person">
            <el-icon>
              <UserFilled/>
            </el-icon>
            个人信息
          </el-menu-item>
          <el-menu-item index="/manager/password">
            <el-icon>
              <Lock/>
            </el-icon>
            修改密码
          </el-menu-item>
          <el-menu-item @click="logout">
            <el-icon>
              <SwitchButton/>
            </el-icon>
            退出登录
          </el-menu-item>
        </el-menu>
      </div>
      <!--左侧导航结束-->
      <!--右侧主题区域开始-->
      <div style="flex: 1;width: 0;background-color: #f3f3fc;padding: 10px">
        <RouterView @updateUser="updateUser"/>
      </div>
    </div>
  </div>
</template>

<script setup>
import {reactive} from "vue";
import router from "@/router/index.js";
import {
  House,
  User,
  UserFilled,
  Document,
  DataAnalysis,
  OfficeBuilding,
  Lock,
  SwitchButton,
  Reading,
  Notebook,
  DocumentCopy,
  CollectionTag
} from '@element-plus/icons-vue';

const data = reactive({
  user: JSON.parse(localStorage.getItem('xm-pro-user'))
})


const logout = () => {
  location.href = '/login'
  localStorage.removeItem('xm-pro-user')
}

const updateUser = () => {
  data.user = JSON.parse(localStorage.getItem('xm-pro-user'))
}
</script>


<style>
.el-menu .is-active {
  background-color: #e6ecf7;
}

.el-sub-menu__title {
  background-color: white;
}
</style>