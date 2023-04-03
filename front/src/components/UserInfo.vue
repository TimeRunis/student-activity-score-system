<template>
  <div>
    <van-skeleton :loading="loading" title :row="8" />
    <van-cell-group inset>
      <van-cell title="单元格" value="内容"></van-cell>
      <van-cell title="单元格" value="内容" label="描述信息"></van-cell>
    </van-cell-group>
  </div>
</template>

<script>
import {apiGet, message} from "@/util/Api";
export default {
  name: "UserInfo",
  beforeMount() {
    this.getParam=localStorage.getItem("userId")
    apiGet("user",this.getParam).then((resp)=>{
      //弹出消息
      message(resp.data)
      this.userinfo=resp.data["data"]
      this.loading=false
    })
  },
  data(){
    return{
      loading:true,
      getParam:{
        id:0,
      },
      userinfo:{},
    }
  }
}
</script>

<style scoped>

</style>