import config from "../config";
import {showToast, Toast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";
import {type Ref, ref} from "vue";
import {useRouter} from "vue-router";
import {da} from "vuetify/locale";
import router from "../router";

let userNameSave: string;

interface Login {
  userName: string;
  repoName: string;
  ownerName: string;
  apiKey: string;
  repoId: number;
  userId: number;
}
export const toLogin: Ref<Login> = ref<Login>({
  userName: '',
  repoName: '',
  ownerName: '',
  apiKey: '',
  repoId: 0,
  userId: 0,
});


export function login(){
  console.log("login wird durchgeführt");
  console.log(toLogin.value.userName+toLogin.value.ownerName+toLogin.value.repoName);
  fetch(`${config.fetchBaseUrl}/api/login`,
    {method: "POST", headers: { 'Content-Type': 'application/json'},
      body: JSON.stringify(toLogin.value)})
    .then(response => {
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      return response.json();})
    .then(data => data as Login[])
    .then(data => {
      console.log(data);
      showToast(new Toast("Alert", `Login Successful!`, "success", faCheck, 5));
      toLogin.value = data;
      console.log(toLogin.value.userName)
      localStorage.setItem('userName', toLogin.value.userName)
      localStorage.setItem('repoId', String(toLogin.value.repoId))
      localStorage.setItem('userId', String(toLogin.value.userId))
      localStorage.setItem('repoName', toLogin.value.repoName)
      router.push('/dashboard');
    })
    .catch(error => showToast(new Toast("Error", error, "error", faXmark, 10)));

}

export function getUserName(): string {

  return userNameSave;
}

