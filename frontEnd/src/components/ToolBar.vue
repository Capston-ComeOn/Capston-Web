<template>
    <v-app-bar clipped-left app color="white" class="elevation-2">
        <v-app-bar-nav-icon @click="onCancelMini()"></v-app-bar-nav-icon>
        <v-toolbar-title>컴온!</v-toolbar-title>
        <div class="flex-grow-1"></div>
        <v-toolbar-items class="hidden-xs-and-down">
<!--            <v-btn v-if="!isAuthenticated" text to="/login">로그인</v-btn>-->
<!--            <v-btn v-if="!isAuthenticated" text to="/join">회원가입</v-btn>-->
<!--            <v-btn v-if="isAuthenticated" text to="/board">게시판</v-btn>-->
<!--            <v-btn v-if="isAuthenticated" text @click="onLogout">로그아웃</v-btn>-->
            <v-avatar class="mt-3" color="orange" size="35">
                <span class="white--text headline">D</span>
            </v-avatar>

        </v-toolbar-items>
    </v-app-bar>
</template>

<script>
    import {mapState,mapGetters, mapMutations} from 'vuex'

    export default {
        data() {
            return {}
        },
        computed: {
            ...mapGetters([
                'isAuthenticated'
            ]),
            ...mapState([
              'mini'
            ])
        },
        methods: {
            ...mapMutations([
                'LOGOUT'
            ]),
            onLogout() {
                this.LOGOUT().then(() => this.router.push("/"));
            },
            onCancelMini() {
                const bool=!this.mini
                this.SET_MINI(bool)
            },
            ...mapMutations([
                'SET_MINI'
            ])
        }
    }
</script>

<style scoped>
</style>
