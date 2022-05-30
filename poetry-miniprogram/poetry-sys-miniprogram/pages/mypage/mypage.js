import Api from "../../api/user.js";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    loginOk: true,
    nickName: "",
    avatarUrl: "",
    gender: "",
  },

  //小程序声明周期的可见性函数里面来控制显示
  onShow() {
    let userInfo = wx.getStorageSync("userInfo");
    console.log(userInfo);
    if (userInfo) {
      this.setData({
        loginOk: true,
        nickName: userInfo.nickName, //从缓存中拿数据
        avatarUrl: userInfo.avatarUrl,
        gender: userInfo.gender,
      });
    } else {
      this.setData({
        loginOk: false,
      });
    }
  },
  //微信授权登录
  loadByWechat() {
    wx.getUserProfile({
      desc: "用户完善会员资料",
    })
      .then((res) => {
        //注意：此时不能使用 wx.switchTab，不支持参数传递

        //保存用户信息到数据库

        wx.login({
          success: (loginRes) => {
            let code = loginRes.code;
            wx.request({
              url: `https://api.weixin.qq.com/sns/jscode2session?appid=&secret=&js_code=${code}&grant_type=authorization_code`,
              success: (codeRes) => {
                this.saveUserCloudDatabase(res, codeRes.data.openid);
              },
            });
          },
        });
      })
      .catch((err) => {
        console.log("用户拒绝了微信授权登录", err);
      });
  },
  toCommentPage() {
    wx.navigateTo({
      url: "/pages/myComments/myComments",
    });
  },
  toCollectPage(e) {
    let type = parseInt(e.currentTarget.dataset.type);
    wx.navigateTo({
      url: `/pages/myCollect/myCollect?type=${type}`,
    });
  },
  toHistoryPage() {
    wx.navigateTo({
      url: "/pages/history/history",
    });
  },
  async saveUserCloudDatabase(res, openid) {
    const data = {
      openId: openid,
      userName: res.userInfo.nickName,
      userPhoto: res.userInfo.avatarUrl,
      userGender: res.userInfo.gender === 0 ? 1 : 0,
    };
    let result = await Api._check(data);
    result = JSON.parse(result.data);
    console.log(result);
    if (result.code === 200 && result.data === false) {
      wx.showToast({
        title: "注册了用户",
        icon: "success",
        duration: 2000,
      });
      this.setData({
        loginOk: true,
        nickName: res.userInfo.nickName, //从缓存中拿数据
        avatarUrl: res.userInfo.avatarUrl,
      });
      res.userInfo.openid = openid;
      wx.setStorageSync("userInfo", res.userInfo);
    } else if (result.code === 200 && result.data === true) {
      console.log(result.data);
      this.doLogin(res, openid);
    }
  },
  async doLogin(res, openid) {
    const data = { openId: openid, userName: res.userInfo.nickName };
    let result = await Api._login(data);
    result = JSON.parse(result.data);
    if (result.code === 200) {
      wx.showToast({
        title: "登录成功",
        icon: "success",
        duration: 2000,
      });
      this.setData({
        loginOk: true,
        nickName: res.userInfo.nickName, //从缓存中拿数据
        avatarUrl: res.userInfo.avatarUrl,
      });
      res.userInfo.openid = openid;
      wx.setStorageSync("userInfo", res.userInfo);
    }
  },
  //点击登录
  load() {
    this.loadByWechat();
  },
  about() {
    wx.showModal({
      content: "",
      showCancel: false,
    });
  },
  athletesLogin() {
    wx.navigateTo({
      url: "../athletes/login/athletesLogin",
    });
  },
  adminsLogin() {
    if (wx)
      wx.navigateTo({
        url: "../admins/login/adminLogin",
      });
  },
  //退出登录
  exit() {
    wx.showModal({
      content: "确定退出吗",
    }).then((res) => {
      if (res.confirm) {
        this.setData({
          loginOk: false,
        });
        //清空登录的缓存
        wx.setStorageSync("userInfo", null);
      } else if (res.cancel) {
        console.log("用户点击了取消");
      }
    });
  },
  toLearnPage() {
    wx.navigateTo({
      url: "/pages/learn/learn",
    });
  },
});
