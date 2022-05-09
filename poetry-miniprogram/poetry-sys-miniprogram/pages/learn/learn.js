// pages/learn/learn.js
const testList = require('./testList.js')
import Api from "../../api/index";
Page({

    /**
     * 页面的初始数据
     */
    data: {
        test_list: []
    },

    async getTestsInWeb(id) {
        let that = this;
        let res = await Api._practice("");
        let str = res.data.match(/<p>(.+?)<\/p>/g);
        let tmp = []
        str.splice(0, 1);
        str.splice(16, 15);
        for (let i = 2; i <= 8; i++) {
            let ress = await Api._practice(`-${i}`);
            let strs = ress.data.match(/<p>(.+?)<\/p>/g);
            strs.forEach(s => {
                if (s !== "<p>&copy;2021&nbsp;文档之家&nbsp;m.doczj.com&nbsp;</p>" && s !== "<p>(A)</p>") {
                    str.push(s)
                }
            })
        }
        str.forEach(s => {
            tmp.push(s.match(/<p>(\S*)<\/p>/)[1]);
        })
        let result = []
        for (let i = 0; i <= tmp.length - 1; i++) {
            let prop = {}
            console.log(tmp[i]);
            if (!tmp[i].includes("A、") && !tmp[i].includes("B、") && !tmp[i].includes("C、") && !tmp[i].includes("D、")) {
                prop.title = tmp[i]
                prop.A = tmp[i + 1].includes("A、") ? tmp[i + 1] : ""
                prop.B = tmp[i + 2].includes("B、") ? tmp[i + 2] : ""
                prop.C = tmp[i + 3].includes("C、") ? tmp[i + 3] : ""
                prop.D = tmp[i + 4].includes("D、") ? tmp[i + 4] : ""
                prop.result = tmp[i].substr(-2, 1)
                result.push(prop)
            }
        }
        console.log(result);
        let final = [...this.data.test_list]
        result.forEach(e => {
            let title = e.title.slice(0, -2);
            title = title.slice(2,title.length);
            final.push({
                test_title: title + ")",
                select: {
                    A: e.A,
                    B: e.B,
                    C: e.C,
                    D: e.D
                },
                result: e.result,
                test: 2,
                user_answer: ""
            })
        })
        let final_data = []
        let item = this.produceNum(0,final.length);
        let ii = 0;
        item.forEach(i => {
            if(final[i].select.A.length !== 0 && final[i].select.B.length !== 0 && final[i].select.C.length !== 0 && final[i].select.D.length !== 0){
                final[i].test_id = ii++;
                final_data.push(final[i])
            }
        })
        console.log(final_data);
        this.setData({
            test_list: final_data
        })
    },
    produceNum(min, max) {
        let arr = [];
        for (let i = 0; i <= 10; i++) {
            let num = Math.floor((Math.random() * (max - min)) + min);
            if (arr.indexOf(num) == -1) {
                arr.push(num);
            } else {
                i--;
            }
        }
        return arr;
    },
    getParenthesesStr(text, start, end) {
        let result = ''
        //字符串拼接 正则表达式文本
        let regex = `/${start}(.+?)${end}/g`;
        //把字符串转换成js代码
        regex = eval(regex);
        let regResult = text.match(regex);
        if (regResult) {
            let item = regResult[0]
            //去除反斜杠
            start = start.replace(/\\/g, "")
            end = end.replace(/\\/g, "")
            if (item) {
                result = item.substring(start.length, item.length - end.length)
            }
        }
        return result
    },
    radioChange(e) {
        console.log(e.detail.value);
        let select = e.detail.value;
        let re = select.split(",");

        let tmp = [...this.data.test_list];
        tmp.forEach(t => {
            if (t.test_id === parseInt(re[0])) {
                t.user_answer = re[1]
            }
        })
        console.log(tmp);
        this.setData({
            test_list: tmp
        })
    },
    submitCheck() {
        let tmp = [...this.data.test_list]
        let check = true;
        for (let t of tmp) {
            if (t.user_answer.length === 0) {
                wx.showModal({
                    title: '警告',
                    content: `请完成第${t.test_id + 1}小题`,
                    showCancel: false
                })
                check = false;
                break;
            }
        }
        return check;
    },
    submit() {
        let that = this;
        let re = this.submitCheck();
        let tmp = [...this.data.test_list]
        if (re) {
            wx.showModal({
                title: "提示",
                content: "确定要提交？",
                success: function (e) {
                    if (e.confirm) {
                        tmp.forEach(t => {
                            if (t.user_answer === t.result) {
                                t.test = 1
                            } else {
                                t.test = 0
                            }
                        })
                        that.setData({
                            test_list: tmp
                        })
                        console.log(tmp);
                    }
                }
            })

        }
    },
    /**
     * 生命周期函数--监听页面加载
     */

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {
        this.getTestsInWeb("")
        this.setData({
            test_list: testList.dataList
        })
        console.log(this.data.test_list);
    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {
        let tmp = [...this.data.test_list]
        tmp.forEach(r => {
            r.user_answer = ""
            r.test = 2
        })
    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {
        let tmp = [...this.data.test_list]
        tmp.forEach(r => {
            r.user_answer = ""
            r.test = 2
        })
    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})