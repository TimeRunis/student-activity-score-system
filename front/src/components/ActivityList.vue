<template>
  <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
    <van-list
        v-model="loading"
        :finished="finished"
        finished-text="没有更多了"
        @load="onLoad"
    >
      <van-cell v-for="item in list" :key="item" :title="item" />
    </van-list>
  </van-pull-refresh>
</template>

<script>
import {apiGet} from "@/util/Api.js";
export default {
  name: "ActivityList",
  data() {
    return {
      getData:{
        current:1,
        size:10
      },
      list: [],
      loading: false,
      finished: false,
      refreshing: false,
    };
  },
  methods: {
    onLoad() {
      // 异步更新数据
      this.getData["size"]+=1
      if (this.refreshing) {
        this.list = [];
        this.refreshing = false;
      }
      apiGet("activity",this.getData)
      // 加载状态结束
      this.loading = false;

    },
    onRefresh() {
      // 清空列表数据
      this.finished = false;

      // 重新加载数据
      // 将 loading 设置为 true，表示处于加载状态
      this.loading = true;
      this.onLoad();
    },
  },
}
</script>

<style scoped>

</style>