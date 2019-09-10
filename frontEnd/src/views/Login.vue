<template>
    <v-container fluid style="max-width: 500px">
        <v-layout row wrap>
            <v-flex xs12>
                <v-card>
                    <v-list>
                        <v-flex pt-3 pl-3 pb-3 pr-3>
                            <v-text-field
                                    v-model="email"
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
                                <v-btn round color="blue lighten-3" @click="submit">로그인</v-btn>
                                <v-btn round color="blue lighten-3" @click="clear">회원가입</v-btn>
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
        name: "login"
    }
</script>

<style scoped>

</style>
