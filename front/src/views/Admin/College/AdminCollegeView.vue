<template>
  <div>
    <van-nav-bar
        title="学院管理"
        left-arrow
        @click-left="$router.back()"
    />
    <div style="display: flex;justify-content: center">
      <div class="cart_box">
        <van-grid column-num="2">
          <van-grid-item icon="add-o" @click="showAdd" />
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
    <van-popup v-model="showPop" position="bottom" style="height: 25%" @close="closePop">
      <admin-college-edit name="add" v-show="isShowAdd" type="add" ref="addCollege"></admin-college-edit>
      <admin-college-edit name="edit" v-show="isShowEdit" type="edit" ref="editCollege"></admin-college-edit>
    </van-popup>
    <admin-college-list ref="collegeList"></admin-college-list>
  </div>
</template>

<script>

import AdminCollegeList from "@/components/Admin/College/AdminCollegeList";
import AdminCollegeAdd from "@/components/Admin/College/AdminCollegeEdit";
import AdminCollegeEdit from "@/components/Admin/College/AdminCollegeEdit";
import {apiPost, apiPut} from "@/util/Api";
import {Notify} from "vant";
export default {
  name: "AdminCollegeView",
  components: {AdminCollegeEdit, AdminCollegeAdd, AdminCollegeList},
  data(){
    return{
      college:{},
      showPop:false,
      isShowAdd:false,
      isShowEdit:false,
      showSearch:false,
      showSearchOption:false,
      searchOption:0,
      searchKey:"",
      menuItems:[
        { text: '学院ID' },
        { text: '学院名'},
      ],
    }
  },
  methods:{
    closePop(){
      setTimeout(()=>{
        this.showPop=false;
        this.isShowEdit=false;
        this.isShowAdd=false;
      },200);
    },
    setCollege(college){
      this.college=college;
    },
    confirmEdit() {
      apiPut("college",this.college).then((resp)=>{
        if(resp.data['code']===0){
          Notify({type:"success",message:resp.data['message']});
          setTimeout(()=>{location.reload()},500);
        }else {
          Notify({type:"danger",message:resp.data['message']});
        }
      })
    },
    confirmAdd() {
      apiPost("college",this.college).then((resp)=>{
        if(resp.data['code']===0){
          Notify({type:"success",message:resp.data['message']});
          setTimeout(()=>{location.reload()},500);
        }else {
          Notify({type:"danger",message:resp.data['message']});
        }
      })
    },
    showEdit(){
      this.isShowEdit=true;
      this.showPop=true;
    },
    showAdd(){
      this.isShowAdd=true;
      this.showPop=true;
    },
    search(){
      this.$refs.collegeList.search(this.searchKey,this.searchOption)
    },
    showEditCollege(college){
      this.showEdit();
      this.college=college;
      this.$nextTick(() => {
        this.$refs.editCollege.setCollege(college);
      })

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