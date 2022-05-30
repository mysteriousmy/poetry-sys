<template>
  <div>
    <a-button size="large" class="editable-add-btn" @click="handleAdd">
      <a-icon type="plus" />
      新增诗词文集
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
        <a-button @click="handleAddPoem(record)" type="link"
          ><a-icon type="edit" /> 添加诗词</a-button
        >
        <a-button @click="handleDelete(record, index)" type="link"
          ><a-icon type="delete" /> 删除</a-button
        >
      </span>
      <span slot="pic" slot-scope="text, record">
        <img style="width: 50px; heigth: 50px" :src="record.poemListImage" />
      </span>
    </a-table>
    <a-modal title="添加诗词" :closable="false" :visible="addpoemVisible">
      <a-form-model ref="ruleForm" :model="poemList">
        <a-form-model-item>
          <a-tag
            v-for="(item, index) in poemList.poems"
            :value="item.poemId"
            :key="index"
            closable
            @close="handleDeletePoem(item.poemId)"
            >{{ item.poemTitle }}</a-tag
          >
        </a-form-model-item>

        <a-form-model-item label="名句所属诗词" prop="desctext">
          <a-select v-model="poem_s" placeholder="请选择要添加的诗词">
            <a-select-option
              v-for="(item, index) in Poem"
              :value="item.poemId"
              :key="index"
              >{{ item.poemTitle }}</a-select-option
            >
          </a-select>
        </a-form-model-item>
        <a-button @click="handleAddPoemReal">+添加诗词</a-button>
      </a-form-model>
      <template slot="footer">
        <a-button key="back" @click="addpoemVisible = false"> 取消 </a-button>
        <a-button
          key="updatePoems"
          type="primary"
          :loading="modalLoading"
          @click="updatepoemList(1)"
        >
          更新
        </a-button>
      </template>
    </a-modal>
    <a-modal title="名言名句信息" :closable="false" :visible="poemListVisible">
      <a-form-model ref="ruleForm" :model="poemList">
        <a-form-model-item label="文集标题" prop="desctext">
          <a-input v-model="poemList.poemListTitle" type="textarea" />
        </a-form-model-item>
        <a-form-model-item label="文集描述" prop="desctext">
          <a-input v-model="poemList.poemListDesc" type="textarea" />
        </a-form-model-item>
        <upload-image-base-64
          ref="UploadImageBase64"
          :limitNum="1"
          @change="change"
          :defaultImageList="poemList.poemListImage"
        />
      </a-form-model>
      <template slot="footer">
        <a-button key="back" @click="poemListVisible = false"> 取消 </a-button>
        <a-button
          key="submit"
          type="primary"
          v-show="submitType == 0"
          :loading="modalLoading"
          @click="submitpoemList"
        >
          提交
        </a-button>
        <a-button
          key="update"
          v-show="submitType == 1"
          type="primary"
          :loading="modalLoading"
          @click="updatepoemList(0)"
        >
          更新
        </a-button>
      </template>
    </a-modal>
  </div>
</template>

<script>
import {
  DeletepoemListById,
  FindAllpoemList,
  SavepoemList,
  UpdatepoemList,
} from "@/api/poemList";
import { FindAllpoem } from "@/api/poem";
import UploadImageBase64 from "../../components/UploadImageBase64.vue";
import {message} from "ant-design-vue";

const columns = [
  {
    title: "封面配图",
    dataIndex: "poemListImage",
    key: "poemListImage",
    scopedSlots: { customRender: "pic" },
  },

  {
    title: "文集标题",
    dataIndex: "poemListTitle",
    key: "poemListTitle",
  },
  {
    title: "文集描述",
    dataIndex: "poemListDesc",
    key: "poemListDesc",
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
      poemList: {},
      Poem: [],
      poem_s: "",
      addpoemVisible: false,
      loading: false,
      modalLoading: false,
      poemListVisible: false,
      poemListLoading: false,
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
      FindAllpoemList().then((res) => {
        setTimeout(() => {
          this.loading = false;
          this.data = res.data;
        }, 600);
      });
    },
    change(e) {
      this.poemList.poemListImage = e;
    },
    submitpoemList() {
      this.modalLoading = true;
      console.log(this.poemList);
      this.poemList.poemListImage = this.poemList.poemListImage[0];
      SavepoemList(this.poemList).then((res) => {
        if (res.code == 200) {
          setTimeout(() => {
            this.modalLoading = false;
            this.poemListVisible = false;
            this.$message.success("诗人信息提交成功");
            this.loadTableData();
          }, 600);
        } else {
          setTimeout(() => {
            this.modalLoading = false;
          }, 600);
        }
      }).catch(e => {
        message.error("不可添加重复的诗词进入文集！");
      });
    },
    updatepoemList(t) {
      this.modalLoading = true;
      if (t === 0) {
        let image = this.poemList.poemListImage[0];
        this.poemList.poemListImage = image;
      }

      UpdatepoemList(this.poemList).then((res) => {
        if (res.code == 200) {
          setTimeout(() => {
            this.modalLoading = false;
            this.poemListVisible = false;
            this.addpoemVisible = false;
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
    handleAddPoem(r) {
      r.poemListImage = r.poemListImage;
      this.poems = r.poems;
      this.poemList = r;
      this.addpoemVisible = true;
    },
    handleDelete(r, index) {
      DeletepoemListById(r.poemListId).then((res) => {
        if (res.code == 200) this.$message.success("诗人信息删除成功");
        this.loadTableData();
      });
      console.log(index);
    },
    handleAdd() {
      this.poemList = {};
      this.submitType = 0;
      this.poemListVisible = true;
    },
    handleUpdate(r) {
      r.poemListImage = [r.poemListImage];
      this.poemList = r;
      this.submitType = 1;
      this.poemListVisible = true;
    },
    handleAddPoemReal(r) {
      let tmp = this.poemList;
      let pd = this.Poem.find((p) => p.poemId === this.poem_s);
      if (tmp.poems === null) {
        tmp.poems = [];
      }
      tmp.poems.push(pd);
    },
    handleDeletePoem(r) {
      let tmp = this.poemList;
      let pd = this.Poem.find((p) => p.poemId === r);

      tmp.poems.pop(pd);
    },
  },
};
</script>

<style scoped>
.editable-add-btn {
  margin-bottom: 15px;
}
</style>
