<template>
    <v-container grid-list-xs>
        <v-layout row wrap>
            <v-flex>
                <h3 class="title">글 작성</h3>
                <v-divider class="mt-3" light></v-divider>
            </v-flex>
            <v-flex xs12 class="mt-5">
                <editor v-model="editorText"/>
            </v-flex>
            <v-flex class="text-xs-right">
                <v-btn large dark @click="" color="red lighten-2">작성</v-btn>
                <v-btn large dark @click="" color="red lighten-2" to="/board">목록</v-btn>
            </v-flex>
        </v-layout>
    </v-container>
</template>
<script>
    import 'tui-editor/dist/tui-editor.css'
    import 'tui-editor/dist/tui-editor-contents.css'
    import 'codemirror/lib/codemirror.css'
    import {Editor, Viewer} from '@toast-ui/vue-editor'

    export default {
        components: {
            'editor': Editor,
            // eslint-disable-next-line vue/no-unused-components
            'viewer': Viewer
        },
        data() {
            return {
                editorText: ''
            }
        }
    }
</script>
