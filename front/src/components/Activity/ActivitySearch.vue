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
    <activity-list ref="child" :is-start="false"></activity-list>
  </div>
</template>

<script>
import ActivityList from "@/components/Activity/ActivityList";
import {Notify} from "vant";

export default {
  name: "ActivitySearch",
  components: {ActivityList},
  data(){
    return{
      value:'',
    }
  },
  methods: {
    onSearch() {
      if(this.value){
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