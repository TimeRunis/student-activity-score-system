<template>
  <div class="login-box">
    <van-form @submit="onSubmit">
      <van-field
          v-model="userMail"
          name="userMail"
          label="邮箱"
          placeholder="邮箱"
          :rules="[{ required: true, message: '请填写用户名' }]"
      />
      <van-field
          v-model="userPsw"
          type="password"
          name="userPsw"
          label="密码"
          placeholder="密码"
          :rules="[{ required: true, message: '请填写密码' }]"
      />
      <div style="margin: 16px;">
        <van-button round block type="info" native-type="submit">提交</van-button>
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
    onSubmit(values) {
      apiPost("login",values).then((resp)=>{
        this.$cookies.set("token",resp.data["data"]["token"])
        localStorage.setItem("userId",resp.data["data"]["info"]["userId"])
        message(resp.data)
        location.reload()
      })
    },
  },
}
</script>

<style scoped>
.login-box{
  margin-top: 50px;
}
</style>