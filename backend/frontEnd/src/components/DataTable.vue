<template>
    <v-card class="pa-3 mt-5 mb-5">
        <v-toolbar color="blue" elevation="10">
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
                v-on:item-selected="onClick"
        >
            <template v-slot:item.title="props">
                <router-link style="text-decoration: none"
                             :to="`/board/${props.item.category.id}/${props.item.id}`">
                    {{props.item.title}}
                </router-link>
            </template>

        </v-data-table>
    </v-card>
</template>

<script>
    import {mapState, mapMutations, mapActions} from 'vuex'

    export default {
        name: "DataTable",
        data() {
            return {
                headers: [
                    {text: '번호', value: 'id'},
                    {text: '제목', value: 'title'},
                    {text: '작성자', value: 'author.name'},
                    {text: '조회수', value: 'viewer'},
                    {text: '날짜', value: 'created'},
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
            ]),
            onClick() {
                // this.$router.push(`/board/${item.category.id}/${item.id}`)
            },
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