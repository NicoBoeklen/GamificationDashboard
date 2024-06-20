<template>
  <v-avatar color="white darken-1" style="margin-left: 1.3em;" class="counter-number"
  >{{realLevel}}</v-avatar
  >
  <v-progress-linear
v-model="progress"
    height="20"
    color="yellow" style="border-radius: 7px;">
  </v-progress-linear>
</template>

<script lang="ts">
import { fetchAvatar } from '../../objects/avatar';

export default {
  data() {
    return {
      level: 0,
      realLevel: 0,
      progress: 0
    };
  },
  async mounted() {
    const user = await fetchAvatar();
    this.level = user.level;
    this.realLevel = Math.round((1+Math.sqrt(1+(this.level/12.5)))/2);
    this.progress = Math.round((((1 + Math.sqrt(1 + (this.level / 12.5))) / 2) - this.realLevel)*100);
  }
};
</script>

<style scoped>

</style>
