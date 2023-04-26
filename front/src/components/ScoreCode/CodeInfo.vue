<template>
  <div style="display: flex;flex-wrap: wrap;justify-content: center">
    <div class="cart_box">
      <van-field v-model="queryParam.code" placeholder="请输入积分码" label="积分码" right-icon="search" @click-right-icon="queryScoreCode"></van-field>
    </div>
    <div class="cart_box">
      <van-cell title="积分码" :value="codeInfo.code"></van-cell>
      <van-cell title="可获得积分" :value="codeInfo.number"></van-cell>
      <van-cell title="截止时间" :value="codeInfo.deadLine"></van-cell>
      <van-cell title="是否使用" :value="codeInfo.used?'是':'否'"></van-cell>
      <van-button type="primary" round style="width: 100%;border-radius: 0.5em" :disabled="codeInfo.used" @click="submitCode">兑换</van-button>
    </div>
  </div>
</template>

<script>
import {apiGet, apiPut} from "@/util/Api";
import {Notify} from "vant";
import {dateFormat} from "@/util/DataTransformer";

export default {
  name: "CodeInfo",
  methods:{
    queryScoreCode(){
      apiGet('scoreCode',this.queryParam).then((resp)=>{
        if(resp.data['code']===0){
          this.codeInfo=resp.data['data'];
          this.codeInfo.deadLine=dateFormat(new Date(this.codeInfo.deadLine));
          Notify({type:"success",message:resp.data["message"]})
        }else {
          this.codeInfo.code=resp.data["message"];
          this.codeInfo.number=resp.data["message"];
          this.codeInfo.deadLine=resp.data["message"];
          this.codeInfo.used=true;
          Notify({type:"danger",message:resp.data["message"]});
        }
      })
    },
    submitCode(){
      apiPut('scoreCode',this.queryParam).then((resp)=>{
        if(resp.data['code']===0){
          Notify({type:"success",message:resp.data["message"]})
          location.reload();
        }else {
          Notify({type:"danger",message:resp.data["message"]});
        }
      })
    }
  },
  data(){
    return{
      queryParam:{
        code:"",
      },
      codeInfo:{
        code: "请查询积分码",
        number: "请查询积分码",
        deadLine: "请查询积分码",
        used: true,
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