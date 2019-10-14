<template>
    <v-app-bar clipped-left app color="white" class="elevation-2">
        <v-app-bar-nav-icon @click="onCancelMini()"></v-app-bar-nav-icon>
        <v-toolbar-title>컴온!</v-toolbar-title>
        <div class="flex-grow-1"></div>
        <v-toolbar-items class="hidden-xs-and-down">

            <v-menu class="ml-2" offset-y>
                <template v-slot:activator="{ on }">
                    <v-avatar v-on="on" class="mt-3" color="orange" size="35">
                        <span class="white--text headline">D</span>
                    </v-avatar>
                </template>

                <v-list>
                    <v-list-item
                            v-for="(item, index) in items"
                            :key="index"
                    >
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                    </v-list-item>
                </v-list>
            </v-menu>


        </v-toolbar-items>

    </v-app-bar>
</template>

<script>
    import {mapState, mapMutations} from 'vuex'

    export default {
        data() {
            return {
                items: [
                    {title: '내 정보'},
                    {title: '쪽지함'},
                    {title: 'Click Me'},
                    {title: 'Click Me 2'},
                ]
            }
        },
        computed: {
            ...mapState([
                'mini'
            ])
        },
        methods: {
            onCancelMini() {
                const bool = !this.mini
                this.SET_MINI(bool)
            },
            ...mapMutations([
                'SET_MINI'
            ])
        }
    }
</script>

<style scoped>
</style>
