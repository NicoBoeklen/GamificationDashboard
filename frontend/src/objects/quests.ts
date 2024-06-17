import config from "../config";
import {showToast, Toast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";

interface QuestObject {
  name: String;
  description: String;
  xp: number;
  type: String;
  condition: number;
  tag: String;
}

export interface Quest {
 quests: QuestObject;
 progress: number;
}

const repoId = localStorage.getItem('repoId');
const userId = localStorage.getItem('userId');
console.log("UserId lautet"+userId);

export async function fetchQuests(): Promise<Quest> {
  const response = await fetch(`${config.fetchBaseUrl}/quest/${userId}/${repoId}`,  {
    method: "GET",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
  });
  if (!response.ok) {
    throw new Error("Failed to fetch Quests");
  }
  const quest: Quest = await response.json();
  showToast(new Toast("Success", `Quests fetched successfully!`, "success", faCheck, 5));
  return quest;
}
