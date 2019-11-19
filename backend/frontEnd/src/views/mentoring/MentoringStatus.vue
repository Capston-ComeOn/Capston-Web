<template>
    <v-container style="max-width: 850px" class="mb-5 mt-5">

        <v-card v-if="mentoring" class="pa-5" :elevation="20">

            <v-label>신청자 <b>{{mentoring.mentees.length}}</b> 명</v-label>
            <v-flex v-for="(mentee,index) in mentoring.mentees" :key="index" xs12>
                <v-list>
                    <v-list-item>
                        <v-list-item-avatar v-if="mentee && mentee.imgSrc" color="grey"
                                            size="64">
                            <v-img @click="dialog=true" style="cursor:pointer"
                                   :src="mentee.imgSrc"/>
                        </v-list-item-avatar>
                        <v-icon v-else size="64" class="mr-2">mdi-account-circle</v-icon>
                        <v-list-item-content>
                            <v-list-item-title v-text="`이름: ${mentee.name}`"/>
                            <v-list-item-title v-text="`이메일: ${mentee.email}`"/>
                            <v-list-item-title v-text="`학번: ${mentee.studentId}`"/>
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
            ...mapState('mentoring', [
                'mentoring'
            ])
        },
        methods: {
            back() {
                this.$router.go(-1)
            },
            ...mapActions('mentoring', [
                'FETCH_MENTORING',
                'ADD_MENTEE'
            ])
        },
        mounted() {
            this.FETCH_MENTORING({id: this.$route.params.id})
        }
    }
</script>

<style scoped>

</style>