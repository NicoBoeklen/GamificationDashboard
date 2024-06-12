<template>
  <Header/>
    <v-container>
      <v-app-bar app color="primary" dark>
        <v-toolbar-title>Home</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn text>Code</v-btn>
        <v-btn text>Issue Management</v-btn>
        <v-btn text>Tests</v-btn>
        <v-btn text>Deployment</v-btn>
      </v-app-bar>
    </v-container>
</template>

<script>
import Header from "./Header.vue";

export default defineComponent({
  name: 'App',
  components: {Header},
});
</script>

<style scoped>
.v-application {
  font-family: 'Inter', sans-serif;
}

.v-toolbar-title {
  font-size: 24px;
}

.v-btn {
  font-weight: 700;
}
</style>
