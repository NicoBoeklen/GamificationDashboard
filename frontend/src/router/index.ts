/**
 * router/index.ts
 *
 * Automatic routes for `./src/pages/*.vue`
 */

// Composables
import { createRouter, createWebHistory } from 'vue-router'
import Home from "../components/Home.vue";
import DashboardHome from "../components/DashboardHome.vue"
import Insights from "../components/Insights.vue";
import CodeInsights from "../components/CodeInsights.vue";
import IssueManagementInsights from "../components/IssueManagementInsights.vue";
import DeploymentInsights from "../components/DeploymentInsights.vue";
import ManagementView from "../components/ManagementView.vue";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: DashboardHome
    },
    {
      path: '/insights',
      name: 'insights',
      component: Insights
    },
    {
      path: '/codeinsights',
      name: 'codeinsights',
      component: CodeInsights
    },
    {
      path: '/issuemanagment',
      name: 'issuemanagment',
      component: IssueManagementInsights
    },
    {
      path: '/deployment',
      name: 'deployment',
      component: DeploymentInsights
    },
    {
      path: '/management',
      name: 'management',
      component: ManagementView
    }
  ]
})

export default router
