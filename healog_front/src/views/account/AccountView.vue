<template>
    <div>
        <RouterView />
    </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import {onMounted} from 'vue'

const router = useRouter()
onMounted(() => {
    router.push({name: 'account'})
})

</script>

<style scoped>

</style>