import config from "../config";
import {showToast, Toast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";

export interface PullRequest {
  numberReviewsUser: number;
  numberReviewsTotal: number;
  averageCommentsPerReviewUser: number;
  openPullRequests: number;
  closedPullRequestsLastMonth: number;
  averageAdditionsPerPRTeam: number;
  averageDeletionsPerPRTeam: number;
  averageCommitsPerPRTeam: number;
  averageProcessTimeInHours: number;
}

const repoId = localStorage.getItem('repoId');
const userId = localStorage.getItem('userId');
//console.log("UserId lautet"+userId);
export async function fetchPullRequests(): Promise<PullRequest> {
  const response = await fetch(`${config.fetchBaseUrl}/pullMetrics/${userId}/${repoId}`,  {
    method: "GET",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
  });
  if (!response.ok) {
    throw new Error("Failed to fetch PullRequests");
  }
  const pull: PullRequest = await response.json();
  //showToast(new Toast("Success", `PullRequests fetched successfully!`, "success", faCheck, 5));
  return pull;
}
