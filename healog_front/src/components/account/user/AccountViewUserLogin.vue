<template>
    <div>
        <h3>user login</h3>
        <label for="email">email: </label>
        <input type="text" v-model="email" id="email" name="email">
        <br>
        <label for="password">password: </label>
        <input type="password" v-model="password" id="password" name="password">
        <br>
        <button @click="login">로그인</button>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { ref } from 'vue'
import axios from 'axios'
import { useUserStore } from '@/stores/user';

const REST_API_USER_LOGIN_URL ='http://localhost:8080/user/login'
const router = useRouter()
const store = useUserStore()

const email = ref("")
const password = ref("")

const login = function(){
    axios.post(REST_API_USER_LOGIN_URL, {
        email : email.value,
        password : password.value
    }).then((res)=>{
        sessionStorage.setItem('access-token', res.data['access-token'])
        console.log(store.loginUser)
        store.loginUser.value = {
            id: res.data.id,
            name: res.data.name,
            email: res.data.email,
            gender: res.data.gender
        }
        console.log(store.loginUser)
        router.replace({name: "home"})
    }).catch((error)=>{
        if(error.response.status===406){
            alert("비밀번호가 일치하지 않습니다.")
        } else if(error.response.status===404){
            alert("존재하지 않는 이메일입니다.")
        }
    })
}

</script>

<style scoped>

</style>