<template>
    <v-flex class="pl-5 pr-5">
        <tab></tab>
        <material-card
                color="green lighten-1"
                title="게시판"
        >
          <v-card-title>
            Nutrition
            <div class="flex-grow-1"></div>
            <v-text-field
                    v-model="search"
                    append-icon="search"
                    label="Search"
                    single-line
                    hide-details
            ></v-text-field>
          </v-card-title>
            <v-data-table
                    :headers="headers"
                    :items="this.articleList"
                    :server-items-length.sync="pagination.totalSize"
                    @pagination="onPagination"
            >
                <template v-slot:body="{ items }">
                    <tbody>
                    <tr v-for="(item,index) in items" :key="index">
                        <td>{{item.id}}</td>
                        <td>{{item.title}}</td>
                        <td>{{item.author.name}}</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <v-btn @click="onDelete(item)" icon>
                                <v-icon>mdi-trash-can-outline</v-icon>
                            </v-btn>
                        </td>
                    </tr>
                    </tbody>

                </template>
            </v-data-table>
        </material-card>
    </v-flex>
</template>

<script>
    import {mapState, mapMutations, mapActions} from 'vuex'

    export default {

        data() {
            return {
                headers: [
                    {text: '번호', value: 'id'},
                    {text: '제목', value: 'title'},
                    {text: '작성자', value: 'author.name'},
                    {text: '조회수', value: 'viewer'},
                    {text: '날짜', value: 'time'},
                    {text: '추천수', value: 'recommend'},
                    {text: '수정', value: 'action'}
                ],
                data:[
                    {text:'오늘 백반메뉴 아는사람!'}
                ],
                pagination: {
                    totalSize: 0
                }
            }
        },
        components: {
            tab: () => import('@/components/material/Tab')
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
                'DESTROY_ARTICLE'
            ]),
            onDelete(item) {
                const {id} = item
                this.DESTROY_ARTICLE({id}).then(({status}) => {
                    if(status>=100 || status<300){
                      alert('삭제 되었습니다.')
                      window.location.replace("table-list")
                    }
                })
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
            this.FETCH_ARTICLE_SIZE({categoryId: this.categoryId})
                .then(data => {
                    this.pagination.totalSize = data
                    this.onPagination()
                })

        }
    }
</script>
