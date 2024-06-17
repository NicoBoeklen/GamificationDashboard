import config from "../config";
import {showToast, Toast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";
import type {Ref} from "vue";
interface Repository {
  description: string;
  createdAt: string;
  updatedAt: string;
}


const repoId = localStorage.getItem('repoId');
export async function fetchRepository(): Ref<Repository> {
  const response = await fetch(`${config.fetchBaseUrl}/api/repositoryData/${repoId}`,  {
    method: "GET",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
  });
  if (!response.ok) {
    throw new Error("Failed to fetch Repository-information");
  }
  const repository: Repository = await response.json();
  showToast(new Toast("Success", `Repository-information fetched successfully!`, "success", faCheck, 5));
  return repository;
}



