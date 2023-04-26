import Vue from 'vue'
import VueRouter from 'vue-router'
import UserView from "@/views/User/UserView";
import ActivityView from "@/views/Activity/ActivityView";
import ActivityDetailView from "@/views/Activity/ActivityDetailView";
import ShopView from "@/views/Shop/ShopView";
import GoodsDetailView from "@/views/Goods/GoodsDetailView";
import AdminView from "@/views/Admin/AdminView";
import AdminUserView from "@/views/Admin/AdminUserView";

Vue.use(VueRouter)

const routes = [
  {
    path:"/",
    redirect:"/activity"
  },
  {
    path:"/user",
    name:"user",
    component:UserView
  },
  {
    path:"/activity",
    name:"activity",
    component:ActivityView,
    children:[

    ]
  },
  {
    path:"/activity/detail",
    name:"activityDetail",
    component:ActivityDetailView
  },
  {
    path:"/goods/Detail",
    name:"goodsDetail",
    component:GoodsDetailView
  },
  {
    path:"/shop",
    name:"shop",
    component:ShopView
  },
  {
    path:"/admin",
    name:"admin",
    component:AdminView
  },
  {
    path:"/admin/user",
    name:"adminUser",
    component:AdminUserView
  }
]

const router = new VueRouter({
  routes
})

export default router
