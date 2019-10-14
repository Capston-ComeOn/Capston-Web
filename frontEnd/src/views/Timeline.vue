<template>
    <v-row class="justify-center">
        <v-card class="pa-3 mt-5 mb-5">
            <v-toolbar color="green" elevation="10">
                <v-toolbar-title class="text-center pt-2">
                    학과 연혁
                </v-toolbar-title>
            </v-toolbar>
            <v-timeline style="max-width: 500px">
                <v-timeline-item
                        v-for="(item,index) in items"
                        :key="index"
                        color="red lighten-2"
                        large
                >
                    <template v-slot:opposite>
                        <span>{{item.year}}</span>
                    </template>
                    <v-card class="elevation-2">
                        <v-card-text class="text--darken-2" style="font-size: 18px">{{item.title}}</v-card-text>
                        <!--                <v-card-text>-->
                        <!--                    Lorem ipsum dolor sit amet, no nam oblique veritus. Commune scaevola imperdiet nec ut, sed euismod convenire principes at. Est et nobis iisque percipit, an vim zril disputando voluptatibus, vix an salutandi sententiae.-->
                        <!--                </v-card-text>-->
                    </v-card>
                </v-timeline-item>
            </v-timeline>
        </v-card>
    </v-row>
</template>

<script>
    export default {
        name: "Timeline",
        data() {
            return {
                items: [
                    {year: 1993, title: '공과대학 컴퓨터공학과 신설'},
                    {year: 1994, title: '공과대학 정보통신공학과 신설'},
                    {year: 1995, title: '교육부 지방대특성화사업 유치'},
                    {year: 1999, title: '교육부 BK21사업 참여("멀티미디어 정보통신")'},
                    {year: 2000, title: '컴퓨터전공으로 통합'},
                    {year: 2006, title: '공과대학 컴퓨터공학과 신설'},
                    {year: 2006, title: '공과대학 컴퓨터공학과 신설'},
                    {year: 2006, title: '공과대학 컴퓨터공학과 신설'},
                    {year: 2007, title: '공과대학 컴퓨터공학과 신설'},
                    {year: 2010, title: '공과대학 컴퓨터공학과 신설'},
                    {year: 2010, title: '공과대학 컴퓨터공학과 신설'}
                ]
            }
        }
    }
</script>

<style scoped>

</style>