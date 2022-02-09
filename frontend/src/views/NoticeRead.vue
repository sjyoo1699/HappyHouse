<template>
  <div>
    <detail
      :articleno="item.articleno"
      :id="item.id"
      :subject="item.subject"
      :content="item.content"
      :regtime="item.regtime"
    />
  </div>
</template>

<script>
import http from "@/util/http-common";
import Detail from "@/components/NoticeDetail.vue";
export default {
  name: "NoticeRead",
  components: {
    Detail
  },
  data: function() {
    return {
      item: {}
    };
  },
  created() {
    http
      .get(`/notice/content/${this.$route.query.articleno}`)
      .then(({ data }) => {
        this.item = data;
        console.dir(data);
      });
  }
};
</script>

<style></style>
