<template>
  <img :src="avatarUrl" alt="Avatar" style="width: 2.5em; height:2.5em; margin-left: 1.3em; border-radius: 50%;">
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
