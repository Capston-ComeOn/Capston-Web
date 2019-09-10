<template>
    <v-container mt-5 fluid style="max-width: 500px">
        <v-layout row wrap>
            <v-flex xs12>
                <h3 class="title mb-3"> 회원가입 </h3>
                <v-divider class="mb-5" light></v-divider>
                <form>
                    <v-text-field
                            label="E-mail"
                            placeholder="이메일을 입력하세요."
                            required
                            type="email"
                    ></v-text-field>
                    <v-text-field
                            class="input-group--focused"
                            placeholder="패스워드를 입력하세요."
                            label="Password"
                            type="password"
                            required
                    ></v-text-field>
                    <v-text-field
                            label="Name"
                            placeholder="이름을 입력하세요."
                            required
                    ></v-text-field>
                    <v-flex class="text-xs-right">
                        <v-btn large dark color="green lighten-2">회원가입</v-btn>
                    </v-flex>
                </form>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    export default {
        name: "join"
    }
</script>

<style scoped>

</style>
