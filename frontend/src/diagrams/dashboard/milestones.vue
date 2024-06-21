<template>
  <v-card-title class="section-title">Milestones</v-card-title>
  <v-card-text>
    <div class="milestone-container" v-if="milestoneUser.length > 0">
      <div v-for="(milestone, index) in milestoneUser" :key="index" class="milestone-item">
        <h3 style="margin-bottom: 0.5em" v-if="index===0">Individual</h3>
        <h3 style="margin-bottom: 0.5em" v-if="index===14">Team</h3>
        <p>{{ milestone.achievement.description }}</p>
        <v-progress-linear
          class="progress-milestones"
          height="25"
          :model-value="Math.round((milestone.progress / milestone.achievement.condition) * 100)"
          @mouseover="showTooltip[index] = true" @mouseleave="showTooltip[index] = false">
          {{ milestone.achievement.xp }} XP
          <span v-if="showTooltip[index]" class="tooltip">{{milestone.progress}} / {{milestone.achievement.condition}}</span>
        </v-progress-linear>
      </div>
    </div>
    <p v-else>Loading...</p>
  </v-card-text>
</template>

<script lang="ts">
import {fetchMilestone, Milestone, MilestoneObject} from '../../objects/milestones';
import {onMounted, ref} from "vue";

const milestoneUser = ref([] as Milestone[]);
let showTooltip = ref([]);

export default {
  setup() {
    onMounted(async () => {
      try {
        milestoneUser.value = await fetchMilestone();
        showTooltip.value = new Array(milestoneUser.value.length).fill(false);
      } catch (error) {
        console.error('Failed to fetch milestones:', error);
      }
    });

    return {
      milestoneUser,
      showTooltip
    };
  }
};
</script>

<style>
.progress-milestones {
  margin: 0em 1em 1em 0em;
  background-color: rgba(8, 98, 189, 0.1);
  border-radius: 7px;
  color: rgba(8, 98, 189, 0.5);
}

.milestone-container {
  max-height: 30em;
  overflow-y: auto;
}

.milestone-item {
  margin-bottom: 1em;
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
  bottom: 0.15em;
  left: 25%;
  transform: translateX(-50%);
}
</style>

