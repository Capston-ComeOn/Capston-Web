<template>
    <v-container fluid>
        <v-row>
            <v-col
                    cols="12"
                    lg="4"
            >
                <material-chart-card
                        :data="dailySalesChart.data"
                        :options="dailySalesChart.options"
                        color="info"
                        type="Line"
                >
                    <h4 class="title font-weight-light">
                        방문자 수
                    </h4>

                    <p class="category d-inline-flex font-weight-light">
                        <v-icon
                                color="green"
                                small
                        >
                            mdi-arrow-up
                        </v-icon>
                        <span class="green--text">55%</span>&nbsp;
                        increase
                    </p>

                    <template v-slot:actions>
                        <v-icon
                                class="mr-2"
                                small
                        >
                            mdi-clock-outline
                        </v-icon>
                        <span class="caption grey--text font-weight-light">updated 4 minutes ago</span>
                    </template>
                </material-chart-card>
            </v-col>

            <v-col
                    cols="12"
                    lg="4"
            >
                <material-chart-card
                        :data="emailsSubscriptionChart.data"
                        :options="emailsSubscriptionChart.options"
                        :responsive-options="emailsSubscriptionChart.responsiveOptions"
                        color="red"
                        type="Bar"
                >
                    <h4 class="title font-weight-light">
                        게시글 수
                    </h4>
                    <p class="category d-inline-flex font-weight-light">
                        모든 게시글을 포함
                    </p>

                    <template v-slot:actions>
                        <v-icon
                                class="mr-2"
                                small
                        >
                            mdi-clock-outline
                        </v-icon>
                        <span class="caption grey--text font-weight-light">updated 10 minutes ago</span>
                    </template>
                </material-chart-card>
            </v-col>

            <v-col
                    cols="12"
                    lg="4"
            >
                <material-chart-card
                        :data="dataCompletedTasksChart.data"
                        :options="dataCompletedTasksChart.options"
                        color="green"
                        type="Line"
                >
                    <h3 class="title font-weight-light">
                        기기별 트래픽
                    </h3>
                    <p class="category d-inline-flex font-weight-light">
                        전체, 웹, 안드로이드
                    </p>

                    <template v-slot:actions>
                        <v-icon
                                class="mr-2"
                                small
                        >
                            mdi-clock-outline
                        </v-icon>
                        <span class="caption grey--text font-weight-light">campaign sent 26 minutes ago</span>
                    </template>
                </material-chart-card>
            </v-col>

            <v-col
                    cols="12"
                    sm="6"
                    lg="3"
            >
                <material-stats-card
                        color="green"
                        icon="mdi-store"
                        title="방문자 수"
                        value="125"
                        sub-icon="mdi-calendar"
                        sub-text="Last 24 Hours"
                />
            </v-col>

            <v-col
                    cols="12"
                    sm="6"
                    lg="3"
            >
                <material-stats-card
                        color="orange"
                        icon="mdi-content-copy"
                        title="서버 용"
                        value="49/50"
                        small-value="GB"
                        sub-icon="mdi-alert"
                        sub-icon-color="error"
                        sub-text="Get More Space..."
                        sub-text-color="text-primary"
                />
            </v-col>

            <v-col
                    cols="12"
                    sm="6"
                    lg="3"
            >
                <material-stats-card
                        color="red"
                        icon="mdi-clipboard-text-outline"
                        title="게시글 수"
                        value="75"
                        sub-icon="mdi-calendar"
                        sub-text="Last 24 Hours"
                />
            </v-col>

            <v-col
                    cols="12"
                    sm="6"
                    lg="3"
            >
                <material-stats-card
                        color="info"
                        icon="mdi-magnify"
                        title="검색 노출수"
                        value="+245"
                        sub-icon="mdi-update"
                        sub-text="Just Updated"
                />
            </v-col>

            <v-col
                    cols="12"
                    lg="6"
            >
                <material-card
                        color="orange"
                        title="최신글 보기"
                >
                    <v-data-table
                            :headers="headers"
                            :items="items"
                            hide-default-footer
                    />
                </material-card>
            </v-col>

            <v-col
                    cols="12"
                    lg="6"
            >
                <material-card
                        color="green"
                        title="최신 유저"
                >
                    <v-data-table
                            :headers="headers2"
                            :items="items2"
                            hide-default-footer
                    />
                </material-card>
            </v-col>

        </v-row>
    </v-container>
</template>

