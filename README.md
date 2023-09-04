# lifeAssistant
初学安卓：生活助手app：人们越来越依赖移动应用程序来满足他们的各种需求：记录笔记、进行虚拟对话以及查看天气是常见的应用场景，因此开发一个综合性的应用程序，集成这些功能，希望能够为用户提供便捷和全面的体验。
## 软件技术点

为了更好的符合现在的安卓系统开发要求。生活助手软件基于**MVVM****（Model – View - ViewModel****）高级项目架构**模式，采用**Jetpack**开发组件工具，**Material Design**界面设计语言， **Kotlin****语言**开发。具体来说软件使用了Room架构，LiveData，ViewModel等新工具，BottomNavigationView、SwipeRefreshLayout、RecyclerView、CoordinatorLayout、Toolbar、DrawerLayout、Fragment等各种新布局，实现对夜间模式和MaterralYou的适配，借助Fragment实现了**平板和手机两种布局**。

MVVM架构使软件易于扩展，具体来说将项目分为logic和ui两个包：logic包用于存放业务逻辑相关的代码，ui包用于存放界面展示相关的代码。其中logic包中又包含了dao、model、network这3个子包，分别用于存放数据访问对象、对象模型以及网络相关的代码。

## 软件功能
软件主要功能包括：
- 1. 查看天气：生活助手采用彩云天气提供的开放API，集成天气预报。用户可以输入城市名，以获取位置的天气情况。显示的天气信息包括实时天气、未来几天的天气和本日的感冒、穿衣，实时紫外线、洗车等信息。
- 2.笔记记录：允许用户创建和管理个人笔记。用户可以输入文本内容，并将其保存为笔记。还可以删除和查看已保存的笔记，允许用户对已经删除的笔记进行删除撤销。
- 3.模拟对话：该应用程序将包含一个模拟对话的功能，通过与用户进行虚拟对话来进行记事。

# 页面和功能展示
## 笔记记录界面与功能实现

- 图2-1 平板端笔记记录展示界面
![image](https://github.com/kingwzun/lifeAssistant/assets/75526768/85a9aba9-846f-4a04-a0af-6d6f3ae4d04f)

- 图2-1 手机端笔记记录展示界面
![image](https://github.com/kingwzun/lifeAssistant/assets/75526768/92795172-f9cf-4771-9d01-c8544492b1c1)
![image](https://github.com/kingwzun/lifeAssistant/assets/75526768/2e8688e2-df29-4ffc-b252-3d6b955a3171)
 
- 图2-3添加和修改界面
![image](https://github.com/kingwzun/lifeAssistant/assets/75526768/bbf69a7f-1949-4b7c-84cf-6703e1c409af)

## 查看天气界面与功能实现
 
- 图2-4 天气展示界面
 ![image](https://github.com/kingwzun/lifeAssistant/assets/75526768/b72dbd86-09b8-4276-b680-49419a3266ca)

- 图2-5查找地址界面（未输入）
![image](https://github.com/kingwzun/lifeAssistant/assets/75526768/70e80e24-6797-4a51-b8e0-2e4fbb7b3e6b)

- 图2-6查找地址界面（正在查找中）

![image](https://github.com/kingwzun/lifeAssistant/assets/75526768/84c19b08-96a6-4a9e-ab6f-40318cf7fdf5)
## 模拟对话界面与功能实现

![image](https://github.com/kingwzun/lifeAssistant/assets/75526768/fb96819d-f66b-4a2d-a40e-e0113a673db6)
