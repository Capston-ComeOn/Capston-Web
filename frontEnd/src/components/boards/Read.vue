<template>
    <v-container v-if="article" mt-3>
        <v-layout row wrap>
            <v-flex xs12>
                <v-label>작성자</v-label>
                <v-row/>
                <v-list>
                    <v-list-item>
                        <v-list-item-avatar color="indigo" size="64">
                            <v-img @click="dialog=true" style="cursor:pointer"
                                   src="https://cdn.vuetifyjs.com/images/profiles/marcus.jpg"/>
                        </v-list-item-avatar>
                        <v-list-item-content>
                            <v-list-item-title v-text="article.author.name"/>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-flex>
            <v-flex xs12 class="mt-3">
                <v-label>제목</v-label>
                <v-textarea
                        solo
                        label="제목"
                        readonly
                        rows="1"
                        :value="`${article.title}`"
                />
            </v-flex>
            <v-flex xs12 class="mt-3">
                <v-label>내용</v-label>
                <v-textarea
                        solo
                        label="내용"
                        readonly
                        rows="10"
                        :value="`${article.contents}`"
                />
            </v-flex>
            <v-flex class="text-right text-xs-right">
                <v-btn large dark color="red lighten-1 mr-1" to="/board">목록</v-btn>
                <v-btn large dark color="red lighten-1 mr-1">수정</v-btn>
                <v-btn large dark color="red lighten-1">삭제</v-btn>
            </v-flex>
        </v-layout>
        <ProfileCard v-if="dialog" v-on:@change="onFalseDialog"/>
    </v-container>
</template>

<script>
    import ProfileCard from "../ProfileCard";
    import {mapState, mapActions} from "vuex"

    export default {
        data() {
            return {
                dialog: false
            }
        },
        computed: {
            ...mapState([
                'article'
            ])
        },
        components: {
            ProfileCard
        },
        methods: {
            ...mapActions([
                'FETCH_ARTICLE'
            ]),
            onFalseDialog() {
                this.dialog = false
            }
        },
        created() {
            this.FETCH_ARTICLE({id: this.$route.params.id})
        }
    }
</script>

<style scoped>

</style>
