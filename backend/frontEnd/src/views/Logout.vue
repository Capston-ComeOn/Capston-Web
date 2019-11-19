<template>
    <div>
    </div>
</template>

<script>
    import {mapMutations} from 'vuex'

    export default {
        methods: {
            ...mapMutations('account', [
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