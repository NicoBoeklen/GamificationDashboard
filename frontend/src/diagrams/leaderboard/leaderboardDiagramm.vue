<template>
  <v-container fluid>
    <v-row>
      <v-col cols="12">
        <v-row>
          <v-col v-for="(value, name, index) in leaderboard" :key="name" cols="12">
            <v-card v-if="index<5" :class="{'highlighted-card': userName === name}" class="pa-3 mb-4">
              <div :class="{'text-h5': index < 5, 'text-h4': index < 3}">{{ index + 1 }}. {{ name }}</div>
              <div class="text-caption text-uppercase">Score: {{ value }}</div>
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
      leaderboard: {},
      userName: ''
    };
  },
  async mounted() {
    try {
      this.userName = localStorage.getItem('userName');
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

.highlighted-card {
  background-color: #FFD700; /* Gold color for highlighted card */
}

.text-h4 {
  font-weight: bold;
}

.text-caption {
  font-size: 0.875rem;
}
</style>
