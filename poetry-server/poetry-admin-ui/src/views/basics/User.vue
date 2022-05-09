<template>
  <div>
    <a-table
      :loading="loading"
      :columns="columns"
      :data-source="data"
      rowKey="id"
    >
      <a slot="username" slot-scope="text">{{ text }}</a>
      <span slot="customTitle"><a-icon type="smile-o" /> 用户名称</span>
      <span slot="action" slot-scope="text, record, index">
        <a-button @click="handleUpdate(record)" type="link"
          ><a-icon type="edit" /> 更新信息</a-button
        >
        <a-divider type="vertical" />
        <a-button @click="handleDelete(record, index)" type="link"
          ><a-icon type="delete" /> 删除</a-button
        >
      </span>
      <span slot="pic" slot-scope="text, record">
        <img style="width:50px;heigth:50px" :src="'http://localhost:8081/assets/login/tou' + record.avatar + '.png'" />
      </span>
    </a-table>

    <a-modal title="用户信息" :closable="false" :visible="UserVisible">
      <a-form-model ref="ruleForm" :model="User">
        <a-form-model-item ref="id" v-show="false" label="userid" prop="id">
          <a-input v-model="User.id" />
        </a-form-model-item>
        <a-form-model-item ref="username" label="用户名" prop="username">
          <a-input v-model="User.username" />
        </a-form-model-item>
        <a-form-model-item ref="password" label="密码" prop="password">
          <a-input v-model="User.password" />
        </a-form-model-item>
        <a-form-model-item ref="phonenum" label="手机号" prop="phonenum">
          <a-input v-model="User.phonenum" />
        </a-form-model-item>
      </a-form-model>
      <template slot="footer">
        <a-button key="back" @click="UserVisible = false">
          取消
        </a-button>
        <a-button
          key="submit"
          type="primary"
          :loading="modalLoading"
          @click="submitUser"
        >
          提交
        </a-button>
      </template>
    </a-modal>
  </div>
</template>

<script>
import { DeleteUserById,FindAllUser, SaveUser } from "@/api/user";
import UploadImageBase64 from "../../components/UploadImageBase64.vue";

const columns = [
{
    title: "用户头像",
    dataIndex: "avatar",
    key: "avatar",
    scopedSlots: { customRender: "pic" },
  },
  {
    dataIndex: "username",
    key: "username",
    slots: { title: "customTitle" },
    scopedSlots: { customRender: "username" },
  },
  {
    title: "密码",
    dataIndex: "password",
    key: "password",
  },
  {
    title: "用户手机号",
    key: "phonenum",
    dataIndex: "phonenum",
  },
  {
    title: "账户创建时间",
    dataIndex: "createAt",
    key: "createAt",
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
      User: {
        id:"",
        username: "",
        password: "",
        phonenum: "",
        avatar: [],
      },
      loading: false,
      modalLoading: false,
      UserVisible: false,
      UserLoading: false,
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
      FindAllUser().then((res) => {
        setTimeout(() => {
          this.loading = false;
          this.data = res.data;
          this.User.id = this.data.id;
        }, 600);
      });
    },
    change(e) {
      console.log("e", e);
      this.User.avatar = e;
      console.log(this.User.avatar);
    },
    submitUser() {
      this.modalLoading = true;
      SaveUser(this.User).then((res) => {
        if (res.status) {
          setTimeout(() => {
            this.modalLoading = false;
            this.UserVisible = false;
            this.$message.success("用户信息提交成功");
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
      DeleteUserById(r.id).then((res) => {
        if (res.status) this.$message.success("用户信息删除成功");
        this.loadTableData();
      });
      console.log(index);
    },

    handleUpdate(r) {
      this.User = r;
      this.UserVisible = true;
    },
  },
};
</script>

<style scoped>
.editable-add-btn {
  margin-bottom: 15px;
}
</style>
