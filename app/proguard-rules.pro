# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).
-dontoptimize
-dontpreverify
# Note that if you want to enable optimization, you cannot just
# include optimization flags in your own project configuration file;
# instead you will need to point to the
# "proguard-android-optimize.txt" file instead of this one from your
# project.properties file.

-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
native <methods>;
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
void set*(***);
*** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}

-keepclassmembers class * implements android.os.Parcelable {
public static final android.os.Parcelable$Creator CREATOR;
}

-keepclassmembers class **.R$* {
public static <fields>;
}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**

# Understand the @Keep support annotation.
-keep class android.support.annotation.Keep

-keep @android.support.annotation.Keep class * {*;}

-keepclasseswithmembers class * {
@android.support.annotation.Keep <methods>;
}

-keepclasseswithmembers class * {
@android.support.annotation.Keep <fields>;
}

-keepclasseswithmembers class * {
@android.support.annotation.Keep <init>(...);
}
#移除log
#-assumenosideeffects class android.util.Log{
#    public static int v(...);
#    public static int i(...);
#    public static int d(...);
#    public static int w(...);
#    public static int e(...);
#}

-keepattributes Signature #范型
#native方法不混淆
-keepclasseswithmembernames class * {
native <methods>;
}
#v4包不混淆
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }
#Gson混淆配置
-keep class sun.misc.Unsafe { *; }
-keep class com.idea.fifaalarmclock.entity.***
-keep class com.google.gson.** { *; }
#JavaBean
-keepclassmembers public class cn.net.duqian.bean.** {
void set*(***);
*** get*();
}
-keep class com.xx.duqian_cloud.JavaScriptInterface { *; }#webview js

#忽略 libiary 混淆
-keep class io.vov.vitamio.** { *; }

#butterknife不混淆
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
@butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
@butterknife.* <methods>;
}
-keepclassmembers class * {
public <init> (org.json.JSONObject);
}
#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
-keep interface okhttp3.**{*;}

# okhttp
-keepattributes SourceFile,LineNumberTable
-keep class com.parse.*{ *; }
-dontwarn com.parse.**
-dontwarn com.squareup.picasso.**
-keepclasseswithmembernames class * {
native <methods>;
}


#okio
-dontwarn okio.**
-keep class okio.**{*;}
-keep interface okio.**{*;}

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-dontwarn rx.**
-keep class rx.**{*;}

#greendao
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties

# If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
# If you do not use Rx:
-dontwarn rx.**


#greenrobot.eventbus
-keepattributes *Annotation*
-keepclassmembers class ** {
@org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
<init>(java.lang.Throwable);
}
-dontwarn com.tencent.smtt.export.external.**
-keep class com.tencent.smtt.export.external.**{
*;
}
-keep class com.tencent.tbs.video.interfaces.IUserStateChangedListener {
*;
}
-keep class com.tencent.smtt.sdk.CacheManager {
public *;
}
-keep class com.tencent.smtt.sdk.CookieManager {
public *;
}
-keep class com.tencent.smtt.sdk.WebHistoryItem {
public *;
}
-keep class com.tencent.smtt.sdk.WebViewDatabase {
public *;
}
-keep class com.tencent.smtt.sdk.WebBackForwardList {
public *;
}
-keep public class com.tencent.smtt.sdk.WebView {
public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebView$HitTestResult {
public static final <fields>;
public java.lang.String getExtra();
public int getType();
}
-keep public class com.tencent.smtt.sdk.WebView$WebViewTransport {
public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebView$PictureListener {
public <fields>;
public <methods>;
}
-keepattributes InnerClasses


-keep public enum com.tencent.smtt.sdk.WebSettings$** {
*;
}
-keep public enum com.tencent.smtt.sdk.QbSdk$** {
*;
}
-keep public class com.tencent.smtt.sdk.WebSettings {
public *;
}


-keep public class com.tencent.smtt.sdk.ValueCallback {
public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebViewClient {
public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.DownloadListener {
public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebChromeClient {
public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebChromeClient$FileChooserParams {
public <fields>;
public <methods>;
}
-keep class com.tencent.smtt.sdk.SystemWebChromeClient{
public *;
}
# 1. extension interfaces should be apparent
-keep public class com.tencent.smtt.export.external.extension.interfaces.* {
public protected *;
}


# 2. interfaces should be apparent
-keep public class com.tencent.smtt.export.external.interfaces.* {
public protected *;
}


-keep public class com.tencent.smtt.sdk.WebViewCallbackClient {
public protected *;
}


-keep public class com.tencent.smtt.sdk.WebStorage$QuotaUpdater {
public <fields>;
public <methods>;
}


-keep public class com.tencent.smtt.sdk.WebIconDatabase {
public <fields>;
public <methods>;
}


-keep public class com.tencent.smtt.sdk.WebStorage {
public <fields>;
public <methods>;
}


-keep public class com.tencent.smtt.sdk.DownloadListener {
public <fields>;
public <methods>;
}


-keep public class com.tencent.smtt.sdk.QbSdk {
public <fields>;
public <methods>;
}


-keep public class com.tencent.smtt.sdk.QbSdk$PreInitCallback {
public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.CookieSyncManager {
public <fields>;
public <methods>;
}


-keep public class com.tencent.smtt.sdk.Tbs* {
public <fields>;
public <methods>;
}


-keep public class com.tencent.smtt.utils.LogFileUtils {
public <fields>;
public <methods>;
}


-keep public class com.tencent.smtt.utils.TbsLog {
public <fields>;
public <methods>;
}


-keep public class com.tencent.smtt.utils.TbsLogClient {
public <fields>;
public <methods>;
}


-keep public class com.tencent.smtt.sdk.CookieSyncManager {
public <fields>;
public <methods>;
}


# Added for game demos
-keep public class com.tencent.smtt.sdk.TBSGamePlayer {
public <fields>;
public <methods>;
}


-keep public class com.tencent.smtt.sdk.TBSGamePlayerClient* {
public <fields>;
public <methods>;
}


-keep public class com.tencent.smtt.sdk.TBSGamePlayerClientExtension {
public <fields>;
public <methods>;
}


-keep public class com.tencent.smtt.sdk.TBSGamePlayerService* {
public <fields>;
public <methods>;
}


-keep public class com.tencent.smtt.utils.Apn {
public <fields>;
public <methods>;
}
-keep class com.tencent.smtt.** {
*;
}
# end
-keep public class com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension {
public <fields>;
public <methods>;
}


-keep class MTT.ThirdAppInfoNew {
*;
}


-keep class com.tencent.mtt.MttTraceEvent {
*;
}


# Game related
-keep public class com.tencent.smtt.gamesdk.* {
public protected *;
}


-keep public class com.tencent.smtt.sdk.TBSGameBooter {
public <fields>;
public <methods>;
}


-keep public class com.tencent.smtt.sdk.TBSGameBaseActivity {
public protected *;
}


-keep public class com.tencent.smtt.sdk.TBSGameBaseActivityProxy {
public protected *;
}


-keep public class com.tencent.smtt.gamesdk.internal.TBSGameServiceClient {
public *;
}


# webView处理，项目中没有使用到webView忽略即可,webview与JS交互内部类不能混淆
-keepclassmembers class com.show.you.WebVerfiedActivity$MyHandler {
public *;
}
-keepattributes *JavascriptInterface*

