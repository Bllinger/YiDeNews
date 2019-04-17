# News

#### 前言

谨以此项目送给那些即将踏入Android开发领域的小伙伴们，希望我的项目可以让你们更一步的了解Android开发。基础很好的小伙伴可以绕道了，这个项目不太适合你们！<br>

#### 项目介绍

基于Android平台的新闻阅读App,数据来源聚合数据，官网地址：https://www.juhe.cn/docs/api/id/235 ， 新闻大致分为：头条，社会，国内等九大类型，采用侧滑菜单的模式，完美的实现了：<br>
1. 基于侧滑菜单的沉浸式状态栏；
2. App冷启动导致启动白屏的解决方案；
3. RecyclerView.Adapter的自己封装头部，尾部，空白页面；
4. App的适配问题；
5. 利用GreenDao3.0去实现App的收藏功能；
6. 对新闻详情页面中的WebView进行优化和处理，避免内存泄露；
7. 采用MVP架构，对RxJava2.0+Retrofit网络请求框架进行封装，在BaseActivity中对于Presenter的添加和移除均做好处理，避免View移除之后的内存泄漏。
8. 如若页面无法请求数据，请在聚合数据申请自己的KEY替换Config中的BASE_KEY,如若未解决，请联系我！

#### 软件架构以及开发中遇到的问题

1.采用MVP+RaJava2.0+Retrofit作为软件的架构，对控制器Presenter进行封装，在BaseActivity的onCreate()和onDestroy()方法中可以对Presenter进行初始化和解除绑定，避免在View失效的情况下产生内除泄露，对于MVP的相关知识点推荐几篇文章：<br>
  * [深入讲解Android MVP框架，附一个很屌的实现MVP架构的开源库Ted Mosby](https://www.jianshu.com/p/02ca76bbad90)<br>
  * [改造 Android 官方架构组件 ViewModel](https://www.jianshu.com/p/11869306f62d)<br>
  * [Google官方架构组件引入MVPArms](https://github.com/JessYanCoding/MVPArms)<br>
  * [传统MVP用在项目中是真的方便还是累赘?](https://www.jianshu.com/p/ac51c9b88af3)<br>
  * [看完不会写MVP架构我跪搓板](https://www.jianshu.com/p/4aa820b14c03)<br>

2.App应用适配。在实际的公司项目开发中，适配对于Android开发者来说是一个避免不了的问题，本人整理了现在技术领域中最快捷有效的三种适配方式，便于读者了解和选用：<br>
  * [首先咱们得了解px，dp，dpi的区别吧？](https://www.jianshu.com/p/b60c6bd26661)<br>
  * [鸿洋的Auto适配方案，推荐用于写Demo和毕业设计使用，公司项目还是算了，毕竟问题还是比较多，性能稍微差了点，不过学一学思想和代码还是很不错的！](https://github.com/hongyangAndroid/AndroidAutoLayout)<br>
  * [Android 目前最稳定和高效的UI适配方案(也是作者极力推荐的)](https://www.jianshu.com/p/a4b8e4c5d9b0)<br>
  * [今日头条Android客户端是配新方案，既然是新的，问题还是有的，性能没话说，只是切换成其他语言好像有点问题](https://www.jianshu.com/p/4254ea9d1b27)<br>

3.关于App启动时候白屏和黑屏，侧滑菜单的沉浸式状态栏的实现，以及App数据库插件GreenDao3.0的基本使用:<br>
  * 关于App启动时候的白屏可以在启动页SplashActivity的Theme中设置android:windowBackground图片，详细可以阅读以下文章:<br>
    * [android冷启动白屏问题](https://www.jianshu.com/p/9495ba92c138)<br>
    * [Android启动界面优化技巧-Splash Screens的正确方式](https://www.jianshu.com/p/cea2864bb587)<br>
  * 关于侧滑菜单的沉浸式状态栏，如下两张图是对该问题最好的诠释：<br>
    * ![完美沉浸式，图片中的侧滑菜单的图片完美延伸至状态栏](https://images.gitee.com/uploads/images/2018/0807/142915_036f55b0_1198940.png "status_1.png")<br>
    * ![非完美沉浸式，侧滑菜单中的图片无法延伸至状态栏中](https://images.gitee.com/uploads/images/2018/0807/143135_05694ae3_1198940.png "status_2.png")<br>
  * 关于数据库插件GreenDao3.0需要注意的地方就是一些初始化和增删改查一些地方，百度都可以搜索到基本的用法，这里不再赘述。<br>

#### 项目深入

1.根据项目中的Module BaseAdapter可以体会一下封装的概念，无论是RecyclerView适配器还是MVP架构的封装，作者都是看过很多代码并且在公司开发中总结出来的；<br>
2.项目中的img/凤凰新闻.pxcp文件是UI尺寸标注文件，百度下载像素大厨打开文件即可看到尺寸标注，新入门的小白可以照着标注文件学一学布局，可以更快速的上手；<br>

#### 应用试玩

![码子](https://www.pgyer.com/app/qrcode/Jcnb)

#### 总结

* Android开发本身就是一件非常容易入门但是难以深入的一项技术，知识面覆盖非常广，技术杂乱难以深入，但是希望有我的项目可以帮到素不相识的你，愿你在Android开发的道路上越走越宽，越走越平坦。<br>
* 项目地址：https://gitee.com/YiDer/News
* 码云个人地址： https://gitee.com/YiDer
* 联系方式：1070138445
* 广告：本人承接各个学历的计算机毕业设计，主要方向是Java，Android以及Web，公司信誉和个人资料担保，开发周期固定，保障顾客利益，避免各类骗术！






