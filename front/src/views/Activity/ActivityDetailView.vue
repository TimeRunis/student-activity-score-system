<template>
  <div style="margin-bottom: 50px">
    <van-nav-bar
        title="活动详情"
        left-arrow
        @click-left="$router.back()"
    />
    <div>
      <activity-detail :ac-id="acId"></activity-detail>
    </div>
    <BottomMenu></BottomMenu>
  </div>
</template>

<script>
import ActivityDetail from "@/components/Activity/ActivityDetail";
import BottomMenu from "@/components/BottomMenu";
export default {
  name: "ActivityDetailView",
  components: {BottomMenu, ActivityDetail},
  beforeMount() {
    this.acId=this.$route.query.id
  },
  data(){
    return{
      acId:''
    }
  }
}
</script>


<style scoped>

</style>