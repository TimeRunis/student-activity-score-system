<template>
  <div>
    <van-cell-group inset class="info_card">
      <van-cell title="用户id" :value="userinfo['userId']"></van-cell>
      <van-cell title="用户邮箱" :value="userinfo['userMail']"></van-cell>
      <van-cell title="用户名" :value="userinfo['userName']"></van-cell>
      <van-cell title="真实姓名" :value="userinfo['realName']"></van-cell>
      <van-cell title="生日" :value="userinfo['birthday']"></van-cell>
      <van-cell title="注册时间" :value="userinfo['createTime']"></van-cell>
      <van-cell title="活跃积分" :value="userinfo['activityScore']"></van-cell>
      <van-cell title="累计活跃积分" :value="userinfo['totalActivityScore']"></van-cell>
      <van-cell>
        <van-button style="width: 50%;margin-top: 10px;margin-bottom: 10px" round type="primary" @click="edit">修改</van-button>
        <van-button style="width: 50%;margin-top: 10px;margin-bottom: 10px" round type="primary" @click="submitEdit">提交修改</van-button>
      </van-cell>
    </van-cell-group>
    <van-popup v-model="isEdit" position="bottom" style="height: 80%;">
      <van-field class="edit_field" v-model="userinfo['userMail']" label="用户邮箱" placeholder="请输入邮箱" />
      <van-field class="edit_field" v-model="userinfo['userName']" label="用户名" placeholder="请输入用户名" />
      <van-field class="edit_field" v-model="userinfo['realName']" label="真实姓名" placeholder="请输入真实姓名" />
      <van-field
          readonly
          clickable
          name="datetimePicker"
          :value="userinfo['birthday']"
          label="生日"
          placeholder="点击选择时间"
          @click="showDate = true"
          is-link
      />
      <van-button style="width: 100%;margin-top: 10px;margin-bottom: 10px" round type="danger" @click="showEditPsw=true">修改密码</van-button>
      <van-popup v-model="showDate" position="bottom">
        <van-datetime-picker
            type="date"
            @confirm="onConfirm"
            @cancel="showDate = false"
            :min-date="new Date(1950,0,1)"
            :max-date="new Date()"
        />
      </van-popup>
      <van-popup v-model="showEditPsw" position="bottom">
        <van-field class="edit_field" v-model="userPsw" label="密码" placeholder="请输入修改后的密码"  type="password"/>
        <van-button style="width: 100%;margin-top: 10px;margin-bottom: 10px" round type="danger" @click="updatePsw">确认</van-button>
      </van-popup>
    </van-popup>
  </div>
</template>

<script>
import { apiPut, message} from "@/util/Api";
import {Notify} from "vant";
export default {
  name: "AdminUserInfo",
  props:['userinfo'],
  methods:{
    submitEdit(){
      apiPut("user",this.userinfo).then((resp)=>{
        message(resp.data)
        location.reload()
      })
    },
    onConfirm(value){
      let date = new Date(value)
      this.userinfo["birthday"]=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()
      console.info(value)
      this.showDate = false
    },
    edit(){
      this.isEdit=true
    },
    updatePsw(){
      if(this.userPsw!==""){
        apiPut("user",{"userPsw":this.userPsw}).then((resp)=>{
          if(resp.data['code']===0){
            message(resp.data)
            location.reload()
          }
        })
      }else {
        Notify({type:"danger",message:"错误"})
      }
    },
  },
  data(){
    return{
      loading:true,
      getParam:{
        id:0,
      },
      userPsw:"",
      isEdit:false,
      showDate:false,
      showEditPsw:false,
      formatDate:"",
    }
  }
}
</script>

<style scoped>
.info_card{
  border-radius: 1em;
  box-shadow: 0 0 15px 15px #f5f5f5;
  border: 1px black;
}
.edit_field{
  padding-top:20px;
}
</style>