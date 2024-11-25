<template>
    <div class="nav">
        <img class="logo" @click="gotoHome" src="@/assets/logo.png"/>
        <!--
        <button @click="gotoHome">home</button>
        -->
        <div v-if="userStore.loginUser.type==='trainer'">
            <div v-if="followerList.length===0">팔로워가 없습니다.</div>
            <div v-else>
                <div v-for="follower in followerList" @click = "setFollower(follower.id, follower.name)" class="subscribeUser" >
                    <div>
                      <font-awesome-icon :icon="['fas', 'circle-user']" />
                    </div>
                    <div class="subscribeUserName">
                      {{ follower.name }}
                    </div>
                </div>
            </div>
      </div>
        <div class="mypage">
            <div class="name">
                Hello, {{ loginUserInfo.name }}
            </div>
            <font-awesome-icon
                class="user"
                @click="toggleLogoutModal"
                :icon="['fas', 'circle-user']"
            />
            <div v-if="showLogoutModal" class="logout-modal">
                <button @click="userStore.logout" class="setting">
                  <font-awesome-icon :icon="['fas', 'arrow-right-from-bracket']" />
                  로그아웃
                </button>
                <button @click="gotoMyPage" class="setting">
                  <font-awesome-icon :icon="['fas', 'user']" />
                  마이페이지
                </button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';
import { onBeforeMount, onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';

const userStore = useUserStore()
const router = useRouter()

const followerList = ref([])
const showLogoutModal = ref(false); // 모달 표시 상태 관리
const REST_API_SUBSCRIBE_URL = "http://localhost:8080/subscribe"

const setFollower = function(id, name){
    userStore.follower.id = id
    userStore.follower.name = name
}

const gotoHome = function(){
    router.push({name : 'main'})
}

const loginUserInfo = ref({
    name : null
})

const gotoMyPage = function(){
    router.push({name: 'mypage'})
}

// 로그아웃 모달 토글
const toggleLogoutModal = function () {
  showLogoutModal.value = !showLogoutModal.value;
};

onMounted(()=>{
    userStore.getUser((userData)=>{
        if(userData){
            loginUserInfo.value = userData
        }
    })

    if(userStore.loginUser.type==='trainer'){
        axios.get(REST_API_SUBSCRIBE_URL+"/follow?id="+userStore.loginUser.id)
        .then((res)=>{
            followerList.value=res.data
            if(!userStore.follower.id && res.data.length!==0){
              userStore.follower.id=res.data[0].id
              userStore.follower.name = res.data[0].name
            }
        })
    }
})
</script>

<style scoped>
.nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
}

.logo {
  width: 5rem;
  cursor: pointer;
}

.logo:hover {
  transform: scale(1.1);
}

.mypage {
  display: flex;
  align-items: center;
  gap: 16px;
  font-size: 14px;
  position: relative; /* 부모 컨테이너를 기준으로 위치 설정 */
}

.mypage .name {
  font-weight: 500;
  color: #333;
}

.mypage div {
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.2s ease-in-out;
}

.subscribeUser {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 1.5rem;
}

.subscribeUserName {
  font-size: 0.5rem;
}

.user {
  font-size: 2rem;
  cursor: pointer;
}

.logout-modal {
  position: absolute;
  display: flex;
  flex-direction: column;
  gap: 1rem;
  top: 100%;
  right: 0;
  background: white;
  border: 1px solid #ddd;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  padding: 10px;
  margin-top: 8px;
  z-index: 1000;
}

.setting {
  display: flex;
  gap: 1rem;
  padding: 0.5rem;
}

.logout-modal button {
  border: none;
  border-radius: 3px;
  cursor: pointer;
  font-size: 14px;
  width: 8rem;
}

.logout-modal button:hover {
  background-color: #7FC678;
}

.mypage div:hover {
  color: #0056b3;
}
</style>

