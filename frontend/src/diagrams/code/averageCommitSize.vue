<template>
  <div style="padding: 1em">
    <div>
      <strong style="color: green;">+</strong> <span >Additions: {{ additionCount }}</span>
    </div>
    <div>
      <strong style="color: red; position: relative; top: -0.3em;" >_</strong> <span > Deletions: {{ deletionCount }} </span>
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
  async mounted() {
    const commits = await fetchCommits();
    this.additionCount = commits.averageAdditionsUser;
    this.deletionCount = commits.averageDeletionsUser;
  }
};
</script>

<style scoped>

</style>
