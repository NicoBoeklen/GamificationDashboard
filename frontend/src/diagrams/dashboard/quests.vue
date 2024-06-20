<template>
<v-card-title class="section-title">Daily Quests</v-card-title>
<v-card-text>
<div class="quest-details">
  <p class="team-quest">Team:create 3 commits</p>
  <v-progress-linear model-value="50" height="20" color="rgba(8, 98, 189, 0.5)"></v-progress-linear>

  <p class="individual-quest">Individual:fix 1 Issue </p>
  <v-progress-linear model-value="25" height="20" color="rgba(8, 98, 189, 0.5)"></v-progress-linear>

</div>
</v-card-text>
</template>
<style>
.quest-details,
.milestone-details {
  margin-bottom: 1px;
}

</style>
<script setup lang="ts">
</script>
