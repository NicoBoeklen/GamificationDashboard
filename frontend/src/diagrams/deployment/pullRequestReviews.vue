<template>
  <div style="padding: 1em">
    <div>
      <span>
        <span :style="{ color: reviewColor, fontSize:'1.5em'}">{{ reviewCount }}</span> Reviews
      </span>
    </div>
  </div>
</template>

<script>
import {fetchPullRequests} from "../../objects/pullRequests";

export default {
  data() {
    return {
      reviewCount: 0,
    };
  },
  computed: {
    reviewColor() {
      if (this.reviewCount < 10) {
        return 'red';
      } else if (this.reviewCount < 20) {
        return '#DAA520'; // Dark yellow
      } else {
        return 'green';
      }
    }
  },
  async mounted() {
    const pullRequest = await fetchPullRequests();
    this.reviewCount = pullRequest.numberReviewsUser;
  }
};
</script>

<style scoped>
</style>
