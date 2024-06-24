import config from "../config";
import {showToast, Toast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";
interface WeeklyIssues {
  week: string;
  issues: number;
}
interface issuesPer1000LoC{
  week: string;
  issueDensity: number;
}
interface WeeklyClosedIssues extends WeeklyIssues{}
interface weeklyOpenIssues extends WeeklyIssues{}
interface weeklyTotalIssues extends WeeklyIssues{}
export interface Issue {
  amountTotalIssuesTeam: number;
  amountFixedIssuesTeam: number;
  amountOpenIssuesTeam: number;
  amountTotalClosedIssuesUser: number;
  averageAgeOfOpenIssues: number;
  amountOpenIssuesLessSevenDays: number;
  amountOpenIssuesBetweenWeekAndMonth: number;
  amountOpenIssuesMoreThanAMonth: number;
  averageTimeToFixIssueInDaysTeam: number;
  weeklyClosedIssues: WeeklyClosedIssues[];
  weeklyOpenIssues: weeklyOpenIssues[];
  weeklyTotalIssues: weeklyTotalIssues[];
  issuesPer1000LoC: issuesPer1000LoC[];
}

const repoId = localStorage.getItem('repoId');
const userId = localStorage.getItem('userId');
//console.log("UserId lautet"+userId);
export async function fetchIssues(): Promise<Issue> {
  const response = await fetch(`${config.fetchBaseUrl}/api/issuesStats/${userId}/${repoId}`,  {
    method: "GET",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
  });
  if (!response.ok) {
    throw new Error("Failed to fetch issues");
  }
  const issue: Issue = await response.json();
  //showToast(new Toast("Success", `Issues fetched successfully!`, "success", faCheck, 5));
  return issue;
}



