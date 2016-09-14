import { requireNativeComponent, View } from 'react-native'
import { PropTypes } from 'react'

var iface = {
    name: 'BlurImageView',
    propTypes: {
        ...View.propTypes,
        blurRadius: PropTypes.number,
        sampling: PropTypes.number,
        source: PropTypes.object,
    },
    defaultProps: {
        blurRadius: 40,
        sampling: 50,
    },
}

export default requireNativeComponent('RCTBlurImageView', iface)

