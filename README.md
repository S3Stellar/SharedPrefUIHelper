# SharedPrefUI
<p>Your shared preferences UI helper !</b></p>
	
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/S3Stellar/SuperSpinner/blob/master/LICENSE) &nbsp;&nbsp;&nbsp;&nbsp;
[![](https://jitpack.io/v/S3Stellar/SharedPrefUIHelper.svg)](https://jitpack.io/#S3Stellar/SharedPrefUIHelper)

<div>
  <p align="center">Built with ❤︎ by
	  <a href="https://github.com/S3Stellar">Naor Farag</a></p>
</div>

![Farmers Market Finder Demo](demo/superspinner.gif)

## 💻 Installation
Add this in your app's build.gradle file (Project & module):
```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
	
	
dependencies {
	implementation 'com.github.S3Stellar:SharedPrefUIHelper:0.1.0'
}
```
## ❔ Usage
**Basic Usage**
</br>
Minimum SDk version required: 24

Two methods to use this library:

1) extend from 'MySharedPrefActivity' 

```java
 public class MainActivity extends MySharedPrefActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

```
2)  Use Intent with startActivity
```java

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, MySharedPrefActivity.class));
    }
}
```
And add the activity to the manifest:
```css
	<activity android:name="com.example.mysharedprefui.MySharedPrefActivity" />
```


# 📃 License

    Copyright 2020 Naor Farag

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

### If you like the library, please click on the ★ Star button at the top 😊
