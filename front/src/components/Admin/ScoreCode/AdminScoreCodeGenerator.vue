<template>
  <div style="display: flex;justify-content: center">
    <div class="cart_box">
      <van-cell title="生成数量">
        <van-stepper v-model="generateParam.number" theme="round" button-size="22" />
      </van-cell>
      <van-cell title="积分数">
        <van-field type="digit" placeholder="请输入积分数" v-model="generateParam.score"></van-field>
      </van-cell>
      <van-cell title="选择有效日期" is-link @click="isShowDateSelector=true" :value="generateParam.deadLine"></van-cell>
      <van-cell>
        <van-button type="primary" style="width: 100%" @click="generateCode">生成</van-button>
      </van-cell>
    </div>
    <van-popup v-model="isShowDateSelector" position="bottom" style="height: 50%">
      <van-datetime-picker
          v-model="selectDate"
          type="datetime"
          title="选择年月日"
          :show-toolbar="false"
          :min-date="minDate"
          :max-date="maxDate"
          :formatter="formatter"
      />
    </van-popup>
  </div>
</template>

<script>
import {dateFormat} from "@/util/DateTransformer";
import {apiDelete, apiPost} from "@/util/Api";
import {Dialog, Notify} from "vant";

export default {
  name: "AdminScoreCodeGenerator",
  updated() {
    this.generateParam.deadLine=dateFormat(this.selectDate);
  },
  data(){
    return{
      codeList:[],
      generateParam:{
          number:1,
          score:100,
          deadLine:""
      },
      selectDate:new Date(),
      isShowDateSelector:false,
      minDate:new Date(),
      maxDate:new Date(2099,1,1),
    }
  },
  methods:{
    generateCode(){
      Dialog.confirm({
        title: '确认',
        message: '确定要生成'+this.generateParam.number+'个价值'+this.generateParam.score+'积分的积分码',
      })
          .then(() => {
            apiPost("scoreCode",this.generateParam).then((resp)=>{
              if(resp.data['code']===0){
                if(this.generateParam.number>1){
                  for(let i in resp.data['data']){
                    this.codeList.push(resp.data['data'][i]['code']);
                  }
                  navigator.clipboard.writeText(this.codeList);
                }else {
                  navigator.clipboard.writeText(resp.data['data']['code']);
                }
                Dialog.alert({
                  title: '提示',
                  message: '成功生成并复制到剪贴板',
                }).then(() => {
                  // on close
                });
              }else {
                Notify({type:"danger",message:resp.data['message']});
              }
            });
          })

    },
    formatter(type, val) {
      if (type === 'year') {
        return val + '年';
      }
      if (type === 'month') {
        return val + '月';
      }
      if (type === 'day') {
        return val + '日';
      }
      if (type === 'hour') {
        return val + '点';
      }
      if (type === 'minute') {
        return val + '分';
      }
      return val;
    },
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