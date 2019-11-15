<template>
    <v-card class="mx-auto" max-width="500">
        <v-toolbar :color="color" elevation="10">
            <v-toolbar-title class="text-center pt-2">
                타이틀
            </v-toolbar-title>
        </v-toolbar>
        <v-list>
            <v-list-item-group v-model="model">
                <v-list-item
                        v-for="(item, i) in items"
                        :key="i"
                >
                    <v-list-item-content>
                        <v-list-item-title v-text="item.text"></v-list-item-title>
                    </v-list-item-content>
                    <v-list-item-icon>
                        <v-icon size="30" class="mr-2">mdi-account-circle</v-icon>
                        <v-list-item-subtitle class="mt-1">이름(학번)</v-list-item-subtitle>
                    </v-list-item-icon>
                </v-list-item>
            </v-list-item-group>
        </v-list>
    </v-card>
</template>

<script>
    export default {
        props:['color'],
        name: "HomeDataTable",
        data: () => ({
            items: [
                {
                    icon: 'mdi-inbox',
                    text: '제목',
                },
                {
                    icon: 'mdi-star',
                    text: '제목',
                },
                {
                    icon: 'mdi-send',
                    text: '제목',
                },
                {
                    icon: 'mdi-email-open',
                    text: '제목',
                },
                {
                    icon: 'mdi-email-open',
                    text: '제목',
                }
            ],
            model: 1,
        }),
    }
</script>

<style scoped>

</style>