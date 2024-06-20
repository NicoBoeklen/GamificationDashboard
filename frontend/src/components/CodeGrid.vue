<template>
  <v-container fluid>
    <v-row>
      <v-col cols="6" v-for="n in 4" :key="n">
        <v-card>
          <template v-if="n != 3">
            <v-card-title class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
              <div v-if="n != 1">
                <avatar-component></avatar-component>
              </div>
              <div v-else>
                <team-avatar-component></team-avatar-component>
              </div>
              {{ diagrams[n-1].title }}
              <v-icon small class="info-icon" @mouseover="showTooltip[n-1] = true" @mouseleave="showTooltip[n-1] = false">mdi-information</v-icon>
              <span v-if="showTooltip[n-1]" class="tooltip">Only commits to main excluding merge commits</span>
            </v-card-title>
            <component :is="diagrams[n-1].component"></component>
          </template>
          <template v-else>
            <v-row>
              <v-col>
                <v-card-title class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
                  <avatar-component></avatar-component>
                  {{ diagrams[2].title1 }}
                </v-card-title>
                <component :is="diagrams[2].component1"></component>
              </v-col>
              <v-col>
                <v-card-title class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
                  <avatar-component></avatar-component>
                  {{ diagrams[2].title2 }}
                  <v-icon small class="info-icon" @mouseover="showTooltip[2] = true" @mouseleave="showTooltip[2] = false">mdi-information</v-icon>
                  <span v-if="showTooltip[2]" class="tooltip">Average of last 5 commits</span>
                </v-card-title>
                <component :is="diagrams[2].component2"></component>
              </v-col>
            </v-row>
          </template>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import CodeGrowth from '../diagrams/code/codeGrowth.vue';
import UserCommits from '../diagrams/code/userCommits.vue';
import Productivity from '../diagrams/code/productivity.vue';
import TotalUserCode from '../diagrams/code/totalUserCode.vue';
import AverageCommitSize from '../diagrams/code/averageCommitSize.vue';
import AvatarComponent from '../diagrams/avatar.vue';
import TeamAvatarComponent from '../diagrams/teamAvatar.vue';

export default {
  name: 'CardGrid',
  components: { AvatarComponent, TeamAvatarComponent },
  data() {
    return {
      diagrams: [
        { component: CodeGrowth, title: 'Team Code Growth' },
        { component: UserCommits, title: 'Personal Commits' },
        {
          title1: 'Your Total Code',
          component1: TotalUserCode,
          title2: 'Average Commit Size',
          component2: AverageCommitSize
        },
        { component: Productivity, title: 'Your Productivity' }
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
  bottom: 1em;
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
