<template>
  <div class="clearfix">
    <a-upload
      :beforeUpload="beforeImageUpload"
      list-type="picture-card"
      :file-list="imageList"
      :multiple="multiple"
      :disabled="disabled"
      @change="handleImageChange"
      @preview="handlePreview"
      :custom-request="customRequest"
    >
      <div v-if="imageList.length < limitNum && !disabled">
        <a-icon type="plus" />
        <div class="ant-upload-text">上传</div>
      </div>
    </a-upload>
    <a-modal :visible="previewVisible" :footer="null" @cancel="handleCancel">
      <img alt="example" style="width: 100%" :src="previewImage" />
    </a-modal>
  </div>
</template>
<script>
function getBase64(file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = (error) => reject(error);
  });
}

export default {
  props: {
    defaultImageList: {
      type: Array,
      default: function() {
        return [];
      },
      required: true,
    },
    fileTypeList: {
      type: Array,
      default: function() {
        return [];
      },
      required: false,
    },
    limitSize: {
      type: Number,
      default: 5,
      required: false,
    },
    limitNum: {
      type: Number,
      default: 20,
      required: false,
    },
    multiple: {
      type: Boolean,
      default: false,
      required: false,
    },
    disabled: {
      type: Boolean,
      default: false,
      required: false,
    },
  },
  data() {
    return {
      previewVisible: false,
      previewImage: "",
      imageList: [],
    };
  },
  watch: {
    defaultImageList(newVal) {
      this.imageList = this.handleData(newVal);
    },
  },
  created() {
    this.imageList = this.handleData(this.defaultImageList);
  },
  methods: {
    // ---------------------------------------------img--start
    beforeImageUpload(file) {
      return new Promise((resolve, reject) => {
        if (!this.fileTypeList) {
          const index = this.fileTypeList.indexOf(file.type);
          if (index > 0) {
            this.$message.error(`您只能上传${this.fileTypeList[index]}文件`);
          }
        }
        const limitSize = file.size / 1024 / 1024 < this.limitSize;
        if (!limitSize) {
          this.$message.error(`文件大小不能大于${this.limitSize}MB`);
          return reject(new Error(`文件大小不能大于${this.limitSize}MB`));
        }
        return resolve(true);
      });
    },
    async handlePreview(file) {
      if (!file.url && !file.preview) {
        file.preview = await getBase64(file.originFileObj);
      }
      this.previewImage = file.url || file.preview;
      this.previewVisible = true;
    },
    handleCancel() {
      this.previewVisible = false;
    },
    customRequest({ action, file, onSuccess, onError, onProgress }) {
      new Promise((resolve) => {
        const fileReader = new FileReader();
        fileReader.readAsDataURL(file);
        fileReader.onload = () => {
          let index = {
            uid: this.genId(5),
            name: file.name,
            status: "done",
            url: fileReader.result,
          };
          this.imageList = [
            ...this.imageList.filter((item) => item.status === "done"),
            index,
          ];
          this.$message.success("图片已传至页面！");
          this.handleChange();
          resolve(fileReader.result);
        };
      });
    },
    handleImageChange(info) {
      let fileList = [...info.fileList];
      this.imageList = fileList;
      this.handleChange();
    },
    handleChange() {
      let index = this.imageList
        .filter((item) => item.url)
        .map((item) => {
          return item.url;
        });
      this.$emit("change", index ? index : []);
    },
    genId(length) {
      return Number(
        Math.random()
          .toString()
          .substr(3, length) + Date.now()
      ).toString(36);
    },
    handleData(list) {
      return list.map((item) => {
        let index = this.genId(5);
        return {
          uid: index,
          name: index,
          status: "done",
          url: item,
        };
      });
    },
    // ---------------------------------------------img--end
  },
};
</script>
<style>
/* you can make up upload button and sample style by using stylesheets */
.ant-upload-select-picture-card i {
  font-size: 32px;
  color: #999;
}

.ant-upload-select-picture-card .ant-upload-text {
  margin-top: 8px;
  color: #666;
}
</style>
