<template>
  <div>
    <a-button size="large" class="editable-add-btn" @click="handleAdd">
      <a-icon type="plus" />
      新增朝代
    </a-button>
    <a-table
      :loading="loading"
      :columns="columns"
      :data-source="data"
      rowKey="id"
    >
      <a slot="name" slot-scope="text">{{ text }}</a>
      <span slot="customTitle"><a-icon type="smile-o" /> 朝代名称</span>
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

    <a-modal title="朝代信息" :closable="false" :visible="dynastyVisible">
      <a-form-model ref="ruleForm" :model="dynasty">
        <a-form-model-item ref="name" v-show="false" label="朝代id" prop="name">
          <a-input v-model="dynasty.dynastyId" />
        </a-form-model-item>
        <a-form-model-item ref="name" label="朝代名称" prop="name">
          <a-input v-model="dynasty.dynastyName" />
        </a-form-model-item>
        <a-form-model-item label="描述信息" prop="description">
          <a-input v-model="dynasty.dynastyDesc" type="textarea" />
        </a-form-model-item>
        <!-- <upload-image-base-64
          ref="UploadImageBase64"
          :limitNum="1"
          @change="change"
          :defaultImageList="dynasty.imgUrl"
        /> -->
      </a-form-model>
      <template slot="footer">
        <a-button key="back" @click="dynastyVisible = false"> 返回 </a-button>
        <a-button
          key="submit"
          type="primary"
          v-show="submitType == 0"
          :loading="modalLoading"
          @click="submitdynasty"
        >
          提交
        </a-button>
        <a-button
          key="update"
          type="primary"
          v-show="submitType == 1"
          :loading="modalLoading"
          @click="updatedynasty"
        >
          更新
        </a-button>
      </template>
    </a-modal>
  </div>
</template>

<script>
import {
  DeletedynastyById,
  FindAlldynasty,
  Savedynasty,
  Updatedynasty,
} from "@/api/dynasty";
import UploadImageBase64 from "../../components/UploadImageBase64.vue";
import { message } from "ant-design-vue";

const columns = [
  {
    title: "记录编号",
    dataIndex: "dynastyId",
    key: "dynastyId",
  },
  {
    dataIndex: "dynastyName",
    key: "dynastyName",
    slots: { title: "customTitle" },
    scopedSlots: { customRender: "name" },
  },
  {
    title: "描述信息",
    dataIndex: "dynastyDesc",
    key: "dynastyDesc",
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
      submitType: 0,
      dynasty: {
        dynastyId: "",
        dynastyName: "",
        dynastyDesc: "朝代简介",
      },
      loading: false,
      modalLoading: false,
      dynastyVisible: false,
      dynastyLoading: false,
      data: [],
      columns,
    };
  },

  mounted() {
    this.loadTableData();
  },

  methods: {
    loadTableData() {
      this.loading = true;
      FindAlldynasty().then((res) => {
        setTimeout(() => {
          this.loading = false;
          this.data = res.data;
          console.log(this.data);
        }, 600);
      });
    },
    // change(e) {
    //   console.log("e", e);
    //   this.dynasty.imgUrl = e;
    //   console.log(this.dynasty.imgUrl);
    // },
    updatedynasty() {
      this.modalLoading = true;
      console.log(this.dynasty);
      Updatedynasty(this.dynasty).then((res) => {
        if (res.data === true) {
          setTimeout(() => {
            this.modalLoading = false;
            this.dynastyVisible = false;
            this.$message.success("朝代信息更新成功");
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
    submitdynasty() {
      this.modalLoading = true;
      console.log(this.dynasty);
      Savedynasty(this.dynasty).then((res) => {
        if (res.data === true) {
          setTimeout(() => {
            this.modalLoading = false;
            this.dynastyVisible = false;
            this.$message.success("朝代信息提交成功");
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
      this.dynasty = {};
      this.submitType = 0;
      this.dynastyVisible = true;
    },
    handleDelete(r, index) {
      DeletedynastyById(r.dynastyId).then((res) => {
        if (res.status) this.$message.success("朝代信息删除成功");
        this.loadTableData();
      });
      console.log(index);
    },

    handleUpdate(r) {
      console.log(r);
      this.dynasty = r;
      this.submitType = 1;
      console.log(this.submitType);
      this.dynastyVisible = true;
    },
  },
};
</script>

<style scoped>
.editable-add-btn {
  margin-bottom: 15px;
}
</style>
