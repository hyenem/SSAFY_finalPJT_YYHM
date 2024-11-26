<template>
    <div class="sidebar">
        <RouterLink 
            :to="{name : 'info'}"
            class="link"
        >
        <font-awesome-icon :icon="['fas', 'gear']" class="icon"/>
        Settings
        </RouterLink>
        <RouterLink 
            :to="{name : 'subscribe'}" 
            v-if="type==='user'"
            class="link"
        >
        <font-awesome-icon :icon="['fas', 'credit-card']" class="icon"/>
        Subscription
        </RouterLink>
        <RouterLink 
            :to="{name : 'request'}" 
            class="link"
        >
        <font-awesome-icon :icon="['fas', 'hand']" class="icon"/>
        Request
        </RouterLink>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/userStore';

const userStore =useUserStore()

const type = userStore.loginUser.type
</script>

<style scoped>
.sidebar {
  display: flex;
  flex-direction: column;
  padding: 1.5rem;
}

.link {
  display: flex;
  gap: 1.5rem;
  align-items: center;
  font-size: 1rem;
  color: #333;
  text-decoration: none;
  padding: 0.8rem 0rem;
  transition: background-color 0.3s ease, color 0.3s ease;
}

.icon {
  font-size: 1.3rem;
}

.link:hover {
  color: #7fc678; /* 호버 시 글자색 */
  border-bottom: 1px solid;
  font-weight: bold;
}
</style>