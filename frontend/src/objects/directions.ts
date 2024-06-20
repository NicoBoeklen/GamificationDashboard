import router from "../router";

export function redirectInsights(){
  router.push('/insights')
}
export function redirectDashboardHome(){
  router.push('/dashboard')
}
export function redirectCodeInsights(){
  router.push('/codeinsights')
}
export function redirectIssueManagment() {
  router.push('/issuemanagment')
}
export function redirectDeployment(){
  router.push('/deployment')
}
export function redirectManagementView(){
  router.push('/management')
}
export function redirectLogin(){
  router.push('/')
}
