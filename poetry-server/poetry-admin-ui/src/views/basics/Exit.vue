<template>
  <div>
  </div>
</template>

<script>

export default {
  data() {
    return {
    };
  },

  mounted() {
    this.handleLogout();
  },

  methods: {
    handleLogout() {
      let that = this
      this.$confirm({
        title: '你确定要注销你的登录信息吗?',
        content: '点击确定将删除你在网站保留的登录信息！',
        onOk() {
          that.$router.push('/login')
          that.$store.commit('user/userLogout')
        },
      });
    },
  },
};
</script>

<style scoped>
.editable-add-btn {
  margin-bottom: 15px;
}
</style>
