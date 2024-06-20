<template>
  <v-avatar color="white darken-1" style="margin-left: 1.3em;" class="counter-number"
  >{{ level }}</v-avatar
  >
  <v-progress-linear
    model-value="50"
    height="20"
    color="yellow"
    class="progress-bar"
  />
</template>

<script lang="ts">
import { fetchAvatar } from '../../objects/avatar';

export default {
  data() {
    return {
      level: 0
    };
  },
  async mounted() {
    const user = await fetchAvatar();
    this.level = user.level;
  }
};
</script>

<style scoped>

</style>
