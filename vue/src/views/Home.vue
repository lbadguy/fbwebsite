<template>
  <div class="home-container">
    <div class="welcome-section">
      <div class="welcome-icon">
        <el-icon :size="60"><HomeFilled /></el-icon>
      </div>
      <h1>欢迎使用企业管理系统</h1>
      <p class="subtitle">高效、便捷、安全的一体化解决方案</p>
    </div>
    
    <div class="dashboard-stats">
      <div class="stat-card" @click="navigateTo('/manager/employee')" v-if="canAccessEmployee">
        <div class="stat-icon">
          <el-icon :size="32"><User /></el-icon>
        </div>
        <div class="stat-content">
          <div class="stat-value">{{ data.employeeCount }}</div>
          <div class="stat-label">员工总数</div>
        </div>
      </div>
      
      <div class="stat-card" @click="navigateTo('/manager/article')" v-if="canAccessArticle">
        <div class="stat-icon">
          <el-icon :size="32"><Document /></el-icon>
        </div>
        <div class="stat-content">
          <div class="stat-value">{{ data.articleCount }}</div>
          <div class="stat-label">文章数量</div>
        </div>
      </div>
      
      <div class="stat-card" @click="navigateTo('/manager/department')" v-if="canAccessDepartment">
        <div class="stat-icon">
          <el-icon :size="32"><OfficeBuilding /></el-icon>
        </div>
        <div class="stat-content">
          <div class="stat-value">{{ data.departmentCount }}</div>
          <div class="stat-label">部门数量</div>
        </div>
      </div>
      
      <div class="stat-card" @click="navigateTo('/manager/data')">
        <div class="stat-icon">
          <el-icon :size="32"><DataAnalysis /></el-icon>
        </div>
        <div class="stat-content">
          <div class="stat-value">{{ data.systemUsage }}</div>
          <div class="stat-label">系统利用率</div>
        </div>
      </div>
    </div>
    
    <div class="info-section">
      <div class="info-card">
        <div class="info-header">
          <el-icon><InfoFilled /></el-icon>
          <h3>系统简介</h3>
        </div>
        <p>本系统为企业提供全面的管理解决方案，包括员工管理、部门管理、文章管理等功能，帮助企业提高工作效率，优化管理流程。</p>
      </div>
      
      <div class="info-card">
        <div class="info-header">
          <el-icon><Guide /></el-icon>
          <h3>使用原则</h3>
        </div>
        <p>简化操作流程，提高用户体验，确保数据安全，为企业管理提供便捷高效的服务支持。</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, onMounted, computed } from 'vue';
import { HomeFilled, User, Document, OfficeBuilding, DataAnalysis, InfoFilled, Guide } from '@element-plus/icons-vue';
import request from "@/untils/request.js";
import { useRouter } from 'vue-router';

const router = useRouter();
const userInfo = JSON.parse(localStorage.getItem('xm-pro-user') || '{}');

// 检查用户权限
const canAccessEmployee = computed(() => userInfo.role === 'ADM');
const canAccessArticle = computed(() => userInfo.role === 'ADM');
const canAccessDepartment = computed(() => userInfo.role === 'ADM');

const data = reactive({
  employeeCount: 0,
  articleCount: 0,
  departmentCount: 0,
  systemUsage: '0%'
});

// 导航到指定页面
const navigateTo = (path) => {
  router.push(path);
};

// 获取员工总数
const fetchEmployeeCount = () => {
  request.get('/employee/selectAll').then(res => {
    if (res.code === '200') {
      data.employeeCount = res.data.length;
    }
  });
};

// 获取文章总数
const fetchArticleCount = () => {
  request.get('/article/selectAll').then(res => {
    if (res.code === '200') {
      data.articleCount = res.data.length;
    }
  });
};

// 获取部门总数
const fetchDepartmentCount = () => {
  request.get('/department/selectAll').then(res => {
    if (res.code === '200') {
      data.departmentCount = res.data.length;
      
      // 计算系统利用率（这里用一个简单的计算方式：部门数量与员工数量的比例）
      if (data.employeeCount > 0) {
        const ratio = Math.min(0.95, data.departmentCount / (data.employeeCount * 0.2 + 1));
        data.systemUsage = Math.round(ratio * 100) + '%';
      } else {
        data.systemUsage = '0%';
      }
    }
  });
};

// 页面加载时获取数据
onMounted(() => {
  fetchEmployeeCount();
  fetchArticleCount();
  fetchDepartmentCount();
});
</script>

<style scoped>
.home-container {
  padding: 20px;
  animation: fadeIn 0.5s ease-in-out;
}

@keyframes fadeIn {
  from { opacity: 0; transform: translateY(20px); }
  to { opacity: 1; transform: translateY(0); }
}

.welcome-section {
  text-align: center;
  margin-bottom: 40px;
  padding: 40px 0;
  background: linear-gradient(135deg, #4b6cb7 0%, #182848 100%);
  border-radius: 12px;
  color: white;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
}

.welcome-icon {
  margin-bottom: 20px;
  animation: pulse 2s infinite;
}

@keyframes pulse {
  0% { transform: scale(1); }
  50% { transform: scale(1.05); }
  100% { transform: scale(1); }
}

.welcome-section h1 {
  font-size: 32px;
  margin-bottom: 10px;
  font-weight: 600;
}

.subtitle {
  font-size: 18px;
  opacity: 0.9;
}

.dashboard-stats {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
  gap: 20px;
  margin-bottom: 30px;
}

.stat-card {
  background: white;
  border-radius: 10px;
  padding: 20px;
  display: flex;
  align-items: center;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  cursor: pointer;
}

.stat-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
  background-color: #f9fafc;
}

.stat-icon {
  background: #f0f5ff;
  padding: 15px;
  border-radius: 10px;
  color: #3c7fff;
  margin-right: 15px;
}

.stat-content {
  flex: 1;
}

.stat-value {
  font-size: 28px;
  font-weight: 700;
  color: #333;
  line-height: 1.2;
}

.stat-label {
  font-size: 14px;
  color: #666;
  margin-top: 5px;
}

.info-section {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(400px, 1fr));
  gap: 20px;
}

.info-card {
  background: white;
  border-radius: 10px;
  padding: 25px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.info-header {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
  color: #3c7fff;
}

.info-header h3 {
  margin: 0 0 0 10px;
  font-size: 18px;
  font-weight: 600;
}

.info-card p {
  color: #555;
  line-height: 1.6;
  margin: 0;
}
</style>