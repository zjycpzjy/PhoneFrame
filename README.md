##  BaseActivity和BaseFragment的基本封装
     
## Android6.0运行时 权限适配
       1. 在点击Button 的时候，来触发检测 请求 权限流程
       2. 注意在MainFest里面声明 nomal和degerous权限
       3. 注意权限组的概念
       4. 注意 api的使用，
       5. 对devicesSDK、tagarSdkVersion、的设置
       6. 根据官方文档的规则来写  提前 把 项目用的 权限 整理出来
       7. 需要适配 多种机型   例如：
          1. 小米4  定位权限 和 联系人 权限。
              1. 用api方法 直接请求 权限 有以下情况：
                  1. 我在请求定位 权限的时候，手动设置 为 “询问” 模式。然后去 请求 这个权限。系统没有没有 提示框。
                  2. 按照上面 流程读写联系人 模式 也没有
                  3. 录音权限 有系统提示提示框。
                  4. 在询问模式的时候，用ContextCompat.checkSelfPermission 返回 -1 。
                  5. 用ActivityCompat.requestPermissions 方法请求完 调用 onRequestPermissionsResult  就直接返回 0。应该 返回-1
              2. 在以上 代码里 加上 百度定位代码  有以下情况：
                  1. 在 拒绝权限 的情况下，ContextCompat.checkSelfPermission  返回 0。应该返回-1
                  2. 加上定位代码后，在询问模式下，有系统提示框 弹出。
          2. 三星 s6 手机可以正常运行
          3. 华为note8 手机可以正常运行
## 自定义ViewGroup和View
      1. 自定义TOPBAR
  

