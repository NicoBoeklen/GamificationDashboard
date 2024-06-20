<template>
  <v-card-title class="section-title">Badges</v-card-title>
  <v-card-text class="badges-container" style="display: flex">
    <v-img
      :src="'https://cdn.builder.io/api/v1/image/assets/TEMP/196eacaba534187ca530bde1ec70c21657fa163daf5d8f5b7a4d0d2d7bf2439c?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&'"
      class="badge"
      alt="Badge 1"
    />
    <v-img
      :src="'https://cdn.builder.io/api/v1/image/assets/TEMP/85e03766cb7ee30ce9f8855881450461c28cf54928fc1ef3fcb1d42a17dbb83a?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&'"
      class="badge"
      alt="Badge 2"
    />
    <v-img
      :src="'https://cdn.builder.io/api/v1/image/assets/TEMP/6aafcacc208e50dc71e7aeb9701f7d46d6f4389846c8f726b1f750773a7a62f5?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&'"
      class="badge"
      alt="Badge 3"
    />
    <v-img
      :src="'https://cdn.builder.io/api/v1/image/assets/TEMP/5e8e314d956b0814fc1a00042dc6bd098726acab30e645a4f3808aa2cf234fc8?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&'"
      class="badge"
      alt="Badge 4"
    />
  </v-card-text>
</template>
<style>
.badge {
  margin-right: 10px;
  height: 50px; /* Adjust height as needed */
  width: auto;
  align-items: start
}

</style>
<script setup lang="ts">
</script>
