<template>
    <v-card :elevation="20" class="mx-auto pa-5 ma-5" style="max-width: 720px">
        <v-toolbar color="green" elevation="10">
            <v-toolbar-title class="text-center pt-2">
                작성하기
            </v-toolbar-title>
        </v-toolbar>
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
                tabs: [
                    {name: '멘토 소개', model: ''},
                    {name: '모임 소개', model: ''},
                    {name: '대상 소개', model: ''},
                    {name: '기타', model: ''}
                ]
            }
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