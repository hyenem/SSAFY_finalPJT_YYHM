import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_API_URL = 'http://localhost:8080/'
const REST_API_USER_LOGIN_URL ='http://localhost:8080/user/login'


export const useUserStore = defineStore('user', () => {
  const loginUser = {
    id : null,
    type : null
  }

  const logout = function(){
    loginUser.id=null
    loginUser.type=null
    sessionStorage.removeItem('access-token')
    router.push({name : "account"})
  }

  const login = function(email, password, type){
    axios.post(REST_API_USER_LOGIN_URL, {
        email : email,
        password : password,
        type: type
    }).then((res)=>{
        sessionStorage.setItem('access-token', res.data['access-token'])
        loginUser.id = res.data.user
        loginUser.type = type
        router.push({name: 'main'})
    }).catch((error)=>{
        if(error){
            console.log(error)
            if(error.response.status===406){
                alert("비밀번호가 일치하지 않습니다.")
            } else if(error.response.status===404){
                alert("존재하지 않는 이메일입니다.")
            }
        }
    })
  }


  return { loginUser, logout, login }
})