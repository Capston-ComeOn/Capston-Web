<template>
    <v-card :elevation="20" class="mx-auto pa-5 ma-5" style="max-width: 720px">
        <v-toolbar color="green" elevation="10">
            <v-toolbar-title class="text-center pt-2">
                작성하기
            </v-toolbar-title>
        </v-toolbar>

        <v-text-field
                class="mt-3"
                label="제목"
        ></v-text-field>

        <v-flex class="pa-3">
            <v-text-field v-model="this.dateRangeText" label="Date range" prepend-icon="mdi-calendar-range"  readonly></v-text-field>
            <v-date-picker v-model="dates" range locale="ko-kr" full-width no-title></v-date-picker>
        </v-flex>


        <v-flex v-for="(tab,index) in tabs" :key="index" class="mt-5">
            <v-label>{{tab.name}}</v-label>
            <v-flex class="mt-5">
                <editor v-model="tab.model"
                        previewStyle="vertical"/>
            </v-flex>
        </v-flex>
        <div class="text-right mt-5">
            <v-btn class="mr-2" to="/mentoring/write" color="orange lighten-2" dark>작성</v-btn>
            <v-btn to="/mentoring/write" color="orange lighten-2" dark>취소</v-btn>
        </div>

    </v-card>
</template>

<script>
    import 'tui-editor/dist/tui-editor.css'
    import 'tui-editor/dist/tui-editor-contents.css'
    import 'codemirror/lib/codemirror.css'
    import {Editor, Viewer} from '@toast-ui/vue-editor'

    export default {
        data() {
            return {
                dates: [new Date().toISOString().substr(0, 10), (new Date(Date.now() + 7*24*60*60*1000)).toISOString().substr(0, 10)],
                tabs: [
                    {name: '멘토 소개', model: ''},
                    {name: '모임 소개', model: ''},
                    {name: '대상 소개', model: ''},
                    {name: '기타', model: ''}
                ]
            }
        },
        computed: {
            dateRangeText () {
                return this.dates.join(' ~ ')
            },
        },
        components: {
            'editor': Editor,
            // eslint-disable-next-line vue/no-unused-components
            'viewer': Viewer
        }

    }
</script>

<style scoped>

</style>