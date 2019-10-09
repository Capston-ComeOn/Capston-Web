<template>
  <v-flex class="pl-5 pr-5">
    <material-card
      color="green lighten-1"
      title="User Profile"
    >
      <v-data-table
        :headers="headers"
        :items="this.accountList"
        :server-items-length.sync="pagination.totalSize"
        @pagination="onPagination"
      >

        <template v-slot:body="{ items }">
          <tbody>
          <tr v-for="(item,index) in items" :key="index">
            <td>{{item.id}}</td>
            <td>{{item.email}}</td>
            <td>{{item.name}}</td>
            <td></td>
            <td></td>
            <td></td>
            <td><v-btn icon><v-icon>mdi-pencil</v-icon></v-btn></td>
          </tr>
          </tbody>

        </template>
      </v-data-table>
    </material-card>
  </v-flex>
</template>

<script>
    import {mapState, mapMutations, mapActions} from 'vuex'

    export default {

        data() {
            return {
                headers: [
                    {text: '번호', value: 'id'},
                    {text: '아이디', value: 'email'},
                    {text: '이름', value: 'name'},
                    {text: '학번', value: 'viewer'},
                    {text: '졸업유무', value: 'time'},
                    {text: '가입일', value: 'recommend'},
                    {text: '수정', value: 'action'}
                ],
                pagination: {
                    totalSize: 0
                }
            }
        },
        computed: {
            ...mapState([
                'accountList',
                'accountSize',
            ]),
        },
        methods: {
            ...mapMutations([
                'SET_PAGE'
            ]),
            ...mapActions([
                'FETCH_ACCOUNT_LIST',
                'FETCH_ACCOUNT_SIZE',
            ]),
            onPagination(pagination) {
                if (pagination) {
                    const {page, itemsPerPage} = pagination
                    this.SET_PAGE(page - 1)
                    this.FETCH_ACCOUNT_LIST({size: itemsPerPage, page: (page - 1)})
                }
            }
        },
        mounted() {
            // 자유게시판
            this.FETCH_ACCOUNT_SIZE()
                .then(data => {
                    this.pagination.totalSize = data
                    this.onPagination()
                })

        }
    }
</script>
