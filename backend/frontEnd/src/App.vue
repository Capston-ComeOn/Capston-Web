<template>
    <v-app>
        <drawer></drawer>
        <!--        <toolbar></toolbar>-->
        <v-content class="ma-3">
            <v-flex @click="onMini">
                <router-view></router-view>
            </v-flex>
        </v-content>
        <message-recent-contact v-if="isAuthenticated" v-show="showMessage"
                                style="position:fixed; bottom:60px; right:30px;"></message-recent-contact>
        <v-btn v-if="isAuthenticated" @click="showMessage=!showMessage" style="position:fixed; bottom:5px; right:30px;"
               absolute right dark fab
               color="orange">
            <v-badge>
                <template v-slot:badge>0</template>
                <v-icon>mdi-email</v-icon>
            </v-badge>
        </v-btn>

    </v-app>
</template>

<script>
    import {mapGetters, mapMutations} from 'vuex'

    export default {
        name: 'App',
        data() {
            return {
                showMessage: false,
            }
        },
        methods: {
            ...mapMutations('article', [
                'SET_MINI'
            ]),
            onMini() {
                this.SET_MINI(true)
            }
        },
        computed: {
            ...mapGetters('account', [
                'isAuthenticated'
            ])
        },
        components: {
            // toolbar: () => import('@/components/ToolBar'),
            drawer: () => import('@/components/Drawer'),
            messageRecentContact: () => import('@/components/message/MessageRecentContact'),

        }
    }
</script>
