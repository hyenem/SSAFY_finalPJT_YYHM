<template>
    <div class="nav">
      <div class="nav-first">
        <img class="logo" @click="gotoHome" src="@/assets/logo.png"/>
        <div v-if="userStore.loginUser.type==='trainer'">
            <div v-if="followerList.length===0">팔로워가 없습니다.</div>
            <div v-else>
              <div class="follwers">
                <div v-for="follower in followerList" @click = "setFollower(follower.id, follower.name)" 
                                        :class="{selectedFollower : follower.id === userStore.follower.id}">
                  <div class="subscribeUser">
                    <div>
                      <font-awesome-icon :icon="['fas', 'circle-user']" />
                    </div>
                    <div class="subscribeUserName">
                      {{ follower.name }}
                    </div>
                  </div>
                </div>
              </div>
            </div>
        </div>
      </div>
      <div class="nav-second">
        <div class="alarm">
          <font-awesome-icon 
            :icon="['fas', 'bell']" 
            @click="router.push({name : 'request'})"
            class="icon" 
          />
          <div v-if="userStore.loginUser.type==='user' && after !== 0" class="badge">{{ after }}</div>
          <div v-else-if="userStore.loginUser.type==='trainer' && before !==0" class="badge">{{ before }}</div>
        </div>
        <hr>
        <div class="mypage">
          <div class="name">
            Hello, {{ loginUserInfo.name }}님
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
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';
import { computed, onBeforeMount, onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';

const userStore = useUserStore()
const router = useRouter()

const followerList = ref([])
const showLogoutModal = ref(false); // 모달 표시 상태 관리
const REST_API_SUBSCRIBE_URL = "http://localhost:8080/subscribe"

const ptList = ref([])
const before = ref(0)
const after = ref(0)

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
            axios.get("http://localhost:8080/pt/request/trainer",{
                params : {
                    trainerId : userStore.loginUser.id
                }
            }).then((res)=>{
                ptList.value = res.data
                before.value = ptList.value.filter((pt)=>pt.requestState<=4).length
                after.value = ptList.value.filter((pt)=>pt.requestState>4).length
            })
        })
    } else {
      axios.get("http://localhost:8080/pt/request/user",{
                params : {
                    userId : userStore.loginUser.id
                }
            }).then((res)=>{
                ptList.value = res.data
                before.value = ptList.value.filter((pt)=>pt.requestState<=4).length
                after.value = ptList.value.filter((pt)=>pt.requestState>4).length
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

.nav-first {
  display: flex;
  gap: 3rem;
}

.nav-second {
  display: flex;
  gap: 2rem;
}

.follwers {
  display: flex;
  gap: 1rem;
}

.logo {
  width: 5rem;
  cursor: pointer;
  margin-bottom: 0.3rem;
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

.alarm {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 2.5rem; /* 알림 박스 크기 */
  height: 2.5rem;
  background-color: #e7e9ef; /* 박스 배경색 */
  border-radius: 1rem; /* 박스를 둥글게 */
}

.alarm .icon {
  font-size: 1.3rem; /* 아이콘 크기 */
  color: #1d1d1e; /* 아이콘 색상 */
  cursor: pointer;
}

.alarm .badge {
  position: absolute;
  top: -0.5rem; /* 배지 위치 조정 */
  right: -0.5rem;
  background-color: #ff3b30; /* 배지 배경색 (빨간색) */
  color: white; /* 배지 텍스트 색상 */
  font-size: 0.7rem;
  font-weight: bold;
  border: 1px solid white;
  border-radius: 50%; /* 배지를 동그랗게 */
  padding: 0.2rem 0.1rem; /* 배지 내부 여백 */
  min-width: 1rem; /* 배지 최소 크기 */
  text-align: center;
  line-height: 1; /* 텍스트 정렬 */
}

.alarm:hover {
  background-color: #d3d6dd; /* Hover 시 밝은 색으로 변경 */
  transform: scale(1.1); /* 살짝 확대 */
  transition: background-color 0.3s ease, transform 0.3s ease; /* 부드러운 전환 효과 */
}

.user:hover {
  transform: scale(1.3); /* 크기 확대 */
  transition: color 0.3s ease, transform 0.3s ease;
}

.subscribeUser:hover {
  transform: scale(1.2); /* 크기 확대 */
  transition: background-color 0.3s ease;
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

.selectedFollower{
  transform: scale(1.4); /* 크기 확대 */
  transition: color 0.3s ease, transform 0.3s ease;
}
</style>

