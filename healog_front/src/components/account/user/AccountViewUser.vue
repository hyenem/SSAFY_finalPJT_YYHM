<template>
    <div class="useraccount">
        <div class="logo-section">
            <img src="@/assets/logo.png" />
            <h3>HEALOG</h3>
        </div>
        <div class="button-section">
            <button @click="gotoUserLogin" class="primary-btn">로그인</button>
            <button @click="gotoUserSignup" class="primary-btn">회원가입</button>
        </div>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router'


const router = useRouter()
const gotoUserLogin = function(){
    router.push({name: 'userLogin'})
}
const gotoUserSignup = function(){
    router.push({name: 'userSignup'})
}
</script>

<style scoped>
.useraccount {
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
  background-color: #65a45b;
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