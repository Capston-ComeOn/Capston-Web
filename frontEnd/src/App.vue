<template>
    <v-app>
        <ToolBar></ToolBar>
        <router-view></router-view>
        <Footer></Footer>
    </v-app>
</template>

<script>
    import ToolBar from './components/ToolBar'
    import Footer from './components/Footer'

    export default {
        name: 'App',
        data() {
            return {}
        },
        components: {
            ToolBar,
            Footer
        },
        methods: {}
    };
</script>
