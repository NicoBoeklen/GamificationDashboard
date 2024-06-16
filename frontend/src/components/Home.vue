<template>
  <v-container class="fill-height">
    <v-responsive
      class="align-center fill-height mx-auto"
      max-width="900"
    >
      <v-img
        class="mb-4"
        height="200"
        src="@/assets/LogoDashboard.png"
      />

      <div class="text-center">
        <div class="text-body-2 font-weight-light mb-n1">Welcome to</div>

        <h1 class="text-h2 font-weight-bold">GameDash</h1>
      </div>

      <div class="py-4" />

      <v-row align="center" justify="center">
        <v-col cols="12" sm="10" md="6" lg="4">
          <v-card class="pa-5">
            <v-card-title class="headline">Login</v-card-title>
            <v-card-text>
              <v-form  ref="form">
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
                <v-btn color="primary" type="button" block  class="mt-4" @click="login()">Login</v-btn>

              </v-form>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-responsive>
  </v-container>
</template>


<script setup lang="ts">
import {toLogin} from "../objects/login";
import {login} from "../objects/login";
const rules = {
  required: (value: string) => !!value || 'Required!'
};

</script>
<style scoped>
.fill-height {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;

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

v-text-field {
  margin-bottom: 16px;
}

v-btn {
  background-color: #3f51b5;
  color: #ffffff;
  font-weight: bold;
}
</style>
