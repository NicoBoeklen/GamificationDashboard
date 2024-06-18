<template>
  <v-container fluid>
    <v-row>
      <v-col cols="6" v-for="n in 4" :key="n">
        <v-card>
          <template v-if="n != 3">
            <v-card-title>
              {{ diagrams[n-1].title }}
            </v-card-title>
            <component :is="diagrams[n-1].component"></component>
          </template>
          <template v-else>
            <v-row>
              <v-col>
                <v-card-title>
                  {{ diagrams[2].title1 }}
                </v-card-title>
                <component :is="diagrams[2].component1"></component>
              </v-col>
              <v-col>
                <v-card-title>
                  {{ diagrams[2].title2 }}
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
import TotalUserCode from "../diagrams/code/totalUserCode.vue";
import AverageCommitSize from "../diagrams/code/averageCommitSize.vue";

export default {
  name: 'CardGrid',
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
      ]
    }
  }
}
</script>

<style scoped>
</style>
