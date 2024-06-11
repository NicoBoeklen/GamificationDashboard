<template>
    <v-app-bar :elevation="2" app color="grey darken-3" dark>
      <v-avatar>
        <img  alt="Avatar" />
      </v-avatar>
      <v-toolbar-title class="mx-5">{{ userName }}</v-toolbar-title>

      <v-app-bar-title>GitHub Dashboard</v-app-bar-title>

      <template v-slot:append>
        <v-btn variant="outlined" class="mr-3">Managment View</v-btn>

        <v-btn icon="mdi mdi-theme-light-dark" ></v-btn>

        <v-btn append-icon="mdi-logout" >Logout</v-btn>
      </template>
    </v-app-bar>
</template>

<script>
import {getUserName, toLogin} from "../objects/login";
import {onMounted, ref} from "vue";

export default {
  setup() {
    const userName = ref('');

    onMounted(() => {
      userName.value = getUserName();
      console.log('Username:', userName.value);
    });


    return { userName };
  }
}
</script>

<style scoped>
.v-avatar img {
  object-fit: cover;
  object-position: center;
}
</style>
