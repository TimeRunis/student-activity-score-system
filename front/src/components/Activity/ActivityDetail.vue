<template>
  <div style="display: flex;justify-content: center">
    <div class="acDetail_box">
      <van-cell align="center" title-style="font-size:15px;font-weight:bold;" style="border-radius: 1em" :title="detail['activityName']" />
      <van-cell style="border-radius: 1em" >
        <div v-html="detail['activityContent']"></div>
        <div class="ac_other_element">
          <van-cell>
            开始时间:{{detail['startTime']}}
          </van-cell>
          <van-cell>
            截止时间:{{detail['deadLine']}}
          </van-cell>
        </div>
      </van-cell>
      <van-cell>
        <van-button :disabled="isDeadLine" style="width: 100%" round type="primary" @click="acSubmit()">参加活动</van-button>
      </van-cell>
    </div>
  </div>
</template>

<script>
import {apiGet, apiPost, message} from "@/util/Api";
import {string2Date} from "@/util/DataTransformer";
import {Notify} from "vant";


export default {
  name: "ActivityDetail",
  components: {},
  props:['acId'],
  beforeMount() {
    //获取参数
    this.param['id']=this.acId
    this.submitParam.acId=this.acId
    //获取活动详情
    apiGet("activity",this.param).then((resp)=>{
      this.detail=resp.data['data']
      //获取时间
      this.todayDate=new Date()
      //获取截止时间
      this.deadLine=new Date(string2Date(this.detail['deadLine']))
      this.isDeadLine=this.todayDate>=this.deadLine
      if (this.isDeadLine){
        Notify({type:"warning",message:"活动已经结束了"})
      }
    })

  },
  methods:{
    acSubmit(){
      apiPost("activitySubmit",this.submitParam).then((resp)=>{
          message(resp.data)
      })
    }
  },
  data(){
    return{
      param:{
        id:'',
      },
      isDeadLine:true,
      submitParam:{
        acId:0,
      },
      todayDate:'',
      deadLine:'',
      detail:{},
    }
  }
}
</script>

<style scoped>
.acDetail_box{
  flex-wrap: nowrap;
  border-radius: 0.5em;
  box-shadow: 0 0 10px 0 #e6e6e6;
  border-style: solid;
  border-width: 1px;
  border-color: #f0f0f0;;
  margin-top: 10px;
  margin-bottom: 15px;
  width: 90%;
}
.ac_other_element{
  border-style: solid;
  border-width: 1px;
  border-color: #e8e8e8;
  margin-top: 30px;
  width:auto;
}
</style>