<template>
    <Header/>
    <v-container class="container">
      <v-row class="counter-wrapper">
        <v-col cols="12" md="6" class="counter-box">
          <v-row class="align-center">
            <v-avatar color="white darken-1" size="37" class="counter-number">level</v-avatar>
            <v-progress-linear value="50" height="20" md="4" color="yellow " class="progress-bar" />
          </v-row>
        </v-col>
        <v-col cols="12" md="4" class="commitment-wrapper">
          <v-img
            :src="'https://cdn.builder.io/api/v1/image/assets/TEMP/a15a58212de51f3f8af173f0f65e334091b691385ab3ae8e623d83ef49bba2bc?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&'"
            class="commitment-logo"
            alt="Commitment Logo"
          />
          <p class="commitment-text">
            <span class="strong-text">400</span> <span>Days of</span> <span class="bold-text">Commit</span> <span>ment</span>
          </p>
        </v-col>
        <v-btn class="insights-button" color="grey darken-2">Insights</v-btn>
      </v-row>
    </v-container>


    <v-container class="main-container">
      <v-row>
        <v-col cols="12" md="4" class="content-section" >
          <v-card class="project-info">
            <v-card-title class="project-header">
              <div>
                <h1 class="project-title">Your Project</h1>
                <p class="project-subtitle">Softwarepraktikum</p>
              </div>
            </v-card-title>
            <v-card-text class="project-description">
              Description: dkaskdsakd dsakdkasdk adkadkadksa
              <br />
              Created at: 25.05.2024
              <br />
              Updated at: 25.05.2024
            </v-card-text>
            <v-card-actions class="project-stats"> </v-card-actions>
          </v-card>


          <v-card class="badges-section">
            <v-card-title class="section-title">Badges</v-card-title>
            <v-card-text class="badges-container">
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
                <p class="team-quest">Team:create 3 commits
                  <v-progress-linear value="50" height="21" color="green darken-2" class="team-progress-bar" />
                </p>
                <p class="individual-quest">Individual:fix 1 Issue
                  <v-progress-linear value="25" height="21" color="orange" class="individual-progress-bar" />
                </p>
              </div>
            </v-card-text>
          </v-card>

          <v-card class="milestones">
            <v-card-title class="section-title">Milestones</v-card-title>
            <v-card-text>
              <div class="milestone-details">
                <p class="individual-milestone">Individual: Write 1000 LoC
                  <v-progress-linear value="75" height="21" color="green darken-2" class="individual-progress-bar" />
                </p>
                <p class="team-milestone">Team: 100% Test Coverage
                  <v-progress-linear value="60" height="21" color="orange" class="team-progress-bar" />
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
            <v-row>

                <v-col cols="12" sm="6" md="4" v-for="leader in leaders" :key="leader.rank">
                  <v-card class="leaderboard-item">
                    <p class="rank">{{ leader.rank }}</p>
                    <v-avatar class="avatar">
                      <v-img :src="leader.avatar" :alt="`Avatar of ${leader.name}`"></v-img>
                    </v-avatar>
                    <p class="name">{{ leader.name }}</p>
                    <v-img :src="leader.badge" :alt="`Rank ${leader.rank} Badge`" height="32" width="32"></v-img>
                  </v-card>
                </v-col>

            </v-row>
          </v-card>
        </v-col>
      </v-row>

    </v-container>







</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Header from "./Header.vue";


export default defineComponent({
  name: 'Leaderboard',
  components: {Header},
  data() {
    return {
      leaders: [
        {
          rank: 1,
          avatar: 'https://cdn.builder.io/api/v1/image/assets/TEMP/f77b48eb9d16d39e1c7ad7b5f8e2bc3847fd652604f32c03cdf04e486257dab6?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&',
          name: 'Klaus Mustermann',
          badge: 'https://cdn.builder.io/api/v1/image/assets/TEMP/1e8399cb57e88e5e9092227495545dfb63d59951c400cd17ae5479e6e0306f1b?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&',
        },
        {
          rank: 2,
          avatar: 'https://cdn.builder.io/api/v1/image/assets/TEMP/06d69d3f6c7276984cf4cf171b587ee1241dc88c0392aa0cd06cf87d09a74ceb?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&',
          name: 'Irene Mustermann',
          badge: 'https://cdn.builder.io/api/v1/image/assets/TEMP/e372f5bef41dc732160069bbfdec8c6db632f36f961f734593352180524e99ba?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&',
        },
        // Add more leaders as needed
      ],
    };
  },
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
  align-items: center;
  justify-content: space-between;
}

.stats-details {
  display: flex;
  align-items: center;
}

.pull-requests,
.issues {
  margin-right: 10px;
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

.quest-progress,
.milestone-progress {
  display: flex;
  align-items: center;

}

.badge {
  margin-right: 10px;
  height: 50px; /* Adjust height as needed */
  width: auto; /* Maintain aspect ratio */
}

.additional-info {
  margin-bottom: 20px;
}

.leaderboard {
  margin-bottom: 20px;
  float: right;
}

.leaderboard-list {
  margin-top: 10px;
}

.leaderboard-item {
  margin-bottom: 10px;
}

.rank {
  font-size: 20px; /* Adjust font size as needed */
  font-weight: bold;
  margin-bottom: 5px;
}

.avatar {
  margin-bottom: 5px;
}

.name {
  font-size: 16px; /* Adjust font size as needed */
  margin-bottom: 5px;
}

.avatar img {
  border-radius: 50%;
}
</style>
