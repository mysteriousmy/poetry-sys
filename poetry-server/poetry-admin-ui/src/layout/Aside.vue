<template>
  <a-layout-sider
    :style="{ overflow: 'auto', height: '100vh', position: 'fixed', left: 0 }"
    width="220"
  >
    <div class="logo">
      <img src="../assets/logo.jpg" alt="" />
      诗词管理系统
    </div>
    <a-menu theme="dark" mode="inline">
      <a-sub-menu v-for="(item, index) in menus" :key="index">
        <span slot="title">
          <a-icon :type="item.icon" />
          <span>{{ item.title }}</span>
        </span>
        <a-menu-item v-for="menu in item.children" :key="menu.title" v-show="menu.show">
          <router-link :to="menu.path" >
            {{ menu.title }}
          </router-link>
        </a-menu-item>
      </a-sub-menu>
    </a-menu>
  </a-layout-sider>
</template>

<script>
export default {
  methods: {
    check(){
      let currentAdmin = localStorage.getItem("user");
      currentAdmin = JSON.parse(currentAdmin);
      if(currentAdmin.username !== "admin"){
        this.menus[1].children[0].show = false;
      }
    }
  },
  mounted() {
    this.check();
  },
  data() {
    return {
      showAdminOnly: true,
      menus: [
        {
          title: "基础信息管理",
          icon: "home",
          children: [
            { title: "朝代管理", path: "/admin/dynasty", show: true },
            { title: "诗人管理", path: "/admin/poets", show: true },
            { title: "诗词管理", path: "/admin/poem", show: true },
            { title: "诗词文集管理", path: "/admin/poemList", show: true },
            { title: "名句名言管理", path: "/admin/rhesis", show: true },
          ],
        },
        {
          title: "系统管理",
          icon: "tool",
          children: [
            { title: "后台人员管理", path: "/admins", show: true},
            { title: "退出登录", path: "/exit", show: true },
          ],
        },
      ],
    };
  },
};
</script>

<style scoped>
#components-layout-demo-fixed-sider .logo {
  padding: 10px 15px;
  height: 50px;
  font-size: 15px;
  margin: 16px;
  color: #ffffff;
  letter-spacing: 2px;
}

.ant-menu {
  letter-spacing: 1px;
}

.logo img {
  width: 32px;
  height: 32px;
  margin-right: 5px;
}
</style>
