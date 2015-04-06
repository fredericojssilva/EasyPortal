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
	}
}
```

```java
class SecondActivity extends Activity {

    @IPortalIn
    public String string1;
    @IPortalIn
    public String string2;
    @IPortalIn
    public int int1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		...
		EasyPortal.fill(this);
    }

}
```
