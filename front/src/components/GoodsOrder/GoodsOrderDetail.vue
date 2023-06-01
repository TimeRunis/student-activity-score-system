<template>
  <div style="display: flex;justify-content: center">
    <div class="cart_box">
      <van-cell title="订单ID" :value="order.goId"></van-cell>
      <van-cell title="订单商品头图" >
        <van-image :src="order.goods.headImg"></van-image>
      </van-cell>
      <van-field
          readonly
          clickable
          label="商品详情"
          @click="showGoods=true"
          is-link
      />
      <van-popup v-model="showGoods" position="bottom" style="height: 80%" @close="showGoods=false">
        <div>
          <van-cell align="center" title-style="font-size:15px;font-weight:bold;" style="border-radius: 1em" :title="order.goods['goodsName']" />
          <van-cell style="border-radius: 1em" >
            <div v-html="order.goods['goodsDetail']"></div>
          </van-cell>
        </div>
      </van-popup>
      <van-steps direction="vertical" :active="0">
        <van-step v-for="(item,index) in order.transportInfo" :key="index">
          <p style="font-weight: bold">{{item['info']}}</p>
          <p>{{ item['updateTime'] }}</p>
        </van-step>
      </van-steps>
      <div style="display: flex;justify-content: center">
        <van-button :disabled="order.endTime!==null" type="primary" round style="width: 90%" @click="receiveGoods()">确认收货</van-button>
      </div>
    </div>
  </div>
</template>

<script>
import GoodsDetail from "@/components/Goods/GoodsDetail";
import {dateFormat} from "@/util/DateTransformer";
import {apiPut} from "@/util/Api";
import {Notify} from "vant";
export default {
  name: "GoodsOrderDetail",
  components: {GoodsDetail},
  data(){
    return{
      showGoods:false,
      showUser:false,
      order:{
        goId: 0,
        goodsId: 2,
        goods:{
          headImg:"",
        },
        userId: 1,
        user:{},
        transportInfo: [
          {
            info:"用户下单",
            updateTime:null,
          },
        ],
        buyTime: "",
        endTime: null
      },
    }
  },
  methods:{
    loadOrder(order){
      this.order=order;
      for(let index in this.order.transportInfo){
        this.order.transportInfo[index]['updateTime']=dateFormat(new Date(this.order.transportInfo[index]['updateTime']))
      }
      this.order.transportInfo=this.order.transportInfo.reverse();
    },
    receiveGoods(){
      apiPut("goodsOrder",{
        "goId":this.order.goId,
        "userReceive":1,
      }).then((resp)=>{
        if(resp.data["code"]===0){
          Notify({ type: 'success', message: resp.data["message"] });
        }else {
          Notify({ type: 'danger', message: resp.data["message"] });
        }
        setTimeout(()=>{
          location.reload();
        },500)
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