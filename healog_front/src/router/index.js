import { createRouter, createWebHistory } from 'vue-router';
import AccountViewUser from '@/components/account/user/AccountViewUser.vue';
import AccountViewChoose from '@/components/account/AccountViewChoose.vue';
import AccountViewUserLogin from '@/components/account/user/AccountViewUserLogin.vue';
import AccountViewUserSignup from '@/components/account/user/AccountViewUserSignup.vue';
import MainView from '@/views/MainView.vue';
import AccountView from '@/views/AccountView.vue';
import FirstView from '@/views/FirstView.vue';
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';
import AccountViewTrainer from '@/components/account/trainer/AccountViewTrainer.vue';
import AccountViewTrainerLogin from '@/components/account/trainer/AccountViewTrainerLogin.vue';
import AccountViewTrainerSignup from '@/components/account/trainer/AccountViewTrainerSignup.vue';
import MyPageView from '@/views/MyPageView.vue';
import MyPageViewInfo from '@/components/MyPage/MyPageViewInfo.vue';
import MyPageViewSubscribe from '@/components/MyPage/MyPageViewSubscribe.vue';
import MyPageRequest from '@/components/MyPage/MyPageRequest.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'first',
      component: FirstView
    },
    {
      path: '/main',
      name: 'main',
      component: MainView
    },
    {
      path:'/mypage',
      name: 'mypage',
      component : MyPageView,
      children : [
        {
          path: 'info',
          name: 'info',
          component: MyPageViewInfo
        },
        {
          path: 'subscribe',
          name: 'subscribe',
          component: MyPageViewSubscribe
        },
        {
          path: 'request',
          name: 'request',
          component : MyPageRequest
        }
      ]
    },
    {
      path: '/account',
      name: 'account',
      component: AccountView,
      children: [
        {
          path: 'choose',
          name: 'choose',
          component: AccountViewChoose
        },
        {
          path: 'user',
          name: 'accountUser',
          component: AccountViewUser,
        },
        {
          path: 'trainer',
          name: 'accountTrainer',
          component: AccountViewTrainer
        },
        {
          path: 'user/login',
          name: 'userLogin',
          component: AccountViewUserLogin
        },
        {
          path: 'trainer/login',
          name: 'trainerLogin',
          component: AccountViewTrainerLogin
        },
        {
          path: 'user/signup',
          name: 'userSignup',
          component: AccountViewUserSignup
        },
        {
          path: 'trainer/signup',
          name: 'trainerSignup',
          component: AccountViewTrainerSignup
        }
      ]
    },
  ],
});

router.beforeEach((to, from, next)=>{
  const userStore = useUserStore()
  if(sessionStorage.getItem('access-token')){
    axios.get('http://localhost:8080/check', {
      headers:{
        'access-token' : sessionStorage.getItem('access-token')
      }
    }).then((res)=>{
      userStore.loginUser.name = res.data.name
      userStore.loginUser.id = res.data.id
      userStore.loginUser.type = res.data.type
      if(userStore.loginUser.type==='trainer'){
        if(!userStore.follower.id){
          axios.get("http://localhost:8080/subscribe/follow?id="+userStore.loginUser.id)
          .then((res)=>{
            if(res.data.length!==0){
              userStore.follower.id = res.data[0].id
              userStore.follower.name = res.data[0].name
            }
          })
        }
      } else {
        userStore.loginUser.trainerExist = res.data.trainerExist
      }
      next()
    }).catch(()=>{
      sessionStorage.removeItem('access-token')
      userStore.loginUser.name = null
      userStore.loginUser.id = null
      userStore.loginUser.type = null
      userStore.follower.name = null
      userStore.follower.id = null
      userStore.loginUser.trainerExist = null
      alert("유효하지 않은 접근입니다. 다시 로그인해주세요.")
      next({name : 'main'})
    })
  } else {
    if(to.fullPath.split('/')[1]!=='account'){
      userStore.loginUser.name=null
      userStore.loginUser.id=null
      userStore.loginUser.type=null
      userStore.follower.id = null
      userStore.follower.name=null
      userStore.loginUser.trainerExist = null
      next({name : 'account'})
    } else{
      next()
    }
  }
})

export default router;
