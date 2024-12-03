<template>
    <div class="accountchoose">
        <div class="logo-section">
            <img src="@/assets/logo.png" />
            <h3>HEALOG</h3>
        </div>
        <div class="button-section">
            <button @click="gotoUser" class="primary-btn">회원</button>
            <button @click="gotoTrainter" class="second-btn">트레이너</button>
        </div>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()
const gotoUser = function(){
    router.push({name : 'accountUser'})
}
const gotoTrainter = function(){
    router.push({name : 'accountTrainer'})
}
</script>

<style scoped>
/* 전체 화면 배경과 중앙 정렬 */
.accountchoose {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  text-align: center;
}

/* 로고 섹션 */
.logo-section img {
  width: 200px; /* 로고 크기 조정 */
  margin-bottom: 1rem;
  animation: fadeIn 1.5s ease-out; /* 부드러운 페이드 인 효과 */
}

.logo-section h3 {
  font-size: 2rem;
  color: #333;
  margin: 0;
  font-weight: bold;
}

/* 버튼 섹션 */
.button-section {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  margin-top: 2rem;
}

/* 버튼 스타일 */
button {
  padding: 0.8rem 8rem;
  font-size: 1.2rem;
  border: none;
  border-radius: 5rem;
  cursor: pointer;
  transition: transform 0.3s, background-color 0.3s;
}

.primary-btn {
  background-color: #7fc678;
  color: white;
}

.primary-btn:hover {
  transform: scale(1.05);
}

.second-btn {
  background-color: #e0f1e2;
  color: black;
}

.second-btn:hover {
  transform: scale(1.05);
}

/* 애니메이션 */
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>
