<template>
    <v-hover v-slot:default="{ hover }">
        <v-card
                :elevation="hover ? 24 : 2"
                max-width="300">
            <v-list-item>
                <v-avatar class="mr-2" v-if="item && item.account && item.account.imgSrc!==null">
                    <img :src="item.account.imgSrc">
                </v-avatar>
                <v-icon size="64" v-else>
                    mdi-account-circle
                </v-icon>
                <v-list-item-content>
                    <v-list-item-title class="headline">{{item.title}}</v-list-item-title>
                    <v-list-item-subtitle>by {{item.account.name}}({{item.account.studentId}})</v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>

            <router-link :to="`/project/detail/${item.id}`">
                <v-img height="194"
                       src="https://cdn.pixabay.com/photo/2015/04/20/13/17/work-731198_1280.jpg"
                       style="cursor:pointer"
                ></v-img>
            </router-link>
            <v-card-text class="ma-5">
                시작 시간: {{item.startTime}}
                <br/>
                종료 시간: {{item.endTime}}
            </v-card-text>
            <div class="text-center mb-5">
                {{item.content}}
            </div>
        </v-card>
    </v-hover>
</template>

<script>

    export default {
        props: ['item']
    }
</script>

<style scoped>

</style>