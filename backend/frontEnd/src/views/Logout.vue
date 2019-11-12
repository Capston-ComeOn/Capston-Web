<template>
    <div>
    </div>
</template>

<script>
    import {mapMutations} from 'vuex'

    export default {
        name: "Logout",
        methods: {
            ...mapMutations([
                'LOGOUT'
            ])
        },
        mounted() {
            this.LOGOUT()
        }
    }
</script>

<style scoped>

</style>