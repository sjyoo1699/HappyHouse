<template>
  <div>
    <v-simple-table>
      <template v-slot:default borderless>
        <tbody>
            <tr>
                <th class="text-left">번호</th>
                <td class="text-left">{{qnaNo}}</td>
            </tr>
            <tr>
                <th class="text-left">아이디</th>
                <td class="text-left">{{qnaUserid}}</td>
            </tr>
            <tr>
                <th class="text-left">제목</th>
                <td class="text-left">{{qnaTitle}}</td>
            </tr>
            <tr>
                <th class="text-left">날짜</th>
                <td class="text-left">{{getFormatDate(qnaDatetime)}}</td>
            </tr>
            <tr>
                <td colspan="2">
                    <v-p>
                        {{qnaContent}}
                    </v-p>
                </td>
            </tr>
        </tbody>
      </template>
    </v-simple-table>
    <br />
    <br />
<v-row v-if="replyContent != null">
  <v-icon>mdi-reply</v-icon>
</v-row>
<v-simple-table v-if="replyContent != null">
      <template v-slot:default borderless>
        <tbody>
            <tr>
                <th class="text-left">답변자</th>
                <td class="text-left">{{replyUserid}}</td>
            </tr>
            <tr>
                <th class="text-left">답변날짜</th>
                <td class="text-left">{{getFormatDate(replyDatetime)}}</td>
            </tr>
            <tr>
                <td colspan="2">
                    <v-p>
                        {{replyContent}}
                    </v-p>
                </td>
            </tr>
        </tbody>
      </template>
    </v-simple-table>
    <br />
    <br />
    
    <div class="text-center">
      <v-btn to="/qna">목록</v-btn>
      <v-btn @click="updateQnA" v-if="replyContent == null">본문수정</v-btn>
      <v-btn @click="deleteQnA">본문삭제</v-btn>
      <v-btn
        @click="addReply"
        v-if="replyContent == null"
      >답변등록</v-btn>
      <v-btn
        @click="updateReply"
        v-else
      >답변수정</v-btn>
    </div>
  </div>
</template>

<script>
import moment from "moment";
export default {
  name: "detail",
  props: {
    qnaNo: { type: Number },
    qnaUserid: { type: String },
    qnaTitle: { type: String },
    qnaContent: { type: String },
    qnaDatetime: { type: String },
    replyContent: { type: String },
    replyDatetime: { type: String },
    replyUserid: { type: String }
  },
  methods: {
    getFormatDate(qnaDatetime) {
      return moment(new Date(qnaDatetime)).format("YYYY.MM.DD HH:mm:ss");
    },
    updateQnA() {
      this.$router.push(`/QnAupdate?qnaNo=${this.qnaNo}`)
    },
    deleteQnA() {
      this.$router.push(`/QnAdelete?qnaNo=${this.qnaNo}`)
    },
    addReply() {
      this.$router.push(`/QnAaddReply?qnaNo=${this.qnaNo}`)
    },
    updateReply() {
      this.$router.push(`/QnAupdateReply?qnaNo=${this.qnaNo}`)
    }
  }
};
</script>
