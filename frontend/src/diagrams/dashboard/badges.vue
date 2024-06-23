<template>
  <v-card-title class="section-title">Badges</v-card-title>
  <v-card-text class="badges-container">
    <div v-for="(achievement, index) in achievementUser" :key="index">
      <p>{{achievement.achievement.name}}</p>
      <v-img
        :src="'https://cdn.builder.io/api/v1/image/assets/TEMP/196eacaba534187ca530bde1ec70c21657fa163daf5d8f5b7a4d0d2d7bf2439c?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&'"
        class="badge"
        alt="Badge 1"
        @mouseover="showTooltip[index] = true"
        @mouseleave="showTooltip[index] = false"
      ><span v-if="showTooltip[index]" class="tooltip">{{ achievement.achievement.description }} </span></v-img>
    </div>
  </v-card-text>
</template>
<script lang="ts">
import {fetchAchievement, Achievement, AchievementObject} from '../../objects/achievements';
import {onMounted, ref} from "vue";

const achievementUser = ref([] as Achievement[]);
let showTooltip = ref([]);

export default {
  setup() {
    onMounted(async () => {
      try {
        achievementUser.value = await fetchAchievement();
        showTooltip.value = new Array(achievementUser.value.length).fill(false);
      } catch (error) {
        console.error('Failed to fetch achievement:', error);
      }
    });

    return {
      achievementUser,
      showTooltip
    };
  }
};
</script>
<style>
.badge {
  height: 5em;
  width: auto;
  padding: 0;
  margin: 0.2em 0;
}

.tooltip {
  position: absolute;
  background-color: rgba(0, 0, 0, 0.8);
  color: white;
  padding: 0.3em 0.5em;
  border-radius: 4px;
  font-size: 0.75rem;
  white-space: nowrap;
  z-index: 10000;
  bottom: 0.25em;
  left: 25%;
  transform: translateX(-50%);
}
</style>

