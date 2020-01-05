<template>
    <v-container>
        <h1 class="text-center">최신글 보기</h1>
        <v-layout row v-if="this.categoryList.length>=1 && this.categoryList.length===table.length">
            <v-flex :key="index" col-6 v-for="(category,index) in this.categoryList">
                <home-data-table :color="`${colors[index]} lighten-4`" :table="table[index].data"
                                 :title="`${category.name}`"
                                 class="ma-3"/>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import {mapActions, mapState} from 'vuex'

    export default {
        data() {
            return {
                colors: ['brown', 'pink', 'indigo', 'cyan', 'lime'],
                table: []
            }
        },
        computed: {
            ...mapState('category', [
                'categoryList'
            ])
        },
        components: {
            'home-data-table': () => import('@/components/HomeDataTable')
        },
        methods: {
            ...mapActions('category', [
                'FETCH_CATEGORY_LIST',
            ]),
            ...mapActions('article', [
                'FETCH_ARTICLE_LIST_RETURN'
            ]),
        },
        mounted() {
            this.FETCH_CATEGORY_LIST().then(data => {
                for (const {id} of data) {
                    this.FETCH_ARTICLE_LIST_RETURN({categoryId: id, size: 5, page: 0}).then(data => {
                        this.table.push({id, data});
                        this.table.sort((a, b) => a.id - b.id);
                    })
                }
            })

        }
    }
</script>

<style scoped>

</style>
