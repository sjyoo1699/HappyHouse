<template>
  <tr @click="emitShowDetail">
    <td>{{ qnaNo }}</td>
    <td>{{ qnaTitle }}</td>
    <td>{{ qnaUserid }}</td>
    <td>{{ getFormatDate(qnaDatetime) }}</td>
  </tr>
</template>

<script>
import moment from "moment";
export default {
  name: "row",
  props: {
    qnaNo: { type: Number },
    qnaUserid: { type: String },
    qnaTitle: { type: String },
    qnaDatetime: { type: String }
  },
  methods: {
    getFormatDate(qnaDatetime) {
      return moment(new Date(qnaDatetime)).format("YYYY.MM.DD");
    },
    emitShowDetail() {
      this.$emit("showDetail", this.qnaNo);
    }
  }
};
</script>
