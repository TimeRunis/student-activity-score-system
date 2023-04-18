<template>
  <div>
    <van-tabbar route>
      <van-tabbar-item to="/activity" icon="home-o">活动</van-tabbar-item>
      <van-tabbar-item to="/shop" icon="shop">商城</van-tabbar-item>
      <van-tabbar-item to="/user" icon="user-o">用户</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
export default {
  name: "BottomMenu",
  data() {
    return {
    };
  },
}
</script>

<style scoped>

</style>