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
                        >
                        <v-icon>mdi-bullhorn-outline</v-icon>
                            공지사항 등록
                        </v-card-title>
                    </v-card>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12">
                    <v-text-field
                    label="제목을 입력하세요"
                    id="subject"
                    ref="subject"
                    v-model="subject"
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
                        id="content"
                        ref="content"
                        v-model="content"
                    ></v-textarea>
                </v-col>
            </v-row>
        </v-container>
    <div class="text-right">
      <v-btn @click="checkHandler">
        등록
      </v-btn>
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
            @click="alert = false"
          >
            Close
          </v-btn>
        </template>
      </v-snackbar>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import http from "@/util/http-common";
export default {
  name: "NoticeForm",
  data: function() {
    return {
      subject: "",
      content: "",
      alert: false,
      alertMsg:"",
    };
  },
  methods: {
    checkHandler() {
      let err = true;
      let msg = "";
      !this.subject &&
        ((msg = "제목을 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.content &&
        ((msg = "내용을 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) {
          this.alertMsg = msg;
          this.alert = true;
      }
      else this.createHandler();
    },
    createHandler() {
      http
        .post("/notice/create", {
          id: this.getProfile,
          subject: this.subject,
          content: this.content
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
    moveList() {
      this.$router.push("/noticeList");
    }
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
  }
};
</script>

<style scoped></style>
