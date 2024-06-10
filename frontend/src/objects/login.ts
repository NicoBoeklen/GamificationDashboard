import config from "../config";
import {showToast, Toast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";
import {type Ref, ref} from "vue";
import {useRouter} from "vue-router";
import {da} from "vuetify/locale";

let userNameSave: string;

interface Login {
  userName: string;
  repoName: string;
  ownerName: string;
}
export const toLogin: Ref<Login> = ref<Login>({
  userName: '',
  repoName: '',
  ownerName: ''
});
const router = useRouter()
export function login(){
  console.log("login ist durchgeführt");
  fetch(`${config.fetchBaseUrl}/login`,
    {method: "POST", headers: { 'Content-Type': 'application/json'},
      body: JSON.stringify(toLogin.value)})

    .then(response => response.json())
    .then(data => data as Login[])
    .then(data => {
      console.log(data);
      showToast(new Toast("Alert", `Login Successful!`, "success", faCheck, 5));
      userNameSave = toLogin.value.userName
      router.push('/dashboard')
    })
    .catch(error => showToast(new Toast("Error", error, "error", faXmark, 10)));

}
export function getUserName(): string{

  return userNameSave;
}
