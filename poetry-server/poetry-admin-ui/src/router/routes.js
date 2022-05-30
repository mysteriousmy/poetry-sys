import Layout from "@/layout/Index";

const routes = [
  //公共布局下的路由
  {
    path: "/",
    component: Layout,
    children: [
      {
        path: "",
        redirect: "/admin/dynasty",
        meta: { auth: true },
      },
      {
        path: "admin/dynasty",
        component: () => import("@/views/basics/Dynasty"),
        meta: { auth: true },
      },
      {
        path: "admin/poets",
        component: () => import("@/views/basics/Poets"),
        meta: { auth: true },
      },
      {
        path: "admin/poem",
        component: () => import("@/views/basics/Poems"),
        meta: { auth: true },
      },
      {
        path: "admin/rhesis",
        component: () => import("@/views/basics/Rhesis"),
        meta: { auth: true },
      },
      {
        path: "admin/poemList",
        component: () => import("@/views/basics/PoemLists"),
        meta: { auth: true },
      },
      {
        path: "admins",
        component: () => import("@/views/basics/Admins"),
        meta: { auth: true },
      },
      {
        path: "exit",
        component: () => import("@/views/basics/Exit"),
        meta: { auth: true },
      },

      //403
      {
        path: "403",
        component: () => import("@/views/error/403"),
        meta: { auth: true },
      },
      //404
      {
        path: "404",
        component: () => import("@/views/error/404"),
        meta: { auth: true },
      },
      //500
      {
        path: "500",
        component: () => import("@/views/error/500"),
        meta: { auth: true },
      },
    ],
  },

  //登录页
  {
    path: "/login",
    name: "Login",
    component: () => import("@/views/Login"),
    meta: { auth: false },
  },

  //初始化
  {
    path: "/init",
    name: "Init",
    component: () => import("@/views/Init"),
    meta: { auth: false },
  },

  //404
  {
    path: "*",
    redirect: "/404",
  },
];

export default routes;
