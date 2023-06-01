<template>
  <div>
    <van-nav-bar
        title="订单管理"
        left-arrow
        @click-left="$router.back()"
    />
    <div style="display: flex;justify-content: center">
      <div class="cart_box">
        <van-grid column-num="1">
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
            <van-popover
                v-model="showSearchOption"
                trigger="click"
                :actions="menuItems"
                style="width: 100%"
                @select="onSelect"
            >
              <template #reference>
                <van-cell style="width: 100%" is-link>{{menuItems[searchOption]['text']}}</van-cell>
              </template>
            </van-popover>
          </van-popover>
        </van-grid>
        <van-popover
            v-model="showFilterOption"
            trigger="click"
            :actions="filterItems"
            style="width: 100%"
            @select="onSelectFilter"
        >
          <template #reference>
            <van-cell style="width: 100%" is-link>{{filterItems[filterOption]['text']}}</van-cell>
          </template>
        </van-popover>
      </div>
    </div>
    <admin-order-list ref="goodsList"></admin-order-list>
  </div>
</template>

<script>
import AdminOrderList from "@/components/Admin/Order/AdminOrderList";
export default {
  name: "AdminOrderView",
  components: {AdminOrderList},
  data(){
    return{
      college:{},
      isShowAdd:false,
      isShowEdit:false,
      showSearch:false,
      showSearchOption:false,
      showFilterOption:false,
      searchOption:0,
      filterOption:0,
      searchKey:"",
      menuItems:[
        { text: '订单ID' },
        { text: '用户ID' },
        { text: '商品ID'},
      ],
      filterItems:[
        { text: '全部' },
        { text: '已完成' },
        { text: '未完成'},
      ],
    }
  },
  methods:{
    showEdit(){
      this.isShowEdit=true;
      this.showPop=true;
    },
    search(){
      this.$refs.goodsList.search(this.searchKey,this.searchOption)
    },
    onSelect(action) {
      this.searchOption=this.menuItems.indexOf(action);
    },
    onSelectFilter(action) {
      this.filterOption=this.filterItems.indexOf(action);
      this.$refs.goodsList.search(this.filterOption,3);
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