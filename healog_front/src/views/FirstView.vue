<template>
    <div id="first">
      <!-- Navbar 자리 -->
      <router-view />
      <!-- Footer 자리 -->
    </div>
  </template>
  
  <script>
  import { onMounted } from 'vue';
  import { useUserStore } from '@/stores/userStore';
  import { useRouter } from 'vue-router';
  
  
  export default {
    name: "First",
    setup(){
      const router = useRouter()
      const userStore = useUserStore()
      
      onMounted(() => {
        if(userStore.loginUser.id){
          let beforeRouter = localStorage.getItem('lastVisitedUrl')
          if(!beforeRouter){
            router.push({name: 'main'})
          } else {
            router.push({name: beforeRouter.split('/')[3]})
          }
        } else {
          router.push({name : 'account'})
        }
      })
    }
  };
  </script>
  
  <style scoped>
  </style>
  