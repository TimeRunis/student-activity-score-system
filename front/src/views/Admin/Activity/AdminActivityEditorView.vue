<template>
  <div>
    <van-nav-bar
        title="活动详情"
        left-arrow
        @click-left="$router.back()"
    />
    <activity-detail ref="acDetail"></activity-detail>
    <div style="display: flex;justify-content: center;flex-wrap:wrap">
     <div class="cart_box">
       <van-field v-model="activity.activityName" label="活动名" placeholder="请输入活动名"></van-field>
       <van-field
           readonly
           clickable
           :value="activity.startTime"
           label="开始时间"
           placeholder="点击选择时间"
           @click="showDate = true;editDateOption=0;"
           is-link
       />
       <van-field
           readonly
           clickable
           :value="activity.deadLine"
           label="截止时间"
           placeholder="点击选择时间"
           @click="showDate = true;editDateOption=1;"
           is-link
       />
       <van-field
           @blur="previewHeadImg"
           v-model="activity.headImg"
           label="活动头图"
           placeholder="请输入图片链接"
       />
       <van-uploader multiple :max-count="1" upload-text="上传头图" v-model="headFile" style="margin:5px;" :after-read="afterRead"  />
       <van-popup v-model="showDate" position="bottom">
         <van-datetime-picker
             type="datetime"
             v-model="nowDate"
             @confirm="onConfirm"
             @cancel="showDate = false"
         />
       </van-popup>
     </div>
    </div>
    <div style="display: flex;justify-content: center;flex-wrap:wrap">
      <div class="cart_box" >
        <WangEditor ref="editor"></WangEditor>
        <div style="margin-left: 5px">
          <p style="margin: 0;color: silver;font-size: 10px">上次更新于 {{ activity.updateTime }}</p>
          <p style="margin: 0;color: silver;font-size: 10px">创建于 {{ activity.createTime }}</p>
        </div>
        <van-button type="primary" style="width: 100%" @click="previewContent">预览</van-button>
        <van-button type="info" style="width: 100%" @click="saveContent">保存</van-button>
      </div>
    </div>
  </div>
</template>

<script>
import ActivityDetail from "@/components/Activity/ActivityDetail";
import WangEditor from "@/components/WangEditor";
import {apiGet, apiPost, apiPut, apiUpload} from "@/util/Api";
import {Notify} from "vant";
import {dateFormat} from "@/util/DateTransformer";

export default {
  name: "AdminActivityEditorView",
  components: {WangEditor, ActivityDetail},
  mounted() {
    if(!this.$route.query.isCreate){
      this.submitParam.id=this.$route.query.id;
      apiGet("activity",this.submitParam).then((resp)=>{
        this.activity=resp.data['data'];
        this.headFile.push({url:resp.data['data']['headImg']});
        this.$refs.acDetail.setDetail(resp.data['data']);
        this.$refs.editor.setContent(resp.data['data']['activityContent']);
      })
    }else {
      this.activity.startTime=dateFormat(new Date());
      this.activity.deadLine=dateFormat(new Date());
      this.activity.createTime=dateFormat(new Date());
      this.activity.updateTime=dateFormat(new Date());
      this.previewContent();
    }
  },
  data(){
    return{
      submitParam:{
        id:0,
      },
      nowDate:new Date(),
      headFile:[],
      showDate:false,
      editDateOption:0,
      activity:{
        activityContent: "",
        activityName: "",
        createTime: "",
        deadLine: "",
        headImg: "",
        startTime: "",
        updateTime: "",
      }
    }
  },
  methods:{
    previewHeadImg(){
      if(this.headFile.length===0){
        this.headFile.push({url:this.activity.headImg});
      }else {
        this.headFile.pop();
        this.headFile.push({url:this.activity.headImg});
      }
      console.info(this.headFile)
    },
    previewContent(){
      this.$refs.acDetail.setContent(this.$refs.editor.html);
      this.$refs.acDetail.setDetail(this.activity);
    },
    saveContent(){
      if(!this.$route.query.isCreate){
        this.$refs.acDetail.setContent(this.$refs.editor.html);
        apiPut("activity",this.$refs.acDetail.detail).then((resp)=>{
          if(resp.data['code']===0){
            Notify({type:"success",message:resp.data['message']});
            setTimeout(()=>{location.reload();},500);
          }else {
            Notify({type:"danger",message:resp.data['message']});
          }
        })
      }else {
        this.previewContent();
        apiPost("activity",this.activity).then((resp)=>{
          if(resp.data['code']===0){
            Notify({type:"success",message:resp.data['message']});
            setTimeout(()=>{location.reload();},500);
          }else {
            Notify({type:"danger",message:resp.data['message']});
          }
        })
      }
    },
    onConfirm(value){
      switch (this.editDateOption){
        case 0:
          this.activity.startTime=value.getFullYear()+"-"+(value.getMonth()+1)+"-"+value.getDate()+" "+value.getHours()+":"+value.getMinutes()+":"+value.getSeconds();
          break;
        case 1:
          this.activity.deadLine=value.getFullYear()+"-"+(value.getMonth()+1)+"-"+value.getDate()+" "+value.getHours()+":"+value.getMinutes()+":"+value.getSeconds();
          break;
      }
      this.showDate = false
    },
    afterRead(file) {
      let formFile=new FormData();
      formFile.append("file",file.file);
      apiUpload('fileUpload',formFile).then((resp)=>{
        if(resp.data['code']===0){
          this.activity.headImg=resp.data['data'][0];
        }
      })
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