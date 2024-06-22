import config from "../config";
import {showToast, Toast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";

interface ReleaseObject {
  id: number;
  tag_name: string;
  published_at: string;
}

export interface Release {
  numberOfReleases: number;
  averageTimeBetweenReleases: number;
  releaseList: ReleaseObject[];
}


const repoId = localStorage.getItem('repoId');
export async function fetchReleases(): Promise<Release> {
  const response = await fetch(`${config.fetchBaseUrl}/releaseMetrics/${repoId}`,  {
    method: "GET",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
  });
  if (!response.ok) {
    throw new Error("Failed to fetch Releases");
  }
  const release: Release = await response.json();
  showToast(new Toast("Success", `Releases fetched successfully!`, "success", faCheck, 5));
  return release;
}
