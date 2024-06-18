<template>
  <v-container fluid>
    <v-row>
      <v-col cols="12">
        <v-row>
          <v-col v-for="(value, name, index) in leaderboard" :key="name" cols="12">
            <v-card class="pa-3 mb-4">
              <div class="text-h5">{{ index + 1 }}. {{ name }}</div>
              <div class="text-caption text-uppercase">Value: {{ value }}</div>
            </v-card>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { fetchLeaderboard } from '../../objects/leaderboard';

export default {
  data() {
    return {
      leaderboard: {}
    };
  },
  async mounted() {
    try {
      const data = await fetchLeaderboard();
      this.leaderboard = data.leaderboardMap;
    } catch (error) {
      console.error("Failed to fetch leaderboard:", error);
    }
  }
};
</script>

<style scoped>
.v-card {
  margin-bottom: 1em;
}
.text-h5 {
  font-size: 1.25rem;
  font-weight: bold;
}
.text-caption {
  font-size: 0.875rem;
}
</style>
