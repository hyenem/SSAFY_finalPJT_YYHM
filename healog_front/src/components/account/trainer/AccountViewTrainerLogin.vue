<template>
    <div class="login-container">
    <div class="login-card">
      <img src="@/assets/logo.png" alt="Healog Logo" class="logo" />
      <h3>HEALOG</h3>
      <div class="trainerlogin">
        <label for="email">Email</label>
        <input 
            type="text" 
            v-model="email" 
            id="email" 
            name="email"
            placeholder="Enter your email"
        />
        <label for="password">Password</label>
        <input
            type="password" 
            v-model="password" 
            id="password" 
            name="password"
            placeholder="Enter your password"
        />
      </div>
    <button @click="userStore.login(email, password, 'trainer')" class="login-btn">
        로그인
    </button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useUserStore } from '@/stores/userStore';

const userStore = useUserStore()

const email = ref("")
const password = ref("")


</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(to bottom, #f9f9f9, #e0f1e2);
  font-family: Arial, sans-serif;
}

/* 로그인 카드 */
.login-card {
  background: white;
  border-radius: 10px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
  padding: 2rem;
  width: 350px;
  text-align: center;
}

/* 로고 */
.logo {
  width: 100px;
  animation: fadeIn 1.2s ease-out;
}

/* 제목 */
h3 {
  margin-bottom: 1rem;
  font-size: 1.5rem;
  color: #333;
}

/* 입력 필드 스타일 */
.trainerlogin {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  margin-bottom: 1.5rem;
}

.trainerlogin label {
  font-size: 0.9rem;
  color: #555;
  text-align: left;
}

.trainerlogin input {
  padding: 0.8rem;
  font-size: 1rem;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #f9f9f9;
  transition: border-color 0.3s ease, box-shadow 0.3s ease;
}

.trainerlogin input:focus {
  border-color: #7fc678;
  box-shadow: 0px 0px 4px rgba(127, 198, 120, 0.5);
  outline: none;
}

/* 버튼 스타일 */
.login-btn {
  padding: 0.8rem 1.5rem;
  font-size: 1rem;
  font-weight: bold;
  color: white;
  background-color: #7fc678;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.login-btn:hover {
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