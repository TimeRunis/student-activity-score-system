<template>
  <div>
    <van-nav-bar
        :title=this.title
    />
  </div>
</template>

<script>
export default {
  name: "HeadBar",
  props:["title"],
  data(){
    return{
    }
  }
}
</script>

<style scoped>

</style>