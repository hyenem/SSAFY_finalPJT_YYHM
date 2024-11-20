import { createRouter, createWebHistory } from "vue-router";
import AccountViewUser from "@/components/account/user/AccountViewUser.vue";
import AccountViewTrainer from "@/components/account/trainer/AccountViewTrainer.vue";
import AccountViewChoose from "@/views/account/AccountViewChoose.vue";
import AccountViewUserLogin from "@/components/account/user/AccountViewUserLogin.vue";
import AccountViewUserSignup from "@/components/account/user/AccountViewUserSignup.vue";
import MainView from "@/views/MainView.vue";
import App from "@/App.vue";
import { useUserStore } from "@/stores/user";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
    },
    {
      path: "/account",
      name: "account",
      component: AccountViewChoose,
    },
    {
      path: "/user",
      name: "user",
      component: AccountViewUser,
    },
    {
      path: "/trainer",
      name: "trainer",
      component: AccountViewTrainer,
    },
    {
      path: "/user/login",
      name: "userLogin",
      component: AccountViewUserLogin,
    },
    {
      path: "/user/signup",
      name: "userSignup",
      component: AccountViewUserSignup,
    },
    {
      path: "/main",
      name: "main",
      component: MainView,
    },
  ],
});

export default router;
