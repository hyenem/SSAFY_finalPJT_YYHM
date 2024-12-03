<template>
    <div class="request">
        <h4 class="title">Request</h4>
        <MyPageRequestTrainer v-if="userStore.loginUser.type==='trainer'"/>
        <MyPageRequestUser v-if = "userStore.loginUser.type==='user'"/>
    </div>
</template>
  
  
<script setup>
import { useUserStore } from '@/stores/userStore';
import MyPageRequestTrainer from './MyPageRequestTrainer.vue';
import MyPageRequestUser from './MyPageRequestUser.vue';

const userStore = useUserStore()

</script>
  
<style scoped>
.request {
    display: flex;
    flex-direction: column;
    padding: 1rem 2rem;
}
  
.title {
    font-size: 1.5rem;
    font-weight: bold;
    color: #333;
    border-bottom: 1px solid #ddd;
    padding-bottom: 0.5rem;
    margin-bottom: 1rem;
}
  
  .account-card {
    display: flex;
    flex-direction: column;
    gap: 1.5rem;
    padding: 1.5rem;
    border: 1px solid #ddd;
    border-radius: 3px;
  }
  
  .account-info {
    display: flex;
    justify-content: space-between;
    align-items: center;
    gap: 1rem;
    padding: 0.8rem;
    border-bottom: 1px solid #f0f0f0;
  }
  
  .account-info:last-child {
    border-bottom: none;
  }
  
  label {
    font-size: 1rem;
    font-weight: bold;
    color: #555;
    min-width: 100px;
  }
  
  .trainer-info {
    font-size: 1rem;
    font-weight: bold;
    color: #7fc678;
    cursor: pointer;
    transition: color 0.3s ease;
  }
  
  .trainer-info:hover {
    color: #65a45b;
  }
  
  div {
    font-size: 1rem;
    color: #444;
  }
  
  .btn-primary {
    padding: 0.5rem 1rem;
    background-color: #7fc678;
    color: white;
    border: none;
    border-radius: 3px;
    font-size: 0.9rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  .btn-primary:hover {
    background-color: #65a45b;
  }
  
  </style>
  