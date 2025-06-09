<template>
  <div>
    <el-card shadow="hover" :body-style="{padding: '20px'}">
      <template #header>
        <div class="card-header">
          <span>图书借阅</span>
        </div>
      </template>
      
      <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
        <!-- 读者选择 -->
        <el-form-item label="读者信息" prop="readerId">
          <el-row :gutter="10">
            <el-col :span="16">
              <el-select v-model="form.readerId" filterable placeholder="请选择读者" style="width: 100%;" @change="handleReaderChange">
                <el-option
                  v-for="item in readers"
                  :key="item.readerId"
                  :label="`${item.name} (${item.cardId})`"
                  :value="item.readerId"
                >
                  <div style="display: flex; justify-content: space-between;">
                    <span>{{ item.name }}</span>
                    <span style="color: #8492a6; font-size: 13px">{{ item.cardId }}</span>
                  </div>
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="8">
              <el-input v-model="readerCardIdInput" placeholder="输入借书证号查询" @keyup.enter="searchReader"></el-input>
            </el-col>
          </el-row>
        </el-form-item>
        
        <!-- 已选读者信息展示 -->
        <div v-if="selectedReader" style="margin-bottom: 20px; padding: 10px; border: 1px solid #ebeef5; border-radius: 4px; background-color: #f5f7fa;">
          <el-row :gutter="20">
            <el-col :span="8">
              <div class="info-item">
                <span class="label">读者姓名：</span>
                <span class="value">{{ selectedReader.name }}</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="info-item">
                <span class="label">借书证号：</span>
                <span class="value">{{ selectedReader.cardId }}</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="info-item">
                <span class="label">读者类型：</span>
                <span class="value">
                  <el-tag v-if="selectedReader.type === 1" type="success" size="small">学生</el-tag>
                  <el-tag v-if="selectedReader.type === 2" type="warning" size="small">教师</el-tag>
                  <el-tag v-if="selectedReader.type === 3" type="info" size="small">其他</el-tag>
                </span>
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="20" style="margin-top: 10px;">
            <el-col :span="8">
              <div class="info-item">
                <span class="label">电话：</span>
                <span class="value">{{ selectedReader.phone || '未设置' }}</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="info-item">
                <span class="label">可借数量：</span>
                <span class="value">{{ selectedReader.maxBorrowNum || 5 }}</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="info-item">
                <span class="label">状态：</span>
                <span class="value">
                  <el-tag v-if="selectedReader.status === 1" type="success" size="small">正常</el-tag>
                  <el-tag v-if="selectedReader.status === 0" type="danger" size="small">禁用</el-tag>
                </span>
              </div>
            </el-col>
          </el-row>
        </div>
        
        <!-- 图书选择 -->
        <el-form-item label="图书信息" prop="bookId">
          <el-row :gutter="10">
            <el-col :span="16">
              <el-select v-model="form.bookId" filterable placeholder="请选择图书" style="width: 100%;" @change="handleBookChange">
                <el-option
                  v-for="item in books"
                  :key="item.bookId"
                  :label="`${item.title} (${item.isbn})`"
                  :value="item.bookId"
                >
                  <div style="display: flex; justify-content: space-between;">
                    <span>{{ item.title }}</span>
                    <span style="color: #8492a6; font-size: 13px">{{ item.isbn }}</span>
                  </div>
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="8">
              <el-input v-model="bookIsbnInput" placeholder="输入ISBN查询" @keyup.enter="searchBook"></el-input>
            </el-col>
          </el-row>
        </el-form-item>
        
        <!-- 已选图书信息展示 -->
        <div v-if="selectedBook" style="margin-bottom: 20px; padding: 10px; border: 1px solid #ebeef5; border-radius: 4px; background-color: #f5f7fa;">
          <el-row :gutter="20">
            <el-col :span="8">
              <div class="info-item">
                <span class="label">图书名称：</span>
                <span class="value">{{ selectedBook.title }}</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="info-item">
                <span class="label">ISBN：</span>
                <span class="value">{{ selectedBook.isbn }}</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="info-item">
                <span class="label">作者：</span>
                <span class="value">{{ selectedBook.author }}</span>
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="20" style="margin-top: 10px;">
            <el-col :span="8">
              <div class="info-item">
                <span class="label">出版社：</span>
                <span class="value">{{ selectedBook.publisher || '未知' }}</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="info-item">
                <span class="label">分类：</span>
                <span class="value">{{ selectedBook.categoryName || '未分类' }}</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="info-item">
                <span class="label">状态：</span>
                <span class="value">
                  <el-tag v-if="selectedBook.status === 1" type="success" size="small">可借</el-tag>
                  <el-tag v-if="selectedBook.status === 0" type="danger" size="small">不可借</el-tag>
                  <el-tag v-if="selectedBook.status === 2" type="warning" size="small">已借出</el-tag>
                </span>
              </div>
            </el-col>
          </el-row>
        </div>
        
        <!-- 借阅日期和归还日期 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="借阅日期" prop="borrowDate">
              <el-date-picker 
                v-model="form.borrowDate" 
                type="date" 
                placeholder="选择借阅日期"
                style="width: 100%;"
                value-format="YYYY-MM-DD"
                :disabled-date="disabledBeforeToday"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="应还日期" prop="dueDate">
              <el-date-picker 
                v-model="form.dueDate" 
                type="date" 
                placeholder="选择应还日期"
                style="width: 100%;"
                value-format="YYYY-MM-DD"
                :disabled-date="disabledBeforeBorrowDate"
              ></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        
        <el-form-item>
          <el-button type="primary" @click="submitForm" :disabled="submitDisabled">办理借阅</el-button>
          <el-button @click="$router.push('/manager/borrow')">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import request from '@/untils/request';
