<template>
  <div style="padding: 1em">
    <div>
      <strong style="color: green;">+</strong> <span >Additions: <span style="font-size: 1.5em">{{ additionCount }} </span></span>
    </div>
  </div>
</template>

<script>
import { fetchCommits } from '../../objects/commits';
import {fetchIssues} from "../../objects/issues";

export default {
  data() {
    return {
      totalFixedIssuesUser: 0,
      averageAgeOfOpenIssues: 0,
    };
  },
  async mounted() {
    const issue = await fetchIssues();
    this.totalFixedIssuesUser = issue.amountTotalClosedIssuesUser;
    this.averageTimeFixIssue = issue.averageTimeFixIssueOpenTeam;
  }
};
</script>

<style scoped>

</style>
