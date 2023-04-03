<template>
  <div>
    <HeadBar :title="title"></HeadBar>
    <Login v-show="!isLogin"></Login>
    <UserInfo v-show="isLogin"></UserInfo>
  </div>
</template>

<script>
import Login from "@/components/Login";
import HeadBar from "@/components/HeadBar";
import UserInfo from "@/components/UserInfo";

export default {
  name: "UserView",
  components: {UserInfo, HeadBar, Login},
  beforeMount() {
    this.isLogin = this.$cookies.isKey("token");
    if(!this.isLogin){
      this.title="登录"
    }
  },
  data(){
    return{
      isLogin:false,
      title:"用户"
    }
  }
}
</script>

<style scoped>

</style>