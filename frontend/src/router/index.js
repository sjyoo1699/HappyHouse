import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import NoticeList from "../views/NoticeList.vue";
import NoticeRead from "../views/NoticeRead.vue";
import NoticeCreate from "../views/NoticeCreate.vue";
import DealList from "../views/DealList.vue";
import Qna from "../views/QnA.vue";
import QnACreate from "../views/QnA/create.vue";
import QnARead from "../views/QnA/read.vue";
import QnAUpdate from "../views/QnA/update.vue";
import QnADelete from "../views/QnA/delete.vue";
import QnAAddReply from "../views/QnA/addReply.vue";
import QnAUpdateReply from "../views/QnA/updateReply.vue";
import QnADeleteReply from "../views/QnA/deleteReply.vue";
import RecommendList from "../views/RecommendList.vue";
import store from "../store/modules/user.js";

Vue.use(VueRouter);

const requireAuth = () => (to, from, next) => {
  if (store.state.profilename !== '') {
    return next();
  }
  next('/');
};

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue")
  },
  {
    path: "/noticeList",
    name: "noticeList",
    component: NoticeList,
    beforeEnter: requireAuth()
  },
  {
    path: "/noticeRead",
    name: "noticeRead",
    component: NoticeRead,
    beforeEnter: requireAuth(),
  },
  {
    path: "/noticeCreate",
    name: "noticeCreate",
    component: NoticeCreate,
    beforeEnter: requireAuth(),
  },
  {
    path: "/dealList",
    name: "dealList",
    component: DealList,
    beforeEnter: requireAuth(),
  },
  {
    path: "/qna",
    name: "qna",
    component: Qna,
    beforeEnter: requireAuth(),
  },
  {
    path: "/QnAcreate",
    name: "QnAcreate",
    component: QnACreate,
    beforeEnter: requireAuth(),
  },
  {
    path: "/QnAread",
    name: "QnAread",
    component: QnARead,
    beforeEnter: requireAuth(),
  },
  {
    path: "/QnAupdate",
    name: "QnAupdate",
    component: QnAUpdate,
    beforeEnter: requireAuth(),
  },
  {
    path: "/QnAdelete",
    name: "QnAdelete",
    component: QnADelete,
    beforeEnter: requireAuth(),
  },
  {
    path: "/QnAaddReply",
    name: "QnAaddReply",
    component: QnAAddReply,
    beforeEnter: requireAuth(),
  },
  {
    path: "/QnAupdateReply",
    name: "QnAupdateReply",
    component: QnAUpdateReply,
    beforeEnter: requireAuth(),
  },
  {
    path: "/QnAdeleteReply",
    name: "QnAdeleteReply",
    component: QnADeleteReply,
    beforeEnter: requireAuth(),
  },
  {
    path: "/recommendList",
    name: "recommendList",
    component: RecommendList,
    beforeEnter: requireAuth(),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
