<template>
    <v-container mt-3>
        <v-layout row wrap>
            <v-flex xs12>
                <v-label>작성자</v-label>
                <v-text-field
                        value=""
                        solo
                        readonly
                ></v-text-field>
            </v-flex>
            <v-flex xs12>
                <v-label>내용</v-label>
                <v-textarea
                        solo
                        label="내용"
                        readonly
                        rows="10"
                        value="테스트 글 작성"
                ></v-textarea>
                {{item}}
            </v-flex>
            <v-flex class="text-right text-xs-right">
                <v-btn large dark color="red lighten-1 mr-1" to="/board">목록</v-btn>
                <v-btn large dark color="red lighten-1 mr-1">수정</v-btn>
                <v-btn large dark color="red lighten-1">삭제</v-btn>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    export default {
        props: ['item']
    }
</script>

<style scoped>

</style>
