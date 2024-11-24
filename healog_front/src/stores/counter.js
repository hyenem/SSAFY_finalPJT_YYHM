import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'
const router = useRouter()

export const useCounterStore = defineStore('counter', () => {
  const INDEX_URL = 'http://localhost:5173'

  return { INDEX_URL }
})