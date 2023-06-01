<template>
  <div>
    <van-grid column-num="2">
      <van-grid-item text="用户名" />
      <van-grid-item text="报名时间" />
    </van-grid>
    <div v-for="(item,index) in list">
      <van-cell :title="item['user']['userName']" title-style="text-align:center" :value="item['submitTime']" value-style="text-align:center""></van-cell>
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminActivitySubmitList",
  props:['list'],
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