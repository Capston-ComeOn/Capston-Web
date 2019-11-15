<template>
    <v-flex>
        <v-app-bar clipped-left app color="white" class="elevation-2">
            <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
            <v-toolbar-title>컴정 온라인 게시판, 컴온!</v-toolbar-title>
            <div class="flex-grow-1"></div>
            <v-toolbar-items class="hidden-xs-and-down">

                <v-menu class="ml-2" offset-y>
                    <template v-slot:activator="{ on }">
<!--                        <v-avatar  v-on="on" class="mt-3" size="45"-->
<!--                                  style=" border: 2px solid;">-->
<!--                            -->
<!--                        </v-avatar>-->
                        <v-avatar v-on="on" >
                            <img v-if="account.length>=1 && account.imgSrc!==null" :src="account.imgSrc"/>
                            <v-icon v-else class="mt-2 pl-5" size="45">mdi-account-circle</v-icon>
                        </v-avatar>
                    </template>

                    <v-list>
                        <v-list-item
                                v-for="(item, index) in items"
                                :key="index"
                        >
                            <v-list-item-title>{{ item.title }}</v-list-item-title>
                        </v-list-item>
                    </v-list>
                </v-menu>


            </v-toolbar-items>

        </v-app-bar>
        <v-navigation-drawer
                v-model="drawer"
                absolute
                temporary
                expand-on-hover
        >

            <v-list v-if="isAuthenticated" nav dense>
                <v-list-item
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
    </v-flex>
</template>

<script>
    import {mapState, mapGetters, mapMutations, mapActions} from 'vuex'

    export default {
        data() {
            return {
                drawer: false,
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
                items: [
                    {title: '내 정보'},
                    {title: '쪽지함'},
                    {title: 'Click Me'},
                    {title: 'Click Me 2'},
                ]
            }
        },
        methods: {
            ...mapActions([
                'FETCH_ARTICLE_LIST',
                'FETCH_LOGIN_ACCOUNT'
            ]),
            ...mapMutations([
                'SET_CATEGORY_ID'
            ])
        },

        computed: {
            ...mapGetters([
                'isAuthenticated'
            ]),
            ...mapState([
                'account'
            ])
        },
        mounted() {
            if (this.isAuthenticated) {
                this.FETCH_LOGIN_ACCOUNT()
            }
        }
    }
</script>

<style scoped>

</style>