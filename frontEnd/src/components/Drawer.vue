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
                    {title: '홈', icon: 'mdi-home-city', link: "/"},
                    {title: '학과 연혁', icon: 'mdi-timeline-text-outline', link: "/timeline"},
                    {title: '내 정보', icon: 'mdi-account-card-details', link: "/profile"},
                    {title: '게시판', icon: 'mdi-book-open-variant', link: "/Board"},
                    {title: '멘토링 ', icon: 'mdi-account-group', link: "/mentoring"},
                    {title: '로그아웃', icon: 'mdi-lock-open-variant-outline', link: "/logout"}
                ],
                logoutItems: [
                    {title: '회원가입', icon: 'mdi-account-multiple-plus', link: "/join"},
                    {title: '로그인', icon: 'mdi-lock-outline', link: "/login"},
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
        }
    }
</script>

<style scoped>

</style>