<template>
    <v-card>
        <v-tabs
                show-arrows
                centered
        >
            <v-tab
                    v-for="(category,i) in this.categoryList"
                    :key="i"
                    :href="'#tab-' + category.id"
                    @click="onSetCategoryId(category)"
            >
                <p>{{ category.name }}</p>
            </v-tab>
        </v-tabs>
    </v-card>
</template>

<script>
    import {mapActions, mapMutations, mapState} from 'vuex'

    export default {
        computed: {
            ...mapState('category', [
                'categoryList'
            ])
        },
        methods: {
            ...mapMutations('category', [
                'SET_CATEGORY_ID'
            ]),
            ...mapActions('article', [
                'FETCH_ARTICLE_LIST'
            ]),
            ...mapActions('category', [
                'FETCH_CATEGORY_LIST'
            ]),
            onSetCategoryId({id}) {
                this.SET_CATEGORY_ID(id)
                this.FETCH_ARTICLE_LIST({categoryId: id, size: 10, page: 0})
            }
        },
        mounted() {
            this.FETCH_CATEGORY_LIST()
        }
    }
</script>

<style scoped>

</style>