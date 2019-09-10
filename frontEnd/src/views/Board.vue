<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-container mt-3>
        <v-layout row wrap>
            <v-flex>
                <h3 class="title"> 게시판 </h3>
                <v-divider light class="mt-3 mb-3"></v-divider>
                <v-data-table
                        :headers="headers"
                        :items="desserts"
                        class="elevation-1"
                >
                    <template v-slot:items="props">
                        <router-link :to="`/board/${props.item.id}`">
                            <td class="text-xs-left">{{ props.item.id }}</td>
                        </router-link>
                        <td>{{ props.item.title }}</td>
                        <td>{{ props.item.name }}</td>
                        <td>{{ props.item.viewer }}</td>
                        <td>{{ props.item.time }}</td>
                        <td>{{ props.item.recommend }}</td>
                    </template>

                </v-data-table>
                <div class="text-right mt-2">
                    <v-btn large dark color="green lighten-2" to="/board/write">작성하기</v-btn>
                </div>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    export default {
        data() {
            return {
                headers: [
                    {text: '번호', value: 'id'},
                    {text: '제목', value: 'title', sortable: false,},
                    {text: '작성자', value: 'name', sortable: false,},
                    {text: '조회수', value: 'viewer'},
                    {text: '날짜', value: 'time'},
                    {text: '추천수', value: 'recommend'},
                ],
                desserts: [
                    {
                        id: 1,
                        title: '제목',
                        name: 'Frozen Yogurt',
                        viewer: 159,
                        time: 6.0,
                        recommend: 24,
                    },
                    {
                        id: 2,
                        title: '제목',
                        name: 'Ice cream sandwich',
                        viewer: 237,
                        time: 9.0,
                        recommend: 37,
                    },
                    {
                        id: 3,
                        title: '제목',
                        name: 'Eclair',
                        viewer: 262,
                        time: 16.0,
                        recommend: 23,
                    },
                    {
                        id: 4,
                        title: '제목',
                        name: 'Cupcake',
                        viewer: 305,
                        time: 3.7,
                        recommend: 67,
                    },
                    {
                        id: 5,
                        title: '제목',
                        name: 'Gingerbread',
                        viewer: 356,
                        time: 16.0,
                        recommend: 49,
                    },
                    {
                        id: 6,
                        title: '제목',
                        name: 'Jelly bean',
                        viewer: 375,
                        time: 0.0,
                        recommend: 94,
                    },
                    {
                        id: 7,
                        title: '제목',
                        name: 'Lollipop',
                        viewer: 392,
                        time: 0.2,
                        recommend: 98,
                    },
                    {
                        id: 8,
                        title: '제목',
                        name: 'Honeycomb',
                        viewer: 408,
                        time: 3.2,
                        recommend: 87,
                    },
                    {
                        id: 9,
                        title: '제목',
                        name: 'Donut',
                        viewer: 452,
                        time: 25.0,
                        recommend: 51,
                    },
                    {
                        id: 10,
                        title: '제목',
                        name: 'KitKat',
                        viewer: 518,
                        time: 26.0,
                        recommend: 65,
                    }
                ]
            }
        }
    }
</script>
<style scoped>

</style>
