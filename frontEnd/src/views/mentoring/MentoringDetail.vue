<template>
    <v-container style="max-width: 850px" class="mb-5 mt-5">
        <v-layout>
            <carousel/>
        </v-layout>
        <v-card class="pa-5" :elevation="20">
            <v-layout>
                <v-tabs centered>
                    <v-tab>멘토 소개</v-tab>
                    <v-tab>모임 소개</v-tab>
                    <v-tab>대상 소개</v-tab>
                    <v-tab>기타</v-tab>
                </v-tabs>
            </v-layout>
            <v-layout>
                <v-flex>
                    <v-avatar color="orange" size="200px" class="mb-3 mt-3">
                        <img
                                aspect-ratio="1"
                                src="https://kimnoin.github.io/images/bio-photo.jpg"
                        >
                    </v-avatar>
                </v-flex>
                <v-flex v-if="mentoring" class="mt-5">
                    <h1>{{mentoring.title}}</h1>
                    <p class="text-right mt-5">{{mentoring.mento.studentId}} {{mentoring.mento.name}}</p>
                    <p class="text-right mt-5">모집 기간:{{mentoring.startTime}} ~ {{mentoring.endTime}}</p>
                    <h3 class="text-right mt-5">수강 인원 : 10명 </h3>

                </v-flex>
            </v-layout>

            <v-divider></v-divider>

            <v-layout v-if="mento" row>
                <v-flex xs12>
                    <h1 class="ma-5">멘토 소개</h1>
                </v-flex>
                <v-flex xs12 class="ml-5">
                    <viewer v-model="mento"/>
                </v-flex>
            </v-layout>


            <v-divider></v-divider>

            <v-layout v-if="metting" row>
                <v-flex xs class="ma-5">
                    <h1 class="mb-5">모임 소개</h1>
                    <p>소개</p>
                    <viewer v-model="metting"/>
                </v-flex>
            </v-layout>


            <v-divider></v-divider>

            <v-layout v-if="target" row>
                <v-flex xs12>
                    <h1 class="ma-5">대상 소개</h1>
                </v-flex>
                <v-flex xs12 class="ml-5">
                    <p>소개</p>
                    <viewer v-model="target"/>
                </v-flex>
            </v-layout>


            <v-divider></v-divider>

            <v-layout v-if="etc" class="mt-3" row>
                <v-flex xs12 class="ml-5">
                    <h1 class="mb-5">기타</h1>
                    <viewer v-model="etc"/>
                </v-flex>
            </v-layout>

            <v-layout style="height: 100px"></v-layout>


            <v-layout class="justify-center mt-5 mb-5">
                <v-btn color="error" dark large>신청하기</v-btn>
            </v-layout>

            <v-layout style="height: 100px"></v-layout>
        </v-card>
    </v-container>
</template>

<script>
    import {Viewer} from '@toast-ui/vue-editor'
    import {mapState, mapActions} from 'vuex'

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
                'mentoring'
            ])
        },
        methods: {
            ...mapActions([
                'FETCH_MENTORING'
            ])
        },
        mounted() {
            this.FETCH_MENTORING({id: this.$route.params.id})
                .then(() => {
                    this.mento = this.mentoring.introduce.mento
                    this.metting = this.mentoring.introduce.metting
                    this.target = this.mentoring.introduce.target
                    this.etc = this.mentoring.introduce.etc
                })

        }
    }
</script>

<style scoped>

</style>