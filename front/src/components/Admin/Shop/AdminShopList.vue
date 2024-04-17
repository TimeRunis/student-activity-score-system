<template>
  <div style="display: flex;flex-wrap: wrap;justify-content: center">
    <div class="cart_box">
      <van-grid column-num="2">
        <van-grid-item text="商品名" />
        <van-grid-item text="商品ID" />
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
              <van-cell title-class="van-ellipsis" :border="false" :title="item['goodsName']" :value="item['goodsId']" icon="shop-o"/>
              <template #right>
                <van-button square text="详情" type="primary" @click="$router.push('/admin/shop/edit?id='+item['goodsId'])" />
                <van-button square text="删除" type="danger" @click="deleteShop(i)" />
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
import {Dialog, Notify} from "vant";

export default {
  name: "AdminShopList",
  components: {},
  data() {
    return {
      getData:{
        current:1,
        size:10
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
          apiGet('goods',this.getData).then((resp)=>{
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
    deleteShop(index){
      Dialog.confirm({
        title: '确认',
        message: '确定要删除这个商品吗',
      })
          .then(() => {
            apiDelete('goods',{id:this.list[index]['goodsId']}).then((resp)=>{
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
            key:value,
            current:1,
            size:10
          };
          this.onRefresh();
          break;
        case 1:
          this.getData={
            id:value,
            current:1,
            size:10
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