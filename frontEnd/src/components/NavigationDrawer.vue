<template>
    <v-navigation-drawer
            v-model="drawer"
            fixed
            clipped
            permanent
            app
            :mini-variant="this.mini"
    >

        <v-list v-if="isAuthenticated" dense>
            <v-list-item
                    @click="onCancelMini(false)"
                    v-for="item in loginItems"
                    :key="item.title"
                    :to="item.link"
            >
                <v-list-item-icon>
                    <v-icon>{{ item.icon }}</v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                    <v-list-item-title>{{ item.title }}</v-list-item-title>
                </v-list-item-content>
            </v-list-item>
        </v-list>
        <v-list v-else dense>
            <v-list-item
                    @click="onCancelMini(false)"
                    v-for="item in logoutItems"
                    :key="item.title"
                    :to="item.link"
            >
                <v-list-item-icon>
                    <v-icon>{{ item.icon }}</v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                    <v-list-item-title>{{ item.title }}</v-list-item-title>
                </v-list-item-content>
            </v-list-item>
        </v-list>
    </v-navigation-drawer>
</template>

<script>
    import {mapState, mapGetters, mapMutations, mapActions} from 'vuex'

    export default {
        data() {
            return {
                drawer: true,
                loginItems: [
                    {title: 'Home', icon: 'mdi-home-city', link: "/"},
                    {title: 'Profile', icon: 'mdi-account-card-details', link: "/profile"},
                    {title: 'Board', icon: 'mdi-book-open-variant', link: "/Board"},
                    {title: 'Logout', icon: 'mdi-lock-open-variant-outline', link: "/logout"}
                ],
                logoutItems: [
                    {title: 'Join', icon: 'mdi-account-multiple-plus', link: "/join"},
                    {title: 'Login', icon: 'mdi-lock-outline', link: "/login"},
                ],
                // mini: true
            }
        },
        methods: {
            onCancelMini(data) {
                this.SET_MINI(data)
                // this.mini = !this.mini
            },
            ...mapActions([
                'FETCH_ARTICLE_LIST'
            ]),
            ...mapMutations([
                'SET_MINI',
                'SET_CATEGORY_ID'
            ])
        },
        computed: {
            ...mapGetters([
                'isAuthenticated'
            ]),
            ...mapState([
                'mini',
                'categoryList',
                'page'
            ])
        },
        mounted() {
        }
    }
</script>

<style scoped>

</style>