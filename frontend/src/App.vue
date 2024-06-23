<template>
  <v-app>
    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>

  <div class="toasts-container">
  <Toasts vertical-position="top" horizontal-position="end">
    <template v-for="toast of activeToasts" :key="toast.key">
      <Toast :type="toast.type" class="alert alert-border-left alert-info">
        <div class="flex-fill flex flex-column">
          <div class="flex">
            <h3 class="flex-fill">{{ toast.title }}</h3>
            <Close @click="toast.close()"/>
          </div>
          <div class="flex">
            <font-awesome-icon :icon="toast.icon" size="xl" class="mie8 pbs2"></font-awesome-icon>
            <div class="flex-fill">
              {{ toast.message }}
            </div>
          </div>
        </div>
      </Toast>
      <div class="mbe14"/>
    </template>
  </Toasts>
  </div>
</template>
<style>
.toasts-container {
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1000;
  width: 300px; /* Adjust the width as necessary */
  padding: 10px; /* Optional: Add padding around the container */
}

.alert {
  margin-bottom: 10px; /* Space between toasts */
}

/* Additional optional styling for the toast notifications */
.alert h3 {
  margin: 0;
}

.alert .flex {
  display: flex;
  align-items: center;
}

.alert .flex-fill {
  flex: 1;
}

.alert .mie8 {
  margin-right: 8px;
}

.alert .pbs2 {
  padding-bottom: 2px;
}

.alert-info {
  background-color: #d9edf7;
  color: #31708f;
  border: 1px solid #dcdcdc; /* Adjust border color */
  padding: 10px;
  border-radius: 5px;
}
</style>

<script lang="ts" setup>
import {activeToasts} from "./ts/toasts";
import {Close, Toast, Toasts} from "agnostic-vue";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {RouterView} from 'vue-router'
import {reactive} from "vue";
console.log('Active Toasts:', activeToasts);
</script>
