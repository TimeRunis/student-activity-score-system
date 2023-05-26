<template>
  <div style="display: flex;justify-content: center">
    <div class="gs_detail_box">
      <van-cell align="center" title-style="font-size:15px;font-weight:bold;" style="border-radius: 1em" :title="detail['goodsName']" />
      <van-cell style="border-radius: 1em" >
        <div v-html="detail['goodsDetail']"></div>
      </van-cell>
      <van-field
          readonly
          clickable
          label="购买详情"
          @click="isShow = true"
          is-link
      />
      <van-sku
          v-model="isShow"
          currency="积分"
          :sku="sku"
          :goods="goods"
          :goods-id="gsId"
          :hide-stock="false"
          :show-add-cart-btn="false"
          @buy-clicked="onBuy"
      >
        <template #sku-header-price="props">
          <div class="van-sku__goods-price">
            <span class="van-sku__price-symbol" style="font-size: 20px">积分</span
            ><span class="van-sku__price-num">{{ props.price }}</span>
          </div>
        </template>
      </van-sku>
      <van-dialog v-model="orderLoading" title="下单中..." show-cancel-button align="center" :showConfirmButton="false" :show-cancel-button="false">
        第{{this.orderNo}}个
        <br>
        <van-loading style="margin: 20px" />
      </van-dialog>
    </div>
  </div>
</template>

<script>
import {apiGet, apiPost} from "@/util/Api";
import {Dialog} from "vant";
import {string2Date} from "@/util/DateTransformer";

export default {
  name: "GoodsDetail",
  components: {},
  props:['gsId'],
  beforeMount() {
    if(this.gsId){
      //获取参数
      this.param['id']=this.gsId;
      this.submitParam.goodsId=this.gsId;
      //获取商品详情
      apiGet("goods",this.param).then((resp)=>{
        this.detail=resp.data['data'];
        this.goods.info=resp.data['data'];
        this.sku.stock_num=this.detail['goodsNumber'];
        this.sku.price=this.detail['goodsPrice'];
      })
    }

  },
  methods:{
    setGoodsSku(sku,info){
      this.sku.stock_num=parseInt(sku['stock_num']);
      this.sku.price=parseInt(sku['price']);
      this.goods=info;

    },
    setDetail(detail){
      this.detail=detail;
    },
    setContent(content){
      this.detail['goodsDetail']=content;
    },
    setGoods(goods){
      this.detail=goods;
    },
    onBuy(skuData){
      //参数初始化
      this.orderNo=0;
      this.failOrderNum=0;
      //下单确认
      Dialog.confirm({
        title: '确认请求',
        message: '你确定要花费 '+skuData['selectedNum']*this.sku.price+' 积分购买 '+skuData['selectedNum']+' 个商品',
      })
          .then(() => {
            // on confirm
            this.orderLoading=true;
            for(let i=1;i<=skuData['selectedNum'];i++){
              apiPost('goodsOrder',this.submitParam).then((resp)=>{
                if(resp.data['code']!==0) {
                  this.failInfo.push(resp.data['message']);
                  this.failOrderNum++;
                }
                this.orderNo++;
                if(this.orderNo===skuData['selectedNum']){
                  this.orderLoading=false;
                  Dialog.alert({title:'消息',message:'成功'+(this.orderNo-this.failOrderNum)+'个,失败'+this.failOrderNum+'个\n'+this.failInfo});
                  this.failInfo=[];
                }
              })
            }


          })
          .catch(() => {
            // on cancel
          });
    },
  },
  data(){
    return{
      orderNo:0,
      failOrderNum:0,
      orderLoading:false,
      param:{
        id:'',
      },
      isDeadLine:true,
      submitParam:{
        goodsId:0,
      },
      failInfo:[],
      detail:{},
      isShow:false,
      sku: {
        tree: [
        ],
        list: [
        ],
        price: "99999", // 默认价格（单位元）
        stock_num: 999, // 商品总库存
        none_sku: true, // 是否无规格商品
      },
      goods: {
        info:'',
        // 默认商品 sku 缩略图
        picture: 'http://localhost:8080/uploads/2023/03/31/214f4e1fc30f90c9b6adf9b5d2f4f3e6_cheems.png',
      },
    }
  }
}
</script>

<style scoped>
.gs_detail_box{
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
.gs_other_element{
  border-style: solid;
  border-width: 1px;
  border-color: #e8e8e8;
  margin-top: 30px;
  width:auto;
}
</style>