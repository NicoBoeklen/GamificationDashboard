<template>
  <Header/>
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

<script>
import Header from "./Header.vue";
import { defineComponent } from 'vue';
import {
  redirectCodeInsights,
  redirectDashboardHome,
  redirectDeployment,
  redirectIssueManagment
} from "../objects/directions";
export default defineComponent({
methods: {redirectDeployment, redirectIssueManagment, redirectCodeInsights, redirectDashboardHome},
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

.center-buttons{
display: flex;
justify-content: center;
}
.button{
display: flex;
margin: 0 8px;
justify-content: center;

}
</style>
