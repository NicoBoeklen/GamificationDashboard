<template>
  <v-container fluid>
    <v-row>
      <v-col cols="6" v-for="n in 4" :key="n">
        <v-card>
          <template v-if="n == 1">
            <v-card-title class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
                <team-avatar-component></team-avatar-component>
              {{ diagrams[n-1].title }}
              <v-icon small class="info-icon" @mouseover="showTooltip[n-1] = true" @mouseleave="showTooltip[n-1] = false">mdi-information</v-icon>
              <span v-if="showTooltip[n-1]" class="tooltip">Only commits to main excluding merge commits</span>
            </v-card-title>
            <component :is="diagrams[n-1].component"></component>
          </template>
          <template v-if="n == 2">
            <v-row>
              <v-col>
                <v-card-title class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
                  <team-avatar-component></team-avatar-component>
                  {{ diagrams[1].title1 }}
                </v-card-title>
                <component :is="diagrams[1].component1"></component>
              </v-col>
              <v-col>
                <v-card-title class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
                  <team-avatar-component></team-avatar-component>
                  {{ diagrams[1].title2 }}
                  <v-icon small class="info-icon" @mouseover="showTooltip[1] = true" @mouseleave="showTooltip[1] = false">mdi-information</v-icon>
                  <span v-if="showTooltip[1]" class="tooltip">Average of last 5 commits</span>
                </v-card-title>
                <component :is="diagrams[1].component2"></component>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-card-title class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
                  <team-avatar-component></team-avatar-component>
                  {{ diagrams[1].title3 }}
                </v-card-title>
                <component :is="diagrams[1].component3"></component>
              </v-col>
            </v-row>
          </template>
          <template v-else-if="n==3">
            <v-row>
              <v-col>
                <v-card-title class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
                  <team-avatar-component></team-avatar-component>
                  {{ diagrams[2].title1 }}
                </v-card-title>
                <component :is="diagrams[2].component1"></component>
              </v-col>
              <v-col>
                <v-card-title class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
                  <team-avatar-component></team-avatar-component>
                  {{ diagrams[2].title2 }}
                  <v-icon small class="info-icon" @mouseover="showTooltip[2] = true" @mouseleave="showTooltip[2] = false">mdi-information</v-icon>
                  <span v-if="showTooltip[2]" class="tooltip">Average of last 5 commits</span>
                </v-card-title>
                <component :is="diagrams[2].component2"></component>
              </v-col>
            </v-row>
          </template>
          <template v-else-if="n==4">
            <v-row>
              <v-col>
                <v-card-title class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
                  <avatar-component></avatar-component>
                  {{ diagrams[3].title1 }}
                </v-card-title>
                <component :is="diagrams[3].component1"></component>
              </v-col>
              <v-col>
                <v-card-title class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
                  <avatar-component></avatar-component>
                  {{ diagrams[3].title2 }}
                  <v-icon small class="info-icon" @mouseover="showTooltip[3] = true" @mouseleave="showTooltip[3] = false">mdi-information</v-icon>
                  <span v-if="showTooltip[3]" class="tooltip">Average of last 5 commits</span>
                </v-card-title>
                <component :is="diagrams[3].component2"></component>
              </v-col>
            </v-row>
          </template>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import PullRequest from '../diagrams/deployment/pullRequestCircle.vue';
import UserCommits from '../diagrams/code/userCommits.vue';
import PullRequestSize from '../diagrams/deployment/pullRequestSize.vue'
import PullRequestProcessTime from "../diagrams/deployment/pullRequestProcessTime.vue";
import PullRequestComments from "../diagrams/deployment/pullRequestComments.vue";
import PullRequestReviews from "../diagrams/deployment/pullRequestReviews.vue";
import ReleasesCount from "../diagrams/deployment/releasesCount.vue";
import ReleasesTime from "../diagrams/deployment/releasesTime.vue";
import ReleaseChart from "../diagrams/deployment/releaseChart.vue";
import AvatarComponent from '../diagrams/avatar.vue';
import TeamAvatarComponent from '../diagrams/teamAvatar.vue';

export default {
  name: 'CardGrid',
  components: { AvatarComponent, TeamAvatarComponent },
  data() {
    return {
      diagrams: [
        { component: PullRequest, title: 'Team Pull Requests Closed/Open' },
        {
          title1: 'Total Releases',
          component1: ReleasesCount,
          title2: 'Average Time between Releases',
          component2: ReleasesTime,
          component3: ReleaseChart, title3: 'Recent Releases'
        },
        {
          title1: 'Average Pull Request Size',
          component1: PullRequestSize,
          title2: 'Average Process Time',
          component2: PullRequestProcessTime
        },
        {
          title1: 'Average number of comments',
          component1: PullRequestComments,
          title2: 'Amount of Reviews performed',
          component2: PullRequestReviews
        },
      ],
      showTooltip: [false, false, false, false], // Initialize array with false values
    };
  },
};
</script>

<style scoped>
.tooltip {
  position: absolute;
  background-color: rgba(0, 0, 0, 0.8);
  color: white;
  padding: 0.3em 0.5em;
  border-radius: 4px;
  font-size: 0.75rem;
  white-space: nowrap;
  z-index: 10000;
  bottom: 1.5em;
  left: 50%;
  transform: translateX(-50%);
}

.v-card-title {
  position: relative;
}

.info-icon {
  margin-left: 0.3em;
  font-size: 0.875rem;
  cursor: pointer;
}
</style>
