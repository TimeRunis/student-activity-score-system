<template>
  <div>
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
    <van-field
        readonly
        clickable
        label="买家信息"
        @click="showUser=true"
        is-link
    />
    <van-popup v-model="showUser" position="bottom" style="height: 80%" @close="showGoods=false">
      <div>
        <admin-user-info :userinfo="order.user" style="margin-top: 20px"></admin-user-info>
      </div>
    </van-popup>
    <van-steps direction="vertical" :active="0">
      <van-step v-for="(item,index) in order.transportInfo" :key="index">
        <p style="font-weight: bold">{{item['info']}}</p>
        <p>{{ item['updateTime'] }}</p>
      </van-step>

    </van-steps>
  </div>
</template>

<script>
import GoodsDetail from "@/components/Goods/GoodsDetail";
import AdminUserInfo from "@/components/Admin/User/AdminUserInfo";
import {dateFormat} from "@/util/DateTransformer";
export default {
  name: "AdminOrderInfo",
  components: {AdminUserInfo, GoodsDetail},
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