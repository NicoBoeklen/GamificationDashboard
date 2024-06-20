<template>
  <div style="padding: 1em">
    <div>
      <span>
        <span :style="{ fontSize:'1.5em'}">{{ averageTimeToFixIssueInDaysTeam }}</span> Days
      </span>
    </div>
  </div>
</template>

<script>
import {fetchIssues} from "../../objects/issues";

export default {
  data() {
    return {
      averageTimeToFixIssueInDaysTeam: 0,
    };
  },

  async mounted() {
    const issue = await fetchIssues();
    this.averageTimeToFixIssueInDaysTeam = issue.averageTimeToFixIssueInDaysTeam;
  }
};
</script>

<style scoped>
</style>
