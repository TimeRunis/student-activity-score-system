<template>
  <div>
    <van-search
        v-model="value"
        show-action
        placeholder="请输入搜索关键词"
        @search="onSearch"
    >
      <template #action>
        <div @click="onSearch">搜索</div>
      </template>
    </van-search>
    <goods-list ref="child" :is-start="false"></goods-list>
  </div>
</template>

<script>
import GoodsList from "@/components/Goods/GoodsList";
import {Notify} from "vant";
export default {
  name: "GoodsSearch",
  components: {GoodsList},
  data(){
    return{
      value:'',
    }
  },
  methods: {
    onSearch() {
      if(this.value){
        //获取查询关键字并让列表处于刷新状态
        this.$refs.child.getData.key=this.value
        this.$refs.child.onRefresh()
      }else {
        Notify({type:"warning",message:"不可为空"})
      }
    },
  },
}
</script>

<style scoped>

</style>