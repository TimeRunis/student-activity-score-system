<template>
  <div style="margin-bottom: 50px">
    <van-nav-bar
        title="积分商城">
      <template #right>
        <van-sticky>
          <van-button icon="search" round @click="isSearch=true" />
        </van-sticky>
      </template>
    </van-nav-bar>
    <div>
      <goods-list :is-start="true"></goods-list>
    </div>
    <van-popup v-model="isSearch" position="bottom" style="height: 70%">
      <goods-search></goods-search>
    </van-popup>
    <bottom-menu></bottom-menu>
  </div>
</template>

<script>
import GoodsList from "@/components/Goods/GoodsList";
import GoodsSearch from "@/components/Goods/GoodsSearch";
import BottomMenu from "@/components/BottomMenu";
export default {
  name: "ShopView",
  components: {BottomMenu, GoodsSearch, GoodsList},
  data(){
    return{
      isSearch:false,
    }
  },
}
</script>

<style scoped>

</style>