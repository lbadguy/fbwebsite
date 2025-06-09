<template>
  <div class="home-container">
    <!-- 欢迎区域 -->
    <div class="welcome-section">
      <div class="welcome-content">
        <h1>欢迎使用后台管理系统</h1>
        <p>{{ greeting }}，<strong>{{ username }}</strong>，祝您工作愉快！</p>
      </div>
      <div class="welcome-image">
        <img src="@/assets/121_传统.png" alt="系统Logo">
      </div>
    </div>



    <!-- 系统介绍 -->
    <div class="system-intro">
      <el-card shadow="hover">
        <template #header>
          <div class="card-header">
            <h3><el-icon><InfoFilled /></el-icon> 系统介绍</h3>
          </div>
        </template>
        <div class="intro-content">
          <p>本系统是一个综合性的企业管理平台，集成了人员管理、数据分析、文档管理等多种功能，旨在提高企业运营效率和管理水平。</p>
          <div class="features">
            <div class="feature-item">
              <el-icon><DataAnalysis /></el-icon>
              <span>数据分析</span>
            </div>
            <div class="feature-item">
              <el-icon><User /></el-icon>
              <span>用户管理</span>
            </div>
            <div class="feature-item">
              <el-icon><Document /></el-icon>
              <span>文档管理</span>
            </div>
            <div class="feature-item">
              <el-icon><Reading /></el-icon>
              <span>图书管理</span>
            </div>
          </div>
        </div>
      </el-card>
    </div>

    <!-- 快速入口 -->
    <div class="quick-access">
      <el-card shadow="hover">
        <template #header>
          <div class="card-header">
            <h3><el-icon><Link /></el-icon> 快速入口</h3>
          </div>
        </template>
        <div class="access-links">
          <el-button type="primary" @click="navigateTo('/manager/data')">
            <el-icon><DataAnalysis /></el-icon> 数据统计
          </el-button>
          <el-button type="success" @click="navigateTo('/manager/book')">
            <el-icon><Notebook /></el-icon> 图书管理
          </el-button>
          <el-button type="warning" @click="navigateTo('/manager/borrow')">
            <el-icon><DocumentCopy /></el-icon> 借阅管理
          </el-button>
          <el-button type="info" @click="navigateTo('/manager/person')">
            <el-icon><UserFilled /></el-icon> 个人信息
          </el-button>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import request from '@/untils/request';
import {
  User, 
  UserFilled, 
  Document, 
  DataAnalysis, 
  Reading, 
  Notebook, 
  DocumentCopy,
  InfoFilled,
  Link
} from '@element-plus/icons-vue';
import { ElMessage } from 'element-plus';

const router = useRouter();

// 获取用户信息
const userInfo = ref(JSON.parse(localStorage.getItem('xm-pro-user') || '{}'));
const username = computed(() => userInfo.value.name || '访客');

// 根据时间生成问候语
const greeting = computed(() => {
  const hour = new Date().getHours();
  if (hour < 6) return '凌晨好';
  if (hour < 9) return '早上好';
  if (hour < 12) return '上午好';
  if (hour < 14) return '中午好';
  if (hour < 18) return '下午好';
  if (hour < 22) return '晚上好';
  return '夜深了';
});

// 统计卡片数据
const statCards = ref([
  { label: '图书总数', value: '0', icon: 'Notebook', loading: true },
  { label: '借阅人数', value: '0', icon: 'User', loading: true },
  { label: '待归还', value: '0', icon: 'DocumentCopy', loading: true },
  { label: '今日访问', value: '0', icon: 'DataAnalysis', loading: true }
]);

