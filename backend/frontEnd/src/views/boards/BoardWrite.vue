<template>
    <v-container grid-list-xs>
        <v-layout row wrap>

            <v-flex xs12 class="mt-3">
                <h3 class="title">글 작성</h3>
                <v-divider class="mt-3" light></v-divider>
                <v-flex>
                    <v-text-field
                            v-model="title"
                            required
                    ></v-text-field>
                </v-flex>
            </v-flex>

            <v-flex xs12>
                <v-row>
                    <v-col cols="4">
                        카테고리
                    </v-col>
                    <v-col cols="4">
                        <v-select
                                v-model="selectedCategory"
                                :items="categoryList"
                                item-text="name"
                                item-value="id"
                                label="1차 카테고리"
                                solo
                        ></v-select>
                    </v-col>
                    <!--                    <v-col cols="4">-->
                    <!--                        <v-select-->
                    <!--                                v-model="selectedSubCategory"-->
                    <!--                                :items="subCategory"-->
                    <!--                                label="2차 카테고리"-->
                    <!--                                solo-->
                    <!--                        ></v-select>-->
                    <!--                    </v-col>-->
                </v-row>
            </v-flex>

            <v-flex xs12 class="mt-5">
                <editor v-model="contents"/>
            </v-flex>
        </v-layout>
        <div class="text-right mt-3">
            <v-btn class="mr-2" large dark color="red lighten-2" @click="onSubmit">작성</v-btn>
            <v-btn large dark color="red lighten-2" to="/board">목록</v-btn>
        </div>
    </v-container>
</template>
<script>
    import 'tui-editor/dist/tui-editor.css'
    import 'tui-editor/dist/tui-editor-contents.css'
    import 'codemirror/lib/codemirror.css'
    import {Editor, Viewer} from '@toast-ui/vue-editor'
    import {mapActions, mapState} from 'vuex'

    export default {
        components: {
            'editor': Editor,
            // eslint-disable-next-line vue/no-unused-components
            'viewer': Viewer
        },
        data() {
            return {
                title: '',
                contents: '',
                selectedCategory: '',
            }
        },
        computed: {
            ...mapState('category', [
                'categoryList'
            ])
        },
        methods: {
            ...mapActions('article', [
                'ADD_ARTICLE',
            ]),
            ...mapActions('category', [
                'FETCH_CATEGORY_LIST'
            ]),
            onSubmit() {
                // categoryId null check
                const {title, contents} = this
                this.ADD_ARTICLE({
                    title,
                    contents,
                    categoryId: this.selectedCategory
                })
                    .then((data) => {
                        if (data.status >= 200 && data.status <= 300) {
                            alert('글이 작성되었습니다.')
                            this.$router.push("/board")
                        }
                    }).catch(() => {
                    alert('잘못된 작성입니다.')
                })
            }
        },
        mounted() {
            this.FETCH_CATEGORY_LIST()
        }
    }
</script>
