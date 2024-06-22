<template>
  <v-avatar color="white darken-1" style="margin-left: 1.3em;" class="counter-number"
  >{{ realLevel }}
    <v-progress-circular v-if="realLevel == 0" indeterminate color="white" size="24"></v-progress-circular>
  </v-avatar
  >
  <v-progress-linear
    v-model="progress"
    height="20"
    color="yellow" style="border-radius: 7px;"
    @mouseover="showTooltip = true" @mouseleave="showTooltip = false">
    <span v-if="showTooltip" class="tooltip">{{level}} / {{50*(realLevel+1)*(realLevel+1)-50*realLevel}}</span>

  </v-progress-linear>
</template>

<script lang="ts">
import {fetchAvatar} from '../../objects/avatar';
import Level from "./level.vue";
import {fetchMilestone} from "../../objects/milestones";

export default {
  computed: {
    Level() {
      return Level
    }
  },
  data() {
    return {
      level: 0,
      realLevel: 0,
      progress: 0,
      showTooltip: false
    };
  },
  async mounted() {
    await fetchMilestone();
    const user = await fetchAvatar();
    this.level = user.level;
    this.realLevel = Math.floor((1 + Math.sqrt(1 + (this.level / 12.5))) / 2);
    this.progress = Math.floor((((1 + Math.sqrt(1 + (this.level / 12.5))) / 2) - Math.floor((1 + Math.sqrt(1 + (this.level / 12.5))) / 2)) * 100);
    console.log(this.progress, this.realLevel, ((1 + Math.sqrt(1 + (this.level / 12.5))) / 2))
  },
};
</script>

<style scoped>
.tooltip {
  position: absolute;
  background-color: yellow;
  color: #000000;
  border-radius: 4px;
  font-size: 0.75rem;
  white-space: nowrap;
  z-index: 10000;
  left: 50%;
  bottom: -0.1em;
  transform: translateX(-50%);
}
</style>
