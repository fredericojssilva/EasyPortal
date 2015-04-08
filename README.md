# EasyPortal

EasyPortal is an simple Android library that helps pass data through activities, eliminate the boilerplate of put and get intent extras.

```java
class FirstActivity extends Activity {

    @IPortalOut
    public String string1;
    @IPortalOut
    public String string2;
    @IPortalOut
    public int int1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		...
		string1="balobilo";
		string2="pussara";
		int1=3;
    }

	public void onButtonClick(){
		EasyPortal.startActivity(this, SecondActivity.class);
		//or EasyPortal.startActivityForResult(this, SecondActivity.class, 3);	
	}
}
```

```java
class SecondActivity extends Activity {

    @IPortalIn
    public String string1;
    @IPortalIn(name="string2")
    public String cuteString;
    @IPortalIn
    public int int1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		...
		EasyPortal.fill(this);
    }

}
```

##Download

**Gradle:**

```
compile 'net.fredericosilva:easyportal:1.0.1'
```
##License
```
Copyright 2015 Frederico Silva

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```