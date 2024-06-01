<script setup lang="ts">
import {Button} from "agnostic-vue";


let name = "";


</script>

<template>
  <main>
    <h1 style="color: white">Welcome to Your Vue.js App</h1>
    <label class="mbs2 mbe2" >Type your name:</label>
    <br/>
    <input type="text" class="mbs2 mbe2" v-model="name"/>
    <br/>
  </main>
</template>
