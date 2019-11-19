<template>
    <v-container class="mx-auto">
        <v-layout>
            <v-row class="mt-5">
                <v-card class="mx-auto pa-3">
                    <v-toolbar color="green" elevation="10">
                        <v-toolbar-title class="text-center pt-2">
                            프로젝트&공모전
                        </v-toolbar-title>
                    </v-toolbar>
                    <v-row class="justify-end ma-5">
                        <v-btn color="orange lighten-2" dark to="/project/write">작성하기</v-btn>
                    </v-row>
                    <h1 class="mb-5 text-center"> 현재 모집중인 프로젝트&공모전 </h1>
                    <v-row class="justify-center mb-5" v-if="this.projectList.length>=1">
                        <project-card :item="item" :key="i" class="ma-4" v-for="(item,i) in this.projectList"/>
                    </v-row>
                    <v-row class="justify-center" v-else>
                        진행중인 멘토링이 없습니다.
                    </v-row>
                </v-card>
            </v-row>
        </v-layout>
    </v-container>
</template>

<script>
    import {mapActions, mapState} from 'vuex'

    export default {
        data() {
            return {
                dialog: false
            }
        },
        methods: {
            ...mapActions('project', [
                'FETCH_PROJECT_LIST'
            ])
        },
        components: {
            'project-card': () => import('@/components/ProjectCard')
        },
        computed: {
            ...mapState('project', [
                'projectList'
            ])
        },
        mounted() {
            this.FETCH_PROJECT_LIST()
        }
    }
</script>

<style scoped>

</style>