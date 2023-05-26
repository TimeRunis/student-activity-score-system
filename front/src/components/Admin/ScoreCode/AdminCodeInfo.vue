<template>
  <div style="display: flex;flex-wrap: wrap;justify-content: center">
    <div class="cart_box">
      <van-cell title="积分码" :value="codeInfo.code"></van-cell>
      <van-cell title="可获得积分" :value="codeInfo.number"></van-cell>
      <van-cell title="截止时间" :value="codeInfo.deadLine"></van-cell>
      <van-cell title="是否使用" :value="codeInfo.used?'是':'否'"></van-cell>
      <van-cell title="使用者ID" :value="userId.user"></van-cell>
      <van-cell title="使用者" :value="codeInfo.user"></van-cell>
      <van-cell title="创建者ID" :value="userId.creator"></van-cell>
      <van-cell title="创建者" :value="codeInfo.creator"></van-cell>
    </div>
  </div>
</template>

<script>

import {dateFormat} from "@/util/DateTransformer";

export default {
  name: "AdminCodeInfo",
  methods:{
    setData(info){
      this.codeInfo=JSON.parse(JSON.stringify(info));
      if(this.codeInfo.user){
        this.userId.user=info['user']['userId'];
        this.codeInfo.user=info['user']['userName'];
      }else {
        this.codeInfo.user="无";
        this.userId.user="无";
      }
      this.userId.creator=info['creator']['userId'];
      this.codeInfo.creator=info['creator']['userName'];
      this.codeInfo.deadLine=dateFormat(new Date(this.codeInfo.deadLine));

    }
  },
  data(){
    return{
      userId:{
        user:"",
        creator: "",
      },
      codeInfo:{
        code: "",
        number: 0,
        userId: 0,
        user: "",
        creatorId: 0,
        creator:"",
        createTime: "",
        deadLine: "",
        useTime: "",
        used: false
      },
    }
  }
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
</style>