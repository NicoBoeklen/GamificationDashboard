import config from "../config";
import {showToast, Toast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";
import {type Ref, ref} from "vue";
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
interface Issue {
  amountTotalIssuesTeam: number;
  amountFixedIssuesTeam: number;
  amountOpenIssuesTeam: number;
  amountTotalClosedIssuesUser: number;
  averageTimeFixIssueOpenTeam: number;
  averageTimeFixIssueTotalTeam: number;
  weeklyClosedIssues: WeeklyClosedIssues[];
  weeklyOpenIssues: weeklyOpenIssues[];
  weeklyTotalIssues: weeklyTotalIssues[];
  issuesPer1000LoC: issuesPer1000LoC[];
}
let issue: Issue = {
  amountTotalIssuesTeam: 0,
  amountFixedIssuesTeam: 0,
  amountOpenIssuesTeam: 0,
  amountTotalClosedIssuesUser: 0,
  averageTimeFixIssueOpenTeam: 0,
  averageTimeFixIssueTotalTeam: 0,
  weeklyClosedIssues: [],
  weeklyOpenIssues: [],
  weeklyTotalIssues: [],
  issuesPer1000LoC: [],
};

function fetchIssues()  {
  fetch(`${config.fetchBaseUrl}/issuesStats/133352623`,  {
  method: "GET",
  headers: {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
  },
  body: JSON.stringify(issue),
})
  .then(response => {
    if (!response.ok) {
      throw new Error("Failed to update todo status");
    }
    showToast(new Toast("Success", `Todo status updated successfully!`, "success", faCheck, 5));
  })
  .catch(error => showToast(new Toast("Error", error.message, "error", faXmark, 10)))
}
export function getIssues(): Ref<Issue>{
  fetchIssues();
  return ref(issue);
}

