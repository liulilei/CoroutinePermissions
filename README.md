# CoroutinePermissions
Kotlin 协程 一行代码动态申请权限（CoroutinePermissions）

## 前言
自6.0之后 所有的敏感权限都需要动态申请，市面上随之出现比较多的权限框架，easypermissions(google出品)、AndPermission、RxPermissions(结合RxJava)。随着 kotlin的逐渐普及，使用协程的人也越来越多，因此就有了本文，结合协程一行代码动态申请权限。
通过创建Fragment结合google提供的easypermissions库封装动态申请权限 成功后继续操作。

- 实现dialog提示语阐述权限用途
- 支持禁用后跳转到应用设置界面手动开启权限
- 实现协程一行代码 顺序执行 逻辑清晰明了

## 上代码
`````
private val permsSd = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
        
CoroutineScope(Dispatchers.Main).launch {
        try {
            requestPermissionsForResult(*permsSd, rationale = "为了更好的提供服务，需要获取存储空间权限")
            //todo 成功 接下来处理逻辑  比如 打开相册
            startActivity(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI))
        } catch (e: Exception) {
        }
}
`````
没错就是这么简单  配合viemodel封装一层try catch 更丝滑！
没有回调，没有链式调用，从上到下执行  简单易用！

## 如何使用
在项目主工程的build.gradle文件中加入：
````
allprojects {
    repositories {
        .......
        maven {
            url  "https://dl.bintray.com/liul1/maven"
        }
        ......
}
````
在需要使用moudel的build.gradle文件中加入：
````
implementation 'com.github.lilei:coroutine-permissions:1.0.0'
````

需要看源码的朋友移步：[https://github.com/liulilei/CoroutinePermissions](https://github.com/liulilei/CoroutinePermissions)

如果对您有帮助，请动动手指点个star，谢谢！



#### 本文参考资料：
[https://github.com/diao-jian/CoroutinePermissions](https://github.com/diao-jian/CoroutinePermissions)

[https://github.com/florent37/InlineActivityResult](https://github.com/florent37/InlineActivityResult)



