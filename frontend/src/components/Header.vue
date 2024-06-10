<template>
  <v-app>
    <v-app-bar app color="grey darken-3" dark>
      <v-avatar>
        <img  alt="Avatar" />
      </v-avatar>
      <v-toolbar-title class="mx-5">{{ userName }}</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-title>GitHub Dashboard</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn text>Management View</v-btn>
      <v-switch v-model="darkMode" label="Dark mode"></v-switch>
      <v-btn text>Logout</v-btn>
    </v-app-bar>
  </v-app>
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
