<template>
    <v-app-bar :elevation="0" app color="#5D5A5A" dark>
      <avatar-component></avatar-component>
      <v-toolbar-title class="mx-5">{{ userName }}</v-toolbar-title>

      <v-app-bar-title>GitHub Dashboard</v-app-bar-title>

      <template v-slot:append>
        <v-btn disabled @click="redirectManagementView()" variant="outlined" class="mr-3">Management View</v-btn>

        <v-btn @click="toggleTheme()" icon="mdi mdi-theme-light-dark"  ></v-btn>

        <v-btn @click="redirectLogin()" append-icon="mdi-logout" >Logout</v-btn>
      </template>
    </v-app-bar>
</template>

<script>
import AvatarComponent from "../diagrams/avatarHeader.vue"
import {onMounted, ref} from "vue";
import { useTheme } from 'vuetify'
import {redirectLogin, redirectManagementView} from "../objects/directions";
export default {
  components: {AvatarComponent},
  methods: {redirectLogin, redirectManagementView},
  setup() {
    const userName = ref('');
    const theme = useTheme()

    function toggleTheme() {
      theme.global.name.value = theme.global.current.value.dark ? 'light' : 'dark'
      localStorage.setItem('theme', theme.global.name.value);
    }
    onMounted(() => {
      userName.value = localStorage.getItem('userName');
      //console.log('Username:', userName);

      const savedTheme = localStorage.getItem('theme');
      if (savedTheme) {
        theme.global.name.value = savedTheme;
      }
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
