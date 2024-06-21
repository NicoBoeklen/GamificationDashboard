<template>
  <HeaderManagement/>

  <v-container class="main-container">
  <v-row align="center" justify="center" dense>
    <v-col cols="12" md="4">
      <v-card class="project-info">
        <v-card-title class="project-header">
          <div>
            <h1 class="project-title">Your Project</h1>
            <p class="project-subtitle">{{ repoName }}</p>
          </div>
        </v-card-title>
        <v-card-text style="margin-bottom: 0; padding-bottom: 0" class="project-description">
          Description: {{ repository.description }}
          <br />
          Created at: {{ repository.created_at }}
          <br />
          Updated at: {{ repository.updated_at }}
        </v-card-text>
        <v-carousel hide-delimiters height="10em" cycle interval="15000">
          <v-carousel-item>
            <v-container class="fill-height" fluid>
              <v-row align="center" justify="center">
                <v-col class="text-center">
                  <div>
                    <h2>Erstes Item</h2>
                    <p>Beschreibung des ersten Items</p>
                  </div>
                </v-col>
              </v-row>
            </v-container>
          </v-carousel-item>

          <v-carousel-item>
            <v-container class="fill-height" fluid>
              <v-row align="center" justify="center">
                <v-col class="text-center">
                  <div>
                    <h2>Zweites Item</h2>
                    <p>Beschreibung des zweiten Items</p>
                  </div>
                </v-col>
              </v-row>
            </v-container>
          </v-carousel-item>

          <v-carousel-item>
            <v-container class="fill-height" fluid>
              <v-row align="center" justify="center">
                <v-col class="text-center">
                  <div>
                    <h2>Drittes Item</h2>
                    <p>Beschreibung des dritten Items</p>
                  </div>
                </v-col>
              </v-row>
            </v-container>
          </v-carousel-item>
        </v-carousel>


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
<script lang="ts">
import HeaderManagement from "./HeaderManagement.vue";
import {defineComponent, onMounted, ref} from "vue";
import {fetchRepository} from "../objects/repository";
import type {Repository} from "../objects/repository";
const repoName = ref('');
let repository = ref({} as Repository);
export default defineComponent({
  components: {HeaderManagement},
  setup(){
    onMounted(async () => {
      repoName.value = localStorage.getItem('repoName') || '';
      console.log('Repo Name:', repoName.value);
      repository.value =  await fetchRepository();

    });
    return {repoName, repository};
  }
});



</script>
