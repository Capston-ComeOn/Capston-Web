<template>
    <v-carousel>
        <v-carousel-item
                v-for="(item,i) in items"
                :key="i"
                :src="item.src"
                reverse-transition="fade-transition"
                transition="fade-transition"
        ></v-carousel-item>
    </v-carousel>
</template>

<script>
    export default {
        data() {
            return {
                items: [
                    {
                        src: 'https://cdn.pixabay.com/photo/2015/04/20/13/17/work-731198_1280.jpg',
                    }
                ],
            }
        },
    }
</script>

<style scoped>

</style>