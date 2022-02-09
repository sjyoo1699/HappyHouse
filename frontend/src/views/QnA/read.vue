<template>
  <div>
    <detail
      :qnaContent="item.qnaContent"
      :qnaTitle="item.qnaTitle"
      :qnaUserid="item.qnaUserid"
      :qnaDatetime="item.qnaDatetime"
      :qnaNo="item.qnaNo"
      :replyUserid="item.replyUserid"
      :replyContent="item.replyContent"
      :replyDatetime="item.replyDatetime"
    />
  </div>
</template>

<script>
import http from "@/util/http-common";
import Detail from "@/components/QnA/Detail.vue";
export default {
  name: "QnAread",
  components: {
    Detail
  },
  data: function() {
    return {
      item: {}
    };
  },
  created() {
    http.get(`/qna/${this.$route.query.qnaNo}`).then(({ data }) => {
      this.item = data;
      console.dir(data);
    });
  }
};
</script>

<style></style>
