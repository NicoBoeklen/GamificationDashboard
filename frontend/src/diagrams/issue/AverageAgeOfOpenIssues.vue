<template>
  <div style="padding: 1em">
    <div>
      <span>
        <span :style="{ fontSize:'1.5em'}">{{ averageAgeOfOpenIssues }}</span> Days
      </span>
    </div>
  </div>
</template>

<script>
import {fetchIssues} from "../../objects/issues";

export default {
  data() {
    return {
      averageAgeOfOpenIssues: 0,
    };
  },

  async mounted() {
    const issue = await fetchIssues();
    this.averageAgeOfOpenIssues = issue.averageAgeOfOpenIssues;
  }
};
</script>

<style scoped>
</style>
