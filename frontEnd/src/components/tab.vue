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
            >
                <p>{{ category.name }}</p>
            </v-tab>
        </v-tabs>
    </v-card>
</template>

<script>
    import {mapState, mapActions} from 'vuex'

    export default {
        computed: {
            ...mapState([
                'categoryList'
            ])
        },
        methods: {
            ...mapActions([
                'FETCH_CATEGORY_LIST'
            ])
        },
        mounted() {
            this.FETCH_CATEGORY_LIST()
        }
    }
</script>

<style scoped>

</style>