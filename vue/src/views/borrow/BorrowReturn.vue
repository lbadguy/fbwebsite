<template>
  <div>
    <el-card shadow="hover" :body-style="{padding: '20px'}">
      <template #header>
        <div class="card-header">
          <span>图书归还</span>
        </div>
      </template>
      
      <!-- 搜索区域 -->
      <div style="margin-bottom: 20px;">
        <el-row :gutter="20">
          <el-col :span="10">
            <el-input v-model="searchBookValue" placeholder="输入ISBN、图书名称或借阅ID查询" clearable @keyup.enter="searchBorrow">
              <template #append>
                <el-button @click="searchBorrow">
                  <el-icon><Search /></el-icon>
                </el-button>
              </template>
            </el-input>
          </el-col>
          <el-col :span="10">
            <el-input v-model="searchReaderValue" placeholder="输入借书证号或读者姓名查询" clearable @keyup.enter="searchBorrowByReader">
              <template #append>
                <el-button @click="searchBorrowByReader">
                  <el-icon><Search /></el-icon>
                </el-button>
              </template>
            </el-input>
          </el-col>
          <el-col :span="4">
            <el-button type="success" @click="scanCode">扫码查询</el-button>
          </el-col>
        </el-row>
      </div>
      
      <!-- 借阅信息展示 -->
      <div v-if="currentBorrow" class="borrow-info">
        <h3 style="margin-top: 0; margin-bottom: 15px;">借阅信息</h3>
        
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="info-item">
              <span class="label">借阅ID：</span>
              <span class="value">{{ currentBorrow.borrowId }}</span>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="info-item">
              <span class="label">借阅日期：</span>
              <span class="value">{{ currentBorrow.borrowDate }}</span>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="info-item">
              <span class="label">应还日期：</span>
              <span class="value">
                {{ currentBorrow.dueDate }}
                <el-tag v-if="isOverdue" type="danger" size="small">已逾期</el-tag>
              </span>
            </div>
          </el-col>
        </el-row>
        
        <el-divider content-position="left">图书信息</el-divider>
        
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="info-item">
              <span class="label">图书名称：</span>
              <span class="value">{{ currentBorrow.bookTitle }}</span>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="info-item">
              <span class="label">ISBN：</span>
              <span class="value">{{ currentBorrow.isbn }}</span>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="info-item">
              <span class="label">状态：</span>
              <span class="value">
                <el-tag v-if="currentBorrow.status === 1" type="primary" size="small">已借出</el-tag>
                <el-tag v-if="currentBorrow.status === 2" type="success" size="small">已归还</el-tag>
                <el-tag v-if="currentBorrow.status === 3" type="warning" size="small">已续借</el-tag>
                <el-tag v-if="currentBorrow.status === 4" type="danger" size="small">逾期未还</el-tag>
              </span>
            </div>
          </el-col>
        </el-row>
        
        <el-divider content-position="left">读者信息</el-divider>
        
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="info-item">
              <span class="label">读者姓名：</span>
              <span class="value">{{ currentBorrow.readerName }}</span>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="info-item">
              <span class="label">借书证号：</span>
              <span class="value">{{ currentBorrow.cardId }}</span>
            </div>
          </el-col>
        </el-row>
        
        <!-- 逾期信息 -->
        <template v-if="isOverdue">
          <el-divider content-position="left">逾期信息</el-divider>
          
          <el-row :gutter="20">
            <el-col :span="8">
              <div class="info-item">
                <span class="label">逾期天数：</span>
                <span class="value red-text">{{ overdueDays }} 天</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="info-item">
                <span class="label">罚款金额：</span>
                <span class="value red-text">¥ {{ currentBorrow.fine || calculateFine(overdueDays) }}</span>
              </div>
            </el-col>
          </el-row>
        </template>
        
        <!-- 归还操作 -->
        <div style="margin-top: 20px; text-align: center;">
          <el-button type="primary" @click="handleReturn" :disabled="currentBorrow.status === 2">
            办理归还
          </el-button>
          <el-button type="warning" @click="handleRenew" :disabled="currentBorrow.status === 2 || currentBorrow.status === 3">
            办理续借
          </el-button>
          <el-button @click="clearCurrentBorrow">清空</el-button>
        </div>
      </div>
      
      <div v-else class="empty-tips">
        <el-empty description="请输入借阅信息进行查询"></el-empty>
      </div>
      
      <!-- 归还确认对话框 -->
      <el-dialog v-model="returnDialogVisible" title="归还确认" width="30%">
        <div v-if="isOverdue" class="overdue-warning">
          <el-alert
            title="该图书已逾期，需缴纳罚款"
            type="warning"
            :closable="false"
            show-icon
          >
            <div style="margin-top: 10px">
              <p>逾期天数: {{ overdueDays }} 天</p>
              <p>罚款金额: ¥ {{ calculateFine(overdueDays) }}</p>
            </div>
          </el-alert>
        </div>
        <div style="margin-top: 15px">
          <span>确认归还图书 "{{ currentBorrow?.bookTitle }}" 吗？</span>
        </div>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="returnDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="confirmReturn">确认</el-button>
          </span>
        </template>
      </el-dialog>
      
      <!-- 续借确认对话框 -->
      <el-dialog v-model="renewDialogVisible" title="续借确认" width="30%">
        <div class="renew-form">
          <el-form :model="renewForm" label-width="100px">
            <el-form-item label="原到期日期">
              <el-input v-model="currentBorrow?.dueDate" disabled></el-input>
            </el-form-item>
            <el-form-item label="续借天数">
              <el-input-number v-model="renewForm.days" :min="1" :max="30" style="width: 100%"></el-input-number>
            </el-form-item>
            <el-form-item label="新到期日期">
              <el-input v-model="renewForm.newDueDate" disabled></el-input>
            </el-form-item>
          </el-form>
        </div>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="renewDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="confirmRenew">确认</el-button>
          </span>
        </template>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
