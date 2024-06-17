<template>
  <HeaderManagement/>

  <v-container class="main-container">
  <v-row align="center" justify="center" dense>
    <v-col cols="12" md="4">
      <v-card
        append-icon="mdi-check"
        class="mx-auto"
        prepend-icon="mdi-account"
        subtitle="prepend-icon and append-icon"
        title="Icons"
      >
        <v-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod.</v-card-text>
      </v-card>
    </v-col>

    <v-col cols="12" md="4">
      <v-card
        class="mx-auto"
        subtitle="prepend and append"
        title="Icons"
      >
        <template v-slot:prepend>
          <v-icon color="primary" icon="mdi-account"></v-icon>
        </template>
        <template v-slot:append>
          <v-icon color="success" icon="mdi-check"></v-icon>
        </template>
        <v-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod.</v-card-text>
      </v-card>
    </v-col>

    <v-col cols="12" md="4">
      <v-card class="mx-auto" subtitle="prepend and append" title="Icons">
        <template v-slot:prepend>
          <v-icon color="primary" icon="mdi-account"></v-icon>
        </template>
        <template v-slot:append>
          <v-icon color="success" icon="mdi-check"></v-icon>
        </template>
        <v-card-text
        >Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
          eiusmod.</v-card-text
        >
      </v-card>
    </v-col>

    <v-col cols="12" >
      <v-card
        append-avatar="https://cdn.vuetifyjs.com/images/john.jpg"
        class="mx-auto"
        prepend-avatar="https://cdn.vuetifyjs.com/images/logos/v-alt.svg"
        subtitle="prepend-avatar and append-avatar"
        title="Avatars"
      >
        <v-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod.</v-card-text>
      </v-card>
    </v-col>


  </v-row>
  </v-container>
</template>
<script setup lang="ts">
import HeaderManagement from "./HeaderManagement.vue";
</script>
