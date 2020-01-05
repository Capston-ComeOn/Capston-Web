<template>
    <v-container class="mt-5 mb-5" fluid style="max-width: 500px">
        <v-layout row wrap>
            <v-flex xs12>
                <v-card class="elevation-12">
                    <v-toolbar
                            color="primary"
                            dark
                            flat
                    >
                        <v-toolbar-title>Login</v-toolbar-title>
                    </v-toolbar>
                    <v-list>
                        <v-flex pt-3 pl-3 pb-3 pr-3>
                            <v-text-field
                                    v-model="username"
                                    label="E-mail"
                                    data-vv-name="email"
                                    required
                            ></v-text-field>
                            <v-text-field
                                    v-model="password"
                                    :counter="10"
                                    type="password"
                                    label="Password"
                                    password
                                    data-vv-name="name"
                                    required
                            ></v-text-field>
                            <!--                            <v-checkbox-->
                            <!--                                    v-validate="'required'"-->
                            <!--                                    value="1"-->
                            <!--                                    label="저장하기"-->
                            <!--                                    data-vv-name="checkbox"-->
                            <!--                                    type="checkbox"-->
                            <!--                                    required-->
                            <!--                            ></v-checkbox>-->
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

    import {mapActions, mapGetters} from 'vuex'

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
            ...mapGetters('account', [
                'isAuthenticated'
            ])
        },
        created() {
            this.returnPath = this.$route.query.returnPath || '/'
        },
        mounted() {
            if (this.isAuthenticated) {
                alert('로그인한 상태로는 다시 로그인 할 수 없습니다.')
                this.$router.push("/")
            }
        },
        methods: {
            ...mapActions('account', [
                'LOGIN'
            ]),
            onSubmit() {
                const {username, password, grant_type} = this
                this.LOGIN({username, password, grant_type})
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
