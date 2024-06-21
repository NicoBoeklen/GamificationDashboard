<template>
  <div style="padding: 1em">
    <div>
      <span>
        <span :style="{ color: issueColor,fontSize:'1.5em'}">{{ averageTimeToFixIssueInDaysTeam }}</span> Days
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
  computed: {
    issueColor() {
      if (this.averageTimeToFixIssueInDaysTeam < 7) {
        return 'green';
      } else if (this.averageTimeToFixIssueInDaysTeam < 14) {
        return '#DAA520'; // Dark yellow
      } else {
        return 'red';
      }
    }
  },

  async mounted() {
    const issue = await fetchIssues();
    this.averageTimeToFixIssueInDaysTeam = issue.averageTimeToFixIssueInDaysTeam;
  }
};
</script>

<style scoped>
</style>