import request from '@/untils/request';
import { Search } from '@element-plus/icons-vue';

export default {
  name: 'BorrowReturn',
  components: {
    Search
  },
  data() {
    return {
      searchBookValue: '',
      searchReaderValue: '',
      currentBorrow: null,
      returnDialogVisible: false,
      renewDialogVisible: false,
      renewForm: {
        days: 14,
        newDueDate: ''
      },
      currentUser: null
    };
  },
  computed: {
    isOverdue() {
      if (!this.currentBorrow) return false;
      const dueDate = new Date(this.currentBorrow.dueDate);
      const today = new Date();
      return dueDate < today && this.currentBorrow.status !== 2;
    },
    overdueDays() {
      if (!this.isOverdue) return 0;
      const dueDate = new Date(this.currentBorrow.dueDate);
      const today = new Date();
      const diffTime = Math.abs(today - dueDate);
      return Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    }
  },
  watch: {
    'renewForm.days'(newVal) {
      if (this.currentBorrow) {
        const dueDate = new Date(this.currentBorrow.dueDate);
        const newDueDate = new Date(dueDate);
        newDueDate.setDate(newDueDate.getDate() + newVal);
        this.renewForm.newDueDate = newDueDate.toISOString().slice(0, 10);
      }
    }
  },
  created() {
    this.getCurrentUser();
    
    // 如果URL中有借阅ID参数，则自动查询
    const borrowId = this.$route.query.borrowId;
    if (borrowId) {
      this.searchBookValue = borrowId;
      this.searchBorrow();
    }
  },
  methods: {
    // 获取当前登录用户
    getCurrentUser() {
      const userInfo = localStorage.getItem('userInfo');
      if (userInfo) {
        this.currentUser = JSON.parse(userInfo);
      }
    },
    // 搜索借阅记录
    searchBorrow() {
      if (!this.searchBookValue) {
        this.$message.warning('请输入查询条件');
        return;
      }
      
      request.get('/borrow/search', {
        params: {
          keyword: this.searchBookValue
        }
      }).then(res => {
        if (res.code === '0' && res.data) {
          this.currentBorrow = res.data;
          
          // 如果已经归还，提示用户
          if (this.currentBorrow.status === 2) {
            this.$message.info('该图书已归还');
          }
        } else {
          this.$message.error('未找到借阅记录');
          this.currentBorrow = null;
        }
      });
    },
    // 根据读者信息搜索借阅记录
    searchBorrowByReader() {
      if (!this.searchReaderValue) {
        this.$message.warning('请输入读者信息');
        return;
      }
      
      request.get('/borrow/reader/search', {
        params: {
          keyword: this.searchReaderValue
        }
      }).then(res => {
        if (res.code === '0' && res.data && res.data.length > 0) {
          // 如果有多个借阅记录，显示第一个未还的记录
          const notReturnedBorrow = res.data.find(item => item.status !== 2);
          if (notReturnedBorrow) {
            this.currentBorrow = notReturnedBorrow;
          } else {
            this.currentBorrow = res.data[0];
            this.$message.info('该读者所有图书已归还');
          }
        } else {
          this.$message.error('未找到借阅记录');
          this.currentBorrow = null;
        }
      });
    },
    // 扫码查询
    scanCode() {
      this.$message.info('请将条形码对准扫描区域');
      // 在实际应用中，这里应该调用扫码设备的API
      // 此处仅为模拟
      setTimeout(() => {
        this.$prompt('请输入条形码', '扫码', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({ value }) => {
          if (value) {
            this.searchBookValue = value;
            this.searchBorrow();
          }
        }).catch(() => {
          this.$message.info('取消扫码');
        });
      }, 500);
    },
    // 清空当前借阅记录
    clearCurrentBorrow() {
      this.currentBorrow = null;
      this.searchBookValue = '';
      this.searchReaderValue = '';
    },
    // 计算罚款金额
    calculateFine(days) {
      // 罚款规则：0.5元/天
      return (days * 0.5).toFixed(2);
    },
    // 处理归还
    handleReturn() {
      if (!this.currentBorrow) return;
      
      if (this.currentBorrow.status === 2) {
        this.$message.warning('该图书已归还');
        return;
      }
      
      this.returnDialogVisible = true;
    },
    // 确认归还
    confirmReturn() {
      const fine = this.isOverdue ? this.calculateFine(this.overdueDays) : 0;
      
      request.post('/borrow/return', {
        borrowId: this.currentBorrow.borrowId,
        operatorId: this.currentUser?.userId,
        returnDate: new Date().toISOString().slice(0, 10),
        fine: fine
      }).then(res => {
        if (res.code === '0') {
          this.$message.success('归还成功');
          this.returnDialogVisible = false;
          
          // 更新当前借阅状态
          this.currentBorrow.status = 2;
          this.currentBorrow.returnDate = new Date().toISOString().slice(0, 10);
          this.currentBorrow.fine = fine;
        } else {
          this.$message.error(res.msg || '归还失败');
        }
      });
    },
    // 处理续借
    handleRenew() {
      if (!this.currentBorrow) return;
      
      if (this.currentBorrow.status === 2) {
        this.$message.warning('该图书已归还，无法续借');
        return;
      }
      
      if (this.currentBorrow.status === 3) {
        this.$message.warning('该图书已续借过，不能再次续借');
        return;
      }
      
      if (this.isOverdue) {
        this.$message.warning('逾期图书需先归还后再借阅');
        return;
      }
      
      // 设置续借表单的初始值
      this.renewForm.days = 14;
      const dueDate = new Date(this.currentBorrow.dueDate);
      const newDueDate = new Date(dueDate);
      newDueDate.setDate(newDueDate.getDate() + this.renewForm.days);
      this.renewForm.newDueDate = newDueDate.toISOString().slice(0, 10);
      
      this.renewDialogVisible = true;
    },
    // 确认续借
    confirmRenew() {
      request.post('/borrow/renew', {
        borrowId: this.currentBorrow.borrowId,
        operatorId: this.currentUser?.userId,
        days: this.renewForm.days,
        newDueDate: this.renewForm.newDueDate
      }).then(res => {
        if (res.code === '0') {
          this.$message.success('续借成功');
          this.renewDialogVisible = false;
          
          // 更新当前借阅状态
          this.currentBorrow.status = 3;
          this.currentBorrow.dueDate = this.renewForm.newDueDate;
        } else {
          this.$message.error(res.msg || '续借失败');
        }
      });
    }
  }
};
</script>

<style scoped>
.el-card {
  margin: 20px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.borrow-info {
  background-color: #f8f9fa;
  padding: 20px;
  border-radius: 4px;
  margin-bottom: 20px;
}
.info-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}
.label {
  color: #606266;
  font-weight: bold;
  margin-right: 5px;
  min-width: 80px;
}
.value {
  color: #303133;
}
.red-text {
  color: #f56c6c;
  font-weight: bold;
}
.empty-tips {
  padding: 40px 0;
}
.overdue-warning {
  margin-bottom: 15px;
}
.renew-form {
  padding: 10px 0;
}
</style> 