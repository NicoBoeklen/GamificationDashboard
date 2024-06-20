<template>
<v-card-title class="section-title">Daily Quests</v-card-title>
<v-card-text>
<div class="quest-details">
  <p class="individual-quest" v-if="questsUser.length > 0">Individual: {{ questsUser[0].quest.description }} </p>
  <p class="individual-quest" v-else>Loading...</p>
  <v-progress-linear class="progress-quest" v-if="questsUser.length > 0" height="25"  :model-value="Math.round((questsUser[0].progress/questsUser[0].quest.condition)*100)" ></v-progress-linear>

  <p class="team-quest" v-if="questsUser.length > 0">Team: {{ questsUser[1].quest.description }}</p>
  <p class="team-quest" v-else>Loading...</p>
  <v-progress-linear class="progress-quest" v-if="questsUser.length > 0" height="25" :model-value= "Math.round((questsUser[1].progress/questsUser[1].quest.condition)*100)" ></v-progress-linear>

</div>
</v-card-text>
</template>

<script lang="ts">
import {fetchQuests, Quest, QuestObject} from '../../objects/quests';
import {onMounted, ref} from "vue";

const questsUser = ref([] as Quest[]);

export default {
  setup() {
    onMounted(async () => {
      try {
        questsUser.value = await fetchQuests();
        console.log(questsUser.value)
      } catch (error) {
        console.error('Failed to fetch quests:', error);
      }
    });

    return {
      questsUser,
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


</style>
