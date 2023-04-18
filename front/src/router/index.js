import Vue from 'vue'
import VueRouter from 'vue-router'
import UserView from "@/views/UserView";
import ActivityView from "@/views/ActivityView";
import ActivityDetailView from "@/views/ActivityDetailView";
import ShopView from "@/views/ShopView";
import GoodsDetailView from "@/views/GoodsDetailView";

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
    component:ActivityView
  },
  {
    path:"/activityDetail",
    name:"activityDetail",
    component:ActivityDetailView
  },
  {
    path:"/goodsDetail",
    name:"goodsDetail",
    component:GoodsDetailView
  },
  {
    path:"/shop",
    name:"shop",
    component:ShopView
  }
]

const router = new VueRouter({
  routes
})

export default router
