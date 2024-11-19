import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const REST_API_URL = 'http://localhost:8080/'

export const useUserStore = defineStore('user', () => {
  const loginUser = ref({})

  const logout = function(){
    loginUser.value={}
    sessionStorage.removeItem('access-token')
  }

  const checkTokenValid = function(){
    if(sessionStorage.getItem('access-token')!==null){
      axios.post(REST_API_URL+"user/login/validate",{
        'access-token' : sessionStorage.getItem('access-token')
      }).then((res)=>{
      }).catch((error)=>{
        sessionStorage.removeItem('access-token')
        loginUser.value={}
      })
    }
  }

  return { loginUser, logout, checkTokenValid }
})