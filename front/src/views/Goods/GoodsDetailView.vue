<template>
  <div style="margin-bottom: 50px">
    <van-nav-bar
        title="商品详情"
        left-arrow
        @click-left="$router.back()"
    />
    <div>
      <goods-detail :gs-id="gsId"></goods-detail>
    </div>
    <bottom-menu></bottom-menu>
  </div>
</template>

<script>
import GoodsDetail from "@/components/Goods/GoodsDetail";
import BottomMenu from "@/components/BottomMenu";
export default {
  name: "GoodsDetailView",
  components: {BottomMenu, GoodsDetail},
  beforeMount() {
    this.gsId=this.$route.query.id
  },
  data(){
    return{
      gsId:''
    }
  }
}
</script>


<style scoped>

</style>