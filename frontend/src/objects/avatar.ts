import config from "../config";
import {showToast, Toast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";

const repoId = localStorage.getItem('repoId');
const userId = localStorage.getItem('userId');

export async function fetchAvatar(): Promise<String> {
  const response = await fetch(`${config.fetchBaseUrl}/avatar/user/${userId}/${repoId}`,  {
    method: "GET",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
  });
  if (!response.ok) {
    throw new Error("Failed to fetch avatar");
  }
  const avatar: String = await response.json();
  showToast(new Toast("Success", `avatar fetched successfully!`, "success", faCheck, 5));
  return avatar;
}
