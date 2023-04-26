<template>
  <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
    <van-list
        v-model="loading"
        :finished="finished"
        finished-text="没有更多了"
        @load="onLoad"
        style="display: flex;flex-wrap: wrap;justify-content: center"
    >
      <div class="ac_box" v-for="(item,i) in list" :key="i" @click="toDetail(item['activityId'])">
        <van-row type="flex" justify="center">
          <van-col span="8">
            <van-image
                width="100%"
                height="100%"
                fit="contain"
                :src="item['headImg']"
            />
          </van-col>
          <van-col span="16">
            <van-cell title-style="font-size:15px;font-weight:bold;" style="border-radius: 1em" :title="item['activityName']" />
            <van-cell style="border-radius: 1em" >
              开始时间:{{item['startTime']}}
              截止时间:{{item['deadLine']}}
            </van-cell>
          </van-col>
        </van-row>
      </div>
    </van-list>

  </van-pull-refresh>
</template>

<script>
import {apiGet} from "@/util/Api.js";
export default {
  name: "ActivityList",
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
      apiGet("activity",this.getData).then((resp)=>{
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
          //初始化数据
          this.list = [];
          this.listMaxSize=1;
          this.getData.current=1;
          this.refreshing = false;
        }
        if (this.list.length >= this.listMaxSize) {
          this.finished = true;
        }else {
          //获取数据
          apiGet("activity",this.getData).then((resp)=>{
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
      this.$router.push('/activity/detail?id='+id)
    },

  },
}
</script>

<style scoped>
.ac_box{
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