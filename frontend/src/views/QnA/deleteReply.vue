<template>
  <div><v-snackbar
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
      </v-snackbar></div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "QnAdelete",
  data: function() {
    return {
      alert : false,
      alertMsg : "",
    }
  },
  created() {
    http.get(`/qna/${this.$route.query.qnaNo}`).then(({ data }) => {
      http
        .put(`/qna/${this.$route.query.qnaNo}`, {
          qnaNo: data.qnaNo,
          qnaDatetime: data.qnaDatetime,
          qnaUserid: data.qnaUserid,
          qnaTitle: data.qnaTitle,
          qnaContent: data.qnaContent,
          replyUserid: null,
          replyContent: null,
          replyDatetime: null
        })
        .then(({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
          }
          this.alertMsg = msg;
          this.alert = true;
          this.$router.push(`/QnAread?qnaNo=${this.$route.query.qnaNo}`);
        })
        .catch(() => {
          this.alertMsg = "삭제 처리시 에러가 발생했습니다.";
          this.alert = true;
        });
    });
  }
};
</script>

<style></style>
