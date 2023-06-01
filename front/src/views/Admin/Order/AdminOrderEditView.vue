<template>
  <div>
    <van-nav-bar
        title="订单操作"
        left-arrow
        @click-left="$router.back()"
    />
    <div style="display: flex;justify-content: center">
      <div class="cart_box">
        <admin-order-info ref="orderInfo"></admin-order-info>
        <van-button :disabled="isEnd" type="primary" style="width: 100%;border-radius: 0.5em;margin-top: 10px" round @click="isShowUpdate=true">更新订单状态</van-button>
        <van-button :disabled="isEnd" type="primary" style="width: 100%;border-radius: 0.5em;margin-top: 10px" round @click="endOrder">完结订单</van-button>
      </div>
    </div>
    <van-popup v-model="isShowUpdate" position="bottom">
      <div style="margin-top: 10px">
        <van-field v-model="updateParam.info" label="更新信息" style="margin: 10px" placeholder="输入信息"></van-field>
        <van-button type="primary" style="width: 100%" round @click="updateOrder">确认</van-button>
      </div>
    </van-popup>
  </div>
</template>

<script>
import AdminOrderInfo from "@/components/Admin/Order/AdminOrderInfo";
import {apiGet, apiPut, message} from "@/util/Api";
import {Notify} from "vant";
export default {
  name: "AdminOrderEditView",
  components: {AdminOrderInfo},
  beforeMount() {
    if(this.$route.query.id){
      this.orderQueryParam.goId=this.$route.query.id;
      this.updateParam.goId=this.$route.query.id;
      apiGet("goodsOrder",this.orderQueryParam).then((resp)=>{
        if (resp.data['code']!==0){
          Notify({type:"danger",message:resp.data['message']});
        }else {
          this.order=resp.data['data']['records'][0];
          this.$refs.orderInfo.loadOrder(this.order);
          //订单操作按钮更新
          if(this.order['endTime']){
            this.isEnd=true;
          }else {
            this.isEnd=false;
          }
        }
      });
    }
  },
  data(){
    return{
      updateParam:{
        goId:0,
        info:"",
      },
      orderQueryParam:{
        goId:0,
        current:1,
        size:10,
        admin:true,
      },
      isEnd:false,
      isShowUpdate:false,
      order:{},
    }
  },
  methods:{
    endOrder(){
      apiPut("goodsOrder",{goId:this.$route.query.id,adminEnd:1}).then((resp)=> {
        if (resp.data['code'] === 0) {
          Notify({type: "success", message: resp.data["message"]});
          setTimeout(() => {
            location.reload();
          }, 500);
        } else {
          Notify({type: "danger", message: resp.data["message"]});
          setTimeout(() => {
            location.reload();
          }, 500);
        }
      })
    },
    updateOrder(){
      apiPut("goodsOrder",this.updateParam).then((resp)=>{
        if(resp.data['code']===0){
          Notify({type:"success",message:resp.data["message"]});
          setTimeout(()=>{
            location.reload();
          },500);
        }else {
          Notify({type:"danger",message:resp.data["message"]});
          setTimeout(()=>{
            location.reload();
          },500);
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