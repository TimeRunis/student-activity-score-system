<template>
  <div style="display: flex;justify-content: center;margin-top:20px">
    <div class="cart_box">
      <van-field  v-model="college.collegeName" placeholder="请输入学院名"></van-field>
      <van-button round type="primary" text="确认" style="border-radius: 0.5rem;width: 100%;margin-top: 10px" @click="clickButton"></van-button>
    </div>
  </div>
</template>

<script>
import {Notify} from "vant";

export default {
  name: "AdminCollegeEdit",
  props:["type"],
  data(){
    return{
      college:{
        collegeId:0,
        collegeName:""
      }
    }
  },
  methods:{
    clickButton(){
      switch (this.type){
          case "add":
            if(this.college.collegeName===""){
              Notify({type:"danger",message:"不可为空"})
            }else {
              this.confirmAdd();
            }
            break;
          case "edit":
            if(this.college.collegeName===""){
              Notify({type:"danger",message:"不可为空"})
            }else {
              this.confirmEdit();
            }
            break;
      }
    },
    confirmEdit(){
      this.$parent.$parent.setCollege(this.college);
      this.$parent.$parent.confirmEdit();
    },
    confirmAdd(){
      this.$parent.$parent.setCollege(this.college);
      this.$parent.$parent.confirmAdd();
    },
    setCollege(college) {
      this.college=college;
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