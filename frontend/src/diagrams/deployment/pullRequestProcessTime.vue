<template>
  <div style="padding: 1em">
    <div>
      <span>
        <span :style="{ color: timeColor, fontSize:'1.5em'}">{{ timeCount }}</span> Hours
      </span>
    </div>
  </div>
</template>

<script>
import {fetchPullRequests} from "../../objects/pullRequests";

export default {
  data() {
    return {
      timeCount: 0,
    };
  },
  computed: {
    timeColor() {
      if (this.timeCount < 24) {
        return 'green';
      } else if (this.timeCount < 48) {
        return '#DAA520'; // Dark yellow
      } else {
        return 'red';
      }
    }
  },
  async mounted() {
    const pullRequest = await fetchPullRequests();
    this.timeCount = pullRequest.averageProcessTimeInHours;
  }
};
</script>

<style scoped>
</style>
