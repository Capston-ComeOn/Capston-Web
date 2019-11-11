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
                    <div class="text-right mt-2">
                        <v-btn large dark color="green lighten-2" @click="onTest()">회원가입</v-btn>
                    </div>
                </form>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    export default {
        name: "join",
        methods:{
            onTest(){
                alert('회원가입')
            }
        },
        mounted() {
            if (this.$store.state.access_token){
                alert('로그인한 상태로는 회원 가입을 할 수 없습니다.')
                this.$router.push("/")
            }
        }
    }
</script>

<style scoped>

</style>
