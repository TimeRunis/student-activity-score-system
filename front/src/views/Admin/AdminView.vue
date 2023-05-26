<template>
  <div>
    <van-nav-bar
        title="管理"
        left-arrow
        @click-left="$router.push('/user')"
    />
    <div style="display: flex;justify-content: center;flex-wrap: wrap">
      <div class="cart_box">
        <van-grid>
          <van-grid-item icon="user-o" text="用户" @click="$router.push('/admin/user')" />
          <van-grid-item icon="smile-comment-o" text="活动" @click="$router.push('/admin/activity')" />
          <van-grid-item icon="shop-o" text="商店" @click="$router.push('/admin/shop')" />
          <van-grid-item icon="manager-o" text="学院" @click="$router.push('/admin/college')" />
        </van-grid>
      </div>
      <div class="cart_box">
        <van-grid>
          <van-grid-item icon="orders-o" text="订单" @click="$router.push('/admin/order')" />
          <van-grid-item icon="balance-o" text="积分" @click="$router.push('/admin/score')" />
          <van-grid-item icon="qr" text="积分码" @click="$router.push('/admin/scoreCode')" />
        </van-grid>
      </div>
    </div>
  </div>
</template>

<script>
import {apiGet} from "@/util/Api";
import {Notify} from "vant";

export default {
  name: "AdminView",
  beforeMount() {
    apiGet("user",{current:1,size:1}).then((resp)=>{
      if (resp.data['code']!==0){
        Notify({type:"danger",message:"权限不足"});
        this.$router.push("/");
      }
    })
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