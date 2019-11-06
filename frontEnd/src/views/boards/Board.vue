<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-container mt-3>
        <tab class="mb-3"></tab>
        <v-layout>
            <v-flex>
                <v-card class="pa-3 mt-5 mb-5">
                    <v-toolbar color="blue" elevation="10">
                        <v-toolbar-title v-if="this.categoryList[this.categoryId-1]" class="text-center pt-2">
                            {{categoryList[categoryId-1].name}}
                        </v-toolbar-title>
                    </v-toolbar>
                    <v-data-table
                            :headers="headers"
                            :items="this.data"
                            :server-items-length.sync="pagination.totalSize"
                            @pagination="onPagination"
                            class="elevation-1 pt-4"
                            v-on:item-selected="onClick"
                    >
<!--                        <template v-slot:item.title="props">-->
<!--                            <router-link style="text-decoration: none" :to="`/board/${props.item.category.id}/${props.item.id}`">-->
<!--                                {{props.item.title}}-->
<!--                            </router-link>-->
<!--                        </template>-->

                    </v-data-table>
                </v-card>

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
            tab
        },
        data() {
            return {
                headers: [
                    {text: '번호', value: 'id'},
                    {text: '제목', value: 'title'},
                    // {text: '작성자', value: 'author.name'},
                    {text: '작성자', value: 'name'},
                    {text: '조회수', value: 'viewer'},
                    {text: '날짜', value: 'time'},
                    {text: '추천수', value: 'recommend'},
                ],
                data:[
                    {id:'10',title:'오늘 백반 메뉴 아는분!',name:'김동규',viewer:13,time:'2019-10-23',recommend:'2'},
                    {id:'9',title:'날씨가 왜 이러죠..',name:'이상훈',viewer:27,time:'2019-10-23',recommend:'5'},
                    {id:'8',title:'참치를 먹고 싶은 걸 참치마요',name:'곽병선',viewer:58,time:'2019-10-23',recommend:'-8'},
                    {id:'7',title:'카페 추천좀 해주세요!',name:'곽병선',viewer:32,time:'2019-10-23',recommend:'0'},
                    {id:'6',title:'슬슬 시험기간 시즌 ㅠㅠ',name:'안태회',viewer:19,time:'2019-10-23',recommend:'5'},
                    {id:'5',title:'학교 끝나고 집갈 때 공감 ',name:'이상훈',viewer:63,time:'2019-10-23',recommend:'5'},
                    {id:'4',title:'후문 알바 추천해주세요!',name:'곽병선',viewer:27,time:'2019-10-23',recommend:'1'},
                    {id:'3',title:'오늘 학교 풍경사진.jpg',name:'김동규',viewer:37,time:'2019-10-23',recommend:'9'},
                    {id:'2',title:'도서관 휴학생 이용가능 한가요?',name:'안태회',viewer:29,time:'2019-10-23',recommend:'0'},
                    {id:'1',title:'영화 요즘 뭐 재밌어요?',name:'이상훈',viewer:22,time:'2019-10-23',recommend:'0'},
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
                console.log('zzz')
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
