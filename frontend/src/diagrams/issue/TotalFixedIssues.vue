<template>
  <div style="padding: 1em">
    <div>
      <span>
        <span :style="{ fontSize:'1.5em'}">{{ fixedIssues }}</span> Issues fixed
      </span>
    </div>
  </div>
</template>

<script>
import {fetchIssues} from "../../objects/issues";

export default {
  data() {
    return {
      fixedIssues: 0,
    };
  },

  async mounted() {
    const issue = await fetchIssues();
    this.fixedIssues = issue.amountTotalClosedIssuesUser;
  }
};
</script>

<style scoped>
</style>
