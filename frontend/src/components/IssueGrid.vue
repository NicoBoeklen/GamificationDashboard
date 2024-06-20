<template>
  <v-container fluid>
    <v-row>
      <v-col cols="6">
        <v-card name="first Card">
          <v-row>
            <v-col>
              <v-card-title name="Open Issues" class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
                <team-avatar-component></team-avatar-component>
                Open Issues
              </v-card-title>
              <OpenIssues class="min-size-component"/>
            </v-col>
            <v-col >
              <v-card-title class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
                <team-avatar-component></team-avatar-component>
                Closed and Total Issues
                <!--<v-icon small class="info-icon" @mouseover="showTooltip[2] = true" @mouseleave="showTooltip[2] = false">mdi-information</v-icon>
                <span v-if="showTooltip[2]" class="tooltip">Average of last 5 commits</span>-->
              </v-card-title>
              <ClosedAndTotalIssues/>
            </v-col>
          </v-row>
        </v-card>
        <v-card style="margin-top: 1em;">
          <v-row>
            <v-col>
              <v-card-title class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
                <avatar-component></avatar-component>
                Your Fixed Issues
              </v-card-title>
              <TotalFixedIssues/>
            </v-col>
            <v-col>
              <v-card-title class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
                <team-avatar-component></team-avatar-component>
                Team Average Time to fix an Issue
                <v-icon small class="info-icon" @mouseover="showTooltip[3] = true" @mouseleave="showTooltip[3] = false">mdi-information</v-icon>
                <span v-if="showTooltip[3]" class="tooltip">Average of last 5 issues</span>
              </v-card-title>
              <TeamAverageTimeFixIssue/>
            </v-col>
          </v-row>
        </v-card>

      </v-col>
      <v-col cols="6">
        <v-card>
          <v-row>
            <v-col>
              <v-card-title class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
                <team-avatar-component></team-avatar-component>
                Age of Issue
              </v-card-title>
              <AgeOfIssueTime/>
            </v-col>
            <v-col>
              <v-card-title class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
                <team-avatar-component></team-avatar-component>
                Average Age of Open Issues
                <v-icon small class="info-icon" @mouseover="showTooltip[1] = true" @mouseleave="showTooltip[1] = false">mdi-information</v-icon>
                <span v-if="showTooltip[1]" class="tooltip">Average of all Issues</span>
              </v-card-title>
              <AverageAgeOfOpenIssues/>
            </v-col>
          </v-row>
        </v-card>
        <v-card style="margin-top: 1em;">
          <v-card-title class="d-flex align-items-center" style="margin-top: 0.4em; margin-bottom: 0; padding-bottom: 0">
            <team-avatar-component></team-avatar-component>
            IssueDensity
            <v-icon small class="info-icon" @mouseover="showTooltip[0] = true" @mouseleave="showTooltip[0] = false">mdi-information</v-icon>
            <span v-if="showTooltip[0]" class="tooltip">Issues per 1000 Lines of Code</span>
          </v-card-title>
          <IssueDensity/>
        </v-card>

      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import OpenIssues from '../diagrams/issue/openIssues.vue';
import ClosedAndTotalIssues from "../diagrams/issue/ClosedAndTotalIssues.vue";
import TeamAvatarComponent from "../diagrams/teamAvatar.vue";
import AvatarComponent from "../diagrams/avatar.vue";
import IssueDensity from "../diagrams/issue/IssueDensity.vue";
import TotalFixedIssues from "../diagrams/issue/TotalFixedIssues.vue";
import AverageAgeOfOpenIssues from "../diagrams/issue/AverageAgeOfOpenIssues.vue";
import TeamAverageTimeFixIssue from "../diagrams/issue/TeamAverageTimeFixIssue.vue";
import AgeOfIssueTime from "../diagrams/issue/AgeOfIssueTime.vue";

export default {
  name: 'CardGrid',
  components: {
    AgeOfIssueTime,
    TeamAverageTimeFixIssue,
    AverageAgeOfOpenIssues,
    IssueDensity, ClosedAndTotalIssues, AvatarComponent, TeamAvatarComponent, OpenIssues, TotalFixedIssues},

  data() {
    return {
      diagrams: [
        {
          component1: OpenIssues,
          title1: 'Open Issues',
          component2: ClosedAndTotalIssues,
          title2: 'Closed and Total Issues'
        },
        {component1: IssueDensity, title1: 'IssueDensity'},
        {tile1: 'asdadadsa', component1: '', title2: '', component2: ''},
        {title1: 'Your fixedIssues', component1: 'TotalFixedIssues'},
      ],
      showTooltip: [false, false, false, false],
    }

  },

}
</script>

<style scoped>
.min-size-component {
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
