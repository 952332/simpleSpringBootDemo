import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
Vue.use(VueRouter)

const routes = [
    // 主页
    {
        path: '/',
        name: 'index',
        component: index,
        meta: {
            index: 0,
            title: '首页'
        }
    },

    // 登录
    {
        path: '/login',
        name: 'login',
        component: login,
        meta: {
            index: 0,
            title: '登录'
        }
    },

    	
	
    // 忘记密码
    {
        path: '/forgot',
        name: "forgot",
        component: forgot,
        meta: {
            index: 0,
            title: '忘记密码'
        }
    },

    // 修改密码
    {
        path: '/user/password',
        name: "password",
        component: () => import("../views/user/password.vue"),
        meta: {
            index: 0,
            title: '修改密码'
        }
    },

    // 视频播放页
    {
        path: "/media/video",
        name: "video",
        component: () => import('../views/media/video.vue'),
        meta: {
            index: 0,
            title: "视频"
        }
    },

    // 音频播放页
    {
        path: "/media/audio",
        name: "audio",
        component: () => import('../views/media/audio.vue'),
        meta: {
            index: 0,
            title: "音频"
        }
    },

            // 权限路由
        {
            path: '/auth/table',
            name: 'auth_table',
            component: () => import('../views/auth/table.vue'),
            meta: {
                index: 0,
                title: '权限列表'
            }
        },
        {
            path: '/auth/view',
            name: 'auth_view',
            component: () => import('../views/auth/view.vue'),
            meta: {
                index: 0,
                title: '权限详情'
            }
        },
    
    
                // 轮播图路由
        {
            path: '/slides/table',
            name: 'slides_table',
            component: () => import('../views/slides/table.vue'),
            meta: {
                index: 0,
                title: '轮播图列表'
            }
        },
        {
            path: '/slides/view',
            name: 'slides_view',
            component: () => import('../views/slides/view.vue'),
            meta: {
                index: 0,
                title: '轮播图详情'
            }
        },
                    // 文章路由
            {
                path: '/article/table',
                name: 'article_table',
                component: () => import('../views/article/table.vue'),
                meta: {
                    index: 0,
                    title: '新闻资讯列表'
                }
            },
            {
                path: '/article/view',
                name: 'article_view',
                component: () => import('../views/article/view.vue'),
                meta: {
                    index: 0,
                    title: '新闻资讯详情'
                }
            },

            // 文章分类路由
            {
                path: '/article_type/table',
                name: 'article_type_table',
                component: () => import('../views/article_type/table.vue'),
                meta: {
                    index: 0,
                    title: '新闻资讯分类列表'
                }
            },
            {
                path: '/article_type/view',
                name: 'article_type_view',
                component: () => import('../views/article_type/view.vue'),
                meta: {
                    index: 0,
                    title: '新闻资讯分类详情'
                }
            },
                            
    
    
            // 公告路由
        {
            path: '/notice/table',
            name: 'notice_table',
            component: () => import('../views/notice/table.vue'),
            meta: {
                index: 0,
                title: '公告通知列表'
            }
        },
        {
            path: '/notice/view',
            name: 'notice_view',
            component: () => import('../views/notice/view.vue'),
            meta: {
                index: 0,
                title: '公告通知详情'
            }
        },
            	    
            // 评论路由
        {
            path: '/comment/table',
            name: 'comment_table',
            component: () => import('../views/comment/table.vue'),
            meta: {
                index: 0,
                title: '评论列表'
            }
        },
        {
            path: '/comment/view',
            name: 'comment_view',
            component: () => import('../views/comment/view.vue'),
            meta: {
                index: 0,
                title: '评论详情'
            }
        },
        	            // 普通用户路由
        {
            path: '/ordinary_user/table',
            name: 'ordinary_user_table',
            component: () => import('../views/ordinary_user/table.vue'),
            meta: {
                index: 0,
                title: '普通用户列表'
            }
        },
        {
            path: '/ordinary_user/view',
            name: 'ordinary_user_view',
            component: () => import('../views/ordinary_user/view.vue'),
            meta: {
                index: 0,
                title: '普通用户详情'
            }
        },
						            // 提醒通知路由
        {
            path: '/reminder_notifications/table',
            name: 'reminder_notifications_table',
            component: () => import('../views/reminder_notifications/table.vue'),
            meta: {
                index: 0,
                title: '提醒通知列表'
            }
        },
        {
            path: '/reminder_notifications/view',
            name: 'reminder_notifications_view',
            component: () => import('../views/reminder_notifications/view.vue'),
            meta: {
                index: 0,
                title: '提醒通知详情'
            }
        },
						            // 消息发送路由
        {
            path: '/message_sending/table',
            name: 'message_sending_table',
            component: () => import('../views/message_sending/table.vue'),
            meta: {
                index: 0,
                title: '消息发送列表'
            }
        },
        {
            path: '/message_sending/view',
            name: 'message_sending_view',
            component: () => import('../views/message_sending/view.vue'),
            meta: {
                index: 0,
                title: '消息发送详情'
            }
        },
						            // 公众号推送路由
        {
            path: '/public_number_push/table',
            name: 'public_number_push_table',
            component: () => import('../views/public_number_push/table.vue'),
            meta: {
                index: 0,
                title: '公众号推送列表'
            }
        },
        {
            path: '/public_number_push/view',
            name: 'public_number_push_view',
            component: () => import('../views/public_number_push/view.vue'),
            meta: {
                index: 0,
                title: '公众号推送详情'
            }
        },
						    			// 操作日志路由
		{
		    path: '/operation_log/table',
		    name: 'operation_log_table',
		    component: () => import('../views/operation_log/table.vue'),
		    meta: {
		        index: 0,
		        title: '操作日志'
		    }
		},
	    // 用户路由
    {
        path: '/user/table',
        name: 'user_table',
        component: () => import('../views/user/table.vue'),
        meta: {
            index: 0,
            title: '用户列表'
        }
    },
    {
        path: '/user/view',
        name: 'user_view',
        component: () => import('../views/user/view.vue'),
        meta: {
            index: 0,
            title: '用户详情'
        }
    },
    {
        path: '/user/info',
        name: 'user_info',
        component: () => import('../views/user/info.vue'),
        meta: {
            index: 0,
            title: '个人信息'
        }
    },
    // 用户组路由
    {
        path: '/user_group/table',
        name: 'user_group_table',
        component: () => import('../views/user_group/table.vue'),
        meta: {
            index: 0,
            title: '用户组列表'
        }
    },
    {
        path: '/user_group/view',
        name: 'user_group_view',
        component: () => import('../views/user_group/view.vue'),
        meta: {
            index: 0,
            title: '用户组详情'
        }
    }
]

const router = new VueRouter({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    let token = to.query.token;
    if (token) {
        $.db.set("token", token, 120);
    }
    next();
})

router.afterEach((to, from, next) => {
    let title = "多平台消息管理系统-admin";
    document.title = title;
})

export default router
