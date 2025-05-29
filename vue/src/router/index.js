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
