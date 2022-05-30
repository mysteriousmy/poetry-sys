<template>
  <div>
    <a-button size="large" class="editable-add-btn" @click="handleAdd">
      <a-icon type="plus" />
      新增名言名句
    </a-button>
    <a-table
      :loading="loading"
      :columns="columns"
      :data-source="data"
      rowKey="id"
    >
      <span slot="action" slot-scope="text, record, index">
        <a-button @click="handleUpdate(record)" type="link"
          ><a-icon type="edit" /> 修改</a-button
        >
        <a-button @click="handleDelete(record, index)" type="link"
          ><a-icon type="delete" /> 删除</a-button
        >
      </span>
      <span slot="pic" slot-scope="text, record">
        <img style="width: 50px; heigth: 50px" :src="record.rhesisBackground" />
      </span>
    </a-table>

    <a-modal title="名言名句信息" :closable="false" :visible="rhesisVisible">
      <a-form-model ref="ruleForm" :model="rhesis">

        <a-form-model-item label="名句所属诗词" prop="desctext">
          <a-select v-model="rhesis.poemId" placeholder="请选择名句所属的诗词" @change="handleChange(rhesis.poemId)">
            <a-select-option
              v-for="(item, index) in Poem"
              :value="item.poemId"
              :key="index"
              >{{ item.poemTitle }}</a-select-option
            >
          </a-select>
        </a-form-model-item>
        <a-form-model-item label="名言名句内容" prop="desctext" v-show="currentPoem.length > 0">
          <a-select v-model="rhesis.rhesisContent" placeholder="请选择诗句">
            <a-select-option
                v-for="(item, index) in currentPoem"
                :value="item"
                :key="index"
            >{{ item }}</a-select-option
            >
          </a-select>
        </a-form-model-item>
        <upload-image-base-64
          ref="UploadImageBase64"
          :limitNum="1"
          @change="change"
          :defaultImageList="rhesis.rhesisBackground"
        />
      </a-form-model>
      <template slot="footer">
        <a-button key="back" @click="rhesisVisible = false"> 取消 </a-button>
        <a-button
          key="submit"
          type="primary"
          v-show="submitType == 0"
          :loading="modalLoading"
          @click="submitrhesis"
        >
          提交
        </a-button>
        <a-button
          key="update"
          v-show="submitType == 1"
          type="primary"
          :loading="modalLoading"
          @click="updaterhesis"
        >
          更新
        </a-button>
      </template>
    </a-modal>
  </div>
</template>

<script>
import {
  DeleterhesisById,
  FindAllrhesis,
  Saverhesis,
  Updaterhesis,
} from "@/api/rhesis";
import { FindAllpoem } from "@/api/poem";
import UploadImageBase64 from "../../components/UploadImageBase64.vue";

const columns = [
  {
    title: "背景配图",
    dataIndex: "rhesisBackground",
    key: "rhesisBackground",
    scopedSlots: { customRender: "pic" },
  },

  {
    title: "名言内容",
    dataIndex: "rhesisContent",
    key: "rhesisContent",
  },
  {
    title: "操作",
    key: "action",
    scopedSlots: { customRender: "action" },
  },
];

const data = [];

export default {
  components: { UploadImageBase64 },
  data() {
    return {
      submitType: 0,
      rhesis: {},
      Poem: [],
      currentPoem: [],
      loading: false,
      modalLoading: false,
      rhesisVisible: false,
      rhesisLoading: false,
      data: [],
      columns,
    };
  },

  mounted() {
    this.loadTableData();
    this.loadPoem();
  },

  methods: {
    loadPoem() {
      FindAllpoem().then((res) => {
        setTimeout(() => {
          this.loading = false;
          this.Poem = res.data;
          console.log(this.data);
        }, 600);
      });
    },
    loadTableData() {
      this.loading = true;
      FindAllrhesis().then((res) => {
        setTimeout(() => {
          this.loading = false;
          this.data = res.data;
        }, 600);
      });
    },
    change(e) {
      this.rhesis.rhesisBackground = e;
    },
    submitrhesis() {
      this.modalLoading = true;
      console.log(this.rhesis);
      this.rhesis.rhesisBackground = this.rhesis.rhesisBackground[0];
      Saverhesis(this.rhesis).then((res) => {
        if (res.code == 200) {
          setTimeout(() => {
            this.modalLoading = false;
            this.rhesisVisible = false;
            this.$message.success("诗人信息提交成功");
            this.loadTableData();
          }, 600);
        } else {
          setTimeout(() => {
            this.modalLoading = false;
          }, 600);
        }
      });
    },
    updaterhesis() {
      this.modalLoading = true;
      this.rhesis.rhesisBackground = this.rhesis.rhesisBackground[0];
      Updaterhesis(this.rhesis).then((res) => {
        if (res.code == 200) {
          setTimeout(() => {
            this.modalLoading = false;
            this.rhesisVisible = false;
            this.$message.success("诗人信息提交成功");
            this.loadTableData();
          }, 600);
        } else {
          setTimeout(() => {
            this.modalLoading = false;
          }, 600);
        }
      });
    },
    handleDelete(r, index) {
      DeleterhesisById(r.rhesisId).then((res) => {
        if (res.code == 200) this.$message.success("诗人信息删除成功");
        this.loadTableData();
      });
      console.log(index);
    },
    handleAdd() {
      this.rhesis = {};
      this.submitType = 0;
      this.rhesisVisible = true;
    },
    handleUpdate(r) {
      r.rhesisBackground = [r.rhesisBackground];
      this.rhesis = r;
      this.submitType = 1;
      this.rhesisVisible = true;
    },
    handleChange(r){
      console.log(r);
      let poem = this.Poem.find(p => p.poemId === r);
      this.currentPoem = poem.poemContent.split("\\");
    }
  },
};
</script>

<style scoped>
.editable-add-btn {
  margin-bottom: 15px;
}
</style>
