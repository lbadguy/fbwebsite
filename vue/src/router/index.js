import {createRouter, createWebHistory} from 'vue-router'


const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {path: '/', redirect: '/login'},
        {
            path: '/manager',
            component: () => import('../views/Manager.vue'),
            // 添加子路由配置
            children: [
                {
                    path: 'home', // 子路由路径会自动继承父路径 (最终路径为 /test)
                    name: 'home',
                    meta: {title: '主页'},
                    component: () => import('../views/Home.vue')
                },
                {
                    path: 'test', // 子路由路径会自动继承父路径 (最终路径为 /test)
                    name: 'test',
                    meta: {title: '测试数据页面'},
                    component: () => import('../views/Test.vue')
                },
                {
                    path: 'data', // 子路由路径会自动继承父路径 (最终路径为 /test)
                    name: 'data',
                    meta: {title: '数据展示页面'},
                    component: () => import('../views/Data.vue')
                }, {
                    path: 'employee', // 子路由路径会自动继承父路径 (最终路径为 /test)
                    name: 'employee',
                    meta: {title: '员工信息页面'},
                    component: () => import('../views/Employee.vue')
                },
                {
                    path: 'admin', // 子路由路径会自动继承父路径 (最终路径为 /test)
                    name: 'admin',
                    meta: {title: '管理员信息页面'},
                    component: () => import('../views/Admin.vue')
                },
                {
                    path: 'person', // 子路由路径会自动继承父路径 (最终路径为 /test)
                    name: 'person',
                    meta: {title: '个人信息页面'},
                    component: () => import('../views/Person.vue')
                },
                {
                    path: 'password', // 子路由路径会自动继承父路径 (最终路径为 /test)
                    name: 'password',
                    meta: {title: '修改密码页面'},
                    component: () => import('../views/Password.vue')
                },
                {
                    path: 'article', // 子路由路径会自动继承父路径 (最终路径为 /test)
                    name: 'article',
                    meta: {title: '文章页面'},
                    component: () => import('../views/Article.vue')
                }, {
                    path: 'department', // 子路由路径会自动继承父路径 (最终路径为 /test)
                    name: 'department',
                    meta: {title: '部门信息'},
                    component: () => import('../views/Department.vue')
                },
                // 图书管理系统相关路由
                {
                    path: 'book',
                    name: 'Book',
                    meta: {title: '图书管理'},
                    component: () => import('../views/book/Book.vue')
                },
                {
                    path: 'book/add',
                    name: 'BookAdd',
                    meta: {title: '添加图书'},
                    component: () => import('../views/book/BookAdd.vue')
                },
                {
                    path: 'book/edit/:bookId',
                    name: 'BookEdit',
                    meta: {title: '编辑图书'},
                    component: () => import('../views/book/BookEdit.vue')
                },
                {
                    path: 'reader',
                    name: 'Reader',
                    meta: {title: '读者管理'},
                    component: () => import('../views/reader/Reader.vue')
                },
                {
                    path: 'reader/add',
                    name: 'ReaderAdd',
                    meta: {title: '添加读者'},
                    component: () => import('../views/reader/ReaderAdd.vue')
                },
                {
                    path: 'reader/edit/:readerId',
                    name: 'ReaderEdit',
                    meta: {title: '编辑读者'},
                    component: () => import('../views/reader/ReaderEdit.vue')
                },
                {
                    path: 'borrow',
                    name: 'Borrow',
                    meta: {title: '借阅管理'},
                    component: () => import('../views/borrow/Borrow.vue')
                },
                {
                    path: 'borrow/add',
                    name: 'BorrowAdd',
                    meta: {title: '添加借阅'},
                    component: () => import('../views/borrow/BorrowAdd.vue')
                },
                {
                    path: 'borrow/return',
                    name: 'BorrowReturn',
                    meta: {title: '归还图书'},
                    component: () => import('../views/borrow/BorrowReturn.vue')
                },
                {
                    path: 'category',
                    name: 'Category',
                    meta: {title: '分类管理'},
                    component: () => import('../views/category/Category.vue')
                },
            ]
        },
        {
            path: '/login',
            name: 'login',
            meta: {title: '登录页面'},
            component: () => import('../views/Login.vue')
        },
        {
            path: '/register',
            name: 'register',
            meta: {title: '欢迎注册'},
            component: () => import('../views/Register.vue')
        },
    ]
})

router.beforeEach((to, from, next) => {
        document.title = to.meta.title
        next()
    }
)
export default router
