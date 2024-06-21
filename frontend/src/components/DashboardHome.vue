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

        <v-col class="commitment-wrapper">
          <div class="d-flex align-center">
            <!--<v-img
              :src="'https://cdn.builder.io/api/v1/image/assets/TEMP/a15a58212de51f3f8af173f0f65e334091b691385ab3ae8e623d83ef49bba2bc?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&'"
              class="commitment-logo"
              alt="Commitment Logo"
            />
            <p class="commitment-text">
              <span class="strong-text">400</span> <span>Days of</span> <span class="bold-text">Commit</span>
              <span>ment</span>
            </p>-->
            <div class="right-align-container">
              <p>To update your project data please re-login!</p>
              <v-btn @click="redirectCodeInsights()" variant="outlined" class="mr-3">Insights
              </v-btn>
            </div>
          </div>
        </v-col>
      </v-row>
    </v-app-bar>
  </v-container>


  <v-container fluid class="main-container" style="margin:0; padding-top: 0">
    <v-row>
      <v-col cols="12" md="4" class="content-section">
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
          <v-card-title class="d-flex align-items-center">Leaderboard
            <v-icon small class="info-icon" @mouseover="showTooltip[0] = true" @mouseleave="showTooltip[0] = false">
              mdi-information
            </v-icon>
            <span v-if="showTooltip[0]" class="tooltip">Sum of your 4 Skill Values</span>
          </v-card-title>
          <leaderboard-diagramm-component></leaderboard-diagramm-component>
        </v-card>
        <v-card class="skills-section">
          <v-card-title class="d-flex align-items-center section-title">Your Skill
            <v-icon small class="info-icon" @mouseover="showTooltip[1] = true" @mouseleave="showTooltip[1] = false">
              mdi-information
            </v-icon>
            <span v-if="showTooltip[1]" class="tooltip">10 means you have the highest value. 8 would be 80% of the maximum</span>
          </v-card-title>
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
import Milestones from "../diagrams/dashboard/milestones.vue";
import Quests from "../diagrams/dashboard/quests.vue";
import Badges from "../diagrams/dashboard/badges.vue";
import Infos from "../diagrams/dashboard/infos.vue";

export default defineComponent({
  name: 'Leaderboard',
  methods: {redirectCodeInsights, redirectInsights},
  components: {
    Infos,
    Quests,
    Milestones,
    Badges,
    HeaderComponent,
    SkillDiagrammComponent,
    LeaderboardDiagrammComponent,
    Level
  },
  data() {
    return {
      showTooltip: [false, false],
    }
  },
  mounted() {
    if (localStorage.getItem('loaded') === "falsch") {
      localStorage.setItem('loaded', "true");
      console.log("Ich sollte die Seite aktualisieren")
      window.location.reload();
    }
  }
});
</script>

<style scoped>
.right-align-container {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  width: 100%;
}

.right-align-container p {
  margin: 0;
  margin-right: 1em;
}

.info-icon {
  margin-left: 0.3em;
  font-size: 0.875rem;
  cursor: pointer;
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
  top: 1em;
  bottom: 85%;
  left: 50%;
  transform: translateX(-50%);
}

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
.leaderboard-section {
  margin-bottom: 1em;
}

.section-title {
  font-size: 20px; /* Adjust font size as needed */
  margin-bottom: 1em;
}

</style>
