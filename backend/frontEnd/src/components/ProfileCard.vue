<template>
    <v-dialog
            v-model="dialog"
            max-width="290"

    >
        <v-card
                class="mx-auto"
                max-width="434"
                tile
        >
            <v-img
                    v-if="author.imgSrc"
                    height="100%"
                    :src="`http://localhost:8090/api/accounts/download?fileName=${author.imgSrc}`"
            >
                <v-row
                        align="end"
                        class="fill-height"
                >
                    <v-col
                            align-self="start"
                            class="pa-0"
                            cols="12"
                    >
                    </v-col>
                    <v-col class="py-0 text-right">
                        <v-list-item
                                color="rgba(0, 0, 0, .4)"
                                dark
                        >
                            <v-list-item-content>
                                <v-list-item-title class="title">{{author.name}}</v-list-item-title>
                                <v-list-item-subtitle>{{author.studentId}}</v-list-item-subtitle>
                            </v-list-item-content>
                        </v-list-item>
                    </v-col>
                </v-row>
            </v-img>
        </v-card>

    </v-dialog>
</template>

<script>
    export default {
        props: ['author'],
        data() {
            return {
                dialog: true
            }
        },
        watch: {
            dialog(val) {
                if (val === false) {
                    this.$emit('@change')
                }
            }
        }
    }
</script>

<style scoped>

</style>