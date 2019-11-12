<template>
    <v-container mt-5 fluid style="max-width: 500px">
        <v-layout row wrap>
            <v-flex xs12>
                <h3 class="title mb-3"> 회원가입 </h3>
                <v-divider class="mb-5" light></v-divider>
                <form>
                    <v-text-field
                            label="E-mail"
                            v-model="email"
                            placeholder="이메일을 입력하세요."
                            required
                            type="email"
                    ></v-text-field>
                    <v-text-field
                            v-model="name"
                            label="이름"
                            placeholder="이름 입력하세요."
                            required
                    ></v-text-field>
                    <v-text-field
                            v-model="studentId"
                            label="학번"
                            placeholder="학번 입력하세요."
                            required
                    ></v-text-field>
                    <v-text-field
                            v-model="password"
                            class="input-group--focused"
                            placeholder="패스워드를 입력하세요."
                            label="Password"
                            type="password"
                            required
                    ></v-text-field>

                    <div class="text-right mt-2">
                        <v-btn large dark color="green lighten-2" @click="onSubmit">회원가입</v-btn>
                    </div>
                </form>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import {mapActions} from 'vuex'

    export default {
        data() {
            return {
                email: '',
                name: '',
                studentId: '',
                password: ''
            }
        },
        methods: {
            onSubmit() {
                const {email, name, studentId, password} = this;
                this.ADD_ACCOUNT({email, name, studentId, password})
                    .then((data) => {
                        if (data.status >= 200 && data.status < 400) {
                            alert('회원 가입 성공')
                        }
                    }).catch((e) => {
                    alert('회원 가입 실패')
                })
            },
            ...mapActions([
                'ADD_ACCOUNT'
            ])
        },
        mounted() {
            if (this.$store.state.access_token) {
                alert('로그인한 상태로는 회원 가입을 할 수 없습니다.')
                this.$router.push("/")
            }
        }
    }
</script>

<style scoped>

</style>
