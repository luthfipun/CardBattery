[![](https://jitpack.io/v/luthfipun/CardBattery.svg)](https://jitpack.io/#luthfipun/CardBattery)

# Android - CardBattery

Android library for view card battery, brightness, volumes like on ios

<img src="https://github.com/luthfipun/CardBattery/blob/master/ss/ss.png" width="250" />

## Install

Add it in your root build.gradle at the end of repositories:

```
allprojects {
  repositories {
  ...
  maven { url 'https://jitpack.io' }
  }
}
```


Add the dependency
```
dependencies {
  implementation 'com.github.luthfipun:CardBattery:Latest-Version'
}
```



## Usage

Call to your layout

```
<luthfipun.cardbattrey.CardBattery
  android:id="@+id/cardbattery"
  android:layout_width="100dp"
  android:layout_height="300dp"
  app:cbProgress="50"
  app:cbRadius="120"
  app:cbProgress_color="#2196F3"
  app:cbBackground="#1E03A9F4" />
```

Or you can call in code

```
val cardbattery = findView...

cardbattery.setProgress(50)
cardbattery.setRadius(50)
...


```
