<template>
  <div>
    <van-nav-bar
        title="积分码管理"
        left-arrow
        @click-left="$router.back()"
    />
    <div style="display: flex;justify-content: center">
      <div class="cart_box">
        <van-grid column-num="2">
          <van-grid-item icon="add-o" @click="isShowAdd=true" />
          <van-grid-item icon="search" @click="showSearch=true" />
          <van-popover
              v-model="showSearch"
              trigger="click"
              placement="bottom-end"
          >
            <van-search
                v-model="searchKey"
                show-action
                placeholder="请输入搜索关键词"
                @search="search"
            >
              <template #action>
                <div @click="search">搜索</div>
              </template>
            </van-search>
          </van-popover>
        </van-grid>
      </div>
    </div>
    <van-popup v-model="showPop" position="bottom" style="height: 70%" @close="closePop">
      <admin-code-info style="margin: 20px" ref="info"></admin-code-info>
    </van-popup>
    <van-popup v-model="isShowAdd" position="bottom" style="height: 40%" @close="closePop">
      <admin-score-code-generator></admin-score-code-generator>
    </van-popup>
    <admin-score-code-list ref="list"></admin-score-code-list>
  </div>
</template>

<script>
import {apiGet, apiPost, apiPut} from "@/util/Api";
import {Notify} from "vant";
import AdminScoreCodeList from "@/components/Admin/ScoreCode/AdminScoreCodeList";
import AdminCodeInfo from "@/components/Admin/ScoreCode/AdminCodeInfo";
import AdminScoreCodeGenerator from "@/components/Admin/ScoreCode/AdminScoreCodeGenerator";
export default {
  name: "AdminScoreCodeView",
  components: {AdminScoreCodeGenerator, AdminCodeInfo, AdminScoreCodeList},
  data(){
    return{
      college:{},
      showPop:false,
      isShowAdd:false,
      isShowInfo:false,
      showSearch:false,
      searchKey:"",
    }
  },
  methods:{
    closePop(){
      setTimeout(()=>{
        this.showPop=false;
        this.isShowInfo=false;
        this.isShowAdd=false;
      },200);
    },
    showInfo(info){
      this.isShowInfo=true;
      this.showPop=true;
      this.$nextTick(() => {
        this.$refs.info.setData(info);
      })
    },
    search(){
      this.$refs.list.search(this.searchKey,this.searchOption)
    },

    onSelect(action) {
      this.searchOption=this.menuItems.indexOf(action);
    },
  },
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