<template>
    <div>
        <v-toolbar color="blue lighten-3">
            <v-toolbar-title>컴정</v-toolbar-title>

            <div class="flex-grow-1"></div>

            <v-toolbar-items class="hidden-xs-and-down">
                <v-btn text to="/login">로그인</v-btn>
                <v-btn text to="/join">회원가입</v-btn>
                <v-btn text to="/board">게시판</v-btn>
            </v-toolbar-items>
        </v-toolbar>
    </div>
</template>

<script>

    export default {
        data() {
            return {}
        }
    }
</script>

<style scoped>
</style>
