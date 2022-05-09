<template>
  <div>
    <a-button size="large" class="editable-add-btn" @click="handleAdd">
      <a-icon type="plus" />
      新增诗人
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
        <img style="width: 50px; heigth: 50px" :src="record.poetsPhoto" />
      </span>
    </a-table>

    <a-modal title="Poets信息" :closable="false" :visible="PoetsVisible">
      <a-form-model ref="ruleForm" :model="Poets">
        <a-form-model-item label="诗人名称" prop="desctext">
          <a-input v-model="Poets.poetsName" type="textarea" />
        </a-form-model-item>
        <a-form-model-item label="诗人生平介绍" prop="desctext">
          <a-input v-model="Poets.poetsDesc" type="textarea" />
        </a-form-model-item>
        <a-form-model-item label="诗人所属朝代" prop="desctext">
          <a-select
            v-model="Poets.dynastyId"
            placeholder="请选择诗人所属的朝代"
          >
            <a-select-option
              v-for="(item, index) in Dynasty"
              :value="item.dynastyId"
              :key="index"
              >{{ item.dynastyName }}</a-select-option
            >
          </a-select>
        </a-form-model-item>
        <upload-image-base-64
          ref="UploadImageBase64"
          :limitNum="1"
          @change="change"
          :defaultImageList="Poets.poetsPhoto"
        />
      </a-form-model>
      <template slot="footer">
        <a-button key="back" @click="PoetsVisible = false"> 取消 </a-button>
        <a-button
          key="submit"
          type="primary"
          v-show="submitType == 0"
          :loading="modalLoading"
          @click="submitPoets"
        >
          提交
        </a-button>
        <a-button
          key="update"
          v-show="submitType == 1"
          type="primary"
          :loading="modalLoading"
          @click="updatePoets"
        >
          更新
        </a-button>
      </template>
    </a-modal>
  </div>
</template>

<script>
import {
  DeletePoetsById,
  FindAllPoets,
  SavePoets,
  UpdatePoets,
} from "@/api/poets";
import UploadImageBase64 from "../../components/UploadImageBase64.vue";
import { FindAlldynasty } from "../../api/dynasty";

const columns = [
  {
    title: "诗人画像",
    dataIndex: "",
    key: "poetsPhoto",
    scopedSlots: { customRender: "pic" },
  },

  {
    title: "诗人名称",
    dataIndex: "poetsName",
    key: "poetsName",
  },
  {
    title: "诗人生平介绍",
    dataIndex: "poetsDesc",

    key: "poetsDesc",
  },
  {
    title: "诗人所属朝代",
    dataIndex: "dynasty.dynastyName",
    key: "dynasty.dynastyName",
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
      Poets: {},
      Dynasty: [],
      loading: false,
      modalLoading: false,
      PoetsVisible: false,
      PoetsLoading: false,
      data: [],
      columns,
    };
  },

  mounted() {
    this.loadTableData();
    this.loadDynasty();
  },

  methods: {
    loadDynasty() {
      FindAlldynasty().then((res) => {
        console.log(res.data);
        this.Dynasty = res.data;
      });
    },
    loadTableData() {
      this.loading = true;
      FindAllPoets().then((res) => {
        setTimeout(() => {
          this.loading = false;
          this.data = res.data;
        }, 600);
      });
    },
    change(e) {
      this.Poets.poetsPhoto = e;
    },
    submitPoets() {
      this.modalLoading = true;
      this.Poets.poetsPhoto = this.Poets.poetsPhoto[0];
      SavePoets(this.Poets).then((res) => {
        if (res.code == 200) {
          setTimeout(() => {
            this.modalLoading = false;
            this.PoetsVisible = false;
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
    updatePoets() {
      this.modalLoading = true;
      this.Poets.poetsPhoto = this.Poets.poetsPhoto[0];
      UpdatePoets(this.Poets).then((res) => {
        if (res.code == 200) {
          setTimeout(() => {
            this.modalLoading = false;
            this.PoetsVisible = false;
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
      DeletePoetsById(r.poetsId).then((res) => {
        if (res.code == 200) this.$message.success("诗人信息删除成功");
        this.loadTableData();
      });
      console.log(index);
    },
    handleAdd() {
      this.Poets = {};
      this.submitType = 0;
      this.PoetsVisible = true;
    },
    handleUpdate(r) {
      r.poetsPhoto = [r.poetsPhoto];
      this.Poets = r;
      this.submitType = 1;
      this.PoetsVisible = true;
    },
  },
};
</script>

<style scoped>
.editable-add-btn {
  margin-bottom: 15px;
}
</style>
