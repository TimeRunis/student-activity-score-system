<template>
  <div class="login_box">
    <van-form @submit="login">
      <van-field
          v-model="userMail"
          name="userMail"
          label="邮箱"
          placeholder="邮箱"
          :rules="[{ required: true, message: '请填写邮箱' }]"
          class="login_field"
      />
      <van-field
          v-model="userPsw"
          type="password"
          name="userPsw"
          label="密码"
          placeholder="密码"
          :rules="[{ required: true, message: '请填写密码' }]"
          class="login_field"
      />
      <div style="margin: 20px;">
        <van-button round block type="info" native-type="submit">登录</van-button>
      </div>
    </van-form>
  </div>
</template>

<script>
import {apiPost} from "@/util/Api";
import {message} from "@/util/Api";

export default {
  name: "Login",
  data() {
    return {
      userMail: '',
      userPsw: '',
    };
  },
  methods: {
    login(values) {
      apiPost("login",values).then((resp)=>{
        if(resp.data["code"]===0){
          this.$cookies.set("token",resp.data["data"]["token"])
          localStorage.setItem("userId",resp.data["data"]["info"]["userId"])
          localStorage.setItem("authorityLevel",resp.data["data"]["info"]["authorityLevel"])
          location.reload()
        }
        message(resp.data)
      })
    },
  },
}
</script>

<style scoped>
.login_box{
  margin-top: 50px;
}
.login_field{
  padding-top: 20px;
  margin: 20px 0px 30px 0px;
}
</style>