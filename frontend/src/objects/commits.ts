import config from "../config";
import {showToast, Toast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";

interface CodeGrowth {
  week: string;
  totalChanges: number;
}

interface CommitsUser {
  week: string;
  totalCommits: number;
}

export interface Commit {
  codeGrowthList: CodeGrowth[];
  commitCountUser: number;
  deletionCountUser:number;
  additionCountUser: number;
  averageAdditionsUser: number;
  averageDeletionsUser: number;
  commitsUser: CommitsUser[];
  productivityUser:number;
  numberOfContributors: number;
  totalLoC: number;
  commitCountTotal: number;
}

const repoId = localStorage.getItem('repoId');
const userId = localStorage.getItem('userId');
//console.log("UserId lautet"+userId);
export async function fetchCommits(): Promise<Commit> {
  const response = await fetch(`${config.fetchBaseUrl}/commitMetrics/${userId}/${repoId}`,  {
    method: "GET",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
  });
  if (!response.ok) {
    throw new Error("Failed to fetch commits");
  }
  const commit: Commit = await response.json();
  //showToast(new Toast("Success", `Commits fetched successfully!`, "success", faCheck, 5));
  return commit;
}
