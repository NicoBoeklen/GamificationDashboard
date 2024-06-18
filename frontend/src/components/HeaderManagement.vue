<template>
  <v-app-bar :elevation="0" app color="#5D5A5A" dark>
    <avatar-component></avatar-component>

    <v-toolbar-title class="mx-5">{{ userName }}</v-toolbar-title>

    <v-app-bar-title><strong>Management View:</strong>: {{ repoName }}</v-app-bar-title>

    <template v-slot:append>
      <v-btn @click="redirectDashboardHome()" variant="outlined" class="mr-3">User View</v-btn>

      <v-btn @click="toggleTheme()" icon="mdi mdi-theme-light-dark"  ></v-btn>

      <v-btn append-icon="mdi-logout" >Logout</v-btn>
    </template>
  </v-app-bar>
</template>

<script>
import {getUserName, toLogin} from "../objects/login";
import {onMounted, ref} from "vue";
import { useTheme } from 'vuetify'
import {redirectDashboardHome} from "../objects/directions";
import AvatarComponent from "../diagrams/avatarHeader.vue";





export default {
  components: {AvatarComponent},
  methods: {redirectDashboardHome},
  setup() {
    const userName = ref('');
    const theme = useTheme()
    const repoName = ref('');

    function toggleTheme() {
      theme.global.name.value = theme.global.current.value.dark ? 'light' : 'dark'
    }
    onMounted(() => {
      userName.value = localStorage.getItem('userName');
      console.log('Username:', userName);
      repoName.value = localStorage.getItem('repoName');
      console.log('Reponame:', repoName);
    });

    return { userName, toggleTheme, repoName };
  }
}

</script>

<style scoped>
.v-avatar img {
  object-fit: cover;
  object-position: center;
}
</style>
