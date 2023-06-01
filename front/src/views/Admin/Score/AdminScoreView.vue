<template>
  <div>
    <van-nav-bar
        title="积分管理"
        left-arrow
        @click-left="$router.back()"
    />
    <div style="display: flex;flex-wrap: wrap;justify-content: center">

      <div class="cart_box">
        <van-field v-model="param.id" type="digit" placeholder="请输入用户ID" label="用户ID" right-icon="search" @click-right-icon="search"></van-field>
      </div>
      <van-popup v-model="beSearch" position="bottom" style="height: 60%">
        <van-cell-group v-if="beSearch" inset class="info_card">
          <van-cell title="用户id" :value="userinfo.userId"></van-cell>
          <van-cell title="用户邮箱" :value="userinfo.userMail"></van-cell>
          <van-cell title="用户名" :value="userinfo.userName"></van-cell>
          <van-cell title="真实姓名" :value="userinfo.realName"></van-cell>
          <van-cell title="生日" :value="userinfo.birthday"></van-cell>
          <van-cell title="注册时间" :value="userinfo.createTime"></van-cell>
          <van-cell title="活跃积分" :value="userinfo.activityScore"></van-cell>
          <van-cell title="累计活跃积分" :value="userinfo.totalActivityScore"></van-cell>
        </van-cell-group>
      </van-popup>
      <div class="cart_box">
        <van-popover
            v-model="showScoreOption"
            trigger="click"
            :actions="menuItems"
            style="width: 100%"
            @select="onSelect"
        >
          <template #reference>
            <van-cell style="width: 100%" is-link>{{menuItems[scoreOption]['text']}}</van-cell>
          </template>
        </van-popover>
        <van-popover
            v-model="showSourceOption"
            trigger="click"
            :actions="source"
            style="width: 100%"
            @select="onSourceSelect"
        >
          <template #reference>
            <van-cell style="width: 100%" is-link>{{source[sourceOption]['text']}}</van-cell>
          </template>
        </van-popover>
        <van-field v-model="number" type="digit" placeholder="请输入操作的数量"></van-field>
        <van-button type="primary" style="width: 100%" @click="submit">提交</van-button>
      </div>
    </div>
  </div>

</template>

<script>
import {apiGet, apiPut} from "@/util/Api";
import {Dialog, Notify} from "vant";

export default {
  name: "AdminScoreView",
  data(){
    return{
      param:{
        id:"",
        current:1,
        size:10,
        admin:true,
      },
      scoreParam:{
        userId:"",
        number:0,
        mode:0,//0增,1减,2设置
        source:0//0积分,1累计积分
      },
      number:"",
      loading:true,
      userinfo:{
        activityScore: 0,
        authorityLevel: 0,
        birthday: "",
        collegeId: 0,
        createTime: "",
        gender: 0,
        realName: "",
        totalActivityScore: 0,
        updateTime: "",
        userId: 0,
        userMail: "",
        userName: "",
      },
      beSearch:false,
      scoreOption:0,
      sourceOption:0,
      showScoreOption:false,
      showSourceOption:false,
      source:[
        { text: '活跃积分' },
        { text: '累计活跃积分'},
      ]
      ,menuItems:[
        { text: '增加' },
        { text: '减少'},
        { text: '设置'},
      ],
    }
  },
  methods:{
    search(){
      apiGet('user',this.param).then((resp)=>{
        if(resp.data['code']===0&&resp.data['data']['total']!==0){
          this.userinfo=resp.data['data']['records'][0];
          Notify({type:"success",message:resp.data['message']});
          this.beSearch=true;
        }else if(resp.data['data']['total']===0){
          Notify({type:"danger",message:"用户不存在"});
        }else{
          Notify({type:"danger",message:resp.data['message']});
        }
      })
    },
    onSelect(action) {
      this.scoreOption=this.menuItems.indexOf(action);
    },
    onSourceSelect(action) {
      this.sourceOption=this.source.indexOf(action);
    },
    submit(){
      if(this.param.id!==""){
        Dialog.confirm({
          title: '确认',
          message: '确认修改用户ID为 '+this.param.id+' 的积分吗?',
        })
            .then(() => {
              this.scoreParam.userId=this.param.id;
              this.scoreParam.mode=this.scoreOption;
              this.scoreParam.number=this.number;
              this.scoreParam.source=this.sourceOption;
              apiPut('score',this.scoreParam).then((resp)=>{
                if(resp.data['code']===0){
                  this.userinfo=resp.data['data'];
                  Notify({type:"success",message:resp.data['message']});
                }else {
                  Notify({type:"danger",message:resp.data['message']});
                }
              })
            }).catch(()=>{})
      }else {
        Notify({type:"danger",message:"请输入用户ID"});
      }
    }
  },
}
</script>

<style scoped>
.cart_box{
  border-radius: 0.5em;
  box-shadow: 0 0 10px 0 #e6e6e6;
  border-style: solid;
  border-width: 1px;
  border-color: #f0f0f0;;
  margin-top: 10px;
  margin-bottom: 15px;
  width: 90%;
}
.info_card{
  margin: 20px;
  border-radius: 1em;
  box-shadow: 0 0 15px 15px #f5f5f5;
  border: 1px black;
}
</style>