import { createRouter, createWebHistory } from 'vue-router';
import AccountViewUser from '@/views/account/user/AccountViewUser.vue';
import AccountViewTrainer from '@/views/account/trainer/AccountViewTrainer.vue';
import AccountViewChoose from '@/components/account/AccountViewChoose.vue';
import AccountViewUserLogin from '@/views/account/user/AccountViewUserLogin.vue';
import AccountViewUserSignup from '@/views/account/user/AccountViewUserSignup.vue';
import App from '@/App.vue';
import { useUserStore } from '@/stores/user';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home'
    },
    {
      path: '/account',
      name: 'account',
      component: AccountViewChoose,
    },
    {
      path: '/user',
      name: 'user',
      component: AccountViewUser,
    },
    {
      path: '/trainer',
      name: 'trainer',
      component: AccountViewTrainer,
    },
    {
      path: '/user/login',
      name: 'userLogin',
      component: AccountViewUserLogin,
    },
    {
      path: '/user/signup',
      name: 'userSignup',
      component: AccountViewUserSignup,
    },
  ],
});


export default router;
