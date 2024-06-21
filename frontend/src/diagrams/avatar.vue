<template>
  <img :src="avatarUrl" alt="Avatar" style="width: 1.5em; height:1.5em; margin-right: 0.4em; border-radius: 50%;">
</template>

<script>
import { fetchAvatar } from '../objects/avatar';

export default {
  data() {
    return {
      avatarUrl: ''
    };
  },
  async mounted() {
      const user = await fetchAvatar();
      this.avatarUrl = user.avatarURL;
  }
};
</script>

<style scoped>
</style>
