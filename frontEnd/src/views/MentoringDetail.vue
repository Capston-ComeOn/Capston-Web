<template>
    <v-container style="max-width: 850px">
        <v-layout>
            <carousel/>
        </v-layout>
        <v-layout>
            <v-tabs centered>
                <v-tab>멘토 소개</v-tab>
                <v-tab>모임 소개</v-tab>
                <v-tab>대상 소개</v-tab>
                <v-tab>기타</v-tab>
            </v-tabs>
        </v-layout>
        <v-layout>
            <v-flex>
                <v-avatar color="orange" size="200px" class="mb-3 mt-3">
                    <img
                            aspect-ratio="1"
                            src="https://kimnoin.github.io/images/bio-photo.jpg"
                    >
                </v-avatar>
            </v-flex>
            <v-flex class="mt-5">
                <h1>웹 프로젝트 멘티 모집합니다.</h1>
                <p class="text-right mt-5">201410000 김노인</p>
                <h3 class="text-right mt-5">수강 인원 : 10명 </h3>
            </v-flex>
        </v-layout>

        <v-divider></v-divider>

        <v-layout row>
            <v-flex xs12>
                <h1 class="ma-5">멘토 소개</h1>
            </v-flex>
            <v-flex xs12 class="ml-5">
                <p>2018.05.26 : 삼성전자 SW/TEST A형 취득</p>
                <p>2018.05~09 : 부스트코스 - 안드로이드분야 수료</p>
                <p>2018.11.03 : 이데일리 코딩챌린지 - 본선진출</p>
                <p> 2019.05.16~17 : 네이버 핵데이 참가</p>
                <p> 2019.07.01~08.30 : 네이버웹툰 SW개발 인턴</p>
            </v-flex>
        </v-layout>


        <v-divider></v-divider>

        <v-layout row>
            <v-flex xs class="ma-5">
                <h1 class="mb-5">모임 소개</h1>
                <p>소개</p>
                <p>1. 주 1회 강의 / 주 1회 과제 리뷰 </p>
                <p>2. 강의가 끝나면 프로젝트 과제</p>
                <p>3. 질문은 언제나 환영</p>
            </v-flex>
        </v-layout>


        <v-divider></v-divider>

        <v-layout row>
            <v-flex xs12>
                <h1 class="ma-5">대상 소개</h1>
            </v-flex>
            <v-flex xs12 class="ml-5">
                <p>소개</p>
                <p>1. 컴퓨터 정보통신 공학과 학생 1~2학년</p>
                <p>2. 웹 개발을 시작해보고 싶고 관심이 가는데 어떻게 시작해야 할지 모르겠는 분들</p>
                <p>3. 열심히 할 수 있는 자신감</p>
            </v-flex>
        </v-layout>


        <v-divider></v-divider>

        <v-layout class="mt-3" row>
            <v-flex xs12 class="ml-5">
                <h1 class="mb-5">기타</h1>
                <p>수강 대상이 넘을경우 멘토가 직접 멘티를 선정합니다.</p>
                <p>조기 마감 될 수 있습니다.</p>
            </v-flex>
        </v-layout>

        <v-layout style="height: 100px"></v-layout>


        <v-layout class="justify-center mt-5 mb-5">
            <v-btn color="error" dark large>신청하기</v-btn>
        </v-layout>

        <v-layout style="height: 100px"></v-layout>

    </v-container>
</template>

<script>
    export default {
        name: "MentoringDetail",
        components: {
            carousel: () => import('@/components/Carousel')
        }
    }
</script>

<style scoped>

</style>