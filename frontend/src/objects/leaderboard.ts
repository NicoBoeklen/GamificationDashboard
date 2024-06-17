import config from "../config";
import {showToast, Toast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";

export interface Leaderboard {
  leaderboard: { [key: string]: number };
}

const repoId = localStorage.getItem('repoId');

export async function fetchLeaderboard(): Promise<Leaderboard> {
  const response = await fetch(`${config.fetchBaseUrl}/getLeaderboard/${repoId}`,  {
    method: "GET",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
  });
  if (!response.ok) {
    throw new Error("Failed to fetch Skill");
  }
  const leaderboard: Leaderboard = await response.json();
  showToast(new Toast("Success", `Leaderboard fetched successfully!`, "success", faCheck, 5));
  return leaderboard;
}
