import config from "../config";
import {showToast, Toast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";
import {type Ref, ref} from "vue";
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


export async function login(onLoadingChange: (isLoading: boolean) => void) {
  onLoadingChange(true); // Ladezustand auf true setzen
  console.log("login wird durchgeführt");
  console.log(toLogin.value.userName + toLogin.value.ownerName + toLogin.value.repoName);

  try {
    const response = await fetch(`${config.fetchBaseUrl}/api/login`, {
      method: "POST",
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(toLogin.value)
    });

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    const data = await response.json() as Login;
    console.log(data);

    showToast(new Toast("Alert", `Login Successful!`, "success", faCheck, 5));
    toLogin.value = data;
    console.log(toLogin.value.userName);
    localStorage.setItem('userName', toLogin.value.userName);
    localStorage.setItem('repoId', String(toLogin.value.repoId));
    localStorage.setItem('userId', String(toLogin.value.userId));
    localStorage.setItem('repoName', toLogin.value.repoName);
    await router.push('/dashboard');
  } catch (error) {
   console.log(error)
  } finally {
    onLoadingChange(false); // Ladezustand auf false setzen
  }
}

export function getUserName(): string {

  return userNameSave;
}

