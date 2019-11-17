<template>
    <v-card class="mx-auto" max-width="500">
        <v-toolbar :color="color" elevation="10">
            <v-toolbar-title class="text-center pt-2">
                {{title}}
            </v-toolbar-title>
        </v-toolbar>
        <v-list>
            <v-list-item-group :key="i" v-for="(item, i) in table">
                <v-list-item :to="`/board/${item.category.id}/${item.id}`">
                    <v-list-item-content>
                        <v-list-item-title v-text="item.title"></v-list-item-title>
                    </v-list-item-content>
                    <v-list-item-icon class="ma-0">
                        <v-icon class="mr-2" size="30" v-if="!item.author.imgSrc">mdi-account-circle</v-icon>
                        <v-list-item-avatar class="pa-0 mr-3" size="30" v-else>
                            <img :src="item.author.imgSrc"/>
                        </v-list-item-avatar>
                        <v-list-item-subtitle class="mt-2">
                            {{item.author.name}}({{item.author.studentId}})
                            <div class="text-right" style="font-size: 8px">{{item.created}}</div>
                        </v-list-item-subtitle>
                    </v-list-item-icon>
                </v-list-item>
            </v-list-item-group>
        </v-list>
    </v-card>
</template>

<script>
    export default {
        props: ['color', 'title', 'table'],
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