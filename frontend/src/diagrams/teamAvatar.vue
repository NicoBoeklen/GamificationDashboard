<template>
   <img  v-if="!$vuetify.theme.global.current.dark" src="https://img.icons8.com/ios-filled/50/groups.png" alt="Avatar" style="width: 1.5em; height:1.5em; margin-right: 0.4em; border-radius: 0%;">
   <img  v-if="$vuetify.theme.global.current.dark" src="https://img.icons8.com/ios/50/FFFFFF/groups.png" alt="Avatar" style="width: 1.5em; height:1.5em; margin-right: 0.4em; border-radius: 0%;">
</template>

<style scoped>
</style>
