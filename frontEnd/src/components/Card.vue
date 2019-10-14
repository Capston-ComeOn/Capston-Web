<template>
    <v-hover v-slot:default="{ hover }">
        <v-card
                :elevation="hover ? 24 : 2"
                max-width="300">
            <v-list-item>
                <v-avatar class="mr-2">
                    <img src="https://kimnoin.github.io/images/bio-photo.jpg">
                </v-avatar>
                <v-list-item-content>
                    <v-list-item-title class="headline">{{item.title}}</v-list-item-title>
                    <v-list-item-subtitle>by {{item.author}}</v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>

            <router-link to="/mentoring/detail">
                <v-img style="cursor:pointer"
                       src="https://kimnoin.github.io/images/bio-photo.jpg"
                       height="194"
                ></v-img>
            </router-link>
            <v-card-text>
                {{item.content}}
            </v-card-text>

            <v-card-actions>
                <div class="flex-grow-1"></div>
                <v-btn icon>
                    <v-icon>mdi-heart</v-icon>
                </v-btn>
                <v-btn icon>
                    <v-icon>mdi-share-variant</v-icon>
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-hover>
</template>

<script>

    export default {
        props: ['item'],
        name: "Card"
    }
</script>

<style scoped>

</style>