import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'
const router = useRouter()

export const usePtStore = defineStore('pt', () => {
  const INDEX_URL = 'http://localhost:5173/pt'

  

  return { INDEX_URL }
})