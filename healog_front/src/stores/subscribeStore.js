import axios from 'axios'
import { defineStore } from 'pinia'
import { useUserStore } from './userStore'

export const useSubscribeStore = defineStore('subscribe', () => {
    const userStore = useUserStore()

    const REST_API_SUBSCRIBE_URL = "http://localhost:8080/subscribe"

    const checkValid = axios.patch(REST_API_SUBSCRIBE_URL+"/check?id="+userStore.loginUser.id)


    const getTrainerInfoByUserId = function(userid, callback){
        axios.get(REST_API_SUBSCRIBE_URL+'/trainer?id='+userid)
        .then((res)=>{
            callback(res.data)
        })
    }

    const getGymById = function(gymId, callback){
        axios.get(REST_API_SUBSCRIBE_URL+'/gym?id='+gymId)
        .then((res)=>{
            callback(res.data)
        })
    }

  return { getTrainerInfoByUserId, getGymById, checkValid }
})