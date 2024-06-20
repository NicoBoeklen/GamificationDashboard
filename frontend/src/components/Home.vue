<template>
  <v-container class="d-flex align-center justify-center fill-height" style="max-width: 70%; margin: 0 auto; padding: 0;">
    <v-responsive class="text-center" style="margin:0; padding: 0;">
      <v-img class="mb-4 mx-auto" height="200" src="@/assets/LogoDashboard.png" />

      <div class="text-center">
        <div class="text-body-2 font-weight-light mb-n1">Welcome to</div>
        <h1 class="text-h2 font-weight-bold">GameDash</h1>
      </div>

      <div class="py-4" />

      <v-row align="center" justify="center">
        <v-col cols="12" sm="10" md="8" lg="6" xl="4">
          <v-card class="pa-5 mb-4" :class="{ highlighted: isCurrentUser }">
            <v-card-title class="headline">Login</v-card-title>
            <v-card-text style="margin:0; padding: 0;">
              <v-form ref="form">
                <v-text-field
                  label="Username"
                  :rules="[rules.required]"
                  v-model="toLogin.userName"
                  color="blue"
                  required
                  clearable
                  prepend-inner-icon="mdi-account"
                  outlined
                  dense
                  class="mb-4"
                ></v-text-field>
                <v-text-field
                  label="Repository Name"
                  :rules="[rules.required]"
                  v-model="toLogin.repoName"
                  color="blue"
                  required
                  clearable
                  prepend-inner-icon="mdi-server"
                  outlined
                  dense
                  class="mb-4"
                ></v-text-field>
                <v-text-field
                  label="Owner Name"
                  :rules="[rules.required]"
                  v-model="toLogin.ownerName"
                  color="blue"
                  required
                  clearable
                  prepend-inner-icon="mdi-account"
                  outlined
                  dense
                  class="mb-4"
                ></v-text-field>
                <v-btn color="primary" type="button" block class="mt-4" @click="login()">Login</v-btn>
              </v-form>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-responsive>
  </v-container>
</template>

<script setup lang="ts">
import { toLogin } from "../objects/login";
import { login } from "../objects/login";
const rules = {
  required: (value: string) => !!value || 'Required!'
};
</script>

<style scoped>
.fill-height {
  min-height: 20%;
}

.highlighted {
  background-color: #f0f8ff; /* Set a different background color for highlighted cards */
}

.text-center {
  text-align: center;
}

.mx-auto {
  margin-left: auto;
  margin-right: auto;
}

v-card {
  background-color: #ffffff;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
}

v-card-title {
  font-size: 24px;
  font-weight: bold;
  color: #3f51b5;
}

v-btn {
  background-color: #3f51b5;
  color: #ffffff;
  font-weight: bold;
}
</style>
