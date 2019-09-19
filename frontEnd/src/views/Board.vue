<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-container mt-3>
        <h3 class="title"> 게시판 </h3>
        <v-divider light class="mt-3 mb-3"></v-divider>
        <v-layout row wrap>
            <v-flex col-sm-2>
                <Drawer></Drawer>
            </v-flex>
            <v-flex col-sm-10>
                <v-data-table
                        :headers="headers"
                        :items="this.articleList"
                        class="elevation-1"
                >
                    <template v-slot:body="{ items }">
                        <tbody>
                        <tr v-for="item in items" :key="item.id">
                            <td>{{item.id}}</td>
                            <td>
                                <router-link style="text-decoration: none;" :to="`/board/${item.id}`">
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
                <div class="text-right mt-2">
                    <v-btn large dark color="green lighten-2" to="/board/write">작성하기</v-btn>
                </div>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import Drawer from '../components/Drawer'
    import {mapState, mapActions} from 'vuex'

    export default {
        components: {
            Drawer
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
                limit: 10,
                pageNumber: 0
            }
        },
        computed: {
            ...mapState([
                'articleList'
            ])
        },
        methods: {
            ...mapActions([
                'FETCH_ARTICLE_LIST'
            ])
        },
        created() {
            this.FETCH_ARTICLE_LIST({size: this.limit, page: this.pageNumber})
        }
    }
</script>
<style scoped>

</style>
