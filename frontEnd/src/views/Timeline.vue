<template>
    <v-card class="pa-5 mt-5 mb-5 mx-auto" max-width="1000px">
        <v-toolbar color="green" elevation="10">
            <v-toolbar-title class="text-center pt-2">
                학과 연혁
            </v-toolbar-title>
        </v-toolbar>
        <v-timeline>
            <v-timeline-item
                    v-for="(item, i) in items"
                    :key="i"
                    :color="item.color"
                    small
            >
                <template v-slot:opposite>
        <span
                :class="`headline font-weight-bold ${item.color}--text`"
                v-text="item.year"
        ></span>
                </template>
                <div class="py-4">
                    <h2 :class="`headline font-weight-light mb-4 ${item.color}--text`">{{item.title}}</h2>
                    <div>
                        Lorem ipsum dolor sit amet, no nam oblique veritus. Commune scaevola imperdiet nec ut, sed euismod convenire principes at. Est et nobis iisque percipit, an vim zril disputando voluptatibus, vix an salutandi sententiae.
                    </div>
                </div>
            </v-timeline-item>
        </v-timeline>
    </v-card>
</template>

<script>
    export default {
        name: "Timeline",
        data() {
            return {
                items: [

                    {year: 1993, title: '공과대학 컴퓨터공학과 신설', color: 'cyan'},
                    {year: 1994, title: '공과대학 정보통신공학과 신설', color: 'green'},
                    {year: 1995, title: '교육부 지방대특성화사업 유치', color: 'pink'},
                    {year: 1999, title: '교육부 BK21사업 참여("멀티미디어 정보통신")', color: 'amber'},
                    {year: 2000, title: '컴퓨터전공으로 통합', color: 'orange'},
                ]
            }
        }
    }
</script>

<style scoped>

</style>