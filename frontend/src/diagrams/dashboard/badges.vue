<template>
  <v-card-title class="section-title">Badges</v-card-title>
  <v-card-text>
    <div v-if="codeAchievements.length > 0">
      <h3>Code</h3>
      <div class="badges-container">
        <div v-for="(achievement, index) in codeAchievements" :key="index" class="badge-wrapper">
          <p v-if="achievement.achievement.image!=''" style="padding-top: 1em">{{ achievement.achievement.name }}</p>
          <v-img v-if="achievement.achievement.image!=''"
                 :src="getImageUrl(achievement.achievement.image)" class="badge"
                 :alt="achievement.achievement.name">
          </v-img>
        </div>
      </div>
    </div>

    <div v-if="issuesAchievements.length > 0">
      <h3>Issues</h3>
      <div class="badges-container">
        <div v-for="(achievement, index) in issuesAchievements" :key="index" class="badge-wrapper">
          <p v-if="achievement.achievement.image!=''" style="padding-top: 1em">{{ achievement.achievement.name }}</p>
          <v-img v-if="achievement.achievement.image!=''"
                 :src="getImageUrl(achievement.achievement.image)" class="badge"
                 :alt="achievement.achievement.name">
          </v-img>
        </div>
      </div>
    </div>

    <div v-if="deploymentAchievements.length > 0">
      <h3>Deployment</h3>
      <div class="badges-container">
        <div v-for="(achievement, index) in deploymentAchievements" :key="index" class="badge-wrapper">
          <p v-if="achievement.achievement.image!=''" style="padding-top: 1em">{{ achievement.achievement.name }}</p>
          <v-img v-if="achievement.achievement.image!=''"
                 :src="getImageUrl(achievement.achievement.image)" class="badge"
                 :alt="achievement.achievement.name">
          </v-img>
        </div>
      </div>
    </div>
  </v-card-text>
</template>


<script lang="ts">
import { fetchAchievement, Achievement } from '../../objects/achievements';
import { onMounted, ref } from "vue";

const achievementUser = ref([] as Achievement[]);
const codeAchievements = ref([] as Achievement[]);
const issuesAchievements = ref([] as Achievement[]);
const deploymentAchievements = ref([] as Achievement[]);

export default {
  setup() {
    onMounted(async () => {
      try {
        achievementUser.value = await fetchAchievement();
        sortAchievements();
      } catch (error) {
        console.error('Failed to fetch achievement:', error);
      }
    });

    const sortAchievements = () => {
      achievementUser.value.forEach(achievement => {
        const type = achievement.achievement.type;
        if (['commits', 'commitsTeam', 'linesOfCodeAdded', 'linesOfCodeDeleted', 'linesOfCodeAddedTeam'].includes(type)) {
          codeAchievements.value.push(achievement);
        } else if (['issues', 'issuesTeam'].includes(type)) {
          issuesAchievements.value.push(achievement);
        } else if (['pullRequests', 'pullRequestsTeam'].includes(type)) {
          deploymentAchievements.value.push(achievement);
        }
      });
    };

    const getImageUrl = (imageName: string) => {
      return new URL(`../../assets/${imageName}`, import.meta.url).href;
    };

    return {
      codeAchievements,
      issuesAchievements,
      deploymentAchievements,
      getImageUrl
    };
  }
};
</script>


<style>
.badge-wrapper {
display: flex;
flex-direction: column;
align-items: center;
}

.badge {
height: 7.5em;
width: 7.5em;
margin: 0 0.4em 0.4em 0.4em;
}

.badges-container {
display: flex;
flex-wrap: wrap;
justify-content: space-between;
}
.section-title {
  margin-bottom: 1em;
}
</style>
