# react-native-blur-image

React Native Image with cross platform blurRadius property.

## Installation

```bash
npm i -S react-native-blur-image
react-native link
```

## Usage

```javascript
import BlurImage from 'react-native-blur-image'

<BlurImage
  source={{ uri:'https://facebook.github.io/react/img/logo_og.png' }}
  style={{ width: 147, height: 77 }}
  blurRadius={100}
/>
```

