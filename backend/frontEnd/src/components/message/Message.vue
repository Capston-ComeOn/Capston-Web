<template>
    <v-alert v-if="this.account.id===message.to.id "
             text
             color="orange lighten-1"
             xs12
             style="max-width: 400px; min-width: 400px;"
    >
        <v-list subheader>
            <v-list-item>
                <v-avatar v-if="message && message.to && message.to.imgSrc!==null" size="60" class="mr-2">
                    <img :src="message.to.imgSrc"/>
                </v-avatar>
                <v-list-item-avatar v-else>
                    <v-icon size="60">mdi-account-circle</v-icon>
                </v-list-item-avatar>


                <v-list-item-content>
                    <v-list-item-subtitle v-html="`${message.to.name} (나) ${message.created}`"></v-list-item-subtitle>
                    {{message.content}}

                </v-list-item-content>
            </v-list-item>
        </v-list>
    </v-alert>
    <v-alert
            v-else
            text
            color="green lighten-1"
            xs12
            style="max-width: 400px; min-width: 400px;"
    >
        <v-list subheader>
            <v-list-item>
                <v-list-item-avatar v-if="!message.to.imgSrc">
                    <v-icon size="60">mdi-account-circle</v-icon>
                </v-list-item-avatar>
                <v-avatar v-else size="60" class="mr-2">
                    <img :src="`${message.to.imgSrc}`"/>
                </v-avatar>
                <v-list-item-content>
                    <v-list-item-subtitle v-html="`${message.to.name} (상대방) ${message.created}`"></v-list-item-subtitle>
                    {{message.content}}
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
            ...mapState('account', [
                'account'
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