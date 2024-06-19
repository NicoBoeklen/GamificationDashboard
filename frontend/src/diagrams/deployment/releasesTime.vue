<template>
  <div style="padding: 1em">
    <div>
      <span>
        <span :style="{ fontSize:'1.5em'}">{{ releaseTime }}</span> Days
      </span>
    </div>
  </div>
</template>

<script>
import {fetchReleases} from "../../objects/releases";

export default {
  data() {
    return {
      releaseTime: 0,
    };
  },

  async mounted() {
    const release = await fetchReleases();
    this.releaseTime = release.averageTimeBetweenReleases;
  }
};
</script>

<style scoped>
</style>
