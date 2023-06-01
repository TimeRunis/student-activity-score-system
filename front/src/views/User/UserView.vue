<template>
  <div class="userview_div">
    <van-nav-bar
        :title="title"
    />
    <div class="userview_content">
      <van-tabs v-show="!isLogin" color="#00ccff">
        <van-tab title="登录">
          <Login></Login>
        </van-tab>
        <van-tab title="注册">
          <Register></Register>
        </van-tab>
      </van-tabs>
      <div v-if="isLogin">
        <van-cell-group class="card" inset >
          <van-cell title="我的信息" is-link @click="isPop=true;showUserInfo=true;" icon="info-o"></van-cell>
          <van-cell title="我的订单" is-link @click="isPop=true;showOrder=true;" icon="orders-o"></van-cell>
          <van-cell title="我参加的活动" is-link @click="isPop=true;showActivity=true" icon="smile-comment-o"></van-cell>
          <van-cell title="积分码" is-link @click="isPop=true;showCode=true" icon="qr"></van-cell>
          <van-cell v-show="isAdmin" title="管理页面" is-link @click="$router.push('admin')" icon="manager-o"></van-cell>
        </van-cell-group>
      </div>
      <van-popup v-model="isPop" closeable @close="closePop" position="bottom" :style="{ height:'100%' }">
        <UserInfo v-if="showUserInfo" style="margin-top: 60px"></UserInfo>
        <goods-order-list v-if="showOrder" :is-start="true" style="margin-top: 60px"></goods-order-list>
        <user-activity-submit v-if="showActivity" :is-start="true" style="margin-top: 60px"></user-activity-submit>
        <code-info v-if="showCode" style="margin-top: 60px"></code-info>
      </van-popup>
    </div>
    <bottom-menu></bottom-menu>
  </div>
</template>

<script>
import Login from "@/components/User/Login";
import UserInfo from "@/components/User/UserInfo";
import Register from "@/components/User/Register";
import UserActivitySubmit from "@/components/User/UserActivitySubmit";
import BottomMenu from "@/components/BottomMenu";
import CodeInfo from "@/components/ScoreCode/CodeInfo";
import GoodsOrderList from "@/components/GoodsOrder/GoodsOrderList";

export default {
  name: "UserView",
  components: {GoodsOrderList, CodeInfo, BottomMenu, UserActivitySubmit, Register, UserInfo, Login},
  beforeMount() {
    this.isLogin = this.$cookies.isKey("token");
    this.isAdmin = localStorage.getItem("authorityLevel")>=9;
    if(!this.isLogin){
      this.title="登录";
    }
  },
  data(){
    return{
      isLogin:false,
      title:"我的",
      isPop:false,
      showUserInfo:false,
      showOrder:false,
      showActivity:false,
      showCode:false,
      isAdmin:false,
    }
  },
  methods:{
    closePop(){
      setTimeout(()=>{
        this.showActivity=false;
        this.showUserInfo=false;
        this.showOrder=false;
        this.showCode=false;
      },200);
    }
  },
}
</script>

<style scoped>
.userview_div{
  border-radius: 1em;
  margin-top: 10px;
}
.userview_content{
  height: 100%;
  margin:10px 5px 0 5px;
}
.card{
  text-align: center;
  border-radius: 1em;
  box-shadow: 0 0 15px 15px #f5f5f5;
  border: 1px black;
}
</style>