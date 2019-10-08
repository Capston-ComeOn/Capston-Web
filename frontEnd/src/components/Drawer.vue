<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-card
            height="590"
            width="256"
    >
        <v-navigation-drawer
                class="blue lighten-3"
                dark
                permanent
        >
            <v-list>
                <v-list-group v-for="(category,index) in categoryList"
                              no-action
                              sub-group
                              :key="index"
                              @click="onCategory(category)"
                >
                    <template v-slot:activator>
                        <v-list-item-content>
                            <v-list-item-title>{{category.name}}</v-list-item-title>
                        </v-list-item-content>
                    </template>
                    <!--                    <v-list-item-->
                    <!--                            v-for="(subcategory, i) in mainCategory.subCategoryList"-->
                    <!--                            :key="i"-->
                    <!--                            link-->
                    <!--                            @click="onLink(mainCategory.id,subcategory.id)"-->
                    <!--                    >-->
                    <!--                        <v-list-item-title v-text="subcategory.name"></v-list-item-title>-->
                    <!--                        &lt;!&ndash;                        <v-list-item-icon>&ndash;&gt;-->
                    <!--                        &lt;!&ndash;                            <v-icon v-text="admin[1]"></v-icon>&ndash;&gt;-->
                    <!--                        &lt;!&ndash;                        </v-list-item-icon>&ndash;&gt;-->
                    <!--                    </v-list-item>-->
                </v-list-group>

            </v-list>


        </v-navigation-drawer>
    </v-card>
</template>
<script>
    import {mapState, mapMutations, mapActions} from 'vuex'

    export default {

        methods: {
            ...mapActions([
                'FETCH_CATEGORY_LIST',
                'FETCH_ARTICLE_LIST'
            ]),
            ...mapMutations([
                'SET_CATEGORY_ID'
            ]),
            onCategory(category) {
                this.SET_CATEGORY_ID(category.id)
                this.FETCH_ARTICLE_LIST({categoryId: category.id, size: 10, page: this.page})
            }
        },
        computed: {
            ...mapState([
                'categoryList',
                'page'
            ])
        },
        data() {
            return {
                items: [
                    {title: 'Dashboard', icon: 'mdi-view-dashboard'},
                    {title: 'Account', icon: 'mdi-image'},
                    {title: 'Admin', icon: 'mdi-help-box'},
                ],
                admins: [
                    ['Management', 'people_outline'],
                    ['Settings', 'settings'],
                ]
            }
        },
        mounted() {
            this.FETCH_CATEGORY_LIST();
        }
    }
</script>
