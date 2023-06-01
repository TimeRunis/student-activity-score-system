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
          <van-cell>
            参与人数: {{userNumber}} 人
          </van-cell>
        </div>
      </van-cell>
      <van-button :disabled="isDeadLine" style="width: 100%" type="primary" @click="acSubmit">参加</van-button>
    </div>
  </div>
</template>

<script>
import {apiGet, apiPost, message} from "@/util/Api";
import {string2Date} from "@/util/DateTransformer";
import {Notify} from "vant";


export default {
  name: "ActivityDetail",
  components: {},
  props:['acId'],
  beforeMount() {
    if(this.acId){
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
      apiGet("activitySubmit",{acId:this.acId,current:1,size:10}).then((resp)=>{
        if (resp.data['code']===0){
          this.userNumber=resp.data['data']['submitNumber'];
        }else {
          this.userNumber="获取失败";
          Notify({type:"danger",message:resp.data['message']});
        }
      })
    }
  },
  methods:{
    acSubmit(){
      apiPost("activitySubmit",this.submitParam).then((resp)=>{
       if(resp.data['code']===0){
         message(resp.data);
         setTimeout(()=>{location.reload()},500)
       }else {
         message(resp.data);
       }
      })
    },
    setDetail(detail,submitNumber){
      this.detail=detail;
      //获取截止时间
      this.deadLine=new Date(string2Date(this.detail['deadLine']));
      this.userNumber=submitNumber;
    },
    setContent(content){
      this.detail['activityContent']=content;
    },
    setActivity(activity){
      this.detail=activity;
    },
  },
  data(){
    return{
      param:{
        id:'',
      },
      submitParam:{
        acId:0,
      },
      userNumber:0,
      isDeadLine:true,
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