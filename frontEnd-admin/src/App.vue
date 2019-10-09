<template>
  <v-app>
    <core-app-bar v-if="isAuthenticated"/>
    <core-drawer/>
    <core-view/>
  </v-app>
</template>

<script>
  import {mapGetters} from 'vuex'
    export default {
        components: {
            CoreAppBar: () => import('@/components/core/AppBar'),
            CoreDrawer: () => import('@/components/core/Drawer'),
            CoreView: () => import('@/components/core/View')
        },
        computed:{
            ...mapGetters([
                'isAuthenticated'
            ])
        },
    }
</script>
