<template>
  <div>
    <a-button size="large" class="editable-add-btn" @click="handleAdd">
      <a-icon type="plus" />
      新增诗词
    </a-button>
    <a-table
      :loading="loading"
      :columns="columns"
      :data-source="data"
      rowKey="id"
    >
      <a slot="name" slot-scope="text">{{ text }}</a>
      <span slot="customTitle"><a-icon type="smile-o" /> 诗词标题</span>
      <span slot="action" slot-scope="text, record, index">
        <a-button @click="handleUpdate(record)" type="link"
          ><a-icon type="edit" /> 修改</a-button
        >
        <a-divider type="vertical" />
        <a-button @click="handleDelete(record, index)" type="link"
          ><a-icon type="delete" /> 删除</a-button
        >
      </span>
      <span slot="pic" slot-scope="text, record">
        <img
          style="width: 50px; heigth: 50px"
          :src="'http://localhost:8081' + record.imgUrl"
        />
      </span>
    </a-table>

    <a-modal title="诗词信息" :closable="false" :visible="poemVisible">
      <a-form-model ref="ruleForm" :model="poem">
        <a-form-model-item ref="name" v-show="false" label="诗词id" prop="name">
          <a-input v-model="poem.poemId" />
        </a-form-model-item>
        <a-form-model-item ref="name" label="诗词标题" prop="name">
          <a-input v-model="poem.poemTitle" />
        </a-form-model-item>
        <a-form-model-item
          label="诗词内容（换行请添加\符号）"
          prop="description"
        >
          <a-input v-model="poem.poemContent" type="textarea" />
        </a-form-model-item>
        <a-form-model-item
          label="诗词注释（换行请添加\符号）"
          prop="description"
        >
          <a-input v-model="poem.poemAnnotation" type="textarea" />
        </a-form-model-item>
        <a-form-model-item
          label="诗词解析（换行请添加\符号）"
          prop="description"
        >
          <a-input v-model="poem.poemAppreciation" type="textarea" />
        </a-form-model-item>
        <a-form-model-item
          label="诗词创作背景（换行请添加\符号）"
          prop="description"
        >
          <a-input v-model="poem.poemCreateScene" type="textarea" />
        </a-form-model-item>
        <a-form-model-item
          label="诗词翻译（换行请添加\符号）"
          prop="description"
        >
          <a-input v-model="poem.poemTranslates" type="textarea" />
        </a-form-model-item>
        <a-form-model-item label="诗词的类型" prop="desctext">
          <a-select v-model="poem.poemsType" placeholder="请选择诗词的类型">
            <a-select-option
              v-for="(item, index) in type"
              :value="item.typeId"
              :key="index"
              >{{ item.typeName }}</a-select-option
            >
          </a-select>
        </a-form-model-item>
        <a-form-model-item label="诗词作者" prop="desctext">
          <a-select v-model="poem.poetsId" placeholder="请选择诗词的作者">
            <a-select-option
              v-for="(item, index) in poets"
              :value="item.poetsId"
              :key="index"
              >{{ item.poetsName }}</a-select-option
            >
          </a-select>
        </a-form-model-item>
        <!-- <upload-image-base-64
          ref="UploadImageBase64"
          :limitNum="1"
          @change="change"
          :defaultImageList="poem.imgUrl"
        /> -->
      </a-form-model>
      <template slot="footer">
        <a-button key="back" @click="poemVisible = false"> 返回 </a-button>
        <a-button
          key="submit"
          type="primary"
          v-show="submitType == 0"
          :loading="modalLoading"
          @click="submitpoem"
        >
          提交
        </a-button>
        <a-button
          key="update"
          type="primary"
          v-show="submitType == 1"
          :loading="modalLoading"
          @click="updatepoem"
        >
          更新
        </a-button>
      </template>
    </a-modal>
  </div>
</template>

<script>
import { DeletepoemById, FindAllpoem, Savepoem, Updatepoem } from "@/api/poem";
import { FindAllPoets } from "@/api/poets";
import UploadImageBase64 from "../../components/UploadImageBase64.vue";

const columns = [
  {
    dataIndex: "poemTitle",
    key: "poemTitle",
    slots: { title: "customTitle" },
    scopedSlots: { customRender: "name" },
  },
  {
    title: "诗词内容",
    dataIndex: "poemContent",
    key: "poemContent",
  },
  {
    title: "诗词作者",
    dataIndex: "poets.poetsName",
    key: "poets.poetsName",
  },
  {
    title: "创建时间",
    dataIndex: "createTime",
    key: "createTime",
  },

  {
    title: "更多操作",
    key: "action",
    scopedSlots: { customRender: "action" },
  },
];

const data = [];

export default {
  components: { UploadImageBase64 },
  data() {
    return {
      type: [
        {
          typeId: 0,
          typeName: "诗",
        },
        {
          typeId: 1,
          typeName: "词",
        },
        {
          typeId: 2,
          typeName: "曲",
        },
        {
          typeId: 3,
          typeName: "文言文",
        },
      ],
      submitType: 0,
      poem: {
        poemId: "",
        poemName: "",
        poemDesc: "诗词简介",
      },
      loading: false,
      modalLoading: false,
      poemVisible: false,
      poemLoading: false,
      data: [],
      columns,
    };
  },

  mounted() {
    this.loadPoets();
    this.loadTableData();
  },

  methods: {
    loadPoets() {
      FindAllPoets().then((res) => {
        setTimeout(() => {
          this.loading = false;
          this.poets = res.data;
          console.log(res.data);
        }, 600);
      });
    },
    loadTableData() {
      this.loading = true;
      FindAllpoem().then((res) => {
        setTimeout(() => {
          this.loading = false;
          this.data = res.data;
          console.log(this.data);
        }, 600);
      });
    },
    // change(e) {
    //   console.log("e", e);
    //   this.poem.imgUrl = e;
    //   console.log(this.poem.imgUrl);
    // },
    updatepoem() {
      this.modalLoading = true;
      console.log(this.poem);
      Updatepoem(this.poem).then((res) => {
        if (res.code === 200) {
          setTimeout(() => {
            this.modalLoading = false;
            this.poemVisible = false;
            this.$message.success("诗词信息更新成功");
            this.loadTableData();
          }, 600);
        } else {
          setTimeout(() => {
            this.modalLoading = false;
          }, 600);
          this.$message.error(res.data);
        }
      });
    },
    submitpoem() {
      this.modalLoading = true;
      console.log(this.poem);
      Savepoem(this.poem).then((res) => {
        if (res.code === 200) {
          setTimeout(() => {
            this.modalLoading = false;
            this.poemVisible = false;
            this.$message.success("诗词信息提交成功");
            this.loadTableData();
          }, 600);
        } else {
          setTimeout(() => {
            this.modalLoading = false;
          }, 600);
          this.$message.error(res.data);
        }
      });
    },
    handleAdd() {
      this.poem = {};
      this.submitType = 0;
      this.poemVisible = true;
    },
    handleDelete(r, index) {
      DeletepoemById(r.poemId).then((res) => {
        if (res.code === 200) this.$message.success("诗词信息删除成功");
        this.loadTableData();
      });
      console.log(index);
    },

    handleUpdate(r) {
      console.log(r);
      this.poem = r;
      this.submitType = 1;
      console.log(this.submitType);
      this.poemVisible = true;
    },
  },
};
</script>

<style scoped>
.editable-add-btn {
  margin-bottom: 15px;
}
</style>
