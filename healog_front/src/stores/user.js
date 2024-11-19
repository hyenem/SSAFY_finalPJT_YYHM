import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_API_URL = 'http://localhost:8080/'

export const useUserStore = defineStore('user', () => {
  const loginUser = ref({})

  const logout = function(){
    loginUser.value={}
    sessionStorage.removeItem('access-token')
    router.push({name : "account"})
  }

  return { loginUser, logout}
})