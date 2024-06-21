<template>
  <v-container fluid>
    <v-row>
      <v-col cols="12" v-if="Object.keys(leaderboard).length > 0">
        <v-row v-for="(value, name, index) in leaderboard" :key="name">
          <v-col style="padding-top:0; padding-bottom:0;margin: 0 auto" v-if="index<5" cols="12">
            <v-card style="padding:0; margin: 0" :class="{'highlighted-card': userName === name}" class="pa-3 mb-4">
              <div :class="{'text-h5': index < 5, 'text-h4': index < 3}">{{ index + 1 }}. {{ name }}</div>
              <div class="text-caption text-uppercase">Score: {{ value }}</div>
            </v-card>
          </v-col>
        </v-row>
      </v-col>
      <p v-else style="margin: 0 auto">Loading <v-progress-circular indeterminate color="white" size="24"></v-progress-circular>
      </p>
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
.highlighted-card {
  background-color: #FFD700; /* Gold color for highlighted card */
}

.text-h4 {
  font-weight: bold;
}

.text-caption {
  font-size: 0.75rem;
}
</style>
