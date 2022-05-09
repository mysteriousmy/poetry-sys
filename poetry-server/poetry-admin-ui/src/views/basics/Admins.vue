<template>
  <div>
    <a-button size="large" class="editable-add-btn" @click="handleAdd">
      <a-icon type="plus" />
      新增管理员
    </a-button>
    <a-table
      :loading="loading"
      :columns="columns"
      :data-source="data"
      rowKey="id"
    >
      <a slot="name" slot-scope="text">{{ text }}</a>
      <span slot="customTitle"><a-icon type="smile-o" /> 管理员登录名</span>
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

    <a-modal title="管理员信息" :closable="false" :visible="adminsVisible">
      <a-form-model ref="ruleForm" :model="admins">
        <a-form-model-item
          ref="name"
          v-show="false"
          label="管理员id"
          prop="name"
        >
          <a-input v-model="admins.adminsId" />
        </a-form-model-item>
        <a-form-model-item ref="name" label="管理员名称" prop="name">
          <a-input v-model="admins.adminsName" />
        </a-form-model-item>
        <a-form-model-item
          ref="name"
          label="管理员密码(默认12345678)"
          prop="name"
        >
          <a-input v-model="admins.adminsPassword" />
        </a-form-model-item>
        <a-form-model-item ref="name" label="管理员邮箱" prop="name">
          <a-input v-model="admins.adminsEmail" />
        </a-form-model-item>
        <!-- <upload-image-base-64
          ref="UploadImageBase64"
          :limitNum="1"
          @change="change"
          :defaultImageList="admins.imgUrl"
        /> -->
      </a-form-model>
      <template slot="footer">
        <a-button key="back" @click="adminsVisible = false"> 返回 </a-button>
        <a-button
          key="submit"
          type="primary"
          v-show="submitType == 0"
          :loading="modalLoading"
          @click="submitadmins"
        >
          提交
        </a-button>
        <a-button
          key="update"
          type="primary"
          v-show="submitType == 1"
          :loading="modalLoading"
          @click="updateadmins"
        >
          更新
        </a-button>
      </template>
    </a-modal>
  </div>
</template>

<script>
import {
  DeleteadminsById,
  FindAlladmins,
  Saveadmins,
  Updateadmins,
} from "@/api/admin";
import UploadImageBase64 from "../../components/UploadImageBase64.vue";

const columns = [
  {
    dataIndex: "adminsName",
    key: "adminsName",
    slots: { title: "customTitle" },
    scopedSlots: { customRender: "name" },
  },
  {
    title: "管理员邮箱",
    dataIndex: "adminsEmail",
    key: "adminsEmail",
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
      admins: {
        adminsId: "",
        adminsName: "",
        adminsEmail: "",
      },
      loading: false,
      modalLoading: false,
      adminsVisible: false,
      adminsLoading: false,
      data: [],
      columns,
    };
  },

  mounted() {
    this.loadTableData();
  },

  methods: {
    loadTableData() {
      let currentAdmin = localStorage.getItem("user");
      currentAdmin = JSON.parse(currentAdmin);
      if(currentAdmin.username !== "admin"){
        this.$router.push("/admin/dynasty");
      }
      this.loading = true;
      FindAlladmins().then((res) => {
        setTimeout(() => {
          this.loading = false;
          this.data = res.data;
          console.log(this.data);
        }, 600);
      });
    },
    // change(e) {
    //   console.log("e", e);
    //   this.admins.imgUrl = e;
    //   console.log(this.admins.imgUrl);
    // },
    updateadmins() {
      this.modalLoading = true;
      this.admins.adminsType = 1;
      console.log(this.admins);
      Updateadmins(this.admins).then((res) => {
        if (res.code === 200) {
          setTimeout(() => {
            this.modalLoading = false;
            this.adminsVisible = false;
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
    submitadmins() {
      this.modalLoading = true;
      console.log(this.admins);
      this.admins.adminsType = 1;
      Saveadmins(this.admins).then((res) => {
        if (res.code === 200) {
          setTimeout(() => {
            this.modalLoading = false;
            this.adminsVisible = false;
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
      this.admins = {};
      this.submitType = 0;
      this.adminsVisible = true;
    },
    handleDelete(r, index) {
      DeleteadminsById(r.adminsId).then((res) => {
        if (res.code === 200) this.$message.success("诗词信息删除成功");
        this.loadTableData();
      });
      console.log(index);
    },

    handleUpdate(r) {
      console.log(r);
      this.admins = r;
      this.submitType = 1;
      console.log(this.submitType);
      this.adminsVisible = true;
    },
  },
};
</script>

<style scoped>
.editable-add-btn {
  margin-bottom: 15px;
}
</style>
