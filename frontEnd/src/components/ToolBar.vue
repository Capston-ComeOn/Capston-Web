<template>
    <div>
        <v-toolbar color="blue lighten-3">
            <v-toolbar-title>컴온!</v-toolbar-title>
            <div class="flex-grow-1"></div>
            <v-toolbar-items class="hidden-xs-and-down">
                <v-btn v-if="!isAuthenticated" text to="/login">로그인</v-btn>
                <v-btn v-if="!isAuthenticated" text to="/join">회원가입</v-btn>
                <v-btn v-if="isAuthenticated" text to="/board">게시판</v-btn>
                <v-btn v-if="isAuthenticated" text @click="onLogout">로그아웃</v-btn>
            </v-toolbar-items>
        </v-toolbar>
    </div>
</template>

<script>
    import {mapGetters, mapMutations} from 'vuex'

    export default {
        data() {
            return {}
        },
        computed: {
            ...mapGetters([
                'isAuthenticated'
            ]),

        },
        methods: {
            ...mapMutations([
                'LOGOUT'
            ]),
            onLogout() {
                this.LOGOUT().then(()=>this.router.push("/"));
            }
        }
    }
</script>

<style scoped>
</style>
