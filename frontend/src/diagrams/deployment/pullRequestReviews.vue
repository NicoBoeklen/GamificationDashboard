<template>
  <div style="padding: 1em">
    <div>
      <span>
        <span :style="{ color: reviewColor, fontSize:'1.5em'}">{{ reviewCount }}</span> Reviews
      </span>
    </div>
    <div>
      <span>
        <span :style="{ fontSize:'1.5em'}">{{ reviewCountTotal }}</span> Total Reviews
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
      reviewCountTotal: 0,
    };
  },
  computed: {
    reviewColor() {
      if (this.reviewCount/this.reviewCountTotal > 0.5) {
        return 'green';
      } else if (this.reviewCount/this.reviewCountTotal > 0.33) {
        return '#DAA520'; // Dark yellow
      } else {
        return 'red';
      }
    }
  },
  async mounted() {
    const pullRequest = await fetchPullRequests();
    this.reviewCount = pullRequest.numberReviewsUser;
    this.reviewCountTotal = pullRequest.numberReviewsTotal;
  }
};
</script>

<style scoped>
</style>
