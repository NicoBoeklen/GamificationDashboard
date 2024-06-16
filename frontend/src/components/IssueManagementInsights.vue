<template>
  <HeaderInsights/>
  <v-container class="container">
    <v-app-bar app color="#5D5A5A" dark>


      <v-toolbar-title>
        <v-btn prepend-icon="mdi mdi-home" variant="tonal" @click="redirectDashboardHome()">Home</v-btn>
      </v-toolbar-title>



      <v-btn variant="tonal" class="button" @click="redirectCodeInsights()">Code</v-btn>
      <v-btn variant="tonal" class="button" @click="redirectIssueManagment()">Issue Management</v-btn>
      <!-- <v-btn variant="tonal" class="button">Tests</v-btn>-->
      <v-btn variant="tonal" class="button" @click="redirectDeployment()">Deployment</v-btn>



      <v-spacer></v-spacer>

    </v-app-bar>
  </v-container>
</template>
<script setup lang="ts">
import HeaderInsights from "./HeaderInsights.vue";
import {
  redirectCodeInsights,
  redirectDashboardHome,
  redirectDeployment,
  redirectIssueManagment
} from "../objects/directions";
</script>
<style>
.button{
  display: flex;
  margin: 0 8px;
  justify-content: center;

}
</style>
