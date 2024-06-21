<template>
  <div style="padding: 1em">
    <div>
      <span>
        <span :style="{ color: commentColor, fontSize:'1.5em'}">{{ commentCount }}</span> Comments per Review
      </span>
    </div>
  </div>
</template>

<script>
import {fetchPullRequests} from "../../objects/pullRequests";

export default {
  data() {
    return {
      commentCount: 0,
    };
  },
  computed: {
    commentColor() {
      if (this.commentCount < 1) {
        return 'red';
      } else if (this.commentCount < 2) {
        return '#DAA520'; // Dark yellow
      } else {
        return 'green';
      }
    }
  },
  async mounted() {
    const pullRequest = await fetchPullRequests();
    this.commentCount = pullRequest.averageCommentsPerReviewUser;
  }
};
</script>

<style scoped>
</style>
