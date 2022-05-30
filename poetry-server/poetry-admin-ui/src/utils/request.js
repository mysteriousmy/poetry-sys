import request from "axios";
import { message } from "ant-design-vue";
import router from "../router/index";
import store from "../store";

let service = request.create({
  baseURL: "http://localhost:8081/api/poetry",
  timeout: 50000,
});
service.interceptors.request.use((req) => {
  let token = localStorage.getItem("token") || "";
  req.baseURL = "http://localhost:8081/api/poetry";
  req.timeout = 5000;
  req.headers.common["Authorization"] = token;
  // req.headers.token = token;
  return req;
});

service.interceptors.response.use(
  (response) => {
    const res = response.data;
    //判断response状态
    if (!res.code || !(res.code === 200)) message.error("请求错误: " + res.msg);

    if (res.code === 403) router.push("/403");

    return res;
  },

  (error) => {
    message.error(error.response.data.errMsg);
    if (error.response.data.errCode === 508) {
      router.push("/login");
      store.commit("user/userLogout");
    }
    // router.push("/500");
    return Promise.reject(error);
  }
);

export default service;
