<template>
    <v-container v-if="article" mt-3 class="mx-auto" style="max-width: 1000px">
        <v-layout row wrap class="mb-5">
            <v-flex xs12>
                <v-label>작성자</v-label>
                <v-row/>
                <v-list>
                    <v-list-item>
<!--                        <v-list-item-avatar v-if="article.author.imgSrc" color="grey" size="64">-->
<!--                            <v-img @click="dialog=true" style="cursor:pointer"-->
<!--                                   :src="`http://localhost:8090/api/accounts/download?fileName=${article.author.imgSrc}`"/>-->
<!--                        </v-list-item-avatar>-->
                        <v-icon size="64" class="mr-2">mdi-account-circle</v-icon>
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
            <v-flex xs12>
                <v-label>내용</v-label>
                <v-card min-height="300px" class="mb-2">
                    <viewer v-model="contents" class="pa-5"/>
                </v-card>
            </v-flex>
            <v-flex class="text-right text-xs-right">
                <v-btn large dark color="red lighten-1 mr-1" to="/board">목록</v-btn>
                <v-btn large dark color="red lighten-1 mr-1" :to="`/board/write/${this.$route.params.id}`">수정</v-btn>
                <v-btn @click="onDelete" large dark color="red lighten-1">삭제</v-btn>
            </v-flex>
        </v-layout>
        <div class="comments pt-5">
            <vue-disqus shortname="comeon-comunity"></vue-disqus>
        </div>
        <ProfileCard v-if="dialog" :author="article.author" v-on:@change="onFalseDialog"/>
    </v-container>
</template>

<script>
    import ProfileCard from "../../components/ProfileCard";
    import {Viewer} from '@toast-ui/vue-editor'
    import {mapState, mapActions} from "vuex"

    export default {

        data() {
            return {
                dialog: false,
                contents: ''
            }
        },
        computed: {
            ...mapState([
                'article'
            ])
        },
        components: {
            ProfileCard,
            'viewer': Viewer
        },
        methods: {
            ...mapActions([
                'FETCH_ARTICLE', 'DESTROY_ARTICLE'
            ]),
            onFalseDialog() {
                this.dialog = false
            },
            onDelete() {
                this.DESTROY_ARTICLE({id: this.$route.params.id}).then(data => {
                    if (data.status === 200) {
                        alert("삭제 되었습니다.")
                        this.$router.push("/board")
                    }
                })
            }
        },
        created() {
            const {id, categoryId} = this.$route.params
            this.FETCH_ARTICLE({id, categoryId}).then(data => {
                this.contents = data.contents
            })
        }
    }
</script>

<style scoped>

</style>
