<template>
  <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
    <van-list
        v-model="loading"
        :finished="finished"
        finished-text="没有更多了"
        @load="onLoad"
        style="display: flex;flex-wrap: wrap;justify-content: center"
    >
        <div class="gl_box" v-for="(item,i) in list" :key="i" @click="toDetail(item['goodsId'])">
          <van-card
              currency="积分"
              :num="item['goodsNumber']"
              :price="item['goodsPrice']"
              :title="item['goodsName']"
              :thumb="item['headImg']"
          >
          </van-card>
        </div>
    </van-list>
  </van-pull-refresh>
</template>

<script>
import {apiGet} from "@/util/Api";

export default {
  name: "GoodsList",
  props:['isStart'],
  data() {
    return {
      getData:{
        key:'',
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
  beforeMount() {
    if(this.isStart===true){
      apiGet("goods",this.getData).then((resp)=>{
        this.list=resp.data['data']['records']
        this.listMaxSize=resp.data['data']['total']
        this.getData["current"]+=1
      })
    }else {
      this.finished=true;
    }
  },
  methods: {
    onLoad() {
      setTimeout(() => {
        if (this.refreshing) {
          this.list = [];
          this.listMaxSize=1;
          this.getData.current=1;
          this.refreshing = false;
        }
        if (this.list.length >= this.listMaxSize) {
          this.finished = true;
        }else {
          //获取数据
          apiGet("goods",this.getData).then((resp)=>{
            for(let i in resp.data['data']['records']){
              this.list.push(resp.data['data']['records'][i]);
            }
            this.listMaxSize=resp.data['data']['total']
            this.getData["current"]+=1;
          })
          // 加载状态结束
          this.loading = false;
        }
      }, 1000);


    },
    onRefresh() {
      // 清空列表数据
      this.finished = false;
      this.refreshing=true;
      // 重新加载数据
      // 将 loading 设置为 true，表示处于加载状态
      this.loading = true;
      this.onLoad();
    },
    toDetail(id){
      this.$router.push('/goods/detail?id='+id)
    },

  },
}
</script>

<style scoped>
.gl_box{
  border-radius: 0.5em;
  box-shadow: 0 0 10px 0 #e6e6e6;
  border-style: solid;
  border-width: 1px;
  border-color: #f0f0f0;;
  margin-top: 10px;
  margin-bottom: 15px;
  width: 90%;
}
.van-card__title{
  font-size: 20px;
  font-weight: bold;
}
.van-card__price-currency{
  font-size: 15px;
}
.van-card__title.van-multi-ellipsis--l2{
  height: 50px;
  padding-top: 10px;
}
</style>