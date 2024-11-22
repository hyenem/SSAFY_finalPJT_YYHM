<template>
    <div>
        <h1>MyPage</h1>
        <NavigationView />
        <MyPageViewSidebar />
        <RouterView />
    </div>
</template>

<script setup>
import MyPageViewSidebar from '@/components/MyPage/MyPageViewSidebar.vue';
import NavigationView from './common/NavigationView.vue';
import { useUserStore } from '@/stores/userStore';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';

const userStore = useUserStore()
const router = useRouter()
const type = userStore.loginUser.type

localStorage.setItem('lastVisitedUrl', location.href)

onMounted((to, from, next)=>{
    if(!userStore.loginUser.id){
        alert("로그인 해주세요.")
        location.href="/"
    } else {
        router.replace({name : 'info'})
    }
})
</script>

<style scoped>

</style>