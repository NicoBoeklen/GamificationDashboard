<template>
  <v-container fluid>
    <v-row>
      <v-col cols="6" v-for="n in 4" :key="n">
        <v-card>
          <v-card-title>
            Test
          </v-card-title>
            <component :is="diagrams[n % diagrams.length].component"></component>
        </v-card>

      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import OpenIssues from '../diagrams/issue/openIssues.vue';
import ClosedAndTotalIssues from "../diagrams/issue/ClosedAndTotalIssues.vue";
export default {
  name: 'CardGrid',
  components: {OpenIssues, },

  data() {
    return {
      diagrams: [
        {component: OpenIssues},
        {component: ClosedAndTotalIssues}
      ]
    }
  }
}
</script>

<style scoped>
</style>
