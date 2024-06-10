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
              <v-form @submit.prevent="login" ref="form">
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
                <v-btn color="primary" type="submit" block class="mt-4" @click="login()">Login</v-btn>
              </v-form>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-responsive>
  </v-container>
</template>


<script setup lang="ts">

import config from "../config";
import {ref, Ref} from "vue";
import {showToast, Toast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";
import {useRouter} from "vue-router";
interface Login {
  userName: string;
  repoName: string;
  ownerName: string;
}
const toLogin: Ref<Login> = ref<Login>({
  userName: '',
  repoName: '',
  ownerName: ''
});
const rules = {
  required: (value: string) => !!value || 'Required!'
};
const router = useRouter()

function login(){
  console.log("login ist durchgeführt");
  fetch(`${config.apiBaseUrl}/login`,
    {method: "POST", headers: { 'Content-Type': 'application/json'},
      body: JSON.stringify(toLogin.value)})

    .then(response => response.json())
    .then(data => data as Login[])
    .then(data => {
      console.log(data);
      showToast(new Toast("Alert", `Login Successful!`, "success", faCheck, 5));
      router.push('/dashboard')
    })
    .catch(error => showToast(new Toast("Error", error, "error", faXmark, 10)));

}









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
