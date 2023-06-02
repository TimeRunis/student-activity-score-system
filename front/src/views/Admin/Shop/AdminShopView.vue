<template>
  <div>
    <van-nav-bar
        title="商品管理"
        left-arrow
        @click-left="$router.back()"
    />
    <div style="display: flex;justify-content: center">
      <div class="cart_box">
        <van-grid column-num="2">
          <van-grid-item icon="add-o" @click="$router.push('/admin/shop/edit?isCreate=true')" />
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
      </div>
    </div>
    <admin-shop-list ref="goodsList"></admin-shop-list>
  </div>
</template>

<script>
import AdminShopList from "@/components/Admin/Shop/AdminShopList";
export default {
  name: "AdminShopView",
  components: {AdminShopList},
  data(){
    return{
      user:{},
      showPop:false,
      showAdd:false,
      showInfo:false,
      showSearch:false,
      showSearchOption:false,
      searchOption:0,
      searchKey:"",
      menuItems:[
        { text: '标题或内容查找' },
        { text: '商品ID'},
      ],
    }
  },
  methods:{
    closePop(){
      setTimeout(()=>{
        this.showInfo=false;
        this.showAdd=false;
      },200);
    },
    search(){
      this.$refs.goodsList.search(this.searchKey,this.searchOption);
    },
    showUserInfo(userInfo){
      this.user=userInfo;
      this.showInfo=true;
      this.showPop=true;
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