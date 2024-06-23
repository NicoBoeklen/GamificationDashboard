<template>
  <v-card-title class="section-title">Badges</v-card-title>
  <v-card-text class="badges-container">
    <div v-for="(achievement, index) in achievementUser" :key="index">
      <p v-if="achievement.achievement.image!=''">{{ achievement.achievement.name }}</p>
      <v-img v-if="achievement.achievement.image!=''"
             :src="getImageUrl(achievement.achievement.image)" class="badge"
             alt={{achievement.achievement.name}}
             @mouseover="showTooltip[index] = true"
             @mouseleave="showTooltip[index] = false"
      =======
      <p>{{achievement.achievement.name}}</p>
      <v-img
          :src="achievement.achievement.image"
          class="badge"
          :alt="achievement.achievement.name"
          @mouseover="showTooltip[index] = true"
          @mouseleave="showTooltip[index] = false"
      ><span v-if="showTooltip[index]" class="tooltip">{{ achievement.achievement.description }} </span></v-img>
    </div>
  </v-card-text>
</template>
<script lang="ts">
import {fetchAchievement, Achievement} from '../../objects/achievements';
import {onMounted, ref} from "vue";

const achievementUser = ref([] as Achievement[]);
let showTooltip = ref([] as boolean[]);

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
    const getImageUrl = (imageName: string) => {
      return new URL(`../../assets/${imageName}`, import.meta.url).href;
    };
    return {
      achievementUser,
      showTooltip,
      getImageUrl
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
  z-index: 10000;
  bottom: 0em;
  left: 25%;
  transform: translateX(-50%);
}

.badges-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}
</style>

