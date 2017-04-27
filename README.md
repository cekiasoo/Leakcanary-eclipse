## 一、在 Eclipse 中运行效果
![image](https://github.com/cekiasoo/Leakcanary-eclipse/raw/master/screenshots/1.gif)<br/>
## 二、使用
### （一）导入
> 导入 Leakcanary-watcher、Leakcanary-analyzer、Leakcanary-android， 在当前项目的引用 Leakcanary-android 这个 library。<br/>
![image](https://github.com/cekiasoo/Leakcanary-eclipse/raw/master/screenshots/2.png)<br/>
### （二）在 AndroidManifest 中配置
> 打开当前的 AndroidManifest 添加下面的代码。

        <!-- Leakcanary config start -->
        <service
            android:name="com.squareup.leakcanary.internal.HeapAnalyzerService"
            android:enabled="false"
            android:process=":leakcanary" />
        <service
            android:name="com.squareup.leakcanary.DisplayLeakService"
            android:enabled="false" />

        <activity
            android:name="com.squareup.leakcanary.internal.DisplayLeakActivity"
            android:enabled="false"
            android:icon="@drawable/leak_canary_icon"
            android:label="@string/leak_canary_display_activity_label"
            android:taskAffinity="com.squareup.leakcanary"
            android:theme="@style/leak_canary_LeakCanary.Base" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity
            android:name="com.squareup.leakcanary.internal.RequestStoragePermissionActivity"
            android:enabled="false"
            android:icon="@drawable/leak_canary_icon"
            android:label="@string/leak_canary_storage_permission_activity_label"
            android:taskAffinity="com.squareup.leakcanary"
            android:theme="@style/leak_canary_Theme.Transparent" />
        <!-- Leakcanary config end -->
```
> 还有权限
··· xml
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
### （三）在 Application 中初始化
> 新建一个 Application 类，继承自 Application 的，如果你项目已经有那就不需要新建了。
覆盖 onCreate() 方法，在 onCreate() 方法里调用 LeakCanary 的 install() 方法就可以了。
``` java
public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		if (LeakCanary.isInAnalyzerProcess(this)) {
			// This process is dedicated to LeakCanary for heap analysis.
			// You should not init your app in this process.
			return;
		}
		LeakCanary.install(this);
	}
}
```
## 三、感谢
感谢 [Square](https://github.com/square) 公司 提供的 [Leakcanary](https://github.com/square/leakcanary)。
## 四、Open source licenses
> **Square-LeakCanary:**<br/>

Copyright 2015 Square, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
