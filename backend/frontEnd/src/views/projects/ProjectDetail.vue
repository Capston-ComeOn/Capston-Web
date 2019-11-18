<template>
    <v-container class="mb-5 mt-5" style="max-width: 850px">
        <v-layout>
            <carousel/>
        </v-layout>
        <v-card :elevation="20" class="pa-5">
            <v-layout>
                <v-tabs centered>
                    <v-tab>개설자 소개</v-tab>
                    <v-tab>모임 소개</v-tab>
                    <v-tab>대상 소개</v-tab>
                    <v-tab>기타</v-tab>
                </v-tabs>
            </v-layout>
            <v-layout>
                <v-flex>
                    <v-avatar class="mb-3 mt-3" size="200px" v-if="account && account.imgSrc!==null">
                        <img :src="account.imgSrc"
                             v-if="account.imgSrc!==null"
                        >
                    </v-avatar>
                    <v-icon size="200px" v-else>
                        mdi-account-circle
                    </v-icon>

                </v-flex>
                <v-flex class="mt-5" v-if="project">
                    <h1>{{project.title}}</h1>
                    <p class="text-right mt-5">{{project.account.studentId}} {{project.account.name}}</p>
                    <p class="text-right mt-5">모집 기간:{{project.startTime}} ~ {{project.endTime}}</p>
                    <h3 class="text-right mt-5">모집 인원 : 10명 </h3>

                </v-flex>
            </v-layout>

            <v-divider></v-divider>

            <v-layout row v-if="mento">
                <v-flex xs12>
                    <h1 class="ma-5">개설자 소개</h1>
                </v-flex>
                <v-flex class="ml-5" xs12>
                    <viewer v-model="mento"/>
                </v-flex>
            </v-layout>


            <v-divider></v-divider>

            <v-layout row v-if="metting">
                <v-flex class="ma-5" xs>
                    <h1 class="mb-5">모임 소개</h1>
                    <p>소개</p>
                    <viewer v-model="metting"/>
                </v-flex>
            </v-layout>


            <v-divider></v-divider>

            <v-layout row v-if="target">
                <v-flex xs12>
                    <h1 class="ma-5">대상 소개</h1>
                </v-flex>
                <v-flex class="ml-5" xs12>
                    <p>소개</p>
                    <viewer v-model="target"/>
                </v-flex>
            </v-layout>


            <v-divider></v-divider>

            <v-layout class="mt-3" row v-if="etc">
                <v-flex class="ml-5" xs12>
                    <h1 class="mb-5">기타</h1>
                    <viewer v-model="etc"/>
                </v-flex>
            </v-layout>

            <v-layout style="height: 100px"></v-layout>


            <v-layout class="justify-center mt-5 mb-5" v-if="account && project ">
                <v-btn @click="onSubmit" color="error" dark large v-if="account.id!==project.account.id">신청 하기</v-btn>
                <v-btn :to="`/project/status/${this.$route.params.id}`" class="ml-2"
                       color="orange" dark large v-if="account.id===project.account.id">신청 현황
                </v-btn>
                <v-btn class="ml-2" color="green" dark large v-if="account.id===project.account.id">멘토링 종료</v-btn>
            </v-layout>

            <v-layout style="height: 100px"></v-layout>
        </v-card>
    </v-container>
</template>

<script>
    import {Viewer} from '@toast-ui/vue-editor'
    import {mapActions, mapState} from 'vuex'

    export default {
        data() {
            return {
                mento: '',
                metting: '',
                target: '',
                etc: '',
            }
        },
        components: {
            'viewer': Viewer,
            carousel: () => import('@/components/Carousel')
        },
        computed: {
            ...mapState([
                'project',
                'account'
            ])
        },
        methods: {
            onSubmit() {
                this.ADD_APPLY({id: this.$route.params.id})
                    .then((data) => {
                        return data
                    })
            },
            ...mapActions([
                'FETCH_PROJECT',
                'ADD_APPLY'
            ])
        },
        mounted() {
            this.FETCH_PROJECT({id: this.$route.params.id})
                .then(() => {
                    this.mento = this.project.introduce.mento
                    this.metting = this.project.introduce.metting
                    this.target = this.project.introduce.target
                    this.etc = this.project.introduce.etc
                })

        }
    }
</script>

<style scoped>

</style>