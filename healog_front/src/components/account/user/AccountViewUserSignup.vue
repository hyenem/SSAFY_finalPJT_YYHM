<template>
    <div>
        <h3>user signup</h3>
        <label for="name">name(*): </label>
        <input type="text" maxlength="10" name="name" id="name" v-model="name">
        <br>
        <label for="email">email(*): </label>
        <input @click="alertCheck" type="email" disabled maxlength="10" name="name" id="id" :value="checkedEmail">
        <button @click="openEmailCheck">email 중복 검사</button>
        <div v-if="isOpenEmailCheck">
            <pr>이메일을 입력해주세요.</pr>
            <input type="email" v-model="checkingEmail">
            <button @click="emailCheck">이메일 중복 확인</button>
        </div>
        <br>
        <label for="password">password(*): </label>
        <input type="password" maxlength="40" name="password" id="password" v-model="password">
        <br>
        <label for="checkpassword">checkpassword:(*) </label>
        <input type="password" maxlength="40" name="checkpassword" id="checkpassword" v-model="checkPassword">
        <p v-if="password!=='' && checkPassword!==''">{{ passwordCheckMessage }}</p>
        <br>
        <label for="gender">gender(*): </label>
        <input type="radio" name="gender" id="" value=0 v-model="gender"> male
        <input type="radio" name="gender" id="" value=1 v-model="gender"> female
        <br>
        <label for="birthday">birthday: </label>
        <input type="date" maxlength="10" name="name" id="id" v-model="birthday">
        <br>
        <label for="phonenumber">phonenumber(*): </label>
        <input type="tel" maxlength="13" name="name" id="id" v-model="phoneNumber">
        <br>
        <button @click="signup">회원가입</button>
    </div>
</template>

<script setup>
import {ref, watch} from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router';

const router = useRouter()
const REST_API_USER_SIGNUP_URL = 'http://localhost:8080/user/signup'

const isOpenEmailCheck = ref(false)
const openEmailCheck = function(){
    isOpenEmailCheck.value = !isOpenEmailCheck.value
}

const alertCheck = function(){
    alert("email 중복 검사 버튼을 눌러주세요.")
}

//중복 채크 하기 전 이메일
const checkingEmail = ref("")
//중복 채크 된 이메일
const checkedEmail = ref("")

const name = ref("")
const password = ref("")
const checkPassword = ref("")
const passwordCheckMessage = ref("")
const gender = ref("")
const birthday = ref("")
const phoneNumber = ref("")

//중복 채크 로직
const emailCheck = function(){
    axios.post(REST_API_USER_SIGNUP_URL+'/id', {email: checkingEmail.value})
        .then((res)=>{
            checkedEmail.value = res.data
        }).catch((error)=>{
            alert("사용할 수 없는 email입니다.")
        })
}

watch(()=>checkPassword.value, ()=>{
    if(checkPassword.value === password.value){
        passwordCheckMessage.value = "비밀번호가 일치합니다."
    } else {
        passwordCheckMessage.value = "비밀번호가 일치하지 않습니다."
    }
})

watch(()=>password.value, ()=>{
    if(checkPassword.value === password.value){
        passwordCheckMessage.value = "비밀번호가 일치합니다."
    } else {
        passwordCheckMessage.value = "비밀번호가 일치하지 않습니다."
    }
})

const signup = function(){
    if(name.value.trim!=='' && checkedEmail.value.trim!=='' && password.value.trim!=='' && checkPassword.value.trim!==''
        && gender.value.trim!=='' && phoneNumber.value.trim!==''){
        if(password.value===checkPassword.value){
            axios.post(REST_API_USER_SIGNUP_URL, {
                name: name.value,
                email : checkedEmail.value,
                password: password.value,
                gender : gender.value,
                birthday : birthday.value,
                phonenumber : phoneNumber.value
            }).then((res)=>{
                alert("회원가입 되었습니다. 로그인창으로 이동합니다.")
                router.replace({name: "userLogin"})
            }).catch((error)=>{
                alert("회원 가입 실패")
            })
        } else {
            alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.")
        }
    } else {
        alert("필수 정보를 모두 입력해주세요.")
    }
}

</script>

<style scoped>

</style>