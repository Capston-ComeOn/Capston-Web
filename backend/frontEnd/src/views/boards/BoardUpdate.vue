<template>
    <v-container grid-list-xs>
        <v-layout row wrap>
            <v-flex>
                <h3 class="title">글 수정</h3>
                <v-divider class="mt-3" light></v-divider>
                <v-flex>
                    <v-text-field
                            v-model="title"
                            required
                    ></v-text-field>
                </v-flex>
            </v-flex>
            <v-flex xs12 class="mt-5">
                <editor v-model="contents"/>
            </v-flex>
        </v-layout>
        <div class="text-right mt-3">
            <v-btn class="mr-2" large dark color="red lighten-2" @click="onSubmit">수정</v-btn>
            <v-btn large dark color="red lighten-2" to="/board">목록</v-btn>
        </div>
    </v-container>
</template>
<script>
    import 'tui-editor/dist/tui-editor.css'
    import 'tui-editor/dist/tui-editor-contents.css'
    import 'codemirror/lib/codemirror.css'
    import {Editor, Viewer} from '@toast-ui/vue-editor'
    import {mapActions} from 'vuex'

    export default {
        components: {
            'editor': Editor,
            // eslint-disable-next-line vue/no-unused-components
            'viewer': Viewer
        },
        data() {
            return {
                title: '',
                contents: ''
            }
        },
        methods: {
            ...mapActions('article', [
                'UPDATE_ARTICLE',
                'FETCH_ARTICLE'
            ]),
            onSubmit() {
                const {title, contents} = this
                const {id} = this.$route.params
                this.UPDATE_ARTICLE({id, title, contents})
                    .then((data) => {
                        if (data.status >= 200 && data.status <= 300) {
                            alert('글이 수정되었습니다.')
                            this.$router.push("/board")
                        }
                    }).catch(() => {
                    alert('잘못된 작성입니다.')
                })
            }
        },
        mounted() {
            this.FETCH_ARTICLE({id: this.$route.params.id}).then(data => {
                this.title = data.title;
                this.contents = data.contents
            })
        }
    }
</script>
