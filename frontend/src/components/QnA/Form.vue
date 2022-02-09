<template>
  <div>
    <v-container fluid>
            <v-row>
                <v-col>
                    <v-card
                        width="200"
                        outlined=false
                    >
                        <v-card-title
                            class="justify-center"
                            v-if="type=='create'"
                        >
                        <v-icon>mdi-comment-edit</v-icon>
                            QnA 등록
                        </v-card-title>
                        <v-card-title
                            class="justify-center"
                            v-else
                        >
                        <v-icon>mdi-comment-edit</v-icon>
                            QnA 수정
                        </v-card-title>
                    </v-card>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12">
                    <v-text-field
                    label="제목을 입력하세요"
                    id="qnaTitle"
                    ref="qnaTitle"
                    v-model="qnaTitle"
                    ></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12">
                    <v-textarea
                        outlined
                        name="input-7-1"
                        label="내용을 입력하세요"
                        hint="1000자 이내"
                        id="qnaContent"
                        ref="qnaContent"
                        v-model="qnaContent"
                    ></v-textarea>
                </v-col>
            </v-row>
        </v-container>
    <div class="text-right">
      <v-btn
        v-if="type == 'create'"
        @click="checkHandler"
      >
        등록
      </v-btn>
      <v-btn v-else @click="checkHandler">수정</v-btn>
      <v-btn @click="moveList">목록</v-btn>
    </div>

    <v-snackbar
        v-model="alert"
        timeout="5000"
      >
        <v-icon color="deep-orange darken-3">mdi-home</v-icon>
        {{ alertMsg }}
        
        <template v-slot:action="{ attrs }">
          <v-btn
            color="red"
            text
            v-bind="attrs"
            @click="loginSuccess = false"
          >
            Close
          </v-btn>
        </template>
      </v-snackbar>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { mapGetters, mapState } from "vuex";
export default {
  name: "board-Form",
  props: {
    type: { type: String }
  },
  data: function() {
    return {
      qnaNo: "",
      qnaDatetime: "",
      qnaUserid: "",
      qnaTitle: "",
      qnaContent: "",
      alert:false,
      alertMsg:"",
    };
  },
  computed: {
    ...mapGetters(["isAuthenticated", "isProfileLoaded", "getProfile"]),
    ...mapState({
      authLoading: state => state.auth.status === "loading",
      uname: state => `${state.user.getProfile}`
    }),
    loading: function() {
      return this.authStatus === "loading" && !this.isAuthenticated;
    }
  },
  methods: {
    checkHandler() {
      let err = true;
      let msg = "";
      !this.qnaTitle &&
        ((msg = "제목을 입력해주세요"),
        (err = false),
        this.$refs.qnaTitle.focus());
      err &&
        !this.qnaContent &&
        ((msg = "내용을 입력해주세요"),
        (err = false),
        this.$refs.qnaContent.focus());

      if (!err) {
        this.alertMsg = msg;
        this.alert = true;
      }
      else this.type == "create" ? this.createHandler() : this.updateHandler();
    },
    createHandler() {
      http
        .post("/qna", {
          qnaUserid: this.getProfile,
          qnaTitle: this.qnaTitle,
          qnaContent: this.qnaContent
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          this.alertMsg = msg;
          this.alert = true;
          this.moveList();
        })
        .catch(() => {
          this.alertMsg = "등록 처리시 에러가 발생했습니다.";
          this.alert = true;
        });
    },
    updateHandler() {
      http
        .put(`/qna/${this.qnaNo}`, {
          qnaNo: this.qnaNo,
          qnaDatetime: this.qnaDatetime,
          qnaUserid: this.qnaUserid,
          qnaTitle: this.qnaTitle,
          qnaContent: this.qnaContent
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          this.alertMsg = msg;
          this.alert = true;
          this.moveList();
        })
        .catch(() => {
          this.alertMsg = "수정 처리시 에러가 발생했습니다.";
          this.alert = true;
        });
    },
    moveList() {
      this.$router.push("/qna");
    }
  },
  created() {
    if (this.type === "update") {
      http
        .get(`/qna/${this.$route.query.qnaNo}`)
        .then(({ data }) => {
          this.qnaNo = data.qnaNo;
          this.qnaDatetime = data.qnaDatetime;
          this.qnaUserid = data.qnaUserid;
          this.qnaTitle = data.qnaTitle;
          this.qnaContent = data.qnaContent;
        })
        .catch(() => {
          this.alertMsg = "에러가 발생했습니다.";
          this.alert = true;
        });
    }
  }
};
</script>

<style scoped></style>
