<template>
  <div id="app">
    <router-view />
    <BottomMenu></BottomMenu>
  </div>
</template>

<style>

</style>
<script>
import BottomMenu from "@/components/BottomMenu";
export default {
  components: {
    BottomMenu
  },

}
</script>