<template>
  <div>
    <div style="margin: 10px 0">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/manager/book' }">图书管理</el-breadcrumb-item>
        <el-breadcrumb-item>添加图书</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-card style="width: 50%; margin: 10px auto">
      <el-form label-width="120px" size="normal" :model="form" :rules="rules" ref="form">
        <el-form-item label="ISBN" prop="isbn">
          <el-input v-model="form.isbn" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="书名" prop="title">
          <el-input v-model="form.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="form.author" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="出版社">
          <el-input v-model="form.publisher" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="出版日期">
          <el-date-picker type="date" placeholder="选择日期" v-model="form.publishDate" style="width: 100%"></el-date-picker>
        </el-form-item>
        <el-form-item label="分类" prop="categoryId">
          <el-select v-model="form.categoryId" placeholder="请选择分类" style="width: 100%">
            <el-option v-for="item in categories" :key="item.categoryId" :label="item.name" :value="item.categoryId"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="form.price" autocomplete="off" type="number"></el-input>
        </el-form-item>
        <el-form-item label="馆藏位置">
          <el-input v-model="form.location" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="form.status" placeholder="请选择状态" style="width: 100%">
            <el-option label="可借" :value="1"></el-option>
            <el-option label="已借出" :value="0"></el-option>
            <el-option label="损坏" :value="2"></el-option>
            <el-option label="丢失" :value="3"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="描述">
          <el-input type="textarea" v-model="form.description" :rows="3"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="save">保存</el-button>
          <el-button @click="$router.push('/manager/book')">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import request from "@/untils/request";

export default {
  name: "BookAdd",
  data() {
    return {
      form: {
        isbn: '',
        title: '',
        author: '',
        publisher: '',
        publishDate: '',
        categoryId: '',
        price: '',
        description: '',
        location: '',
        status: 1
      },
      categories: [],
      rules: {
        isbn: [
          { required: true, message: '请输入ISBN', trigger: 'blur' }
        ],
        title: [
          { required: true, message: '请输入书名', trigger: 'blur' }
        ],
        author: [
          { required: true, message: '请输入作者', trigger: 'blur' }
        ],
        categoryId: [
          { required: true, message: '请选择分类', trigger: 'change' }
        ]
      }
    }
  },
  created() {
    this.loadCategories()
  },
  methods: {
    loadCategories() {
      request.get("/category/list").then(res => {
        this.categories = res.data
      })
    },
    save() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          request.post("/book", this.form).then(res => {
            if (res.code === '0') {
              this.$message({
                type: "success",
                message: "添加成功"
              })
              this.$router.push("/manager/book")
            } else {
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style> 