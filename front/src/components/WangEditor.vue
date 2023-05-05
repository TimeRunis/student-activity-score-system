<template>
  <div style="border: 1px solid #ccc;">
    <Toolbar
        style="border-bottom: 1px solid #ccc"
        :editor="editor"
        :defaultConfig="toolbarConfig"
        :mode="mode"
    />
    <Editor
        style="height: 500px; overflow-y: hidden;"
        v-model="html"
        :defaultConfig="editorConfig"
        :mode="mode"
        @onCreated="onCreated"
    />
  </div>
</template>

<script>
import "@wangeditor/editor/dist/css/style.css"
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'

export default {
  name:"WangEditor",
  components: { Editor, Toolbar },
  data() {
    return {
      editor: null,
      html: '',
      toolbarConfig: { },
      editorConfig: {
        placeholder: '请输入内容...' ,
        MENU_CONF:{
          uploadImage:{
            server: 'http://localhost:8080/fileUpload',
            //表单文件名
            fieldName: 'file',
            // 添加header
            headers: {
              token:this.$cookies.get('token')
            },
            // 选择文件时的类型限制
            allowedFileTypes: ['image/*'],
            customInsert(res,insertFn){
              for(let i in res['data']){
                insertFn(res['data'][i])
              }
            }
          },
          uploadVideo:{
            server: 'http://localhost:8080/fileUpload',
            //表单文件名
            fieldName: 'file',
            // 添加header
            headers: {
              token:this.$cookies.get('token')
            },
            // 单个文件的最大体积限制
            maxFileSize: 200 * 1024 * 1024,
            // 选择文件时的类型限制
            allowedFileTypes: ['video/*'],
            customInsert(res,insertFn){
              for(let i in res['data']){
                insertFn(res['data'][i])
              }
            }
          }
        }
      },
      mode: 'default',
    }
  },
  methods: {
    onCreated(editor) {
      this.editor = Object.seal(editor)
    },
    setContent(content) {
      this.html=content;
    },
  },
  mounted() {
  },
  beforeDestroy() {
    const editor = this.editor
    if (editor == null) return
    editor.destroy()
  }
}

</script>
<style scoped>

</style>
