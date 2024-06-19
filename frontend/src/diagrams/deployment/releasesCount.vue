<template>
  <div style="padding: 1em">
    <div>
      <span>
        <span :style="{ fontSize:'1.5em'}">{{ releaseCount }}</span> Releases
      </span>
    </div>
  </div>
</template>

<script>
import {fetchReleases} from "../../objects/releases";

export default {
  data() {
    return {
      releaseCount: 0,
    };
  },

  async mounted() {
    const release = await fetchReleases();
    this.releaseCount = release.numberOfReleases;
  }
};
</script>

<style scoped>
</style>
