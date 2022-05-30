<template>
  <div class="backs">
    <div class="login-box">
      <div>
        <div class="box-header">
          <img class="logo" src="../assets/logo.jpg" alt="" />
          <div class="box-header-t">诗词管理系统</div>
        </div>
        <div class="title">管理员登录</div>
        <a-tabs
          @change="tabClick"
          default-active-key="1"
          :tabBarStyle="{ textAlign: 'center' }"
        >
          <a-tab-pane key="1" tab="密码登陆">
            <a-input
              v-model="form.adminsName"
              size="large"
              style="margin-top: 10px"
              class="input"
              placeholder="用户名"
            >
              <a-icon slot="prefix" type="user" />
            </a-input>
            <a-input-password
              v-model="form.adminsPassword"
              size="large"
              class="input"
              placeholder="密码"
            >
              <a-icon slot="prefix" type="lock" />
            </a-input-password>
          </a-tab-pane>
          <a-tab-pane key="2" tab="验证码登陆" force-render>
            <a-input
              v-model="form.adminsEmail"
              size="large"
              style="margin-top: 10px"
              class="input"
              placeholder="邮箱"
            >
              <a-icon slot="prefix" type="mail" />
            </a-input>
            <div style="display: flex">
              <a-input
                v-model="form.verifyCode"
                size="large"
                class="input"
                placeholder="验证码"
              >
                <a-icon slot="prefix" type="safety-certificate" />
              </a-input>
              <a-button
                class="code-btn"
                :loading="sendLoading"
                @click="sendEmail"
              >
                获取验证码
              </a-button>
            </div>
          </a-tab-pane>
        </a-tabs>
        <a-button
          :loading="submitLoading"
          class="submit-btn"
          type="primary"
          @click="submitLogin"
        >
          确认登陆
        </a-button>
        <div class="des">@Copyright 2022 - 诗词管理系统</div>
      </div>
    </div>
  </div>
</template>

<script>
import { AdminLogin, AdminSendEmail, AdminEmailLogin } from "@/api/admin";
import { IsInit } from "../api/admin";
export default {
  data() {
    return {
      sendLoading: false,
      submitType: "1", //1账号登录 2邮箱登录
      submitLoading: false,
      form: {
        adminsName: "",
        adminsPassword: "",
        adminsEmail: "",
        verifyCode: "",
        type: "",
        remember: false,
      },
    };
  },

  mounted() {
    IsInit().then((res) => {
      console.log(res.data);
      if (!res.data) this.$router.push("/init");
    });
  },

  methods: {
    sendEmail() {
      if (this.checkEmail()) {
        this.sendLoading = true;
        AdminSendEmail(this.form.adminsEmail).then((res) => {
          if (res.data) this.$message.success("验证码发送成功");
          this.sendLoading = false;
        });
      }
    },

    submitLogin() {
      this.form.type = this.submitType === "1" ? "passwrod" : "email";
      if (this.submitType === "1") {
        const data = {
          adminsName: this.form.adminsName,
          adminsPassword: this.form.adminsPassword,
        };
        AdminLogin(data).then((res) => {
          console.log(res.data);
          if (res.code === 200) {
            this.$store.commit("user/saveToken", res.data.token);
            this.$store.commit("user/saveLoginUser", res.data.user);
            setTimeout(() => {
              this.$router.push("/admin/dynasty");
              this.submitLoading = false;
            }, 700);
            this.$message.success("登陆成功");
          }
        });
      } else {
        if (this.checkEmail()) {
          const data = {
            adminsEmail: this.form.adminsEmail,
            verifyCode: this.form.verifyCode,
          };
          AdminEmailLogin(data).then((res) => {
            console.log(res.data);
            if (res.code === 200) {
              this.$store.commit("user/saveToken", res.data.token);
              console.log(res.data.user);
              this.$store.commit("user/saveLoginUser", res.data.user);
              setTimeout(() => {
                this.$router.push("/admin/dynasty");
                this.submitLoading = false;
              }, 700);
              this.$message.success("登陆成功");
            }
          });
        }
      }
    },

    tabClick(key) {
      this.submitType = key;
    },

    checkEmail() {
      const emailRegex = new RegExp(
        "^\\w{3,}(\\.\\w+)*@[A-z0-9]+(\\.[A-z]{2,5}){1,2}$"
      );
      if (!emailRegex.test(this.form.adminsEmail)) {
        this.$message.error("请输入正确格式的邮箱");
        return false;
      } else {
        return true;
      }
    },
  },
};
</script>

<style scoped>
body {
  background: #000000 !important;
}

>>> .ant-tabs-bar {
  border-bottom: none !important;
}

>>> .ant-btn-primary {
  border-color: #5a84fd;
}

.login-box {
  width: 400px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  padding: 20px;
  background: rgba(255, 255, 255, 0.8);
  backdrop-filter: blur(10px);
  border-radius: 6px;
}
.backs {
  position: absolute;
  background: url("../assets/back.jpg");
  width: 100%;
  height: 100%;
}
.box-header {
  display: flex;
}

.box-header-t {
  font-weight: bolder;
  font-size: 30px;
}

.logo {
  width: 44px;
  height: 44px;
  margin-right: 20px;
  margin-left: 43px;
}

>>> .ant-tabs-nav {
  width: 350px;
}

>>> .ant-tabs-ink-bar {
  left: 52px;
}

>>> .ant-input-affix-wrapper .ant-input {
  font-size: 12px !important;
}

.title {
  color: #91949c;
  padding-top: 15px;
  padding-bottom: 35px;
  font-size: 13px;
  text-align: center;
}

.input {
  margin-bottom: 25px;
  font-size: 10px;
}

.code-btn {
  height: 40px;
  margin-left: 30px;
}

.submit-btn {
  letter-spacing: 2px;
  background: #5a84fd;
  width: 100%;
  height: 45px;
}

.des {
  padding-top: 25px;
  font-size: 13px;
  text-align: center;
  color: #91949c;
  letter-spacing: 2px;
}
</style>
