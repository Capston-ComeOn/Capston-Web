<template>
    <v-flex>
        <v-card style="overflow:scroll;" max-height="550px">
            <v-toolbar color="orange lighten-1" dark>
                <v-toolbar-title>Message Box</v-toolbar-title>
                <v-text-field class="ml-3"
                              prepend-inner-icon="mdi-magnify"
                              label="이름 검색"
                              v-model="search"
                              flat
                              hide-details
                              solo-inverted
                ></v-text-field>
            </v-toolbar>

            <v-list v-if="this.accountList && this.accountList.length>0" two-line style="min-height: 500px">
                <h3 class="ml-2">이름 검색</h3>
                <template v-for="(item, index) in this.accountList">
                    <v-list-item
                            :key="item.name"
                            ripple
                            @click="onShowMessageRoom(item.id)"
                    >
                        <v-icon v-if="!item.imgSrc" size="50" class="mr-2">mdi-account-circle</v-icon>
                        <v-avatar v-else size="50" class="mr-2">
                            <img :src="`http://localhost:8090/api/accounts/download?fileName=${item.imgSrc}`"/>
                        </v-avatar>
                        <v-list-item-content>
                            <v-list-item-title v-html="item.name"></v-list-item-title>
                            <!--                            <v-list-item-subtitle v-html="item.subtitle"></v-list-item-subtitle>-->
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider
                            :key="index"
                            inset
                    ></v-divider>
                </template>
            </v-list>
            <v-list v-else two-line style="min-height: 500px; max-width: 500px">
                <h3 class="ml-2">최근 대화상대</h3>
                <template v-for="(item, index) in this.recentMessageList">
                    <!--                    {{item}}-->
                    <v-list-item
                            :key="item.account.name"
                            ripple
                            @click="onShowMessageRoom(item.account.id)"
                    >
                        <v-icon v-if="!item.account.imgSrc" size="50" class="mr-2">mdi-account-circle</v-icon>
                        <v-avatar v-else size="50" class="mr-2">
                            <img :src="`http://localhost:8090/api/accounts/download?fileName=${item.account.imgSrc}`"/>
                        </v-avatar>
                        <v-list-item-content>
                            <v-list-item-title v-html="item.account.name"></v-list-item-title>
                            <v-list-item-subtitle class="mt-3 mb-3 ml-1" v-html="item.content"></v-list-item-subtitle>
                            <v-list-item-subtitle class="text-right" v-html="item.created"></v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider
                            :key="index"
                            inset
                    ></v-divider>
                </template>
            </v-list>
        </v-card>
        <message-room v-show="showMessageRoom" :from="from" @onClose="onClose"
                      style="position:fixed; bottom:60px; right:30px;"/>
    </v-flex>
</template>
<script>
    import {mapState, mapActions} from 'vuex'

    export default {
        computed: {
            ...mapState([
                'accountList',
                'recentMessageList'
            ])
        },
        methods: {
            ...mapActions([
                'FETCH_ACCOUNT_LIST',
                'FETCH_RECENT_CONTACT_LIST'
            ]),
            onShowMessageRoom(id) {
                this.from = id
                this.showMessageRoom = true;
            },
            onClose() {
                this.showMessageRoom = false;
            }
        },
        components: {
            messageRoom: () => import('@/components/message/MessageRoom')
        },
        watch: {
            search() {
                if (this.search.length >= 1) {
                    this.FETCH_ACCOUNT_LIST({name: this.search})
                }
            }
        },
        mounted() {
            this.FETCH_RECENT_CONTACT_LIST()
        },
        data() {
            return {
                from: 0,
                search: '',
                showMessageRoom: false
            }
        },
    }
</script>

<style scoped>

</style>