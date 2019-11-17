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
                        {{item.content}}
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

                    {
                        year: 1993,
                        title: '공과대학 컴퓨터공학과 신설',
                        color: 'cyan',
                        content: 'Establishment of Computer Science Department of Technical University'
                    },
                    {
                        year: 1994,
                        title: '공과대학 정보통신공학과 신설',
                        color: 'green',
                        content: 'Establishment of Information and Communication Engineering Department of Technical University'
                    },
                    {
                        year: 1995,
                        title: '교육부 지방대특성화사업 유치',
                        color: 'pink',
                        content: 'Lead the education ministry\'s regional university specialization project'
                    },
                    {
                        year: 1999,
                        title: '교육부 BK21사업 참여("멀티미디어 정보통신")',
                        color: 'amber',
                        content: 'Participation in BK21 Project of the Ministry of Education("Multimedia Information and Communication")'
                    },
                    {
                        year: 2005,
                        title: '컴퓨터 정보통신공학전공으로 지정',
                        color: 'teal',
                        content: 'Integrated into Computer Information Communication Engineering major'
                    },
                    {
                        year: 2006,
                        title: 'IT특성화학부로 소속 변경',
                        color: 'light-blue',
                        content: 'Changing the subdivision of IT characteristics'
                    },
                    {
                        year: 2006,
                        title: '정통부 NEXT사업 참여(모바일 임베디드 소프트웨어)',
                        color: 'light-green',
                        content: ' Participate in NEXT Business of Ministry of Information and Communication(Mobile Embedded Software)'
                    },
                    {
                        year: 2007,
                        title: '강원대 취업우수학과 포상',
                        color: 'lime',
                        content: 'a prize for excellence in employment at Kangwon National University'
                    },
                    {
                        year: 2010,
                        title: 'IT대학으로 명칭 및 소속 변경, 전공단위 학생모집',
                        color: 'yellow',
                        content: 'a prize for excellence in employment at Kangwon National University'
                    },
                    {
                        year: 2012,
                        title: '2012년도 학과 평가 1등급(상위 10%), 우수학과 선정',
                        color: 'deep-orange',
                        content: 'Departmental Evaluation 1st Grade(Top 10%), Excellence Department Selection'
                    },
                    {
                        year: 2012,
                        title: '강원대학교-삼성전자 SW인력양성 프로그램 유치(삼성융합소프트웨어전공)',
                        color: 'brown',
                        content: 'Kangwon National University and Samsung Electronics SW Staff Training Program (Samsung Convergence Software Co., Ltd.)'
                    },
                    {year: 2017, title: 'LINC+ 사업 참여 ', color: 'blue-grey', content: 'Participate in LINC+ Business'},
                    {year: 2018, title: '데이터분석센터 유치', color: 'amber', content: 'lead a data analysis center'},

                ]
            }
        }
    }
</script>

<style scoped>

</style>