<template>
    <v-container>
        <v-layout row>
            <v-flex col-6>
                <home-data-table :color="'brown lighten-4'" class="ma-3"/>
            </v-flex>
            <v-flex col-6>
                <home-data-table :color="'pink lighten-4'" class="ma-3"/>
            </v-flex>
            <v-flex col-6>
                <home-data-table :color="'indigo lighten-4'" class="ma-3"/>
            </v-flex>
            <v-flex col-6>
                <home-data-table :color="'cyan lighten-4'" class="ma-3"/>
            </v-flex>
            <v-flex col-6>
                <home-data-table :color="'lime lighten-4'" class="ma-3"/>
            </v-flex>
            <v-flex col-6>
                <home-data-table :color="'amber lighten-4'" class="ma-3"/>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>

    export default {
        components: {
            'home-data-table': () => import('@/components/HomeDataTable')
        }
    }
</script>

<style scoped>

</style>
