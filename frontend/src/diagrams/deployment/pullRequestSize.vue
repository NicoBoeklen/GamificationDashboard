<template>
  <div style="padding: 1em">
    <div>
      <span>
        <span :style="{ color: additionColor, fontSize:'1.5em'}">{{ additionCount }}</span> Additions
      </span>
    </div>
    <div>
      <span>
        <span :style="{ color: commitsColor, fontSize:'1.5em' }">{{ commitsCount }}</span> Commits
      </span>
    </div>
  </div>
</template>

<script>
import {fetchPullRequests} from "../../objects/pullRequests";

export default {
  data() {
    return {
      additionCount: 0,
      commitsCount: 0
    };
  },
  computed: {
    additionColor() {
      if (this.additionCount < 1000) {
        return 'green';
      } else if (this.additionCount < 2000) {
        return '#DAA520'; // Dark yellow
      } else {
        return 'red';
      }
    },
    commitsColor() {
      if (this.commitsCount < 30) {
        return 'green';
      } else if (this.commitsCount < 45) {
        return '#DAA520'; // Dark yellow
      } else {
        return 'red';
      }
    }
  },
  async mounted() {
    const pullRequest = await fetchPullRequests();
    this.additionCount = pullRequest.averageAdditionsPerPRTeam;
    this.commitsCount = pullRequest.averageCommitsPerPRTeam;
  }
};
</script>

<style scoped>
</style>
