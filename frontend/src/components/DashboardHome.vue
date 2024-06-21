<template>
    <HeaderComponent/>
    <v-container fluid class="container">
      <v-app-bar app color="#5D5A5A" dark>
      <v-row>
        <v-col cols="12" md="4">
          <div class="d-flex align-center">
            <Level></Level>
          </div>
        </v-col>

        <v-col  class="commitment-wrapper">
          <div class="d-flex align-center">
          <v-img
            :src="'https://cdn.builder.io/api/v1/image/assets/TEMP/a15a58212de51f3f8af173f0f65e334091b691385ab3ae8e623d83ef49bba2bc?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&'"
            class="commitment-logo"
            alt="Commitment Logo"
          />
          <p class="commitment-text">
            <span class="strong-text">400</span> <span>Days of</span> <span class="bold-text">Commit</span> <span>ment</span>
          </p>
          <v-btn @click="redirectCodeInsights()"  variant="outlined" style="margin-left:1em;" class="mr-3">Insights</v-btn>
          </div>
        </v-col>
      </v-row>
      </v-app-bar>
    </v-container>


    <v-container fluid class="main-container" style="margin:0; padding-top: 0">
      <v-row>
        <v-col cols="12" md="4" class="content-section" >
          <v-card class="project-info">
            <infos></infos>
          </v-card>

          <v-card class="badges-section">
            <badges></badges>
          </v-card>
        </v-col>


        <v-col cols="12" md="4" class="Quest-Milestones">
          <v-card class="daily-quests">
            <quests></quests>
          </v-card>

          <v-card class="milestones">
            <milestones></milestones>
          </v-card>

        </v-col>


        <v-col cols="12" md="4" class="Leaderboard">
          <v-card class="leaderboard-section">
            <v-card-title>Leaderboard</v-card-title>
            <leaderboard-diagramm-component></leaderboard-diagramm-component>
          </v-card>
          <v-card class="skills-section">
            <v-card-title class="section-title">Your Skill</v-card-title>
            <skill-diagramm-component></skill-diagramm-component>
          </v-card>
        </v-col>
      </v-row>

    </v-container>

</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue';
import HeaderComponent from "./Header.vue";
import SkillDiagrammComponent from "../diagrams/skill/skillDiagramm.vue";
import LeaderboardDiagrammComponent from "../diagrams/leaderboard/leaderboardDiagramm.vue";
import Level from "../diagrams/level/level.vue";
import {redirectCodeInsights, redirectInsights} from "../objects/directions";
import {fetchRepository} from "../objects/repository";
import type {Repository} from "../objects/repository";
import Milestones from "../diagrams/dashboard/milestones.vue";
import Quests from "../diagrams/dashboard/quests.vue";
import Badges from "../diagrams/dashboard/badges.vue";
import Infos from "../diagrams/dashboard/infos.vue";
let repository = ref({} as Repository);
const repoName = ref('');

export default defineComponent({
  name: 'Leaderboard',
  methods: {redirectCodeInsights, redirectInsights},
  components: {Infos, Quests, Milestones, Badges, HeaderComponent, SkillDiagrammComponent, LeaderboardDiagrammComponent, Level},
});
</script>

<style scoped>
.commitment-logo {
  height: 50px; /* Adjust height as needed */
  width: auto; /* Maintain aspect ratio */
}

.commitment-text {
  font-size: 20px; /* Adjust font size as needed */
}

.content-section {
  margin-bottom: 1em;
}

.project-info {
  margin-bottom: 1em;
}

.daily-quests,
.milestones,
.leaderboard-section{
  margin-bottom: 1em;
}

.section-title {
  font-size: 20px; /* Adjust font size as needed */
  margin-bottom: 1em;
}

</style>
