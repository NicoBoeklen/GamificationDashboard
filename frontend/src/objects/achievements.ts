import config from "../config";
import {showToast, Toast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";

interface AchievementObject{
  name: String;
  description: String;
  xp: number;
  type: String;
  condition: number;
}

export interface Achievement {
  achievements: AchievementObject;
}

const repoId = localStorage.getItem('repoId');
const userId = localStorage.getItem('userId');
console.log("UserId lautet"+userId);

export async function fetchAchievement(): Promise<Achievement> {
  const response = await fetch(`${config.fetchBaseUrl}/achievements/${userId}/${repoId}`,  {
    method: "GET",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
  });
  if (!response.ok) {
    throw new Error("Failed to fetch Achievements");
  }
  const achievement: Achievement = await response.json();
  showToast(new Toast("Success", `Achievements fetched successfully!`, "success", faCheck, 5));
  return achievement;
}