<script>
    export default {
        data() {
            return {
                dailySalesChart: {
                    data: {
                        labels: ['M', 'T', 'W', 'T', 'F', 'S', 'S'],
                        series: [
                            [12, 17, 7, 17, 23, 18, 38]
                        ]
                    },
                    options: {
                        lineSmooth: this.$chartist.Interpolation.cardinal({
                            tension: 0
                        }),
                        low: 0,
                        high: 50, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
                        chartPadding: {
                            top: 0,
                            right: 0,
                            bottom: 0,
                            left: 0
                        }
                    }
                },
                dataCompletedTasksChart: {
                    data: {
                        labels: ['12am', '3pm', '6pm', '9pm', '12pm', '3am', '6am', '9am'],
                        series: [
                            [230, 750, 450, 300, 280, 240, 200, 190]
                        ]
                    },
                    options: {
                        lineSmooth: this.$chartist.Interpolation.cardinal({
                            tension: 0
                        }),
                        low: 0,
                        high: 1000, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
                        chartPadding: {
                            top: 0,
                            right: 0,
                            bottom: 0,
                            left: 0
                        }
                    }
                },
                emailsSubscriptionChart: {
                    data: {
                        labels: ['Ja', 'Fe', 'Ma', 'Ap', 'Mai', 'Ju', 'Jul', 'Au', 'Se', 'Oc', 'No', 'De'],
                        series: [
                            [542, 443, 320, 780, 553, 453, 326, 434, 568, 610, 756, 895]

                        ]
                    },
                    options: {
                        axisX: {
                            showGrid: false
                        },
                        low: 0,
                        high: 1000,
                        chartPadding: {
                            top: 0,
                            right: 5,
                            bottom: 0,
                            left: 0
                        }
                    },
                    responsiveOptions: [
                        ['screen and (max-width: 640px)', {
                            seriesBarDistance: 5,
                            axisX: {
                                labelInterpolationFnc: function (value) {
                                    return value[0]
                                }
                            }
                        }]
                    ]
                },
                headers: [
                    {text: '번호', value: 'id'},
                    {text: '제목', value: 'title'},
                    // {text: '작성자', value: 'author.name'},
                    {text: '작성자', value: 'name'},
                    {text: '조회수', value: 'viewer'},
                    {text: '날짜', value: 'time'},
                    {text: '추천수', value: 'recommend'},
                ],
                items: [
                    {id: '10', title: '오늘 백반 메뉴 아는분!', name: '김동규', viewer: 13, time: '2019-10-23', recommend: '2'},
                    {id: '9', title: '날씨가 왜 이러죠..', name: '이상훈', viewer: 27, time: '2019-10-23', recommend: '5'},
                    {id: '8', title: '참치를 먹고 싶은 걸 참치마요', name: '곽병선', viewer: 58, time: '2019-10-23', recommend: '-8'},
                    {id: '7', title: '카페 추천좀 해주세요!', name: '곽병선', viewer: 32, time: '2019-10-23', recommend: '0'},
                    {id: '6', title: '슬슬 시험기간 시즌 ㅠㅠ', name: '안태회', viewer: 19, time: '2019-10-23', recommend: '5'}
                ],
                headers2: [
                    {text: '번호', value: 'id'},
                    {text: '아이디', value: 'email'},
                    {text: '이름', value: 'name'},
                    {text: '학번', value: 'number'},
                    {text: '졸업유무', value: 'time'},
                    {text: '가입일', value: 'recommend'}
                ],
                items2: [
                    {id: '5', email: 'user1@email.com', name: '김동규', number: 201912345, time: 'N', recommend: '2019-10-23'},
                    {id: '4', email: 'user2@email.com', name: '이상훈', number: 201912346, time: 'N', recommend: '2019-10-23'},
                    {id: '3', email: 'user3@email.com', name: '곽병선', number: 201912347, time: 'N', recommend: '2019-10-23'},
                    {id: '2', email: 'user4@email.com', name: '안태회', number: 201912348, time: 'N', recommend: '2019-10-23'},
                    {id: '1', email: 'user5@email.com', name: '홍길동', number: 201912349, time: 'Y', recommend: '2019-10-23'}
                ],
                tabs: 0,
                list: {
                    0: false,
                    1: false,
                    2: false
                }
            }
        },
        methods: {
            complete(index) {
                this.list[index] = !this.list[index]
            }
        }
    }
</script>
