import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'

const REST_API_URL = 'http://localhost:8080/'

const router = useRouter()

export const useCounterStore = defineStore('counter', () => {
  const goHome = function(){
    router.push({name : 'home'})
  }
  return { goHome }
})