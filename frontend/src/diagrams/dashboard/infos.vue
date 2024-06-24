<template>
  <v-card-title class="project-header">
    Your Project
  </v-card-title>
  <v-card-text>
    <div>
      <h3 class="project-subtitle">{{ repoName }}</h3>
    </div>
    <div style="margin-bottom: 0; padding-bottom: 0" class="project-description">
    Description: {{ repository.description }}
    <br />
    Created at: {{ formatDate(repository.created_at)}}
    <br />
    Updated at: {{ formatDate(repository.updated_at)}}
    </div>
  </v-card-text>
  <v-carousel hide-delimiters height="5em" cycle interval="15000" style="margin-bottom: 0.45em;">
    <v-carousel-item>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col class="text-center">
            <div>
              <h2>Languages:</h2>
              <p>{{ repository.language }}</p>
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
              <h2>Open Issues</h2>
              <p>{{ repository.openIssues }}</p>
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
              <h2>Contributors:</h2>
              <p>{{ repository.numberOfContributors }}</p>
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
              <h2>Open Pull Requests:</h2>
              <p>{{ repository.numberOfOpenPullRequests }}</p>
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
              <h2>Releases:</h2>
              <p>{{ repository.numberOfReleases }}</p>
            </div>
          </v-col>
        </v-row>
      </v-container>
    </v-carousel-item>
  </v-carousel>
</template>
<style>
.project-subtitle {
  font-size: 18px;
  margin-bottom: 0.5em;
}

.project-description {
  margin-bottom: 0.5em;
}

</style>
<script  lang="ts">
import {defineComponent, onMounted, ref} from "vue";
import {redirectCodeInsights, redirectInsights} from "../../objects/directions";
import 'moment/locale/de';
import {fetchRepository, Repository} from "../../objects/repository";
let repository = ref({} as Repository);
const repoName = ref('');
export default defineComponent({
  name: 'Leaderboard',
  methods: {redirectCodeInsights, redirectInsights, formatDate(dateString) {
      const options = {
        day: 'numeric',
        month: 'long',
        year: 'numeric',
        hour: 'numeric',
        minute: 'numeric',
        hour12: false,
      };
      const date = new Date(dateString);
      return date.toLocaleDateString('de-DE', options) + ' Uhr';
    }},
  components: {},
  setup(){
    onMounted(async () => {
      repoName.value = localStorage.getItem('repoName') || '';
     // console.log('Repo Name:', repoName.value);
      repository.value =  await fetchRepository();

    });



    return {repoName, repository};


  },
});


</script>
