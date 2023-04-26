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
  props:['content'],
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
            fieldName: 'uploadFiles',
            // 添加header
            headers: {
              token:this.$cookies.get('token')
            },
            // 选择文件时的类型限制，默认为 ['image/*'] 。如不想限制，则设置为 []
            allowedFileTypes: ['image/*'],
            customInsert(res,insertFn){
              for(let i in res['data']){
                insertFn(res['data'][i])
              }
            }

          }
        }
      },
      mode: 'default', // or 'simple'
    }
  },
  methods: {
    onCreated(editor) {
      this.editor = Object.seal(editor) // 一定要用 Object.seal() ，否则会报错
    },
  },
  mounted() {
    // 模拟 ajax 请求，异步渲染编辑器
    setTimeout(() => {
      this.html = this.content
    }, 1500)
  },
  beforeDestroy() {
    const editor = this.editor
    if (editor == null) return
    editor.destroy() // 组件销毁时，及时销毁编辑器
  }
}

</script>
<style scoped>

</style>
