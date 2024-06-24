import config from "../config";
import {showToast, Toast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";

export interface Skill {
  reviewValueUser: number;
  fixedIssuesValueUser: number;
  productivityValueUser: number;
  commitValueUser: number;
}

const repoId = localStorage.getItem('repoId');
const userId = localStorage.getItem('userId');
//console.log("UserId lautet"+userId);

export async function fetchSkills(): Promise<Skill> {
  const response = await fetch(`${config.fetchBaseUrl}/getSkills/${userId}/${repoId}`,  {
    method: "GET",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
  });
  if (!response.ok) {
    throw new Error("Failed to fetch Skill");
  }
  const skill: Skill = await response.json();
  //showToast(new Toast("Success", `Skills fetched successfully!`, "success", faCheck, 5));
  return skill;
}
