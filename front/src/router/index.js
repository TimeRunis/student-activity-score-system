import Vue from 'vue'
import VueRouter from 'vue-router'
import UserView from "@/views/UserView";
import ActivityView from "@/views/ActivityView";
import ActivityDetailView from "@/views/ActivityDetailView";

Vue.use(VueRouter)

const routes = [
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
  }
]

const router = new VueRouter({
  routes
})

export default router
