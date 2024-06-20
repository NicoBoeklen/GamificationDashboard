<template>
  <v-card-title class="section-title">Milestones</v-card-title>
  <v-card-text>
    <div v-if="milestoneUser.length > 0">
      <div v-for="(milestone, index) in milestoneUser" :key="index">
        <p>{{ milestone.achievement.description }}</p>
        <v-progress-linear
          class="progress-milestones"
          height="25"
          :model-value="Math.round((milestone.progress / milestone.achievement.condition) * 100)"
        >
          {{ milestone.achievement.xp }} XP
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

export default {
  setup() {
    onMounted(async () => {
      try {
        milestoneUser.value = await fetchMilestone();
      } catch (error) {
        console.error('Failed to fetch milestones:', error);
      }
    });

    return {
      milestoneUser,
    };
  }
};
</script>

<style>
.progress-milestones {
  margin: 1em 1em 1em 0em;
  background-color: rgba(8, 98, 189, 0.1);
  border-radius: 7px;
  color: rgba(8, 98, 189, 0.5);
}


</style>

