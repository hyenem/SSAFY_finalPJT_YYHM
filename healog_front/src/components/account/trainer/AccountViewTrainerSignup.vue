<template>
    <div class="signup-container">
        <div class="signup-card">
            <img src="@/assets/logo.png" alt="Healog Logo" class="logo" />
            <h3>HEALOG</h3>
            <div class="usersignup">
                <label for="name">Name (*)</label>
                <input
                type="text"
                maxlength="10"
                name="name"
                id="name"
                v-model="name"
                placeholder="Enter your name"
                />

                <label for="email">Email (*)</label>
                <input
                @click="alertCheck"
                type="email"
                disabled
                maxlength="10"
                name="name"
                id="id"
                :value="checkedEmail"
                />
                <button @click="openEmailCheck" class="secondary-btn">
                Email 중복 검사
                </button>
                <div v-if="isOpenEmailCheck" class="email-check">
                <input
                    type="email"
                    v-model="checkingEmail"
                    placeholder="Enter your email"
                />
                <button @click="emailCheck" class="secondary-btn">
                    이메일 중복 확인
                </button>
                </div>

                <label for="password">Password (*)</label>
                <input
                type="password"
                maxlength="40"
                name="password"
                id="password"
                v-model="password"
                placeholder="Enter your password"
                />

                <label for="checkpassword">Confirm Password (*)</label>
                <input
                type="password"
                maxlength="40"
                name="checkpassword"
                id="checkpassword"
                v-model="checkPassword"
                placeholder="Confirm your password"
                />
                <p v-if="password !== '' && checkPassword !== ''" class="password-msg">
                {{ passwordCheckMessage }}
                </p>

                <label for="gender">Gender (*)</label>
                <div class="gender-options">
                <input type="radio" name="gender" id="male" value="0" v-model="gender" />
                <label for="male">Male</label>
                <input type="radio" name="gender" id="female" value="1" v-model="gender" />
                <label for="female">Female</label>
                </div>

                <label for="birthday">Birthday</label>
                <input
                type="date"
                maxlength="10"
                name="birthday"
                id="birthday"
                v-model="birthday"
                />

                <label for="phonenumber">Phone Number (*)</label>
                <input
                type="tel"
                maxlength="13"
                name="phonenumber"
                id="phonenumber"
                v-model="phoneNumber"
                placeholder="Enter your phone number"
                />
            </div>
            <button @click="signup" class="primary-btn">회원가입</button>
        </div>
    </div>
</template>

<script setup>
import {onMounted, ref, watch} from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router';

const router = useRouter()
const REST_API_TRAINER_URL = 'http://localhost:8080/trainer'

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
const location = ref(0)

//중복 채크 로직
const emailCheck = function(){
    axios.post(REST_API_TRAINER_URL+'/signup/id', {email: checkingEmail.value})
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
        && gender.value.trim!=='' && phoneNumber.value.trim!=='' && location.value!==0){
        if(password.value===checkPassword.value){
            axios.post(REST_API_TRAINER_URL+'/signup', {
                name: name.value,
                email : checkedEmail.value,
                password: password.value,
                gender : gender.value,
                birthday : birthday.value,
                phonenumber : phoneNumber.value,
                location : location.value
            }).then((res)=>{
                alert("회원가입 되었습니다. 로그인창으로 이동합니다.")
                router.replace({name: "trainerLogin"})
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

const gyms = ref(null)
onMounted(()=>{
    axios.get(REST_API_TRAINER_URL+'/gym')
    .then((res)=>{
        gyms.value=res.data
    })
})

</script>

<style scoped>
.signup-container {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  height: 100vh;
  background: linear-gradient(to bottom, #f9f9f9, #e0f1e2);
  font-family: Arial, sans-serif;
  overflow-y: auto;
  padding: 1rem; /* 모바일에서도 여백 확보 */
}

.signup-card {
  background: white;
  border-radius: 10px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
  padding: 2rem;
  width: 400px;
  max-width: 90%;
  text-align: center;
  margin: 2rem 0;
}

.usersignup {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

@media screen and (max-width: 768px) {
  .signup-card {
    width: 90%;
    padding: 1.5rem;
  }

  .usersignup label,
  .usersignup input {
    font-size: 0.9rem;
  }

  .primary-btn {
    font-size: 0.9rem;
    padding: 0.6rem 1rem;
  }

  .secondary-btn {
    font-size: 0.8rem;
    padding: 0.5rem 0.8rem;
  }
}

/* 로고 */
.logo {
  width: 100px;
}

/* 제목 */
h3 {
  margin-bottom: 1rem;
  font-size: 1.5rem;
  color: #333;
}

/* 입력 필드 스타일 */
.usersignup {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  margin-bottom: 1.5rem;
}

.usersignup label {
  font-size: 0.9rem;
  color: #555;
  text-align: left;
}

.usersignup input {
  padding: 0.8rem;
  font-size: 1rem;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #f9f9f9;
  transition: border-color 0.3s ease, box-shadow 0.3s ease;
}

.usersignup input:focus {
  border-color: #7fc678;
  box-shadow: 0px 0px 4px rgba(127, 198, 120, 0.5);
  outline: none;
}

.email-check {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}

.gender-options {
  display: flex;
  justify-content: start;
  gap: 1rem;
}

.password-msg {
  font-size: 0.8rem;
  color: #e74c3c;
}

/* 버튼 스타일 */
.primary-btn {
  padding: 0.8rem 1.5rem;
  font-size: 1rem;
  font-weight: bold;
  color: white;
  background-color: #7fc678;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.primary-btn:hover {
  background-color: #65a45b;
  transform: scale(1.05);
}

.secondary-btn {
  padding: 0.5rem 1rem;
  font-size: 0.9rem;
  color: #7fc678;
  background: transparent;
  border: 1px solid #7fc678;;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease, color 0.3s ease;
}

.secondary-btn:hover {
  color: white;
  background-color: #7fc678;
}

/* 애니메이션 */
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>