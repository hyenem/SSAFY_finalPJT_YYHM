<template>
    <div>
        <NavigationView />
        <div class="mypage">
            <div class="leftPanel">
                <MyPageViewSidebar />
            </div>
            <div class="rightPanel">
                <RouterView />
            </div>
        </div>
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

.mypage {
  display: flex;
  justify-content: space-between;
  align-items: stretch; /* 자식 요소 높이를 동일하게 설정 */
  padding: 2rem;
  background-color: #e0f1e2;
  gap: 1rem;
}

.leftPanel {
  width: 25%; /* 좌측 패널 폭 설정 */
  background-color: white; /* 배경 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 약간의 그림자 효과 */
  border-radius: 3px; /* 둥근 모서리 */
}

.rightPanel {
  width: 75%; /* 우측 패널 폭 설정 */
  overflow-y: auto; /* 내용이 길 경우 스크롤 활성화 */
  background-color: white; /* 배경 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 약간의 그림자 효과 */
  border-radius: 3px; /* 둥근 모서리 */
}


</style>