import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'
import { computed, ref, watch } from 'vue'

const REST_API_TRAINER_URL = 'http://localhost:8080/trainer'
const REST_API_USER_URL = 'http://localhost:8080/user'
const REST_API_USER_LOGIN_URL ='http://localhost:8080/user/login'
const REST_API_TRAINER_LOGIN_URL ='http://localhost:8080/trainer/login'
const REST_API_SUBSCRIBE_URL = 'http://localhost:8080/subscribe'



export const useUserStore = defineStore('user', () => {
  const loginUser = {
    name : null,
    id : null,
    type : null,
    trainerExist : null
  }

  const follower = {
    id : null,
    name : null
  }

  const logout = function(){
    sessionStorage.removeItem('access-token')
    loginUser.id = null
    loginUser.type= null
    follower.id = null
    follower.name = null
    router.push({name : "account"})
  }


  const login = function(email, password, type){
    if(type==='user'){
      axios.post(REST_API_USER_LOGIN_URL, {
          email : email,
          password : password,
          type: type
      }).then((res)=>{
          sessionStorage.setItem('access-token', res.data['access-token'])
          router.push({name : 'main'})
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
    } else {
      axios.post(REST_API_TRAINER_LOGIN_URL, {
        email : email,
        password : password,
        type: type
      }).then((res)=>{
          sessionStorage.setItem('access-token', res.data['access-token'])
          router.push({name : 'main'})
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
  }

  const getUser = function(callback){
    let API_URL = ""
    if(loginUser.type==='user'){
      API_URL = REST_API_USER_URL
    } else if(loginUser.type==='trainer'){
      API_URL = REST_API_TRAINER_URL
    } else {
      return null
    }

    axios.get(`${API_URL}?id=${loginUser.id}`)
    .then((res)=>{
      callback(res.data)
    }).catch((error)=>{
      console.log(error)
      alert("잘못된 접근입니다.")
      location.href='/'
      callback(null)
    })
  }


  return { loginUser, logout, login, getUser, follower }
})