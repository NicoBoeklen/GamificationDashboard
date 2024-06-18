<template>
  <div style="padding: 1em">
    <div style="padding-bottom: 0.1em">Last 5 Working Days:</div>
    <div :style="{ fontSize: '2em'}">
      <span :style="{color: productivityColor}" >{{ productivity }}</span>
      <span style="font-size: 0.5em;" > LoC / Working Day</span>
    </div>
    <div style="font-size: 0.75em;">Working Day = Day with Commit</div>
  </div>
</template>

<script>
import { fetchCommits } from '../../objects/commits';
export default {
  data() {
    return {
      productivity: 0
    };
  },
  computed: {
    productivityColor() {
      if (this.productivity > 100) {
        return 'green';
      } else if (this.productivity > 50) {
        return '#DAA520';
      } else {
        return 'red';
      }
    }
  },
  async mounted() {
    const commits = await fetchCommits();
    this.productivity = commits.productivityUser;
  }
};
</script>

<style scoped>

</style>
