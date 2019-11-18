<template>
    <v-container class="mb-5 mt-5" style="max-width: 850px">

        <v-card :elevation="20" class="pa-5" v-if="project">

            <v-label>신청자 <b>{{project.applicant.length}}</b> 명</v-label>
            <v-flex :key="index" v-for="(apply,index) in project.applicant" xs12>
                <v-list>
                    <v-list-item>
                        <v-list-item-avatar color="grey" size="64"
                                            v-if="apply && apply.imgSrc">
                            <v-img :src="apply.imgSrc" @click="dialog=true"
                                   style="cursor:pointer"/>
                        </v-list-item-avatar>
                        <v-icon class="mr-2" size="64" v-else>mdi-account-circle</v-icon>
                        <v-list-item-content>
                            <v-list-item-title v-text="`이름: ${apply.name}`"/>
                            <v-list-item-title v-text="`이메일: ${apply.email}`"/>
                            <v-list-item-title v-text="`학번: ${apply.studentId}`"/>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
                <v-divider></v-divider>
            </v-flex>
            <v-flex class="text-right mt-3">
                <v-btn @click="back" color="green" dark large> 이전</v-btn>
            </v-flex>
        </v-card>
    </v-container>
</template>

<script>
    import {mapActions, mapState} from 'vuex'

    export default {

        computed: {
            ...mapState([
                'project'
            ])
        },
        methods: {
            back() {
                this.$router.go(-1)
            },
            ...mapActions([
                'FETCH_PROJECT',
            ])
        },
        mounted() {
            this.FETCH_PROJECT({id: this.$route.params.id})
        }
    }
</script>

<style scoped>

</style>