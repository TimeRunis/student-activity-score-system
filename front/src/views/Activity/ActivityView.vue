<template>
  <div style="margin-bottom: 50px">
    <van-nav-bar
        title="活动">
      <template #right>
        <van-sticky>
          <van-button icon="search" round @click="isSearch=true" />
        </van-sticky>
      </template>
    </van-nav-bar>
    <div>
      <ActivityList :is-start="true"></ActivityList>
    </div>
    <van-popup v-model="isSearch" position="bottom" style="height: 70%">
      <activity-search></activity-search>
    </van-popup>
    <router-view name="detail" />
    <bottom-menu></bottom-menu>
  </div>
</template>

<script>
import ActivityList from "@/components/Activity/ActivityList";
import ActivitySearch from "@/components/Activity/ActivitySearch";
import BottomMenu from "@/components/BottomMenu";
export default {
  name: "ActivityView",
  components: {BottomMenu, ActivitySearch, ActivityList},
  methods:{
    openSearch(){
      this.openSearch=true
    }
  },
  data(){
    return{
      isSearch:false,
    }
  }
}
</script>

<style scoped>

</style>