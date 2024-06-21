<template>
  <div style="padding: 1em">
    <div>
      <span>
        <span :style="{ color: issueColor,fontSize:'1.5em'}">{{ fixedIssues }}</span> Issues fixed
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
      totalFixedIssues: 0
    };
  },
  computed: {
    issueColor() {
      if (this.fixedIssues/this.totalFixedIssues > 0.5) {
        return 'green';
      } else if (this.fixedIssues/this.totalFixedIssues > 0.33) {
        return '#DAA520'; // Dark yellow
      } else {
        return 'red';
      }
    }
  },
  async mounted() {
    const issue = await fetchIssues();
    this.fixedIssues = issue.amountTotalClosedIssuesUser;
    this.totalFixedIssues = issue.amountFixedIssuesTeam;
  }
};
</script>

<style scoped>
</style>
