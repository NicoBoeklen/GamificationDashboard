<template>
<v-card-title class="section-title">Daily Quests</v-card-title>
<v-card-text>
<div class="quest-details">
  <p class="individual-quest" v-if="questsUser.length > 0">Individual: {{ questsUser[0].quest.description }} </p>
  <p class="individual-quest" v-else>Loading...</p>
  <v-progress-linear class="progress-quest" v-if="questsUser.length > 0" height="25"  :model-value="Math.round((questsUser[0].progress/questsUser[0].quest.condition)*100)"
                     @mouseover="showTooltip[0] = true" @mouseleave="showTooltip[0] = false">
    {{questsUser[0].quest.xp}} XP
    <span v-if="showTooltip[0]" class="tooltip">{{questsUser[0].progress}} / {{questsUser[0].quest.condition}}</span>
  </v-progress-linear>

  <p class="team-quest" v-if="questsUser.length > 0">Team: {{ questsUser[1].quest.description }}</p>
  <p class="team-quest" v-else>Loading...</p>
  <v-progress-linear class="progress-quest" v-if="questsUser.length > 0" height="25" :model-value= "Math.round((questsUser[1].progress/questsUser[1].quest.condition)*100)"
                     @mouseover="showTooltip[1] = true" @mouseleave="showTooltip[1] = false">
    {{questsUser[1].quest.xp}} XP
    <span v-if="showTooltip[1]" class="tooltip">{{ questsUser[1].progress}} / {{questsUser[1].quest.condition}}</span>
  </v-progress-linear>

</div>
</v-card-text>
</template>

<script lang="ts">
import {fetchQuests, Quest, QuestObject} from '../../objects/quests';
import {onMounted, ref} from "vue";

const questsUser = ref([] as Quest[]);
let showTooltip = ref([]);


export default {
  setup() {
    onMounted(async () => {
      try {
        questsUser.value = await fetchQuests();
        showTooltip.value = new Array(questsUser.value.length).fill(false);
      } catch (error) {
        console.error('Failed to fetch quests:', error);
      }
    });

    return {
      questsUser,
      showTooltip
    };
  }
};
</script>

<style>
.progress-quest {
  margin: 1em 1em 1em 0em;
  background-color: rgba(8, 98, 189, 0.1);
  border-radius: 7px;
  color: rgba(8, 98, 189, 0.5);
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