// 获取统计数据
const fetchStatistics = async () => {
  try {
    // 获取图书总数
    const bookRes = await request.get('/book/list');
    if (bookRes.code === '0') {
      statCards.value[0].value = bookRes.data.length;
      statCards.value[0].loading = false;
    }

    // 获取借阅人数
    const readerRes = await request.get('/reader/list');
    if (readerRes.code === '0') {
      statCards.value[1].value = readerRes.data.length;
      statCards.value[1].loading = false;
    }

    // 获取待归还数量
    const borrowRes = await request.get('/borrow/list');
    if (borrowRes.code === '0') {
      // 过滤出未归还的借阅记录
      const notReturnedCount = borrowRes.data.filter(item => item.status === 1).length;
      statCards.value[2].value = notReturnedCount;
      statCards.value[2].loading = false;
    }

    // 由于没有访问量API，设置一个随机数
    const randomVisits = Math.floor(Math.random() * 100) + 50;
    statCards.value[3].value = randomVisits;
    statCards.value[3].loading = false;
    
  } catch (error) {
    console.error('获取统计数据失败', error);
    ElMessage.error('获取统计数据失败');
    
    // 如果API调用失败，设置所有卡片的loading为false
    statCards.value.forEach(card => {
      card.loading = false;
    });
  }
};

// 页面跳转
const navigateTo = (path) => {
  router.push(path);
};

// 初始化函数
onMounted(() => {
  // 获取实时统计数据
  fetchStatistics();
});
</script>

<style scoped>
.home-container {
  padding: 20px;
}

.welcome-section {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: linear-gradient(135deg, #3c7fff 0%, #5a94ff 100%);
  border-radius: 12px;
  padding: 30px;
  color: white;
  margin-bottom: 20px;
  box-shadow: 0 4px 12px rgba(60, 127, 255, 0.2);
}

.welcome-content h1 {
  font-size: 28px;
  margin: 0 0 10px 0;
}

.welcome-content p {
  font-size: 16px;
  margin: 0;
  opacity: 0.9;
}

.welcome-image img {
  width: 80px;
  height: 80px;
  object-fit: contain;
  background-color: rgba(255, 255, 255, 0.2);
  padding: 15px;
  border-radius: 50%;
}

.stat-cards {
  margin-bottom: 20px;
}

.stat-card {
  border-radius: 10px;
  transition: all 0.3s;
  height: 120px;
  display: flex;
  align-items: center;
}

.stat-card:hover {
  transform: translateY(-5px);
}

.stat-card-content {
  display: flex;
  align-items: center;
}

.stat-icon {
  padding: 15px;
  border-radius: 10px;
  margin-right: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.stat-card-0 .stat-icon {
  background-color: rgba(64, 158, 255, 0.1);
  color: #409EFF;
}

.stat-card-1 .stat-icon {
  background-color: rgba(103, 194, 58, 0.1);
  color: #67C23A;
}

.stat-card-2 .stat-icon {
  background-color: rgba(230, 162, 60, 0.1);
  color: #E6A23C;
}

.stat-card-3 .stat-icon {
  background-color: rgba(245, 108, 108, 0.1);
  color: #F56C6C;
}

.stat-info {
  display: flex;
  flex-direction: column;
}

.stat-value {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 5px;
}

.stat-label {
  font-size: 14px;
  color: #909399;
}

.system-intro, .quick-access {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  align-items: center;
}

.card-header h3 {
  margin: 0;
  font-size: 18px;
  display: flex;
  align-items: center;
}

.card-header h3 .el-icon {
  margin-right: 8px;
}

.intro-content p {
  margin-top: 0;
  line-height: 1.6;
}

.features {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
}

.feature-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 15px;
  border-radius: 8px;
  background-color: #f5f7fa;
  min-width: 100px;
}

.feature-item .el-icon {
  font-size: 28px;
  margin-bottom: 8px;
  color: #3c7fff;
}

.access-links {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  gap: 10px;
}

.access-links .el-button {
  min-width: 120px;
}

@media (max-width: 768px) {
  .stat-cards .el-col {
    width: 50%;
    margin-bottom: 15px;
  }
  
  .welcome-section {
    flex-direction: column;
    text-align: center;
  }
  
  .welcome-image {
    margin-top: 20px;
  }
  
  .features {
    flex-wrap: wrap;
    gap: 10px;
  }
  
  .feature-item {
    min-width: 40%;
    margin-bottom: 10px;
  }
}
</style>