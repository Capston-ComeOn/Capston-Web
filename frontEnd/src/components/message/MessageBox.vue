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

            <v-list v-if="this.accountList && this.accountList.length>0" two-line>
                <h3 class="ml-2">이름 검색</h3>
                <template v-for="(item, index) in this.accountList">
                    <v-list-item
                            :key="item.title"
                            ripple
                            @click="onShowMessageRoom(item)"
                    >
                        <v-list-item-avatar>
                            <img src="https://kimnoin.github.io/images/bio-photo.jpg">
                        </v-list-item-avatar>
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
            <v-list v-else two-line>
                <template v-for="(item, index) in this.items">
                    <v-divider
                            v-if="item.divider"
                            :key="index"
                            inset
                    ></v-divider>

                    <v-list-item
                            v-else
                            :key="item.title"
                            ripple
                            @click="showChatting=true"
                    >
                        <v-list-item-avatar>
                            <img :src="item.avatar">
                        </v-list-item-avatar>
                        <v-list-item-content>
                            <v-list-item-title v-html="item.title"></v-list-item-title>
                            <v-list-item-subtitle v-html="item.subtitle"></v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
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
                'accountList'
            ])
        },
        methods: {
            ...mapActions([
                'FETCH_ACCOUNT_LIST'
            ]),
            onShowMessageRoom(item) {
                this.from=item.id
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
        data() {
            return {
                from: 0,
                search: '',
                showMessageRoom: false,
                items: [
                    {
                        avatar: 'https://picsum.photos/250/300?image=660',
                        title: 'Meeting @ Noon',
                        subtitle:
                            "<span class='text--primary'>Spike Lee</span> &mdash; I'll be in your neighborhood",
                    },
                    {divider: true},
                    {
                        avatar: 'https://picsum.photos/250/300?image=821',
                        title: 'Summer BBQ <span class="grey--text text--lighten-1"></span>',
                        subtitle:
                            "<span class='text--primary'>to Operations support</span> &mdash; Wish I could come.",
                    },
                    {divider: true},
                    {
                        avatar: 'https://picsum.photos/250/300?image=783',
                        title: 'Yes yes',
                        subtitle:
                            "<span class='text--primary'>Bella</span> &mdash; Do you have Paris recommendations",
                    },
                    {divider: true},
                    {
                        avatar: 'https://picsum.photos/250/300?image=1006',
                        title: 'Dinner tonight?',
                        subtitle:
                            "<span class='text--primary'>LaToya</span> &mdash; Do you want to hang out?",
                    },
                    {divider: true},
                    {
                        avatar: 'https://picsum.photos/250/300?image=146',
                        title: 'So long',
                        subtitle:
                            "<span class='text--primary'>Nancy</span> &mdash; Do you see what time it is?",
                    },
                    {divider: true},
                    {
                        avatar: 'https://picsum.photos/250/300?image=1008',
                        title: 'Breakfast?',
                        subtitle:
                            "<span class='text--primary'>LaToya</span> &mdash; Do you want to hang out?",
                    },
                    {divider: true},
                    {
                        avatar: 'https://picsum.photos/250/300?image=839',
                        title:
                            'Winter Porridge <span class="grey--text text--lighten-1"></span>',
                        subtitle:
                            "<span class='text--primary'>cc: Daniel</span> &mdash; Tell me more...",
                    },
                    {divider: true},
                    {
                        avatar: 'https://picsum.photos/250/300?image=145',
                        title: 'Oui oui',
                        subtitle:
                            "<span class='text--primary'>Nancy</span> &mdash; Do you see what time it is?",
                    },
                ],
            }
        },
    }
</script>

<style scoped>

</style>