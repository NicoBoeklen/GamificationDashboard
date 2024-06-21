<template>
  <div style="padding: 1em">
    <div>
      <span>
        <span :style="{ color: issueColor,fontSize:'1.5em'}">{{ averageAgeOfOpenIssues }}</span> Days
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
  computed: {
    issueColor() {
      if (this.averageAgeOfOpenIssues < 7) {
        return 'green';
      } else if (this.averageAgeOfOpenIssues < 14) {
        return '#DAA520'; // Dark yellow
      } else {
        return 'red';
      }
    }
  },
  async mounted() {
    const issue = await fetchIssues();
    this.averageAgeOfOpenIssues = Math.round(issue.averageAgeOfOpenIssues*10)/10;
  }
};
</script>

<style scoped>
</style>
