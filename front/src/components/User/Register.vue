<template>
  <div>
    <van-form @submit="register" class="register_box">
      <van-field
          v-model="formParams.userMail"
          name="userMail"
          label="邮箱"
          placeholder="邮箱"
          :rules="[{ required: true, message: '请填写邮箱' }]"
          class="register_field"
      />
      <van-field
          v-model="formParams.userName"
          name="userName"
          label="用户名"
          placeholder="用户名"
          :rules="[{ required: true, message: '请填写用户名' }]"
          class="register_field"
      />
      <van-field
          v-model="formParams.realName"
          name="realName"
          label="真实姓名"
          placeholder="真实姓名"
          :rules="[{ required: true, message: '请填写真实姓名' }]"
          class="register_field"
      />
      <van-field
          class="register_field"
          readonly
          clickable
          name="gender"
          :value="showGender"
          label="性别"
          placeholder="点击选择"
          @click="isShowGender = true"
      />
      <van-popup v-model="isShowGender" position="bottom">
        <van-picker
            show-toolbar
            :columns="['男','女']"
            @confirm="onGenderConfirm"
            @cancel="isShowGender = false"
        />
      </van-popup>
      <van-field
          class="register_field"
          readonly
          clickable
          name="college"
          :value="college"
          label="学院"
          placeholder="点击选择"
          @click="isShowCollege = true"
      />
      <van-popup v-model="isShowCollege" position="bottom">
        <van-picker
            show-toolbar
            :columns="collegeColumns"
            @confirm="onCollegeConfirm"
            @cancel="isShowCollege = false"
        />
      </van-popup>
      <van-field
          v-model="formParams.userPsw"
          type="password"
          name="userPsw"
          label="密码"
          placeholder="密码"
          :rules="[{ required: true, message: '请填写密码' }]"
          class="register_field"
      />
      <div style="margin: 20px;">
        <van-button round block type="info" native-type="submit">注册</van-button>
      </div>
    </van-form>
  </div>
</template>

<script>
import {apiGet, apiPost, message} from "@/util/Api";
import {Notify} from "vant";

export default {
  name: "Register",
  data() {
    return {
      //表单参数
      formParams: {
        userMail: '',
        realName: '',
        userName: '',
        userPsw: '',
        gender: 1,
        collegeId: 0,
      },
      //辅助参数
      collegeList:[],
      isShowGender: false,
      isShowCollege:false,
      college: '',
      collegeColumns:[],
      showGender:'男',
    };
  },
  beforeMount() {
    apiGet("college").then((resp)=>{
      if (resp.data["code"]===0){
        this.collegeList=resp.data['data']
        for (let i in resp.data['data']){
          this.collegeColumns.push(resp.data['data'][i]["collegeName"])
        }
      }else {
        Notify({type:'danger',message:'获取学院失败'})
      }
    })
  },
  methods: {
    onCancel(){
      this.isShowGender=false
      this.isShowCollege=false
    },
    onCollegeConfirm(value){
      this.isShowCollege=false
      this.college=value
      for (let i in this.collegeList){
        if(this.collegeList[i]["collegeName"]===value){
          this.formParams.collegeId=this.collegeList[i]["collegeId"]
        }
      }
      console.info(this.collegeId)
    },
    onGenderConfirm(value){
      this.isShowGender=false
      this.showGender=value
      if(value==="男"){
        this.formParams.gender=1
      }else if(value==="女"){
        this.formParams.gender=0
      }
    },
    register() {
      apiPost("register",this.formParams).then((resp)=>{
        message(resp.data)
        if(resp.data['code']===0){
          location.reload()
        }
      })
    },
  },
}
</script>

<style scoped>
.register_box{
  margin-top: 50px;
}
.register_field{
  padding-top: 10px;
  margin: 10px 0 20px 0;
}
</style>