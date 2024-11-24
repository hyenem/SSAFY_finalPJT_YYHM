<template>
  <div id="app">
    <!-- Navbar 자리 -->
    <router-view />
    <!-- Footer 자리 -->
  </div>
</template>

<script>
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useUserStore } from './stores/userStore';

export default {
  name: "App",
  setup(){
    const router = useRouter()
    
    onMounted(() => {
      router.push({name:'first'})
    })
  }
};
</script>

<style scoped>
</style>
