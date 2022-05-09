// pages/poets/list/poetList.js
const random = require('../../../utils/random');
import Api from '../../../api/index';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        current_dynasty: '全部',
        current_type: '全部',
        current_num: 0,
        poem_list: [],
        poem_type: [{
                type_id: 0,
                type_name: "诗",
                color: "",
            },
            {
                type_id: 1,
                type_name: "词",
                color: "",
            },
            {
                type_id: 2,
                type_name: "曲",
                color: "",
            },
            {
                type_id: 3,
                type_name: "文言文",
                color: "",
            },
        ],
        poem_dynasty: [],
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        let tmp = [...this.data.poem_type];
        tmp.forEach(t => {
            t.color = random.RandomColor();
        });
        this.getAllDynasty();
        this.setData({
            poem_type: tmp
        })
        this.getPoemList();
    },
    changeType(e) {
        let type = e.currentTarget.dataset.type;
        this.setData({
            current_type: this.getEnumType(parseInt(type))
        })
        this.getPoemList()
    },
    changeDynasty(e) {
        let dynasty = e.currentTarget.dataset.id;
        if (dynasty === "-1") {
            this.setData({
                current_dynasty: "全部"
            })
        } else {
            let tmp = [...this.data.poem_dynasty];
            let dy = tmp.find(p => p.id === dynasty);
            this.setData({
                current_dynasty: dy.dynasty_name
            })
        }

        this.getPoemList()
    },
    toDetailPage(e){
        let id = e.currentTarget.dataset.id;
        wx.navigateTo({
          url: `/pages/poem/index/poemIndex?id=${id}`,
        })
    },
    async getPoemList() {
        let dynasty;
        if (this.data.current_dynasty === "全部") {
            dynasty = "-1";
        } else {
            console.log(this.data.poem_dynasty);
            dynasty = this.data.poem_dynasty.find(p => p.dynasty_name === this.data.current_dynasty).id;
        }

        let type = this.getTypeEnum(this.data.current_type);
        let result = await Api._poemsList({
            id: dynasty,
            type: type
        });
        result = JSON.parse(result.data).data;
        this.setData({
            poem_list: result
        })
    },
    async getAllDynasty() {
        let result = await Api._dynasty();
        result = JSON.parse(result.data).data;
        let tmp = []
        result.forEach(r => {
            tmp.push({
                dynasty_name: r.dynastyName,
                id: r.dynastyId,
                color: random.RandomColor()
            })
        })
        this.setData({
            poem_dynasty: tmp
        })
    },
    getTypeEnum(type) {
        switch (type) {
            case "全部":
                return 4
            case "诗":
                return 0
            case "词":
                return 1
            case "曲":
                return 2
            case "文言文":
                return 3
        }
    },
    getEnumType(type) {
        switch (type) {
            case 4:
                return "全部"
            case 0:
                return "诗"
            case 1:
                return "词"
            case 2:
                return "曲"
            case 3:
                return "文言文"
        }
    }
})