<template>
    <v-hover v-slot:default="{ hover }">
        <v-card
                :elevation="hover ? 24 : 2"
                max-width="300">
            <v-list-item>
                <v-avatar v-if="item && item.mento && item.mento.imgSrc!==null" class="mr-2">
                    <img :src="item.mento.imgSrc">
                </v-avatar>
                <v-icon size="64" v-else>
                    mdi-account-circle
                </v-icon>
                <v-list-item-content>
                    <v-list-item-title class="headline">{{item.title}}</v-list-item-title>
                    <v-list-item-subtitle>by {{item.mento.name}}({{item.mento.studentId}})</v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>

            <router-link :to="`/mentoring/detail/${item.id}`">
                <v-img style="cursor:pointer"
                       src="https://cdn.pixabay.com/photo/2015/04/20/13/17/work-731198_1280.jpg"
                       height="194"
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
        props: ['item'],
    }
</script>

<style scoped>

</style>