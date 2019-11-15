<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-container mt-3>
        <tab class="mb-3"></tab>
        <v-layout>
            <v-flex>
                <data-table/>
                <div class="text-right mt-2">
                    <v-btn large dark color="blue lighten-2" to="/board/write">작성하기</v-btn>
                </div>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import tab from '../../components/Tab'
    import {mapState, mapMutations, mapActions} from 'vuex'

    export default {
        components: {
            tab,
            'data-table': () => import('@/components/DataTable')
        },
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
