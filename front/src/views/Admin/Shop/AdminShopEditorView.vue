<template>
  <div>
    <van-nav-bar
        title="商品详情"
        left-arrow
        @click-left="$router.back()"
    />
    <goods-detail ref="goods"></goods-detail>
    <div style="display: flex;justify-content: center;flex-wrap:wrap">
     <div class="cart_box">
       <van-field v-model="goods.goodsName" label="商品名" placeholder="请输入商品名"></van-field>
       <van-field v-model="goods.goodsNumber" label="商品库存(个)" placeholder="请输入数字" type="digit"></van-field>
       <van-field v-model="goods.goodsPrice" label="商品价格(积分)" placeholder="请输入数字" type="digit"></van-field>
       <van-field
           @blur="previewHeadImg"
           v-model="goods.headImg"
           label="商品头图"
           placeholder="请输入图片链接"
       />
       <van-uploader multiple :max-count="1" upload-text="上传头图" v-model="headFile" style="margin:5px;" :after-read="afterRead"  />
       <van-popup v-model="showDate" position="bottom">
         <van-datetime-picker
             type="datetime"
             v-model="nowDate"
             @cancel="showDate = false"
         />
       </van-popup>
     </div>
    </div>
    <div style="display: flex;justify-content: center;flex-wrap:wrap">
      <div class="cart_box" >
        <WangEditor ref="editor"></WangEditor>
        <div style="margin-left: 5px">
          <p style="margin: 0;color: silver;font-size: 10px">上次更新于 {{ goods.updateTime }}</p>
          <p style="margin: 0;color: silver;font-size: 10px">创建于 {{ goods.createTime }}</p>
        </div>
        <van-button type="primary" style="width: 100%" @click="previewContent">预览</van-button>
        <van-button type="info" style="width: 100%" @click="saveContent">保存</van-button>
      </div>
    </div>
  </div>
</template>

<script>
import WangEditor from "@/components/WangEditor";
import {apiGet, apiPost, apiPut, apiUpload} from "@/util/Api";
import {Notify} from "vant";
import {dateFormat} from "@/util/DateTransformer";
import GoodsDetail from "@/components/Goods/GoodsDetail";

export default {
  name: "AdminGoodsEditorView",
  components: {GoodsDetail, WangEditor, },
  mounted() {
    if(!this.$route.query.isCreate){
      this.submitParam.id=this.$route.query.id;
      apiGet("goods",this.submitParam).then((resp)=>{
        this.goods=resp.data['data'];
        this.headFile.push({url:resp.data['data']['headImg']});
        this.$refs.goods.setDetail(resp.data['data']);
        this.$refs.editor.setContent(resp.data['data']['goodsDetail']);
        this.sku.stock_num=this.goods.goodsNumber;
        this.sku.price=this.goods.goodsPrice;
        this.goodsInfo.picture=this.goods.headImg;
        this.previewContent();
      })
    }else {
      this.goods.createTime=dateFormat(new Date());
      this.goods.updateTime=dateFormat(new Date());
      this.sku.stock_num=this.goods.goodsNumber;
      this.sku.price=this.goods.goodsPrice;
      this.goodsInfo.picture=this.goods.headImg;
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
      sku: {
        tree: [
        ],
        list: [
        ],
        price: "99999", // 默认价格（单位元）
        stock_num: 999, // 商品总库存
        none_sku: true, // 是否无规格商品
      },
      goodsInfo: {
        info:'',
        // 默认商品 sku 缩略图
        picture: '',
      },
      goods: {
        goodsId: 0,
        goodsName: "",
        goodsDetail: "",
        goodsPrice: 0,
        goodsNumber: 0,
        creatorId: 0,
        createTime: "",
        updateTime: "",
        headImg: "",
        show: false
      },
    }
  },
  methods:{
    previewHeadImg(){
      if(this.headFile.length===0){
        this.headFile.push({url:this.goods.headImg});
      }else {
        this.headFile.pop();
        this.headFile.push({url:this.goods.headImg});
      }
    },
    previewContent(){
      this.sku.price=this.goods.goodsPrice;
      this.sku.stock_num=this.goods.goodsNumber;
      this.goodsInfo.picture=this.goods.headImg;
      this.$refs.goods.setGoodsSku(this.sku,this.goodsInfo);
      this.$refs.goods.setContent(this.$refs.editor.html);
      this.$refs.goods.setDetail(this.goods);
    },
    saveContent(){
      if(!this.$route.query.isCreate){
        this.$refs.goods.setContent(this.$refs.editor.html);
        apiPut("goods",this.$refs.goods.detail).then((resp)=>{
          if(resp.data['code']===0){
            Notify({type:"success",message:resp.data['message']});
            setTimeout(()=>{location.reload();},500);
          }else {
            Notify({type:"danger",message:resp.data['message']});
          }
        })
      }else {
        this.previewContent();
        apiPost("goods",this.goods).then((resp)=>{
          if(resp.data['code']===0){
            Notify({type:"success",message:resp.data['message']});
            setTimeout(()=>{location.reload();},500);
          }else {
            Notify({type:"danger",message:resp.data['message']});
          }
        })
      }
    },
    afterRead(file) {
      let formFile=new FormData();
      formFile.append("file",file.file);
      apiUpload('fileUpload',formFile).then((resp)=>{
        if(resp.data['code']===0){
          this.goods.headImg=resp.data['data'][0];
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