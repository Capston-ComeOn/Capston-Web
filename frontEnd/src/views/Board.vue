<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-container mt-3>
        <tab class="mb-3"></tab>
        <v-layout>
            <v-flex>
                <v-card>
                    <v-toolbar color="green" elevation="10">
                        <v-toolbar-title v-if="this.categoryList[this.categoryId-1]" class="text-center pt-2">
                            {{categoryList[categoryId-1].name}}
                        </v-toolbar-title>
                    </v-toolbar>
                    <v-data-table
                            :headers="headers"
                            :items="this.articleList"
                            :server-items-length.sync="pagination.totalSize"
                            @pagination="onPagination"
                            class="elevation-1 pt-4"
                    >
                        <template v-slot:body="{ items }">
                            <tbody>
                            <tr v-for="item in items" :key="item.id">
                                <td>{{item.id}}</td>
                                <td>
                                    <router-link style="text-decoration: none;"
                                                 :to="`/board/${item.category.id}/${item.id}`">
                                        {{ item.title }}
                                    </router-link>
                                </td>
                                <td>{{item.author.name}}</td>
                                <td>{{item.viewer}}</td>
                                <td>{{item.time}}</td>
                                <td>{{item.recommend}}</td>
                            </tr>
                            </tbody>
                        </template>
                    </v-data-table>
                </v-card>

                <div class="text-right mt-2">
                    <v-btn large dark color="green lighten-2" to="/board/write">작성하기</v-btn>
                </div>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import Drawer from '../components/Drawer'
    import tab from '../components/tab'
    import {mapState, mapMutations, mapActions} from 'vuex'

    export default {
        components: {
            Drawer,
            tab
        },
        data() {
            return {
                headers: [
                    {text: '번호', value: 'id'},
                    {text: '제목', value: 'title'},
                    {text: '작성자', value: 'author.name'},
                    {text: '조회수', value: 'viewer'},
                    {text: '날짜', value: 'time'},
                    {text: '추천수', value: 'recommend'},
                ],
                pagination: {
                    totalSize: 0
                }
            }
        },
        computed: {
            ...mapState([
                'articleList',
                'articleSize',
                'categoryId',
                'categoryList'
            ]),
        },
        methods: {
            ...mapMutations([
                'SET_PAGE'
            ]),
            ...mapActions([
                'FETCH_ARTICLE_LIST',
                'FETCH_ARTICLE_SIZE',
                'FETCH_CATEGORY_LIST'
            ]),
            onPagination(pagination) {
                if (pagination) {
                    const {page, itemsPerPage} = pagination
                    this.SET_PAGE(page - 1)
                    this.FETCH_ARTICLE_LIST({categoryId: this.categoryId, size: itemsPerPage, page: (page - 1)})
                }
            }
        },
        mounted() {
            // 자유게시판
            this.FETCH_ARTICLE_SIZE({categoryId: 2})
                .then(data => {
                    this.pagination.totalSize = data
                    this.onPagination()
                })

        }
    }
</script>
<style scoped>

</style>
