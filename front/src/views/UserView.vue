<template>
  <div class="userview_div">
    <HeadBar :title="title"></HeadBar>
    <div class="userview_content">
      <van-tabs v-show="!isLogin" color="#00ccff">
        <van-tab title="登录">
          <Login></Login>
        </van-tab>
        <van-tab title="注册">
          <Register></Register>
        </van-tab>
      </van-tabs>
      <UserInfo v-if="isLogin"></UserInfo>
    </div>
  </div>
</template>

<script>
import Login from "@/components/Login";
import HeadBar from "@/components/HeadBar";
import UserInfo from "@/components/UserInfo";
import Register from "@/components/Register";

export default {
  name: "UserView",
  components: {Register, UserInfo, HeadBar, Login},
  beforeMount() {
    this.isLogin = this.$cookies.isKey("token");
    if(!this.isLogin){
      this.title="登录"
    }
  },
  data(){
    return{
      isLogin:false,
      title:"用户信息"
    }
  }
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
</style>