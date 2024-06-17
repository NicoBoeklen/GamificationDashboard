<template>
    <Header/>
    <v-container class="container">
      <v-row>
        <v-col cols="12" md="4">
          <div class="d-flex align-center">
            <v-avatar color="white darken-1" size="50" class="counter-number"
            >level</v-avatar
            >
            <v-progress-linear
              model-value="50"
              height="20"
              color="yellow"
              class="progress-bar"
            />
          </div>
        </v-col>

        <v-col  class="commitment-wrapper">
          <v-img
            :src="'https://cdn.builder.io/api/v1/image/assets/TEMP/a15a58212de51f3f8af173f0f65e334091b691385ab3ae8e623d83ef49bba2bc?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&'"
            class="commitment-logo"
            alt="Commitment Logo"
          />
          <p class="commitment-text">
            <span class="strong-text">400</span> <span>Days of</span> <span class="bold-text">Commit</span> <span>ment</span>
          </p>
        </v-col>
        <v-btn @click="redirectInsights()"  class="insights-button" color="grey darken-2">Insights</v-btn>

      </v-row>
    </v-container>


    <v-container class="main-container">
      <v-row>
        <v-col cols="12" md="4" class="content-section" >
          <v-card class="project-info">
            <v-card-title class="project-header">
              <div>
                <h1 class="project-title">Your Project</h1>
                <p class="project-subtitle">{{ repoName }}</p>
              </div>
            </v-card-title>
            <v-card-text class="project-description">

              <br />
              Created at: {{ repository.created_at }}
              <br />
              Updated at: {{ repository.updated_at }}
            </v-card-text>
              <v-carousel hide-delimiters height="300" cycle interval="15000">
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


          </v-card>


          <v-card class="badges-section">
            <v-card-title class="section-title">Badges</v-card-title>
            <v-card-text class="badges-container" style="display: flex">
              <v-img
                :src="'https://cdn.builder.io/api/v1/image/assets/TEMP/196eacaba534187ca530bde1ec70c21657fa163daf5d8f5b7a4d0d2d7bf2439c?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&'"
                class="badge"
                alt="Badge 1"
              />
              <v-img
                :src="'https://cdn.builder.io/api/v1/image/assets/TEMP/85e03766cb7ee30ce9f8855881450461c28cf54928fc1ef3fcb1d42a17dbb83a?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&'"
                class="badge"
                alt="Badge 2"
              />
              <v-img
                :src="'https://cdn.builder.io/api/v1/image/assets/TEMP/6aafcacc208e50dc71e7aeb9701f7d46d6f4389846c8f726b1f750773a7a62f5?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&'"
                class="badge"
                alt="Badge 3"
              />
              <v-img
                :src="'https://cdn.builder.io/api/v1/image/assets/TEMP/5e8e314d956b0814fc1a00042dc6bd098726acab30e645a4f3808aa2cf234fc8?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&'"
                class="badge"
                alt="Badge 4"
              />
            </v-card-text>
          </v-card>
        </v-col>


        <v-col cols="12" md="4" class="Quest-Milestones">
          <v-card class="daily-quests">
            <v-card-title class="section-title">Daily Quests</v-card-title>
            <v-card-text>
              <div class="quest-details">
                <p class="team-quest">Team:create 3 commits</p>
                  <v-progress-linear model-value="50" height="20" color="green darken-2"></v-progress-linear>

                <p class="individual-quest">Individual:fix 1 Issue </p>
                  <v-progress-linear model-value="25" height="20" color="orange"></v-progress-linear>

              </div>
            </v-card-text>
          </v-card>

          <v-card class="milestones">
            <v-card-title class="section-title">Milestones</v-card-title>
            <v-card-text>
              <div class="milestone-details">
                <p class="individual-milestone">Individual: Write 1000 LoC
                  <v-progress-linear model-value="75" height="21" color="green darken-2" class="individual-progress-bar" />
                </p>
                <p class="team-milestone">Team: 100% Test Coverage
                  <v-progress-linear model-value="60" height="21" color="orange" class="team-progress-bar" />
                </p>
              </div>
            </v-card-text>
          </v-card>

          <v-card class="skills-section">
            <v-card-title class="section-title">Your Skill</v-card-title>
            <v-card-text>
              <v-img
                :src="'https://cdn.builder.io/api/v1/image/assets/TEMP/81eabb22cac08b2287addddfd799f21f73a1d93835cc2bc7583fe1f65abe5281?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&'"
                class="skill"
                alt="Your skill representation"
              />
            </v-card-text>
          </v-card>

        </v-col>


        <v-col cols="12" md="4" class="Leaderboard">
          <v-card class="leaderboard-section">
            <v-card-title>Leaderboard</v-card-title>
              <v-list lines="one" v-for="leader in leaders" :key="leader.rank">
                <v-list-item
                  :key="leader.rank"
                  :title="leader.rank +'  '+ leader.name  "
                >
                </v-list-item>
              </v-list>
          </v-card>
        </v-col>
      </v-row>

    </v-container>







</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue';
import Header from "./Header.vue";
import {redirectInsights} from "../objects/directions";
import {fetchRepository} from "../objects/repository";
import type {Repository} from "../objects/repository";
let repository = ref({} as Repository);
const repoName = ref('');
export default defineComponent({
  name: 'Leaderboard',
  methods: {redirectInsights},
  components: {Header},
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

<style scoped>

.counter-box {
  margin-bottom: 20px;
}

.commitment-wrapper {
  display: flex;
  align-items: center;
  margin-bottom: 2px;
}

.commitment-logo {
  margin-right: 75px;
  height: 50px; /* Adjust height as needed */
  width: auto; /* Maintain aspect ratio */
}

.commitment-text {
  font-size: 20px; /* Adjust font size as needed */

}

.insights-button {
  margin-top: 20px;
  float: right;
}

.content-section {
  margin-bottom: 20px;
}

.project-info {
  margin-bottom: 20px;
}

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



.daily-quests,
.milestones {
  margin-bottom: 10px;
}

.section-title {
  font-size: 20px; /* Adjust font size as needed */
  margin-bottom: 10px;
}

.quest-details,
.milestone-details {
  margin-bottom: 1px;
}



.badge {
  margin-right: 10px;
  height: 50px; /* Adjust height as needed */
  width: auto;
  align-items: start
}


.avatar img {
  border-radius: 50%;
}
</style>
