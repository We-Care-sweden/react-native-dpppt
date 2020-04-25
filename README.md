# react-native-dpppt

## Getting started

`$ yarn add https://github.com/chriamue/react-native-dpppt`

### Mostly automatic installation

`$ react-native link react-native-dpppt`

## Usage

Add this to your AndroidManifest.

```xml
<uses-permission android:name="android.permission.BLUETOOTH"/>
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```


```javascript
import Dpppt from 'react-native-dpppt';

// TODO: What to do with the module?
Dpppt.init(APP_ID, true);
Dpppt.start();
Dpppt.stop();
Dpppt.sendIWasExposed(new Date(), 'authCode');
Dpppt.sync();
```
