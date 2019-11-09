<template>
    <v-container class="mx-auto">
        <v-layout>
            <v-row class="mt-5">
                <v-card class="mx-auto pa-3">
                    <v-toolbar color="green" elevation="10">
                        <v-toolbar-title class="text-center pt-2">
                            멘토링
                        </v-toolbar-title>
                    </v-toolbar>
                    <v-row class="justify-end ma-5">
                        <v-btn to="/mentoring/write" color="orange lighten-2" dark>작성하기</v-btn>
                    </v-row>
                    <h1 class="mb-5 text-center"> 현재 진행중인 멘토링 </h1>
                    <v-row class="justify-center mb-5">
                        <card :item="item" class="ma-4" v-for="(item,i) in this.mentoringList" :key="i"/>
                    </v-row>
                </v-card>
            </v-row>
        </v-layout>
    </v-container>
</template>

<script>
    import {mapActions,mapState} from 'vuex'

    export default {
        data() {
            return {
                dialog: false,
                items: [
                    {title: '웹 프로젝트', author: 'john', content: '프론트엔드 1명, 백엔드 1명 구해요.'}
                ]
            }
        },
        methods: {
            ...mapActions([
                'FETCH_MENTORING_LIST'
            ])
        },
        components: {
            card: () => import('@/components/Card')
        },
        computed:{
          ...mapState([
              'mentoringList'
          ])
        },
        mounted() {
            this.FETCH_MENTORING_LIST()
        }
    }
</script>

<style scoped>

</style>