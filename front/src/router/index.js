import Vue from 'vue'
import VueRouter from 'vue-router'
import UserView from "@/views/User/UserView";
import ActivityView from "@/views/Activity/ActivityView";
import ActivityDetailView from "@/views/Activity/ActivityDetailView";
import ShopView from "@/views/Shop/ShopView";
import GoodsDetailView from "@/views/Goods/GoodsDetailView";
import AdminView from "@/views/Admin/AdminView";
import AdminUserView from "@/views/Admin/User/AdminUserView";
import AdminActivityView from "@/views/Admin/Activity/AdminActivityView";
import AdminActivityEditorView from "@/views/Admin/Activity/AdminActivityEditorView";
import AdminCollegeView from "@/views/Admin/College/AdminCollegeView";
import AdminShopView from "@/views/Admin/Shop/AdminShopView";
import AdminShopEditorView from "@/views/Admin/Shop/AdminShopEditorView";
import AdminScoreView from "@/views/Admin/Score/AdminScoreView";
import AdminScoreCodeView from "@/views/Admin/ScoreCode/AdminScoreCodeView";
import AdminOrderView from "@/views/Admin/Order/AdminOrderView";
import AdminOrderEditView from "@/views/Admin/Order/AdminOrderEditView";
import AdminActivitySubmitView from "@/views/Admin/Activity/AdminActivitySubmitView";

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
  },
  {
    path:"/admin/activity",
    name:"adminActivity",
    component:AdminActivityView
  },
  {
    path:"/admin/shop",
    name:"adminShop",
    component:AdminShopView
  },
  {
    path:"/admin/shop/edit",
    name:"adminShopEdit",
    component:AdminShopEditorView
  },
  {
    path:"/admin/college",
    name:"adminCollege",
    component:AdminCollegeView
  },
  {
    path:"/admin/activity/edit",
    name:"adminActivityEditor",
    component:AdminActivityEditorView
  },
  {
    path:"/admin/score",
    name:"adminScore",
    component:AdminScoreView
  },
  {
    path:"/admin/scoreCode",
    name:"adminScoreCode",
    component:AdminScoreCodeView
  },
  {
    path:"/admin/order",
    name:"adminOrderCode",
    component:AdminOrderView
  },
  {
    path:"/admin/order/edit",
    name:"adminOrderEdit",
    component:AdminOrderEditView
  },
  {
    path:"/admin/activity/submitList",
    name:"adminActivitySubmitList",
    component:AdminActivitySubmitView
  }
]

const router = new VueRouter({
  routes
})

export default router
