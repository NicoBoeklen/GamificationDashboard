<template>
  <div style="padding: 1em">
    <div>
      <strong style="color: green;">+</strong> <span >Additions: <span style="font-size: 1.5em">{{ additionCount }} </span></span>
    </div>
    <div>
      <strong style="color: red; position: relative; top: -0.3em;" >_</strong> <span > Deletions: <span style="font-size: 1.5em">{{ deletionCount }} </span></span>
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
    this.additionCount = commits.additionCountUser;
    this.deletionCount = commits.deletionCountUser;
  }
};
</script>

<style scoped>

</style>
