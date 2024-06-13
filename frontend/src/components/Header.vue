<template>
    <v-app-bar :elevation="0" app color="#5D5A5A" dark>
      <v-avatar>
        <img  alt="Avatar" />
      </v-avatar>
      <v-toolbar-title class="mx-5">{{ userName }}</v-toolbar-title>

      <v-app-bar-title>GitHub Dashboard</v-app-bar-title>

      <template v-slot:append>
        <v-btn variant="outlined" class="mr-3">Managment View</v-btn>

        <v-btn @click="toggleTheme()" icon="mdi mdi-theme-light-dark"  ></v-btn>

        <v-btn append-icon="mdi-logout" >Logout</v-btn>
      </template>
    </v-app-bar>
</template>

<script>
import {getUserName, toLogin} from "../objects/login";
import {onMounted, ref} from "vue";
import { useTheme } from 'vuetify'





export default {
  setup() {
    const userName = ref('');
    const theme = useTheme()

    function toggleTheme() {
      theme.global.name.value = theme.global.current.value.dark ? 'light' : 'dark'
    }
    onMounted(() => {
      userName.value = getUserName();
      console.log('Username:', userName.value);
    });


    return { userName, toggleTheme };
  }
}

</script>

<style scoped>
.v-avatar img {
  object-fit: cover;
  object-position: center;
}
</style>
