<template>
  <v-container fluid>
    <v-row>
      <v-col cols="6" v-for="n in 4" :key="n">
        <v-card>
          <v-card-title>
            Total Lines of Code
          </v-card-title>
          <CodeGrowth/>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import CodeGrowth from '../diagrams/code/codeGrowth.vue';
export default {
  name: 'CardGrid',
  components: {CodeGrowth},
}
</script>

<style scoped>
</style>
