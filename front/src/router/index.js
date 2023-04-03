import Vue from 'vue'
import VueRouter from 'vue-router'
import UserView from "@/views/UserView";

Vue.use(VueRouter)

const routes = [
  {
    path:"/user",
    name:"user",
    component:UserView
  }
]

const router = new VueRouter({
  routes
})

export default router
