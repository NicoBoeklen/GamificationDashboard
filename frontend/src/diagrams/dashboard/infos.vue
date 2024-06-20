<template>
  <v-card-title class="project-header">
    <div>
      <h1 class="project-title">Your Project</h1>
      <p class="project-subtitle">{{ repoName }}</p>
    </div>
  </v-card-title>
  <v-card-text style="margin-bottom: 0; padding-bottom: 0" class="project-description">
    Description: {{ repository.description }}
    <br />
    Created at: {{ repository.created_at }}
    <br />
    Updated at: {{ repository.updated_at }}
  </v-card-text>
  <v-carousel hide-delimiters height="10em" cycle interval="15000">
    <v-carousel-item>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col class="text-center">
            <div>
              <h2>Erstes Item</h2>
              <p>Beschreibung des ersten Items</p>
            </div>
          </v-col>
        </v-row>
      </v-container>
    </v-carousel-item>

    <v-carousel-item>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col class="text-center">
            <div>
              <h2>Zweites Item</h2>
              <p>Beschreibung des zweiten Items</p>
            </div>
          </v-col>
        </v-row>
      </v-container>
    </v-carousel-item>

    <v-carousel-item>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col class="text-center">
            <div>
              <h2>Drittes Item</h2>
              <p>Beschreibung des dritten Items</p>
            </div>
          </v-col>
        </v-row>
      </v-container>
    </v-carousel-item>
  </v-carousel>
</template>
<style>
.project-header {
  margin-bottom: 10px;
}

.project-title {
  margin-bottom: 5px;
  font-size: 24px; /* Adjust font size as needed */
}

.project-subtitle {
  font-size: 18px; /* Adjust font size as needed */
}

.project-description {
  margin-bottom: 10px;
}

.project-stats {
  display: flex;
  justify-content: space-between;
}


</style>
<script  lang="ts">
import {defineComponent, onMounted, ref} from "vue";
import {redirectCodeInsights, redirectInsights} from "../../objects/directions";
import Badges from "./badges.vue";
import Quests from "./quests.vue";
import Milestones from "./milestones.vue";
import HeaderComponent from "../../components/Header.vue";
import SkillDiagrammComponent from "../skill/skillDiagramm.vue";
import LeaderboardDiagrammComponent from "../leaderboard/leaderboardDiagramm.vue";
import Level from "../level/level.vue";
import {fetchRepository, Repository} from "../../objects/repository";
import Infos from "./infos.vue";
let repository = ref({} as Repository);
const repoName = ref('');
export default defineComponent({
  name: 'Leaderboard',
  methods: {redirectCodeInsights, redirectInsights},
  components: {},
  setup(){
    onMounted(async () => {
      repoName.value = localStorage.getItem('repoName') || '';
      console.log('Repo Name:', repoName.value);
      repository.value =  await fetchRepository();

    });



    return {repoName, repository};


  }

});


</script>
