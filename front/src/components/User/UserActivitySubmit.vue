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
    <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
      <van-list
          v-model="loading"
          :finished="finished"
          finished-text="没有更多了"
          @load="onLoad"
          style="display: flex;flex-wrap: wrap;justify-content: center"
      >
        <div class="acs_box" v-for="(item,i) in list" :key="i" @click="toDetail(item['activityId'])">
          <van-row type="flex" justify="center">
            <van-col span="8">
              <van-image
                  width="100%"
                  height="100%"
                  fit="contain"
                  :src="item['activity']['headImg']"
              />
            </van-col>
            <van-col span="16">
              <van-cell title-style="font-size:15px;font-weight:bold;" style="border-radius: 1em" :title="item['activity']['activityName']" />
              <van-cell style="border-radius: 1em" >
                开始时间:{{item['activity']['startTime']}}
                截止时间:{{item['activity']['deadLine']}}
                报名时间:{{item['submitTime']}}
              </van-cell>
            </van-col>
          </van-row>
        </div>
      </van-list>
    </van-pull-refresh>
  </div>

</template>

<script>
import {apiGet} from "@/util/Api";

export default {
  name: "UserActivitySubmit",
  props:['isStart'],
  data() {
    return {
      getData:{
        userId:localStorage.getItem("userId"),
        current:1,
        size:10
      },
      list: [],
      listMaxSize:1,
      loading: false,
      finished: false,
      refreshing: false,
      value:"",
    };
  },
  beforeMount() {
    if(this.isStart===true){
      apiGet("activitySubmit",this.getData).then((resp)=>{
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
          apiGet("activitySubmit",this.getData).then((resp)=>{
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
    onSearch(){

    },

  },
}

</script>

<style scoped>
.acs_box{
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