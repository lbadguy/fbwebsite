<template>
  <div class="manager-layout">
    <!-- 头部导航栏 -->
    <div class="header-navbar">
      <div class="logo-container">
        <img src="@/assets/121_传统.png" alt="Logo" class="logo-image">
        <span class="logo-text">企业管理系统</span>
      </div>
      <div class="flex-spacer"></div>
      <div class="user-info">
        <el-dropdown trigger="click">
          <div class="user-dropdown-link">
            <img
              :src="data.user.avatar || 'https://tse3-mm.cn.bing.net/th/id/OIP-C.mWqbERralY6bvkWEQuhAjwHaIQ?w=152&h=180&c=7&r=0&o=5&pid=1.7'"
              alt="用户头像"
              class="user-avatar"
            >
            <span class="username">{{ data.user.name }}</span>
            <el-icon class="el-icon--right"><arrow-down /></el-icon>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="router.push('/manager/person')">
                <el-icon><user-filled /></el-icon>个人信息
              </el-dropdown-item>
              <el-dropdown-item @click="router.push('/manager/password')">
                <el-icon><lock /></el-icon>修改密码
              </el-dropdown-item>
              <el-dropdown-item divided @click="logout">
                <el-icon><switch-button /></el-icon>退出登录
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
    
    <!-- 主体内容区域 -->
    <div class="main-container">
      <!-- 左侧导航菜单 -->
      <div class="sidebar">
        <el-menu
          router
          :default-active="router.currentRoute.value.path"
          :default-openeds="['1']"
          class="sidebar-menu"
        >
          <el-menu-item index="/manager/home" class="menu-item">
            <el-icon><house /></el-icon>
            <span>首页</span>
          </el-menu-item>
          
          <el-menu-item index="/manager/data" class="menu-item">
            <el-icon><data-analysis /></el-icon>
            <span>数据统计</span>
          </el-menu-item>
          
          <el-menu-item index="/manager/article" v-if="data.user.role === 'ADM'" class="menu-item">
            <el-icon><document /></el-icon>
            <span>文章管理</span>
          </el-menu-item>
          
          <el-menu-item index="/manager/department" v-if="data.user.role === 'ADM'" class="menu-item">
            <el-icon><office-building /></el-icon>
            <span>部门管理</span>
          </el-menu-item>
          
          <el-sub-menu index="1" v-if="data.user.role === 'ADM'">
            <template #title>
              <el-icon><user /></el-icon>
              <span>用户管理</span>
            </template>
            <el-menu-item index="/manager/admin">
              <span style="padding-left: 20px;">管理员信息</span>
            </el-menu-item>
            <el-menu-item index="/manager/employee">
              <span style="padding-left: 20px;">员工信息</span>
            </el-menu-item>
          </el-sub-menu>
          
          <el-menu-item index="/manager/person" class="menu-item">
            <el-icon><user-filled /></el-icon>
            <span>个人信息</span>
          </el-menu-item>
          
          <el-menu-item index="/manager/password" class="menu-item">
            <el-icon><lock /></el-icon>
            <span>修改密码</span>
          </el-menu-item>
          
          <el-menu-item @click="logout" class="menu-item logout-item">
            <el-icon><switch-button /></el-icon>
            <span>退出登录</span>
          </el-menu-item>
        </el-menu>
      </div>
      
      <!-- 右侧内容区域 -->
      <div class="content-area">
        <RouterView @updateUser="updateUser" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from "vue";
import router from "@/router/index.js";
import { 
  House, 
  DataAnalysis, 
  Document, 
  OfficeBuilding, 
  User, 
  UserFilled, 
  Lock, 
  SwitchButton,
  ArrowDown
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

<style scoped>
.manager-layout {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

/* 头部导航栏样式 */
.header-navbar {
  height: 64px;
  background: linear-gradient(90deg, #3c7fff, #5a94ff);
  display: flex;
  align-items: center;
  padding: 0 20px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  position: relative;
  z-index: 10;
}

.logo-container {
  display: flex;
  align-items: center;
}

.logo-image {
  width: 36px;
  height: 36px;
  object-fit: contain;
}

.logo-text {
  font-size: 20px;
  font-weight: 600;
  color: white;
  margin-left: 12px;
}

.flex-spacer {
  flex: 1;
}

.user-info {
  display: flex;
  align-items: center;
}

.user-dropdown-link {
  display: flex;
  align-items: center;
  cursor: pointer;
  padding: 6px 10px;
  border-radius: 6px;
  transition: background-color 0.3s;
}

.user-dropdown-link:hover {
  background-color: rgba(255, 255, 255, 0.1);
}

.user-avatar {
  width: 36px;
  height: 36px;
  border-radius: 50%;
  object-fit: cover;
  border: 2px solid rgba(255, 255, 255, 0.6);
}

.username {
  color: white;
  margin: 0 8px;
  font-size: 15px;
}

/* 主体内容区域样式 */
.main-container {
  display: flex;
  flex: 1;
}

/* 侧边栏样式 */
.sidebar {
  width: 220px;
  background-color: white;
  box-shadow: 2px 0 8px rgba(0, 0, 0, 0.05);
  z-index: 5;
  overflow-y: auto;
  height: calc(100vh - 64px);
}

.sidebar-menu {
  height: auto;
  border-right: none;
}

.menu-item {
  font-size: 15px;
  height: 50px;
  line-height: 50px;
}

.custom-submenu-item {
  padding-left: 55px !important;
  font-size: 14px !important;
  height: 40px !important;
  line-height: 40px !important;
}

.logout-item {
  margin-top: 20px;
  color: #f56c6c;
}

/* 内容区域样式 */
.content-area {
  flex: 1;
  padding: 20px;
  background-color: #f3f3fc;
  overflow-y: auto;
  min-width: 0;
}
</style>

<style>
.el-menu .is-active {
  background-color: #e6f0ff !important;
  color: #3c7fff !important;
  font-weight: 500;
}

.el-menu-item:hover {
  background-color: #f5f9ff !important;
}

.el-sub-menu__title {
  background-color: white;
}

.el-sub-menu__title:hover {
  background-color: #f5f9ff !important;
}

.el-dropdown-menu__item:focus, 
.el-dropdown-menu__item:not(.is-disabled):hover {
  background-color: #f5f9ff;
  color: #3c7fff;
}

/* 修复子菜单展开问题 */
.el-menu--collapse .el-sub-menu__title {
  padding-right: 20px !important;
}

.el-menu-item, .el-sub-menu {
  overflow: visible !important;
}

.el-menu .el-menu {
  background-color: #f9fbff;
  overflow: visible !important;
}

.el-sub-menu > .el-menu {
  padding: 0 !important;
}

.el-sub-menu .el-menu-item {
  min-width: 200px !important;
}

/* 确保子菜单文字不重叠 */
.el-menu--popup {
  min-width: 200px !important;
}

.el-menu--popup .el-menu-item {
  height: 40px !important;
  line-height: 40px !important;
  padding: 0 20px !important;
  text-align: left !important;
}
</style>