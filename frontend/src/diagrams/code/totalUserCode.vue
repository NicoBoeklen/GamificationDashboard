<template>
  <div style="padding: 1em">
    <div>
      <strong style="color: green;">+ </strong> <span > <span style="font-size: 1.5em">{{ additionCount }} </span> Additions</span>
    </div>
    <div>
      <strong style="color: red; position: relative; top: -0.5em;" >_ </strong> <span > <span style="font-size: 1.5em">{{ deletionCount }} </span> Deletions</span>
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
