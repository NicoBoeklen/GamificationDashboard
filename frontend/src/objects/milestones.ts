import config from "../config";
import {showToast, Toast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";

export interface MilestoneObject{
  name: string;
  description: string;
  xp: number;
  type: string;
  condition: number;
}

export interface Milestone {
  achievement: MilestoneObject;
  progress: number;
}

const repoId = localStorage.getItem('repoId');
const userId = localStorage.getItem('userId');
//console.log("UserId lautet"+userId);

export async function fetchMilestone(): Promise<Milestone[]> {
  const response = await fetch(`${config.fetchBaseUrl}/milestones/${userId}/${repoId}`,  {
    method: "GET",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
  });
  if (!response.ok) {
    throw new Error("Failed to fetch Milestones");
  }
  const milestone: Milestone[] = await response.json();
  //showToast(new Toast("Success", `Milestones fetched successfully!`, "success", faCheck, 5));
  return milestone;
}
