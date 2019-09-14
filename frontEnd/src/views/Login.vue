<template>
    <v-container class="mt-5 mb-5" fluid style="max-width: 500px">
        <v-layout row wrap>
            <v-flex xs12>
                <v-card>
                    <v-list>
                        <v-flex pt-3 pl-3 pb-3 pr-3>
                            <v-text-field
                                    v-model="username"
                                    v-validate="'required|email'"
                                    label="E-mail"
                                    data-vv-name="email"
                                    required
                            ></v-text-field>
                            <v-text-field
                                    v-model="password"
                                    :counter="10"
                                    label="Password"
                                    password
                                    data-vv-name="name"
                                    required
                            ></v-text-field>
                            <v-checkbox
                                    v-model="checkbox"
                                    v-validate="'required'"
                                    value="1"
                                    label="저장하기"
                                    data-vv-name="checkbox"
                                    type="checkbox"
                                    required
                            ></v-checkbox>

                            <v-layout justify-end>
                                <v-btn class="mr-2" color="blue lighten-3" @click="onSubmit">로그인</v-btn>
                            </v-layout>
                        </v-flex>
                    </v-list>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>

    export default {
        data() {
            return {
                username: '',
                password: '',
                returnPath: '',
                grant_type: 'password',
            }
        },
        computed: {
            invalidForm() {
                return !this.username || !this.password
            }
        },
        created() {
            this.returnPath = this.$route.query.returnPath || '/'
        },
        mounted() {
        },
        methods: {
            onSubmit() {
                const {username, password, grant_type} = this
                console.log(username, password, grant_type)
                this.$store.dispatch('LOGIN', {username, password, grant_type})
                    .then(() => {
                        this.$router.push(this.returnPath)
                    })
                    .catch(err => {
                        const error = err.response.data.error
                        if (error === 'invalid_grant') {
                            alert("잘못된 아이디/비밀번호 입니다.");
                        }
                    })
            }
        }
    }
</script>

<style scoped>

</style>
