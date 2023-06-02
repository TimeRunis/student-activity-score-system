<template>
  <div style="display: flex;flex-wrap: wrap;justify-content: center">

    <div class="cart_box">
      <van-grid column-num="2">
        <van-grid-item text="用户" />
        <van-grid-item text="权限" />
      </van-grid>
      <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
        <van-list
            v-model="loading"
            :finished="finished"
            finished-text="没有更多了"
            @load="onLoad"
        >
          <div v-for="(item,i) in list" :key="i" style="display: flex;flex-wrap: wrap;justify-content: center">
            <van-swipe-cell style="width: 100%">
              <van-cell title-class="van-ellipsis" :border="false" :title="item['userName']" icon="user-o" :value="item['authorityLevel']" />
              <template #right>
                <van-button square text="详情" type="primary" @click="checkUserInfo(i)" />
                <van-button square text="删除" type="danger" @click="deleteUser(i)" />
              </template>
            </van-swipe-cell>
          </div>
        </van-list>
      </van-pull-refresh>

    </div>
  </div>
</template>

<script>
import {apiDelete, apiGet} from "@/util/Api";
import AdminUserInfo from "@/components/Admin/User/AdminUserInfo";
import {Dialog, Notify} from "vant";
import AdminUserAdd from "@/components/Admin/User/AdminUserAdd";

export default {
  name: "AdminUserList",
  components: {AdminUserAdd, AdminUserInfo},
  data() {
    return {
      getData:{
        current:1,
        size:10,
        admin:true,
      },
      list: [],
      listMaxSize:1,
      loading: false,
      finished: false,
      refreshing: false,
    };
  },
  methods: {
    onLoad() {
      setTimeout(() => {
        if (this.refreshing) {
          //初始化数据
          this.list = [];
          this.listMaxSize = 1;
          this.getData.current = 1;
          this.refreshing = false;
        }
        if (this.list.length >= this.listMaxSize) {
          this.finished = true;
        } else {
          //获取数据
          apiGet('user',this.getData).then((resp)=>{
            if(resp.data['code']===0&&resp.data['data']['records']){
              for(let i in resp.data['data']['records']){
                this.list.push(resp.data['data']['records'][i]);
              }
              this.listMaxSize=resp.data['data']['total']
              this.getData["current"]+=1;
            }else if(resp.data['code']===0){
              this.list.push(resp.data['data']);
              this.listMaxSize=1;
              this.getData["current"]=1;
            }
          })
          // 加载状态结束
          this.loading = false;
        }
      }, 1000);


    },
    onRefresh() {
      // 清空列表数据
      this.finished = false;
      this.refreshing = true;
      // 重新加载数据
      // 将 loading 设置为 true，表示处于加载状态
      this.loading = true;
      this.onLoad();
    },
    checkUserInfo(index){
      this.$parent.showUserInfo(this.list[index]);
    },
    deleteUser(index){
      Dialog.confirm({
        title: '确认',
        message: '确定要删除这个用户吗',
      })
          .then(() => {
            apiDelete('user',{userId:this.list[index]['userId']}).then((resp)=>{
              if(resp.data['code']===0){
                Notify({type:"success",message:resp.data['message']});
                this.$delete(this.list,index);
              }
            })
          })

    },
    search(value,type){
      switch (type){
        case 0:
          this.getData={
            name:value,
            current:1,
            size:10,
            admin:1
          };
          this.onRefresh();
          break;
        case 1:
          this.getData={
            relName:value,
            current:1,
            size:10,
            admin:1
          };
          this.onRefresh();
          break;
        case 2:
          this.getData={
            id:value,
            current:1,
            size:10,
            admin:1
          };
          this.onRefresh();
          break;
      }
    }
  }
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