<template>
  <div id="Home">
    <div class="line-border">
      <left/>
    </div>
    <div class="line-border">
      <center/>
    </div>
    <div class="line-border">
      <right/>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

import Left from "@/views/details/Left";
import Center from "@/views/details/Center";
import Right from "@/views/details/Right";

export default {
  name: 'Home',
  components: {
    Left, Center, Right
  }
}
</script>

<style lang="less" scoped>
#Home {
  display: grid;
  grid-template-columns: 1fr 3.5fr 1fr;
  grid-column-gap: 0.2rem;
  .line-border {
    //border: red solid 1px;
    height: 60.5rem;
  }
}
</style>
