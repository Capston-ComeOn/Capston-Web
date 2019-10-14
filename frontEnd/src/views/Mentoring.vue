<template>
    <v-row class="justify-center">
        <v-card class="pa-3 ma-5">
            <v-toolbar color="green" elevation="10">
                <v-toolbar-title class="text-center pt-2">
                    멘토링
                </v-toolbar-title>
            </v-toolbar>
            <v-row class="justify-end  ma-5">
                <v-btn color="orange lighten-2" dark>작성하기</v-btn>
            </v-row>
            <v-row class="justify-center mb-5">
                <card :item="item" class="ma-4" v-for="(item,i) in items" :key="i"/>
            </v-row>
            <v-row class="justify-center mt-5">
                <v-progress-circular
                        class="m-5"
                        :width="5"
                        color="green"
                        :size="50"
                        indeterminate
                ></v-progress-circular>
            </v-row>
        </v-card>
    </v-row>
</template>

<script>
    export default {
        data() {
            return {
                dialog: false,
                items: [
                    {title: '웹 프로젝트', author: 'john', content: '프론트엔드 1명, 백엔드 1명 구해요.'},
                    {title: '웹 프로젝트', author: 'john', content: '프론트엔드 1명, 백엔드 1명 구해요.'},
                    {title: '웹 프로젝트', author: 'john', content: '프론트엔드 1명, 백엔드 1명 구해요.'}
                ]
            }
        },
        methods: {
        },
        components: {
            card: () => import('@/components/Card')
        }
    }
</script>

<style scoped>

</style>