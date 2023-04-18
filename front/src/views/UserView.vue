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
          <van-cell title="我的信息" is-link @click="isPop=true;showUserInfo=true;"></van-cell>
          <van-cell title="我的订单" is-link @click="isPop=true;showOrder=true;"></van-cell>
          <van-cell title="我参加的活动" is-link @click="isPop=true;showActivity=true"></van-cell>
        </van-cell-group>
      </div>
      <van-popup v-model="isPop" closeable @close="closePop" position="bottom" :style="{ height:'100%' }">
        <UserInfo v-show="showUserInfo" style="margin-top: 60px"></UserInfo>
        <div v-show="showOrder">123</div>
        <user-activity-submit :is-start="true" style="margin-top: 60px"></user-activity-submit>
      </van-popup>

    </div>
  </div>
</template>

<script>
import Login from "@/components/User/Login";
import UserInfo from "@/components/User/UserInfo";
import Register from "@/components/User/Register";
import UserActivitySubmit from "@/components/User/UserActivitySubmit";

export default {
  name: "UserView",
  components: {UserActivitySubmit, Register, UserInfo, Login},
  beforeMount() {
    this.isLogin = this.$cookies.isKey("token");
    if(!this.isLogin){
      this.title="登录"
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
    }
  },
  methods:{
    closePop(){
      setTimeout(()=>{
        this.showActivity=false;
        this.showUserInfo=false;
        this.showOrder=false;
      },200)
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