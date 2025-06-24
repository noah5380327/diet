import { RouteRecordRaw } from 'vue-router';

const routes: RouteRecordRaw[] = [
  {
    path: '',
    redirect: '/dashboard',
  },

  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: 'dashboard',
        name: 'dashboard',
        component: () => import('pages/DashboardPage.vue'),
        meta: {
          title: 'Dashboard',
        },
      },
      {
        path: 'profile',
        name: 'profile',
        component: () => import('pages/ProfilePage.vue'),
        meta: {
          title: 'Profile',
        },
      },
      {
        path: 'selectCoach',
        name: 'selectCoach',
        component: () => import('pages/SelectCoachPage.vue'),
        meta: {
          title: 'SelectCoach',
        },
      },
      {
        path: 'exerciseRecord',
        name: 'exerciseRecord',
        component: () => import('pages/ExerciseRecordPage.vue'),
        meta: {
          title: 'ExerciseRecord',
        },
      },
      {
        path: 'recipeResult',
        name: 'recipeResult',
        component: () => import('pages/RecipeResultPage.vue'),
        meta: {
          title: 'RecipeResult',
        },
      },
      {
        path: 'coachRequest',
        name: 'coachRequest',
        component: () => import('pages/CoachRequestPage.vue'),
        meta: {
          title: 'CoachRequest',
        },
      },
    ],
  },

  {
    path: '/login',
    name: 'login',
    component: () => import('pages/LoginPage.vue'),
    meta: {
      title: 'Login',
    },
  },

  {
    path: '/register',
    name: 'register',
    component: () => import('pages/RegisterPage.vue'),
    meta: {
      title: 'Register',
    },
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('components/ErrorNotFound.vue'),
  },
];

export default routes;
