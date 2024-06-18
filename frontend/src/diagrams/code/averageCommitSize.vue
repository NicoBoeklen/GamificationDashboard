<template>
  <div style="padding: 1em">
    <div>
      <strong style="color: green;">+</strong>
      <span>
        Additions: <span :style="{ color: additionColor, fontSize:'1.5em'}">{{ additionCount }}</span>
      </span>
    </div>
    <div>
      <strong style="color: red; position: relative; top: -0.3em;">_</strong>
      <span>
        Deletions: <span :style="{ color: deletionColor, fontSize:'1.5em' }">{{ deletionCount }}</span>
      </span>
    </div>
  </div>
</template>

<script>
import { fetchCommits } from '../../objects/commits';

export default {
  data() {
    return {
      additionCount: 0,
      deletionCount: 0
    };
  },
  computed: {
    additionColor() {
      if (this.additionCount < 50) {
        return 'green';
      } else if (this.additionCount < 100) {
        return '#DAA520'; // Dark yellow
      } else {
        return 'red';
      }
    },
    deletionColor() {
      if (this.deletionCount < 50) {
        return 'green';
      } else if (this.deletionCount < 100) {
        return '#DAA520'; // Dark yellow
      } else {
        return 'red';
      }
    }
  },
  async mounted() {
    const commits = await fetchCommits();
    this.additionCount = commits.averageAdditionsUser;
    this.deletionCount = commits.averageDeletionsUser;
  }
};
</script>

<style scoped>
</style>
