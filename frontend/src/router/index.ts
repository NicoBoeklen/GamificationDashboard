import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AboutView from "@/views/AboutView.vue";
import AssigneesView from "@/views/AssigneesView.vue";
import CreateAssigneeView from "@/views/CreateAssigneeView.vue";
import EditAssigneeView from "@/views/EditAssigneeView.vue";
import TodosView from "@/views/TodosView.vue";



const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      component: AboutView
    },
    {
      path: '/assignees',
      name: 'assignees',
      component: AssigneesView
    },
    {
      path: '/create-assignee',
      name: 'create assignees',
      component: CreateAssigneeView
    },
    {
      path: '/assignees/:id',
      name: 'update assignees',
      component: EditAssigneeView
    },
    {
      path: '/todos',
      name: 'todos',
      component: TodosView
    }
  ]
})

export default router
