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

onMounted(()=>{
    if(!userStore.loginUser.id){
        alert("로그인 해주세요.")
        location.href="/"
    } else {
      if(location.href.split('/')[location.href.split('/').length-1]==="mypage"){
        router.push({name : 'info'})
      }
    }
})
</script>

<style scoped>

.mypage {
  display: flex;
  justify-content: space-between;
  align-items: stretch; /* 자식 요소 높이를 동일하게 설정 */
  height: calc(100vh - 5rem);
  padding: 2rem;
  background-color: #e0f1e2;
  gap: 1rem;
}

.leftPanel {
  width: 25%; /* 좌측 패널 폭 설정 */
  height: 100%; /* 부모 높이를 따라 좌측 패널 높이 설정 */
  background-color: white; /* 배경 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 약간의 그림자 효과 */
  border-radius: 3px; /* 둥근 모서리 */
}

.rightPanel {
  width: 75%; /* 우측 패널 폭 설정 */
  height: 100%; /* 부모 높이를 따라 우측 패널 높이 설정 */
  background-color: white; /* 배경 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 약간의 그림자 효과 */
  border-radius: 3px; /* 둥근 모서리 */
  overflow-y: auto;
}

/* 반응형 스타일 */
@media (max-width: 1024px) {
  .mypage {
    height: auto; /* 높이를 자동으로 조정 */
    flex-direction: column; /* 좌우 배치에서 위아래 배치로 변경 */
    align-items: stretch; /* 요소를 동일한 폭으로 */
  }

  .leftPanel,
  .rightPanel {
    width: 100%; /* 모든 패널이 화면 너비를 채움 */
    height: auto; /* 높이를 자동으로 조정 */
  }
}

</style>