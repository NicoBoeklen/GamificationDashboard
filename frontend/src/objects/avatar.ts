import config from "../config";
import {showToast, Toast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";

const repoId = localStorage.getItem('repoId');
const userId = localStorage.getItem('userId');

export interface User {
  avatarURL: String
}
export async function fetchAvatar(): Promise<User> {
  const response = await fetch(`${config.fetchBaseUrl}/avatar/user/${userId}/${repoId}`,  {
    method: "GET",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
  });
  if (!response.ok) {
    throw new Error("Failed to fetch User");
  }
  const user: User = await response.json();
  showToast(new Toast("Success", `User fetched successfully!`, "success", faCheck, 5));
  return user;
}
