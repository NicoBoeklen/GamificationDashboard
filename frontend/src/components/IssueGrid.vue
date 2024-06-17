<template>
  <v-container fluid>
    <v-row>
      <v-col cols="6" v-for="n in 4" :key="n">
        <v-card>
          <v-card-title>
            Test
          </v-card-title>
            <OpenIssues/>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import OpenIssues from '../diagrams/issue/openIssues.vue';
export default {
  name: 'CardGrid',
  components: {OpenIssues},
}
</script>

<style scoped>
</style>
