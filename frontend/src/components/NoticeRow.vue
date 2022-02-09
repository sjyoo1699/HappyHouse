<template>
  <tr @click="emitShowDetail">
    <td>{{ articleno }}</td>
    <td>{{ id }}</td>
    <td>{{subject}}</td>
    <td>{{ getFormatDate(regtime) }}</td>
  </tr>
</template>

<script>
import moment from "moment";
export default {
  name: "row",
  props: {
    articleno: { type: Number },
    id: { type: String },
    subject: { type: String },
    regtime: { type: String }
  },
  methods: {
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY.MM.DD");
    },
    emitShowDetail() {
      this.$emit("showDetail", this.articleno);
    }
  }
};
</script>
