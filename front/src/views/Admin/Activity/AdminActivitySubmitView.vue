<template>
  <div>
    <van-nav-bar
        title="活动参与者"
        left-arrow
        @click-left="$router.back()"
    />
    <admin-activity-submit-list :list="submitList"></admin-activity-submit-list>
    <van-pagination v-model="page.current" :total-items="pageTotal" :show-page-size="6" style="position: fixed;bottom: 0;width: 100%">
      <template #prev-text>
        <van-icon name="arrow-left" />
      </template>
      <template #next-text>
        <van-icon name="arrow" />
      </template>
      <template #page="{ text }">{{ text }}</template>
    </van-pagination>
  </div>
</template>

<script>
import {apiGet} from "@/util/Api";
import {Notify} from "vant";
import AdminActivitySubmitList from "@/components/Admin/Activity/AdminActivitySubmitList";

export default {
  name: "AdminActivitySubmitView",
  components: {AdminActivitySubmitList},
  beforeMount() {
    apiGet('activitySubmit',this.page).then((resp)=>{
      if(resp.data['code']===0){
        this.submitList=resp.data['data']['records'];
        this.pageTotal=resp.data['data']['total'];
      }else {
        Notify({type:'danger',message:resp.data['message']});
      }

    })
  },
  data(){
    return{
      page:{
        acId:this.$route.query.id,
        current:1,
        size:10,
      },
      submitList:[],
      pageTotal:0,
    }
  }
}
</script>

<style scoped>

</style>