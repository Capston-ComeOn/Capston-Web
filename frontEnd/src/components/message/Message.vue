<template>
    <v-alert v-if="this.loginAccountId===message.to.id "
             text
             color="orange lighten-1"
             xs12
    >
        <v-list subheader>
            <v-list-item>
                <v-list-item-avatar>
                    <v-icon size="60">mdi-account-circle</v-icon>
                </v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-subtitle v-html="message.to.name"></v-list-item-subtitle>
                    <v-list-item-subtitle v-html="formatDate(message.created)"></v-list-item-subtitle>
                    <v-list-item-title style="max-width: 350px" v-html="message.content"></v-list-item-title>
                </v-list-item-content>
            </v-list-item>
        </v-list>
    </v-alert>
    <v-alert
            v-else
            text
            color="green lighten-1"
            xs12
    >
        <v-list subheader>
            <v-list-item>
                <v-list-item-avatar>
                    <v-icon size="60">mdi-account-circle</v-icon>
                </v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-subtitle v-html="message.to.name"></v-list-item-subtitle>
                    <v-list-item-subtitle v-html="formatDate(message.created)"></v-list-item-subtitle>
                    <v-list-item-title style="max-width: 350px" v-html="message.content"></v-list-item-title>
                </v-list-item-content>
            </v-list-item>
        </v-list>
    </v-alert>
</template>

<script>
    import {mapState} from 'vuex'

    export default {
        props: ['message'],
        name: "Message",
        computed: {
            ...mapState([
                'loginAccountId'
            ])
        },
        methods: {
            formatDate(date) {
                let d = new Date(date)
                const {zero} = this
                let yymmdd = zero(d.getFullYear()) + "-" + zero((d.getMonth() + 1)) + "-" + zero(d.getDate());
                let hhmmdd = zero(d.getHours()) + ":" + zero(d.getMinutes()) + ":" + zero(d.getSeconds());
                return yymmdd + " " + hhmmdd;
            },
            zero(data) {
                if (data < 10)
                    return "0" + data;
                return data
            }
        }
    }
</script>

<style scoped>

</style>