import { getCurrentInstance } from 'vue';

export default {
  name: 'BorrowAdd',
  data() {
    return {
      form: {
        readerId: null,
        bookId: null,
        borrowDate: new Date().toISOString().slice(0, 10),
        dueDate: new Date(Date.now() + 30 * 24 * 60 * 60 * 1000).toISOString().slice(0, 10),
        operatorId: null
      },
      rules: {
        readerId: [
          { required: true, message: '请选择读者', trigger: 'change' }
        ],
        bookId: [
          { required: true, message: '请选择图书', trigger: 'change' }
        ],
        borrowDate: [
          { required: true, message: '请选择借阅日期', trigger: 'change' }
        ],
        dueDate: [
          { required: true, message: '请选择应还日期', trigger: 'change' }
        ]
      },
      readers: [],
      books: [],
      selectedReader: null,
      selectedBook: null,
      readerCardIdInput: '',
      bookIsbnInput: '',
      currentUser: null
    };
  },
  computed: {
    submitDisabled() {
      if (!this.selectedReader || !this.selectedBook) return true;
      if (this.selectedReader.status !== 1) return true;
      if (this.selectedBook.status !== 1) return true;
      return false;
    }
  },
  created() {
    this.loadReaders();
    this.loadBooks();
    this.getCurrentUser();
  },
  methods: {
    // 加载读者列表
    loadReaders() {
      request.get('/reader/list').then(res => {
        if (res.code === '0') {
          this.readers = res.data;
        }
      });
    },
    // 加载图书列表
    loadBooks() {
      request.get('/book/available').then(res => {
        if (res.code === '0') {
          this.books = res.data;
        }
      });
    },
    // 获取当前登录用户
    getCurrentUser() {
      const userInfo = localStorage.getItem('userInfo');
      if (userInfo) {
        this.currentUser = JSON.parse(userInfo);
        this.form.operatorId = this.currentUser.userId;
      }
    },
    // 根据借书证号查询读者
    searchReader() {
      if (!this.readerCardIdInput) return;
      
      request.get('/reader/card/' + this.readerCardIdInput).then(res => {
        if (res.code === '0') {
          this.form.readerId = res.data.readerId;
          this.selectedReader = res.data;
          this.$message.success('读者查询成功');
        } else {
          this.$message.error(res.msg || '未找到读者信息');
        }
      });
    },
    // 根据ISBN查询图书
    searchBook() {
      if (!this.bookIsbnInput) return;
      
      request.get('/book/isbn/' + this.bookIsbnInput).then(res => {
        if (res.code === '0') {
          this.form.bookId = res.data.bookId;
          this.selectedBook = res.data;
          this.$message.success('图书查询成功');
        } else {
          this.$message.error(res.msg || '未找到图书信息');
        }
      });
    },
    // 读者选择变更
    handleReaderChange(readerId) {
      const reader = this.readers.find(r => r.readerId === readerId);
      if (reader) {
        this.selectedReader = reader;
      }
    },
    // 图书选择变更
    handleBookChange(bookId) {
      const book = this.books.find(b => b.bookId === bookId);
      if (book) {
        this.selectedBook = book;
      }
    },
    // 禁用今天之前的日期
    disabledBeforeToday(time) {
      return time.getTime() < Date.now() - 24 * 60 * 60 * 1000;
    },
    // 禁用借阅日期之前的归还日期
    disabledBeforeBorrowDate(time) {
      if (!this.form.borrowDate) return false;
      const borrowDate = new Date(this.form.borrowDate);
      return time.getTime() < borrowDate.getTime();
    },
    // 提交表单
    submitForm() {
      this.$refs.formRef.validate(valid => {
        if (valid) {
          if (this.selectedBook.status !== 1) {
            this.$message.error('该图书不可借阅');
            return;
          }
          
          if (this.selectedReader.status !== 1) {
            this.$message.error('该读者状态异常，不可借阅');
            return;
          }
          
          request.post('/borrow/add', this.form).then(res => {
            if (res.code === '0') {
              this.$message.success('借阅成功');
              this.$router.push('/manager/borrow');
            } else {
              this.$message.error(res.msg || '借阅失败');
            }
          });
        } else {
          return false;
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
.info-item {
  display: flex;
  align-items: center;
}
.label {
  color: #606266;
  font-weight: bold;
  margin-right: 5px;
}
.value {
  color: #303133;
}
</style> 