<template>
    <v-app>
        <Drawer></Drawer>
        <ToolBar></ToolBar>
        <v-content>
            <v-flex @click="onMini">
                <router-view></router-view>
            </v-flex>
        </v-content>
    </v-app>
</template>

<script>
    import ToolBar from './components/ToolBar'
    import NavigationDrawer from "./components/NavigationDrawer";
    import {mapMutations} from 'vuex'

    export default {
        name: 'App',
        data() {
            return {}
        },
        methods: {
            ...mapMutations([
                'SET_MINI'
            ]),
            onMini(){
                this.SET_MINI(true)
            }
        },
        components: {
            ToolBar,
            Drawer: NavigationDrawer
        }
    };
</script>
