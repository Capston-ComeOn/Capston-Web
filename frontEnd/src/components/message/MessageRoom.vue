<template>
    <v-flex>
        <v-toolbar color="orange lighten-1" dark>
            <v-toolbar-title>Message Box</v-toolbar-title>
            <div class="flex-grow-1"></div>
            <v-btn @click="onClose" icon>
                <v-icon>mdi-close</v-icon>
            </v-btn>
        </v-toolbar>
        <v-card style="overflow:scroll;" max-height="550px">
            <message v-for="(message,index) in this.messageList" :key="index" :message="message"/>
            <form class="row ma-3">
                <v-text-field xs8
                              v-model="content"
                              :counter="100"
                              required
                ></v-text-field>
                <v-btn color="blue lighten-3" xs4 class="ml-2 mt-5" @click="onSubmit()">submit</v-btn>
            </form>
        </v-card>
    </v-flex>
</template>

<script>
    import {mapMutations, mapState, mapActions} from 'vuex'

    export default {
        props: ['from'],
        name: "MessageRoom",
        computed: {
            ...mapState([
                'messageList'
            ])
        },
        methods: {
            onClose() {
                this.CLEAR_MESSAGE_LIST()
                this.$emit('onClose')
            },
            onSubmit() {
                this.ADD_MESSAGE({from: this.from, content: this.content})
                this.content = ''
            },
            ...mapMutations([
                'CLEAR_MESSAGE_LIST'
            ]),
            ...mapActions([
                'ADD_MESSAGE',
                'FETCH_MESSAGE_LIST',
                'FETCH_LOGIN_ACCOUNT'
            ])
        },
        data() {
            return {
                content: '',
                myself: {
                    title: '타이틀타이틀타이틀타이틀타이틀타이틀타이틀',
                    content: '내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용'
                },
                others: {title: '타이틀', content: '내용내용내용내용내용내용'}
            }
        },
        components: {
            message: () => import('@/components/message/Message')
        },
        mounted() {
            this.FETCH_LOGIN_ACCOUNT()
            setInterval(() => {
                if (this.from > 0) {
                    this.FETCH_MESSAGE_LIST({from: this.from});
                }
            }, 1000);
        }
    }
</script>

<style scoped>

</style>