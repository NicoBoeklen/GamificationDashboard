<template>
  <v-card-title class="section-title">Milestones</v-card-title>
  <v-card-text>
    <div class="milestone-details">
      <p class="individual-milestone">Individual: Write 1000 LoC
        <v-progress-linear model-value="75" height="21" color="rgba(8, 98, 189, 0.5)" class="individual-progress-bar" />
      </p>
      <p class="team-milestone">Team: 100% Test Coverage
        <v-progress-linear model-value="60" height="21" color="rgba(8, 98, 189, 0.5)" class="team-progress-bar" />
      </p>
    </div>
  </v-card-text>
</template>
<script setup lang="ts">
</script>
