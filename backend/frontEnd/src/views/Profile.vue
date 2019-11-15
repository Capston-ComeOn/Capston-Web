<template>
    <v-card
            max-width="500"
            class="mx-auto mt-5"
    >
        <v-list-item class="pa-3">

            <v-avatar v-if="account && account.imgSrc!==null" size="120" class="mr-2">
                <img :src="account.imgSrc"/>
            </v-avatar>
            <v-icon v-else size="100">mdi-account-circle</v-icon>
            <v-list-item-content>
                <v-btn @click="isShowImgChangeModal=true">Change Avatar</v-btn>
            </v-list-item-content>
        </v-list-item>

        <v-text-field
                v-model="accountDto.name"
                class="ma-4"
                label="이름"
                placeholder="이름"
        ></v-text-field>

        <v-text-field
                v-model="accountDto.studentId"
                class="ma-4"
                label="학번"
                placeholder="학번"
        ></v-text-field>

        <v-text-field
                v-model="accountDto.email"
                class="ma-4"
                label="이메일"
                placeholder="이메일"
        ></v-text-field>

        <div class="text-right">
            <v-btn
                    class="ma-2"
                    text
                    color="deep-purple accent-4"
            >
                수정하기
            </v-btn>
        </div>

        <modal v-if="isShowImgChangeModal">
            <h3 slot="header">이미지 업로드</h3>
            <div slot="body">
                <div class="form-group">
                    <div class="custom-file mt-2">
                        <v-file-input label="Choose file" id="file" name="file" type="file" ref="files"
                                      @change="onFileChange"
                                      :rules="rules"
                                      accept="image/png, image/jpeg, image/bmp"
                                      placeholder="사진 업로드"
                                      prepend-icon="mdi-camera"/>
                    </div>

                    <v-img v-if="url" :src="url"/>
                    <!--                    <img v-else-if="account.accountFile" class="img-thumbnail"-->
                    <!--                         :src="`http://localhost:8090/api/accounts/download?fileName=${account.accountFile.uuid}_${account.accountFile.fileName}`"/>-->
                    <!--                    <img v-else class="img-thumbnail" src="../../../assets/person_profile.png">-->

                </div>
                <ul class="list-group mt-2">
                    <li v-for="(file,index) in files" :key="index" class="list-group-item">{{file.name}}</li>
                </ul>
            </div>
            <div class="text-rightMentoring.vue" slot="footer">
                <v-btn @click="onAddItem" class="mr-2">수정</v-btn>
                <v-btn @click="onFileList">취소</v-btn>
            </div>
        </modal>

    </v-card>
</template>

<script>
    import {mapActions, mapState} from 'vuex'

    export default {
        name: "Profile",
        components: {
            modal: () => import('@/components/ImageUploader')
        },
        data() {
            return {
                rules: [
                    value => !value || value.size < 2000000 || '사진 2 MB 이하만 가능합니다.',
                ],
                accountDto: {
                    name: '',
                    studentId: '',
                    email: ''
                },
                files: '',
                isShowImgChangeModal: false,
                url: ''
            }
        },
        computed: {
            ...mapState([
                'account'
            ])
        },
        methods: {
            ...mapActions([
                'ADD_ACCOUNT_FILE',
                'FETCH_LOGIN_ACCOUNT'
            ]),
            onAddItem() {
                let formData = new FormData()
                let inputFile = document.querySelector('#file')
                let file = inputFile.files
                for (let i = 0; i < file.length; i++) {
                    if (!this.isExtensionAndSize(file[i].name, file[i].size)) {
                        return
                    }
                    formData.append('uploadFile', file[i])
                }
                this.ADD_ACCOUNT_FILE({formData: formData})
                alert('등록이 완료되었습니다.')
                this.$router.push('/')
            },
            isExtensionAndSize(fileName, fileSize) {

                // eslint-disable-next-line no-useless-escape
                let regex = new RegExp('(.*?)\.(exe|sh|zip|alz)$')
                let maxSize = 1048576  // 1MB
                if (fileSize >= maxSize) {
                    alert('파일 사이즈 초과')
                    return false
                }
                if (regex.test(fileName)) {
                    alert('해당 파일형식은 업로드 불가능합니다.')
                    return false
                }
                return true
            },
            onFileList() {
                this.isShowImgChangeModal = false
                this.files = null
                this.url = null
            },
            onFileChange() {
                const file = document.querySelector('#file').files[0];
                this.url = URL.createObjectURL(file);
                this.files = this.$refs.files.files;
            }
        },
        mounted() {
            this.FETCH_LOGIN_ACCOUNT().then(() => {
                this.accountDto.name = this.account.name;
                this.accountDto.studentId = this.account.studentId
                this.accountDto.email = this.account.email
            })
        }
    }
</script>

<style scoped>

</style>